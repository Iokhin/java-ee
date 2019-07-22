package ru.iokhin.tm.boot.util;

import org.jetbrains.annotations.NotNull;

public final class StringValidator {
    public static void validate(@NotNull final String... strings) {
        for (String string : strings) {
            if (string.trim().isEmpty())
                throw new IllegalArgumentException("INVALID PARAMETER");
        }
    }
}