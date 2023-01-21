package com.learn.sprintboot.restfulwebservice.user;

import com.learn.sprintboot.restfulwebservice.jpa.PostRepository;
import com.learn.sprintboot.restfulwebservice.jpa.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    //@Autowired
    private UserDaoService userDaoService;
    //@Autowired
    private UserRepository userJpaRepository;

    private PostRepository postRepository;

    public UserJpaResource(UserDaoService userDaoService, UserRepository userJpaRepository, PostRepository postRepository) {
        this.userDaoService = userDaoService;
        this.userJpaRepository = userJpaRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userJpaRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        Optional<User> user = userJpaRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("user not found with id: "+ id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        //To create links in response
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //This returns the "/users" path
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userJpaRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable Integer id) {
        Optional<User> user = userJpaRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("user not found with id: "+ id);
        }
        return user.get().getPosts();
    }

    @GetMapping("/jpa/users/{uid}/posts/{pid}")
    public Post retrievePost(@PathVariable Integer uid, @PathVariable Integer pid) {
        Optional<User> user = userJpaRepository.findById(uid);
        if(user.isEmpty()) {
            throw new UserNotFoundException("user not found with id: "+ uid);
        }
        Optional<Post> post = postRepository.findById(pid);
        if(post.isEmpty()) {
            throw new PostNotFoundException("post with id: "+ pid+ "not fond for user with id: "+ uid);
        }
        return post.get();
    }
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Validated @RequestBody Post post) {
        Optional<User> user = userJpaRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("user not found with id: "+ id);
        }
        post.setUser(user.get());
        Post newPost = postRepository.save(post);
        URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(uriLocation).build();
    }


    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        User newUser = userJpaRepository.save(user);
        //Note: to return the uri of the created user, e.g. after creating a new user you will get response as users/x e.g.users/4
        // Note: ServletUriComponentsBuilder.fromCurrentRequest() >>> this will give uri of the current method i.e. /users
        //Note: .path("/{id}") >> will append /{id} to /users >> /users/{id}
        // buildAndExpand(newUser.getId()) >>> will replace the {id} value
        URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        //ResponseEntity is used to send correct Response status
        return ResponseEntity.created(uriLocation).build();//To make this method return 201 status we use ResponseEntity.created(null).build() will give 201
    }

    @GetMapping("/jpa/userId")
    public User getUser(@RequestParam("uId") Integer userId) {
        Optional<User> user = userJpaRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException("user not found with  uId: "+ userId);
        }
        return user.get();
    }
}

