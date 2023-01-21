/*package com.learn.sprintboot.restfulwebservice.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourceJdbcCommandLineRunner implements CommandLineRunner {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());


    @Autowired
    private CourseJdbcRepository courseJdbcRepository;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info(">>>>>>>>>>>>>>>>>>>> CommandLineRunner");
        courseJdbcRepository.insert();
    }
}
*/