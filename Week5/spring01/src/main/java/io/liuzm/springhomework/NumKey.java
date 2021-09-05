package io.liuzm.springhomework;

import io.liuzm.spring01.Student;
import lombok.Data;

import java.util.List;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 15:29:22
 * @Description: io.liuzm.springhomework
 * @version: 1.1
 */
@Data
public class NumKey {

    List<Key> keys;

    public void printNumkey() {
        System.out.println(this.getKeys());
    }
}
