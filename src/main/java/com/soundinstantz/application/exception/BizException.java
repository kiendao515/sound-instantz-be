package com.soundinstantz.application.exception;

import java.io.Serial;

public class BizException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public BizException(String message) {
        super(message);
    }

    public BizException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}
