package com.sfeir.sensorsource.model;

public class SensorData {

    public SensorData() {
    }


    public SensorData(int id, int temperature) {
        this.id = id;
        this.temperature = temperature;
    }

    private int id;
    private int temperature;

    public int getId() {
        return id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
