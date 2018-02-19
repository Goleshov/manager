package com.task_manager.manager.repository;

import com.task_manager.manager.entity.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByRole(String role);
}
