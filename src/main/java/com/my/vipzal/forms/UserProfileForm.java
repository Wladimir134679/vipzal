package com.my.vipzal.forms;

import com.my.vipzal.table.Trainer;
import com.my.vipzal.table.User;

public class UserProfileForm {

    public User user;
    public Trainer dataTrainer;

    public UserProfileForm(User user) {
        this.user = user;
    }

    public long getId(){
        return user.id;
    }

    public void setDataTriner(Trainer trainer){
        this.dataTrainer = trainer;
    }

    public String getFIO(){
        return user.fio;
    }

    public String getEmail(){
        return user.email;
    }

    public int getAge(){
        return user.age;
    }

    public int getMass(){
        return user.mass;
    }

    public int getGrowth(){
        return user.growth;
    }

    public String getPhone(){
        return user.phoneNumber;
    }

    public String getAddress(){
        return user.addressResidence;
    }

    public String getTrainerFIO(){
        return dataTrainer.fio;
    }

    public String getTrainerPhone(){
        return dataTrainer.phoneNumber;
    }
}
