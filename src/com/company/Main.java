package com.company;

import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    private static Scanner in1 = new Scanner(System.in);
    private static Scanner in2 = new Scanner(System.in);
    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static final Path WRITE_PATH1 = Paths.get("./driver.json");

    public static void main(String[] args) throws Exception {
        ServiceImpl service = new ServiceImpl();
        commands();
        String a = "";
        while (!a.equals("7")) {
            a = buttons();
            if (a.equals("1")) {
                System.out.print("Input truck Id: ");
                int truckId = in1.nextInt();
                System.out.print("Input a new truck State: ");
                int truckState = in1.nextInt();
                service.changeTruckState(truckId, truckState);
            } else if (a.equals("2")) {
                System.out.print("Input truck Id: ");
                int truckId = in1.nextInt();
                service.changeDriver(truckId);
            } else if (a.equals("3")) {
                System.out.print("Input truck Id: ");
                int truckId = in1.nextInt();
                service.startDriving(truckId);
            } else if (a.equals("4")) {
                System.out.print("Input truck Id: ");
                int truckId = in1.nextInt();
                service.startRepair(truckId);
            } else if (a.equals("5")) {
                System.out.println("Id | Bus              | Driver   | State  |");
                service.getTrucks().stream().forEach(x -> System.out.println(x));
            } else if (a.equals("6")) {
                System.out.println("Id | Driver           | Bus           |");
                service.getDrivers().stream().forEach(x -> System.out.println(x));
            }
        }
    }

    static void commands() {
        System.out.println("--------------Commands-----------------------");
        System.out.println("Press 1 to change the truck's state");
        System.out.println("Press 2 to change Driver");
        System.out.println("Press 3 to send to the Route");
        System.out.println("Press 4 to send to the Repairing");
        System.out.println("Press 5 to display Trucks table");
        System.out.println("Press 6 to display Driver table");
        System.out.println("Press 7 to exit the App");
        System.out.println("---------------------------------------------");
    }

    public static String buttons(){
        System.out.print("Choose a command: ");
        return in2.nextLine();
    }

   public static String readTruck() {
       return getString(WRITE_PATH);
   }

   public static String readDriver() {
       return getString(WRITE_PATH1);
   }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))){
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }
}