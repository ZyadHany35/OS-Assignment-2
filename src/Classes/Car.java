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

    public int getCarNum() {
        return carNum;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getParkTime() {
        return parkTime;
    }

    public int getGateNum() {
        return gateNum;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setParkTime(int parkTime) {
        this.parkTime = parkTime;
    }

    public void setGateNum(int gateNum) {
        this.gateNum = gateNum;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(arrivalTime * 1000L);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        LogAndReport.logArrive(carNum, gateNum, arrivalTime);
        parkingLot.tryToPark(carNum, gateNum, parkTime);
    }
}
