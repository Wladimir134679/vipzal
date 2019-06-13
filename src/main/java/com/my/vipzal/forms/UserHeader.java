package com.my.vipzal.forms;

import com.my.vipzal.table.User;

public class UserHeader {

    public User user;

    public UserHeader() {
    }

    public UserHeader(User user) {
        this.user = user;
    }

    public String getFIO(){
        return user.fio;
    }
}
