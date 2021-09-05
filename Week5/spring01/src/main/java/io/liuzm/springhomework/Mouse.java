package io.liuzm.springhomework;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 16:27:49
 * @Description: io.liuzm.springhomework
 * @version: 1.1
 */
@Data
public class Mouse implements Serializable {
    private int id;
    private String name;

    public Mouse(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
