package com.Blog.Blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ResourceNotFound extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private int fieldValue;

    public ResourceNotFound(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s not found %s:%s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
