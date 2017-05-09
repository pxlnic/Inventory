/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import nicreicheltinventorysystemv2.model.Part;
import nicreicheltinventorysystemv2.view_controller.MainScreenController;


/**
 *
 * @author nicre
 */

public class NicReicheltInventorySystemV2 extends Application {
//Creation of the window for the stage and scene
    Stage window;
    
//Creation of Obersaveable list for Main Screen
    private ObservableList<Part> partData = FXCollections.observableArrayList();
    
//Method to get part data for the observable arraylist
    public ObservableList<Part> getPartData() {
        return partData;
    }
    
    /*public void showPartsOverview() throws IOException{
    //Load parts overview
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NicReicheltInventorySystemV2.class.getResource("view/MainScreen.fxml"));
        AnchorPane MainScreenView = (AnchorPane) loader.load();
    
    //Give Controller access to the Main Application
        MainScreenController controller = loader.getController();
        controller.setMainApp(this);
    }*/
    
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
