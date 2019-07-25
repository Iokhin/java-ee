package ru.iokhin.tm.boot.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.iokhin.tm.boot.enumerated.RoleEnum;
import ru.iokhin.tm.boot.model.dto.UserDTO;
import ru.iokhin.tm.boot.util.MD5Util;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
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
    private RoleEnum role;

    @ManyToMany
    private Set<Role> roles;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    public User(@Nullable String login, @Nullable String password, @Nullable RoleEnum role) {
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
        this.role = role;
    }

    public User(@NotNull String id, @Nullable String login, @Nullable String password, @Nullable RoleEnum role) {
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
        userDTO.setPassword(passwordHash);
        userDTO.setRole(role);
        return userDTO;
    }

}
