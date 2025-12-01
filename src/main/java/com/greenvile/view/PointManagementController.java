package com.greenvile.view;

import com.greenvile.viewmodel.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class PointManagementController {
    @FXML private ComboBox<String> frequencyCombo;
    @FXML private Label lastResetLabel;
    @FXML private Label poolLabel;

    private MainViewModel mainViewModel;
    private PointManagementViewModel pointViewModel;

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
        this.pointViewModel = new PointManagementViewModel(mainViewModel.getDataManager());
        loadData();
    }

    @FXML
    private void initialize() {
        frequencyCombo.getItems().addAll("Weekly", "Monthly", "Yearly");
    }

    private void loadData() {
        frequencyCombo.getSelectionModel().select(pointViewModel.getSettings().getResetFrequency());
        lastResetLabel.setText("Last Reset: " + pointViewModel.getLastResetDate());
        poolLabel.setText("Communal Points Pool: " + pointViewModel.getCommunalPoolTotal());
    }

    @FXML
    private void onFrequencyChange() {
        String selected = frequencyCombo.getSelectionModel().getSelectedItem();
        if (selected != null) {
            pointViewModel.setResetFrequency(selected);
            mainViewModel.saveAllData();
        }
    }

    @FXML
    private void onManualReset() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Reset");
        alert.setHeaderText("Manual Point Reset");
        alert.setContentText("Are you sure you want to reset all personal points? They will be transferred to the communal pool.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            pointViewModel.manualReset();
            mainViewModel.saveAllData();
            loadData();
        }
    }
}
