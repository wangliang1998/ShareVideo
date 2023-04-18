package com.sharevideo.video.Data;

public class User {

    private String username;
    private String password;
    private int age;
    private String sex;
    private String address;
    private String register_time;
    private String phone;
    private String icon;
    private int role;

    public User() {
    }

    public User(String username, String password, int age, String sex, String address, String register_time, String phone, String icon, int role) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.register_time = register_time;
        this.phone = phone;
        this.icon = icon;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
