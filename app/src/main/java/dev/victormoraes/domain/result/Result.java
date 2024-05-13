package dev.victormoraes.domain.result;

public class Result<T> {
    private final boolean success;
    private T result;
    private Exception errorType;

    private String errorMessage;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public Result(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
    }

    public Result(Exception errorType) {
        this.success = false;
        this.errorType = errorType;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Exception getThrownException() {
        return errorType;
    }

    public void setThrownException(Exception thrownException) {
        this.errorType = thrownException;
    }
}
