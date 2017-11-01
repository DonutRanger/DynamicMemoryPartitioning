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
        DR_Test testRun2 = new DR_Test();
        
        System.out.println("FIRST FIT ALGORITHM");
        testRun.readJob();
        testRun.readMemory();
        testRun.FirstandBestFitAlgo(Job, Partition, WaitQueue);
        
        
        
        System.out.println("BEST FIT ALGORITHM");
        testRun2.readJob();
        testRun2.readMemory();
        WaitQueue.clear();
        testRun2.changeArrangement(Partition);
        testRun2.FirstandBestFitAlgo(Job, Partition, WaitQueue);
        //testRun.dynamicFirstFit(Job, Partition, WaitQueue);
        //testRun.loadJobs();
    }
    public static void skip(Scanner s,int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(s.hasNextLine())s.nextLine();
        }
    }
    
    public void FirstandBestFitAlgo(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> memorypartition, LinkedList<MemoryJob> queue){
        int clock = 0;
        boolean allJobStatus = false;

        do{
            
            System.out.println("---------------------------------");
            System.out.println("Time cycle: " + clock);
            
            for(int i = 0;i<memorypartition.size();i++){
                if(memorypartition.get(i).getOccupied()){
                    int currentJobNum = memorypartition.get(i).getJobNum();
                    int currentProcessTime = job.get(currentJobNum).getProcessTime();
                    int newProcessTime = --currentProcessTime;
                    job.get(currentJobNum).setProcessTime(newProcessTime);
                    if(job.get(currentJobNum).getProcessTime() == 0){
                        memorypartition.get(i).setOccupied(false);
                        job.get(currentJobNum).setJobDone(true);
                        System.out.println("Job Done: " + job.get(currentJobNum).getJobNum());
                        //System.out.println("-----------------------------------------------");
                    }
                }
            }
            
            // Queue job list
            if(!queue.isEmpty()){
                
                for(int q = 0;q<queue.size();q++){
                    System.out.println("Queue : " + queue.get(q).getJobNum() + " and " + queue.get(q).getJobSize());
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

                            System.out.println("Queue Job Processing: " + job.get(lineNum).getJobNum());
                            System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                            System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                            //System.out.println("-----------------------------------------------");

                            queue.remove(queueLine);
                            break;
                        }
                    }
                }
            }
            
            // loaded Job list
            for(int jobLine = 0;jobLine<job.size();jobLine++){

                if(job.get(jobLine).getArrivalTime()==clock && !job.get(jobLine).getProcessStatus() && !job.get(jobLine).getJobDone()){
                  System.out.println(jobLine);
                  System.out.println("Arrival Job: " + job.get(jobLine).getJobNum());
                    
                  for(int k = 0;k<memorypartition.size();k++){ 

                    if(job.get(jobLine).getJobSize() <= memorypartition.get(k).getMemorySize() && !memorypartition.get(k).getOccupied()){
                        
                        job.get(jobLine).setProcessStatus(true);
                        memorypartition.get(k).setJobNum(jobLine);
                        memorypartition.get(k).setOccupied(true);
                        memorypartition.get(k).fragmentationVal(memorypartition.get(k).getMemorySize()-job.get(jobLine).getJobSize());

                        System.out.println("Job Processing: " + job.get(jobLine).getJobNum());
                        System.out.println("Memory Block Used: " + memorypartition.get(k).getMemorySize());
                        System.out.println("Fragmentation Value: " + memorypartition.get(k).getFragmentationVal());
                        //System.out.println("-----------------------------------------------");

                        break;
                    }

                  }

                  if(!job.get(jobLine).getProcessStatus()){
                      
                      System.out.println("Job not Fit: " + job.get(jobLine).getJobNum());
                      queue.addLast(job.get(jobLine));
                      System.out.println("-----------------------------------------------");
                  
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
                System.out.println("Job " + job.get(i).getJobNum() + ": Permanently Not Done.");
            }
        }
        
    }
        
    public void changeArrangement(LinkedList<MemoryPartition> memorypartition){
        
        for(int outerloop = 0; outerloop < 10; outerloop++){
            for(int innerloop = 0; innerloop < 10; innerloop++){
                if(memorypartition.get(innerloop).getMemorySize() > memorypartition.get(innerloop + 1).getMemorySize()){
                    Collections.swap(memorypartition, innerloop, innerloop+1);
                }
            
            }
        }
        
    }
    
    // Asad's code
    public void dynamicFirstFit(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> fixedpartition, Queue<MemoryJob> queue){
        
        int clock = 0;
        int i;
        boolean allJobStatus = false;
        int freeMemory; //the algorithms need access to free memory space available to control loops
        int totalMemoryBlocks;
        LinkedList<MemoryPartition> dynamicpartition = new LinkedList();
        
        totalMemoryBlocks=0;
        for(int j=0;j<fixedpartition.size();j++)
            totalMemoryBlocks += fixedpartition.get(j).getMemorySize();
        
        freeMemory = totalMemoryBlocks;
        dynamicpartition.addLast(new MemoryPartition(freeMemory, false)); //creates a node for all free memory
       
        
    }
    
    public void dynamicMemoryMerge(LinkedList<MemoryPartition> partition){
        for(int i=0;i<partition.size();i++){
            if(!partition.get(i).getOccupied()&&!partition.get(i+1).getOccupied())
            {
                partition.get(i+1).SetMemory(partition.get(i).getMemorySize()+partition.get(i).getMemorySize());
                partition.remove(i);
                i--;
            }            
        }
    }
    
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
