package com.example.budgeit;

public class TranType {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TranType(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
