package com.springmvcwithspringboot.exception;

public class TicketNotFoundException extends Exception{

    // default constructor
    public TicketNotFoundException(){
        super();
    }

    // parameterized constructor
    public TicketNotFoundException(String msg){
        super(msg);
    }

    public TicketNotFoundException(Throwable t, String msg){
        super(msg, t);
    }

    public TicketNotFoundException(Throwable cause){
        super(cause);
    }

    public TicketNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
