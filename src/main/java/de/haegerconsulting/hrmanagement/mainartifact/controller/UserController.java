package de.haegerconsulting.hrmanagement.mainartifact.controller;


import de.haegerconsulting.hrmanagement.mainartifact.VO.ResponseVO;
import de.haegerconsulting.hrmanagement.mainartifact.exception.exceptiontypes.ResourceNotFoundException;
import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import de.haegerconsulting.hrmanagement.mainartifact.service.SkillService;
import de.haegerconsulting.hrmanagement.mainartifact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO<Iterable<User>>> getUsers() throws ResourceNotFoundException {
        return ResponseEntity.ok(new ResponseVO<>(userService.findAll()));
    }

    @GetMapping(value = "/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO<User>> getUser(@PathVariable("user_id") String userId) throws ResourceNotFoundException {
        return ResponseEntity.ok( new ResponseVO<>(userService.get(userId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO<User>> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.created(URI.create("/" + savedUser.getId())).body(new ResponseVO<>(savedUser));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<ResponseVO<String>> deleteUser(@PathVariable("userId") String userId) {
        userService.delete(userId);
        return ResponseEntity.ok(new ResponseVO<>(userId));
    }



}
