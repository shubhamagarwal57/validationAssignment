package com.validation.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyPresent extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceAlreadyPresent(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s is already present  with %s : '%s'. please try with new %s", resourceName, fieldName, fieldValue,fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
