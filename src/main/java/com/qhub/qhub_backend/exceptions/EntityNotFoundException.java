package com.qhub.qhub_backend.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String entityName) {
        super (entityName + " not found");
    }
}
