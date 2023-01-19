package com.learn.sprintboot.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {

    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 characters")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth date should be past date")
    private LocalDate birthDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
/*
"message": "Validation failed for argument [0]
in public org.springframework.http.ResponseEntity<com.learn.sprintboot.restfulwebservice.user.User>
com.learn.sprintboot.restfulwebservice.user.UserResource.createUser(com.learn.sprintboot.restfulwebservice.user.User)
with 2 errors: [Field error in object 'user' on field 'name': rejected value [];
codes [Size.user.name,Size.name,Size.java.lang.String,Size];
arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.name,name];
 arguments []; default message [name],2147483647,2]; default message [Name should have at least 2 characters]]
 [Field error in object 'user' on field 'birthDate': rejected value [2025-07-07];
 codes [Past.user.birthDate,Past.birthDate,Past.java.time.LocalDate,Past];
 arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.birthDate,birthDate]; arguments [];
 default message [birthDate]]; default message [Birth date should be past date]] ",

 */