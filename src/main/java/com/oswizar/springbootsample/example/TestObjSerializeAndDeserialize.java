package com.oswizar.springbootsample.example;

/**
 * @date: 2019/7/23 16:36
 * @author: oswizar
 * @description:
 */

import com.oswizar.springbootsample.entity.Person;

import java.io.*;
import java.text.MessageFormat;

public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        // 序列化Person对象
        serializePerson();

        // 反序列Perons对象
        Person p = deserializePerson();
        System.out.println(p);
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}",
                p.getName(), p.getAge(), p.getSex()));
    }


    private static void serializePerson() throws Exception {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(25);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:/Person.txt")));
        oos.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oos.close();
    }

    private static Person deserializePerson() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:/Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }

}
