package com.liuzm.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 23:05:59
 * @Description: io.liuzm.homework
 * @version: 1.1
 */
@Configuration
@Import(KeyConfiguration.class)
@EnableConfigurationProperties(KeyProperties.class)
public class AutoConfiguration {

    @Autowired
    KeyProperties keyProperties;
    @Autowired
    KeyConfiguration keyConfiguration;

    @Bean
    public Key createKey() {
        return new Key(101, keyConfiguration.name + "-" + keyProperties.getA());
    }
}
