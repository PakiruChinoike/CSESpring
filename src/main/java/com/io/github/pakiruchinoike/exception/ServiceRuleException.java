package com.io.github.pakiruchinoike.exception;

public class ServiceRuleException extends RuntimeException{
    
    public ServiceRuleException() {
        super("{object.not-found}");
    }

}
