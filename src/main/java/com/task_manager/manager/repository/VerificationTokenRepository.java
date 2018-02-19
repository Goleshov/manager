package com.task_manager.manager.repository;

import com.task_manager.manager.entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    VerificationToken findByToken(String Token);
}
