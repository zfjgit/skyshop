package com.sitv.skyshop.massagechair.handler;

public abstract class ServiceConfirmHandler {

	private ServiceConfirmHandler handler;

	public abstract void handle();

	public void setHandler(ServiceConfirmHandler handler) {
		this.handler = handler;
	}

	public ServiceConfirmHandler getHandler() {
		return handler;
	}
}
