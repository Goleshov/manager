package com.task_manager.manager.controller;

import com.task_manager.manager.entity.Task;
import com.task_manager.manager.entity.enums.Status;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.service.CommentService;
import com.task_manager.manager.service.ProjectService;
import com.task_manager.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    ResponseEntity getProjects() {

        return new ResponseEntity(projectService.findProjectsByUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/{id}/tasks", method = RequestMethod.GET)
    ResponseEntity getTasksByProjectId(@PathVariable Long id) {
        return new ResponseEntity(taskService.findTasksByProjectId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    ResponseEntity getTask(@PathVariable Long id) throws ExpectedException{
        return new ResponseEntity(taskService.findTaskById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    ResponseEntity getUsersTasts(){
        return new ResponseEntity(taskService.findTasksByUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    ResponseEntity deleteComment(@PathVariable Long id) throws ExpectedException {
        commentService.deleteComment(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/{id}/{text}", method = RequestMethod.PUT)
    ResponseEntity updateComment(@PathVariable Long id, @PathVariable String text) throws ExpectedException {
        commentService.updateComment(id, text);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{projectId}", method = RequestMethod.POST)
    ResponseEntity createTask(@PathVariable Long projectId, @RequestBody Task task) throws ExpectedException {

        taskService.createTask(projectId, task);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/comments/{taskId}/{text}", method = RequestMethod.POST)
    ResponseEntity createComment(@PathVariable Long taskId, @PathVariable String text) throws ExpectedException {

        commentService.createComment(taskId, text);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tasks/{id}/{status}", method = RequestMethod.PUT)
    ResponseEntity updateTaskStatus(@PathVariable Long id, @PathVariable String status) throws ExpectedException {
        taskService.updateTaskStatus(Status.valueOf(status.toUpperCase()), id);
        return new ResponseEntity(HttpStatus.OK);
    }



    @RequestMapping(value = "/index", method = RequestMethod.GET)
    String getSinglePae() {
        return "index";
    }

}
