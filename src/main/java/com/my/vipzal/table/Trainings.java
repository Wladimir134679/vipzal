package com.my.vipzal.table;

import javax.persistence.*;

@Entity
@Table(name = "TRAININGS")
public class Trainings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public long id;
    @Column(name = "ID_USER")
    public long idUser;
    @Column(name = "ID_TRAINER")
    public long idTrainer;
    @Column(name = "DAY")
    public int day;
    @Column(name = "MONTH")
    public int month;
    @Column(name = "YEAR")
    public int year;
    @Column(name = "HOUR")
    public int hour;
}
