package com.task_manager.manager.service;

import com.task_manager.manager.entity.Authority;
import com.task_manager.manager.exception.ExpectedException;

public interface AuthorityService {
    Authority findAuthorityByRole(String role)throws ExpectedException;
}
