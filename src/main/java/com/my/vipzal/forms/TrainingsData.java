package com.my.vipzal.forms;

import com.my.vipzal.table.Trainer;
import com.my.vipzal.table.Trainings;
import com.my.vipzal.table.User;

public class TrainingsData {

    public Trainings trainings;
    public Trainer trainer;
    public User user;

    public TrainingsData(Trainings trainings) {
        this.trainings = trainings;
    }

    public long getId(){
        return trainings.id;
    }

    public int getDay(){
        return trainings.day;
    }

    public int getHour(){
        return trainings.hour;
    }

    public int getMonth(){
        return trainings.month;
    }

    public int getYear(){
        return trainings.year;
    }

    public String getData(){
        return String.valueOf(getDay() + "." + getMonth() + "." + getYear());
    }

    public String getTime(){
        return String.valueOf(getHour() + ":15");
    }

    public String getFIOUser(){
        return user.fio;
    }

    public String getPhoneUser(){
        return user.phoneNumber;
    }

    public String getEmailUser(){
        return user.email;
    }

    public long getIdUser(){
        return user.id;
    }

    public String getFIOTrainer(){
        return trainer.fio;
    }

    public String getPhoneTrainer(){
        return trainer.phoneNumber;
    }

    public String getEmailTrainer(){
        return trainer.email;
    }
}
