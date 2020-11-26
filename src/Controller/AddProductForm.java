package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.xml.bind.ValidationException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddProductForm implements Initializable {

    @FXML private TableView<Part> productPartTable;
    @FXML private TableColumn<Part, Integer> productPartIDColumn;
    @FXML private TableColumn<Part, String> productPartNameColumn;
    @FXML private TableColumn<Part, Integer> productPartInvColumn;
    @FXML private TableColumn<Part, Double> productPartPriceColumn;

    @FXML private TableView<Part> associatedPartTable;
    @FXML private TableColumn<Part, Integer> associatedPartIDColumn;
    @FXML private TableColumn<Part, String> associatedPartNameColumn;
    @FXML private TableColumn<Part, Integer> associatedPartInvColumn;
    @FXML private TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML private TextField productIDField;
    @FXML private TextField productNameField;
    @FXML private TextField productInvField;
    @FXML private TextField productPriceField;
    @FXML private TextField productMinField;
    @FXML private TextField productMaxField;
    @FXML private TextField productPartSearchField;

    @FXML private Button addProductCancel;

    Random rand = new Random();
    int productIDNumber;
    private boolean isMatch;
    private ObservableList<Part> partsToAssociate = FXCollections.observableArrayList();

    /**
     * This is the Product ID method. This method generates a random product number and checks the existing product numbers to confirm there are no duplicates.
     * @return the product ID.
     */
    private int assignProductID(){
        int randProdID;
        randProdID = 1 + rand.nextInt(999999);

        for(Product p : Inventory.getAllProducts()){
            if(p.getProductID() == randProdID){
                isMatch = true;
                assignProductID();
            }
        }
        return randProdID;
    }

    /**
     *This is the add part method. When the user clicks the add button, the part that is selected in the part table is added to the associated part list and displayed in the bottom table.
     * @param actionEvent the click on the add button.
     */
    public void addAssociatedPart(ActionEvent actionEvent) {
        Part selectPart = productPartTable.getSelectionModel().getSelectedItem();
        if(selectPart != null){
            partsToAssociate.add(selectPart);
            associatedPartTable.setItems(partsToAssociate);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected");
            alert.setContentText("Select a part to associate");
            alert.showAndWait();
        }
    }

    /**
     *This is the part search method. This method searches the part table by part name or ID number for a specified part when the search button is clicked.
     * @param actionEvent the click on the search button.
     */
    public void productPartSearch(ActionEvent actionEvent) {
        String searchText = productPartSearchField.getText();
        if(searchText.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Search field is empty");
            alert.setContentText("Enter a part name or ID");
            alert.showAndWait();
        }
        ObservableList<Part> partsReturned = Inventory.lookupPart(searchText);
        if(partsReturned.size() == 0){
            int idNumber = Integer.parseInt(searchText);
            Part p = Inventory.lookupPart(idNumber);
            if(p != null){
                partsReturned.add(p);
            }
        }
        productPartTable.setItems(partsReturned);
        productPartSearchField.setText("");
    }

    /**
     *This is the remove associated part method. This method removes an associated part from the lower table and the associated parts list.
     * @param actionEvent the click on the Remove Associated button.
     */
    @FXML
    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part p = associatedPartTable.getSelectionModel().getSelectedItem();
        if(p != null){
            partsToAssociate.remove(p);
        }
    }

    /**
     *This is the save method. This method takes the information entered in the text fields and converts it to the specified data types to create a product object that is added to the Inventory.
     * @param actionEvent the click on the save button.
     * @throws NumberFormatException
     * @throws NullPointerException
     * @throws ValidationException
     */
    @FXML
    public void saveNewProduct(ActionEvent actionEvent) throws NumberFormatException, NullPointerException, ValidationException {
        int newProductID = Integer.parseInt(productIDField.getText());
        String newProductName = productNameField.getText();
        int newProductInv = Integer.parseInt(productInvField.getText());
        Double newProductPrice = Double.parseDouble(productPriceField.getText());
        int newProductMax = Integer.parseInt(productMaxField.getText());
        int newProductMin = Integer.parseInt(productMinField.getText());

        Product newProduct = new Product();
        newProduct.setID(newProductID);
        newProduct.setName(newProductName);
        try {
            newProduct.setStock(newProductInv);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Inventory count must be a number");
            alert.showAndWait();

        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Please enter an amount");
            alert.showAndWait();
        }
        try {
            newProduct.setPrice(newProductPrice);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Price must be entered in XX.XX format");
            alert.showAndWait();

        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Please enter a price");
            alert.showAndWait();
        }
        try {
            newProduct.setMax(newProductMax);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Inventory maximum must be a number");
            alert.showAndWait();

        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Please enter an amount");
            alert.showAndWait();
        }
        try {
            newProduct.setMin(newProductMin);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Inventory maximum must be a number");
            alert.showAndWait();

        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Please enter an amount");
            alert.showAndWait();
        }
        for(Part p : partsToAssociate){
            newProduct.addAssociatedPart(p);
        }
        if(isValidProduct(newProduct)){
            Inventory.addProduct(newProduct);
            Stage stage = (Stage) addProductCancel.getScene().getWindow();
            stage.close();
        }

    }

    /**
     *This is the cancel method. When the user clicks the cancel button, this method confirms the action then closes the stage without saving a new part.
     * @param actionEvent the click on the cancel button.
     */
    @FXML
    public void cancelAddNewProduct(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Add Product");
        alert.setContentText("Are you sure you want to cancel?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) addProductCancel.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     *This is the validation method. This method reviews the entered information in each field and confirms it is valid. If it isn't the method displays a descriptive error method to prompt the user to correct it.
     * @param product the produce which is being checked.
     * @return true.
     * @throws ValidationException
     */
    public boolean isValidProduct(Product product) throws ValidationException{

        String checkedName = productNameField.getText();
        int checkedInv = Integer.parseInt(productInvField.getText());
        Double checkedPrice = Double.parseDouble(productPriceField.getText());
        int checkedMax = Integer.parseInt(productMaxField.getText());
        int checkedMin = Integer.parseInt(productMinField.getText());

        if(checkedName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Name field can not be empty");
            alert.showAndWait();
            throw new ValidationException("Name field can not be empty");
        }
        if(checkedMin > checkedMax){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Minimum inventory can not be greater than maximum");
            alert.showAndWait();
            throw new ValidationException("Minimum inventory can not be greater than maximum");
        }
        if(checkedPrice < 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Price can not be less than zero");
            alert.showAndWait();
            throw new ValidationException("Price can not be less than zero");
        }
        if(checkedInv < checkedMin){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Inventory can not be less than minimum");
            alert.showAndWait();
            throw new ValidationException("Inventory can not be less than minimum");
        }
        if(checkedInv > checkedMax){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Inventory can not be greater than maximum");
            alert.showAndWait();
            throw new ValidationException("Inventory can not be greater than maximum");
        }
        return true;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productPartTable.setItems(Inventory.getAllParts());

        associatedPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartTable.setItems(partsToAssociate);

        productIDNumber = assignProductID();
        productIDField.setText(Integer.toString(productIDNumber));
        productIDField.setEditable(false);

    }


}
