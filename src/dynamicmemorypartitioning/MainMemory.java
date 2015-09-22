/* 
 * File:   MainMemory.java
 * Author: @Pablo A. Arias
 * Email: parias@aggies.ncat.edu
 * Course: Comp 755 - Advanced perating Systems: Dr. Bryant
 * Objective: Simulated implementation of Memory Allocation
 * Notes: Implementation of Memory Allocation Algorithms
 *          Best Fit  -  methodName()
 *          First Fit -  methodName()
 *          Next Fit  -  methodName()
 */
package dynamicmemorypartitioning;

import static java.lang.Math.abs;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Pablo
 */
public class MainMemory {

    private int size;
    private LinkedList<MemoryBlock> list = new LinkedList<>();
    private Queue<MemoryBlock> queue = new LinkedList();
    private int nextLocation = 0; //used for NextFit allocation
    private int waitTime = 0;

    public MainMemory(int size) {

        //Main memory represented as MemBlock with MAX_VALUE integer time
        MemoryBlock block = new MemoryBlock(size, 0, false);
        this.size = size;
        list.add(block);
    }

    public void addBlockFirstFit(MemoryBlock block) {

        getQueue().add(block);
        MemoryBlock blockQueue = getQueue().element();
        if (checkFit(blockQueue)) {
            outerLoop:
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).isOccupied() && (list.get(i).getBlockSize() >= blockQueue.getBlockSize())) {
                    MemoryBlock remainingBlock = new MemoryBlock(list.get(i).getBlockSize() - blockQueue.getBlockSize(),
                            list.get(i).getRequestedTime(),
                            false);
                    list.add(i, blockQueue);
                    list.set(i + 1, remainingBlock);
                    getQueue().remove();
                    break outerLoop;
                }
            }
        }
    }

    public void addBlockNextFit(MemoryBlock block) {

        getQueue().add(block);
        if (getQueue().size() > 1) {
            setWaitTime(getWaitTime() + 1);
        }
        MemoryBlock blockQueue = getQueue().element();
        if (checkFit(blockQueue)) {
            boolean foundFit = false;
            outerLoop:
            for (int i = nextLocation; i < list.size(); i++) {
                if (!list.get(i).isOccupied() && (blockQueue.getBlockSize() <= list.get(i).getBlockSize())) {
                    nextLocation = i;
                    MemoryBlock remainingBlock = new MemoryBlock(list.get(i).getBlockSize() - blockQueue.getBlockSize(),
                            list.get(i).getRequestedTime(),
                            false);
                    list.add(i, blockQueue);
                    list.set(i + 1, remainingBlock);
                    getQueue().remove();
                    foundFit = true;
                    break outerLoop;
                }
            }
            if (foundFit == false) {
                otherLoop:
                for (int i = 0; i < nextLocation; i++) {
                    if (!list.get(i).isOccupied() && (blockQueue.getBlockSize() <= list.get(i).getBlockSize())) {
                        nextLocation = i;
                        MemoryBlock remainingBlock = new MemoryBlock(list.get(i).getBlockSize() - blockQueue.getBlockSize(),
                                list.get(i).getRequestedTime(),
                                false);
                        list.add(i, block);
                        list.set(i + 1, remainingBlock);
                        getQueue().remove();
                        break otherLoop;
                    }
                }
            }
        }
    }

    public void addBlockBestFit(MemoryBlock block) {

        //checks if fits in any memory slot. If not, put in queue
        getQueue().add(block);
        MemoryBlock blockQueue = getQueue().element();
        if (checkFit(blockQueue)) {

            //if queue is empty, put into list, else add to queue
            int difference = Integer.MAX_VALUE;
            int location = -1;

            //checks best fit location. Sets location to location.
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isOccupied() == false) {
                    int localDiff = list.get(i).getBlockSize() - blockQueue.getBlockSize();
                    if ((localDiff >= 0) && (abs(localDiff) < difference)) {
                        difference = abs(localDiff);
                        location = i;
                    }
                }
            }

            //Creates new remaining Mem, allocates MemBlock according to location.
            MemoryBlock remainingBlock = new MemoryBlock(list.get(location).getBlockSize() - blockQueue.getBlockSize(),
                    list.get(location).getRequestedTime(),
                    false);
            list.add(location, block);
            list.set(location + 1, remainingBlock);
            getQueue().remove();
        }
    }

    public int unAllocate() {
        int finished = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getRequestedTime() <= 0) {
                list.get(i).setOccupied(false);
                finished++;
                mergeBlocks();
            } else {
                list.get(i).setRequestedTime(list.get(i).getRequestedTime() - 1);
            }
        }
        return finished;
    }

    public void mergeBlocks() {
        for (int i = 0; i < list.size() - 1; i++) {
            if (!list.get(i).isOccupied() && !list.get(i + 1).isOccupied()) {
                MemoryBlock mergeMem = new MemoryBlock(list.get(i).getBlockSize() + list.get(i + 1).getBlockSize(),
                        0, false);
                list.set(i, mergeMem);
                list.remove(i + 1);
            }
        }
    }

    /*
     * Checks if MemBlock fits in Memory. Used in Fit Algorithms.
     * If doesnt, put block into Queue.
     */
    public boolean checkFit(MemoryBlock block) {
        boolean fit = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isOccupied() == false
                    && block.getBlockSize() < list.get(i).getBlockSize()) {
                fit = true;
            }
        }
        return fit;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the list
     */
    public LinkedList getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(LinkedList list) {
        this.list = list;
    }

    /**
     * @return the queue
     */
    public Queue<MemoryBlock> getQueue() {
        return queue;
    }

    /**
     * @param queue the queue to set
     */
    public void setQueue(Queue<MemoryBlock> queue) {
        this.queue = queue;
    }

    /**
     * @return the waitTime
     */
    public int getWaitTime() {
        return waitTime;
    }

    /**
     * @param waitTime the waitTime to set
     */
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

}
