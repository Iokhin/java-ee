package ru.iokhin.tm.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.AbstractEntity;
import ru.iokhin.tm.model.User;

import java.util.Collection;

public interface IRepository<E extends AbstractEntity> {

    E persist(@NotNull E entity);

    E merge(@NotNull E entity);

    E remove(@NotNull E entity);

    E findOne(@NotNull String id);

    Collection<E> findAll();

}
