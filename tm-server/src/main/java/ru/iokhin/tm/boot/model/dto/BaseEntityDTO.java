package ru.iokhin.tm.boot.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.boot.enumerated.Status;
import ru.iokhin.tm.boot.model.entity.AbstractEntity;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BaseEntityDTO extends AbstractEntity {

    @NotNull
    protected String userId = "";

    @Nullable
    protected String name;

    @Nullable
    protected String description;

    @Nullable
    protected Status status = Status.PLANNING;

    @Nullable
    protected Date dateStart;

    @Nullable
    protected Date dateEnd;

}
