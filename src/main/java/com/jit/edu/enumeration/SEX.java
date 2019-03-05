package com.jit.edu.enumeration;


/**
 * @author:guxiang
 * @version:2019/3/3
 */
public enum SEX {

    /**
     * 代表男生
     */
    BOY(0,"男"),

    /**
     * 代表女生
     */
    GIRL(1,"女");

    private  int sign;
    private  String value;

    SEX(int sign, String value) {
        this.sign = sign;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
