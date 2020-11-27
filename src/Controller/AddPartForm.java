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
import javax.xml.bind.ValidationException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * This class is the controller for the Add Part form.
 */
public class AddPartForm implements Initializable{

    @FXML private TextField partIDField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvCountField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMaxInvField;
    @FXML private TextField partMinInvField;
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
    boolean isMatch = false;


    /**
     * This method is used to change the source label for parts when the Inhouse or Outsourced radio buttons are selected.
     *
     */
    public void changeSourceLabel(){
        if(source.getSelectedToggle().equals(inhouseRadioButton)){
            sourceLabel.setText("Machine ID");
        }else if(source.getSelectedToggle().equals(outsourcedRadioButton)){
            sourceLabel.setText("Company Name");
        }
    }

    /**This is the method for generating part numbers. The number is randomly generated from 1 to 999999 and is checked against existing part numbers to ensure there aren't duplicats.
     * @return a random number to be used for an ID number
     */
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

    /**This is the save part method. This method creates a new part with the user entered information and adds it to the inventory for display.
     * @param actionEvent saves a new part to the part table
     * @throws NumberFormatException
     * @throws ValidationException
     * @throws NullPointerException
     */
    @FXML
    private void saveNewPart(ActionEvent actionEvent) throws NumberFormatException, ValidationException, NullPointerException {

        int newID = Integer.parseInt(partIDField.getText());
        String newName = partNameField.getText();
        String newInv = partInvCountField.getText();
        String newPrice = partPriceField.getText();
        String newMax = partMaxInvField.getText();
        String newMin = partMinInvField.getText();
        String newSource = partSourceField.getText();

        if(source.getSelectedToggle().equals(inhouseRadioButton)) {
            InHousePart newInhousePart = new InHousePart();
            newInhousePart.setId(newID);
            newInhousePart.setName(newName);
            try {
                newInhousePart.setStock(Integer.parseInt(newInv));
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
                newInhousePart.setPrice(Double.parseDouble(newPrice));
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
                newInhousePart.setMax(Integer.parseInt(newMax));
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
                newInhousePart.setMin(Integer.parseInt(newMin));
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
                newInhousePart.setMachineID(Integer.parseInt(newSource));
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setContentText("Machine ID must be a number");
                alert.showAndWait();

            } catch (NullPointerException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setContentText("Please enter the Machine ID number");
                alert.showAndWait();
            }
            if(isValidPart(newInhousePart)){
                Inventory.addPart(newInhousePart);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        }

        if(source.getSelectedToggle().equals(outsourcedRadioButton)) {
            OutSourcedPart newOutsourcePart = new OutSourcedPart();
            newOutsourcePart.setId(newID);
            newOutsourcePart.setName(newName);
            try {
                newOutsourcePart.setStock(Integer.parseInt(newInv));
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
                newOutsourcePart.setPrice(Double.parseDouble(newPrice));
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
                newOutsourcePart.setMax(Integer.parseInt(newMax));
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
                newOutsourcePart.setMin(Integer.parseInt(newMin));
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
                newOutsourcePart.setCompanyName(newSource);
            } catch (NullPointerException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setContentText("Please enter the Company Name");
                alert.showAndWait();
            }
            if(isValidPart(newOutsourcePart)){
                Inventory.addPart(newOutsourcePart);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * This is the cancel method. When the user clicks the cancel button, this method confirms the action then closes the add part screen without saving information.
     * @param actionEvent
     */
    @FXML
    private void cancelAddPart(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Add Part");
        alert.setContentText("Are you sure you want to cancel?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * This is the validation method. This method checks the input in the text fields to ensure it is valid and throws an exception with a description to prompt the user to fix if not.
     * @param part the part that is being checked.
     * @return true
     * @throws ValidationException
     */
    public boolean isValidPart(Part part) throws ValidationException{

        String name = partNameField.getText();
        int inv = Integer.parseInt(partInvCountField.getText());
        Double price = Double.parseDouble(partPriceField.getText());
        int max = Integer.parseInt(partMaxInvField.getText());
        int min = Integer.parseInt(partMinInvField.getText());

        if(name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Name field can not be empty");
            alert.showAndWait();
            throw new ValidationException("Name field can not be empty");
        }
        if(min > max){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Minimum inventory can not be greater than maximum");
            alert.showAndWait();
            throw new ValidationException("Minimum inventory can not be greater than maximum");
        }
        if(price < 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Price can not be less than zero");
            alert.showAndWait();
            throw new ValidationException("Price can not be less than zero");
        }
        if(inv < min){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Inventory can not be less than minimum");
            alert.showAndWait();
            throw new ValidationException("Inventory can not be less than minimum");
        }
        if(inv > max){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Inventory can not be greater than maximum");
            alert.showAndWait();
            throw new ValidationException("Inventory can not be greater than maximum");
        }
        return true;

    }

    /**
     * This method initializes the radio buttons and sets the non-editable part number.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        source = new ToggleGroup();
        this.inhouseRadioButton.setToggleGroup(source);
        this.outsourcedRadioButton.setToggleGroup(source);

        idNumber = assignPartNumber();
        partIDField.setText(Integer.toString(idNumber));
        partIDField.setEditable(false);


    }

}
