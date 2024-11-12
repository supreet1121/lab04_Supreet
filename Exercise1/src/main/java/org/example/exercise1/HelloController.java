package org.example.exercise1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class HelloController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField provinceField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private CheckBox studentCouncilCheckbox;

    @FXML
    private CheckBox volunteerWorkCheckbox;

    @FXML
    private RadioButton csRadioButton;

    @FXML
    private RadioButton businessRadioButton;

    @FXML
    private ComboBox<String> coursesComboBox;
    @FXML
    private TextField coursesTextField;

    @FXML
    private TextArea displayArea;

    private ToggleGroup programGroup;

    @FXML
    private void initialize() {
        coursesComboBox.setItems(FXCollections.observableArrayList("Java", "Python", "C#", "JavaScript"));

        programGroup = new ToggleGroup();
        csRadioButton.setToggleGroup(programGroup);
        businessRadioButton.setToggleGroup(programGroup);


        coursesComboBox.setOnAction(e -> onCourseSelected());
    }

    @FXML
    private void onCourseSelected() {
        String selectedCourse = coursesComboBox.getValue();

        if (selectedCourse != null && !selectedCourse.isEmpty()) {
            String currentText = coursesTextField.getText();
            if (!currentText.contains(selectedCourse)) {
                if (!currentText.isEmpty()) {
                    coursesTextField.setText(currentText + ", " + selectedCourse);
                } else {
                    coursesTextField.setText(selectedCourse);
                }
            }
        }
    }

    @FXML
    private void onDisplayButtonClick() {
        String name = nameField.getText();
        String address = addressField.getText();
        String province = provinceField.getText();
        String city = cityField.getText();
        String postalCode = postalCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();
        String selectedCourses = coursesTextField.getText();

        if (name.isBlank() || address.isBlank() || province.isBlank() || city.isBlank() ||
                postalCode.isBlank() || phoneNumber.isBlank() || email.isBlank() || selectedCourses.isBlank()) {
            showAlert("Input Error", "Please fill in all fields and select at least one course.");
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append(name).append(", ").append(address).append(", ").append(city).append(", ")
                .append(province).append(", ").append(postalCode).append(", ").append(phoneNumber)
                .append(", ").append(email).append("\n");

        if (studentCouncilCheckbox.isSelected()) result.append("Student Council\n");
        if (volunteerWorkCheckbox.isSelected()) result.append("Volunteer Work\n");

        if (csRadioButton.isSelected()) result.append("Program: Computer Science\n");
        if (businessRadioButton.isSelected()) result.append("Program: Business\n");

        result.append("Courses:\n").append(selectedCourses);

        displayArea.setText(result.toString());
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
