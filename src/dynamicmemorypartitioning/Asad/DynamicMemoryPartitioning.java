/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicmemorypartitioning.Asad;

import dynamicmemorypartitioning.Haris.MemoryJob;
import dynamicmemorypartitioning.Haris.MemoryPartition;
import java.util.LinkedList;
import java.util.Queue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author Asad
 */
public class DynamicMemoryPartitioning extends Application {
    
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;
    Stage thestage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        thestage=primaryStage;
        //can now use the stage in other methods
       
    }
    
    /*public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnscene1)
            thestage.setScene(scene2);
        else
            thestage.setScene(scene1);
    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void dynamicFirstFit(LinkedList<MemoryJob> job, LinkedList<MemoryPartition> fixedpartition, Queue<MemoryJob> queue){
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
        LinkedList<MemoryPartition> dynamicpartition = new LinkedList();
        
        totalMemoryBlocks=0;
        for(int j=0;j<fixedpartition.size();j++)
            totalMemoryBlocks += fixedpartition.get(j).getMemorySize();
        
        freeMemory = totalMemoryBlocks;
        dynamicpartition.addLast(new MemoryPartition(freeMemory, false)); //creates a node for all free memory
       
        do{
            
            System.out.println("Time cycle: " + clock);

            while(freeMemory!=totalMemoryBlocks){
                if(dynamicpartition.get(i).getOccupied()){
                    int currentJobNum = dynamicpartition.get(i).getJobNum();
                    int currentProcessTime = job.get(currentJobNum).getProcessTime();
                    job.get(currentJobNum).setProcessTime(currentProcessTime--);
                    if(job.get(currentJobNum).getProcessTime() == 0){
                        dynamicpartition.get(i).setOccupied(false);
                        job.get(i).setJobDone(true);
                    }
                }
            }
        
            //while loop for when checking available memory.
            //while(freeMemory>=dynamicpartition.get(currentJobNum).getMemorySize())
            
            if(!queue.isEmpty()){

                for(i = 0;i<queue.size();i++){

                    System.out.println("Queue : " + queue.get(i).getJobNum() + " and " + queue.get(i).getJobSize());
    
                    for(int k = 0; k < memorypartition.size();k++){ //The loop control with for causes it to always execute.
                                                                    //How to always execute with while?
                        
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
