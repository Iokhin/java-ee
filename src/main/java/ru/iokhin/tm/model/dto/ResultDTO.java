package ru.iokhin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ResultDTO implements Serializable {

    private boolean success;

    public ResultDTO(boolean success) {
        this.success = success;
    }

}
