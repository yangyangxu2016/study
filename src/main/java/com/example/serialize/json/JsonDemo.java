package com.example.serialize.json;


import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JsonDemo {

    public static Person init() {
        Person person = new Person();
        person.setName("mic");
        person.setAge(18);
        return person;
    }


    /**
     * 测试jackjson序列化速度速度
     *
     * @throws IOException
     */
    public static void testjackJson() throws IOException {

        Person person = init();

        ObjectMapper mapper = new ObjectMapper();
        byte[] writeBytes = null;

        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            writeBytes = mapper.writeValueAsBytes(person);
        }
        System.out.println("jackJson序列化：" + (System.currentTimeMillis() - start) + "ms ：" + "总大小 : " + writeBytes.length);
        Person person1 = mapper.readValue(writeBytes, Person.class);
        System.out.println(person1);
    }


    /**
     * 测试fastjson序列化速度
     *
     * @throws IOException
     */
    public static void testFastJson() throws IOException {

        Person person = init();

        String text = null;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            text = JSON.toJSONString(person);
        }
        System.out.println("FastJson序列化：" + (System.currentTimeMillis() - start) + "ms ：" + "总大小 : " + text.getBytes().length);
        Person person1 = JSON.parseObject(text, Person.class);
        System.out.println(person1);


    }

    /**
     * 测试JprotoBuf序列化速度
     *
     * @throws IOException
     */
    public static void testJprotoBuf() throws IOException {
        Person person = init();
        Codec<Person> personCodec = ProtobufProxy.create(Person.class, false);

        Long start = System.currentTimeMillis();

        byte[] bytes = null;
        for (int i = 0; i < 10000; i++) {
            bytes = personCodec.encode(person);
        }
        System.out.println("JprotoBuf序列化：" + (System.currentTimeMillis() - start) + "ms ：" + "总大小 : " + bytes.length);
        Person person1 = personCodec.decode(bytes);
        System.out.println(person1);
    }

    /**
     * Hessian
     *
     * @throws IOException
     */
    public static void testHessian() throws IOException {
        Person person = init();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);

        Long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            ho.writeObject(person);
            if (i == 0) {
                System.out.println(os.toByteArray().length);
            }
        }
        System.out.println(" Hessian序列化：" + (
                System.currentTimeMillis() - start) + "ms ：" + "总大小 : " + os.toByteArray().length);
        HessianInput hi = new HessianInput(new ByteArrayInputStream(os.toByteArray()));
        Person person1 = (Person) hi.readObject();
        System.out.println(person1);
    }

    /**
     * 对Person{age=18, name='mic'}对象循环10000次，测试速度如下
     * jackJson序列化：130ms ：总大小 : 23
     * Person{age=18, name='mic'}
     * FastJson序列化：161ms ：总大小 : 23
     * Person{age=18, name='mic'}
     * JprotoBuf序列化：25ms ：总大小 : 7
     * Person{age=18, name='mic'}
     * 62
     * Hessian序列化：5ms ：总大小 : 50057
     * Person{age=18, name='mic'}
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //jackJson序列化：83ms ：总大小 : 23
        testjackJson();
        //FastJson序列化：125ms ：总大小 : 23
        testFastJson();
        //JprotoBuf序列化：28ms ：总大小 : 7
        testJprotoBuf();
        // Hessian序列化：6ms ：总大小 : 62
        testHessian();
    }
}
