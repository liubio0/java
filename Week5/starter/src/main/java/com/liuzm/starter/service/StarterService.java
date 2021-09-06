package com.liuzm.starter.service;

/**
 * @Author: liuzm
 * @Date: 2021-09-06 23:59:40
 * @Description: com.liuzm.starter.service
 * @version: 1.1
 */
public class StarterService {
    public String name;
    public String male;

    public StarterService(String name, String male) {
        this.name = name;
        this.male = male;
    }

    public String getString() {
        return this.name + ", " + this.male;
    }
}
