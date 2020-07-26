package com.stacksimplify.restservices.exceptions;

import java.util.Date;

public class CustomerErrorDetails {
private Date timeStamp;
private String message;
private String errordetails;
public CustomerErrorDetails(Date timeStamp, String message, String errordetails) {
	super();
	this.timeStamp = timeStamp;
	this.message = message;
	this.errordetails = errordetails;
}
public Date getTimeStamp() {
	return timeStamp;
}
public String getMessage() {
	return message;
}
public String getErrordetails() {
	return errordetails;
}

}
