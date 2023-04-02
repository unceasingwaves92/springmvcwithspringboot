package com.springmvcwithspringboot.exceptionhandling;

public class ResourceFoundException extends Exception {

    private static final long serialVersionUID = -9079454849611061074L;

    public ResourceFoundException() {
        super();
    }

    public ResourceFoundException(final String message) {
        super(message);
    }
}

