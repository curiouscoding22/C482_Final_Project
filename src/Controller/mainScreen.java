package Controller;

import Model.*;
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



public class mainScreen implements Initializable {


    static boolean entered;


    @FXML
    private TextField partSearchField;

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





    //////////////////Main Screen, Part Functions////////////////////////////

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
    }


    //Button function to bring up Add Part Stage
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




    //////////////////Main Screen, Product Functions////////////////////////////

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!entered){
            Inventory.addPart(new OutSourcedPart( 1, "Ryzen 7 5800x", 495.00, 10, 1, 20, "AMD") );
            Inventory.addPart(new OutSourcedPart( 15, "Ryzen 9 5900x", 495.00, 10, 1, 20, "AMD") );
            Inventory.addPart(new OutSourcedPart(12, "Test Outsourced", 6.00, 15, 1, 10, "Acme"));
            Inventory.addPart(new InHousePart(2, "Basic Motherboard", 89.00, 16, 1, 50, 1123));
            Inventory.addPart(new InHousePart( 3, "ATX Case", 67.00, 15, 1, 10, 1179));

            Inventory.addProduct(new Product(13, "Basic Package", 456.00, 3, 1, 14));



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
