package io.liuzm.springhomework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 15:17:55
 * @Description: io.liuzm.springhomework
 * @version: 1.1
 */
public class QuestionTwo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextNew.xml");

        //加载通过XML配置创建的bean。
        Key key103 = (Key) context.getBean("key103");
        key103.print();
        System.out.println(key103);

        Keyboard keyboard = (Keyboard) context.getBean("keyboard");
        keyboard.printKeyboard();

        //xml配置component-scan，扫描加载@Component注解的java类加载class
        ComponentScan componentScan = (ComponentScan) context.getBean("componentScan");
        componentScan.print();

        //java代码创建
        Mouse mouse = new Mouse(1, "Logitech");
        System.out.println(mouse);

    }
}
