package com.example.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 双重检查锁
 *
 * @author xuyy
 */
public class TestSingleton implements Serializable {

    private static volatile TestSingleton singleton = null;

    /**
     * 防止通过反射破解（除枚举单例）
     */
    private TestSingleton() {
        if (singleton != null) {
            throw new RuntimeException("不能通过反射场景对象");
        }
    }

    /**
     * 实现此方法防止反序列化（除枚举单例）
     *
     * @return
     */
    private Object readResolve() {
        return singleton;
    }


    public static TestSingleton getSingleton() {
        if (singleton == null) {
            synchronized (TestSingleton.class) {
                if (singleton == null) {
                    singleton = new TestSingleton();
                }
            }
        }
        return singleton;
    }


    public static void main(String[] args) {

//       测试单例模型

//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                TestSingleton.getSingleton();
//            }).start();
//        }

//        通过反射破解
//        try {
//            Class<?> aClass = Class.forName("com.example.thread.TestSingleton");
//            Constructor<?> c = aClass.getDeclaredConstructor(null);
//            c.setAccessible(true);
//            Object o1 = c.newInstance();
//            Object o2 = c.newInstance();
//            System.out.println(o1);
//            System.out.println(o2);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

//        通过反序列化破解
        TestSingleton s1 = TestSingleton.getSingleton();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            File file = new File("d:/a.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);
            oos.close();
            fos.close();

            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Object s2 = (TestSingleton) ois.readObject();
            System.out.println(s1);
            System.out.println(s2);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
