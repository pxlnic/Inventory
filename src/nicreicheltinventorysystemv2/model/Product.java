/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author nicre
 */
public class Product {
    //Instance variables
    private static ObservableList<Part> parts = FXCollections.observableArrayList();
    protected IntegerProperty productID;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty min;
    protected IntegerProperty max;
    
    //Contructor
    public Product(){
        productID = new SimpleIntegerProperty(-1);
        name = new SimpleStringProperty("Change this name");
        price = new SimpleDoubleProperty(-1.00);
        inStock = new SimpleIntegerProperty(-1);
        min = new SimpleIntegerProperty(-1);
        max = new SimpleIntegerProperty(-1);
    }

//Class methods
//Product ID Setters and Getters
    public void setProductID(int productID){
        this.productID.set(productID);
    }
    public int getProductID(){
        return this.productID.get();
    }
    public IntegerProperty productIDProperty(){
        return productID;
    }
    
//Product Name Setters and Getters
    public void setProductName(String name){
        this.name.set(name);
    }
    public String getProductName(){
        return this.name.get();
    }
    public StringProperty productNameProperty(){
        return name;
    }

//Product Price Setters and Getters
    public void setProductPrice(double price){
        this.price.set(price);
    }
    public double getProductPrice(){
        return this.price.get();
    }
    public DoubleProperty productPriceProperty(){
        return price;
    }

//Product Inventory Setters and Getters
    public void setProductInStock(int inStock){
        this.inStock.set(inStock);
    }
    public int getProductInStock(){
        return this.inStock.get();
    }
    public IntegerProperty productInvProperty(){
        return inStock;
    }

//Product Min Setters and Getters
    public void setProductMin(int min){
        this.min.set(min);
    }
    public int getProductMin(){
        return this.min.get();
    }

//Product Max Setters and Getters
    public void setProductMax(int max){
        this.max.set(max);
    }
    public int getProductMax(){
        return this.max.get();
    }

//Product Parts Setters and Getters
    public void setProductParts(ObservableList<Part> parts){
        this.parts = parts;
    }
    public ObservableList getProductParts(){
        return parts;
    }
    
//Methods for adding and removing parts from a product
    /*public void addPart(Part part){
        parts.add(part);
    }
    boolean removePart(int partID){
        boolean idFound = false;
        for(int i=0; i < parts.size(); i++){
            if(partID == parts.get(i).getPartID()){
                parts.remove(i);
                idFound = true;
            }
            else{
                System.out.println("This product was not found");
                idFound = false;
            }
        }
        return idFound;
    }
    Part lookupPart(int ID){
        Part lookupPart = null;
        for(int i=0; i < parts.size(); i++){
            if(ID == parts.get(i).getPartID()){
                lookupPart = parts.get(i);
            }
            else{
                System.out.println("This product was not found");
            }
        }
        return lookupPart;
    }
    void updatePart(int ID){
        Part updatePart = null;
        for(int i=0; i < parts.size(); i++){
            if(ID == parts.get(i).getPartID()){
                parts.set(i, updatePart);
            }
            else{
                System.out.println("This product was not found");
            }
        }
    }*/
    
//Product Exception Handling
        //min, max, inv, price, parts, message
        public static String isProductValid(int min, int max, int inv, double price, ObservableList<Part> parts, String message){
        double sumParts = 0.00;
    //Adding cost or parts
        for(int i=0;i < parts.size(); i++){
            sumParts = sumParts + parts.get(i).getPartPrice();
        }
        
    //Min cannot be negative
        if(min<0){
            message = message + ("-The minimum allowed inventory cannot negative. Please re-enter.\n");
        }
    //Price cannot be negative
        if(price<0){
            message = message + ("-The price cannot be negative. Please re-enter.\n");
        }
    //Min/Max Handler
        if(min>max){
            message = message + ("-The part min cannot be great than max or part max cannot be lower tha min. Please re-enter.\n");
        }
    //Inventory - Min/Max Handler
        if(inv<min || inv>max){
            message = message + ("-The part inventory cannot be lower than min or greater than max. Please re-enter.\n");
        }
    //Parts list cannot be 0
        if(parts.size()<1){
            message = message + ("-There must be at least one part included. Please add parts.\n");
        }
    //Product price cannot be less than sum of parts
        if(sumParts > price){
            message = message + ("-The cost of included parts cannot be greater than the price of the product. Please re-enter.\n");
        }
        
    //Return message
        return message;
    }
}
