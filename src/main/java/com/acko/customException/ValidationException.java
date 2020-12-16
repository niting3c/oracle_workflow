package com.acko.customException;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ValidationException extends Exception {
    private String message;

    public ValidationException(String message) {
        this.message = message;
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
