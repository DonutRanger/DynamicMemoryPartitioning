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
        MemoryBlock block = new MemoryBlock(size, Integer.MAX_VALUE, false);
        list.add(block);
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

    public void addBlockFirstFit(MemoryBlock block) {

        for (int i = nextLocation; i < list.size(); i++) {
            if (queue.isEmpty()) {
                if (!list.get(i).isOccupied() && (list.get(i).getBlockSize() > block.getBlockSize())) {
                    MemoryBlock remainingBlock = new MemoryBlock(list.get(i).getBlockSize() - block.getBlockSize(),
                            list.get(i).getRequestedTime(),
                            false);
                    list.add(i, block);
                    list.set(i + 1, remainingBlock);
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

        if (checkFit(block)) {
            if (queue.isEmpty()) {
                int difference = Integer.MAX_VALUE;
                int location = -1;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isOccupied() == false) {
                        int localDiff = list.get(i).getBlockSize() - block.getBlockSize();
                        if ((localDiff >= 0) && (abs(localDiff) < difference)) {
                            difference = abs(localDiff);
                            location = i;
                        }
                    }
                }
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

}
