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
public class Dynamic {
    private int jobNum;
    private int processTime;
    private int jobSize;
    private int memorySize;
    private int memoryUpBound;
    private int memoryLowBound;
    
    public Dynamic (int memorySize, int memoryUpBound, int memoryLowBound) {
        this.memorySize = memorySize;
        this.memoryUpBound = memoryUpBound;
        this.memoryLowBound = memoryLowBound;
    }
    
    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }
    
    public int getJobNum(){
        return jobNum;
    }
    
    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }
    
    public int getProcessTime(){
        return processTime;
    }
    
    public void setJobSize(int jobNum) {
        this.jobSize = jobSize;
    }
    
    public int getJobSize() {
        return jobSize;
    }
    
    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }
    
    public int getMemorySize(){
        return memorySize;
    }
    
    public void setMemoryUpBound(int memoryUpBound) {
        this.memoryUpBound = memoryUpBound;
    }
    
    public int getMemoryUpBound() {
        return memoryUpBound;
    }
    
    public void setMemoryLowBound(int memoryLowBound) {
        this.memoryLowBound = memoryLowBound;
    }
    
}
