package com.dreawer.goods.config;

public class ProduceResult {

	private boolean status = false;
	private String message = null;

	public ProduceResult() {
	}

	public ProduceResult(boolean status) {
		this.status = status;
	}

	public ProduceResult(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
