/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicmemorypartitioning.Haris;

import dynamicmemorypartitioning.Haris.MemoryJob;
import dynamicmemorypartitioning.Haris.MemoryPartition;
import java.io.*;
import java.util.*;

/**
 *
 * @Iman Haris Bin Hadi DonutRanger
 */

public class DR_Test {
    
    static LinkedList<MemoryJob> Job = new LinkedList();
    static LinkedList<MemoryPartition> Partition = new LinkedList();
    static LinkedList<MemoryJob> WaitQueue = new LinkedList();
    //LinkedList<Integer> test = new LinkedList();
    static ArrayList<String> loadArray = new ArrayList(); 
    String readTxtFile;
    MemoryJob load = new MemoryJob();
        
    
    //
    //List<String> writeStore = new ArrayList<String>(Arrays.asList(txtWrite.split("[\\s\\n]")));
    //int JobTime;
    
    public static void main(String[] args) throws IOException{
        DR_Test testRun = new DR_Test();
        testRun.readJob();
        testRun.readMemory();
        //testRun.FirstFitAlgo(Job, Partition, WaitQueue);
        testRun.BestFitAlgo(Job, Partition, WaitQueue);
        //testRun.dynamicFirstFit(Job, Partition, WaitQueue);
        //testRun.loadJobs();
    }
    public static void skip(Scanner s,int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(s.hasNextLine())s.nextLine();
        }
    }
    
    public void FirstFitAlgo(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> memorypartition, LinkedList<MemoryJob> queue){
        int clock = 0;
        int i;
        boolean allJobStatus = false;
        
        do{
        
            System.out.println("Time cycle: " + clock);

            for(i = 0;i<memorypartition.size();i++){
                if(memorypartition.get(i).getOccupied()){
                    int currentJobNum = memorypartition.get(i).getJobNum();
                    int currentProcessTime = job.get(currentJobNum).getProcessTime();
                    int newProcessTime = --currentProcessTime;
                    job.get(currentJobNum).setProcessTime(newProcessTime);
                    if(job.get(currentJobNum).getProcessTime() == 0){
                        memorypartition.get(i).setOccupied(false);
                        job.get(currentJobNum).setJobDone(true);
                        System.out.println("Job Done: " + job.get(currentJobNum).getJobNum());
                        System.out.println("-----------------------------------------------");
                    }
                }
            }

            if(!queue.isEmpty()){

                for(i = 0;i<queue.size();i++){

                    System.out.println("Queue : " + queue.get(i).getJobNum() + " and " + queue.get(i).getJobSize());
    
                    for(int k = 0; k < memorypartition.size();k++){
                        
                        if( queue.get(i).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                            
                            int lineNum = 0;
                            int currentJobNum = queue.get(i).getJobNum();
                            
                            for(int t = 1; t<job.size();t++){
                                if(currentJobNum == job.get(t).getJobNum()){
                                    lineNum = t;
                                }
                            }
                            
                            job.get(lineNum).setProcessStatus(true);
                            memorypartition.get(k).setJobNum(lineNum);
                            memorypartition.get(k).setOccupied(true);
                            memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(lineNum).getJobSize());

                            System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                            System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                            System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                            System.out.println("-----------------------------------------------");

                            queue.remove(i);
                            break;
                        }
                    }

                }
            }
            for(i = 0;i<job.size();i++){

                if(job.get(i).getArrivalTime()==clock && !job.get(i).getProcessStatus() && !job.get(i).getJobDone()){

                  for(int k = 0;k<memorypartition.size();k++){ 

                    if(job.get(i).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){

                        job.get(i).setProcessStatus(true);
                        memorypartition.get(k).setJobNum(i);
                        memorypartition.get(k).setOccupied(true);
                        memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(i).getJobSize());

                        System.out.println("Job Processing: " + job.get(i).getJobNum());
                        System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                        System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                        System.out.println("-----------------------------------------------");

                        break;
                    }

                  }

                  if(!job.get(i).getProcessStatus()){
                      
                      System.out.println("Job Not Match: " + job.get(i).getJobNum());
                      queue.addLast(job.get(i));
                      System.out.println("-----------------------------------------------");
                  
                  }

                }

            }

            for(i = 0; i< job.size(); i++){
                
                if(!job.get(i).getJobDone()){
                   break;
                }
                
            }
            
            clock++;
            
        }while(!allJobStatus);
    }
    
    public void BestFitAlgo(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> memorypartition, LinkedList<MemoryJob> queue){
        int clock = 0;
        int i;
        boolean allJobStatus = false;
        int memoryWaste=0;
        int initialMemoryWaste=0;
        
        changeArrangement(memorypartition);
        
        do{
        
            System.out.println("Time cycle: " + clock);

            /*for(i = 0;i<memorypartition.size();i++){
                if(memorypartition.get(i).getOccupied()){
                    int currentJobNum = memorypartition.get(i).getJobNum();
                    int currentProcessTime = job.get(currentJobNum).getProcessTime();
                    int newProcessTime = --currentProcessTime;
                    job.get(currentJobNum).setProcessTime(newProcessTime);
                    if(job.get(currentJobNum).getProcessTime() == 0){
                        memorypartition.get(i).setOccupied(false);
                        job.get(currentJobNum).setJobDone(true);
                        System.out.println("Job Done: " + job.get(currentJobNum).getJobNum());
                        System.out.println("-----------------------------------------------");
                    }
                }
            }*/

            /*if(!queue.isEmpty()){

                for(i = 0;i<queue.size();i++){

                    System.out.println("Queue : " + queue.get(i).getJobNum() + " and " + queue.get(i).getJobSize());
    
                    for(int k = 0; k < memorypartition.size();k++){
                        
                        if( queue.get(i).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                            
                            int lineNum = 0;
                            int currentJobNum = queue.get(i).getJobNum();
                            
                            for(int t = 1; t<job.size();t++){
                                if(currentJobNum == job.get(t).getJobNum()){
                                    lineNum = t;
                                }
                            }
                            
                            job.get(lineNum).setProcessStatus(true);
                            memorypartition.get(k).setJobNum(lineNum);
                            memorypartition.get(k).setOccupied(true);
                            memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(lineNum).getJobSize());

                            System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                            System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                            System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                            System.out.println("-----------------------------------------------");

                            queue.remove(i);
                            break;
                        }
                    }

                }
            }*/
            for(i = 0;i<job.size();i++){
                                
                if(job.get(i).getArrivalTime()==clock && !job.get(i).getProcessStatus() && !job.get(i).getJobDone()){
                
                    initialMemoryWaste = 50000 - job.get(i).getJobSize();
                  
                    for(int k = 0; k < memorypartition.size() ;k++){
                        
                        
                        
                    }
                       
                    /*for(int k = 0;k<memorypartition.size();k++){ 

                    if(job.get(i).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){

                        job.get(i).setProcessStatus(true);
                        memorypartition.get(k).setJobNum(i);
                        memorypartition.get(k).setOccupied(true);
                        memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(i).getJobSize());

                        System.out.println("Job Processing: " + job.get(i).getJobNum());
                        System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                        System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                        System.out.println("-----------------------------------------------");

                        break;
                    }

                  }*/

                  if(!job.get(i).getProcessStatus()){
                      
                      System.out.println("Job Not Match: " + job.get(i).getJobNum());
                      queue.addLast(job.get(i));
                      System.out.println("-----------------------------------------------");
                  
                  }

                }

            }

            /*for(i = 0; i< job.size(); i++){
                
                if(!job.get(i).getJobDone()){
                   break;
                }
                
            }*/
            
            clock++;
            
        }while(!allJobStatus);
    }
    
    public void changeArrangement(LinkedList<MemoryPartition> memory){
        
        for(int outerloop = 0; outerloop < memory.size(); outerloop++){
            
            for(int innerloop = 0; innerloop < memory.size(); innerloop++){
                if(memory.get(innerloop).getMemorySize() > memory.get(innerloop + 1).getMemorySize()){
                    Collections.swap(memory, innerloop, innerloop+1);
                }
            
            }

        }
        
    }
    
    /*public void dynamicFirstFit(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> fixedpartition, Queue<MemoryJob> queue){
        //Declare a memorypartitioner two-way linked list. The nodes should store memory size and free/used state.
        
        //.FOR the whole job list,
        // .transverse the memory list checking for nodes with free state. Since this is first fit, look for the first available.
        // .if node with free state memory size is more or equal to job size, 
        //   .allocate memory for the job in the linked list by creating a new node with memory size  
        //    splitting the free memory to two nodes - one tagged not free the other tagged free.
        //   .FOR each job
        //     .just copy Kai Wen's code to complete the job processing time.
        //     .when job completes, deallocate memory by tagging the node the memory was assigned to as free.
        //     .if memory of next and/or previous node is also free, merge the two nodes by adding up the memory, 
        //      changing the memory size of the first node of the list to the total memory and deleting all nodes
        //      that were involved in the merging process.
        // else
        //   .put job into waiting queue.
        //   .calculate external fragmentation. (total of all free state nodes)
        
        int clock = 0;
        int i;
        boolean allJobStatus = false;
        int freeMemory; //the algorithms need access to free memory space available to control loops
        int totalMemoryBlocks;
        int currentJobNum = 1; //start from job 1.
        LinkedList<MemoryPartition> dynamicpartition = new LinkedList();
        
        totalMemoryBlocks=0;
        for(int j=0;j<fixedpartition.size();j++)
            totalMemoryBlocks += fixedpartition.get(j).getMemorySize();
        
        freeMemory = totalMemoryBlocks;
        dynamicpartition.addLast(new MemoryPartition(freeMemory, false)); //creates a node for all free memory
       
        do{
            
            System.out.println("Time cycle: " + clock);

            while(freeMemory>=dynamicpartition.get(currentJobNum).getMemorySize()){
                if(dynamicpartition.get(i).getOccupied()){
                    currentJobNum = dynamicpartition.get(i).getJobNum();
                    int currentProcessTime = job.get(currentJobNum).getProcessTime();
                    job.get(currentJobNum).setProcessTime(currentProcessTime--);
                    if(job.get(currentJobNum).getProcessTime() == 0){
                        dynamicpartition.get(i).setOccupied(false);
                        job.get(i).setJobDone(true);
                    }
                }
            }
        
            if(!queue.isEmpty()){

                for(i = 0;i<queue.size();i++){

                    System.out.println("Queue : " + queue.get(i).getJobNum() + " and " + queue.get(i).getJobSize());
    
                    for(int k = 0; k < memorypartition.size();k++){
                        
                        if( queue.get(i).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                            
                            int lineNum = 0;
                            int currentJobNum = queue.get(i).getJobNum();
                            
                            for(int t = 1; t<job.size();t++){
                                if(currentJobNum == job.get(t).getJobNum()){
                                    lineNum = t;
                                }
                            }
                            
                            job.get(lineNum).setProcessStatus(true);
                            memorypartition.get(k).setJobNum(lineNum);
                            memorypartition.get(k).setOccupied(true);
                            memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(lineNum).getJobSize());

                            System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                            System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                            System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                            System.out.println("-----------------------------------------------");

                            queue.remove(i);
                            break;
                        }
                    }

                }
            }
            for(i = 0;i<job.size();i++){

                if(job.get(i).getArrivalTime()==clock && !job.get(i).getProcessStatus() && !job.get(i).getJobDone()){

                  for(int k = 0;k<memorypartition.size();k++){ 

                    if(job.get(i).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){

                        job.get(i).setProcessStatus(true);
                        memorypartition.get(k).setJobNum(i);
                        memorypartition.get(k).setOccupied(true);
                        memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(i).getJobSize());

                        System.out.println("Job Processing: " + job.get(i).getJobNum());
                        System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                        System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                        System.out.println("-----------------------------------------------");

                        break;
                    }

                  }

                  if(!job.get(i).getProcessStatus()){
                      
                      System.out.println("Job Not Match: " + job.get(i).getJobNum());
                      queue.addLast(job.get(i));
                      System.out.println("-----------------------------------------------");
                  
                  }

                }

            }

            for(i = 0; i< job.size(); i++){
                
                if(!job.get(i).getJobDone()){
                   break;
                }
                
            }
            
            clock++;
            
        }while(!allJobStatus);
    }*/
    
    public void readJob() throws IOException {
        //BufferedReader reader = null;
        try{
            //int i = 0;
            //reader = new BufferedReader(new FileReader("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/JoblistTest.txt"));
            //while((readTxtFile = reader.readLine())!= null){
            /*i++;
            List<String> loadText = new ArrayList<String>(Arrays.asList(readTxtFile.split("[\\s\\n]")));
            Job.setJobNum(Integer.parseInt(loadText.get(1)));*/
            
            
            //loadArray.add(readTxtFile);    
            int temp1, temp2, temp3, temp4, nJob;
            Scanner reader = new Scanner(new File
        ("C:\\Users\\user\\Documents\\NetBeansProjects\\DynamicMemoryPartitioning-master\\DynamicMemoryPartitioning\\src\\dynamicmemorypartitioning\\Haris\\JoblistTest.txt"));
            //int testvar = reader;
            //List<String> loadText = new ArrayList<String>(Arrays.asList(readTextFile.split("[\\s\\n]")));
            while(reader.hasNextLine()){
                //nJob = reader.nextInt();
                //System.out.println(nJob);
                skip(reader, 1);
                //i++;
                //Job.insert((MemoryJob)reader);
                if(reader.hasNextInt()){
                    temp1 = reader.nextInt();
                    temp2 = reader.nextInt();
                    temp3 = reader.nextInt();
                    temp4 = reader.nextInt();                    
                    Job.addLast(new MemoryJob(temp1,temp2, temp3, temp4, false, false));
                    //System.out.println(Job.get(0).getArrivalTime());
                    //System.out.println(Job.get(1).getArrivalTime());

                    //System.out.print(Job.isEmpty());
                    //test.add(reader);
                    //Job.add((mgetArrivalTime());
                    //Job.ProcessTime();
                    //Job.getBlockSize();
                }
                
                //List<String> readTest = new ArrayList<String>(Arrays.asList(readString.split("[\\s\\n]")));
                
                /*for(int i = 0; i < jobArray.size(); i++){
                    Job.add((MemoryJob) readTest);
                }*/
               
                //jobArray.add(readString);
            }
            //System.out.println(i);
            //int nJobs = Integer.parseInt(loadArray.get(0));
            
            //String dummy;
            //for(int n = 1; n < loadArray.size(); n++){
              //  dummy = loadArray.get(n);
                //System.out.println(loadArray.size());
                //String[] arry = new String[loadArray.size()];
                //String[] arr = new String[loadArray.size()];
                //arry = dummy.split(" ");
                //int v = Integer.parseInt(arry[1]);
                //System.out.println(v);
                //dummy = arr.split(" ")
                //List<String>  arr = new ArrayList<>(Arrays.asList(dummy.split("[\\s\\n]")));
                
                //System.out.println(loadArray.isEmpty());
                //System.out.println(Arrays.toString(arry));
                
                //System.out.println(arry[n]); //+ " " + arry[n+1] + " " + arry[n+2] + " " + arry[n+3]);
                //Job.add(new MemoryJob(Integer.parseInt(arry[1]),(arry[2]),(arry[3]), (arry[0])));
                /*for(int m = 0; m < 4; m++)
                {
                    System.out.println(arr.get);
                }*/
                
                
                //System.out.println(arr.get(1));
                //System.out.println(arr.get(2));
                //**** index & parse problem ****//
                //getJobNum() = Integer.parseInt(arr.get(1));

                //arry[n] = Integer.parseInt(arr.get(n));
                //load.setJobNum(arry[n]);
                //load.setArrivalTime(arry[n+2]);
                //System.out.print(arry[n] + "\n");

                //arry[n][] = Integer.parseInt(arr.get(n));
                //load.setArrivalTime(arry[n][]);

                
                //Job.add(new MemoryJob((arr.get(i+1)),(arr.get(2)),(arr.get(3)), (arr.get(4))));
       
                //System.out.println(arry[n][]);

               
                /*List<String> testLoad = new ArrayList<String>(Arrays.asList(dummy.split("[\\s]")));
                for(int m = 0; m < loadArray.size();m++){
                    Job.add(new MemoryJob(Integer.parseInt((loadArray.get(m)), (loadArray.get(m+1)), (loadArray.get(m+2)), (loadArray.get(m+3)))));
                }*/
            //}
            
            //int num
            
            
           
            
            /*for(int i = 0; i < jobArray.size(); i++){
                readTest
            }*/
            
            // Check how many elements
            /*for(int n = 0; n < jobArray.size();n++){
                System.out.println(jobArray.get(n));
            }*/
            
            //System.out.println(i);
            //reader.close();
            
            /*for(int n = 0; n < jobArray.size(); n++) {
                Job.add(jobArray.get(n));
            }*/
            
            
        } catch(IOException e) {
            System.out.println(e);
        }
        
    }
    
    //public void loadJobs(){
        //change first job in the list because it is int of how many jobs.
        //int numJobs = Integer.parseInt(jobArray.get(0));
        //int nJobs = (int) toCast;
        //System.out.print(numJobs);
    //}
    
    public void readMemory() {
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
                    Partition.add(new MemoryPartition(temp1, temp2));
                }
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    
}
