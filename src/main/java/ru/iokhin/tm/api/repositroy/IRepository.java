package ru.iokhin.tm.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.entity.AbstractEntity;

import java.util.List;

public interface IRepository<E extends AbstractEntity> {

    void persist(@NotNull E entity);

    void merge(@NotNull E entity);

    void removeById(@NotNull String id);

    E findOne(@NotNull String id);

    List<E> findAll();

}
