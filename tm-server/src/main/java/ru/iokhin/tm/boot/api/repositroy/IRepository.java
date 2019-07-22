package ru.iokhin.tm.boot.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.model.entity.AbstractEntity;

import java.util.List;

public interface IRepository<E extends AbstractEntity> {

    void persist(@NotNull E entity);

    void merge(@NotNull E entity);

    void removeById(@NotNull String id);

    E findOne(@NotNull String id);

    List<E> findAll();

}
