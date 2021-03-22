package com.aso.codingwiki.exception;

public class ImageDeletionFailed extends RuntimeException{
    public ImageDeletionFailed(String msg){
        super(msg);
    }
    public ImageDeletionFailed(Exception ex){
        super(ex);
    }
}
