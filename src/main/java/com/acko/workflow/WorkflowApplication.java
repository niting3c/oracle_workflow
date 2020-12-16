package com.acko.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.acko.dao")
@EntityScan({"com.acko.entities"})
@ComponentScan(basePackages = "com.acko")
public class WorkflowApplication {
    private static final Logger LOG = LoggerFactory.getLogger(WorkflowApplication.class.getCanonicalName());

    public static void main(String[] args) {
        LOG.info("Starting workflow server");
        SpringApplication.run(WorkflowApplication.class, args);
    }

}
