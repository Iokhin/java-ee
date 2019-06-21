package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.Status;

import java.util.Date;

@Getter
@Setter
public class BaseEntity extends AbstractEntity {

    @NotNull
    private String userId = "";

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Status status;

    @Nullable
    private Date dateStart;

    @Nullable
    private Date dateEnd;

}
