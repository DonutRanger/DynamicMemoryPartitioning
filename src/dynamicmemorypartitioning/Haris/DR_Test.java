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
    static ArrayList<String> loadArray = new ArrayList(); 
    String readTxtFile;
    
    
    MemoryPar load = new MemoryPar();
        
    
    //
    //List<String> writeStore = new ArrayList<String>(Arrays.asList(txtWrite.split("[\\s\\n]")));
    //int JobTime;
    
    public static void main(String[] args) throws IOException{
        DR_Test testRun = new DR_Test();
        testRun.readJob();
        testRun.loadJobs();
    }
    
    public void readJob() throws IOException {
        BufferedReader reader = null;
        try{
            int i = 0;
            reader = new BufferedReader(new FileReader
        ("C:\\Users\\user\\Documents\\NetBeansProjects\\DynamicMemoryPartitioning-master\\DynamicMemoryPartitioning\\src\\dynamicmemorypartitioning\\Haris\\JoblistTest.txt"));
            while((readTxtFile = reader.readLine())!= null){
            
            loadArray.add(readTxtFile);    
            
            //Scanner reader = new Scanner(new File("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/textTest.txt"));
            //int testvar = reader;
            //List<String> loadText = new ArrayList<String>(Arrays.asList(readTextFile.split("[\\s\\n]")));
            
            
            /*while(reader.hasNext()){
                //i++;
                //Job.insert((MemoryPar)reader);
                if(reader.hasNextInt()){
                    //Job.add((MemoryPar)getJobNum());
                    //test.add(reader);
                    //Job.add((mgetArrivalTime());
                    //Job.ProcessTime();
                    //Job.getBlockSize();
                }*/
                
                //List<String> readTest = new ArrayList<String>(Arrays.asList(readString.split("[\\s\\n]")));
                
                /*for(int i = 0; i < jobArray.size(); i++){
                    Job.add((MemoryPar) readTest);
                }*/
               
                //jobArray.add(readString);
            }
            
            String dummy;
            for(int n = 1; n < loadArray.size(); n++){
                dummy = loadArray.get(n);
                //System.out.println(loadArray.size());
                Integer[][] arry = new Integer[loadArray.size()][4];
                //String[] arr = new String[loadArray.size()];
                //dummy = arr.split(" ")
                List<String>  arr = new ArrayList<>(Arrays.asList(dummy.split("[\\s]")));
                //Job.add(new MemoryPar((arr.get(i+1)),(arr.get(2)),(arr.get(3)), (arr.get(4))));
                System.out.println(arr.get(0));
                //System.out.println(arr.get(1));
                //System.out.println(arr.get(2));
                //**** index & parse problem ****//
                //getJobNum() = Integer.parseInt(arr.get(1));
                //arry[n][] = Integer.parseInt(arr.get(n));
                //load.setArrivalTime(arry[n][]);
                
                //Job.add(new MemoryPar((arr.get(i+1)),(arr.get(2)),(arr.get(3)), (arr.get(4))));
               
                //System.out.println(arry[n][]);
               
                /*List<String> testLoad = new ArrayList<String>(Arrays.asList(dummy.split("[\\s]")));
                for(int m = 0; m < loadArray.size();m++){
                    Job.add(new MemoryPar(Integer.parseInt((loadArray.get(m)), (loadArray.get(m+1)), (loadArray.get(m+2)), (loadArray.get(m+3)))));
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
