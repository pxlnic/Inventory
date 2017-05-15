/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nic Reichelt
 */
public class OutsourcedPart extends Part{
    //Instance variable
    private StringProperty companyName;
    
    //Constructor
    public OutsourcedPart(){
        super();
        companyName = new SimpleStringProperty("Change this company name");
    }
    
    //Class methods specific to Inhouse parts
    public void setPartCompanyName(String companyName){
        this.companyName.set(companyName);
    }
    
    public String getPartCompanyName(){
        return this.companyName.get();
    }  
}
