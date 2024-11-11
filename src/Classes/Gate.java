package org.ps;

import java.util.List;

public class Gate implements Runnable {
    private int gateNum;
    private List<Car> cars;
    private ParkingLot parkingLot;

    public Gate(int gateNum, List<Car> cars, ParkingLot parkingLot) {
        this.gateNum = gateNum;
        this.cars = cars;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        for (Car car : cars){
            car.start();
        }
        for (Car car : cars)
            try {
                car.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

    }
}
