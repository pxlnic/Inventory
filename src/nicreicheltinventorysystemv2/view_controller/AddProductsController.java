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
import javafx.stage.Modality;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.model.Inventory;
import nicreicheltinventorysystemv2.model.Part;
import static nicreicheltinventorysystemv2.model.Inventory.getPartInv;
import nicreicheltinventorysystemv2.model.Product;

/**
 * FXML Controller class
 *
 * @author nicre
 */
public class AddProductsController implements Initializable {
//Instance Variables
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    private String exceptionMessage = new String();
    private int productID;
    
//FXML Declarations
    @FXML private TextField AddProductsIDField;
    @FXML private TextField AddProductsNameField;
    @FXML private TextField AddProductsPriceField;
    @FXML private TextField AddProductsInvField;
    @FXML private TextField AddProductsMinField;
    @FXML private TextField AddProductsMaxField;
    @FXML private TextField AddProductDeletePartSearchField;
    @FXML private TextField AddProductAddPartSearchField;

//FXML Declarations for Inventory Part Table View
    @FXML private TableView<Part> AddProductsAddTableView;
    @FXML private TableColumn<Part, Integer> AddProductPartIDCol;
    @FXML private TableColumn<Part, String> AddProductPartNameCol;
    @FXML private TableColumn<Part, Integer> AddProductInvLevelCol;
    @FXML private TableColumn<Part, Double> AddProductPriceCol;

//FXML Declarations for Current Part Table View
    @FXML private TableView<Part> AddProductsDeleteTableView;
    @FXML private TableColumn<Part, Integer> AddProductCurrentPartIDCol;
    @FXML private TableColumn<Part, String> AddProductCurrentPartNameCol;
    @FXML private TableColumn<Part, Integer> AddProductCurrentInvCol;
    @FXML private TableColumn<Part, Double> AddProductCurrentPriceCol;
    
//Constructor
    public AddProductsController(){
    }
    
//FXML Button Handling
//Searches all parts to find search term
    @FXML void AddProductsSearchPartAddBtn(ActionEvent event) {
        System.out.println("Search Parts clicked");
        
        String searchPart = AddProductAddPartSearchField.getText();
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
            AddProductsAddTableView.setItems(tempProdList);
        }  
    }
    
//Clears the add parts search box and reset Tableview
    @FXML void AddProductsSearchPartClearBtn(ActionEvent event) {
        updatePartTableView();
        AddProductAddPartSearchField.setText("");
    }

//Adds selected part to the current parts list
    @FXML void AddProductsAddPartBtn(ActionEvent event) {
    //Adds selected Part to Current Parts Observable ArrayList to populate Observable ArrayList for new product
        Part part = AddProductsAddTableView.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        updateCurrentPartTableView();
        System.out.println("New part added - Part " + part.getPartName() + " was added.");
    }

//Searches the current parts list to find search term
    @FXML void AddProductsSearchPartDeleteBtn(ActionEvent event) {
        System.out.println("Search Parts clicked");
        
        String searchPart = AddProductDeletePartSearchField.getText();
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
            AddProductsDeleteTableView.setItems(tempProdList);
        }  
    }

//Clears the delete parts search box and resets TablView
    @FXML void AddProductsSearchPartDeleteClearBtn(ActionEvent event) {
        updateCurrentPartTableView();
        AddProductDeletePartSearchField.setText("");
    }

//Deletes selected part from the current parts list
    @FXML void AddProductsDeletePartBtn(ActionEvent event) {
    //Deletes selected Part from the Current Parts Observable ArrayList
        System.out.println("Delete Current Part Clicked");

        Part part = AddProductsDeleteTableView.getSelectionModel().getSelectedItem();
        
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

//Saves the new product to the Products Inventory Observable ArrayList
    @FXML void AddProductsSaveButtonClicked(ActionEvent event) throws IOException {
    //Saves the product to the Products Observable ArrayList in Inventory
        exceptionMessage = "";
    //Product ID is automatically set by using the Product Observable ArrayList Size

    //Get data from text fields to add to constuctor for part being added
        String productName = AddProductsNameField.getText();
        String productInv = AddProductsInvField.getText();
        String productPrice = AddProductsPriceField.getText();
        String productMin = AddProductsMinField.getText();
        String productMax = AddProductsMaxField.getText();
        
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

                exceptionMessage = "";
            }
            else{
                System.out.println("Product name: "+ productName);
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
                Inventory.addInvProduct(newProduct);

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
    
//Button to cancel the product without adding
    @FXML void AddProductsCancelClicked (ActionEvent event) throws IOException {
        System.out.println("Cancel Product Clicked");

    //Confirmation alert to validate user wants to delete the product
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed!");
        alert.setHeaderText("Confirm Product Delete!");
        alert.setContentText("Are you sure you want to delete product " + AddProductsNameField.getText() + "?");
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
    //This will initialize the Parts TableView as soon as the page loads
        AddProductPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        AddProductPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        AddProductInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        AddProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
  
    //This will initialize the Current Parts TableView as soon as the page loads
        AddProductCurrentPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        AddProductCurrentPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        AddProductCurrentInvCol.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        AddProductCurrentPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        
    //Update TableViews
        updatePartTableView();
        updateCurrentPartTableView();
        
    //Set Product ID
        productID = Inventory.getProductIDCount();
        AddProductsIDField.setText("Product ID autoset to: " + productID);
    }

//Update Add Part TableView
    public void updatePartTableView(){
        AddProductsAddTableView.setItems(getPartInv());
    }
    
//Update Current Parts TableView
    public void updateCurrentPartTableView(){
        AddProductsDeleteTableView.setItems(currentParts);
    }
}
