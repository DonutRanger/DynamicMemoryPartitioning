/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicmemorypartitioning.Haris;

/**
 *
 * @author user
 */
public class MemoryManager {
    

    private int blockSize;
    private boolean occupied;
    private int fragmentation;
    private int memoryLocation;
 
    
    public MemoryManager() {
    }
    
    public MemoryManager(int blockSize, int memoryLocation, boolean occupied){
        this.blockSize=blockSize;
        this.memoryLocation=memoryLocation;
        this.occupied=occupied;

    }
    
    public void setAllValue(int blocksize, int memorylocation, boolean occupied){
        this.blockSize=blocksize;
        this.memoryLocation=memorylocation;
        this.occupied=occupied;
    }
    
    public int getMemoryLocation(){
        return this.memoryLocation;
    }

    public void setMemoryLocation(int memoryLocation){
        this.memoryLocation = memoryLocation;
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

    public void fragmentationVal(int val) {
        this.fragmentation = val;
        
    }

    public int getFragmentationVal() {
        return this.fragmentation;
    }
}

