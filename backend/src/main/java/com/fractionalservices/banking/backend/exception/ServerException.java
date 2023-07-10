package com.fractionalservices.banking.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "Bad Request")
public class ServerException extends Exception {

    public ServerException(String message) {
        super(message);
    }

}
