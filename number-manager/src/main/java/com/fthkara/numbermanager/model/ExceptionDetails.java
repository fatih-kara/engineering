package com.fthkara.numbermanager.model;

import java.util.Date;

public class ExceptionDetails {

    private String message;
    private int errorCode;
    private String details;
    private Date timestamp;

    public ExceptionDetails(String message, int errorCode, String details, Date timestamp) {
        super();
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
