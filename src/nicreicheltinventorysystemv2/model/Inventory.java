/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

import static com.sun.deploy.util.ReflectionUtil.instanceOf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author nicre
 */
public class Inventory {
//Observable ArrayLists for Parts and Products
    private static ObservableList<Product> productsInv = FXCollections.observableArrayList();
    private static ObservableList<Part> partsInv = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;

//Constructors
    public Inventory() {
}
    
//Class methods to manage inventory
//Get Part data
    public static ObservableList<Part> getPartInv(){
        return partsInv;
    }

//Add Part to Parts Observable ArrayList
    public static void addInvPart(Part part){
        partsInv.add(part);
    }

//Remove Part from parts Observable ArrayList
    public static void removeInvPart(Part part){
        partsInv.remove(part);
    }
    
//Uupdate Part in Parts Observable Arraylist
    public static void updatePart(int index, Part part){
        partsInv.set(index, part);
    }
    
//Increment and get Part ID
    public static int getPartIDCount(){
        partIDCount++;
        return partIDCount;
    }

//Get Product data
    public static ObservableList<Product> getProductInv(){
        return productsInv;
    }

//Add Product to Product Observable ArrayList
    public static void addInvProduct(Product product){
        productsInv.add(product);
    }

//Remove Product from Product Observable ArrayList
    public static void removeInvProduct(Product product){
        productsInv.remove(product);
    }
    
//Increment and get Product ID
    public static int getProductIDCount(){
        productIDCount++;
        return productIDCount;
    }
    
    public boolean isInteger(String input) {
    try { //Try to make the input into an integer
        Integer.parseInt( input );
        return true; //Return true if it works
    }
    catch( Exception e ) { 
        return false; //If it doesn't work return false
    }
}

//Lookup product in search boxes
    int lookupProduct(String searchTerm){
        boolean isFound = false;
        int index = 0;

    //If statement to determin if value in searchTerm is a string or an int.
        if(isInteger(searchTerm)){
            for(int i=0; i < productsInv.size(); i++){
                if(Integer.parseInt(searchTerm) == productsInv.get(i).getProductID()){
                    index = i;
                    isFound = true;
            }
        }
        }
        else{
            for(int i=0; i<productsInv.size(); i++){
                if (searchTerm.equals(productsInv.get(i).getProductName())) {
                    index = i;
                    isFound = true;
                }
            }
        }

    //If statement to validate if product was found
        if(isFound = true){
            return index;
        }
        else{
            System.out.println("No parts found matching criteria");
            return -1;
        }
    }
    public static void updateProduct(int index, Product product){
        productsInv.set(index, product);
    }
    
//Validate part is not part of a product
    public static boolean validatePartDelete(Part part){
        boolean isFound = false;
    
    //Determines if the part is contained within any products
        for(int i = 0; i < productsInv.size(); i++){
            if(productsInv.get(i).getProductParts().contains(part)){
                isFound = true;
            }
        }
    //Returns isFound statement
    //If isFound is false then part can be removed.
        return isFound;
    }
    
}
