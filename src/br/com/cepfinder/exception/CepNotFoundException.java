package br.com.cepfinder.exception;

public class CepNotFoundException extends RuntimeException{
    public CepNotFoundException(String message){
        super(message);
    }
}
