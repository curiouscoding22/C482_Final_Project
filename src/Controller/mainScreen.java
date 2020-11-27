package Controller;

import Model.*;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * This class is the controller for the main screen functions.
 */
public class mainScreen implements Initializable {


    static boolean entered;


    @FXML private TextField partSearchField;
    @FXML private TextField productSearchField;
    @FXML private Label searchErrorLabel;

    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInventoryColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;

    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Part, Integer> productIDColumn;
    @FXML private TableColumn<Part, String> productNameColumn;
    @FXML private TableColumn<Part, Integer> productInventoryColumn;
    @FXML private TableColumn<Part, Double> productPriceColumn;

    /**
     *This is the part search method. This method searches the part table for a match to the user entry in the textf field by either part name or ID number.
     * @param actionEvent Search button clicked.
     */
    public void searchHandle(ActionEvent actionEvent) {
        String searchText = partSearchField.getText();
        if(searchText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed Search");
            alert.setContentText("Enter the part name or ID number to search");
            alert.showAndWait();
        }
        ObservableList<Part> returnedParts = Inventory.lookupPart(searchText);
        if (returnedParts.size() == 0) {
            try {
                int partIDNumber = Integer.parseInt(searchText);
                Part p = Inventory.lookupPart(partIDNumber);
                if (p != null) {
                    returnedParts.add(p);
                }
            } catch (NumberFormatException e) {
                    //ignore exception
            }
        }
        partsTable.setItems(returnedParts);
        partSearchField.setText("");
        if(returnedParts.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed Search");
            alert.setContentText("No matching part found.");
            alert.showAndWait();
            partsTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     *This is the add part screen method. This method creates a new stage for the add part function using the fxml file and controller.
     * @param event Add button clicked.
     */
    public void onAddPartClicked(MouseEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/addPartForm.fxml"));
            Stage addPartStage = new Stage();
            addPartStage.setTitle("Add Part");
            addPartStage.setScene(new Scene(root, 519, 475));
            addPartStage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *This is the modify part screen method. This method creates the new stage for the modify part scene and functions using the corresponding fxml file and controller.
     * @param event Modify button clicked.
     */
    public void onModifyPartClicked(MouseEvent event){
        if(partsTable.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ModifyPartForm.fxml"));
                Parent mainScreen = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Modify Part");
                stage.setScene(new Scene(mainScreen, 600, 495));
                stage.show();
                ModifyPartForm controller = loader.getController();
                Part selectPart = partsTable.getSelectionModel().getSelectedItem();
                controller.retrieveSelectPart(selectPart);
                /**
                 * When I began working on this method I was having a run time error where the part information I was attempting to load was not populating in the fields of the modify part screen. While the application was running, if I clicked the "Modify" button, the application would throw a NullPointerException to the terminal. I began reviewing this method as well as the retrieveSelectPart method that is called within it to see where the null pointer was being thrown. After longer than it should have taken, I watched the available video in the course resource links on using the debugger to step through the application to see what is happening at a given time. I set the breakpoint at line 113 and ran in debugger. When it hit the breakpoint, I was able to see the stock variable for the part was null and was causing the error to occur. The variable for the stock text field in the controller class did not match the fxid of the field in Scene Builder. Once this name was matched, the application was able to load the part data and the error was cleared.
                 */
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setContentText("Select a part from the list to modify");
            alert.showAndWait();
        }
    }

    /**
     *This is the delete part method. This method deletes a selected part in the tableview by removing it from the allparts list.
     * @param actionEvent Delete button clicked.
     */
    public void onDeletePartClicked(ActionEvent actionEvent) {
        Part selectedDelete = partsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part");
        alert.setContentText("You are about to delete " + selectedDelete.getName() + ", do you want to proceed?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Inventory.deletePart(selectedDelete);
            }
        });

    }

    /**
     *This is the product search method. This method searches for a product match to the user entered information in the search field and compares it to the existing product names or ID's.
     * @param actionEvent Search button clicked.
     */
    public void searchProductTable(ActionEvent actionEvent) {
        String searchText = productSearchField.getText();
        if(searchText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed Search");
            alert.setContentText("Enter the product name or ID number to search");
            alert.showAndWait();
        }
        ObservableList<Product> returnedProds = Inventory.lookupProductName(searchText);
        if (returnedProds.size() == 0) {
            try {
                int productID = Integer.parseInt(searchText);
                Product p = Inventory.productIDLookup(productID);
                if (p != null) {
                    returnedProds.add(p);
                }
            } catch (NumberFormatException e) {
                //ignore exception
            }
        }
        productsTable.setItems(returnedProds);
        productSearchField.setText("");
        if(returnedProds.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed Search");
            alert.setContentText("No matching part found.");
            alert.showAndWait();
            productsTable.setItems(Inventory.getAllProducts());
        }

//        ObservableList<Product> returnedProducts = Inventory.lookupProductName(searchText);
//        productsTable.setItems(returnedProducts);
//        partSearchField.setText("");
//


//        ObservableList<Product> returnedProducts = Inventory.lookupProductName(searchText);
//        if (returnedProducts.size() == 0) {
//            try {
//                int productIDNumber = Integer.parseInt(searchText);
//                Product p = Inventory.productIDLookup(productIDNumber);
//                if (p != null) {
//                    returnedProducts.add(p);
//                    productsTable.setItems(returnedProducts);
//                    partSearchField.setText("");
//                }
//            } catch (NullPointerException ex){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Failed Search");
//                alert.setContentText("No matching product found.");
//                alert.showAndWait();
//            }
//        }
    }

    /**
     *This is the add product screen method. This method creates a new stage which is formatted with the add product fxml file and uses the corresponding controller file for functionality.
     * @param event Add button clicked in product pane.
     */
    public void onAddProductClicked(MouseEvent event){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/View/AddProductForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Product");
            stage.setScene(new Scene(root, 840, 550));
            stage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *This is the modify product screen method. This method creates a new stage which is formatted with the modify product fxml file and uses the corresponding controller file for functionality.
     * @param event Modify button clicked in product pane.
     */
    public void onModifyProductClicked(MouseEvent event){
        if(productsTable.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ModifyProductForm.fxml"));
                Parent mainScreen = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Modify Product");
                stage.setScene(new Scene(mainScreen, 840, 550));
                stage.show();
                ModifyProductForm controller = loader.getController();
                Product selectProduct = productsTable.getSelectionModel().getSelectedItem();
                controller.retrieveSelectProduct(selectProduct);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Product Selected");
            alert.setContentText("Select a product from the list to modify");
            alert.showAndWait();
        }
    }

    /**
     *This is the delete product method. This method checks that the selected product doesn't have any associated parts (if it does, the user is prompted to remove them) and removes the product from the allproducts list and products tableview.
     * @param actionEvent Delete clicked in product pane.
     */
    public void deleteProduct(ActionEvent actionEvent) {
        Product productToDelete = productsTable.getSelectionModel().getSelectedItem();
        if(!productToDelete.getAllAssociatedParts().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unable to delete");
            alert.setContentText("Can not delete product with associated parts, please remove and try again.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setContentText("You are about to delete " + productToDelete.getProductName() + ", do you want to proceed?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Inventory.deleteProduct(productToDelete);
                }
            });
        }
    }

