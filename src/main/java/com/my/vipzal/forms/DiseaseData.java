package com.my.vipzal.forms;

import com.my.vipzal.table.Disease;

public class DiseaseData {

    public Disease disease;

    public DiseaseData(Disease disease) {
        this.disease = disease;
    }

    public String getName(){
        return disease.name;
    }

    public String getDescription(){
        return disease.description;
    }

    public long getID(){
        return disease.id;
    }
}
