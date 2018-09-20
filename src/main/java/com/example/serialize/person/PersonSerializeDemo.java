package com.example.serialize.person;

import java.io.*;

/**
 * 静态变量的序列化
 * 序列化并不保存静态变量的状态
 *
 * Transient关键字
 * transient关键字表示指定属性不参与序列化
 *
 * @author uinnova
 */
public class PersonSerializeDemo {


    public static void main(String[] args) {

        serializePerson();

        Person.height = 5;

        Person person = reSerializePerson();

        System.out.println(person.height);
    }

    /**
     * 序列化
     */
    public static void serializePerson() {


        Person person = new Person();
        person.setAge("18");
        person.setName("xuyagnyang");
        person.setId(1);
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(new File("person")));
            outputStream.writeObject(person);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     */
    public static Person reSerializePerson() {

        ObjectInputStream inputStream;
        Person person = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(new File("person")));
            person = (Person) inputStream.readObject();
            System.out.println(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

}
