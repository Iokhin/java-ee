package ru.iokhin.tm.boot.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.boot.enumerated.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity extends AbstractEntity {

    @NotNull
    @ManyToOne
    protected User user;

    @Nullable
    protected String name;

    @Nullable
    protected String description;

    @Nullable
    @Enumerated(EnumType.STRING)
    protected Status status = Status.PLANNING;

    @Nullable
    protected Date dateStart;

    @Nullable
    protected Date dateEnd;

}
