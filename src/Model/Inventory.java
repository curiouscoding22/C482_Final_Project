package Model;

import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Part partIDLookup(int partID){
        for (Part i : allParts) {
            if (i.getId() == partID) {
                return i;
            }
        }
        return null;
    }

    public static Product productIDLookup(int productID){
        for(Product prod : allProducts){
            if(prod.getProductID() == productID){
                return prod;
            }
        }
        return null;
    }

    public static Part lookupPartName(String searchedPart){
        for(Part i:allParts){
            if(i.getName().toLowerCase().equals(searchedPart.toLowerCase())){
                return i;
            }
        }
        return null;
    }

    public static Product lookupProductName(String searchedProduct){
        for(Product prod : allProducts){
            if(prod.getProductName().toLowerCase().contains(searchedProduct.toLowerCase())){
                return prod;
            }
        }
        return null;
    }

    public static void updatePart(Part selectPart){
        for(int i = 0; i<allParts.size(); i++){
            if(allParts.get(i).getId() == selectPart.getId()){
                allParts.set(i, selectPart);
            }
        }
    }

    public static void updateProduct(Product selectedProduct){
        for(int i = 0; i<allProducts.size(); i++){
            if(allProducts.get(i).getProductID() == selectedProduct.getProductID()){
                allProducts.set(i, selectedProduct);
            }
        }
    }

    public static boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
