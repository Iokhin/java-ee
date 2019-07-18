package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.Status;

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
