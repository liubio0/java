package io.liuzm.homework;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
//    @Autowired(required = true) //primary
    public NumKey numkey;

//    @Resource(name = "key101")
    public Key key101;

    public Keyboard(NumKey _numkey, Key _key){
        numkey = _numkey;
        key101 = _key;
    }

    public void printKeyboard() {
        System.out.println("Numkey have " + this.numkey.getKeys().size() + " keys, they are " + numkey.getKeys());
    }
}
