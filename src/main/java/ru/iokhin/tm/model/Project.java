package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.Setter;

public class Project extends BaseEntity {
    @Override
    public String toString() {
        return getId();
    }
}
