package com.my.vipzal.table;

import javax.persistence.*;

@Entity
@Table(name = "TRAINER")
public class Trainer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    @Column(name = "FIO")
    public String fio;
    @Column(name = "EMAIL")
    public String email;
    @Column(name = "PASSWORD")
    public String password;
    @Column(name = "PHONE_NUMBER")
    public String phoneNumber;
    @Column(name = "NUMBER_END_CLASSES")
    public int numberEndClasses;
    @Column(name = "DESCRIPTION")
    public String description;

    public void set(Trainer trainer){
        fio = trainer.fio;
        email = trainer.email;
        password = trainer.password;
        phoneNumber = trainer.phoneNumber;
        description = trainer.description;
    }
}
