package com.learn.sprintboot.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id         //>>> JPA
    @GeneratedValue     //>>> JPA
    private Integer id;
    @Size(min = 10, message = "Description should have at least 2 characters")
    private String description;

    //@ManyToOne(fetch-FetchType_EAGER) If you want to retrieve the details of Post and User in the same query you can use fetch.EagerQuery
    //@ManyToOne(fetch-FetchType_LAZY) If you want to retrieve the details of Post and don't want User details
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post() {
    }

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
