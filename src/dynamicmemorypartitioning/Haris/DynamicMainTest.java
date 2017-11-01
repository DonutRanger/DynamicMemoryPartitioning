/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamicmemorypartitioning.Haris;
import java.io.*;
import java.util.*;
/**
 *
 * @Iman Haris Bin Hadi DonutRanger
 */
public class DynamicMainTest {
    static LinkedList<MemoryJob> Job = new LinkedList();
    static LinkedList<Dynamic> MemoryList = new LinkedList();
    //static LinkedList<Dynamic> newMemory = new LinkedList();
    
    
    public static void main(String[] args){
        DynamicMainTest testDynamic = new DynamicMainTest();
        testDynamic.readJob();
        //testDynamic.initialDyna();
        testDynamic.D_FirstFit(Job, MemoryList);
    }
    
    public int getEmpty(int empty){
        return empty;
    }
    
    
    public void D_FirstFit(LinkedList<MemoryJob> jobQueue, LinkedList<Dynamic> memoryList){
        
        LinkedList<Dynamic> obj = new LinkedList();
        int clock = 0;
        int minSize2 = 0, maxSize2 = 50000;
        //int i = 0;
        System.out.println(memoryList.get(1).hasemptyBlock());
        for(int n = 0; n < jobQueue.size(); n++) {
            
            if(memoryList.get(n).hasemptyBlock()) {
                obj.get(n).setJobNum(n);
                obj.get(n).setProcessTime(jobQueue.get(n).getProcessTime());
                obj.get(n).setMemorySize(jobQueue.get(n).getJobSize()); // unsure
                obj.get(n).setOccupyStatus(true);
                
                //need to implement frag to check
                for(int m = 0; m < obj.size(); m++){
                    if(obj.get(m).getJobNum() == 1){
                        obj.get(m).setJobSize(jobQueue.get(m).getJobSize());
                        obj.get(m).setStartBound(0);
                        int startBound = obj.get(m).getStartBound();
                        int memSize = obj.get(m).getJobSize();
                        obj.get(m).setEndBound(startBound + memSize);
                    }
                    
                    else{
                        
                    }
                }
            }
        }
        
        
        
        
        
        
        do {
            int minSize = 0;
            int maxSize = 50000;
            int remainSize;
            int empty;
            //check frag size
            for (int i = 0; i < jobQueue.size(); i++) {
                
                if (jobQueue.get(i).getJobNum() == 1){
                    memoryList.get(i).setJobNum(i);
                    memoryList.get(i).setJobSize(jobQueue.get(i).getJobSize());
                    memoryList.get(i).setStartBound(0);
                    int startBound = memoryList.get(i).getStartBound();
                    int memSize = memoryList.get(i).getJobSize();
                    memoryList.get(i).setEndBound(startBound + memSize);

                    remainSize = maxSize - memSize;
                    System.out.println(remainSize);
                    memoryList.get(i).setMemorySize(remainSize);
                    System.out.println(remainSize);
                    empty = maxSize - memoryList.get(i).getEndBound();
                    memoryList.get(i).setFrag(empty);
                }
                
                else{
                    empty = memoryList.get(i).getFrag();
                    if(jobQueue.size() < empty){
                        memoryList.get(i).setJobNum(jobQueue.get(i).getJobNum());
                        memoryList.get(i).setJobSize(jobQueue.get(i).getJobSize());
                        int n_startBound = memoryList.get(i - 1).getStartBound() + 1;
                        memoryList.get(i).setStartBound(n_startBound);
                        int startBound = memoryList.get(i).getStartBound();
                        int memSize = memoryList.get(i).getJobSize();
                        memoryList.get(i).setEndBound(startBound + memSize);
                    }
                }
            }
            clock++;
        } while (clock < 10);
        
        
        /*memoryList.get(i).setJobNum(i);
        memoryList.get(i).setMemoryUpBound(c_memoryQueue.get(i).getMemoryUpBound());
        memoryList.get(i).setMemoryLowBound(c_memoryQueue.get(i).getMemoryLowBound());
        memoryList.get(i).setMemoryStatus(true);
        System.out.println(memoryList.get(i).getMemoryLowBound());*/
        
        // Still need to fix this block
        /*for(int n = 0; n < jobQueue.size(); n++) {
            if(jobQueue.get(n).getArrivalTime() == 0) {
                MemoryList.add(new Dynamic());
                memoryList.get(n).setJobNum(n);
                memoryList.get(n).setMemoryUpBound(MemoryList.get(n).getMemoryUpBound());
                memoryList.get(n).setMemoryLowBound(MemoryList.get(n).getMemoryLowBound());
                memoryList.get(n).setMemoryStatus(true);
                
                System.out.println(memoryList.get(n).getMemoryLowBound());
                System.out.println(memoryList.get(n).getMemoryUpBound());
            
            }
            
            else{
                for(int m = 0 ; m < jobQueue.size(); m++) {
                    memoryList.get(m).setJobNum(m);
                    memoryList.get(m).setMemoryUpBound(memoryList.get(m).getMemoryLowBound());

                    int newBound = (memoryList.get(m).getMemoryLowBound()) + (memoryList.get(m).getMemorySize());
                    memoryList.get(m).setMemoryLowBound(newBound);
                    memoryList.get(m).setMemoryStatus(true);

                    System.out.println(memoryList.get(m).getMemoryLowBound());
                    System.out.println(memoryList.get(m).getMemoryUpBound());
                }
                //if(memoryList) if memory full then put to queue
            }
        }*/
    }
    
 
    /*public void initialDyna() {
        int memorySize = sumMemory();
        int memoryUpBound = 0;
        int memoryLowBound = memoryUpBound + memorySize;
        Memory.add(new Dynamic(memorySize, memoryUpBound, memoryLowBound));
    }*/
    
    // Just to read
    public static void skip(Scanner s,int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(s.hasNextLine())s.nextLine();
        }
    }
    
    public void readJob() {
        try {
            int temp1, temp2, temp3, temp4;
            Scanner reader = new Scanner(new File
("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/JoblistTest.txt"));
            while(reader.hasNextLine()) {
                skip(reader, 1);
                if(reader.hasNextInt()) {
                    temp1 = reader.nextInt();
                    temp2 = reader.nextInt();
                    temp3 = reader.nextInt();
                    temp4 = reader.nextInt();
                    System.out.print(temp1 + " ");
                    System.out.print(temp2 + " ");
                    System.out.print(temp3 + " ");
                    System.out.print(temp4 + "\n");
                    Job.add(new MemoryJob(temp1, temp2, temp3, temp4, false, false));
                }
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    
    public int sumMemory() {
        int sum = 0;
        try {
            int temp1;
            Scanner reader = new Scanner(new File
        ("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/MemoryListTest.txt"));
            while(reader.hasNextLine()) {
                skip(reader, 1);
                if(reader.hasNextInt()) {
                    temp1 = reader.nextInt();
                    sum = sum + temp1;
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.print(e);
        }
        return sum;
    }   
}