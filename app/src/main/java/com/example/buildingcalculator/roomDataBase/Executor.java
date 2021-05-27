package com.example.buildingcalculator.roomDataBase;

import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Entity(tableName = "people_executor")
public class Executor extends People {

    @Nullable
    private Long numberOfCompletedProjects;

    @Nullable
    private Long numberOfWorkers;

    public Executor(){};



    @Nullable
    public Long getNumberOfCompletedProjects() {
        return numberOfCompletedProjects;
    }

    public void setNumberOfCompletedProjects(@Nullable Long numberOfCompletedProjects) {
        this.numberOfCompletedProjects = numberOfCompletedProjects;
    }

    public @Nullable Long getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(@Nullable Long numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    @Override
    public @NotNull String toString() {
        return "Executor{" +
                "id=" + super.getId()+
                ", name=" + super.getName() +
                ", surname=" + super.getSurname() +
                ", patronymic=" + super.getPatronymic() +
                ", status=" + super.getLegalStatus() +
                ", mailbox=" + super.getMailbox() +
                ", nameOfFirm=" + super.getNameOfFirm() +
                ", addressOfFirm='" + super.getAddressOfFirm() +
                ", numberOfCompletedProjects=" + numberOfCompletedProjects +
                ", NumberOfWorkers=" + numberOfWorkers +
                '}';
    }
}
