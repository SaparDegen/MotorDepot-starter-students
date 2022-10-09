package com.company.entities;

public class Truck {
    private int id;
    private String truckName;
    private String driverName;
    private State State;

    public Truck(int id, String truckName, String driverName, State State) {
        this.id = id;
        this.truckName = truckName;
        this.driverName = driverName;
        this.State = State;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public State getState() {
        return State;
    }

    public void setState(State state) {
        State = state;
    }

    @Override
    public String toString() {
        String s1 = String.format("%-3s|", id);
        String s2 = String.format("%-18s|", truckName);
        String s3 = String.format("%-10s|", driverName);
        String s4 = String.format("%-8s|", State);
        return s1 + s2 + s3 + s4;
    }
}

