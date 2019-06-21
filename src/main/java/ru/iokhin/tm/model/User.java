package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.util.MD5Util;

@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {

    @Nullable
    private String login;

    @Nullable
    private String passwordHash;

    @Nullable
    private Role role;

    public User(@Nullable String login, @Nullable String password, @Nullable Role role) {
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
        this.role = role;
    }
}
