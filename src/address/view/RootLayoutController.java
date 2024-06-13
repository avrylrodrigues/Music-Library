package address.view;

// Importing libraries
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import address.MusicLibrary;

// RootLayoutController class
public class RootLayoutController {
    // Reference to main
    private MusicLibrary main;
    public void setMain(MusicLibrary main) {
        this.main = main;
    }

    @FXML
    private void handleNew() {
        main.getArtistData().clear();
        main.setArtistFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show open file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (file != null) {
            main.loadArtistDataFromFile(file);
        }
    }

    @FXML
    private void handleSave() {
        File artistFile = main.getArtistFilePath();
        if (artistFile != null) {
            main.saveArtistDataToFile(artistFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show save file dialog
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.saveArtistDataToFile(file);
        }
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Artist Details");
        alert.setHeaderText("About");
        alert.setContentText("Avryl Rodrigues - M00974288");
        alert.showAndWait();
    }

    // Exit the application
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
