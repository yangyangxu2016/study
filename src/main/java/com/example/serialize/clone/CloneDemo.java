package com.example.serialize.clone;

import java.io.IOException;

/**
 * 浅拷贝（浅复制、浅克隆）：被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象。
 * 　　换言之，浅拷贝仅仅复制所拷贝的对象，而不复制它所引用的对象。
 * 深拷贝（深复制、深克隆）：被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。
 * 　　那些引用其他对象的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象。
 * 　　换言之，深拷贝把要复制的对象所引用的对象都复制了一遍
 *
 * 利用序列化进行深度克隆对象
 *
 * @author uinnova
 */
public class CloneDemo {


    /**
     * Student{age=18, name='mufeng', teacher=Teacher{name='nuc'}}
     * Student{age=18, name='mufeng', teacher=Teacher{name='second'}}
     * Student{age=18, name='mufeng', teacher=Teacher{name='third'}}
     * false
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Teacher teacher = new Teacher();
        teacher.setName("nuc");

        Student student = new Student();
        student.setName("mufeng");
        student.setAge(18);
        student.setTeacher(teacher);

        System.out.println(student);


        Student student1 = (Student) student.clone();
        student1.getTeacher().setName("second");
        System.out.println(student1);


        //深度克隆一个对象
        Student student2 = (Student) student.deepClone();
        //深度克隆后改变对象引用的对象，原对象不会变化，表示此克隆为深度克隆
        student2.getTeacher().setName("third");
        System.out.println(student2);

        System.out.println(student == student2);



    }


}
