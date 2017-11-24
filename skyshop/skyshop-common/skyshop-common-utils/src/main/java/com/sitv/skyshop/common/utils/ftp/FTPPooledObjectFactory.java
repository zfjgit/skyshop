/**
 * 
 */
package com.sitv.skyshop.common.utils.ftp;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.nio.charset.Charset;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author zfj20 @ 2017年9月28日
 */
public class FTPPooledObjectFactory extends BasePooledObjectFactory<FTPClient> {

	private String host;
	
	private int port;
	
	private String user;
	
	private String pwd;
	
	private String dir;
	
	private String passiveModeConf;
	

	public FTPPooledObjectFactory(String host, int port, String user, String pwd, String dir, String passiveModeConf) {
		this.host = host;
		this.port = port;
		this.pwd = pwd;
		this.user = user;
		this.dir = dir;
		this.passiveModeConf = passiveModeConf;
	}
	
	public FTPClient create() throws Exception {
		FTPClient ftpClient = new FTPClient();

		ftpClient.connect(host, port);
		ftpClient.setControlKeepAliveTimeout(300);// set timeout to 5 minutes
		
		ftpClient.login(user, pwd);
		
		ftpClient.sendCommand("OPTS UTF8", "ON");
		
		ftpClient.setCharset(Charset.forName("UTF-8"));
		
		ftpClient.setControlEncoding("UTF-8");
		
		ftpClient.setAutodetectUTF8(true);
		
		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("", 21));
		ftpClient.setProxy(proxy);
		
		boolean passiveMode = false;
		if (passiveModeConf == null || Boolean.parseBoolean(passiveModeConf) == true) {
			passiveMode = true;
		}
		
		if (passiveMode) {
			ftpClient.enterLocalPassiveMode();
		}
		
		ftpClient.changeWorkingDirectory(dir);
		
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		return ftpClient;
	}

	public PooledObject<FTPClient> wrap(FTPClient obj) {
		return new DefaultPooledObject<FTPClient>(obj);
	}
	
	@Override
	public PooledObject<FTPClient> makeObject() throws Exception {
		return super.makeObject();
	}
	
	@Override
	public boolean validateObject(PooledObject<FTPClient> p) {
		return p != null && p.getObject() != null && p.getObject().isAvailable() && p.getObject().isConnected();
	}
	
	@Override
	public void activateObject(PooledObject<FTPClient> p) throws Exception {
		super.activateObject(p);
	}
	
	@Override
	public void destroyObject(PooledObject<FTPClient> p) throws Exception {
		super.destroyObject(p);
	}
	
	@Override
	public void passivateObject(PooledObject<FTPClient> p) throws Exception {
		super.passivateObject(p);
	}

}
