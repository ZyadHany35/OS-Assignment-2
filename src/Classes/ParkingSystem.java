package org.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    private ParkingLot parkingLot;
    private LogAndReport logger;
    private Gate[] gates;
    private String carInputFile;

    public ParkingSystem(ParkingLot parkingLot, LogAndReport logger, Gate[] gates, String carInputFile) {
        this.parkingLot = parkingLot;
        this.logger = logger;
        this.gates = gates;
        this.carInputFile = carInputFile;
    }

    public List<Car> loadCarsFromFile() {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader carInput = new BufferedReader(new FileReader(carInputFile))) {
            String line;
            while ((line = carInput.readLine()) != null) {
                String[] parts = line.split(", ");
                int gateId = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkTime = Integer.parseInt(parts[3].split(" ")[1]);

                Car car = new Car(carId, gateId, parkTime, arrivalTime, parkingLot);
                cars.add(car);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return cars;
    }

    public void runParkingSystem() throws InterruptedException {
        List<Car> cars = loadCarsFromFile();

        List<Car> gate1Cars = new ArrayList<>();
        List<Car> gate2Cars = new ArrayList<>();
        List<Car> gate3Cars = new ArrayList<>();

        for (Car car : cars) {
            switch (car.getGateNum()) {
                case 1 -> gate1Cars.add(car);
                case 2 -> gate2Cars.add(car);
                case 3 -> gate3Cars.add(car);
                default -> System.out.println("no gate ID for car: " + car.getCarNum());
            }
        }


        gates[0] = new Gate(1, gate1Cars, parkingLot);
        gates[1] = new Gate(2, gate2Cars, parkingLot);
        gates[2] = new Gate(3, gate3Cars, parkingLot);

        Thread gate1Thread = new Thread(new Gate(1,gate1Cars,parkingLot));
        Thread gate2Thread = new Thread(new Gate(2,gate2Cars,parkingLot));
        Thread gate3Thread = new Thread(new Gate(3,gate3Cars,parkingLot));

        gate1Thread.start();
        gate2Thread.start();
        gate3Thread.start();

        gate1Thread.join();
        gate2Thread.join();
        gate3Thread.join();


        logger.printSummary();
    }
}
