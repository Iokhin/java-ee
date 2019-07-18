package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity {

    @NotNull
    protected String id = UUID.randomUUID().toString();

}