    /**
     *This is the exit method. This method confirms the user wants to exit and terminates the application.
     * @param actionEvent Exit button clicked.
     */
    public void exitProgram(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Do you want to close the program?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }


    /**
     * This method initializes the tables and provides default objects.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!entered){
            Inventory.addPart(new OutSourcedPart( 1, "Ryzen 7 5800x", 495.00, 10, 1, 20, "AMD") );
            Inventory.addPart(new OutSourcedPart( 15, "Ryzen 9 5900x", 495.00, 10, 1, 20, "AMD") );
            Inventory.addPart(new OutSourcedPart(12, "Test Outsourced", 6.00, 15, 1, 10, "Acme"));
            Inventory.addPart(new InHousePart(2, "Basic Motherboard", 89.00, 16, 1, 50, 1123));
            Inventory.addPart(new InHousePart( 3, "ATX Case", 67.00, 15, 1, 10, 1179));
            Inventory.addProduct(new Product(13, "Basic Package", 456.00, 3, 1, 14));
            Inventory.addProduct(new Product(156, "Test 1", 3.45, 6, 1, 17));
            Inventory.addProduct(new Product(18776, "Test 2", 6.78, 3, 1, 15));
        }
        //Binding part table columns
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getAllParts());

        //Binding product table columns
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productsTable.setItems(Inventory.getAllProducts());
    }
}
