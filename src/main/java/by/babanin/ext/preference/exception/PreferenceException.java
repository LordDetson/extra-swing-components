package by.babanin.ext.preference.exception;

public class PreferenceException extends RuntimeException {

    public PreferenceException(String message) {
        super(message);
    }

    public PreferenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PreferenceException(Throwable cause) {
        super(cause);
    }

    public PreferenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
