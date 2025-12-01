package com.greenvile.view;

import com.greenvile.model.CommunalTask;
import com.greenvile.viewmodel.CommunalViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CommunalTaskEditController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField pointsField;
    @FXML private Button saveButton;

    private CommunalViewModel communalViewModel;
    private int goalId;
    private CommunalTask task;
    private boolean isNewTask;

    public void setCommunalViewModel(CommunalViewModel communalViewModel) {
        this.communalViewModel = communalViewModel;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setTask(CommunalTask task) {
        this.task = task;
        this.isNewTask = (task == null);

        if (!isNewTask) {
            titleField.setText(task.getTitle());
            descriptionArea.setText(task.getDescription());
            pointsField.setText(String.valueOf(task.getPointsAwarded()));
        }
    }

    @FXML
    private void initialize() {
        saveButton.setDisable(true);

        titleField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        descriptionArea.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
        pointsField.textProperty().addListener((obs, oldVal, newVal) -> checkChanges());
    }

    private void checkChanges() {
        saveButton.setDisable(titleField.getText().isEmpty());
    }

    @FXML
    private void onSave() {
        int points = 0;
        try {
            points = Integer.parseInt(pointsField.getText());
        } catch (NumberFormatException e) {
            points = 0;
        }

        if (isNewTask) {
            CommunalTask newTask = new CommunalTask(
                0,
                titleField.getText(),
                descriptionArea.getText(),
                points
            );
            communalViewModel.addTaskToGoal(goalId, newTask);
        } else {
            task.setTitle(titleField.getText());
            task.setDescription(descriptionArea.getText());
            task.setPointsAwarded(points);
            communalViewModel.updateTask(goalId, task);
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
