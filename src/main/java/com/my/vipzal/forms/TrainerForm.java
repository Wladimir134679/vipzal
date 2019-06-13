package com.my.vipzal.forms;

import com.my.vipzal.table.Trainer;

public class TrainerForm {

    public String fio;
    public String phone;
    public String email;
    public String password;
    public String description;

    public void set(Trainer trainer){
        fio = trainer.fio;
        phone = trainer.phoneNumber;
        email = trainer.email;
        password = trainer.password;
        description = trainer.description;
    }

    public void get(Trainer trainer){
        trainer.fio = fio;
        trainer.phoneNumber = phone;
        trainer.email = email;
        trainer.password = password;
        trainer.description = description;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //    public Trainer trainer;

//    public String getFio(){
//        return trainer.fio;
//    }
//
//    public String getPhone(){
//        return trainer.phoneNumber;
//    }
//
//    public String getEmail(){
//        return trainer.email;
//    }
//
//    public String getPassword(){
//        return trainer.password;
//    }
//
//    public String getDescription(){
//        return trainer.description;
//    }
//
//    ////////////////////
//
//    public void setFio(String f){
//        trainer.fio = f;
//    }
//
//    public void setPhone(String p){
//        trainer.phoneNumber = p;
//    }
//
//    public void setEmail(String e){
//        trainer.email = e;
//    }
//
//    public void setPassword(String p){
//        trainer.password = p;
//    }
//
//    public void setDescription(String s){
//        trainer.description = s;
//    }
}
