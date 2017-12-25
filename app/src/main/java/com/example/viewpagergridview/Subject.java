package com.example.viewpagergridview;

/**
 * Created by CZY on 2017/6/23.
 */
public class Subject {

    //主题名
    private String name;

    //主题图标资源ID
    private String icon;

    public Subject(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
