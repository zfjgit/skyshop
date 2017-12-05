/*
 * com.xilincy.xilincyapp.utils
 * copyright (c) xilincy
 * Zhou - 2011-3-24
 */
package com.sitv.skyshop.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类
 *
 * @author zfj
 */
public class Utils implements Serializable {
	private static final long serialVersionUID = 7291033981547467488L;

	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	private static final byte[] IV_KEY = { 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8 };
	private static final String ENCRYPT_PWD = "qwertyuiqwertyui";

	/**
	 * 判断是否为空或null
	 *
	 * @param object
	 *            String, Collection, Map, Array
	 * @return true:为空或null
	 */
	public static boolean isNull(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			return "".equals(object) || "null".equals(object);
		} else if (object instanceof Collection) {
			return ((Collection<?>) object).isEmpty();
		} else if (object instanceof Map<?, ?>) {
			return ((Map<?, ?>) object).isEmpty();
		} else if (object.getClass().isArray()) {
			return Array.getLength(object) == 0;
		} else if (object instanceof JSONObject) {
			return ((JSONObject) object).length() == 0;
		} else if (object instanceof JSONArray) {
			return ((JSONArray) object).length() == 0;
		}
		return false;
	}

	public static Map<String, String> parseXml(String str) {
		Map<String, String> map = new HashMap<>();

		try {
			Document document = DocumentHelper.parseText(str);

			Element root = document.getRootElement();

			List<?> elementList = root.elements();

			for (Object o : elementList) {
				Element e = (Element) o;
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return map;
	}

	public static Calendar getMaxDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 9999);
		c.set(Calendar.MONTH, Calendar.DECEMBER);
		c.set(Calendar.DAY_OF_MONTH, 31);
		return c;
	}

	public static Calendar getMinDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1970);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c;
	}

	public static Calendar getNextDayStartTime() {
		Calendar startTime = Calendar.getInstance();
		startTime.set(Calendar.DATE, startTime.get(Calendar.DATE) + 1);
		startTime.set(Calendar.HOUR_OF_DAY, 0);
		startTime.set(Calendar.MINUTE, 0);
		startTime.set(Calendar.SECOND, 0);
		return startTime;
	}

	/**
	 * 默认格式：'yyyy-MM-dd HH:mm:ss' 如果参数为空则格式化当前日期
	 *
	 * @param c
	 *            Calendar
	 * @param pattern
	 *            格式
	 * @return string
	 * @author Zhou 2010-4-27
	 */
	public static String time2String(Calendar c, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATETIME_FORMAT);

		if (!Utils.isNull(pattern)) {
			formatter.applyPattern(pattern);
		}
		if (c == null) {
			return formatter.format(Calendar.getInstance().getTime());
		}
		return formatter.format(c.getTime());
	}

	/**
	 * 默认格式：'yyyy-MM-dd HH:mm:ss' 如果参数为空则格式化当前日期
	 *
	 * @param time
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static Calendar toCalendar(String time, String pattern) {
		if (isNull(time)) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATETIME_FORMAT);

		if (!isNull(pattern)) {
			formatter.applyPattern(pattern);
		}
		try {
			Date d = formatter.parse(time);
			Calendar c = (Calendar) Calendar.getInstance().clone();
			c.setTime(d);
			return c;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据上传的文件名生成服务器保存的文件名
	 *
	 * @param fileName
	 *            上传文件名称
	 * @return 保存到服务器的文件名
	 * @author Zhou
	 */
	public static String getDestFileName(String dir, String fileName) {
		// 检查文件是否重名，重名则在文件名中间插入毫秒数避免重名
		File[] files = new File(dir).listFiles();
		if (null != files) {
			for (File f : files) {
				if (f.getName().equals(fileName)) {
					int i = fileName.lastIndexOf(".");
					fileName = (fileName.substring(0, i) + "_" + System.currentTimeMillis() + fileName.substring(i));
				}
			}
		}
		return fileName;
	}

	/**
	 * 文件上传
	 *
	 * @param src
	 *            客户端提交的文件对象
	 * @param dst
	 *            服务器另存文件对象
	 * @return
	 * @throws Exception
	 * @author Zhou
	 */
	public static boolean copy(File src, File dst) {
		Utils.log.debug("正在上传文件...");
		int BUFFER_SIZE = 20 * 1024;
		boolean result = false;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			result = true;
			Utils.log.debug("上传完毕！");
		} catch (Exception e) {
			Utils.log.error("文件上传错误", e);
			result = false;
			// throw new Exception("文件上传错误：" + e.getMessage());
		} finally {
			try {
				if (null != in) {
					in.close();
					in = null;
				}
				if (null != out) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				Utils.log.error("文件上传错误", e);
				result = false;
				// throw new Exception("文件上传错误：" + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 加密 AES/CBC/PKCS5Padding
	 *
	 * @param pwd
	 * @return
	 */
	public static String encrypt(String pwd, String... encryptPwd) {
		try {
			if (isNull(encryptPwd) || isNull(encryptPwd[0])) {
				encryptPwd = new String[] { ENCRYPT_PWD };
			}
			SecretKeySpec key = new SecretKeySpec(encryptPwd[0].getBytes("UTF-8"), "AES");

			// 获得一个私鈅加密类Cipher，CBC是加密方式，PKCS5Padding是填充方法
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV_KEY));// 使用私鈅加密

			byte[] cipherText = cipher.doFinal(pwd.getBytes("UTF-8"));
			return parseByte2HexStr(cipherText);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密
	 *
	 * @param pwd
	 * @param decryptPwd
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String pwd, String... decryptPwd) {
		try {
			if (isNull(decryptPwd) || isNull(decryptPwd[0])) {
				decryptPwd = new String[] { ENCRYPT_PWD };
			}
			SecretKeySpec key = new SecretKeySpec(decryptPwd[0].getBytes("UTF-8"), "AES");

			// 获得一个私鈅加密类Cipher，CBC是加密方式，PKCS5Padding是填充方法
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV_KEY));// 使用私钥解密

			byte[] newPlainText = cipher.doFinal(Utils.parseHexStr2Byte(pwd));

			return new String(newPlainText, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toUnicode(String str) {
		if (str == null) {
			return null;
		}

		StringBuilder result = new StringBuilder();
		for (Character ch : str.toCharArray()) {
			if (ch < '\020') {
				result.append("\\u000" + Integer.toHexString(ch));
			}
			if (ch < '?') {
				result.append("\\u00" + Integer.toHexString(ch));
			}
			if (ch < '?') {
				result.append("\\u0" + Integer.toHexString(ch));
			}
			result.append("\\u" + Integer.toHexString(ch));
		}
		return result.toString();
	}

	/**
	 * @param str
	 * @param method
	 *            MD5/SHA-1/SHA-256
	 * @return hex string
	 */
	public static String digest(String str, String method) {
		try {
			MessageDigest md = MessageDigest.getInstance(method);
			byte[] digestBytes = md.digest(str.getBytes());

			return parseByte2HexStr(digestBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String UUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public static String request(String host, String action, String params, String method) {
		BufferedReader reader = null;
		try {
			log.debug("调用接口：" + host + action);
			// log.debug("参数：" + params);

			trustAllHosts();

			HttpURLConnection connection = (HttpURLConnection) new URL(host + action).openConnection();
			if (host.startsWith("https")) {
				((HttpsURLConnection) connection).setHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String paramString, SSLSession paramSSLSession) {
						return true;
					}
				});
			}
			connection.setReadTimeout(0);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.getOutputStream().write(params.toString().getBytes("UTF-8"));
			connection.getOutputStream().flush();
			connection.getOutputStream().close();

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuilder response = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			// log.debug("返回：" + response.toString());

			return response.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) throws java.security.cert.CertificateException {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) throws java.security.cert.CertificateException {
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
