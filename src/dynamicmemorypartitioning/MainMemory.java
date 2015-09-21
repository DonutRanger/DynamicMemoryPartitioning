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
import java.util.ListIterator;
import java.util.Queue;

/**
 *
 * @author Pablo
 */
public class MainMemory {

    private int size;
    private LinkedList<MemoryBlock> list = new LinkedList<>();
    ListIterator<MemoryBlock> iterator = list.listIterator();
    Queue queue = new LinkedList();
    private int nextLocation = 0; //used for NextFit allocation

    public MainMemory(int size) {

        //Main memory represented as MemBlock with MAX_VALUE integer time
        MemoryBlock block = new MemoryBlock(size, Integer.MAX_VALUE, false);
        list.add(block);
    }

    public void addBlockFirstFit(MemoryBlock block) {

        outerLoop:
        for (int i = nextLocation; i < list.size(); i++) {
            if (queue.isEmpty()) {
                if (!list.get(i).isOccupied() && (list.get(i).getBlockSize() > block.getBlockSize())) {
                    MemoryBlock remainingBlock = new MemoryBlock(list.get(i).getBlockSize() - block.getBlockSize(),
                            list.get(i).getRequestedTime(),
                            false);
                    list.add(i, block);
                    list.set(i + 1, remainingBlock);
                    break outerLoop;
                }
            } else {
                queue.add(block);
            }
        }

    }

    public void addBlockNextFit(MemoryBlock block) {

        boolean foundFit = false;
        outerLoop:
        for (int i = nextLocation; i < list.size(); i++) {
            if (queue.isEmpty()) {
                if (!list.get(i).isOccupied() && block.getBlockSize() < list.get(i).getBlockSize()) {
                    nextLocation = i;
                    MemoryBlock remainingBlock = new MemoryBlock(list.get(i).getBlockSize() - block.getBlockSize(),
                            list.get(i).getRequestedTime(),
                            false);
                    list.add(i, block);
                    list.set(i + 1, remainingBlock);
                    foundFit = true;
                    break outerLoop;
                }
            } else {
                queue.add(block);
            }
        }
        if (foundFit == false) {
            otherLoop:
            for (int i = 0; i < nextLocation; i++) {
                if (queue.isEmpty()) {
                    if (!list.get(i).isOccupied() && block.getBlockSize() < list.get(i).getBlockSize()) {
                        nextLocation = i;
                        MemoryBlock remainingBlock = new MemoryBlock(list.get(i).getBlockSize() - block.getBlockSize(),
                                list.get(i).getRequestedTime(),
                                false);
                        list.add(i, block);
                        list.set(i + 1, remainingBlock);
                        break otherLoop;
                    } else {
                        queue.add(block);
                    }
                }
            }
        }
    }

    public void addBlockBestFit(MemoryBlock block) {

        //checks if fits in any memory slot. If not, put in queue
        if (checkFit(block)) {

            //if queue is empty, put into list, else add to queue
            if (queue.isEmpty()) {
                int difference = Integer.MAX_VALUE;
                int location = -1;

                //checks best fit location. Sets location to location.
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isOccupied() == false) {
                        int localDiff = list.get(i).getBlockSize() - block.getBlockSize();
                        if ((localDiff >= 0) && (abs(localDiff) < difference)) {
                            difference = abs(localDiff);
                            location = i;
                        }
                    }
                }

                //Creates new remaining Mem, allocates MemBlock according to location.
                MemoryBlock remainingBlock = new MemoryBlock(list.get(location).getBlockSize() - block.getBlockSize(),
                        list.get(location).getRequestedTime(),
                        false);
                list.add(location, block);
                list.set(location + 1, remainingBlock);
            } else {
                queue.add(block);
            }
        } else {
            queue.add(block);
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

}
