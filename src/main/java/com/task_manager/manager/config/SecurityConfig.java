package com.task_manager.manager.config;

import com.task_manager.manager.service.impl.DetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DetailsServiceImpl detailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration/*", "/registrationConfirm/", "/auth", "/built/bundle.js", "/index**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index")
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService);
    }

}
