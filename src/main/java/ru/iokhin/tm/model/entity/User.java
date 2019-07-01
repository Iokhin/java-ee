package ru.iokhin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.model.dto.UserDTO;
import ru.iokhin.tm.util.MD5Util;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User extends AbstractEntity implements Serializable {

    @Nullable
    @Column(unique = true)
    private String login;

    @Nullable
    private String email;

    @Nullable
    private String passwordHash;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Role role;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    public User(@Nullable String login, @Nullable String password, @Nullable Role role) {
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
        this.role = role;
    }

    public User(@NotNull String id, @Nullable String login, @Nullable String password, @Nullable Role role) {
        this.id = id;
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
        this.role = role;
    }

    public UserDTO getUserDTO() {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setLogin(login);
        userDTO.setEmail(email);
        userDTO.setPasswordHash(passwordHash);
        userDTO.setRole(role);
        return userDTO;
    }

}
