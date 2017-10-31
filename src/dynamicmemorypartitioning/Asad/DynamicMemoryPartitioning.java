/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicmemorypartitioning.Asad;

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
    
    public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnscene1)
            thestage.setScene(scene2);
        else
            thestage.setScene(scene1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
