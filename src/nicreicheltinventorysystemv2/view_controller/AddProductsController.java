/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.view_controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    
//FXML Declarations
    @FXML private TextField AddProductsIDField;
    @FXML private TextField AddProductsMinField;
    @FXML private TextField AddProductsMaxField;
    @FXML private TextField AddProductsInvField;
    @FXML private TextField AddProductsNameField;
    @FXML private TextField AddProductsPriceField;

//FXML Declarations for Inventory Part Table View
    @FXML private AnchorPane AddProductView;
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
    
    @FXML
    void AddProductsSearchPartAddBtn(ActionEvent event) {
    //Searches for the Part ID/Name to be added to the Current Parts TableView from all Parts.
    //Pull text from the Add Search Sox
    
    }
    
    @FXML
    void AddProductsAddPartBtn(ActionEvent event) {
    //Adds selected Part to Current Parts Observable ArrayList to populate Observable ArrayList for new product
    
    }

    @FXML
    void AddProductsSearchPartDeleteBtn(ActionEvent event) {
    //Searches for the Part ID/Name to be deleted from the Current Parts Observable ArrayList
    //Pull text from the Delete Search Box
    
    }
    
    @FXML
    void AddProductsDeletePartBtn(ActionEvent event) {
    //Deletes selected Part from the Current Parts Observable ArrayList
    
    }

//Saves the new product to the Products Inventory Observable ArrayList
    @FXML
    void AddProductsSaveButtonClicked(ActionEvent event) throws IOException {
    //Saves the product to the Products Observable ArrayList in Inventory
    //Product ID is automatically set by using the Product Observable ArrayList Size
        int productIDCount = Inventory.getProductInv().size();
        int productID = productIDCount+1;

    //Get data from text fields to add to constuctor for part being added
        String productName = AddProductsNameField.getText();
        String productInv = AddProductsInvField.getText();
        String productPrice = AddProductsPriceField.getText();
        String productMin = AddProductsMinField.getText();
        String productMax = AddProductsMaxField.getText();
        
    //Construct part - parse data gathered above in constructor parameters
        Product newProduct = new Product();
    
    //Set part data with calls to setter methods.
        newProduct.setProductID(productID);
        newProduct.setProductName(productName);
        newProduct.setProductPrice(Double.parseDouble(productPrice));
        newProduct.setProductInStock(Integer.parseInt(productInv));
        newProduct.setProductMin(Integer.parseInt(productMin));
        newProduct.setProductMax(Integer.parseInt(productMax));
        //Create method to add part to parts arraylist (need to first get data from the observable arraylist currentParts
            
    //Console output to verify Inhouse part was added and validate part name
        System.out.println("Save Product Clicked - Product " + productName + " was added to products list");
        Inventory.addInvProduct(newProduct);
        
    //Close screen and reload main screen
    //Load Add Products Screen
        Parent productsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(productsSave);
        
    //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    
//Button to cancel the product without adding
    @FXML
    void AddProductsCancelClicked (ActionEvent event) throws IOException {
        System.out.println("Cancel Product Clicked");
        
    //Load Add Products Screen
        Parent productCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(productCancel);
        
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
    //This will initialize the Parts TableView as soon as the page loads
        AddProductPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        AddProductPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        AddProductInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        AddProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        updatePartTableView();
        int productIDCount = Inventory.getProductInv().size()+1;
        String productID = Integer.toString(productIDCount);
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
