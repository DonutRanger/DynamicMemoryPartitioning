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
    
    LinkedList<MemoryJob> Job = new LinkedList();
    LinkedList<MemoryPartition> Partition = new LinkedList();
    //Queue<MemoryPar> WaitQueue = new LinkedList();
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

        //testRun.loadJobs();
    }
    public static void skip(Scanner s,int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(s.hasNextLine())s.nextLine();
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
            Scanner reader = new Scanner(new File("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/JoblistTest.txt"));
            //int testvar = reader;
            //List<String> loadText = new ArrayList<String>(Arrays.asList(readTextFile.split("[\\s\\n]")));
            while(reader.hasNextLine()){
                nJob = reader.nextInt();
                System.out.println(nJob);
                skip(reader, 1);
                //i++;
                //Job.insert((MemoryJob)reader);
                if(reader.hasNextInt()){
                    temp1 = reader.nextInt();
                    temp2 = reader.nextInt();
                    temp3 = reader.nextInt();
                    temp4 = reader.nextInt();
                    System.out.print(temp1 + " ");
                    System.out.print(temp2 + " ");
                    System.out.print(temp3 + " ");
                    System.out.print(temp4 + "\n");
                    
                    Job.add(new MemoryJob(temp1,temp2, temp3, temp4));
                    
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
            
            String dummy;
            for(int n = 1; n < loadArray.size(); n++){
                dummy = loadArray.get(n);
                //System.out.println(loadArray.size());
                String[] arry = new String[loadArray.size()];
                //String[] arr = new String[loadArray.size()];
                arry = dummy.split(" ");
                int v = Integer.parseInt(arry[1]);
                System.out.println(v);
                //dummy = arr.split(" ")
                //List<String>  arr = new ArrayList<>(Arrays.asList(dummy.split("[\\s\\n]")));
                
                //System.out.println(loadArray.isEmpty());
                System.out.println(Arrays.toString(arry));
                
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
            }
            
            //int num
            
            
           
            
            /*for(int i = 0; i < jobArray.size(); i++){
                readTest
            }*/
            
            // Check how many elements
            /*for(int n = 0; n < jobArray.size();n++){
                System.out.println(jobArray.get(n));
            }*/
            
            //System.out.println(i);
            reader.close();
            
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
            Scanner reader = new Scanner(new File("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/MemoryListTest.txt"));
            while(reader.hasNextLine()) {
                skip(reader, 1);
                if(reader.hasNextInt()) {
                    temp1 = reader.nextInt();
                    System.out.print(temp1 + "\n");
                    Partition.add(new MemoryPartition(temp1, temp2));
                }
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    
}
