package address.view;

// Importing libraries
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import address.MusicLibrary;
import address.model.Artist;

// ArtistDetailsController class
public class ArtistDetailsController {
    @FXML
    private TableView<Artist> artistTable;
    @FXML
    private TableColumn<Artist, String> artistNameColumn;
    @FXML
    private TableColumn<Artist, String> albumNameColumn;
    @FXML
    private Label artistNameLabel;
    @FXML
    private Label albumNameLabel;
    @FXML
    private Label averageTimeLabel;
    @FXML
    private Label song1Label;
    @FXML
    private Label song2Label;
    @FXML
    private Label song3Label;
    @FXML
    private Label song4Label;

    // Reference to main
    private MusicLibrary main;

    // Constructor
    public ArtistDetailsController() {
    }

    @FXML
    private void initialize() {
        // Initializes the artist table with the two columns
        artistNameColumn.setCellValueFactory(cellData -> cellData.getValue().artistNameProperty());
        albumNameColumn.setCellValueFactory(cellData -> cellData.getValue().albumNameProperty());
        // Clear artist details.
        showArtistDetails(null);
        // Listen for selection changes and show the new details when changed
        artistTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showArtistDetails(newValue));
    }

    public void setMain(MusicLibrary main) {
        this.main = main;
        // Adds list data to the table
        artistTable.setItems(main.getArtistData());
    }
    // Fills text fields with artist details
    private void showArtistDetails(Artist artist) {
        if (artist != null) {
            // Fill the labels with info from the artist object
            artistNameLabel.setText(artist.getArtistName());
            albumNameLabel.setText(artist.getAlbumName());
            averageTimeLabel.setText(Integer.toString(artist.getAverageTime()));
            song1Label.setText(artist.getSong1());
            song2Label.setText(artist.getSong2());
            song3Label.setText(artist.getSong3());
            song4Label.setText(artist.getSong4());

        } else {
            // If artist is null it removes all the text
            artistNameLabel.setText("");
            albumNameLabel.setText("");
            averageTimeLabel.setText("");
            song1Label.setText("");
            song2Label.setText("");
            song3Label.setText("");
            song4Label.setText("");
        }
    }

    // Called when the delete button is clicked
    @FXML
    private void handleDeleteArtist() {
        int selectedIndex = artistTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            artistTable.getItems().remove(selectedIndex);
        } else {
            // When no artist is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection made");
            alert.setHeaderText("No Artist Selected!");
            alert.setContentText("Select an artist from the table to be deleted");
            alert.showAndWait();
        }
    }

    // Called when the new button is clicked
    @FXML
    private void handleNewArtist() {
        Artist tempArtist = new Artist();
        boolean okClicked = main.showArtistUpdateDialog(tempArtist);
        if (okClicked) {
            main.getArtistData().add(tempArtist);
        }
    }

    // Called when the update button is clicked
    @FXML
    private void handleUpdateArtist() {
        Artist selectedArtist = artistTable.getSelectionModel().getSelectedItem();
        if (selectedArtist != null) {
            boolean okClicked = main.showArtistUpdateDialog(selectedArtist);
            if (okClicked) {
                showArtistDetails(selectedArtist);
            }

        } else {
            // When no artist is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection made");
            alert.setHeaderText("No Artist Selected!");
            alert.setContentText("Select an artist from the table to be updated");
            alert.showAndWait();
        }
    }
}
