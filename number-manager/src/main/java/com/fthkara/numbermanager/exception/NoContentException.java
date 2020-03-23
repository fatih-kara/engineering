package com.fthkara.numbermanager.exception;

import org.springframework.http.HttpStatus;

public class NoContentException extends ApiException {

    public NoContentException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NO_CONTENT;
    }
}
