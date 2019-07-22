package ru.iokhin.tm.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.model.entity.Role;
import ru.iokhin.tm.model.entity.User;
import ru.iokhin.tm.repository.UserRepository;

import java.util.*;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        @Nullable final User user = userRepository.findUserByLogin(login).orElse(null);
        if (user == null) throw new UsernameNotFoundException(login);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPasswordHash(), grantedAuthorities);

//        ALTERNATIVE WAY
//        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
//        builder =  org.springframework.security.core.userdetails.User.withUsername(login);
//        builder.password(user.getPasswordHash());
//        final Set<Role> roleSet = user.getRoles();
//        final List<String> roles = new ArrayList<>();
//        for (Role role : roleSet) {
//            roles.add(role.getName());
//        }
//        builder.roles(roles.toArray(new String[]{}));
//        return builder.build();
    }
}
