package ru.iokhin.tm.boot.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.boot.enumerated.RoleEnum;
import ru.iokhin.tm.boot.model.entity.Role;
import ru.iokhin.tm.boot.model.entity.User;
import ru.iokhin.tm.boot.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPasswordHash());
        @NotNull final RoleEnum userRole = user.getRole();
        builder.roles(userRole.name());
        @NotNull final UserDetails userDetails = builder.build();
        return userDetails;
    }
}
