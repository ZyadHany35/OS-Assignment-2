package org.ps;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = new ParkingLot(4);
        LogAndReport logger = new LogAndReport();
        Gate gates[] = new Gate[3];

        ParkingSystem parkingSystem = new ParkingSystem(parkingLot, logger, gates, "carOutputFile.txt");

        parkingSystem.runParkingSystem();
    }

}
