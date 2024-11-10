package org.ps;

public class LogAndReport {
    private int totalCarsServed = 0;
    private final int[] gateCarsServed = new int[3];

    public synchronized void logArrive(int carId, int gateId,int arriveTime) {
        System.out.println("Car " + carId + " from Gate " + gateId + " arrived at time " + arriveTime );
    }

    public synchronized void logPark(int carId, int gateId, int occupiedSpots) {
        totalCarsServed++;
        gateCarsServed[gateId - 1]++;
        System.out.println("Car " + carId + " from Gate " + gateId + " parked. (Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public synchronized void logWait(int carId, int gateId, int waitTime) {
        if (waitTime > 0) {
            System.out.println("Car " + carId + " from Gate " + gateId + " waited for " + waitTime + " parked. (Parking Status: 4 spots occupied)");
        } else {
            System.out.println("Car " + carId + " from Gate " + gateId + " waiting for a spot.");
        }
    }

    public synchronized void logLeaving(int carId, int gateId, int parkTime, int occupiedSpots) {
        System.out.println("Car " + carId + " from Gate " + gateId + " left after " + parkTime + " units of time. (Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public void printSummary() {
        System.out.println("\nTotal Cars Served: " + totalCarsServed);
        System.out.println("Current Cars in Parking: 0");
        System.out.println("Details:");
        for (int i = 0; i < gateCarsServed.length; i++) {
            System.out.println("- Gate " + (i + 1) + " served " + gateCarsServed[i] + " cars.");
        }
    }
}

