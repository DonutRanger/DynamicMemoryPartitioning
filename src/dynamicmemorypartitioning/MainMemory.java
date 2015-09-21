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

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Pablo
 */
public class MainMemory {
    
    private int size;
    private LinkedList<MemoryBlock> list = new LinkedList<>();
    ListIterator<MemoryBlock> iterator = list.listIterator();
    
    public MainMemory(int size){
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
    
    public void addBlockFirstFit(int size, int requestedTime){
        boolean foundFit = false;
        int emptyPosition;
        int i = 0;
        while(iterator.hasNext() && !foundFit){
            if((size < list.get(i).getBlockSize()) && !list.get(i).isOccupied()){
                listShift(i);
                list.set(i+1, new MemoryBlock(list.get(i+1).getBlockSize()-size, 
                                         list.get(i).getRequestedTime()-requestedTime,
                                         false)
                );
                list.set(i, new MemoryBlock(size, requestedTime, true));
            }
            ++i;
        }
        
    }
    public void addBlockNextFit(int size){
        
    }
    
    public void addBlockBestFit(int size){
        
    }
    
    public void listShift(int i){
        for(int j = list.size()-1; j >= i; j--){
            list.add(list.get(j));
        }
    }
     
}
