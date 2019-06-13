package com.my.vipzal.forms;

import com.my.vipzal.table.Trainer;

public class TrainerHeader {

    public Trainer trainer;

    public TrainerHeader() {
    }

    public TrainerHeader(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getFIO(){
        return trainer.fio;
    }
}
