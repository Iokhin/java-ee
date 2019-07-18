package ru.iokhin.tm.model;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@NoArgsConstructor
public class ProjectDTO extends BaseEntityDTO implements Serializable {
    @Override
    public String toString() {
        return getId();
    }

    public ProjectDTO(@NotNull final String userId) {
        this.userId = userId;
        this.name = "NEW PROJECT";
        this.description = "NEW DESCRIPTION";
    }
}
