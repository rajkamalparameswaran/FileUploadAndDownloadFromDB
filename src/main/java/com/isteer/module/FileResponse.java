package com.isteer.module;

import java.util.List;

public class FileResponse {
	
	private int statusCode;
	private String msg;
	private List<String> downloadUrl;
	public FileResponse(int statusCode, String msg, List<String> downloadUrl) {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
		this.downloadUrl = downloadUrl;
	}
	public FileResponse() {
		super();
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<String> getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(List<String> downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

}
