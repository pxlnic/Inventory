/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.view_controller;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.NicReicheltInventorySystemV2;
import nicreicheltinventorysystemv2.model.InHousePart;
import nicreicheltinventorysystemv2.model.OutsourcedPart;
import nicreicheltinventorysystemv2.model.Inventory;
import static nicreicheltinventorysystemv2.model.Inventory.removeInvPart;
import nicreicheltinventorysystemv2.model.Part;


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
    private String exceptionMessage = new String();
    //Part ID is automatically set by using the Parts Observable ArrayList Size
    private int partID;

//Controller Methods for buttons
//Radio Button Handling
    @FXML void AddPartsInHouseRadio(ActionEvent event) {
        System.out.println("Inhouse parts radio button clicked");
        isOutsourced=false;
        DynAddPartLabel.setText("Machine ID");
    }
    @FXML void AddPartsOutsourcedRadio(ActionEvent event) {
        System.out.println("Outsourced parts radio button clicked");
        isOutsourced=true;
        DynAddPartLabel.setText("Comp. Name");
    }
    
//Saves new part to Inventory Parts Observable ArrayList
    @FXML void AddPartsSaveClicked(ActionEvent event) throws IOException {
    //Saves the part to the Parts Observable ArrayList in Inventory    
        exceptionMessage = "";
        //Get data from text fields to add to constuctor for part being added
        String partName = AddPartsNameField.getText();
        String partInv = AddPartsInvField.getText();
        String partPrice = AddPartsPriceField.getText();
        String partMin = AddPartsMinField.getText();
        String partMax = AddPartsMaxField.getText();
        String partDyn = AddPartsDynField.getText();
        
    //Exception handler
        try{
            exceptionMessage = Part.isPartValid(partName, Integer.parseInt(partMin), Integer.parseInt(partMax), Integer.parseInt(partInv), Double.parseDouble(partPrice), exceptionMessage);
        //If Statement to throw error if min is greater then max
            if(exceptionMessage.length()>0){
            //Setup and show alert - Remaining errors
                Alert alert = new Alert(AlertType.INFORMATION);
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
            //Load Main Screen
                Parent partsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(partsSave);

            //Loads stage information from main file
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            //Load scene onto stage
                window.setScene(scene);
                window.show();
            }
        }
        catch(NumberFormatException e){
            //Errors out when fields are left blank that require text
                System.out.println("Fields are blank");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error Adding Part!");
                alert.setHeaderText("Error!");
                alert.setContentText("Fields cannot be left blank!");
                alert.showAndWait();
        }
    }
    
//Cancels new part and goes back to main screen
    @FXML void AddPartsCancelClicked (ActionEvent event) throws IOException {
        System.out.println("Cancel Part Clicked");
    
    //Confirmation alert to validate user wants to delete the product
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed!");
        alert.setHeaderText("Confirm Part Delete!");
        alert.setContentText("Are you sure you want to delete part " + AddPartsNameField.getText() + "?");
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partID = Inventory.getPartIDCount();
        AddPartsIDField.setText("Part ID autoset to: " + partID);
    }
}
