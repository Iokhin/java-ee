package ru.iokhin.tm.model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractEntity {

    @NotNull
    protected String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

}
