package com.task_manager.manager.validator;

import com.task_manager.manager.entity.User;

import javax.xml.bind.ValidationException;

public class UserValidator {
    private static final String EMAIL_REGEXP = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";

    public static void validateUser(User user) throws ValidationException {
        if (user == null || user.getName() == null || user.getSurname() == null || user.getEmail() == null ||
                !user.getEmail().matches(EMAIL_REGEXP) || user.getPassword() == null) {
            throw new ValidationException("Invalid User");
        }
    }
}
