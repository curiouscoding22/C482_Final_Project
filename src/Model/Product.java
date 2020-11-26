package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private int productID;
    private String productName;
    private double productPrice;
    private int productStock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * This is the empty constructor. It is used to create an instance of the product class.
     */
    public Product(){}

    /**
     * This is the full constructor. This is used to create full Product objects.
     * @param productID
     * @param productName
     * @param productPrice
     * @param productStock
     * @param min
     * @param max
     */
    public Product(int productID, String productName, double productPrice, int productStock, int min, int max){
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.min = min;
        this.max = max;
    }

    /**
     * This method returns the product's ID number.
     * @return the ID number.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This method sets the product ID number.
     * @param productID
     */
    public void setID(int productID) {
        this.productID = productID;
    }

    /**
     * This method returns the product's name.
     * @return the name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method sets the product name
     * @param productName
     */
    public void setName(String productName) {
        this.productName = productName;
    }

    /**
     *This method returns the product price.
     * @return the price
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     *This method sets the product price.
     * @param productPrice
     */
    public void setPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     *This method returns the inventory count
     * @return the stock
     */
    public int getProductStock() {
        return productStock;
    }

    /**
     *This method sets the product stock level.
     * @param productStock
     */
    public void setStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     *This method returns the minimum stock level,
     * @return the minimum level.
     */
    public int getMin() {
        return min;
    }

    /**
     *This method sets the minimum stock level.
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     *This method returns the maximum stock level.
     * @return the maximum stock
     */
    public int getMax() {
        return max;
    }

    /**
     *This method sets the maximum stock level.
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *This method adds a part to the associated part list.
     * @param part
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     *This method removes a part from the associated part list.
     * @param selectedAssociatePart
     * @return true
     */
    public boolean deleteAssociatedPart(Part selectedAssociatePart){
        associatedParts.remove(selectedAssociatePart);
        return true;
    }

    /**
     *This method returns the list of all associated parts.
     * @return all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
