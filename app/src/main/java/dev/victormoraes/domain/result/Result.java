package dev.victormoraes.domain.result;

public class Result<T> {
    private final boolean success;
    private T result;

    private String errorMessage;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public Result(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return !success;
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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
