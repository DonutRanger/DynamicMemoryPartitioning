/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicmemorypartitioning.Haris;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @Iman Haris Bin Hadi DonutRanger
 */

public class DR_Test {
    
    static LinkedList<MemoryJob> Job = new LinkedList();
    static LinkedList<MemoryJob> BFJob = new LinkedList();
    static LinkedList<MemoryPartition> Partition = new LinkedList();
    static LinkedList<MemoryPartition> BFPartition = new LinkedList();
    static LinkedList<MemoryJob> WaitQueue = new LinkedList();
    static LinkedList<MemoryJob> BFWaitQueue = new LinkedList();
    static LinkedList<MemoryJob> DJob = new LinkedList();
    static LinkedList<MemoryPartition> DPartition = new LinkedList();
    static LinkedList<MemoryJob> BFDJob = new LinkedList();
    static LinkedList<MemoryPartition> BFDPartition = new LinkedList(); 
    static ArrayList<String> loadArray = new ArrayList(); 
    static LinkedList<MemoryJob> WFDJob = new LinkedList();
    static LinkedList<MemoryPartition> WFDPartition = new LinkedList();
    
    String readTxtFile;
    MemoryJob load = new MemoryJob();
    
    static LinkedList<MemoryManager> mManager = new LinkedList();
    
