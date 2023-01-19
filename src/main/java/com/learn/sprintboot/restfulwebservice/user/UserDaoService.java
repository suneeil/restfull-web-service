package com.learn.sprintboot.restfulwebservice.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static Integer userId = 0;

    static {
        users.add(new User(++userId, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userId, "Eve", LocalDate.now().minusYears(10)));
        users.add(new User(++userId, "Mark", LocalDate.now().minusYears(35)));
        users.add(new User(++userId, "Paul", LocalDate.now().minusYears(20)));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User createUser(User user) {
        Integer newUserId = ++userId;
        user.setId(newUserId);
        users.add(user);
        return user;
    }

    public User getUser(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteUserById(@Valid Integer id) {
        //Predicate<User> predicate = user -> user.getId().equals(id);
        // users.removeIf(predicate);
        users.removeIf(user -> user.getId().equals(id));
    }
}
