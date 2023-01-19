package com.learn.sprintboot.restfulwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        User user = userDaoService.getUser(id);
        if(user == null) {
            throw new UserNotFoundException("user not found with id: "+ id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        //To create links in response
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //This returns the "/users" path
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userDaoService.deleteUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        User newUser = userDaoService.createUser(user);
        //Note: to return the uri of the created user, e.g. after creating a new user you will get response as users/x e.g.users/4
        // Note: ServletUriComponentsBuilder.fromCurrentRequest() >>> this will give uri of the current method i.e. /users
        //Note: .path("/{id}") >> will append /{id} to /users >> /users/{id}
        // buildAndExpand(newUser.getId()) >>> will replace the {id} value
        URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        //ResponseEntity is used to send correct Response status
        return ResponseEntity.created(uriLocation).build();//To make this method return 201 status we use ResponseEntity.created(null).build() will give 201
    }

    @GetMapping("/userId")
    public User getUser(@RequestParam("uId") Integer userId) {
        User user = userDaoService.getUser(userId);
        if(user == null) {
            throw new UserNotFoundException("user not found with  uId: "+ userId);
        }
        return user;
    }
}

