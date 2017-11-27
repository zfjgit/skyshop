/**
 * 
 */
package com.sitv.skyshop.sync.receiverdata.service;

import org.json.JSONObject;

/**
 * 
 * @author zfj20 @ 2017年9月16日
 */
public interface IUpdateService {

	String OK = "OK";
	String ERROR = "ERROR";
	
	void update(JSONObject sqlObject);
}
