/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

import java.util.ArrayList;


/**
 *
 * @author nicre
 */
public class Product {
    //Instance variables
    private ArrayList<Part> parts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    //Contructor
    public Product(int productID, String name, double price, int inStock, int min, int max){
        parts = new ArrayList<>();
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    //Class methods to set and get product information
    void setProductID(int productID){
        this.productID = productID;
    }
    int getProductID(){
        return productID;
    }
    void setName(String name){
        this.name = name;
    }
    String getName(){
        return name;
    }
    void setPrice(double price){
        this.price = price;
    }
    double getPrice(){
        return price;
    }
    void setInStock(int inStock){
        this.inStock = inStock;
    }
    int getInStock(){
        return inStock;
    }
    void setMin(int min){
        this.min = min;
    }
    int getMin(){
        return min;
    }
    void setMax(int max){
        this.max = max;
    }
    int getMax(){
        return max;
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
        
    }
    
}
