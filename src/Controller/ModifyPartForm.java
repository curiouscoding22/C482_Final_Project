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
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class ModifyPartForm implements Initializable {

    @FXML
    private TextField partIDField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partInvField;

    @FXML
    private TextField partPriceField;

    @FXML
    private TextField partMaxInvField;

    @FXML
    private TextField partMinInvField;

    @FXML
    private TextField partSourceField;

    @FXML
    private Button cancelButton;


    //Radiobuttons and label to change
    @FXML private RadioButton inhouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;
    @FXML private Label sourceLabel;
    @FXML private ToggleGroup source;

    private Part part;

    /**
     * This is the change source method. This method changes the source text field to "Machine ID" or "Company Name" based on the radio button selected.
     */
    public void changeSourceLabel(){
        if(source.getSelectedToggle().equals(inhouseRadioButton)){
            sourceLabel.setText("Machine ID");
        }else if(source.getSelectedToggle().equals(outsourcedRadioButton)){
            sourceLabel.setText("Company Name");
        }
    }

    /**This is the part retrieve method. This method is used to collect the selected part information from the main screen and populate it on the modify screen.
     * @param selectedPart this is the part selected from the main screen part table.
     */
    public void retrieveSelectPart(Part selectedPart) {
        this.part = selectedPart;

        if(selectedPart instanceof InHousePart){
            sourceLabel.setText("Machine ID");
            InHousePart inhouse = (InHousePart)selectedPart;
            partIDField.setText(Integer.toString(inhouse.getId()));
            partNameField.setText(inhouse.getName());
            partPriceField.setText(Double.toString(inhouse.getPrice()));
            partInvField.setText(Integer.toString(inhouse.getStock()));
            partMaxInvField.setText(Integer.toString(inhouse.getMax()));
            partMinInvField.setText(Integer.toString(inhouse.getMin()));
            partSourceField.setText(Integer.toString(inhouse.getMachineID()));
            inhouseRadioButton.setSelected(true);

        } else if(selectedPart instanceof OutSourcedPart){
            sourceLabel.setText("Company Name");
            OutSourcedPart outsourced = (OutSourcedPart)selectedPart;
            partIDField.setText(Integer.toString(outsourced.getId()));
            partNameField.setText(outsourced.getName());
            partPriceField.setText(Double.toString(outsourced.getPrice()));
            partInvField.setText(Integer.toString(outsourced.getStock()));
            partMaxInvField.setText(Integer.toString(outsourced.getMax()));
            partMinInvField.setText(Integer.toString(outsourced.getMin()));
            partSourceField.setText(outsourced.getCompanyName());
            outsourcedRadioButton.setSelected(true);
            inhouseRadioButton.setSelected(false);
        }
    }

    /**This is the save method for modified parts. This method takes the information from the text fields and saves a new version of the part in the main screen part table.
     * @param actionEvent this is the event that occurs when the user clicks the save button.
     * @throws NumberFormatException
     * @throws ValidationException
     * @throws NullPointerException
     */
    public void saveModifiedPart(ActionEvent actionEvent) throws NumberFormatException, ValidationException, NullPointerException {

        int newID = Integer.parseInt(partIDField.getText());
        String newName = partNameField.getText();
        String newInv = partInvField.getText();
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
            if(isValid(newInhousePart)){
                Inventory.updatePart(newInhousePart);
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
            if(isValid(newOutsourcePart)){
                Inventory.updatePart(newOutsourcePart);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**This is the validate part method. This method performs logical checks to ensure the entered information is used to create a valid part.
     * @param part the part to validate
     * @return returns a boolean value, true if valid, else false.
     * @throws ValidationException
     */
    public boolean isValid(Part part) throws ValidationException{

        String name = partNameField.getText();
        int inv = Integer.parseInt(partInvField.getText());
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

    /**This is the cancel method. This method is an action performed when the user clicks the cancel button. It closes the modify screen and directs the user to the main screen.
     * @param actionEvent
     * @throws IOException
     */
    public void cancelModifyPart(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Modify Part");
        alert.setContentText("Are you sure you want to cancel?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        source = new ToggleGroup();
        this.inhouseRadioButton.setToggleGroup(source);
        this.outsourcedRadioButton.setToggleGroup(source);
        partIDField.setEditable(false);

    }

}
