package io.liuzm.homework;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 15:29:22
 * @Description: io.liuzm.springhomework
 * @version: 1.1
 */
@Data
public class NumKey {

    public String name;
    List<Key> keys;

    public void printNumkey() {
        System.out.println(this.getKeys());
    }

    public NumKey(String _name, List<Key> _keys){
        name = _name;
        keys = _keys;
    }
}
