package Model;

import Model.Part;

/**
 * This is the Inhouse Part class. This class extends the abstract part class to create Inhouse Part objects.
 */
public class InHousePart extends Part {

    public InHousePart(){}

    private int machineID;


    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**Returns the machine ID number for a part.
     * @return machine ID
     */
    public int getMachineID() {
        return machineID;
    }

    /**Sets the machine ID number for a part.
     * @param machineID the designated machine ID number
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
