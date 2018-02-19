package com.task_manager.manager.repository;

import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.enums.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findUserByNameAndSurnameAndAuthority_RoleAndIsActivatedIsTrue(String name, String surname, String role );


}
