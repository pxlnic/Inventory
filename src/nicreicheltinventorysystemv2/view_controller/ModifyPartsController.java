/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.view_controller;

import static com.sun.deploy.util.ReflectionUtil.instanceOf;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.model.InHousePart;
import nicreicheltinventorysystemv2.model.Inventory;
import static nicreicheltinventorysystemv2.model.Inventory.getPartInv;
import nicreicheltinventorysystemv2.model.OutsourcedPart;
import nicreicheltinventorysystemv2.model.Part;
import static nicreicheltinventorysystemv2.view_controller.MainScreenController.partToModifyIndex;

/**
 * FXML Controller class
 *
 * @author nicre
 */
public class ModifyPartsController implements Initializable {
//Instance Variables
    private boolean isOutsourced;
    int partIndex = partToModifyIndex();
    private String exceptionMessage = new String();
    private int partID;
    
//FXML Declarations
    @FXML private TextField ModifyPartsIDField;
    @FXML private TextField ModifyPartsNameField;
    @FXML private TextField ModifyPartsInvField;
    @FXML private TextField ModifyPartsPriceField;
    @FXML private TextField ModifyPartsMinField;
    @FXML private TextField ModifyPartsDynField;
    @FXML private TextField ModifyPartsMaxField;
    @FXML private Label DynModifyPartLabel;
    @FXML private RadioButton ModifyPartsInHouseRadioButton;
    @FXML private RadioButton ModifyPartsOutsourcedRadioButton;

//Radio buttons to set part to In-House or Outsrouced
    @FXML void ModifyPartsOutsourcedRadio(ActionEvent event) {
        System.out.println("Outsourced parts radio button clicked");
        isOutsourced=true;
        DynModifyPartLabel.setText("Comp Name.");
    }
    @FXML void ModifyPartsInHouseRadio(ActionEvent event) {
        System.out.println("Inhouse parts radio button clicked");
        isOutsourced=false;
        DynModifyPartLabel.setText("Machine ID");
    }

//Cancels the modification of part and returns to main screen
    @FXML void ModifyPartsCancelClicked (ActionEvent event) throws IOException {
        System.out.println("Cancel Modify Part Clicked");

    //Confirmation alert to validate user wants to delete the product
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed!");
        alert.setHeaderText("Confirm Part Delete!");
        alert.setContentText("Are you sure you want to cancel update of part " + ModifyPartsNameField.getText() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        
    //If statement determines if part should be removed or nothing happens based on user selection
        if(result.get() == ButtonType.OK){
        //Product is removed based on selected item
            System.out.println("Part add has been cancelled.");
        //Load Main Screen
            Parent partsCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(partsCancel);
        
        //Loads stage information from main file
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        //Load scene onto stage
            window.setScene(scene);
            window.show();
        }
        else{
            System.out.println("You clicked cancel. Please complete part info.");    
        }
    }

    @FXML void ModifyPartsSaveClicked (ActionEvent event) throws IOException {
    //Saves the part to the Parts Observable ArrayList in Inventory
        
        //Get data from text fields to add to constuctor for part being added
        String partName = ModifyPartsNameField.getText();
        String partInv = ModifyPartsInvField.getText();
        String partPrice = ModifyPartsPriceField.getText();
        String partMin = ModifyPartsMinField.getText();
        String partMax = ModifyPartsMaxField.getText();
        String partDyn = ModifyPartsDynField.getText();
        
    //Exception handler
    //min, max, inv, price, message
        exceptionMessage = Part.isPartValid(Integer.parseInt(partMin), Integer.parseInt(partMax), Integer.parseInt(partInv), Double.parseDouble(partPrice), exceptionMessage);
    //If Statement to throw error if min is greater then max
        if(exceptionMessage.length()>0){
        //Setup and show alert - Min > Max
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Part!");
            alert.setHeaderText("Error!");
            alert.setContentText(exceptionMessage);
            alert.showAndWait();
        }
        else{
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
                System.out.println("Update Part Clicked - Inhouse part " + partName + " was modified in parts list");
                Inventory.updatePart(partIndex, iPart);
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
                System.out.println("Update Part Clicked - Outsourced part " + partName + " was modified in parts list");
                Inventory.updatePart(partIndex, oPart);;
            }
       
        //Load Main Screen
            Parent modifyProductCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(modifyProductCancel);
        
        //Loads stage information from main file
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        //Load scene onto stage
            window.setScene(scene);
            window.show();
        }
   }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //Set Text Fields to be the part info
        Part part = getPartInv().get(partIndex);
        partID = getPartInv().get(partIndex).getPartID();
        System.out.println("Part ID " + partID + " is available.");
        ModifyPartsIDField.setText("Part ID autoset to: " + partID);
        ModifyPartsNameField.setText(part.getPartName());
        ModifyPartsInvField.setText(Integer.toString(part.getPartInStock()));
        ModifyPartsPriceField.setText(Double.toString(part.getPartPrice()));
        ModifyPartsMinField.setText(Integer.toString(part.getPartMin()));
        ModifyPartsMaxField.setText(Integer.toString(part.getPartMax()));
        if(part instanceof InHousePart){
            ModifyPartsDynField.setText(Integer.toString(((InHousePart)getPartInv().get(partIndex)).getPartMachineID()));
            DynModifyPartLabel.setText("Machine ID");
            ModifyPartsInHouseRadioButton.setSelected(true);
            
        }else{
            ModifyPartsDynField.setText(((OutsourcedPart)getPartInv().get(partIndex)).getPartCompanyName());
            DynModifyPartLabel.setText("Comp. Name");
            ModifyPartsOutsourcedRadioButton.setSelected(true);
        }
    }    
}
