/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

/**
 *
 * @author nicre
 */
public abstract class Part {
    //Instance variables for the part class
    private String name;
    private int partID;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    //Contructor
    public Part(String name, int partID, double price, int inStock, int min, int max){
        this.name = name;
        this.partID = partID;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    
    //Class methods
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
    
    void setPartID(int partID){
        this.partID = partID;
    }
    
    int getPartID(){
        return partID;
    }
}
