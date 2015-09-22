/* 
 * File:   DynamicMemoryPartitioning.java
 * Author: @Pablo A. Arias
 * Email: parias@aggies.ncat.edu
 * Course: Comp 755 - Advanced perating Systems: Dr. Bryant
 * Objective: Simulated implementation of Memory Allocation
 * Notes: 
 */
package dynamicmemorypartitioning;

import java.util.Random;

/**
 *
 * @author Pablo
 */
public class DynamicMemoryPartitioning {

    private static int mainMemSize, totalSimTime, minArrivalTime, maxArrivalTime,
            minMemSize, maxMemSize, minUseTime, maxUseTime, seed;
    private static Random random;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DynamicMemoryPartitioning d = new DynamicMemoryPartitioning();
        d.requestInput(args);

        MainMemory mainMemory = new MainMemory(mainMemSize);
        MemoryRequestGenerator generatorSeed = new MemoryRequestGenerator(
                minMemSize, maxMemSize,
                minUseTime, maxUseTime,
                seed
        );
        random = new Random(seed);

        int interArrivalTime = randInt(minArrivalTime, maxArrivalTime);
        int throughPut = 0;
        int queueTime = 0;
        int blocksAdded = 0;
        while (totalSimTime > 0) {
            if (!mainMemory.getQueue().isEmpty()) {
                queueTime++;
            }
            if (interArrivalTime <= 0) {
                MemoryBlock memBlock = generatorSeed.createBlock();
                mainMemory.addBlockFirstFit(memBlock);
                blocksAdded++;
                interArrivalTime = randInt(minArrivalTime, maxArrivalTime);
            }

            interArrivalTime--;
            totalSimTime--;
            int throughput = mainMemory.unAllocate();
            throughPut += throughput;
        }

        System.out.println("Throughput: " + throughPut);
        System.out.println("Average Wait Time: " + (queueTime / (blocksAdded * 1.0)));

    }

    /*
     * Creates random number between two Integers
     */
    public static int randInt(int min, int max) {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
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

    public void requestInput(String[] args) {

        setMainMemSize(Integer.parseInt(args[0]));
        setTotalSimTime(Integer.parseInt(args[1]));
        setMinArrivalTime(Integer.parseInt(args[2]));
        setMaxArrivalTime(Integer.parseInt(args[3]));
        setMinMemSize(Integer.parseInt(args[4]));
        setMaxMemSize(Integer.parseInt(args[5]));
        setMinUseTime(Integer.parseInt(args[6]));
        setMaxUseTime(Integer.parseInt(args[7]));
        setSeed(Integer.parseInt(args[8]));

    }

}
