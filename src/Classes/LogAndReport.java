package org.ps;
// How to use make and instance of LogAndReport in your class and call logger.method you want to logging status
public class LogAndReport {
    private static int totalCarsServed = 0;
    private static final int[] gateCarsServed = new int[3];

    public static synchronized void logArrive(int carNum, int gateNum,int arriveTime) {
        System.out.println("Car " + carNum + " from Gate " + gateNum + " arrived at time " + arriveTime );
    }

    public static synchronized void logPark(int carNum, int gateNum, int occupiedSpots) {
        totalCarsServed++;
        gateCarsServed[gateNum - 1]++;
        System.out.println("Car " + carNum + " from Gate " + gateNum + " parked. (Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public static synchronized void logWait(int carNum, int gateNum) {
        System.out.println("Car " + carNum + " from Gate " + gateNum + " waiting for a spot.");
    }

    public static synchronized void logParkAfterWaiting(int carNum, int gateNum, int occupiedSpots,long waiting) {
        totalCarsServed++;
        gateCarsServed[gateNum - 1]++;
        System.out.println("Car " + carNum + " from Gate " + gateNum +" after waiting for " + waiting + " units of time parked. (Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public static synchronized void logLeaving(int carNum, int gateNum, int parkTime, int occupiedSpots) {
        System.out.println("Car " + carNum + " from Gate " + gateNum + " left after " + parkTime + " units of time. (Parking Status: " + occupiedSpots + " spots occupied)");
    }

    public static void printSummary() {
        System.out.println("\nTotal Cars Served: " + totalCarsServed);
        System.out.println("Current Cars in Parking: 0");
        System.out.println("Details:");
        for (int i = 0; i < gateCarsServed.length; i++) {
            System.out.println("- Gate " + (i + 1) + " served " + gateCarsServed[i] + " cars.");
        }
    }
}

