package org.ehap.exceptions;

import java.io.IOException;

public class IncorrectRequestMethodException extends RuntimeException {

    public IncorrectRequestMethodException(){
        super("Use the correct method!");
    }

    public IncorrectRequestMethodException(String message) {
        super(message);
    }
}
