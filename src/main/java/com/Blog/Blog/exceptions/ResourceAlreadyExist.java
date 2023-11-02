package com.Blog.Blog.exceptions;

public class ResourceAlreadyExist extends RuntimeException{
    private String fieldName;

    public ResourceAlreadyExist(String fieldName) {
        super(String.format("%s already exists",fieldName));
        this.fieldName = fieldName;
    }
}
