package address;

// Importing libraries
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import address.model.ArtistListWrapper;
import address.view.ArtistUpdateDialogController;
import address.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import address.model.Artist;
import address.view.ArtistDetailsController;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MusicLibrary extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Music Library");
        initRootLayout();
        showArtistDetails();
    }

    // Initialize artistData so that it can hold artist objects
    private ObservableList<Artist> artistData = FXCollections.observableArrayList();

    // Constructor
    public MusicLibrary() {
        // Sample data
        artistData.add(new Artist("Selena Gomez", "Rare"));
        artistData.add(new Artist("Stray Kids", "Oddinary"));
        artistData.add(new Artist("Stephen Sanchez", "Angel Face"));
        artistData.add(new Artist("Avril Lavigne", "Goodbye Lullaby"));
        artistData.add(new Artist("Charlie Puth", "Voicenotes"));
        artistData.add(new Artist("Little Mix", "LM5"));
    }

    // Helps display the list of artists
    public ObservableList<Artist> getArtistData() {
        return artistData;
    }

    public void initRootLayout() {
        try {
            // Loads root layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MusicLibrary.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Creates a new scene and sets it to the primary stage
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            primaryStage.show();
        // Handles any exception that may happen
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = getArtistFilePath();
        if (file != null) {
            loadArtistDataFromFile(file);
        }
    }

    // Shows the artist details
    public void showArtistDetails() {
        try {
            // Loads artist details
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MusicLibrary.class.getResource("view/ArtistDetails.fxml"));
            AnchorPane artistDetails = (AnchorPane) loader.load();
            rootLayout.setCenter(artistDetails);
            ArtistDetailsController controller = loader.getController();
            controller.setMain(this);
        // Handles any exception that may happen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Opens artist update dialog
    public boolean showArtistUpdateDialog(Artist artist) {
        try {
            // Loads artist update dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MusicLibrary.class.getResource("view/ArtistUpdateDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Artist");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            ArtistUpdateDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setArtist(artist);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        // Handles any exception that may happen
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public File getArtistFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MusicLibrary.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setArtistFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MusicLibrary.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("Artist Details - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("Artist Details");
        }
    }

    public void loadArtistDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ArtistListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            ArtistListWrapper wrapper = (ArtistListWrapper) um.unmarshal(file);
            artistData.clear();
            artistData.addAll(wrapper.getArtists());
            setArtistFilePath(file);
        // Handles any exception that may happen
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data!");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    public void saveArtistDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ArtistListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ArtistListWrapper wrapper = new ArtistListWrapper();
            wrapper.setArtists(artistData);
            m.marshal(wrapper, file);
            setArtistFilePath(file);
        // Handles any exception that may happen
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data!");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
}
