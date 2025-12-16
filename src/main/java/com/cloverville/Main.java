package com.cloverville;

import com.cloverville.view.MainMenuController;
import com.cloverville.viewmodel.MainViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainViewModel mainViewModel = new MainViewModel();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cloverville/fxml/MainMenu.fxml"));
        Parent root = loader.load();

        MainMenuController controller = loader.getController();
        controller.setMainViewModel(mainViewModel);

        primaryStage.setTitle("Cloverville Admin");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
