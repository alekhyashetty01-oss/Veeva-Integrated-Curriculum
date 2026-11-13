package com.studentapp.model;

import java.io.Serializable;

/**
 * Demonstrates Abstraction and Inheritance base class.
 */
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    // Encapsulated private attributes
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Abstract method demonstrating Polymorphism in subclasses
    public abstract String getRoleDescription();
}
