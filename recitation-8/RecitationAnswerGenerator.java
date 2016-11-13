import java.util.ArrayList;
import java.util.List;

/**
 * Answers the three questions posted in recitation 8
 * @author ryan
 */
public class RecitationAnswerGenerator {

    private ImdbLocationList data;

    public RecitationAnswerGenerator(ImdbLocationList data) {
        this.data = data;
    }

    /**
     * Retrieves the filming locations for a title
     * @param title of movie/tv show
     * @return a list of filming locations
     */
    public List<String> queryFilmingLocation(String title) {
        List<ImdbLocationListEntry> entriesWithTitle = data.findByTitle(title);

        List<String> locations = new ArrayList<>();
        for (ImdbLocationListEntry entry : entriesWithTitle) {
            locations.add(entry.getLocation());
        }

        return locations;
    }

    /**
     * Retrieves the number of movies filmed in Philly
     * @return number of movies
     */
    public int queryNumMoviesFilmedInPhilly() {
        int count = 0;
        for (ImdbLocationListEntry entry : data.findByLocation("Philadelphia, Pennsylvania, USA")) {
            if (entry.isMovie()) {
                count++;
            }
        }
        return count;
    }

}
