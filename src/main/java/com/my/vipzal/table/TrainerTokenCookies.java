package com.my.vipzal.table;

import javax.persistence.*;

@Entity
@Table(name = "TRAINER_TOKEN_COOKIES")
public class TrainerTokenCookies {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    @Column(name = "ID_TRAINER")
    public long idTrainer;
    @Column(name = "TOKEN")
    public String token;
    @Column(name = "time")
    public long time;
    @Column(name = "age")
    public int age;
}
