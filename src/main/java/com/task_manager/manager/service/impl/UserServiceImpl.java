package com.task_manager.manager.service.impl;

import com.task_manager.manager.entity.Authority;
import com.task_manager.manager.entity.DTO.UserDTO;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.enums.Role;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.repository.AuthorityRepository;
import com.task_manager.manager.repository.UserRepository;
import com.task_manager.manager.service.UserService;
import com.task_manager.manager.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserByEmail(String email) {

        return UserDTO.convertToUserDTO(userRepository.findByEmail(email));
    }

    @Override
    public void createUser(User user, String role) throws ExpectedException, ValidationException {
        UserValidator.validateUser(user);
        if (userRepository.findByEmail(user.getEmail()) == null) {
            Authority authority = authorityRepository.findByRole(role);
            user.setAuthority(authority);
            user.setIsActivated(false);
            userRepository.save(user);
        } else {
            throw new ExpectedException("User with this email already exists");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByNameAndSurname(String name, String surname) {
        return userRepository.findUserByNameAndSurnameAndAuthority_RoleAndIsActivatedIsTrue(name, surname, Role.ROLE_USER.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void activateUser(User user) {
        user.setIsActivated(true);
    }
}
