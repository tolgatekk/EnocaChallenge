package org.tolga.exception;

import lombok.Getter;

@Getter
public class CartServiceException extends RuntimeException{
    private final ErrorType errorType;

    public CartServiceException(ErrorType errorType) {

        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public CartServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
