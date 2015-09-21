/* 
 * File:   MemoryRequestGenerato.java
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
public class MemoryRequestGenerator {

    private int minSize, maxSize, minTime, maxTime;
    long seed;
    private static int multiplier = 5;
    private Random random;

    public MemoryRequestGenerator(int minSize, int maxSize, int minTime, int maxTime) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.minTime = minTime;
        this.maxTime = maxTime;
        random = new Random();
    }

    public MemoryRequestGenerator(int minSize, int maxSize, int minTime,
            int maxTime, long seed) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.seed = seed;
        this.random = new Random(seed);
    }

    public MemoryBlock createBlock() {

        int blockSize, requestedTime, randomMemoryValue;
        boolean occupied = false;

        int memoryRequest = random.nextInt(maxSize) * 5 + minSize;
        requestedTime = random.nextInt(randInt(minTime, maxTime));
        
        MemoryBlock memBlock = new MemoryBlock(memoryRequest, requestedTime, occupied);
        return memBlock;
    }

    /**
     * @return the minSize
     */
    public int getMinSize() {
        return minSize;
    }

    /**
     * @param minSize the minSize to set
     */
    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    /**
     * @return the maxSize
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * @param maxSize the maxSize to set
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * @return the minTime
     */
    public int getMinTime() {
        return minTime;
    }

    /**
     * @param minTime the minTime to set
     */
    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    /**
     * @return the maxTime
     */
    public int getMaxTime() {
        return maxTime;
    }

    /**
     * @param maxTime the maxTime to set
     */
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * @return the seed
     */
    public long getSeed() {
        return seed;
    }

    /**
     * @param seed the seed to set
     */
    public void setSeed(long seed) {
        this.seed = seed;
    }

    /**
     * @return the random
     */
    public Random getRandom() {
        return random;
    }

    /**
     * @param random the random to set
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * @return the multiplier
     */
    public static int getMultiplier() {
        return multiplier;
    }

    /**
     * @param aMultiplier the multiplier to set
     */
    public static void setMultiplier(int aMultiplier) {
        multiplier = aMultiplier;
    }

    public int randInt(int min, int max) {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
