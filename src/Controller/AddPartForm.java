package Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutSourcedPart;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddPartForm implements Initializable{

    @FXML private TextField partIDField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvCountField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMaxCostField;
    @FXML private TextField partMinCostField;
    @FXML private TextField partSourceField;

    //Radiobuttons and label to change
    @FXML private RadioButton inhouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;
    @FXML private Label sourceLabel;
    private ToggleGroup source;

    @FXML private Button cancelButton;

    //Random number generator for part numbers
    Random rand = new Random();
    int idNumber;




    /**
     * Method to change the source label for parts
     *
     */
    public void changeSourceLabel(){
        if(source.getSelectedToggle().equals(inhouseRadioButton)){
            sourceLabel.setText("Machine ID");
        }else if(source.getSelectedToggle().equals(outsourcedRadioButton)){
            sourceLabel.setText("Company Name");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        source = new ToggleGroup();
        this.inhouseRadioButton.setToggleGroup(source);
        this.outsourcedRadioButton.setToggleGroup(source);

        idNumber = assignPartNumber();
        partIDField.setText(Integer.toString(idNumber));


    }

    boolean isMatch = false;

    public int assignPartNumber(){
        int randPartID;
        randPartID = 1 + rand.nextInt(999999);

        for(Part p : Inventory.getAllParts()){
            if(p.getId() == randPartID) {
                isMatch = true;
                assignPartNumber();
            }
        }
        return randPartID;
    }


    public void saveNewPart(ActionEvent actionEvent) throws IOException {
        int addID = Integer.parseInt(partIDField.getText());
        String addName = partNameField.getText();
        double addPrice = Double.parseDouble(partPriceField.getText());
        int addInv = Integer.parseInt(partInvCountField.getText());
        int addMax = Integer.parseInt(partMaxCostField.getText());
        int addMin = Integer.parseInt(partMinCostField.getText());
        try{
            if(source.getSelectedToggle().equals(inhouseRadioButton)){
                int addMachineID = Integer.parseInt(partSourceField.getText());
                InHousePart newPart = new InHousePart(addID, addName, addPrice, addInv, addMax, addMin, addMachineID);
                Inventory.addPart(newPart);
            }
            if(source.getSelectedToggle().equals(outsourcedRadioButton)){
                String addCompany = partSourceField.getText();
                Inventory.addPart(new OutSourcedPart(addID, addName, addPrice, addInv, addMax, addMin, addCompany));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void cancelAddPart(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cancel Add Part");
        alert.setContentText("Are you sure you want to cancel?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }

}
