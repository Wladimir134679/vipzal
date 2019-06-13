package com.my.vipzal.forms;

import com.my.vipzal.table.User;

public class UserHeaderForm {

    public String name;

    public UserHeaderForm(){}

    public UserHeaderForm(User user){
        name = user.fio;
    }
}
