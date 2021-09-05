package io.liuzm.springhomework;
import org.springframework.stereotype.Component;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 16:13:59
 * @Description: io.liuzm.springhomework
 * @version: 1.1
 */
@Component
public class ComponentScan {
    public void print(){
        System.out.println("It's loaded by @Component and component-scan.");
    }
}
