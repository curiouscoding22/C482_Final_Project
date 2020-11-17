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

    public Product(){}

    public Product(int productID, String productName, double productPrice, int productStock, int min, int max){
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.min = min;
        this.max = max;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatePart){
        return associatedParts.remove(selectedAssociatePart);
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
