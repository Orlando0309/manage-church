package com.dos.church;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import terminal.Terminal;

@SpringBootApplication
@ComponentScan({"com.dos.controller","com.dos.factory","com.dos.services"})
@EntityScan(basePackages = "com.dos.model")
public class ChurchApplication {

    public static void main(String[] args) {
        System.out.println(Terminal.class.getClassLoader());

        SpringApplication.run(ChurchApplication.class, args);
    }
}
