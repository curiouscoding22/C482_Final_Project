package Controller;

import Model.InHousePart;
import Model.OutSourcedPart;
import Model.Part;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ModifyPartForm {

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
    private ToggleGroup source;





    private Part part;

    public void retrieveSelectPart(Part selectedPart) {
        this.part = selectedPart;
        partIDField.setText(Integer.toString(selectedPart.getId()));
        partNameField.setText(selectedPart.getName());
        partPriceField.setText(Double.toString(selectedPart.getPrice()));
        partInvField.setText(Integer.toString(selectedPart.getStock()));
        partMaxInvField.setText(Integer.toString(selectedPart.getMax()));
        partMinInvField.setText(Integer.toString(selectedPart.getMin()));
        if(selectedPart instanceof InHousePart){
            InHousePart inhouse = (InHousePart)selectedPart;
            partSourceField.setText(Integer.toString(selectedPart.getId()));
            sourceLabel.setText("Machine ID");
            inhouseRadioButton.setSelected(true);
            outsourcedRadioButton.setSelected(false);
        } else if(selectedPart instanceof OutSourcedPart){
            OutSourcedPart outsourced = (OutSourcedPart)selectedPart;
            partSourceField.setText(((OutSourcedPart) selectedPart).getCompanyName());
            sourceLabel.setText("Company Name");
            inhouseRadioButton.setSelected(false);
            outsourcedRadioButton.setSelected(true);
        }



    }
}
