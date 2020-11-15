package Controller;

import Model.InHousePart;
import Model.OutSourcedPart;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        source = new ToggleGroup();
        this.inhouseRadioButton.setToggleGroup(source);
        this.outsourcedRadioButton.setToggleGroup(source);

    }
}
