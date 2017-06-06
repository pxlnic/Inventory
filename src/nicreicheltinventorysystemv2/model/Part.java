/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

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
public abstract class Part {
    //Instance variables for the part class
    protected StringProperty name;
    protected IntegerProperty partID;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty min;
    protected IntegerProperty max;
    
    //Contructor
    public Part(){
        partID = new SimpleIntegerProperty(-1);
        name = new SimpleStringProperty("Change this name");
        price = new SimpleDoubleProperty(-1.00);
        inStock = new SimpleIntegerProperty(-1);
        min = new SimpleIntegerProperty(-1);
        max = new SimpleIntegerProperty(-1);
    }
    
//Class methods
//Part ID Getters and Setters
    public void setPartID(int partID){
        this.partID.set(partID);
    }
    public int getPartID(){
        return this.partID.get();
    }
    public IntegerProperty partIDProperty(){
        return partID;
    }

//Part Name Getters and Setters
    public void setPartName(String name){
        this.name.set(name);
    }
    public String getPartName(){
        return this.name.get();
    }
    public StringProperty partNameProperty(){
        return name;
    }

//Part Price Getters and Setters
    public void setPartPrice(double price){
        this.price.set(price);
    }
    public double getPartPrice(){
        return this.price.get();
    }
    public DoubleProperty partPriceProperty(){
        return price;
    }

//Part Inventory Getters and Setters
    public void setPartInStock(int inStock){
        this.inStock.set(inStock);
    }
    public int getPartInStock(){
        return this.inStock.get();
    }
    public IntegerProperty partInvProperty(){
        return inStock;
    }

//Part Min Getters and Setters
    public void setPartMin(int min){
        this.min.set(min);
    }
    public int getPartMin(){
        return this.min.get();
    }

//Part Max Getters and Setters
    public void setPartMax(int max){
        this.max.set(max);
    }
    public int getPartMax(){
        return this.max.get();
    }

//Part Exception   
        //name, min, max, inv, price, message
        public static String isPartValid(String name, int min, int max, int inv, double price, String message){
        //If name is blank
            if(name == null){
                message = message + ("-The Name field cannot be left blank! Please enter a Name!\n");
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
        //Return message
            return message;
    }
}
