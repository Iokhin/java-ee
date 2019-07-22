package ru.iokhin.tm.boot.api.service;

import ru.iokhin.tm.boot.model.dto.UserDTO;

public interface ISecurityService {

    String findLoggedInUsername();

    boolean isAuth();

    UserDTO getLoggedUser();

    void autoLogin(String username, String password);

}
