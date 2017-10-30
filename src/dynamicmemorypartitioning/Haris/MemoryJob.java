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
public class MemoryJob {

    private int jobNum;
    private int arrivalTime;
    private int processTime;
    private int jobSize;
    
    public MemoryJob(){
    }
    
    public MemoryJob(int jobNum, int arrivalTime, int processTime, int jobSize){
        this.jobNum = jobNum;
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
        this.jobSize = jobSize;
    }
    
    //methods to store objects into the memory block list. since I can't figure out a way like c++;
    public void setJobNum(int jobNum) {
        this.jobNum=jobNum;
    }
    
    public int getJobNum() {
        return jobNum;
    }
    
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime=arrivalTime;
    }
    
    public int getArrivalTime() {
        return arrivalTime;
    }
    
    public void setProcessTime(int processTime) {
        this.processTime=processTime;
    }
    
    public int getProcessTime() {
        return processTime;
    }
    
    public void setJobSize(int jobSize) {
        this.jobSize=jobSize;
    }
    
    public int getJobSize() {
        return jobSize;
    }
    
    //
    //
    
    
    
     
    //
    // 
    
}
