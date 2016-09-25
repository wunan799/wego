/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.utils;

public class WnException extends RuntimeException {
    public static final int ERROR_GENERIC    = 1;
    public static final int ERROR_IO         = 100;
    public static final int ERROR_NOTFOUND   = 101;
    public static final int ERROR_NULLPOINT  = 102;

    private int errorCode = ERROR_GENERIC;

    public WnException(Throwable cause) {
        this(ERROR_GENERIC, null, cause);
    }

    public WnException(String message) {
        this(ERROR_GENERIC, message, null);
    }
    public WnException(int errorCode, String message) {
        this(errorCode, message, null);
    }

    public WnException(int errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public WnException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
