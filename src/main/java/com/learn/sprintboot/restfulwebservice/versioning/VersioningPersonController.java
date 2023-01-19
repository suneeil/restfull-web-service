package com.learn.sprintboot.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //=================== version in Path =======================
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //================== RequestParameter ========================
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //================== Version with Header ========================
    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //================== Version with MediaType ========================
    @GetMapping(path = "/person", produces = "application/com.sunil.app-v1+json")
    public PersonV1 getFirstVersionOfPersonRequestAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", produces = "application/com.sunil.app-v2+json")
    public PersonV2 getSecondVersionOfPersonRequestAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
