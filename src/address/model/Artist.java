package address.model;

// Importing libraries
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Artist class
public class Artist {
    private final StringProperty artistName;
    private final StringProperty albumName;
    private final IntegerProperty averageTime;
    private final StringProperty song1;
    private final StringProperty song2;
    private final StringProperty song3;
    private final StringProperty song4;

    /* Default constructor
    initializes the attributes */
    public Artist() {
        this(null, null);
    }

    /* Constructor
    gives the attributes values */
    public Artist(String artistName, String albumName) {
        this.artistName = new SimpleStringProperty(artistName);
        this.albumName = new SimpleStringProperty(albumName);
        // default values
        this.averageTime = new SimpleIntegerProperty(0);
        this.song1 = new SimpleStringProperty("Song 1");
        this.song2 = new SimpleStringProperty("Song 2");
        this.song3 = new SimpleStringProperty("Song 3");
        this.song4 = new SimpleStringProperty("Song 4");
    }

    /* Getter method
    retrieves the value */
    public String getArtistName() {
        return artistName.get();
    }
    /* Setter method
    sets the value */
    public void setArtistName(String artistName) {
        this.artistName.set(artistName);
    }
    // Property accessor method
    public StringProperty artistNameProperty() {
        return artistName;
    }

    /* Getter method
    retrieves the value */
    public String getAlbumName() {
        return albumName.get();
    }
    /* Setter method
    sets the value */
    public void setAlbumName(String albumName) {
        this.albumName.set(albumName);
    }
    // Property accessor method
    public StringProperty albumNameProperty() {
        return albumName;
    }

    /* Getter method
    retrieves the value */
    public int getAverageTime() {
        return averageTime.get();
    }
    /* Setter method
    sets the value */
    public void setAverageTime(int averageTime) {
        this.averageTime.set(averageTime);
    }
    // Property accessor method
    public IntegerProperty averageTimeProperty() {
        return averageTime;
    }

    /* Getter method
    retrieves the value */
    public String getSong1() {
        return song1.get();
    }
    /* Setter method
    sets the value */
    public void setSong1(String song1) {
        this.song1.set(song1);
    }
    // Property accessor method
    public StringProperty song1Property() {
        return song1;
    }

    /* Getter method
    retrieves the value */
    public String getSong2() {
        return song2.get();
    }
    /* Setter method
    sets the value */
    public void setSong2(String song2) {
        this.song2.set(song2);
    }
    // Property accessor method
    public StringProperty song2Property() {
        return song2;
    }

    /* Getter method
    retrieves the value */
    public String getSong3() {
        return song3.get();
    }
    /* Setter method
    sets the value */
    public void setSong3(String song3) {
        this.song3.set(song3);
    }
    // Property accessor method
    public StringProperty song3Property() {
        return song3;
    }

    /* Getter method
    retrieves the value */
    public String getSong4() {
        return song4.get();
    }
    /* Setter method
    sets the value */
    public void setSong4(String song4) {
        this.song4.set(song4);
    }
    // Property accessor method
    public StringProperty song4Property() {
        return song4;
    }
}
