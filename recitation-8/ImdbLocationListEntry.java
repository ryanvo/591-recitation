/**
 * Simple data structure to store each data entry in the file
 */
public class ImdbLocationListEntry {

    final private String title;
    final private String location;
    final private boolean isMovie;

    /**
     * Initialize with entries from row
     * @param title of the movie
     * @param location of filming
     * @param isMovie is true if it's a movie, false if it's a TV show
     */
    public ImdbLocationListEntry(String title, String location, boolean isMovie) {
        this.title = title;
        this.location = location;
        this.isMovie = isMovie;
    }

    /**
     * @return name of movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return location of filming
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return true if it's a movie, false if it's a TV show
     */
    public boolean isMovie() {
        return isMovie;
    }

}
