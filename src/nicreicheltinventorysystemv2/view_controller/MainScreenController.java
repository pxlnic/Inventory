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
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nicreicheltinventorysystemv2.NicReicheltInventorySystemV2;
import nicreicheltinventorysystemv2.model.Part;
import static nicreicheltinventorysystemv2.model.Inventory.getPartInv;
import static nicreicheltinventorysystemv2.model.Inventory.getProductInv;
import static nicreicheltinventorysystemv2.model.Inventory.removeInvPart;
import static nicreicheltinventorysystemv2.model.Inventory.removeInvProduct;
import static nicreicheltinventorysystemv2.model.Inventory.validatePartDelete;
import nicreicheltinventorysystemv2.model.Product;

/**
 *
 * @author nicre
 */
public class MainScreenController implements Initializable {

//FXML Declarations    
//Anchorpane
    @FXML private AnchorPane rootPane;
    
//Part TableView Declarations
    @FXML private TableView<Part> MainPartsTableView;
    @FXML private TableColumn<Part, Integer> MainPartIDCol;
    @FXML private TableColumn<Part, String> MainPartNameCol;
    @FXML private TableColumn<Part, Integer> MainPartInvCol;
    @FXML private TableColumn<Part, Double> MainPartPriceCol;

//Product TableView Declarations
    @FXML private TableView<Product> MainProductsTableView;
    @FXML private TableColumn<Product, Integer> MainProductIDCol;
    @FXML private TableColumn<Product, String> MainProductNameCol;
    @FXML private TableColumn<Product, Integer> MainProductInvCol;
    @FXML private TableColumn<Product, Double> MainProductPriceCol;

//Search Boxes
    @FXML private TextField MainPartsSearchField;
    @FXML private TextField MainProductsSearchField;

//Instance Variables
    private NicReicheltInventorySystemV2 mainApp;
    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    private static int modifyProductIndex;
    

