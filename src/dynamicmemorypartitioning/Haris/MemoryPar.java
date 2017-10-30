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
public class MemoryPar {

    private int jobNum;
    private int arrivalTime;
    private int processTime;
    private int blockSize;
    
    public MemoryPar(int jobnum, int arrivalTime, int processTime, int blocksize){
        this.jobNum = jobNum;
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
        this.blockSize = blockSize;
    }
    
    //methods to store objects into the memory block list. since I can't figure out a way like c++;
    public int getJobNum() {
        return jobNum;
    }
    
    public int getArrivalTime() {
        return arrivalTime;
    }
    
    public int getProcessTime() {
        return processTime;
    }
    
    public int getBlockSize() {
        return blockSize;
    }
    
    //
    //
    
    
    
     
    //
    // 
    
}
