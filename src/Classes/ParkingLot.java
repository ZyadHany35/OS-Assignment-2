package org.ps;

import java.util.concurrent.Semaphore;

class ParkingLot {
    private final Semaphore spots;
    private int totalSpots;

    public ParkingLot(int t) {
        this.totalSpots = t;
        spots = new Semaphore(totalSpots, true); // for fifo ( cause no more than 1 car can access the same gate at the same time
    }

    public void tryToPark(int carNum, int gateNum, int parkTime) {
        try{
            if (spots.tryAcquire()){
                LogAndReport.logPark(carNum, gateNum, totalSpots - spots.availablePermits());
                Thread.sleep(parkTime * 1000L);
            }else{
                LogAndReport.logWait(carNum, gateNum);
                long startTime = System.currentTimeMillis();
                spots.acquire();
                long endTime = System.currentTimeMillis();

                long waiting = endTime - startTime;
                LogAndReport.logParkAfterWaiting(carNum, gateNum, totalSpots - spots.availablePermits(),waiting / 1000);
                Thread.sleep(parkTime * 1000L);

            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }finally {
            leaveSpot(carNum, gateNum, parkTime);
        }
    }

    public void leaveSpot(int carNum, int gateNum, int parkTime) {
        spots.release();
        LogAndReport.logLeaving(carNum, gateNum, parkTime,totalSpots - spots.availablePermits());
    }

    public int getOccupiedSpots() {
        return totalSpots - spots.availablePermits();
    }
}
