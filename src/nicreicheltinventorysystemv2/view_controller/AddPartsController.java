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
import nicreicheltinventorysystemv2.NicReicheltInventorySystemV2;
import nicreicheltinventorysystemv2.model.InHousePart;
import nicreicheltinventorysystemv2.model.OutsourcedPart;
import nicreicheltinventorysystemv2.model.Inventory;


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
          
    //Instance Variables
    private boolean isOutsourced;
    private NicReicheltInventorySystemV2 mainApp;

//Controller Methods for buttons
    @FXML
    void AddPartsInHouseRadio(ActionEvent event) {
        System.out.println("Inhouse parts radio button clicked");
        isOutsourced=false;
        DynAddPartLabel.setText("Machine ID");
    }
    @FXML
    void AddPartsOutsourcedRadio(ActionEvent event) {
        System.out.println("Outsourced parts radio button clicked");
        isOutsourced=true;
        DynAddPartLabel.setText("Comp. Name");
    }
    
//Saves new part to Inventory Parts Observable ArrayList
    @FXML
    void AddPartsSaveClicked(ActionEvent event) throws IOException {
    //Saves the part to the Parts Observable ArrayList in Inventory
    //Part ID is automatically set by using the Parts Observable ArrayList Size    
        int partIDCount = Inventory.getPartInv().size();
        int partID = partIDCount+1;
        
        //Get data from text fields to add to constuctor for part being added
        String partName = AddPartsNameField.getText();
        String partInv = AddPartsInvField.getText();
        String partPrice = AddPartsPriceField.getText();
        String partMin = AddPartsMinField.getText();
        String partMax = AddPartsMaxField.getText();
        String partDyn = AddPartsDynField.getText();
        
    //Construct part - parse data gathered above in constructor parameters
    //If statement used to determine if creating an Inhouse or Outsourced part
        if(isOutsourced == false){
    //Create Inhouse part
           InHousePart iPart = new InHousePart();
    //Set part data with calls to setter methods.
           iPart.setPartID(partID);
           iPart.setPartName(partName);
           iPart.setPartPrice(Double.parseDouble(partPrice));
           iPart.setPartInStock(Integer.parseInt(partInv));
           iPart.setPartMin(Integer.parseInt(partMin));
           iPart.setPartMax(Integer.parseInt(partMax));
           iPart.setPartMachineID(Integer.parseInt(partDyn));
            
    //Console output to verify Inhouse part was added and validate part name
            System.out.println("Save Part Clicked - Inhouse part " + partName + " was added to parts list");
            Inventory.addInvPart(iPart);
        }
        else{
    //Create Outsourced part
            OutsourcedPart oPart = new OutsourcedPart();
    //Set part data with calls to setter methods.
           oPart.setPartID(partID);
           oPart.setPartName(partName);
           oPart.setPartPrice(Double.parseDouble(partPrice));
           oPart.setPartInStock(Integer.parseInt(partInv));
           oPart.setPartMin(Integer.parseInt(partMin));
           oPart.setPartMax(Integer.parseInt(partMax));
           oPart.setPartCompanyName(partDyn);
    //Console output to verify Outsourced part was added and validate part name
            System.out.println("Save Part Clicked - Outsourced part " + partName + " was added to parts list");
            Inventory.addInvPart(oPart);
        }
        
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
    
//Saves new part to Inventory Parts Observable ArrayList
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
        int partIDCount = Inventory.getPartInv().size()+1;
        String partID = Integer.toString(partIDCount);
        AddPartsIDField.setText("Part ID autoset to: " + partID);
    }    
}
