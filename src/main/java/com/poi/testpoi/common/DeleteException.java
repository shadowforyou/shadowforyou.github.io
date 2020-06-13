package com.poi.testpoi.common;

public class DeleteException extends RuntimeException{

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	
	
	/**
	 * 删除成功
	 * @param data
	 * @param message
	 */
	public DeleteException(String message) {
		this.message = message;
	}
	
	/**
	 * 删除失败
	 */
	public DeleteException() {
	}
	
}
