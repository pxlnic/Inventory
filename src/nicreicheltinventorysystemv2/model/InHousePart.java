/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicreicheltinventorysystemv2.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author nicre
 */
public class InHousePart extends Part {
    //Instance variable
    private IntegerProperty machineID;
    
    //Constructor
    public InHousePart(){
        super();
        machineID = new SimpleIntegerProperty(-1);
    }
    
    //Setter and Getters specific to Inhouse parts
    public void setPartMachineID(int machineID){
        this.machineID.set(machineID);
    }
    public int getPartMachineID(){
        return this.machineID.get();
    }
}