    public static void main(String[] args) throws IOException{
        DR_Test testRun = new DR_Test();

        System.out.println("FIRST FIT ALGORITHM");
        testRun.readJob(Job);
        testRun.readMemory(Partition);
        testRun.FirstFitAlgo(Job, Partition, WaitQueue);
        
        
        System.out.println("BEST FIT ALGORITHM");
        testRun.readJob(BFJob);
        testRun.readMemory(BFPartition);
        testRun.changeArrangement(BFPartition);
        WaitQueue.clear();
        testRun.BestFitAlgo(BFJob, BFPartition, BFWaitQueue);
        

        testRun.readJob(DJob);
        WaitQueue.clear();
        testRun.dynamicFirstFit(DJob, mManager,DPartition, WaitQueue);
        
        testRun.readJob(BFDJob);
        testRun.changeBFArrangement(BFDJob);
        WaitQueue.clear();
        testRun.dynamicFirstFit(BFDJob, mManager, BFDPartition, WaitQueue);
        
        testRun.readJob(BFDJob);
        testRun.changeWFArrangement(BFDJob);
        WaitQueue.clear();
        testRun.dynamicFirstFit(BFDJob, mManager, BFDPartition, WaitQueue);
        
        testRun.readJob(WFDJob);
        testRun.changeWFArrangement(WFDJob);
        WaitQueue.clear();
        testRun.dynamicFirstFit(WFDJob, mManager, WFDPartition, WaitQueue);
        
    }
    public static void skip(Scanner s,int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(s.hasNextLine())s.nextLine();
        }
    }
    
    public void FirstFitAlgo(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> memorypartition, LinkedList<MemoryJob> queue){
        int clock = 0;
        boolean allJobStatus = false;
        boolean checkEvent = false;
        double numJobDone = 0;
        int totalFrag = 0;
        
        do{
            
            System.out.println("\n\n-------------------------------------------------");
            System.out.println("Time cycle: " + clock);
            System.out.println("-------------------------------------------------");
            
            for(int i = 0;i<memorypartition.size();i++){
                if(memorypartition.get(i).getOccupied()){
                    int currentJobNum = memorypartition.get(i).getJobNum();
                    int currentProcessTime = job.get(currentJobNum).getProcessTime();
                    int newProcessTime = --currentProcessTime;
                    job.get(currentJobNum).setProcessTime(newProcessTime);
                    if(job.get(currentJobNum).getProcessTime() == 0){
                        memorypartition.get(i).setOccupied(false);
                        job.get(currentJobNum).setJobDone(true);
                        job.get(currentJobNum).setEndTime(clock);
                        System.out.println("-------------------------------------------------");
                        System.out.println("Job Done: " + job.get(currentJobNum).getJobNum());
                        System.out.println("-------------------------------------------------");
                    }
                }
            }
            
            // Queue job list
            if(!queue.isEmpty()){
                if(checkEvent){
                    System.out.println("-------------------------------------------------");
                    System.out.println("Updated Queue: ");

                    for(int q = 0;q<queue.size();q++){
                        System.out.println("Job " + queue.get(q).getJobNum() + " with size " + queue.get(q).getJobSize());
                    }
                    System.out.println("-------------------------------------------------");
                }
                for(int queueLine = 0;queueLine<queue.size();queueLine++){
    
                    for(int k = 0; k < memorypartition.size();k++){
                        
                        if( queue.get(queueLine).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                            
                            int lineNum = 0;
                            int currentJobNum = queue.get(queueLine).getJobNum();
                            
                            for(int t = 1; t<job.size();t++){
                                if(currentJobNum == job.get(t).getJobNum()){
                                    lineNum = t;
                                }
                            }
                            job.get(lineNum).setProcessStatus(true);
                            memorypartition.get(k).setJobNum(lineNum);
                            memorypartition.get(k).setOccupied(true);
                            memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(lineNum).getJobSize());
                            totalFrag += memorypartition.get(k).getFragmentationVal();
                            
                            System.out.println("-------------------------------------------------");
                            System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                            System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                            System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                            System.out.println("-------------------------------------------------");

                            queue.remove(queueLine);
                            break;
                        }
                    }
                }
            }
            checkEvent = false;
            // loaded Job list
            for(int jobLine = 0;jobLine<job.size();jobLine++){
                
                if(job.get(jobLine).getArrivalTime()==clock && !job.get(jobLine).getProcessStatus() && !job.get(jobLine).getJobDone()){
                  
                  System.out.println("Arrival Job: " + job.get(jobLine).getJobNum());
                    
                  for(int k = 0;k<memorypartition.size();k++){ 

                    if(job.get(jobLine).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                        checkEvent = true;
                        job.get(jobLine).setProcessStatus(true);
                        memorypartition.get(k).setJobNum(jobLine);
                        memorypartition.get(k).setOccupied(true);
                        memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(jobLine).getJobSize());
                        totalFrag += memorypartition.get(k).getFragmentationVal();
                        
                        System.out.println("-------------------------------------------------");
                        System.out.println("Job Processing: " + job.get(jobLine).getJobNum());
                        System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                        System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                        System.out.println("-------------------------------------------------");

                        break;
                    }

                  }

                  if(!job.get(jobLine).getProcessStatus()){
                      checkEvent = true;
                      System.out.println("-------------------------------------------------");
                      System.out.println("Job not Fit: " + job.get(jobLine).getJobNum());
                      queue.addLast(job.get(jobLine));
                      System.out.println("-------------------------------------------------");
                  
                  }

                }
            }

            for(int i = 0; i< job.size(); i++){
                
                if(!job.get(i).getJobDone()){
                   break;
                }
                
            }
            
            clock++;
            
            if(clock > 100){
                break;
            }
            
        }while(!allJobStatus);
        
        for(int i = 0; i<job.size(); i++){
            if(!job.get(i).getJobDone()){
                System.out.println("-------------------------------------------------");
                System.out.println("Job " + job.get(i).getJobNum() + ": Permanently Not Done.");
                System.out.println("-------------------------------------------------");
            } else
                ++numJobDone;
        }
        
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(3);
        
        System.out.println("Total Internal Fragmentation: " + totalFrag);
        System.out.println("Average Internal Fragmentation: " + totalFrag/50000);
        System.out.println("Total Jobs Completed: " + numJobDone);
        System.out.println("Total Time Unit: " + endTime(job));
        System.out.println("Partition Under Used: " );
        System.out.println("Partition Normal Used: ");
        System.out.println("Partition Heavily Used: ");
        System.out.println("Average Waiting Time: ");
        System.out.println("Max Waiting Time: ");
        System.out.println("Min Waiting Time: ");
        System.out.println("Throughput: " + format.format(numJobDone/endTime(job)));
        System.out.println("Average Waiting Length: ");
        System.out.println("Max Waiting Length: ");
        System.out.println("Min Waiting Length: ");
        
    }
    
    public void BestFitAlgo(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> memorypartition, LinkedList<MemoryJob> queue){
        int clock = 0;
        boolean allJobStatus = false;
        double numJobDone = 0;
        boolean checkEvent = false;

        for(int outerloop = 0; outerloop < 9; outerloop++){
            for(int innerloop = 0; innerloop < 9; innerloop++){
                if(memorypartition.get(innerloop).getMemorySize() > memorypartition.get(innerloop + 1).getMemorySize()){
                    Collections.swap(memorypartition, innerloop, innerloop+1);
                }
            
            }
        }
        
        do{
            
            System.out.println("-------------------------------------------------");
            System.out.println("Time cycle: " + clock);
            System.out.println("-------------------------------------------------");
            
            for(int i = 0;i<memorypartition.size();i++){
                if(memorypartition.get(i).getOccupied()){
                    int currentJobNum = memorypartition.get(i).getJobNum();
                    int currentProcessTime = job.get(currentJobNum).getProcessTime();
                    int newProcessTime = --currentProcessTime;
                    job.get(currentJobNum).setProcessTime(newProcessTime);
                    if(job.get(currentJobNum).getProcessTime() == 0){
                        memorypartition.get(i).setOccupied(false);
                        job.get(currentJobNum).setJobDone(true);
                        job.get(currentJobNum).setEndTime(clock);
                        System.out.println("-------------------------------------------------");
                        System.out.println("Job Done: " + job.get(currentJobNum).getJobNum());
                        System.out.println("-------------------------------------------------");
                    }
                }
            }
            
            // Queue job list
            if(!queue.isEmpty()){
                
                if(checkEvent){
                    System.out.println("-------------------------------------------------");
                    System.out.println("Updated Queue: ");

                    for(int q = 0;q<queue.size();q++){
                        System.out.println("Job " + queue.get(q).getJobNum() + " with size " + queue.get(q).getJobSize());
                    }
                    System.out.println("-------------------------------------------------");
                }
                
                for(int queueLine = 0;queueLine<queue.size();queueLine++){
    
                    for(int k = 0; k < memorypartition.size();k++){
                        
                        if( queue.get(queueLine).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                            
                            int lineNum = 0;
                            int currentJobNum = queue.get(queueLine).getJobNum();
                            
                            for(int t = 1; t<job.size();t++){
                                if(currentJobNum == job.get(t).getJobNum()){
                                    lineNum = t;
                                }
                            }
                            job.get(lineNum).setProcessStatus(true);
                            memorypartition.get(k).setJobNum(lineNum);
                            memorypartition.get(k).setOccupied(true);
                            memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(lineNum).getJobSize());
                            
                            System.out.println("-------------------------------------------------");
                            System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                            System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                            System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                            System.out.println("-------------------------------------------------");

                            queue.remove(queueLine);
                            break;
                        }
                    }
                }
            }
            
            checkEvent = false;
            // loaded Job list
            for(int i = 0;i<job.size();i++){
                   
                if(job.get(i).getArrivalTime()==clock && !job.get(i).getProcessStatus() && !job.get(i).getJobDone()){
                  System.out.println(job.size());
                  System.out.println(i);
                  System.out.println("Arrival Job: " + job.get(i).getJobNum());
                    
                  for(int k = 0;k<memorypartition.size();k++){ 

                    if(job.get(i).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                        checkEvent = true;
                        job.get(i).setProcessStatus(true);
                        memorypartition.get(k).setJobNum(i);
                        memorypartition.get(k).setOccupied(true);
                        memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(i).getJobSize());
                        
                        System.out.println("-------------------------------------------------");
                        System.out.println("Job Processing: " + job.get(i).getJobNum());
                        System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                        System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                        System.out.println("-------------------------------------------------");

                        break;
                    }

                  }

                  if(!job.get(i).getProcessStatus()){
                      checkEvent = true;
                      System.out.println("-------------------------------------------------");
                      System.out.println("Job not Fit: "+ job.get(i).getJobNum());
                      queue.addLast(job.get(i));
                      System.out.println("-------------------------------------------------");
                  
                  }

                }
            }

            for(int i = 0; i< job.size(); i++){
                
                if(!job.get(i).getJobDone()){
                   break;
                }
                
            }
            
            clock++;
            
            if(clock > 100){
                break;
            }
            
        }while(!allJobStatus);
        
        for(int i = 0; i<job.size(); i++){
            if(!job.get(i).getJobDone()){
                System.out.println("-------------------------------------------------");
                System.out.println("Job " + job.get(i).getJobNum() + ": Permanently Not Done.");
                System.out.println("-------------------------------------------------");
            } else
                ++numJobDone;
        }
    
        
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(3);
        
        System.out.println("Total Internal Fragmentation: ");
        System.out.println("Average Internal Fragmentation: ");
        System.out.println("Total Jobs Completed: " + numJobDone);
        System.out.println("Total Time Unit: " + endTime(job));
        System.out.println("Partition Under Used: ");
        System.out.println("Partition Normal Used: ");
        System.out.println("Partition Heavily Used: ");
        System.out.println("Average Waiting Time: ");
        System.out.println("Max Waiting Time: ");
        System.out.println("Min Waiting Time: ");
        System.out.println("Throughput: " + format.format(numJobDone/endTime(job)));
        System.out.println("Average Waiting Length: ");
        System.out.println("Max Waiting Length: ");
        System.out.println("Min Waiting Length: ");
        
    }
        
    public int endTime(LinkedList<MemoryJob> job){

        int endTime = job.get(0).getEndTime();
        for(int h = 0; h < 39; h++){

            if(job.get(h).getEndTime() > job.get(h+1).getEndTime()){
                if(job.get(h).getEndTime() > endTime){
                    endTime = job.get(h).getEndTime();
                }
            } else if(job.get(h+1).getEndTime() > job.get(h).getEndTime()){
                if(job.get(h+1).getEndTime() > endTime){
                    endTime = job.get(h+1).getEndTime();
                }
            }  
        }
        
        return endTime;
    }
    
    public void changeArrangement(LinkedList<MemoryPartition> memorypartition){
        
        for(int outerloop = 0; outerloop < 9; outerloop++){
            for(int innerloop = 0; innerloop < 9; innerloop++){
                if(memorypartition.get(innerloop).getMemorySize() > memorypartition.get(innerloop + 1).getMemorySize()){
                    Collections.swap(memorypartition, innerloop, innerloop+1);
                }
            
            }
        }
        
    }
    
    public void dynamicFirstFit(LinkedList<MemoryJob> job, LinkedList<MemoryManager> memorymanager, LinkedList<MemoryPartition> partition, LinkedList<MemoryJob> queue){
        
        int clock = 0;
        boolean allJobStatus = false;
        int freeMemory = 50000; //the algorithms need access to free memory space available to control loops
        int totalMemoryBlocks;
        int totalFrag=0;
        
        partition.add(0,new MemoryPartition(freeMemory, false)); //creates a node for all free memory
        //dynamicpartition.add
        
        while(true){
            
            System.out.println("-------------------------------------------------");
            System.out.println("Time cycle: " + clock);
            System.out.println("-------------------------------------------------");
            
            for (int i = 0; i < partition.size(); i++) {
               
                if (partition.get(i).getOccupied()) {
                    
                    int lineNum = 0;
                    int currentJobNum = partition.get(i).getJobNum();
                    for(int t = 1; t<job.size();t++){
                    if(currentJobNum == job.get(t).getJobNum()){
                          lineNum = t;
                        }
                    }                    
                    int currentProcessTime = job.get(lineNum).getProcessTime();
                    int newProcessTime = --currentProcessTime;
                    job.get(lineNum).setProcessTime(newProcessTime);

                    if (job.get(lineNum).getProcessTime() == 0) {
                        partition.get(i).setOccupied(false);
                        job.get(lineNum).setJobDone(true);
                        job.get(lineNum).setEndTime(clock);
                        System.out.println("-------------------------------------------------");
                        System.out.println("Job Done: " + job.get(i).getJobNum());
                        System.out.println("-------------------------------------------------");
                        memorymanager.get(i).setAllValue(job.get(lineNum).getJobSize(), i, false);
                    }
                }
            }
            
            for(int i=0;i<(partition.size()-1);i++){
                if(!partition.get(i).getOccupied()&&!partition.get(i+1).getOccupied()){
                                        
                    partition.get(i+1).SetMemory(partition.get(i).getMemorySize()+partition.get(i).getMemorySize());
                    partition.remove(i);
                    --i;
                }
            }
            
            memorymanager.clear();
            
            for(int k = 0; k < partition.size(); k++){
                memorymanager.addLast(new MemoryManager(partition.get(k).getMemorySize(), k, partition.get(k).getOccupied()));
            }

            if(!queue.isEmpty()){
                int q=0;
                //if(checkEvent){
                    System.out.println("-------------------------------------------------");
                    System.out.println("Updated Queue: ");

                    for(int t = 0;t<queue.size();t++){
                        System.out.println("Job " + queue.get(t).getJobNum() + " with size " + queue.get(t).getJobSize());
                    }
                    System.out.println("-------------------------------------------------");
                //}
                for(int queueLine = 0;queueLine<queue.size();queueLine++){
    
                    if (queue.get(queueLine).getJobSize() <= freeMemory) {

                        if (!memorymanager.isEmpty()) {

                            for (q = 0; q < memorymanager.size()-1; q++) {

                                if (!memorymanager.get(q).getOccupied() && queue.get(queueLine).getJobSize() <= memorymanager.get(q).getMemorySize()) {

                                    break;
                                }

                            }

                            if (queue.get(queueLine).getJobSize() == memorymanager.get(q).getMemorySize()) {
                                
                                int lineNum = 0;
                                int currentJobNum = queue.get(queueLine).getJobNum();
                            
                                for(int t = 1; t<job.size();t++){
                                    if(currentJobNum == job.get(t).getJobNum()){
                                    lineNum = t;
                                    }
                                }
                                
                                job.get(lineNum).setProcessStatus(true);
                                
                                partition.get(q).setAllValue(queue.get(queueLine).getJobSize(), queue.get(queueLine).getJobNum(), true);
                                memorymanager.get(q).setAllValue(queue.get(queueLine).getJobSize(), q, true);
                                
                                System.out.println("-------------------------------------------------");
                                System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                                System.out.println("-------------------------------------------------");
                                
                                queue.remove(queueLine);
                                
                            } else if (queue.get(queueLine).getJobSize() < memorymanager.get(q).getMemorySize()) {
                                
                                int lineNum = 0;
                                int currentJobNum = queue.get(queueLine).getJobNum();

                                for (int t = 1; t < job.size(); t++) {
                                    if (currentJobNum == job.get(t).getJobNum()) {
                                        lineNum = t;
                                    }
                                }
                                job.get(lineNum).setProcessStatus(true);

                                partition.add(q + 1, new MemoryPartition(partition.get(q).getMemorySize() - queue.get(queueLine).getJobSize(), false));
                                memorymanager.add(q + 1, new MemoryManager(memorymanager.get(q).getMemorySize() - queue.get(queueLine).getJobSize(), q + 1, true));
                                
                                partition.get(q).setAllValue(queue.get(queueLine).getJobSize(), queue.get(queueLine).getJobNum(), true);
                                memorymanager.get(q).setAllValue(queue.get(queueLine).getJobSize(), q, true);

                                System.out.println("-------------------------------------------------");
                                System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                                System.out.println("-------------------------------------------------");
                                
                                queue.remove(queueLine);
                            
                            }
                        }
                    }
                }
            }
            
            
            // loaded Job list        
            int h;
                
            for(int jobLine = 0;jobLine<job.size();jobLine++){
                
                
                if(job.get(jobLine).getArrivalTime()==clock && !job.get(jobLine).getProcessStatus() && !job.get(jobLine).getJobDone()){
                    
                   
                    System.out.println("Arrival Job: " + job.get(jobLine).getJobNum());
                   
                              
                               for(h=0;h<partition.size()-1;h++){
                                   if( !partition.get(h).getOccupied() && job.get(jobLine).getJobSize() <= partition.get(h).getMemorySize()){
                                          System.out.println("Memory: " + partition.get(h).getMemorySize());
                                        break;
                                   }

                               }

                                   if(job.get(jobLine).getJobSize() == memorymanager.get(h).getMemorySize()){
                                   
                                   partition.get(h).setAllValue(job.get(jobLine).getJobSize(), job.get(jobLine).getJobNum(), true);
                                   memorymanager.get(h).setAllValue(job.get(jobLine).getJobSize(), h, true);
                                   
                                   job.get(jobLine).setProcessStatus(true);

                                   System.out.println("-------------------------------------------------");
                                   System.out.println("Job Processing: " + job.get(jobLine).getJobNum());
                                   System.out.println("Memory Block Allocated: " + partition.get(h).getMemorySize());
                                   System.out.println("-------------------------------------------------");
                                   
                               } else if (job.get(jobLine).getJobSize() < memorymanager.get(h).getMemorySize()){
                                   
                                   partition.add(h+1, new MemoryPartition((partition.get(h).getMemorySize()-job.get(jobLine).getJobSize()), false));
                                   memorymanager.add(h+1, new MemoryManager((memorymanager.get(h).getMemorySize()-job.get(jobLine).getJobSize()), h+1, false));
                                   
                                   partition.get(h).setAllValue(job.get(jobLine).getJobSize(), job.get(jobLine).getJobNum(), true);
                                   memorymanager.get(h).setAllValue(job.get(jobLine).getJobSize(), h, true);
                                   
                                   
                                   
                                   job.get(jobLine).setProcessStatus(true);

                                   System.out.println("-------------------------------------------------");
                                   System.out.println("Job Processing: " + job.get(jobLine).getJobNum());
                                   System.out.println("Memory Block Allocated: " + partition.get(h).getMemorySize());
                                   System.out.println("-------------------------------------------------");
                                   
                               } else {
                                   
                                   System.out.println("-------------------------------------------------");
                                    System.out.println("Job not Fit: " + job.get(jobLine).getJobNum());
                                    queue.addLast(job.get(jobLine));
                                    System.out.println("-------------------------------------------------");
                                   
                               }     
            }
        }
            
            clock++;
            if(partition.get(0).getMemorySize() == freeMemory){
                break;
            }
            if(clock > 100){
                break;
            }
        }
        
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(3);
        
        System.out.println("Total Internal Fragmentation: " + totalFrag);
        System.out.println("Average Internal Fragmentation: " + totalFrag/50000);
        System.out.println("Total Jobs Completed: " + 40);
        System.out.println("Total Time Unit: " + endTime(job));
        System.out.println("Partition Under Used: " );
        System.out.println("Partition Normal Used: ");
        System.out.println("Partition Heavily Used: ");
        System.out.println("Average Waiting Time: ");
        System.out.println("Max Waiting Time: ");
        System.out.println("Min Waiting Time: ");
        System.out.println("Throughput: " + format.format(40/endTime(job)));
        System.out.println("Average Waiting Length: ");
        System.out.println("Max Waiting Length: ");
        System.out.println("Min Waiting Length: ");
        
    }
    
    public void changeBFArrangement(LinkedList<MemoryJob> job){
        
        for(int outerloop = 0; outerloop < 9; outerloop++){
            for(int innerloop = 0; innerloop < 9; innerloop++){
                if(job.get(innerloop).getJobSize() > job.get(innerloop + 1).getJobSize()){
                    Collections.swap(job, innerloop, innerloop+1);
                }
            
            }
        }
        
    }
    
    public void changeWFArrangement(LinkedList<MemoryJob> job){
        
        for(int outerloop = 0; outerloop < 9; outerloop++){
            for(int innerloop = 0; innerloop < 9; innerloop++){
                if(job.get(innerloop).getJobSize() > job.get(innerloop + 1).getJobSize()){
                    Collections.swap(job, innerloop+1, innerloop);
                }
            
            }
        }
        
    }
    
    public void readJob(LinkedList<MemoryJob> job) throws IOException {
          
        try{
           
            int temp1, temp2, temp3, temp4;
            Scanner reader = new Scanner(new File
        ("C:\\Users\\user\\Documents\\NetBeansProjects\\DynamicMemoryPartitioning-master\\DynamicMemoryPartitioning\\src\\dynamicmemorypartitioning\\Haris\\JoblistTest.txt"));
            
            while(reader.hasNextLine()){
               
                skip(reader, 1);
               
                if(reader.hasNextInt()){
                    temp1 = reader.nextInt();
                    temp2 = reader.nextInt();
                    temp3 = reader.nextInt();
                    temp4 = reader.nextInt();                    
                    job.addLast(new MemoryJob(temp1,temp2, temp3, temp4, false, false, 0));
                    
                }
                
                
            }
            
        } catch(IOException e) {
            System.out.println(e);
        }
        
    }
    
    public void readMemory(LinkedList<MemoryPartition> memorypartition) {
        try {
            int temp1;
            boolean temp2 = false; // since it is newly partitioned -> not occupied
            Scanner reader = new Scanner(new File
        ("C:\\Users\\user\\Documents\\NetBeansProjects\\DynamicMemoryPartitioning-master\\DynamicMemoryPartitioning\\src\\dynamicmemorypartitioning\\Haris\\MemoryListTest.txt"));
            while(reader.hasNextLine()) {
                skip(reader, 1);
                if(reader.hasNextInt()) {
                    temp1 = reader.nextInt();
                    //System.out.print(temp1 + "\n");
                    memorypartition.add(new MemoryPartition(temp1, temp2));
                }
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    
}
