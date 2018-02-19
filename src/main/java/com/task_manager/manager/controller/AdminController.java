package com.task_manager.manager.controller;

import com.task_manager.manager.entity.Project;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.service.CommentService;
import com.task_manager.manager.service.ProjectService;
import com.task_manager.manager.service.TaskService;
import com.task_manager.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/project/create", method = RequestMethod.POST)
    ResponseEntity createProject(@RequestBody Project project) {
        projectService.createProject(project);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/project/user/add", method = RequestMethod.POST)
    ResponseEntity addUserToProject(@RequestParam Long projectId, @RequestParam Long userId) throws ExpectedException {
        User user = userService.findUserById(userId);
        projectService.addUserToProject(userId, projectId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/task/user/add", method = RequestMethod.POST)
    ResponseEntity addUserToTask(@RequestParam Long taskId, @RequestParam  Long userId) throws ExpectedException {
        User user = userService.findUserById(userId);
        taskService.addUserToTask(userId, taskId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/users/{name}/{surname}", method = RequestMethod.GET)
    ResponseEntity findUser(@PathVariable String name, @PathVariable String surname) {

        return new ResponseEntity(userService.findUserByNameAndSurname(name, surname), HttpStatus.OK);
    }



}
