package com.example.demo;

import java.io.Serializable;

/**
 * @author uinnova
 */
public class Person  implements Serializable {

    private static final long serialVersionUID = 7179112825220495519L;

    public static int height = 2;

    private String age;

    private String name;

    private transient int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Person.height = height;
    }


    @Override
    public String toString() {
        return "Person{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
