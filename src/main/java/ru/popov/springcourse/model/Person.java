package ru.popov.springcourse.model;


import javax.validation.constraints.*;

public class Person {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 30,message = "Name should be between 2 and max 30")
    private String name;

    @Min(value = 0,message = "Age should be greater than 0")
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
