/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 *
 * @author nicre
 */

public class NicReicheltInventorySystemV2 extends Application {
    
    Stage window;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Setting the stage
        window = primaryStage;
        Parent mainScreen = FXMLLoader.load(getClass().getResource("view_controller/MainScreen.fxml"));
        
        //Setting the scene
        Scene scene = new Scene(mainScreen);
        
        //Showing the scene on the stage
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
