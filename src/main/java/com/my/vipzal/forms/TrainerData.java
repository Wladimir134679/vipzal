package com.my.vipzal.forms;

import com.my.vipzal.table.Trainer;

public class TrainerData {

    public Trainer trainer;

    public TrainerData(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getFIO(){
        return trainer.fio;
    }

    public String getPhone(){
        return trainer.phoneNumber;
    }

    public String getEmail(){
        return trainer.email;
    }

    public String getDescription(){
        return trainer.description;
    }

    public int getNumClass(){
        return trainer.numberEndClasses;
    }
}
