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
    public InHousePart(int partID, String name, double price, int inStock, int min, int max, int machineID){
        super(partID, name, price, inStock, min, max);
        this.machineID = machineID;
    }
    
    //Class methods specific to Inhouse parts
    public void setMachineID(int machineID){
        this.machineID = machineID;
    }
    
    public int getMachineID(){
        return machineID;
    }
}
