package com.task_manager.manager.service.impl;

import com.task_manager.manager.entity.Authority;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.repository.AuthorityRepository;
import com.task_manager.manager.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    @Transactional(readOnly = true)
    public Authority findAuthorityByRole(String role) throws ExpectedException {
        Authority authority = authorityRepository.findByRole(role);
        if(authority==null){
            throw  new ExpectedException("Role doesn't exist");
        }
        return authority;
    }
}
