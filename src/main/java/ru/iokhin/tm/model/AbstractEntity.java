package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
public abstract class AbstractEntity {

    @NotNull
    private final String id = UUID.randomUUID().toString();

}
