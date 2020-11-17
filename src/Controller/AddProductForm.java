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

    Random rand = new Random();
    int productIDNumber;
    private boolean isMatch;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

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


    public void addAssociatedPart(ActionEvent actionEvent) {
        Part selectPart = productPartTable.getSelectionModel().getSelectedItem();
        if(selectPart != null){
            associatedParts.add(selectPart);
            associatedPartTable.setItems(associatedParts);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected");
            alert.setContentText("Select a part to associate");
            alert.showAndWait();
        }
    }

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

    public void removeAssociatedPart(ActionEvent actionEvent) {
    }

    public void saveNewProduct(ActionEvent actionEvent) {
    }

    public void cancelAddNewProduct(ActionEvent actionEvent) {
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
        associatedPartTable.setItems(associatedParts);

        productIDNumber = assignProductID();
        productIDField.setText(Integer.toString(productIDNumber));
        productIDField.setEditable(false);

    }


}
