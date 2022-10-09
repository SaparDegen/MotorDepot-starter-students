package com.company.service;

import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.Main.*;

public class ServiceImpl implements Service{
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTruck(), Truck[].class)));
    List<Truck> trucksNew = new ArrayList<>(trucks);
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(),Driver[].class)));
    List<Driver> driversNew = new ArrayList<>(drivers);

    public List<Truck> getTrucks() {
        return trucksNew;
    }

    public List<Driver> getDrivers() {
        return driversNew;
    }

    public Truck findTruckById(int truckId) {
        Truck truck = trucksNew.stream().filter(x -> x.getId() == truckId).findAny().orElse(null);
        if (truck == null) {
            try {
                throw new Exception("Selected truck not found!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return truck;
    }

    public Driver findDriver() {
        Driver driverFree = driversNew.stream().filter(x -> x.getTruckName().equals("")).findFirst().orElse(null);
        if (driverFree == null) {
            try {
                throw new Exception("No free drivers!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return driverFree;
    }

    @Override
    public void changeDriver(int truckId) {
        Truck truck = findTruckById(truckId);
        Driver driver = findDriver();
        if (truck.getState() == State.BASE) {
            if (driver != null) {
                driver.setTruckName(truck.getTruckName());
                if (truck.getDriverName() != null) {
                    String driverPriorName = truck.getDriverName();
                    driversNew.stream().filter(x -> x.getName().equals(driverPriorName)).findAny().get().setTruckName("");
                }
                truck.setDriverName(driver.getName());
                System.out.println("Now a truck " + truck.getTruckName() + " is driven by driver " + driver.getName());
            }
        } else if (truck.getState() == State.ROUTE) {
            System.out.println("The truck is on the way, it is impossible to change the driver");
        } else if (truck.getState() == State.REPAIR) {
            System.out.println("The truck is under repair, you can not change the driver");
        }
    }

    @Override
    public void startDriving(int truckId) {
        Random random = new Random();
        Truck truck = findTruckById(truckId);
        if (truck.getState() == State.BASE) {
            if (truck.getDriverName() != null) {
                truck.setState(State.ROUTE);
                System.out.println("The truck successfully went on the route");
            }
        } else if (truck.getState() == State.ROUTE) {
            System.out.println("Truck is on the way");
        } else if (truck.getState() == State.REPAIR) {
            int r = random.nextInt(1, 3);
            if (r == 1) {
                truck.setState(State.BASE);
                System.out.println("Truck is on the base");
            } else if (r == 2) {
                truck.setState(State.ROUTE);
                System.out.println("Truck is on the way");
            }
        }
    }

    @Override
    public void startRepair(int truckId) {
        Truck truck = findTruckById(truckId);
        if (truck.getState() == State.BASE || truck.getState() == State.ROUTE) {
            truck.setState(State.REPAIR);
            System.out.println("The truck sent for repair");
        } else if (truck.getState() == State.REPAIR) {
            System.out.println("The truck almost is under repair");
        }
    }

    @Override
    public void changeTruckState(int truckId, int state) {
        Truck truck = findTruckById(truckId);
        if (truck != null) {
            if (state == 1) {
                truck.setState(State.BASE);
            } else if (state == 2) {
                truck.setState(State.ROUTE);
            } else if (state == 3) {
                truck.setState(State.REPAIR);
            }
        }
    }
}












