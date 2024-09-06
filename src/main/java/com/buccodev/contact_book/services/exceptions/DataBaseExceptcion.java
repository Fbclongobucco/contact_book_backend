package com.buccodev.contact_book.services.exceptions;

public class DataBaseExceptcion extends RuntimeException{

    private static final long sereialVersionUId = 1L;

    public DataBaseExceptcion(String msg){
        super(msg);
    }

}
