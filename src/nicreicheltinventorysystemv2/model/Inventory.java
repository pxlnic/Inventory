/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

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

//Get Product data
    public static ObservableList<Product> getProductInv(){
        return productsInv;
    }

//Add Product to Product Observable ArrayList
    public static void addInvProduct(Product product){
        productsInv.add(product);
    }
    
    boolean removeProduct(int ID){
        boolean idFound = false;
        for(int i=0; i < productsInv.size(); i++){
            if(ID == productsInv.get(i).getProductID()){
                productsInv.remove(i);
                idFound = true;
            }
            else{
                System.out.println("This product was not found");
                idFound = false;
            }
        }
        return idFound;
    }
    Product lookupProduct(int ID){
        Product lookupProduct = null;
        for(int i=0; i < productsInv.size(); i++){
            if(ID == productsInv.get(i).getProductID()){
                lookupProduct = productsInv.get(i);
            }
            else{
                System.out.println("This product was not found");
            }
        }
        return lookupProduct;
    }
    void updateProduct(int ID){
        lookupProduct(ID);
    }
    
}
