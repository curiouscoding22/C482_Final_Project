package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class mainScreen implements Initializable {

    //////////////////Main Screen, Part Functions////////////////////////////


    //Search the Part Table by Part ID or Name
    public void searchPartTable(ActionEvent actionEvent) {


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

            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyPartForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(root, 600, 495));
            stage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

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

    }


}
