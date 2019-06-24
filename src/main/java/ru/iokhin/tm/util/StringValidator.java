package ru.iokhin.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StringValidator {
    public static void validate(@NotNull final String... strings) {
        for (String string : strings) {
            if (string.trim().isEmpty())
                throw new IllegalArgumentException("INVALID PARAMETER");
        }
    }
}