package code;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    // GUI Components
    // Root component grid pane
    @FXML private GridPane mainGridPane;

    // the text boxes the user enters info into
    @FXML private TextField kilogramField;
    @FXML private TextField stoneField;
    @FXML private TextField poundField;
    @FXML private TextField ounceField;

    // the labels that can display error messages if the user enters something invalid
    @FXML private Label metricErrorLabel;
    @FXML private Label imperialErrorLabel;

    // the check boxes the user can tick to disable a unit's use
    @FXML private CheckBox stonesCheckBox;
    @FXML private CheckBox poundsCheckBox;
    @FXML private CheckBox ouncesCheckBox;

    Converter converter = new Converter();

    @FXML
    private void initialize() {
    }

    // methods called when enter is pressed within one of the text fields
    @FXML
    public void kilogramFieldChanged() {
        String newValue = kilogramField.textProperty().get();
        // checks if input is "valid"
        if (validate(newValue)) {
            // if the value is a valid decimal number remove error message
            metricErrorLabel.setText("");
            // only update if field isn't empty
            if (!newValue.isEmpty()) {
                updateMetric();
            }
        }
        else {
            // value is not a valid decimal number, give error message
            metricErrorLabel.setText("Invalid Input");
        }
    }
    @FXML
    public void stoneFieldChanged() {
        String newValue = stoneField.textProperty().getValue();
        changeImperial(newValue);
    }
    @FXML
    public void poundFieldChanged() {
        String newValue = poundField.textProperty().getValue();
        changeImperial(newValue);
    }
    @FXML
    public void ounceFieldChanged() {
        String newValue = ounceField.textProperty().getValue();
        changeImperial(newValue);
    }

    // methods called when checkbox is checked/unchecked they set the appropriate text field to be disables/enables and then updates the converter to which units need to be used
    @FXML
    public void stonesCheckBoxPressed() {
        stoneField.setDisable(stonesCheckBox.isSelected());
        converter.setUnitsInUse(!stonesCheckBox.isSelected(), converter.isPoundInUse(), converter.isOunceInUse());
    }
    @FXML
    public void poundsCheckBoxPressed() {
        poundField.setDisable(poundsCheckBox.isSelected());
        converter.setUnitsInUse(converter.isStoneInUse(), !poundsCheckBox.isSelected(), converter.isOunceInUse());
    }
    @FXML
    public void ouncesCheckBoxPressed() {
        ounceField.setDisable(ouncesCheckBox.isSelected());
        converter.setUnitsInUse(converter.isStoneInUse(), converter.isStoneInUse(), !ouncesCheckBox.isSelected());
    }

    // method that validates that an entered new value is valid before calling the update imperial method
    private void changeImperial(String newValue) {
        // checks if input is "valid"
        if(validate(newValue)) {
            // if the value is a valid decimal number remove error message
            imperialErrorLabel.setText("");
            if(!newValue.isEmpty()) {
                updateImperial();
            }
        }
        else {
            // value is not a valid decimal number, give error message
            imperialErrorLabel.setText("Invalid Input");
        }
    }

    // method takes input from imperial text fields and then passes these values to the converter which returns a value to change kilogram field to
    private void updateImperial() {

        double numberOfStones = Double.valueOf(stoneField.textProperty().get());
        double numberOfPounds = Double.valueOf(poundField.textProperty().get());
        double numberOfOunces = Double.valueOf(ounceField.textProperty().get());

        converter.changeImperial(numberOfStones, numberOfPounds, numberOfOunces);

        double totalKilograms = converter.getTotalKilograms();
        kilogramField.setText(String.valueOf(totalKilograms));

    }
    // method takes input from kilogram text and passes these values to the converter which returns appropriate imperial values
    private void updateMetric() {

        double numberOfKilograms = Double.valueOf(kilogramField.textProperty().get());
        converter.changeMetric(numberOfKilograms);

        int[] imperialUnits = converter.getImperialUnits();
        stoneField.setText(String.valueOf(imperialUnits[0]));
        poundField.setText(String.valueOf(imperialUnits[1]));
        ounceField.setText(String.valueOf(imperialUnits[2]));


    }

    // use a regex expression to check if the input is a valid number
    private boolean validate(String input) {
        return input.matches("\\d*(\\.\\d*)?") || input.matches("[0-9]*");
    }



}
