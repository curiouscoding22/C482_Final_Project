package inventoryManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**

 */

/**
 * This class runs the application.
 *
 * If this application were to be updated with additional features to extend its usability one feature I would add would be an additional section for tracking the pricing to note last ordered price for outsourced parts from a particular vendor. Working in procurement currently, it is important to keep a record of the price for an item especially if is sourced from multiple vendors. Knowing price trends and getting the best possible price are important purchasing decisions that must be considered each time something is ordered.
 *
 */
public class Main extends Application {

    /**
     * This is the start method. This method loads the main screen of the application.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/mainScreen.fxml"));
        primaryStage.setTitle("Sage 100 Constructor");
        primaryStage.setScene(new Scene(root, 1000, 457));
        primaryStage.show();
    }

    /**
     * This is the main method. This method launches the application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
