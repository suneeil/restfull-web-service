package com.learn.sprintboot.restfulwebservice.jpa;

import com.learn.sprintboot.restfulwebservice.user.Post;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository talks to the Database
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
}
