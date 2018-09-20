package com.example.serialize.user;

import java.io.*;

/**
 * 父子类问题
 * 如果父类没有实现序列化，而子类实现列序列化。那么父类中的成员没办法做序列化操作
 *
 * @author uinnova
 */
public class UserSerializeDemo {

    public static void main(String[] args) {

        serializePerson();
        User user = reSerializePerson();
    }

    /**
     * 序列化
     */
    public static void serializePerson() {


        User user = new User();
        user.setAge("18");

        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(new File("user")));
            outputStream.writeObject(user);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     */
    public static User reSerializePerson() {

        ObjectInputStream inputStream;
        User user = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(new File("user")));
            user = (User) inputStream.readObject();
            System.out.println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

}
