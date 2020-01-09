package bullsandcows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kiryl Matusevich
 * 
 * BullsAndCows v.1.0 
 */
public class BullsAndCows extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Main.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/StyleW.css");
        
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.setTitle("Bulls And Cows v.1.0");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
