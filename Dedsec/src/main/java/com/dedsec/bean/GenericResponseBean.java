package com.dedsec.bean;

import java.util.ArrayList;

public class GenericResponseBean {
	String errorCode;
	String errorMessage;
	Object sharedData;
	
	

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getSharedData() {
		return sharedData;
	}

	public void setSharedData(Object sharedData) {
		this.sharedData = sharedData;
	}

}
