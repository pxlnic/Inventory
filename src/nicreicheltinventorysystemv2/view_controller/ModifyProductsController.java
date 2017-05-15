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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.model.Inventory;
import static nicreicheltinventorysystemv2.model.Inventory.getPartInv;
import static nicreicheltinventorysystemv2.model.Inventory.getProductInv;
import nicreicheltinventorysystemv2.model.Part;
import nicreicheltinventorysystemv2.model.Product;
import static nicreicheltinventorysystemv2.view_controller.MainScreenController.productToModifyIndex;

/**
 * FXML Controller class
 *
 * @author nicre
 */
public class ModifyProductsController implements Initializable {
//Instance Variables
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    private int productIndex = productToModifyIndex();
    private String exceptionMessage = new String();
    private int productID;

//FXML Declarations
    @FXML private AnchorPane ModifyProductView;
    @FXML private TextField ModifyProductsIDField;
    @FXML private TextField ModifyProductsMinField;
    @FXML private TextField ModifyProductsMaxField;
    @FXML private TextField ModifyProductsInvField;
    @FXML private TextField ModifyProductsNameField;
    @FXML private TextField ModifyProductsPriceField;
    @FXML private TextField ModifyProductAddPartSearchField;
    @FXML private TextField ModifyProductDeletePartSearchField; 

//FXML Declarations for Inventory Part Table View
    @FXML private TableView<Part> ModifyProductAddTableView;
    @FXML private TableColumn<Part, Integer> ModifyProductPartIDCol;
    @FXML private TableColumn<Part, String> ModifyProductPartNameCol;
    @FXML private TableColumn<Part, Integer> ModifyProductPartInvCol;
    @FXML private TableColumn<Part, Double> ModifyProductPartPriceCol;

//FXML Declarations for Current Part Table View
    @FXML private TableView<Part> ModifyProductDeleteTableView;
    @FXML private TableColumn<Part, Integer> ModifyProductCurrentPartIDCol;
    @FXML private TableColumn<Part, String> ModifyProductCurrentPartNameCol;
    @FXML private TableColumn<Part, Integer> ModifyProductCurrentPartInvCol;
    @FXML private TableColumn<Part, Double> ModifyProductCurrentPartPriceCol;

//FXML Button Handlers
//Search all parts to add to the current parts list
    @FXML void ModifyProductsSearchPartAddBtn(ActionEvent event) {
        System.out.println("Search Parts clicked");
        
        String searchPart = ModifyProductAddPartSearchField.getText();
        int partIndex = -1;
        
    //If statement validates if search term is a valid Product ID or Name and returns the product ID or an error
        if(Inventory.lookupPart(searchPart) == -1){
        //Alert that part is a part of a product and cannot be removed
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error!");
            alert.setHeaderText("Part not found");
            alert.setContentText("The search term entered does not match any Part!");
            alert.showAndWait();
        }
        else{
            partIndex = Inventory.lookupPart(searchPart);
            Part tempPart = Inventory.getPartInv().get(partIndex);

        //New Observable ArrayList created to hold the search value]
            ObservableList<Part> tempProdList = FXCollections.observableArrayList();
            tempProdList.add(tempPart);
            ModifyProductAddTableView.setItems(tempProdList);
        } 
    }

//Clears Add part search box and reset TableView to all Inventory Parts
    @FXML void ModifyProductsSearchPartClearBtn(ActionEvent event) {
        updatePartTableView();
        ModifyProductAddPartSearchField.setText("");
    } 

//Add part to current parts list
    @FXML void ModifyProductsAddButton(ActionEvent event) {
        Part part = ModifyProductAddTableView.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        updateCurrentPartTableView();
        System.out.println("New part added - Part " + part.getPartName() + " was added.");
    }

//Search currently added parts to remove from the current parts list
    @FXML void ModifyProductsSearchPartDeleteBtn(ActionEvent event) {
        System.out.println("Search Parts clicked");
        
        String searchPart = ModifyProductDeletePartSearchField.getText();
        int partIndex = -1;
        
    //If statement validates if search term is a valid Product ID or Name and returns the product ID or an error
        if(Inventory.lookupPart(searchPart) == -1){
        //Alert that part is a part of a product and cannot be removed
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error!");
            alert.setHeaderText("Part not found");
            alert.setContentText("The search term entered does not match any Part!");
            alert.showAndWait();
        }
        else{
            partIndex = Inventory.lookupPart(searchPart);
            Part tempPart = Inventory.getPartInv().get(partIndex);

        //New Observable ArrayList created to hold the search value]
            ObservableList<Part> tempProdList = FXCollections.observableArrayList();
            tempProdList.add(tempPart);
            ModifyProductDeleteTableView.setItems(tempProdList);
        } 
    }

//Clears Delete part search box and resets TableView to all current parts
    @FXML void ModifyProductsSearchCurPartClearBtn(ActionEvent event) {
        updateCurrentPartTableView();
        ModifyProductDeletePartSearchField.setText("");
    }

//Deletes selected parts from current parts list
    @FXML void ModifyProductsDeleteButton(ActionEvent event) {
    //Deletes selected Part from the Current Parts Observable ArrayList
        System.out.println("Delete Current Part Clicked");

        Part part = ModifyProductDeleteTableView.getSelectionModel().getSelectedItem();
        
    //Confirmation alert to validate user wants to delete the product
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed!");
        alert.setHeaderText("Confirm Current Part Delete!");
        alert.setContentText("Are you sure you want to delete part " + part.getPartName() + " from current parts?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            System.out.println("Part deleted from current parts list!");
            currentParts.remove(part);
        }else{
            System.out.println("You clicked cancel!");
        }
    }

//Save product to Inventory Proucts List
    @FXML void ModifyProductsSaveButtonClicked(ActionEvent event) throws IOException {
    //Saves the product to the Products Observable ArrayList in Inventory
        exceptionMessage = "";

    //Get data from text fields to add to constuctor for part being added
        String productName = ModifyProductsNameField.getText();
        String productInv = ModifyProductsInvField.getText();
        String productPrice = ModifyProductsPriceField.getText();
        String productMin = ModifyProductsMinField.getText();
        String productMax = ModifyProductsMaxField.getText();
        
    //Exception handler
        try{
        //min, max, inv, price, message
            exceptionMessage = Product.isProductValid(productName, Integer.parseInt(productMin), Integer.parseInt(productMax), Integer.parseInt(productInv), 
                                                      Double.parseDouble(productPrice),currentParts, exceptionMessage);
        //If Statement to throw error if min is greater then max
            if(exceptionMessage.length()>0){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Product!");
                alert.setHeaderText("Error!");
                alert.setContentText(exceptionMessage);
                alert.showAndWait();
            }
            else{
            //Construct part - parse data gathered above in constructor parameters
                Product newProduct = new Product();

            //Set part data with calls to setter methods.
                newProduct.setProductID(productID);
                newProduct.setProductName(productName);
                newProduct.setProductPrice(Double.parseDouble(productPrice));
                newProduct.setProductInStock(Integer.parseInt(productInv));
                newProduct.setProductMin(Integer.parseInt(productMin));
                newProduct.setProductMax(Integer.parseInt(productMax));
                newProduct.setProductParts(currentParts);

            //Console output to verify Inhouse part was added and validate part name
                System.out.println("Save Product Clicked - Product " + productName + " was added to products list");
                System.out.println("There are " + currentParts.size() + " parts in this product.");
                Inventory.updateProduct(productIndex, newProduct);

            //Close screen and reload main screen
            //Load Main Screen
                Parent productsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(productsSave);

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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Part!");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be left blank!");
            alert.showAndWait();
        }
    }

//Cancel produt modification button handler
    @FXML void ModifyProductsCancelClicked (ActionEvent event) throws IOException {
        System.out.println("Cancel Modify Product Clicked");

    //Confirmation alert to validate user wants to delete the product
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed!");
        alert.setHeaderText("Confirm Product Delete!");
        alert.setContentText("Are you sure you want to cancel update of product " + ModifyProductsNameField.getText() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        
    //If statement determines if part should be removed or nothing happens based on user selection
        if(result.get() == ButtonType.OK){
        //Product is removed based on selected item
            System.out.println("Product update has been cancelled.");
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
            System.out.println("You clicked cancel. Please complete product info.");    
        }
    }

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //Set text fields
        Product product = getProductInv().get(productIndex);
        productID = getProductInv().get(productIndex).getProductID();
        System.out.println("Product ID " + productID + " is available.");
        ModifyProductsIDField.setText("Part ID autoset to: " + productID);
        ModifyProductsNameField.setText(product.getProductName());
        ModifyProductsInvField.setText(Integer.toString(product.getProductInStock()));
        ModifyProductsPriceField.setText(Double.toString(product.getProductPrice()));
        ModifyProductsMinField.setText(Integer.toString(product.getProductMin()));
        ModifyProductsMaxField.setText(Integer.toString(product.getProductMax()));
        currentParts = product.getProductParts();
        
    //This will initialize the Parts TableView as soon as the page loads
        ModifyProductPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        ModifyProductPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        ModifyProductPartInvCol.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        ModifyProductPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
  
    //This will initialize the Current Parts TableView as soon as the page loads
        ModifyProductCurrentPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        ModifyProductCurrentPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        ModifyProductCurrentPartInvCol.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        ModifyProductCurrentPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
    
    //Update TableViews
        updatePartTableView();
        updateCurrentPartTableView();
    }    
    
//Update Add Part TableView
    public void updatePartTableView(){
        ModifyProductAddTableView.setItems(getPartInv());
    }
    
//Update Current Parts TableView
    public void updateCurrentPartTableView(){
        ModifyProductDeleteTableView.setItems(currentParts);
    }
    
}
