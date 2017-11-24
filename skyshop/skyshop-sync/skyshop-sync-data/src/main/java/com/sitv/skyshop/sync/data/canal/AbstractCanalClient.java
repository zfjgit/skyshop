package com.sitv.skyshop.sync.data.canal;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.CanalEntry.TransactionBegin;
import com.alibaba.otter.canal.protocol.CanalEntry.TransactionEnd;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sitv.skyshop.common.utils.httpclient4.HttpConnectionManager;

/**
 * @author jianghang 2013-4-15 下午04:17:12
 * @version 1.0.4
 */
public abstract class AbstractCanalClient {

	protected final static Logger logger = LoggerFactory.getLogger(AbstractCanalClient.class);
	protected static final String SEP = SystemUtils.LINE_SEPARATOR;
	protected static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	protected volatile boolean running = false;

	protected Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
		public void uncaughtException(Thread t, Throwable e) {
			logger.error("parse events has an error", e);
		}
	};

	protected Thread thread = null;
	protected CanalConnector connector;
	protected static String context_format = null;
	protected static String row_format = null;
	protected static String transaction_format = null;
	protected String destination;

	@Autowired
	private HttpConnectionManager httpConnectionManager;

	@Autowired
	protected Environment environment;

	// @Autowired
	// protected Sender amqpSender;

	static {
		context_format = SEP + "****************************************************" + SEP;
		context_format += "* Batch Id: [{}] ,count : [{}] , memsize : [{}] , Time : {}" + SEP;
		context_format += "* Start : [{}] " + SEP;
		context_format += "* End : [{}] " + SEP;
		context_format += "****************************************************" + SEP;

		row_format = SEP + "----------------> binlog[{}:{}] , name[{},{}] , eventType : {} , executeTime : {} , delay : {}ms" + SEP;

		transaction_format = SEP + "================> binlog[{}:{}] , executeTime : {} , delay : {}ms" + SEP;
	}

	public AbstractCanalClient(String destination) {
		this(destination, null);
	}

	public AbstractCanalClient(String destination, CanalConnector connector) {
		this.destination = destination;
		this.connector = connector;
	}

	protected void start() {
		Assert.notNull(connector, "connector is null");
		thread = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});

		thread.setUncaughtExceptionHandler(handler);
		thread.start();

		logger.info("running=" + running);

		running = true;
	}

	protected void stop() {
		if (!running) {
			return;
		}
		running = false;
		if (thread != null) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// ignore
			}
		}

		MDC.remove("destination");
	}

	protected void process() {
		int batchSize = 5 * 1024;

		logger.info("running=" + running);

		while (running) {
			try {
				MDC.put("destination", destination);
				connector.connect();
				connector.subscribe();
				while (running) {
					Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
					long batchId = message.getId();
					int size = message.getEntries().size();
					if (batchId == -1 || size == 0) {
						// try {
						// Thread.sleep(1000);
						// } catch (InterruptedException e) {
						// }
					} else {
						// printSummary(message, batchId, size);
						printEntry(message.getEntries());
					}

					connector.ack(batchId); // 提交确认
					// connector.rollback(batchId); // 处理失败, 回滚数据
				}
			} catch (Exception e) {
				logger.error("process error!", e);
			} finally {
				connector.disconnect();
				MDC.remove("destination");
			}
		}

		logger.info("running=" + running);
	}

	protected String buildPositionForDump(Entry entry) {
		long time = entry.getHeader().getExecuteTime();
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return entry.getHeader().getLogfileName() + ":" + entry.getHeader().getLogfileOffset() + ":" + entry.getHeader().getExecuteTime() + "(" + format.format(date) + ")";
	}

	protected void printEntry(List<Entry> entrys) {
		for (Entry entry : entrys) {

			if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
				if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN) {
					TransactionBegin begin = null;
					try {
						begin = TransactionBegin.parseFrom(entry.getStoreValue());
					} catch (InvalidProtocolBufferException e) {
						throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
					}
					logger.debug(" TRANSACTIONBEGIN ----> transaction id: {}", begin.getTransactionId());
				} else if (entry.getEntryType() == EntryType.TRANSACTIONEND) {
					TransactionEnd end = null;
					try {
						end = TransactionEnd.parseFrom(entry.getStoreValue());
					} catch (InvalidProtocolBufferException e) {
						throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
					}
					logger.debug(" TRANSACTIONEND ----> transaction id: {}", end.getTransactionId());
				}

				continue;
			}

			if (entry.getEntryType() == EntryType.ROWDATA) {
				RowChange rowChage = null;
				try {
					rowChage = RowChange.parseFrom(entry.getStoreValue());
				} catch (Exception e) {
					throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
				}

				EventType eventType = rowChage.getEventType();

				if (eventType == EventType.QUERY) {
					logger.debug(" sql ----> " + rowChage.getSql() + SEP);
				}

				if (rowChage.getIsDdl()) {
					logger.debug(" sql ----> " + rowChage.getSql() + SEP);

					JSONObject sql = new JSONObject();

					sql.put("sql", rowChage.getSql());
					sql.put("params", new JSONArray());

					sendSql(sql.toJSONString());
					// amqpSender.send(sql.toJSONString());
					continue;
				}

				for (RowData rowData : rowChage.getRowDatasList()) {
					JSONObject sql = null;
					if (eventType == EventType.DELETE) {
						sql = printDeleteFields(entry.getHeader().getTableName(), rowData.getBeforeColumnsList());

						// printColumn(rowData.getBeforeColumnsList());
					} else if (eventType == EventType.INSERT) {
						sql = printInsertFields(entry.getHeader().getTableName(), rowData.getAfterColumnsList());

						// printColumn(rowData.getAfterColumnsList());
					} else if (eventType == EventType.UPDATE) {
						sql = printUpdateFields(entry.getHeader().getTableName(), rowData.getAfterColumnsList());

						// printColumn(rowData.getAfterColumnsList());
					}

					logger.info("sql=" + sql.toJSONString());

					// amqpSender.send(sql.toJSONString());
					sendSql(sql.toJSONString());
				}
			}
		}
	}

	private void sendSql(String sql) {
		try {
			HttpPost request = new HttpPost(environment.getProperty("data.receiver.url"));

			sql = URLEncoder.encode(sql, "UTF-8");

			StringBody sqlBody = new StringBody(sql, ContentType.create("text/plain", "UTF-8"));

			HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("sql", sqlBody).build();
			request.setEntity(httpEntity);

			HttpClient client = httpConnectionManager.getConnection();
			HttpResponse httpResponse = client.execute(request);

			String r = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
			logger.info("r=" + r);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected JSONObject printDeleteFields(String table, List<Column> columns) {
		StringBuilder builder = new StringBuilder("delete from " + table);

		List<String> params = new ArrayList<>();

		String where = "";
		for (Column column : columns) {
			if (column.getIsKey()) {
				if (StringUtils.isBlank(where)) {
					where += " where " + column.getName() + "=?";
				} else {
					where += " and " + column.getName() + "=?";
				}

				params.add(column.getValue());
			}
		}

		builder.append(where);

		JSONObject sql = new JSONObject();
		sql.put("sql", builder.toString());
		sql.put("params", params);

		return sql;
	}

	protected JSONObject printUpdateFields(String table, List<Column> columns) {
		StringBuilder builder = new StringBuilder("update " + table + " set ");

		List<String> params = new ArrayList<>();
		for (int i = 0; i < columns.size() - 1; i++) {
			Column column = columns.get(i);

			if (column.getIsKey()) {
				continue;
			}

			if (isEmptyInt(column)) {
				continue;
			}

			builder.append(column.getName() + "=?,");
			params.add(column.getValue());
		}

		Column lastCol = columns.get(columns.size() - 1);

		if (!lastCol.getIsKey()) {
			builder.append(lastCol.getName() + "=?");
			params.add(lastCol.getValue());
		}

		String where = "";
		for (Column column : columns) {
			if (column.getIsKey()) {
				if (StringUtils.isBlank(where)) {
					where += " where " + column.getName() + "=?";
				} else {
					where += " and " + column.getName() + "=?";
				}

				params.add(column.getValue());
			}
		}

		builder.append(where);

		JSONObject sql = new JSONObject();
		sql.put("sql", builder.toString());
		sql.put("params", params);

		return sql;
	}

	private boolean isEmptyInt(Column column) {
		logger.info("column datatype=" + column.getMysqlType());

		if (("tinyint".equalsIgnoreCase(column.getMysqlType()) || "int".equalsIgnoreCase(column.getMysqlType()) || "bigint".equalsIgnoreCase(column.getMysqlType()))
		                && "".equals(column.getValue())) {
			return true;
		}
		return false;
	}

	protected JSONObject printInsertFields(String table, List<Column> columns) {
		List<String> params = new ArrayList<>();

		StringBuilder builder = new StringBuilder("insert into " + table + "(");
		for (int i = 0; i < columns.size() - 1; i++) {
			Column column = columns.get(i);

			if (isEmptyInt(column)) {
				continue;
			}

			builder.append(column.getName() + ",");

			params.add(column.getValue());
		}

		Column lastCol = columns.get(columns.size() - 1);

		builder.append(lastCol.getName());

		params.add(lastCol.getValue());

		builder.append(") values (");

		for (int i = 0; i < columns.size() - 1; i++) {
			if (isEmptyInt(columns.get(i))) {
				continue;
			}
			builder.append("?,");
		}
		builder.append("?)");

		JSONObject sql = new JSONObject();
		sql.put("sql", builder.toString());
		sql.put("params", params);
		sql.put("table", table);

		return sql;
	}

	protected void printColumn(List<Column> columns) {
		for (Column column : columns) {
			StringBuilder builder = new StringBuilder();
			builder.append(column.getName() + " : " + column.getValue());
			builder.append("    type=" + column.getMysqlType());
			if (column.getUpdated()) {
				builder.append("    update=" + column.getUpdated());
			}
			builder.append(SEP);
			logger.debug(builder.toString());
		}
	}

	public void setConnector(CanalConnector connector) {
		this.connector = connector;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
