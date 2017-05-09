/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author nicre
 */
public class Product {
    //Instance variables
    private ArrayList<Part> parts;
    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    
    //Contructor
    public Product(int productID, String name, double price, int inStock, int min, int max){
        parts = new ArrayList<>();
        this.productID.set(productID);
        this.name.set(name);
        this.price.set(price);
        this.inStock.set(inStock);
        this.min.set(min);
        this.max.set(max);
    }
    //Class methods to set and get product information
    void setProductID(int productID){
        this.productID.set(productID);
    }
    int getProductID(){
        return this.productID.get();
    }
    void setName(String name){
        this.name.set(name);
    }
    String getName(){
        return this.name.get();
    }
    void setPrice(double price){
        this.price.set(price);
    }
    double getPrice(){
        return this.price.get();
    }
    void setInStock(int inStock){
        this.inStock.set(inStock);
    }
    int getInStock(){
        return this.inStock.get();
    }
    void setMin(int min){
        this.min.set(min);
    }
    int getMin(){
        return this.min.get();
    }
    void setMax(int max){
        this.min.set(max);
    }
    int getMax(){
        return this.max.get();
    }
    
    //Methods for adding and removing parts from a product
    void addPart(Part part){
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
    }
    
}
