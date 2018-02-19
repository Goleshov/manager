package com.task_manager.manager.controller;

import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.VerificationToken;
import com.task_manager.manager.event.OnRegistrationCompleteEvent;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.service.UserService;
import com.task_manager.manager.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.spring.web.json.Json;

import javax.xml.bind.ValidationException;
import java.util.Locale;

@Controller
public class AuthorizationController {

    @Autowired
    UserService userService;
    @Autowired
    VerificationTokenService tokenService;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/user")
    ResponseEntity  getUserParams(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity(userService.findUserByEmail(email), HttpStatus.OK);

    }

    @RequestMapping(value = "/registration/{role}", method = RequestMethod.POST)
    ResponseEntity registration(@PathVariable String role, @RequestBody User user, WebRequest request) throws ExpectedException, ValidationException {
        userService.createUser(user, role);
        String appUrl = request.getContextPath();
        Locale locale = request.getLocale();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, locale, appUrl));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registrationConfirm/", method = RequestMethod.GET)
    String confirm(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User user = verificationToken.getUser();
        user.setIsActivated(true);
        userService.activateUser(user);
       return "index";
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity error(UsernameNotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


