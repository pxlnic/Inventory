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
public class Inventory {
    //Instance Variables
    private ArrayList<Product> products;
    
    //Class methods to manage inventory
    void addProduct(Product product){
        products.add(product);
    }
    boolean removeProduct(int ID){
        boolean idFound = false;
        for(int i=0; i < products.size(); i++){
            if(ID == products.get(i).getProductID()){
                products.remove(i);
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
        for(int i=0; i < products.size(); i++){
            if(ID == products.get(i).getProductID()){
                lookupProduct = products.get(i);
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
