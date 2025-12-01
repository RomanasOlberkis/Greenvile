package com.greenvile.view;

import com.greenvile.model.Resident;
import com.greenvile.viewmodel.ResidentViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class ResidentEditController {
    @FXML private TextField fullNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private TextField pointsField;
    @FXML private TextField pictureField;
    @FXML private Button saveButton;

    private ResidentViewModel residentViewModel;
    private Resident resident;
    private boolean isNewResident;

    public void setResidentViewModel(ResidentViewModel residentViewModel) {
        this.residentViewModel = residentViewModel;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
        this.isNewResident = (resident == null);

        if (!isNewResident) {
            fullNameField.setText(resident.getFullName());
            phoneField.setText(resident.getPhoneNumber());
            emailField.setText(resident.getEmail());
            addressField.setText(resident.getAddress());
            pointsField.setText(String.valueOf(resident.getPersonalPoints()));
            pictureField.setText(resident.getPicturePath());
        }
    }

    @FXML
    private void initialize() {
        saveButton.setDisable(true);

        fullNameField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        phoneField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        emailField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        addressField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        pointsField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        pictureField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
    }

    private void checkChanges() {
        if (isNewResident) {
            saveButton.setDisable(fullNameField.getText().isEmpty());
        } else {
            boolean changed = !fullNameField.getText().equals(resident.getFullName()) ||
                !phoneField.getText().equals(resident.getPhoneNumber()) ||
                !emailField.getText().equals(resident.getEmail()) ||
                !addressField.getText().equals(resident.getAddress()) ||
                !pointsField.getText().equals(String.valueOf(resident.getPersonalPoints())) ||
                !pictureField.getText().equals(resident.getPicturePath());
            saveButton.setDisable(!changed);
        }
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

        if (isNewResident) {
            Resident newResident = new Resident(
                0,
                fullNameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText(),
                pictureField.getText(),
                points
            );
            residentViewModel.addResident(newResident);
        } else {
            resident.setFullName(fullNameField.getText());
            resident.setPhoneNumber(phoneField.getText());
            resident.setEmail(emailField.getText());
            resident.setAddress(addressField.getText());
            resident.setPersonalPoints(points);
            resident.setPicturePath(pictureField.getText());
            residentViewModel.updateResident(resident);
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
