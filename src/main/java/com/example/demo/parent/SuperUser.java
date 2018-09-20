package com.example.demo.parent;

public class SuperUser  {

    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "age='" + age + '\'' +
                '}';
    }
}
