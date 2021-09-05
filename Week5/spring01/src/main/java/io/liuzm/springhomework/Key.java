package io.liuzm.springhomework;

import io.liuzm.spring01.Student;
import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 15:21:44
 * @Description: io.liuzm.springhomework
 * @version: 1.1
 */
@Data
public class Key implements Serializable,BeanNameAware,ApplicationContextAware {
    private int id;
    private String name;

    private String beanName;
    private ApplicationContext applicationContext;

    public void Key(int id, String name){
        id = id;
        name = name;
    }

    public void init(){
        System.out.println("hello, it's a key");
    }

//    public static Key create(){
//        return new Key(101,"1");
//    }

    public void print() {
        System.out.println(this.beanName);
        System.out.println("context.getBeanDefinitionNames() ===>> "
                + String.join(",", applicationContext.getBeanDefinitionNames()));

    }
}
