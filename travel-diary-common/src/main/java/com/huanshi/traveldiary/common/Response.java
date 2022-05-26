package com.huanshi.traveldiary.common;

import org.jetbrains.annotations.NotNull;

public record Response(int status, String message, Object data) {
    @NotNull
    public static Response success(@NotNull String message) {
        return new Response(200, message, null);
    }

    @NotNull
    public static Response success(@NotNull String message, @NotNull Object data) {
        return new Response(200, message, data);
    }

    @NotNull
    public static Response error(@NotNull String message) {
        return new Response(500, message, null);
    }
}