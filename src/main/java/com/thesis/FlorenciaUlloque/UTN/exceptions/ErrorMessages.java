package com.thesis.FlorenciaUlloque.UTN.exceptions;

public enum ErrorMessages {
    NO_RECORD_FOUND("La petición solicitada no se encuentra"),
    CLIENT_RECORD_INEXIST("Error!, El usuario o contraseña ingresados no pertenecen a ningun usuario registrado"),
    RECORD_ALREADY_EXIST("Error! El dato a guardar ya existe");

    private String errorMessage;


    ErrorMessages(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
