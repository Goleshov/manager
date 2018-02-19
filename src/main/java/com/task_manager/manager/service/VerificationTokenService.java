package com.task_manager.manager.service;

import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.VerificationToken;
import org.springframework.stereotype.Service;


public interface VerificationTokenService {
     void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);

}
