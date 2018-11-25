package com.example.lenovo.mymeituanapp;

public class Order {
    private String name;
    private String person;
    private double price;

    public Order() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPerson(){return person;}

    public void setPerson(){this.person = person;}

}
