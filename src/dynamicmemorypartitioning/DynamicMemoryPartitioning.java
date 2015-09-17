/* 
 * File:   DynamicMemoryPartitioning.java
 * Author: @Pablo A. Arias
 * Email: parias@aggies.ncat.edu
 * Course: Comp 755 - Advanced perating Systems: Dr. Bryant
 * Objective: Simulated implementation of Memory Allocation
 * Notes: 
 */
package dynamicmemorypartitioning;

import java.util.Scanner;

/**
 *
 * @author Pablo
 */
public class DynamicMemoryPartitioning {
    
    private static int mainMemSize, totalSimTime, minArrivalTime, maxArrivalTime, 
                minMemSize, maxMemSize, minUseTime, maxUseTime, seed;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DynamicMemoryPartitioning d = new DynamicMemoryPartitioning();
        d.requestInput();
        
    }

    /**
     * @return the mainMemSize
     */
    public static int getMainMemSize() {
        return mainMemSize;
    }

    /**
     * @param aMainMemSize the mainMemSize to set
     */
    public static void setMainMemSize(int aMainMemSize) {
        mainMemSize = aMainMemSize;
    }

    /**
     * @return the totalSimTime
     */
    public static int getTotalSimTime() {
        return totalSimTime;
    }

    /**
     * @param aTotalSimTime the totalSimTime to set
     */
    public static void setTotalSimTime(int aTotalSimTime) {
        totalSimTime = aTotalSimTime;
    }

    /**
     * @return the minArrivalTime
     */
    public static int getMinArrivalTime() {
        return minArrivalTime;
    }

    /**
     * @param aMinArrivalTime the minArrivalTime to set
     */
    public static void setMinArrivalTime(int aMinArrivalTime) {
        minArrivalTime = aMinArrivalTime;
    }

    /**
     * @return the maxArrivalTime
     */
    public static int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    /**
     * @param aMaxArrivalTime the maxArrivalTime to set
     */
    public static void setMaxArrivalTime(int aMaxArrivalTime) {
        maxArrivalTime = aMaxArrivalTime;
    }

    /**
     * @return the minMemSize
     */
    public static int getMinMemSize() {
        return minMemSize;
    }

    /**
     * @param aMinMemSize the minMemSize to set
     */
    public static void setMinMemSize(int aMinMemSize) {
        minMemSize = aMinMemSize;
    }

    /**
     * @return the maxMemSize
     */
    public static int getMaxMemSize() {
        return maxMemSize;
    }

    /**
     * @param aMaxMemSize the maxMemSize to set
     */
    public static void setMaxMemSize(int aMaxMemSize) {
        maxMemSize = aMaxMemSize;
    }

    /**
     * @return the minUseTime
     */
    public static int getMinUseTime() {
        return minUseTime;
    }

    /**
     * @param aMinUseTime the minUseTime to set
     */
    public static void setMinUseTime(int aMinUseTime) {
        minUseTime = aMinUseTime;
    }

    /**
     * @return the maxUseTime
     */
    public static int getMaxUseTime() {
        return maxUseTime;
    }

    /**
     * @param aMaxUseTime the maxUseTime to set
     */
    public static void setMaxUseTime(int aMaxUseTime) {
        maxUseTime = aMaxUseTime;
    }

    /**
     * @return the seed
     */
    public static int getSeed() {
        return seed;
    }

    /**
     * @param aSeed the seed to set
     */
    public static void setSeed(int aSeed) {
        seed = aSeed;
    }
    
    public void requestInput(){
            
        Scanner scanner = new Scanner(System.in);
        System.out.println("Size of Main Memory");
        setMainMemSize(scanner.nextInt());
        System.out.println("Total Simulation Time");
        setTotalSimTime(scanner.nextInt());
        System.out.println("Min inter-arrival time for new requests");
        setMinArrivalTime(scanner.nextInt());
        System.out.println("Max inter-arrival time for new requests");
        setMaxArrivalTime(scanner.nextInt());
        System.out.println("Min memory block request size");
        setMinMemSize(scanner.nextInt());
        System.out.println("Max memory block request size");
        setMaxMemSize(scanner.nextInt());
        System.out.println("Min use time for memory block");
        setMinUseTime(scanner.nextInt());
        System.out.println("Max use time for memory block");
        setMaxUseTime(scanner.nextInt());
        System.out.println("Random number generator for seed");
        setSeed(scanner.nextInt());
    }
    
}
