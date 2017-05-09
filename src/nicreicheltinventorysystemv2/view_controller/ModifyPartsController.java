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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.model.InHousePart;
import nicreicheltinventorysystemv2.model.OutsourcedPart;

/**
 * FXML Controller class
 *
 * @author nicre
 */
public class ModifyPartsController implements Initializable {

    @FXML private TextField ModifyPartsIDField;
    @FXML private TextField ModifyPartsNameField;
    @FXML private TextField ModifyPartsInvField;
    @FXML private TextField ModifyPartsPriceField;
    @FXML private TextField ModifyPartsMinField;
    @FXML private TextField ModifyPartsDynField;
    @FXML private TextField ModifyPartsMaxField;
          private boolean isOutsourced;
    
    @FXML
    void ModifyPartsCancelClicked (ActionEvent event) throws IOException {
        System.out.println("Cancel Modify Part Clicked");
        
    //Load Add Products Screen
        Parent modifyProductCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(modifyProductCancel);
        
    //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    
   void ModifyPartsSaveClicked (ActionEvent event) throws IOException {
       System.out.println("Save MOdify Part Clicked");
       
    //Get data from text fields to add to constuctor for part being added
        String partID = ModifyPartsIDField.getText();
        String partName = ModifyPartsNameField.getText();
        String partInv = ModifyPartsInvField.getText();
        String partPrice = ModifyPartsPriceField.getText();
        String partMin = ModifyPartsMinField.getText();
        String partMax = ModifyPartsMaxField.getText();
        String partDyn =ModifyPartsDynField.getText();
        
        isOutsourced=false;
        
    //Construct part - parse data gathered above in constructor parameters
    //If statement used to determine if creating an Inhouse or Outsourced part
        if(isOutsourced = false){
    //Create Inhouse part
            InHousePart iPart;
            /*iPart = new InHousePart(partName,Integer.parseInt(partID),Double.parseDouble(partPrice),Integer.parseInt(partInv),
                                   Integer.parseInt(partMin),Integer.parseInt(partMax),Integer.parseInt(partDyn));*/
    //Console output to verify Inhouse part was added and validate part name
            System.out.println("Save Part Clicked - Inhouse part " + partName + " was modified in parts list");
        }
        else{
    //Create Outsourced part
            OutsourcedPart oPart;
            /*oPart = new OutsourcedPart(partName,Integer.parseInt(partID),Double.parseDouble(partPrice),Integer.parseInt(partInv),
                                      Integer.parseInt(partMin),Integer.parseInt(partMax),partDyn);*/
    //Console output to verify Outsourced part was added and validate part name
            System.out.println("Save Part Clicked - Outsourced part " + partName + " was modified in parts list");
        }
     
    //Add object to ArrayList of parts
       
    //Load Add Products Screen
        Parent modifyProductCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(modifyProductCancel);
        
    //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    //Load scene onto stage
        window.setScene(scene);
        window.show();
   }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
