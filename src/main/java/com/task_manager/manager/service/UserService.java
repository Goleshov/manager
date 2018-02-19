package com.task_manager.manager.service;


import com.task_manager.manager.entity.DTO.UserDTO;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.entity.User;

import javax.xml.bind.ValidationException;

public interface UserService {
    void createUser(User user, String role) throws ExpectedException, ValidationException;
    User findUserByNameAndSurname(String name, String surname);
    User findUserById(Long id);
    void activateUser(User user);
    UserDTO findUserByEmail(String email);

}
