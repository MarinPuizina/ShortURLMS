package com.marin.urlshortenerms.Exception;

public enum ErrorMessages {

    DATABASE_CONNECTION_ISSUE("There is an issue with database connection.");


    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
