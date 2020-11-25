package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the Inventory class. This class contains methods for adding and editing parts and products to the inventory lists.
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**This method adds a part to the inventory. This part is then displayed in the part table.
     * @param newPart the new part to be added.
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**This method adds a product to the inventory. This product is then displayed in the product table.
     * @param newProduct the new product to be added.
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**This is the part search method. This method is called to look through the parts in the inventory and return a match if it exists.
     * @param searchedIDNumber the ID number of the part that is being searched for.
     * @return the matched part (if there is a match).
     */
    public static Part lookupPart(int searchedIDNumber){
        ObservableList<Part> partsToSearch = Inventory.getAllParts();
        for(int i = 0; i < partsToSearch.size(); i++){
            Part p = partsToSearch.get(i);
            if(p.getId() == searchedIDNumber){
                return p;
            }
        }
        return null;
    }

    /**This is the product search method. This method is called to look through the products in the inventory and return a product with a matching ID number.
     * @param productID the ID number of the part that is being searched for.
     * @return the matched part (if it exists).
     */
    public static Product productIDLookup(int productID){
        ObservableList<Product> productsToSearch = Inventory.getAllProducts();
        for(Product prod : productsToSearch){
            if(prod.getProductID() == productID){
                return prod;
            }
        }
        return null;
    }

    /**This is the part search method. This method is overloaded to search by either the full or partial part name.
     * @param partialPart the full or partial part name used to search.
     * @return the matching part(s).
     */
    public static ObservableList<Part> lookupPart(String partialPart){
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for(Part p : allParts){
            if(p.getName().toLowerCase().contains(partialPart.toLowerCase())){
                foundParts.add(p);
            }
        }
        return foundParts;
    }

    /**This is the product search method. This method is overloaded to search by either the full or partial product name.
     * @param searchedProduct the full or partial product name used in the search.
     * @return the matching product(s).
     */
    public static ObservableList<Product> lookupProductName(String searchedProduct){
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for(Product prod : allProducts){
            if(prod.getProductName().toLowerCase().contains(searchedProduct.toLowerCase())){
                foundProducts.add(prod);
            }
        }
        return foundProducts;
    }

    /**This is the update part method. This method takes a modified part and replaces the existing inventory part with it by matching the ID number.
     * @param selectPart the modified part that is going into the inventory.
     */
    public static void updatePart(Part selectPart){
        for(int i = 0; i<allParts.size(); i++){
            if(allParts.get(i).getId() == selectPart.getId()){
                allParts.set(i, selectPart);
            }
        }
    }

    /**This is the update Product method. This method takes a modified product and replaces the existing product in inventory with it by matching the product ID number.
     * @param selectedProduct the modified product that is going into the inventory.
     */
    public static void updateProduct(Product selectedProduct){
        for(int i = 0; i<allProducts.size(); i++){
            if(allProducts.get(i).getProductID() == selectedProduct.getProductID()){
                allProducts.set(i, selectedProduct);
            }
        }
    }

    /**This is the part delete method. This removes a selected part from the inventory.
     * @param selectedPart the part to remove
     * @return returns true if the part is deleted.
     */
    public static boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        return true;
    }

    /**This is the delete product method. This method removes a selected product from the inventory.
     * @param selectedProduct the product to remove.
     * @return returns true if the product was deleted.
     */
    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;
    }

    /**This is the get parts method. This method returns all the current parts in the inventory list.
     * @return all parts in inventory.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**This is the get products method. This method returns all the current products in the inventory.
     * @return all products in inventory.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
