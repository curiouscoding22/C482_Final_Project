package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddPartForm implements Initializable{

    @FXML private TextField partIDField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvCountField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMaxAmountField;
    @FXML private TextField partMinAmountField;
    @FXML private TextField partSourceField;

    //Radiobuttons and label to change
    @FXML private RadioButton inhouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;
    @FXML private Label sourceLabel;
    private ToggleGroup source;

    //Random number generator for part numbers
    Random rand = new Random();


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

    }

    public int assignPartNumber(){
        int randPartID = rand.nextInt();
        if (randPartID > 1 || randPartID < 999999){
            randPartID = rand.nextInt();
        }
        return randPartID;
    }

    public void saveNewPart(ActionEvent actionEvent) throws IOException {

    }

}
