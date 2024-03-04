package org.tolga.exception;

import lombok.Getter;

@Getter
public class OrderServiceException extends RuntimeException{
    private final ErrorType errorType;

    public OrderServiceException(ErrorType errorType) {

        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public OrderServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
