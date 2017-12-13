package com.sitv.skyshop.massagechair.handler;

public class OpenConfirmHandler extends ServiceConfirmHandler {

	public void handle() {
		if (getHandler() != null) {
			getHandler().handle();
		} else {
			handle();
		}
	}
}
