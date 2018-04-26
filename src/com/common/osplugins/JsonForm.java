package com.common.osplugins;

import java.io.Serializable;

/**
 * @author 作者 李涛
 * @version v1 创建时间：2014年11月5日下午2:51:08 类说明：
 */
public class JsonForm implements Serializable {
	private static final long serialVersionUID = -6391551397350201948L;
	private Boolean success;
	private Object data;
	private String error;
	private String msg;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static JsonForm warpJsonForm(Boolean success) {
		JsonForm jsonForm = new JsonForm();
		jsonForm.setSuccess(success);
		return jsonForm;
	}

	public static JsonForm warpJsonForm(String error) {
		JsonForm jsonForm = new JsonForm();
		jsonForm.setSuccess(Boolean.valueOf(false));
		jsonForm.setError(error);
		return jsonForm;
	}

	public static JsonForm warpJsonForm(Boolean success, Object data) {
		JsonForm jsonForm = new JsonForm();
		jsonForm.setSuccess(success);
		jsonForm.setData(data);
		return jsonForm;
	}

	public static JsonForm warpJsonForm(Boolean success, Object data, String msg) {
		JsonForm jsonForm = new JsonForm();
		jsonForm.setData(data);
		jsonForm.setMsg(msg);
		jsonForm.setSuccess(success);
		return jsonForm;
	}

}
