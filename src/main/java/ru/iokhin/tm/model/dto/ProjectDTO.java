package ru.iokhin.tm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
