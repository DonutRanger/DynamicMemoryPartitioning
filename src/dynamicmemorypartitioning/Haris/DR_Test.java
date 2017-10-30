/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicmemorypartitioning.Haris;

import dynamicmemorypartitioning.Haris.MemoryPar;
import java.io.*;
import java.util.*;

/**
 *
 * @Iman Haris Bin Hadi DonutRanger
 */

public class DR_Test {
    
    LinkedList<MemoryPar> Job = new LinkedList();
    Queue<MemoryPar> WaitQueue = new LinkedList();
    LinkedList<Integer> test = new LinkedList();
    static ArrayList<Integer> jobArray = new ArrayList(); 
    int readTxtFile;
    //
    //List<String> writeStore = new ArrayList<String>(Arrays.asList(txtWrite.split("[\\s\\n]")));
    //int JobTime;
    
    public static void main(String[] args) throws IOException{
        DR_Test testRun = new DR_Test();
        testRun.readJob();
        testRun.loadJobs();
    }
    
    public void readJob() throws IOException {
        //BufferedReader reader = null;
        try{
            int i = 0;
            //reader = new BufferedReader(new FileReader("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/JoblistTest.txt"));
            //while((readTxtFile = reader.readLine())){
            Scanner reader = new Scanner(new File("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/textTest.txt"));
            //int testvar = reader;
            while(reader.hasNext()){
                //i++;
                //Job.insert((MemoryPar)reader);
                if(reader.hasNextInt()){
                    //Job.add((MemoryPar)getJobNum());
                    //test.add(reader);
                    //Job.add((mgetArrivalTime());
                    //Job.ProcessTime();
                    //Job.getBlockSize();
                }
                
                //List<String> readTest = new ArrayList<String>(Arrays.asList(readString.split("[\\s\\n]")));
                
                /*for(int i = 0; i < jobArray.size(); i++){
                    Job.add((MemoryPar) readTest);
                }*/
               
                //jobArray.add(readString);
            }
           
            // List<String> readTest = new ArrayList<String>(Arrays.asList(readString.split("[\\s\\n]")));
            /*for(int i = 0; i < jobArray.size(); i++){
                readTest
            }*/
            
            // Check how many elements
            /*for(int n = 0; n < jobArray.size();n++){
                System.out.println(jobArray.get(n));
            }*/
            
            System.out.println(i);
            reader.close();
            
            /*for(int n = 0; n < jobArray.size(); n++) {
                Job.add(jobArray.get(n));
            }*/
            
            
        } catch(IOException e) {
            System.out.println(e);
        }
        
    }
    
    public void loadJobs(){
        //change first job in the list because it is int of how many jobs.
        //int numJobs = Integer.parseInt(jobArray.get(0));
        //int nJobs = (int) toCast;
        //System.out.print(numJobs);
    }
    
}
