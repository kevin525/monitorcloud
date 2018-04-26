package com.apk.model;


public class ReturnData {

	private int return_code; //0失败 1成功
	private String return_message;
	private Object return_data;
	
	public ReturnData() {
		super();
	}
	
	public ReturnData(int return_code, String return_message,
			Object return_data) {
		super();
		this.return_code = return_code;
		this.return_message = return_message;
		this.return_data = return_data;
	}
	public ReturnData(int return_code, String return_message
			) {
		super();
		this.return_code = return_code;
		this.return_message = return_message;
	}
	public int getReturn_code() {
		return return_code;
	}
	public void setReturn_code(int return_code) {
		this.return_code = return_code;
	}
	public String getReturn_message() {
		return return_message;
	}
	public void setReturn_message(String return_message) {
		this.return_message = return_message;
	}
	public Object getReturn_data() {
		return return_data;
	}
	public void setReturn_data(Object return_data) {
		this.return_data = return_data;
	}
	
	
}
