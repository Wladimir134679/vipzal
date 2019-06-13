package com.my.vipzal.table;

import javax.persistence.*;

@Entity
@Table(name = "TRAINER_FOR_USER")
public class TrainerForUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public long id;
    @Column(name = "ID_USER")
    public long idUser;
    @Column(name = "ID_TRAINER")
    public long idTrainer;
}
