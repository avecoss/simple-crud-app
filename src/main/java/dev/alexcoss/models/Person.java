package dev.alexcoss.models;

import javax.validation.constraints.*;

public class Person {
    private static final String REGEX_ADDRESS = "[A-Z]\\w+, [A-Z]\\w+, \\d{2}-\\d{3}";

    private int id;
    private boolean isAdmin = false;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Min(value = 0, message = "Should be greater than 0")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @Pattern(regexp = REGEX_ADDRESS, message = "Your address should be in this format: Country, City, 00-000 (post code)")
    private String address;

    public Person() {
    }

    public Person(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
