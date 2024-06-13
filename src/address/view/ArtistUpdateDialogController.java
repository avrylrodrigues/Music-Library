package address.view;

// Importing libraries
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import address.model.Artist;

// ArtistUpdateDialogController class
public class ArtistUpdateDialogController {
    @FXML
    private TextField artistNameField;
    @FXML
    private TextField albumNameField;
    @FXML
    private TextField averageTimeField;
    @FXML
    private TextField song1Field;
    @FXML
    private TextField song2Field;
    @FXML
    private TextField song3Field;
    @FXML
    private TextField song4Field;
    private Stage dialogStage;
    private Artist artist;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    // Sets stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Sets artist to be edited
    public void setArtist(Artist artist) {
        this.artist = artist;
        artistNameField.setText(artist.getArtistName());
        albumNameField.setText(artist.getAlbumName());
        averageTimeField.setText(Integer.toString(artist.getAverageTime()));
        song1Field.setText(artist.getSong1());
        song2Field.setText(artist.getSong2());
        song3Field.setText(artist.getSong3());
        song4Field.setText(artist.getSong4());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    // Called when OK is clicked
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            artist.setArtistName(artistNameField.getText());
            artist.setAlbumName(albumNameField.getText());
            artist.setAverageTime(Integer.parseInt(averageTimeField.getText()));
            artist.setSong1(song1Field.getText());
            artist.setSong2(song2Field.getText());
            artist.setSong3(song3Field.getText());
            artist.setSong4(song4Field.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    // Called when cancel is clicked
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    // Checks if inputs are valid
    private boolean isInputValid() {
        TextField[] fields = {artistNameField, albumNameField, averageTimeField, song1Field, song2Field,
                song3Field, song4Field};
        StringBuilder errorMessage = new StringBuilder();
        for (TextField field : fields) {
            if (field.getText() == null || field.getText().isEmpty()) {
                errorMessage.append("Invalid ").append(field.getPromptText()).append("!\n");
            }
        }
        try {
            Integer.parseInt(averageTimeField.getText());
        // Handles any exception that may happen
        } catch (NumberFormatException e) {
            errorMessage.append("Invalid time (must be an integer)\n");
        }
        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Shows error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false;
        }
    }
}
