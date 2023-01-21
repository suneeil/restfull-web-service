package com.learn.sprintboot.restfulwebservice.jpa;

import com.learn.sprintboot.restfulwebservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository talks to the Database
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
