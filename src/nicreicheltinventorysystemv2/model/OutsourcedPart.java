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
public class OutsourcedPart extends Part{
    //Instance variable
    private String companyName;
    
    //Constructor
    public OutsourcedPart(String name, int partID, double price, int inStock, int min, int max, String companyName){
        super(name, partID, price, inStock, min, max);
        setCompanyName(companyName);
    }
    
    //Class methods specific to Inhouse parts
    void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    String getcompanyName(){
        return companyName;
    }  
}
