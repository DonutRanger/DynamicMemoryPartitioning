/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamicmemorypartitioning.Haris;

/**
 *
 * @Iman Haris Bin Hadi DonutRanger
 */
public class MemoryPartition {
    
    private int blockSize;
    private boolean occupied;
    private int fragmentation;
    private boolean memory_allocated;
    
    public MemoryPartition() {
    }
    
    public MemoryPartition(int blockSize, boolean occupied){
        this.blockSize=blockSize;
        this.occupied=occupied;
    }


    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    public boolean getOccupied(){
        return occupied;
    }
    
    public void SetMemory(int size) {
        this.blockSize = size;
    }
    
    public int getMemorySize() {
        return this.blockSize;
    }
    
    public boolean getMemoryAllocated() {
        return this.memory_allocated;
    }
    
    public void allocateMemory() {
        this.memory_allocated = true;
    }

    public void deallocateMemory() {
        this.memory_allocated = false;
    }

    public void fragmentationVal(int val) {
        this.fragmentation = val;
        
    }

    public int getFragmentationVal() {
        return this.fragmentation;
    }
}