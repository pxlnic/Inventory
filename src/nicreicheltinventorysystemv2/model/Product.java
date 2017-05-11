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


/**
 *
 * @author nicre
 */
public class Product {
    //Instance variables
    /*private ArrayList<Part> parts;*/
    protected IntegerProperty productID;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty min;
    protected IntegerProperty max;
    
    //Contructor
    public Product(){
        /*parts = new ArrayList<>();*/
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
        this.min.set(max);
    }
    public int getProductMax(){
        return this.max.get();
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
    
}
