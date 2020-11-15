package Model;

import Model.Part;

/**
 * This is the outsourced part class.
 * This class extends the abstract part class to make outsourced part objects.
 */
public class OutSourcedPart extends Part {

    public OutSourcedPart(){}

    private String companyName;

    public OutSourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** This is the getter for the company name variable.
     * It returns the company name.
     * @return Returns the Company Name
     */
    public String getCompanyName() {
        return companyName;
    }

    /** This is the setter for the company name variable. It sets the comany name.
     * @param companyName The company name that makes the part.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
