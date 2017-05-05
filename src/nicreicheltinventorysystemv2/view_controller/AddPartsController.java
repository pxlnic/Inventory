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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.model.InHousePart;
import nicreicheltinventorysystemv2.model.OutsourcedPart;

/**
 * FXML Controller class
 *
 * @author nicre
 */
public class AddPartsController implements Initializable {
    
//Instance Variables
    @FXML private AnchorPane AddPartView;
    @FXML private RadioButton AddPartInHouseRadioBtn;
    @FXML private RadioButton AddPartOutsourcedRadioBtn;
    @FXML private Label DynAddPartLabel;
    @FXML private TextField AddPartsIDField;
    @FXML private TextField AddPartsNameField;
    @FXML private TextField AddPartsInvField;
    @FXML private TextField AddPartsPriceField;
    @FXML private TextField AddPartsMinField;
    @FXML private TextField AddPartsDynField;
    @FXML private TextField AddPartsMaxField;
          private boolean isOutsourced;

//Controller Methods for buttons
    @FXML
    void AddPartsInHouseRadio(ActionEvent event) {
        
    }
    @FXML
    void AddPartsOutsourcedRadio(ActionEvent event) {

    }
    @FXML
    void AddPartsSaveClicked(ActionEvent event) throws IOException {
    
    //Get data from text fields to add to constuctor for part being added
        String partID = AddPartsIDField.getText();
        String partName = AddPartsNameField.getText();
        String partInv = AddPartsInvField.getText();
        String partPrice = AddPartsPriceField.getText();
        String partMin = AddPartsMinField.getText();
        String partMax = AddPartsMaxField.getText();
        String partDyn = AddPartsDynField.getText();
        
        isOutsourced=false;
        
    //Construct part - parse data gathered above in constructor parameters
    //If statement used to determine if creating an Inhouse or Outsourced part
        if(isOutsourced = false){
    //Create Inhouse part
            InHousePart iPart;
            iPart = new InHousePart(partName,Integer.parseInt(partID),Double.parseDouble(partPrice),Integer.parseInt(partInv),
                                   Integer.parseInt(partMin),Integer.parseInt(partMax),Integer.parseInt(partDyn));
    //Console output to verify Inhouse part was added and validate part name
            System.out.println("Save Part Clicked - Inhouse part " + partName + " was added to parts list");
        }
        else{
    //Create Outsourced part
            OutsourcedPart oPart;
            oPart = new OutsourcedPart(partName,Integer.parseInt(partID),Double.parseDouble(partPrice),Integer.parseInt(partInv),
                                      Integer.parseInt(partMin),Integer.parseInt(partMax),partDyn);
    //Console output to verify Outsourced part was added and validate part name
            System.out.println("Save Part Clicked - Outsourced part " + partName + " was added to parts list");
        }
        
    //Add object to ArrayList of parts
        
    //Close screen and reload main screen
    //Load Add Products Screen
        Parent partsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(partsSave);
        
    //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    @FXML
    void AddPartsCancelClicked (ActionEvent event) throws IOException {
        System.out.println("Cancel Part Clicked");
        
    //Load Main Screen
        Parent partsCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(partsCancel);
        
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
