package com.liuzm.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: liuzm
 * @Date: 2021-09-05 23:09:08
 * @Description: io.liuzm.homework
 * @version: 1.1
 */
@ConfigurationProperties(prefix = "web")
public class KeyProperties {

    private String a = "super A ";

    public String getA(){
        return a;
    }

    public void setA(String a){
        this.a = a;
    }
}
