package com.chatbot.util;

public class StatusUpdateResponse {
	private int id;
	private String result;
	private boolean status;
	public StatusUpdateResponse() {
		super();
	};
	public StatusUpdateResponse(int id, String result, boolean status) {
		super();
		this.id = id;
		this.result = result;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
