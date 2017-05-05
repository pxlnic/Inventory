/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author nicre
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    //Main Screen Exit Button method.
    @FXML
    void MainExitClick (ActionEvent event) {
        System.out.println("Exited application");
        System.exit(0);
    }
    
    //***Methods to switch to appropriate screens***
    //Main Screen Add Parts button method
    @FXML 
    void MainAddPartsClick (ActionEvent event) throws IOException{
        System.out.println("Add Parts clicked");
        
        //Load Add Parts Screen
        Parent addParts = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
        Scene scene = new Scene(addParts);
        
        //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    
    //Main Screen Add Products button method.
    @FXML
    void MainAddProductsClick (ActionEvent event) throws IOException {
        System.out.println("Add Products Clicked");
        
        //Load Add Products Screen
        Parent addProducts = FXMLLoader.load(getClass().getResource("AddProducts.fxml"));
        Scene scene = new Scene(addProducts);
        
        //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    
    //Main Screen Modify Parts button method.
    @FXML
    void MainModifyPartsClick (ActionEvent event) throws IOException {
        System.out.println("Modify Parts Clicked");
        
        //Load Add Products Screen
        Parent modifyParts = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
        Scene scene = new Scene(modifyParts);
        
        //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    
    //Main Screen Modify Parts button method. 
    @FXML
    void MainModifyProductsClick (ActionEvent event) throws IOException {
        System.out.println("Modify Products Clicked");
        
        //Load Add Products Screen
        Parent modifyProducts = FXMLLoader.load(getClass().getResource("ModifyProducts.fxml"));
        Scene scene = new Scene(modifyProducts);
        
        //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    @FXML
    void MainSearchProductsBtn(ActionEvent event) throws IOException{
        System.out.println("Search Products clicked");
    }
    @FXML
    void MainDeleteProductsClick(ActionEvent event) throws IOException{
        System.out.println("Delete Product clicked");
    }
    @FXML
    void MainPartsSearchBtn(ActionEvent event) throws IOException{
        System.out.println("Search Parts clicked");
    }
    @FXML
    void MainDeletePartsClick(ActionEvent event) throws IOException{
        System.out.println("Delete Part clicked");
    }
    
    //Control Methods to manipulate date
    
    //Initialize Interface
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
