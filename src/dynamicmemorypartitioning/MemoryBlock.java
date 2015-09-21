/* 
 * File:   MemoryBlock.java
 * Author: @Pablo A. Arias
 * Email: parias@aggies.ncat.edu
 * Course: Comp 755 - Advanced perating Systems: Dr. Bryant
 * Objective: Simulated implementation of Memory Allocation
 * Notes: 
 */
package dynamicmemorypartitioning;

/**
 *
 * @author Pablo
 */
public class MemoryBlock {

    private int blockSize;
    private int requestedTime;
    private boolean occupied;

    public MemoryBlock(int blockSize, int requestedTime, boolean occupied) {
        this.blockSize = blockSize;
        this.requestedTime = requestedTime;
        this.occupied = occupied;
    }

    /**
     * @return the blockSize
     */
    public int getBlockSize() {
        return blockSize;
    }

    /**
     * @param blockSize the blockSize to set
     */
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * @return the requestedTime
     */
    public int getRequestedTime() {
        return requestedTime;
    }

    /**
     * @param requestedTime the requestedTime to set
     */
    public void setRequestedTime(int requestedTime) {
        this.requestedTime = requestedTime;
    }

    /**
     * @return the occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * @param occupied the occupied to set
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

}
