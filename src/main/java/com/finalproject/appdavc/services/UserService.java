package com.finalproject.appdavc.services;

import com.finalproject.appdavc.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> list();

    void add(UserDTO user);

    UserDTO get(Long id);

    void update(UserDTO updatedUser);

    void delete(Long id);

    UserDTO validateUser(String emailAddress, String password);

}
