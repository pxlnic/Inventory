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
public class InHousePart extends Part {
    //Instance variable
    private int machineID;
    
    //Constructor
    public InHousePart(String name, int partID, double price, int inStock, int min, int max, int machineID){
        super(name, partID, price, inStock, min, max);
        setMachineID(machineID);
    }
    
    //Class methods specific to Inhouse parts
    void setMachineID(int machineID){
        this.machineID = machineID;
    }
    
    int getMachineID(){
        return machineID;
    }
}
