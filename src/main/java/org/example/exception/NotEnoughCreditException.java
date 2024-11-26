package org.example.exception;

public class NotEnoughCreditException extends RuntimeException{
    public NotEnoughCreditException(){
        super("Not enough credit! ");
    }
}
