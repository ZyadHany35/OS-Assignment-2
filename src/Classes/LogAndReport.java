package org.ps;
// How to use make and instance of LogAndReport in your class and call logger.method you want to logging status
public class LogAndReport {
    private int totalCarsServed = 0;
    private final int[] gateCarsServed = new int[3];

    public synchronized void logArrive(int carNum, int gateNum,int arriveTime) {
        System.out.println("Car " + carNum + " from Gate " + gateNum + " arrived at time " + arriveTime );
    }

    public synchronized void logPark(int carNum, int gateNum, int occupiedSpots) {
        totalCarsServed++;
        gateCarsServed[gateNum - 1]++;
        System.out.println("Car " + carNum + " from Gate " + gateNum + " parked. (Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public synchronized void logWait(int carNum, int gateNum, int waitTime) {
        if (waitTime > 0) {
            System.out.println("Car " + carNum + " from Gate " + gateNum + " waited for " + waitTime + " parked. (Parking Status: 4 spots occupied)");
        } else {
            System.out.println("Car " + carNum + " from Gate " + gateNum + " waiting for a spot.");
        }
    }

    public synchronized void logLeaving(int carNum, int gateNum, int parkTime, int occupiedSpots) {
        System.out.println("Car " + carNum + " from Gate " + gateNum + " left after " + parkTime + " units of time. (Parking Status: " + occupiedSpots + " spots occupied)");
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

