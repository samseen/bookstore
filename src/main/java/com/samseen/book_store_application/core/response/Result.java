package com.samseen.book_store_application.core.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Result<T> {
    private boolean status;
    private String message;
    private T data;

    public static <T> Result<T> response(boolean status, String message, T data) {
        return new Result<>(status, message, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return response(true, message, data);
    }

    public static <T> Result<T> success(T data) {
        return success("Success", data);
    }

    public static <T> Result<T> failure(String message, T data) {
        return response(false, message, data);
    }

    public static <T> Result<T> failure(T data) {
        return failure("Failed", data);
    }
}
