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
    private int startBound;
    private int endBound;
    private int frag;
    private boolean occupyStatus;
    
    
    public Dynamic (int memorySize, int startBound, int endBound, boolean occupyStatus) {
        //this.jobNum = jobNum;
        //this.processTime = processTime;
        //this.jobSize = jobSize;
        this.memorySize = memorySize;
        this.startBound = startBound;
        this.endBound = endBound;
        this.occupyStatus = occupyStatus;
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
    
    public void setStartBound(int startBound) {
        this.startBound = startBound;
    }
    
    public int getStartBound() {
        return startBound;
    }
    
    public void setEndBound(int endBound) {
        this.endBound = endBound;
    }
    
    public int getEndBound() {
        return endBound;
    }
    
    public boolean getOccupyStatus(){
        return occupyStatus;
    }
    
    public void setOccupyStatus(boolean occupyStatus) {
        this.occupyStatus = occupyStatus;
    }
    
    public int getFrag() {
        return frag;
    }
    
    public void setFrag(int frag) {
        this.frag = frag;
    }
    
}
