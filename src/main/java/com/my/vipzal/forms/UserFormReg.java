package com.my.vipzal.forms;

import com.my.vipzal.table.User;

public class UserFormReg {

    public String fio;
    public int age;
    public int growth;
    public int mass;
    public String address;
    public String phone;
    public String email;
    public String password;
    public String password1;

    public void set(User user){
        fio = user.fio;
        age = user.age;
        growth = user.growth;
        mass = user.mass;
        address = user.addressResidence;
        phone = user.phoneNumber;
        email = user.email;
        password = user.password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrowth() {
        return growth;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }
}
