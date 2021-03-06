package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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
import java.util.ResourceBundle;

/**
 * This class is the controller for the Modify Product Form.
 */
public class ModifyProductForm implements Initializable {

    @FXML private TextField productIDField;
    @FXML private TextField productNameField;
    @FXML private TextField productInvField;
    @FXML private TextField productPriceField;
    @FXML private TextField productMaxField;
    @FXML private TextField productMinField;
    @FXML private TextField partSearchField;

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

    @FXML private Button cancelModifyProductButton;

    private ObservableList<Part> partsToAssociate = FXCollections.observableArrayList();

    private Product product;

    /**
     * This is the save method. This method takes the modified product information and overwrites the existing product when the save button is clicked by the mouse.
     * @param actionEvent the user click on the "Save" button
     * @throws ValidationException thrown if the entered information entered does not fit the valid format
     */
    public void saveModifiedProduct(ActionEvent actionEvent) throws ValidationException {

        int modProductID = Integer.parseInt(productIDField.getText());
        String modProductName = productNameField.getText();
        int modProductInv = 0;
        Double modProductPrice = 0.0;
        int modProductMax = 0;
        int modProductMin = 0;

        try {
            modProductInv = Integer.parseInt(productInvField.getText());
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
            modProductPrice = Double.parseDouble(productPriceField.getText());
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
            modProductMax = Integer.parseInt(productMaxField.getText());
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
            modProductMin = Integer.parseInt(productMinField.getText());
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
        Product modProduct =  new Product(modProductID, modProductName, modProductPrice, modProductInv, modProductMin, modProductMax);
        for(Part p : partsToAssociate){
            modProduct.addAssociatedPart(p);
        }
        if(isValidProduct(modProduct)){
            Inventory.updateProduct(modProduct);
            Stage stage = (Stage) cancelModifyProductButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * This method is a validation method. This is used to confirm that the information entered in the fields conforms to valid product information.
     * @param product
     * @return true
     * @throws ValidationException thrown if the entered information entered does not fit the valid format
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

    /**
     * This is the product retrieve method. This method collects the values from the table on the main screen and assigns them to the text fields
     * @param selectedProduct the selected product in the table.
     */
    public void retrieveSelectProduct(Product selectedProduct) {
        this.product = selectedProduct;

        productIDField.setText(Integer.toString(product.getProductID()));
        productNameField.setText(product.getProductName());
        productInvField.setText(Integer.toString(product.getProductStock()));
        productPriceField.setText(Double.toString(product.getProductPrice()));
        productMaxField.setText(Integer.toString(product.getMax()));
        productMinField.setText(Integer.toString(product.getMin()));
        partsToAssociate = product.getAllAssociatedParts();
        associatedPartTable.setItems(partsToAssociate);
    }

    /**
     *This method is the event associated with the cancel button. When clicked by the user, it prompts them to confirm their action and closes the stage without saving.
     * @param actionEvent the user click on the cancel button.
     */
    public void cancelModifyProduct(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Modify Product");
        alert.setContentText("Are you sure you want to cancel?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelModifyProductButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     *This method handles the user clicking the search button. The method searches the part list by name or ID to find a part to associate.
     * @param actionEvent the user click on the search button.
     */
    public void searchPartsModify(ActionEvent actionEvent) {
        String searchText = partSearchField.getText();
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
        partSearchField.setText("");
    }

    /**
     *This method handles the user clicking the save button. The method validates the information and overwrites the product information in the inventory.
     * @param actionEvent the user click on the save button.
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
     *This method handles the user clicking the remove button. The method disassociates the selected part from the product's associated part list.
     * @param actionEvent the user click on the remove button.
     */
    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part p = associatedPartTable.getSelectionModel().getSelectedItem();
        if(p != null){
            partsToAssociate.remove(p);
            product.deleteAssociatedPart(p);
        }
    }

    /**
     * This method initializes the tables on the screen and populates them with the part objects.
     * @param url
     * @param resourceBundle
     */
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

        productIDField.setEditable(false);
    }

}
