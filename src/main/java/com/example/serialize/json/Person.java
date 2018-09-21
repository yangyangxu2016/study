package com.example.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

import javax.sound.midi.Soundbank;

/**
 * @author uinnova
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 7179112825220495519L;

    public static int height = 2;

    @Protobuf(fieldType = FieldType.INT32)
    private int age;
    @Protobuf(fieldType = FieldType.STRING)
    private String name;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\uinnova\\Desktop\\ThingJS场景\\");

        ArrayList arrayList = new ArrayList();
        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                String name = file1.getName();
                name = "\"" + name + "\"";
                arrayList.add(name);
                System.out.println(name);
            }
        }
        Object parse = JSON.parse(arrayList.toString());
        FileUtils.write(new File("13827.json"),parse.toString()  , "utf-8");

    }


}
