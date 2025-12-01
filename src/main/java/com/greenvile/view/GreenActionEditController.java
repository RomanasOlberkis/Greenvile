package com.greenvile.view;

import com.greenvile.model.GreenAction;
import com.greenvile.model.Resident;
import com.greenvile.viewmodel.GreenActionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GreenActionEditController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField pointsField;
    @FXML private TextField pictureField;
    @FXML private ListView<Resident> participantListView;
    @FXML private Button saveButton;

    private GreenActionViewModel greenActionViewModel;
    private GreenAction action;
    private boolean isNewAction;

    public void setGreenActionViewModel(GreenActionViewModel greenActionViewModel) {
        this.greenActionViewModel = greenActionViewModel;
        loadResidents();
    }

    public void setAction(GreenAction action) {
        this.action = action;
        this.isNewAction = (action == null);

        if (!isNewAction) {
            titleField.setText(action.getTitle());
            descriptionArea.setText(action.getDescription());
            pointsField.setText(String.valueOf(action.getPointsAwarded()));
            pictureField.setText(action.getPicturePath());

            for (int i = 0; i < participantListView.getItems().size(); i++) {
                Resident r = participantListView.getItems().get(i);
                if (action.getParticipantIds().contains(r.getId())) {
                    participantListView.getSelectionModel().select(i);
                }
            }
        }
    }

    @FXML
    private void initialize() {
        participantListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        saveButton.setDisable(true);

        titleField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        descriptionArea.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        pointsField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        pictureField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
    }

    private void loadResidents() {
        participantListView.getItems().addAll(greenActionViewModel.getAllResidents());
    }

    private void checkChanges() {
        saveButton.setDisable(titleField.getText().isEmpty());
    }

    @FXML
    private void onBrowsePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(pictureField.getScene().getWindow());
        if (file != null) {
            pictureField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void onSave() {
        int points = 0;
        try {
            points = Integer.parseInt(pointsField.getText());
        } catch (NumberFormatException e) {
            points = 0;
        }

        List<Integer> selectedParticipants = new ArrayList<>();
        for (Resident r : participantListView.getSelectionModel().getSelectedItems()) {
            selectedParticipants.add(r.getId());
        }

        if (isNewAction) {
            GreenAction newAction = new GreenAction(
                0,
                titleField.getText(),
                descriptionArea.getText(),
                pictureField.getText(),
                points
            );
            newAction.setParticipantIds(selectedParticipants);
            greenActionViewModel.addAction(newAction);
        } else {
            action.setTitle(titleField.getText());
            action.setDescription(descriptionArea.getText());
            action.setPointsAwarded(points);
            action.setPicturePath(pictureField.getText());
            action.setParticipantIds(selectedParticipants);
            greenActionViewModel.updateAction(action);
        }

        closeWindow();
    }

    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
