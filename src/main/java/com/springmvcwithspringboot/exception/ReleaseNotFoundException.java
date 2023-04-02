package com.springmvcwithspringboot.exception;

public class ReleaseNotFoundException extends Exception{

    // default constructor
    public ReleaseNotFoundException(){
        super();
    }

    // parameterized constructor
    public ReleaseNotFoundException(String msg){
        super(msg);
    }

    public ReleaseNotFoundException(Throwable t, String msg){
        super(msg, t);
    }

    public ReleaseNotFoundException(Throwable cause){
        super(cause);
    }

    public ReleaseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
