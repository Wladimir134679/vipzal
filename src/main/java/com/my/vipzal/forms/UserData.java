package com.my.vipzal.forms;

import com.my.vipzal.table.User;

public class UserData {

    public User user;

    public UserData(User user) {
        this.user = user;
    }

    public long getId(){
        return user.id;
    }

    public String getFIO(){
        return user.fio;
    }

    public String getPhone(){
        return user.phoneNumber;
    }
}
