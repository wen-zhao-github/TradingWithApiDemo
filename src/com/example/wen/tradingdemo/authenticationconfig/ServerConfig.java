package com.example.wen.tradingdemo.authenticationconfig;

public class ServerConfig {
	private String serverUrl;

	public ServerConfig(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
}
