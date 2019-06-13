package com.my.vipzal.table;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    @Column(name = "FIO")
    public String fio;
    @Column(name = "PASSWORD")
    public String password;
    @Column(name = "EMAIL")
    public String email;
    @Column(name = "AGE")
    public int age;
    @Column(name = "MASS")
    public int mass;
    @Column(name = "GROWTH")
    public int growth;
    @Column(name = "ADDRESS_RESIDENCE")
    public String addressResidence;
    @Column(name = "PHONE_NUMBER")
    public String phoneNumber;
    @Column(name = "NUMBER_CLASSES")
    public int numberClasses;
}
