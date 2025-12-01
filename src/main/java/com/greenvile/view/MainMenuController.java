package com.greenvile.view;

import com.greenvile.viewmodel.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {
    private MainViewModel mainViewModel;

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    @FXML
    private void openResidents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/greenvile/fxml/ResidentView.fxml"));
            Parent root = loader.load();
            ResidentController controller = loader.getController();
            controller.setMainViewModel(mainViewModel);
            Stage stage = new Stage();
            stage.setTitle("Residents");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openGreenActions() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/greenvile/fxml/GreenActionView.fxml"));
            Parent root = loader.load();
            GreenActionController controller = loader.getController();
            controller.setMainViewModel(mainViewModel);
            Stage stage = new Stage();
            stage.setTitle("Green Actions");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openCommunal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/greenvile/fxml/CommunalView.fxml"));
            Parent root = loader.load();
            CommunalController controller = loader.getController();
            controller.setMainViewModel(mainViewModel);
            Stage stage = new Stage();
            stage.setTitle("Communal Tasks");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openTrades() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/greenvile/fxml/TradeView.fxml"));
            Parent root = loader.load();
            TradeController controller = loader.getController();
            controller.setMainViewModel(mainViewModel);
            Stage stage = new Stage();
            stage.setTitle("Trades");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openPointManagement() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/greenvile/fxml/PointManagementView.fxml"));
            Parent root = loader.load();
            PointManagementController controller = loader.getController();
            controller.setMainViewModel(mainViewModel);
            Stage stage = new Stage();
            stage.setTitle("Point Management");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
