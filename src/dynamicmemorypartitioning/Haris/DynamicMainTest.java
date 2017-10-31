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
    static LinkedList<Dynamic> Memory = new LinkedList();
    
    
    public static void main(String[] args){
        DynamicMainTest testDynamic = new DynamicMainTest();
        testDynamic.readJob();
        testDynamic.initialDyna();
    }
    
    public void loadDyna(LinkedList<MemoryJob> jobQueue){
        for(int i = 0; i < jobQueue.size(); i++) {
            int currentJob = 
        }
    }
    
    
    
    public void initialDyna() {
        int memorySize = sumMemory();
        int memoryUpBound = 0;
        int memoryLowBound = memorySize;
        Memory.add(new Dynamic(memorySize, memoryUpBound, memoryLowBound));
    }
    
    // Just to read
    public static void skip(Scanner s,int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(s.hasNextLine())s.nextLine();
        }
    }
    
    public void readJob() {
        try {
            int temp1, temp2, temp3, temp4;
            boolean temp5 = false, temp6 = false; // since it is newly partitioned -> not occupied
            Scanner reader = new Scanner(new File
        ("/Users/DonutRanger/NetBeansProjects/DynamicMemoryPartitioning/src/dynamicmemorypartitioning/Haris/JoblistTest.txt"));
            while(reader.hasNextLine()) {
                skip(reader, 1);
                if(reader.hasNextInt()) {
                    temp1 = reader.nextInt();
                    temp2 = reader.nextInt();
                    temp3 = reader.nextInt();
                    temp4 = reader.nextInt();
                    //System.out.print(temp1 + "\n");
                    Job.add(new MemoryJob(temp1, temp2, temp3, temp4, temp5, temp6));
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
