package Controller;

import Model.InHousePart;
import Model.OutSourcedPart;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    public void changeSourceLabel(){
        if(source.getSelectedToggle().equals(inhouseRadioButton)){
            sourceLabel.setText("Machine ID");
        }else if(source.getSelectedToggle().equals(outsourcedRadioButton)){
            sourceLabel.setText("Company Name");
        }
    }

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
