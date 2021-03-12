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

    /**This is the method for generating part numbers. The number is randomly generated from 1 to 999999 and is checked against existing part numbers to ensure there aren't duplicates.
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

    /**This is the save part method. This method creates a new part with the user entered information and adds it to the inventory for display.  One runtime error I encountered occurred while trying to save a part to the main screen.  When I would click the save button which would take the entered part information and create a new, validated part, the application would throw a NullPointerException. I had added try/catch blocks to each of the times the input was converted to the appropriate variable type but none of those exception alerts was being shown to point out where the error was coming from. I set a breakpoint at the line where the Inhouse part is created and used the debugger to determine where the error was. The maximum inventory variable was null and causing the exception though there was input in the text field. After terminating the application, I reviewed the fxid of the max field and noticed it didn't match with the TextField variable that was initialized in this class. Once the variable name matched with the fxid the variable was assigned correctly and the part was saved to the inventory.
     * @param actionEvent saves a new part to the part table
     * @throws NumberFormatException thrown if the user enters text into a field that should be a number
     * @throws ValidationException thrown if the entered information entered does not fit the valid format
     * @throws NullPointerException thrown if any of the fields are left blank
     *
     *
     */
    @FXML
    public void saveNewPart(ActionEvent actionEvent) throws NumberFormatException, ValidationException, NullPointerException {

        int newID = Integer.parseInt(partIDField.getText());
        String newName = partNameField.getText();
        int newInv = 0;
        double newPrice = 0;
        int newMax = 0;
        int newMin = 0;
        int machineID = 0;
        String companyName = "";

        if (source.getSelectedToggle().equals(inhouseRadioButton)) {
            try {
                newInv = Integer.parseInt(partInvCountField.getText());
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
                newPrice = Double.parseDouble(partPriceField.getText());
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
                newMax = Integer.parseInt(partMaxInvField.getText());
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
                newMin = Integer.parseInt(partMinInvField.getText());
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
                machineID = Integer.parseInt(partSourceField.getText());
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
            Part newInhouse = new InHousePart(newID, newName, newPrice, newInv, newMax, newMin, machineID);
            if (isValidPart(newInhouse)) {
                Inventory.addPart(newInhouse);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        }
        if (source.getSelectedToggle().equals(outsourcedRadioButton)) {
            try {
                newInv = Integer.parseInt(partInvCountField.getText());
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
                newPrice = Double.parseDouble(partPriceField.getText());
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
                newMax = Integer.parseInt(partMaxInvField.getText());
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
                newMin = Integer.parseInt(partMinInvField.getText());
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
                companyName = partSourceField.getText();
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
            Part newOutsource = new OutSourcedPart(newID, newName, newPrice, newInv, newMax, newMin, companyName);
            if (isValidPart(newOutsource)) {
                Inventory.addPart(newOutsource);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        }
    }


    /**
     * This is the cancel method. When the user clicks the cancel button, this method confirms the action then closes the add part screen without saving information.
     * @param actionEvent user clicking on the "Cancel" button
     */
    @FXML
    public void cancelAddPart(ActionEvent actionEvent) {
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
     * @throws ValidationException thrown if the entered information entered does not fit the valid format
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
