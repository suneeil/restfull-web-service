package com.learn.sprintboot.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello New World");
    }

    @GetMapping(path = "/hello-world/path-variable/{newName}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String newName) {
        return new HelloWorldBean("Path-Variable...... Hello " + newName);
    }

    /*
    In the API request user need to set Accept-Language
    Return based in different language based on Accept-Language Header
     */
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternalization() {
        Locale local = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", local);
    }
}
