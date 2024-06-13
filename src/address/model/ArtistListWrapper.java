package address.model;

// Importing libraries
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artists")
// ArtistListWrapper class
public class ArtistListWrapper {
    // Creates a field to hold list of artist details
    private List<Artist> artists;
    @XmlElement(name = "artists")
    // Gets the list of artists
    public List<Artist> getArtists() {
        return artists;
    }
    // Sets the list of artists
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
