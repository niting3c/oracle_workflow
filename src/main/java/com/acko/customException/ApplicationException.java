package com.acko.customException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ApplicationException extends Exception {
    private String message;

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public ApplicationException(String message) {
        this.message = message;
    }
}