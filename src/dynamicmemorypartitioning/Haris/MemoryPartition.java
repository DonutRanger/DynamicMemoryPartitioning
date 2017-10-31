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
    private int jobNum;
    
    public MemoryPartition() {
    }
    
    public MemoryPartition(int blockSize, boolean occupied){
        this.blockSize=blockSize;
        this.occupied=occupied;
    }
    
    public int getJobNum(){
        return this.jobNum;
    }

    public void setJobNum(int jobNum){
        this.jobNum = jobNum;
    }

    public void setOccupied(boolean occupied) { // Please revise the functions, they look the same
        this.occupied = occupied;
    }
    
    public boolean getOccupied(){ // Please revise the functions, they look the same
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