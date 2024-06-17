package com.geektrust.backend.Exception;

public class NoSuchProgCommandException extends RuntimeException{
    public NoSuchProgCommandException()
    {
     super();
    }
    public NoSuchProgCommandException(String msg)
    {
     super(msg);
    }
}

