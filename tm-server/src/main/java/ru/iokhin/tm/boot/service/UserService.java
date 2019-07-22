package ru.iokhin.tm.boot.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.boot.api.service.IUserService;
import ru.iokhin.tm.boot.enumerated.RoleEnum;
import ru.iokhin.tm.boot.exception.AuthException;
import ru.iokhin.tm.boot.model.dto.UserDTO;
import ru.iokhin.tm.boot.model.entity.Role;
import ru.iokhin.tm.boot.model.entity.User;
import ru.iokhin.tm.boot.repository.RoleRepository;
import ru.iokhin.tm.boot.repository.UserRepository;
import ru.iokhin.tm.boot.util.MD5Util;
import ru.iokhin.tm.boot.util.StringValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(UserService.NAME)
public class UserService implements IUserService {

    @NotNull
    public static final String NAME = "userService";

    @NotNull
    @Autowired
    @Qualifier("userRepo")
    private UserRepository userRepository;

    @NotNull
    @Autowired
    private RoleRepository roleRepository;

    @NotNull
    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserDTO findByLogin(@NotNull String login) {
        final User user = userRepository.findUserByLogin(login).orElse(null);
        if (user == null) return null;
        return user.getUserDTO();
    }

    @Override
    public void persist(@NotNull UserDTO userDTO) {
        @NotNull final User user = getUserFromDTO(userDTO);
        if (user == null) return;
        userRepository.save(user);
    }

    @Override
    public void merge(@NotNull UserDTO userDTO) {
        @NotNull final User user = getUserFromDTO(userDTO);
        if (user == null) return;
        userRepository.save(user);
    }

    @Override
    public void removeById(@NotNull String id) {
        userRepository.delete(id);
    }

    @Override
    public UserDTO findOne(@NotNull String id) {
        @Nullable final User user = userRepository.getOne(id);
        if (user == null) return null;
        return user.getUserDTO();
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(User::getUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO authUser(@NotNull String login, @NotNull String password) throws AuthException {
        StringValidator.validate(login, password);
        UserDTO user = findByLogin(login);
        if (user == null) throw new AuthException("WRONG LOGIN");
        if (!user.getPassword().equals(MD5Util.passwordToHash(password))) throw new AuthException("WRONG PASSWORD");
        return user;
    }

    private User getUserFromDTO(@NotNull final UserDTO userDTO) {
        @NotNull final User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(encoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        if (userDTO.getRole().equals(RoleEnum.ADMIN)) {
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            return user;
        }
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleEnum.USER.toString())
                .orElse(new Role(RoleEnum.USER.toString())))));
        return user;
    }

}
