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
}