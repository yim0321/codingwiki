package com.aso.codingwiki.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String msg){
        super(msg);
    }
    public CommentNotFoundException(Exception ex){
        super(ex);
    }
}
