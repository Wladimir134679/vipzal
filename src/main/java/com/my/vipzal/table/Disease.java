package com.my.vipzal.table;

import javax.persistence.*;

@Entity
@Table(name = "DISEASE")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public long id;
    @Column(name = "NAME")
    public String name;
    @Column(name = "DESCRIPTION")
    public String description;
    @Column(name = "ID_USER")
    public long idUser;
}
