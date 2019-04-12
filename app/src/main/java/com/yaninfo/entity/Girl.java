package com.yaninfo.entity;

/**
 * @Author: zhangyan
 * @Date: 2019/3/27 10:10
 * @Description:
 * @Version: 1.0
 */
public class Girl {

    private String name;
    private int girlId;

    public Girl(String name, int girlId) {
        this.name = name;
        this.girlId = girlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGirlId() {
        return girlId;
    }

    public void setGirlId(int girlId) {
        this.girlId = girlId;
    }
}
