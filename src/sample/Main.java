package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;


public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Controller controller = new Controller();
        if(controller.saved)
            primaryStage.setTitle(controller.savedFileName.getName() + " ~ Text Editor");
        else
            primaryStage.setTitle("Untitled ~ Text Editor");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /*@Override
    public void stop() {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("popUpForSave.fxml"));
//            popUpForSave p =
        try
        {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popUpForSave.fxml"));
            Parent root1 = fxmlLoader.load();
//            popUpForSave p = fxmlLoader.getController();
            Stage stage = new Stage();
//            p.setTextArea(t);
            stage.setTitle("Text Editor");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
//                System.out.println("Cant load new window");
        }
        System.out.println("tanuj");
//        Platform.exit();
    }*/


    public static void main(String[] args) {
        launch(args);
    }
}
