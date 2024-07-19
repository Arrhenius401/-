package com.work.entity;

import javafx.beans.property.SimpleStringProperty;

public class PutTable {
    public SimpleStringProperty startTime = new SimpleStringProperty();
    public SimpleStringProperty endTime = new SimpleStringProperty();

    public String getStartTime() {
        return startTime.get();
    }

    public SimpleStringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getEndTime() {
        return endTime.get();
    }

    public SimpleStringProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }
}
