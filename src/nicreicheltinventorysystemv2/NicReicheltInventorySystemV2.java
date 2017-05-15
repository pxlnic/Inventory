/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.model.InHousePart;
import nicreicheltinventorysystemv2.model.Inventory;
import static nicreicheltinventorysystemv2.model.Inventory.getPartInv;
import nicreicheltinventorysystemv2.model.OutsourcedPart;
import nicreicheltinventorysystemv2.view_controller.MainScreenController;


/**
 *
 * @author nicre
 */

public class NicReicheltInventorySystemV2 extends Application {
//Creation of the window for the stage and scene
    Stage window;
    private AnchorPane MainScreenView;
    
    //RootLayout
    public void initMainScreen() throws IOException{
//Load parts overview
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NicReicheltInventorySystemV2.class.getResource("view_controller/MainScreen.fxml"));
        AnchorPane MainScreenView = (AnchorPane) loader.load();
    
//Setting the scene
        Scene scene = new Scene(MainScreenView);
        
//Showing the scene on the stage
        window.setScene(scene);
        window.show();
    }
    
    public void showMainScreen() throws IOException{
    // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NicReicheltInventorySystemV2.class.getResource("view_controller/MainScreen.fxml"));
        AnchorPane MainScreenView = (AnchorPane) loader.load();
        
    //Give Controller access to the Main Application
        MainScreenController controller = loader.getController();
        controller.setMainApp(this);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
//Setting the stage
        window = primaryStage;
        window.setTitle("Inventory Manager");
        
        initMainScreen();
        showMainScreen();
        
    //Adds parts to the parts ObservableArraylist    
        InHousePart part1 = new InHousePart();
        OutsourcedPart part2 = new OutsourcedPart();
        part1.setPartID(Inventory.getPartIDCount());
        part1.setPartName("Snow Tire");
        part1.setPartPrice(80.00);
        part1.setPartInStock(12);
        part1.setPartMin(8);
        part1.setPartMax(50);
        part1.setPartMachineID(12);
        part2.setPartID(Inventory.getPartIDCount());
        part2.setPartName("Fuel Pump");
        part2.setPartPrice(75.00);
        part2.setPartInStock(15);
        part2.setPartMin(5);
        part2.setPartMax(100);
        part2.setPartCompanyName("Mighty Parts");
        Inventory.addInvPart(part1);
        Inventory.addInvPart(part2);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
