package com.qhub.qhub_backend.exceptions;

public class QuoteNotFoundException extends EntityNotFoundException{

    public QuoteNotFoundException() {
        super ("QUOTE");
    }
}
