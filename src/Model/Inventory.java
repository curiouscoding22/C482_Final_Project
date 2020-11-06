package Model;

import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partID){
        Part temp = null;
        for (Part allPart : allParts) {
            if (allPart.getId() == partID) {
                temp = allPart;
            }
        }
        return temp;
    }

    public static Product lookupProduct(int productID){
        for(Product prod : allProducts){
            if(prod.getId() == productID){
                return prod;
            }
        }
        return null;
    }

    public static Part lookupPart(String searchedPart){
        for(Part part:allParts){
            if(part.getName().toLowerCase().equals(searchedPart.toLowerCase())){
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(String searchedProduct){
        for(Product prod : allProducts){
            if(prod.getName().toLowerCase().equals(searchedProduct.toLowerCase())){
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
            if(allProducts.get(i).getId() == selectedProduct.getId()){
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
