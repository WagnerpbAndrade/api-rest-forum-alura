package br.com.wagnerandrade.alura.forum.infra.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
