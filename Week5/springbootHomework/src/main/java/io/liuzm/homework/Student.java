package io.liuzm.homework;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 23:10:51
 * @Description: io.liuzm.homework
 * @version: 1.1
 */
@Data
public class Student implements Serializable, ApplicationContextAware {

    private int id;
    private String name;

    private ApplicationContext applicationContext;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
