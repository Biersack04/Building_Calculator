package com.example.buildingcalculator;

public class State {

    private String name; // название
    // столица

    public State(String name){

        this.name=name;
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}