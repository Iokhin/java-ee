package ru.iokhin.tm.boot.util;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.model.dto.BaseEntityDTO;

public class DataValidator {

    public static void validate(@NotNull final BaseEntityDTO baseEntityDTO) {
        if (baseEntityDTO.getId() == null || baseEntityDTO.getName() == null || baseEntityDTO.getUserId() == null)
            throw new IllegalArgumentException("INVALID DATA");
        StringValidator.validate(baseEntityDTO.getName());
    }

}
