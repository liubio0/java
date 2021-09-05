package io.liuzm.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
        System.out.println("springboot app is running···");
    }

    //自动装配
    @Autowired
    Student info;

    @Autowired
    Key key;

    @Autowired
    Keyboard keyboard;

    @Autowired
    NumKey numKey;

    @Bean
    public void printInfo(){
        System.out.println(info.getName());
        System.out.println(key);
        System.out.println(keyboard);
        System.out.println(numKey);
    }

}
