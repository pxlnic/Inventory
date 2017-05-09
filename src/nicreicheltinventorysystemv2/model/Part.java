/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author nicre
 */
public abstract class Part {
    //Instance variables for the part class
    private StringProperty name;
    private IntegerProperty partID;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    
    //Contructor
    public Part(int partID, String name, double price, int inStock, int min, int max){
        this.partID.set(partID);
        this.name.set(name);
        this.price.set(price);
        this.inStock.set(inStock);
        this.min.set(min);
        this.max.set(max);
    }
    
    //Class methods
    public void setPartName(String name){
        this.name.set(name);
    }
    
    public String getPartName(){
        return this.name.get();
    }
    
    public StringProperty partNameProperty(){
        return name;
    }
    
    public void setPartPrice(double price){
        this.price.set(price);
    }
    
    public double getPartPrice(){
        return this.price.get();
    }
    
    public DoubleProperty partPriceProperty(){
        return price;
    }
    
    public void setPartInStock(int inStock){
        this.inStock.set(inStock);
    }
    
    public int getPartInStock(){
        return this.inStock.get();
    }
    
    public IntegerProperty partInvProperty(){
        return inStock;
    }
    
    public void setPartMin(int min){
        this.min.set(min);
    }
    
    public int getPartMin(){
        return this.min.get();
    }
    
    public void setPartMax(int max){
        this.max.set(max);
    }
    
    public int getPartMax(){
        return this.max.get();
    }
    
    public void setPartID(int partID){
        this.partID.set(partID);
    }
    
    public int getPartID(){
        return this.partID.get();
    }
    
    public IntegerProperty partIDProperty(){
        return partID;
    }
}
