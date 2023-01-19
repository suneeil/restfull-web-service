package com.learn.sprintboot.restfulwebservice.helloworld;

public class HelloWorldBean {

    private final String message;

    public String getMessage() {
        return message;
    }

    public HelloWorldBean(String message) {
        this.message = message;
    }

   /* @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }*/

}
