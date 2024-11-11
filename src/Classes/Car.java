package org.ps;

class Car extends Thread {
    private int carNum;
    private int arrivalTime;
    private int parkTime;
    private int gateNum;
    private ParkingLot parkingLot;

    public Car(int carNum, int gateNum, int parkTime,int arrivalTime, ParkingLot parkingLot) {
        this.carNum = carNum;
        this.arrivalTime = arrivalTime;
        this.parkTime = parkTime;
        this.gateNum = gateNum;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.tryToPark(carNum, gateNum, parkTime);
    }
}
