package io.liuzm.springhomework;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 15:21:53
 * @Description: io.liuzm.springhomework
 * @version: 1.1
 */
@Data
public class Keyboard {

    // Resource
    @Autowired(required = true) //primary
    NumKey numkey;

    @Resource(name = "key101")
    Key key101;

    public void printKeyboard() {
        System.out.println("Numkey have " + this.numkey.getKeys().size() + " keys, they are " + numkey.getKeys());
    }
}
