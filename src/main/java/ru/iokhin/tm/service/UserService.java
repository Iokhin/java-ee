package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.dto.UserDTO;
import ru.iokhin.tm.model.entity.User;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.util.StringValidator;

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
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findOne(@NotNull String id) {
        @Nullable final User user = userRepository.findById(id).orElse(null);
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
        if (!user.getPasswordHash().equals(MD5Util.passwordToHash(password))) throw new AuthException("WRONG PASSWORD");
        return user;
    }

    private User getUserFromDTO(@NotNull final UserDTO userDTO) {
        @NotNull final User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPasswordHash());
        user.setRole(userDTO.getRole());
        return user;
    }

}