    public static int partToModifyIndex(){
        return modifyPartIndex;
    }
    public static int productToModifyIndex(){
        return modifyProductIndex;
    }
    
//Constructor
    public MainScreenController(){
    }

//Main Screen Exit Button handler
    @FXML void MainExitClick (ActionEvent event) {
        System.out.println("Exit clicked!");

  //Confirmation alert to validate user wants to delete the product
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed!");
        alert.setHeaderText("Confirm Exit!");
        alert.setContentText("Are you sure you want to exit application?");
        Optional<ButtonType> result = alert.showAndWait();
        
    //If statement determines if part should be removed or nothing happens based on user selection
        if(result.get() == ButtonType.OK){
        //Product is removed based on selected item
            System.out.println("Application exited!");
        //Exit application
            System.exit(0);
        }
        else{
            System.out.println("You clicked cancel. Please complete part info.");    
        }
    }
    
//***Handlers to switch to appropriate screens***
//Main Screen Add Parts button handler
    @FXML  void MainAddPartsClick (ActionEvent event) throws IOException{
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
    
//Main Screen Add Products button handler
    @FXML void MainAddProductsClick (ActionEvent event) throws IOException {
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
    
//Main Screen Modify Parts button handler
    @FXML void MainModifyPartsClick (ActionEvent event) throws IOException {
        System.out.println("Modify Parts Clicked");
        
    //Set part to modify
        modifyPart = MainPartsTableView.getSelectionModel().getSelectedItem();
        modifyPartIndex = getPartInv().indexOf(modifyPart);
        
    //Load Modify Parts Screen
        Parent modifyParts = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
        Scene scene = new Scene(modifyParts);
        
    //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    //Load scene onto stage
        window.setScene(scene);
        window.show();
    }
    
//Main Screen Modify Parts button handler 
    @FXML void MainModifyProductsClick (ActionEvent event) throws IOException {
        System.out.println("Modify Products Clicked");

    //Set part to modify
        modifyProduct = MainProductsTableView.getSelectionModel().getSelectedItem();
        modifyProductIndex = getProductInv().indexOf(modifyProduct);
        System.out.println("Product ID: " + modifyProductIndex+1 + " is ready to modify.");

    //Load Modify Products Screen
        Parent modifyProducts = FXMLLoader.load(getClass().getResource("ModifyProducts.fxml"));
        Scene scene = new Scene(modifyProducts);
        
    //Loads stage information from main file
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    //Load scene onto stage
        window.setScene(scene);
        window.show();
    }

//***Handlers to search and delete parts/product***
//Main Screen Search Product Button handler
    @FXML void MainSearchProductsBtn(ActionEvent event) throws IOException{
        System.out.println("Search Products clicked");
    }

//Main Screen Delete Product Button handler
    @FXML void MainDeleteProductsClick(ActionEvent event) throws IOException{
    //Console output confirm delete button was clicked
        System.out.println("Delete Product clicked");

    //Product is gathered from selection in TableView
        Product product = MainProductsTableView.getSelectionModel().getSelectedItem();
        
    //Confirmation alert to validate user wants to delete the product
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Product Delete!");
        alert.setHeaderText("Confirm?");
        alert.setContentText("Are you sure you want to delete product " + product.getProductName() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        
    //If statement to prevent deletion if user clicks cancel
        if (result.get() == ButtonType.OK){
        //Product is removed based on selected item
            removeInvProduct(product);
            updateProductTableView();
            System.out.println("Product removed - Product " + product.getProductName() + " was removed.");
        }
        else {
            System.out.println("Product not removed - Product " + product.getProductName() + " was not removed.");
        }
    }

//Mains Screen Search Part Button handler
    @FXML void MainPartsSearchBtn(ActionEvent event) throws IOException{
        System.out.println("Search Parts clicked");
        
        
    }

//Main Screen Delete Part Button handler
    @FXML void MainDeletePartsClick(ActionEvent event) throws IOException{
   //Console output confirm delete button was clicked
        System.out.println("Delete Part clicked");

    //Product is gathered from selection in TableView
        Part part = MainPartsTableView.getSelectionModel().getSelectedItem();
    
    //Verify if selected Part is a part of a product
        if(validatePartDelete(part)){
        //Alert that part is a part of a product and cannot be removed
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Part Delete Error!");
            alert.setHeaderText("Part cannot be removed!");
            alert.setContentText("This part is used in a product and cannot be removed!");
            alert.showAndWait();
       }
        else{
        //Confirmation alert to validate user wants to delete the product
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Confirm Product Delete!");
            alert.setHeaderText("Confirm?");
            alert.setContentText("Are you sure you want to delete part " + part.getPartName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        //If statement determines if part should be removed or nothing happens based on user selection
            if(result.get() == ButtonType.OK){
            //Product is removed based on selected item
            removeInvPart(part);
            updatePartTableView();
            System.out.println("Part removed - Part " + part.getPartName() + " was removed.");
            }
            else{
                System.out.println("Part not removed - Part " + part.getPartName() + " was not removed.");    
            }
        }
    }
    
//Initialize Interface
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //Initializes the Parts TableView as soon as the page loads
        MainPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        MainPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        MainPartInvCol.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        MainPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
    //Initializes the Parts TableView as soon as the page loads
        MainProductIDCol.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        MainProductNameCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        MainProductInvCol.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());
        MainProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        updatePartTableView();
        updateProductTableView();
    } 
    
//Update Part TableView
    public void updatePartTableView(){
        MainPartsTableView.setItems(getPartInv());
    }

//Update Product TableView
    public void updateProductTableView(){
        MainProductsTableView.setItems(getProductInv());
    }
    
//Set mainApp to the main application.
    public void setMainApp(NicReicheltInventorySystemV2 mainApp) {
        this.mainApp = mainApp;
        updatePartTableView();
        updateProductTableView();
    }
}
