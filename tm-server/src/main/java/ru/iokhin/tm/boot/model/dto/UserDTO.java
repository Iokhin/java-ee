package ru.iokhin.tm.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.boot.enumerated.RoleEnum;
import ru.iokhin.tm.boot.model.entity.AbstractEntity;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends AbstractEntity {

    @Nullable
    private String login;

    @Nullable
    private String email;

    @Nullable
    private String password;

    @Nullable
    private RoleEnum role;

    public UserDTO(@Nullable String login, @Nullable String password, @Nullable RoleEnum role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserDTO(@NotNull String id, @Nullable String login, @Nullable String password, @Nullable RoleEnum role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

}
