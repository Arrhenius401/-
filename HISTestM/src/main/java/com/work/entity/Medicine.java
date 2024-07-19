package com.work.entity;

import javafx.scene.control.Button;

import java.io.Serializable;

public class Medicine extends HisItem{

    private double price;          //药物价格
    private int number;         //药物数量
    private String description; //药物描述
    private boolean isPrescription;     //是否是处方药
    private Button operater;        //药物页面的操作按钮

    public Medicine(){
        super();
    }

    public Medicine(String UID, String name, double price, int number, String description, boolean isPrescription){
        super(UID,name);
        this.setPrice(price);
        this.setNumber(number);
        this.setDescription(description);
        this.setPrescription(isPrescription);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrescription() {
        return isPrescription;
    }

    public void setPrescription(boolean prescription) {
        isPrescription = prescription;
    }

    public Button getOperater() {return operater;}

    public void setOperater(Button operater) {this.operater = operater;}


    @Override
    public String toString() {
        return "Medicine{" +
                "UID='" + this.getUID() +'\'' +
                "name='" + this.getName() + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }
}
