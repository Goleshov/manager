package com.task_manager.manager.service.impl;

import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.VerificationToken;
import com.task_manager.manager.repository.VerificationTokenRepository;
import com.task_manager.manager.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    VerificationTokenRepository tokenRepository;

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        tokenRepository.save(verificationToken);
    }

    @Override
    @Transactional(readOnly = true)
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
