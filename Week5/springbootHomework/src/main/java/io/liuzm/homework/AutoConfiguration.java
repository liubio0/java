package io.liuzm.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 23:05:59
 * @Description: io.liuzm.homework
 * @version: 1.1
 */
@Configuration
@Import({StudentConfiguration.class,
        KeyConfiguration.class,
        KeyboardConfiguration.class,
        NumKeyConfiguration.class})
@EnableConfigurationProperties({StudentProperties.class,
        KeyProperties.class})
public class AutoConfiguration {

    @Autowired
    StudentProperties properties;
    @Autowired
    KeyProperties keyProperties;

    @Autowired
    StudentConfiguration configuration;
    @Autowired
    KeyConfiguration keyConfiguration;
    @Autowired
    KeyboardConfiguration keyboardConfiguration;
    @Autowired
    NumKeyConfiguration numKeyConfiguration;

    @Bean
    public Student createStudent() {
        return new Student(100, configuration.name + "-" + properties.getA());
    }

    @Bean
    public Key createKey() {
        return new Key(101, keyConfiguration.name + "-" + keyProperties.getA());
    }

    @Bean
    public NumKey CreateNumkey() {
        Key key1 = new Key(102, "key_2");
        Key key2 = new Key(103, "key_3");
        return new NumKey(numKeyConfiguration.name, Arrays.asList(key1, key2));
    }

    @Bean
    public Keyboard CreateKeyboard() {

        Key key1 = new Key(104, "key_4");
        Key key2 = new Key(105, "key_5");
        return new Keyboard(new NumKey(numKeyConfiguration.name, Arrays.asList(key1, key2)), new Key(106, keyConfiguration.name));
    }
}
