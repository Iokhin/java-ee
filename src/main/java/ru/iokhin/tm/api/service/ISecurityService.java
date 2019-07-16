package ru.iokhin.tm.api.service;

import ru.iokhin.tm.model.dto.UserDTO;

public interface ISecurityService {

    String findLoggedInUsername();

    boolean isAuth();

    UserDTO getLoggedUser();

    void autoLogin(String username, String password);

}
