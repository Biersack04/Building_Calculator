package com.example.buildingcalculator.roomDataBase;

import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Entity(tableName = "people_customer")
public class Customer extends People {

    @Nullable
    private String telephone;

    public Customer(){

    }


    @Override
    public @NotNull String toString() {
        return "Customer{" +
                "id=" + super.getId()+
                ", name=" + super.getName() +
                ", surname=" + super.getSurname() +
                ", patronymic=" + super.getPatronymic() +
                ", status=" + super.getLegalStatus() +
                ", mailbox=" + super.getMailbox() +
                ", nameOfFirm=" + super.getNameOfFirm() +
                ", addressOfFirm='" + super.getAddressOfFirm() +
                ", telephone=" + telephone +
                '}';
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
