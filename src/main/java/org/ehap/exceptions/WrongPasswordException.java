package org.ehap.exceptions;

public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException() {
        super("Senha incorreta. Tente Novamente");
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
