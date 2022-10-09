package com.company.entities;

public class Driver {
    private int idDriver;
    private String name;
    private String truckName;

    public Driver() {
    }

    public Driver(int idDriver, String name, String truckName) {
        this.idDriver = idDriver;
        this.name = name;
        this.truckName = truckName;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    @Override
    public String toString() {
        String s1 = String.format("%-3s|", idDriver);
        String s2 = String.format("%-18s|", name);
        String s3 = String.format("%-15s|", truckName);
        return s1 + s2 + s3;
    }
}
