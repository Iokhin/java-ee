package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Task extends BaseEntity {

    @NotNull
    private String projectId;

}
