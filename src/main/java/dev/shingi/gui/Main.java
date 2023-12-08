package dev.shingi.gui;

import java.io.IOException;

import dev.shingi.services.ResourceManager;
import dev.shingi.services.SaveData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static SaveData save = new SaveData();
    public static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        loadFXML("/snapshot_list_subscene");
        loadFXML("/customer_list_subscene");
        loadFXML("/customer_details_subscene");
        Parent primaryRoot = loadFXML("/primary");

        scene = new Scene(primaryRoot);
        scene.getMnemonics();
        stage.setScene(scene);
        
        Image icon = new Image("/Logo (zonder kroon) photoshop.png");
        stage.getIcons().add(icon);
        stage.setTitle("RengerCustomers");

        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static boolean saveData() {
		try {
			ResourceManager.saveData(Main.save, "data");
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
	}
}
