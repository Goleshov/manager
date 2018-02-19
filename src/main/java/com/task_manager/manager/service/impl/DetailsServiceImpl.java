package com.task_manager.manager.service.impl;

import com.task_manager.manager.entity.Authority;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class DetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails loadedUser;
        try {
            User user = userRepository.findByEmail(username);
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(user.getAuthority());
            loadedUser = new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), user.getIsActivated(),
                    true, true, true, authorities
            );
        } catch (Exception repositoryProblem) {
            throw new UsernameNotFoundException("No such user", repositoryProblem);
        }
        return loadedUser;
    }


}
