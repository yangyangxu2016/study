package com.example.serialize.clone;

import java.io.Serializable;

/**
 * @author uinnova
 */
public class Teacher  implements Serializable {

    private static final long serialVersionUID = 316077735221187753L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }
}
