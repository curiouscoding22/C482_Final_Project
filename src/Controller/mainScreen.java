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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.lookupPartName;
import static Model.Inventory.partIDLookup;


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
        ObservableList<Part> returnedParts = searchByPartName(searchText);
        if(returnedParts.size() == 0){
            try{
                int partIDNumber = Integer.parseInt(searchText);
                Part p = searchByPartID(partIDNumber);
                if(p != null){
                    returnedParts.add(p);
                }
            } catch (NumberFormatException e){
                //ignore exception
            }
        }
        partsTable.setItems(returnedParts);
        partSearchField.setText("");
    }

    private ObservableList<Part> searchByPartName(String partialPart){
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for(Part p : Inventory.getAllParts()){
            if(p.getName().contains(partialPart)){
                foundParts.add(p);
            }
        }
        return foundParts;
    }

    private Part searchByPartID(int searchedIDNumber){
        ObservableList<Part> partsToSearch = Inventory.getAllParts();
        for(int i = 0; i < partsToSearch.size(); i++){
            Part p = partsToSearch.get(i);
            if(p.getId() == searchedIDNumber){
                return p;
            }
        }
        return null;
    }

    //Button function to bring up Add Part Stage
    public void onAddPartClicked(MouseEvent event){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/View/addPartForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add Part");
            stage.setScene(new Scene(root, 519, 475));
            stage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onModifyPartClicked(MouseEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ModifyPartForm.fxml"));
            Parent mainScreen = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(mainScreen, 600, 495));
            stage.show();
            


        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyProductForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(root, 840, 550));
            stage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!entered){
            Inventory.addPart(new OutSourcedPart( 1, "Ryzen 7 5800x", 495.00, 10, 1, 20, "AMD") );
            Inventory.addPart(new InHousePart(2, "Basic Motherboard", 89.00, 16, 1, 50, 1123));
            Inventory.addPart(new InHousePart( 3, "ATX Case", 67.00, 15, 1, 10, 1179));



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
