package inventoryManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static Part lookupPart(int partID){
        Part temp = null;
        for(int i=0; i<allParts.size(); i++){
            if(allParts.get(i).getId() == partID){
                temp = allParts.get(i);
            }
        }
        return temp;
    }

}
