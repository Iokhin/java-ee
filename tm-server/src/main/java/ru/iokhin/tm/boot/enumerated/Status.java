package ru.iokhin.tm.boot.enumerated;

import org.jetbrains.annotations.NotNull;

public enum Status {
    PLANNING("PLANNING"),
    PROCESSING("PROCESSING"),
    READY("READY");

    @NotNull
    private final String name;

    Status(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Status getStatusByName(@NotNull final String name) {
        for (Status status : values()) {
            if (status.getName().equalsIgnoreCase(name))
                return status;
        }
        return null;
    }
}
