package com.springmvcwithspringboot.exception;

public class ApplicationNotFoundException extends Exception{

    // default constructor
    public ApplicationNotFoundException(){
        super();
    }

    // parameterized constructor
    public ApplicationNotFoundException(String msg){
        super(msg);
    }

    public ApplicationNotFoundException(Throwable t, String msg){
        super(msg, t);
    }

    public ApplicationNotFoundException(Throwable cause){
        super(cause);
    }

    public ApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
