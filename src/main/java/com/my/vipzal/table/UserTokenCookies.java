package com.my.vipzal.table;

import javax.persistence.*;

@Entity
@Table(name = "USER_TOKEN_COOKIES")
public class UserTokenCookies {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "ID_USER")
    public long idUser;
    @Column(name = "TIME")
    public long time;
    @Column(name = "TIME_CREATE")
    public long timeCreate;
    @Column(name = "TOKEN", length = 254)
    public String token;
}
