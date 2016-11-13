import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data structure that stores all entries
 */
public class ImdbLocationList {

    private Map<String, List<ImdbLocationListEntry>> title = new HashMap<>();
    private Map<String, List<ImdbLocationListEntry>> location = new HashMap<>();

    public ImdbLocationList(FileReaderv2 fileReader, ImdbLocationListParser parser) {

        ArrayList<String> linesInFile = fileReader.getLines();

        /**
         * Ignore first 15 lines
         */
        int linesToRemove = 15;
        linesInFile.subList(0, linesToRemove).clear();

        for (String line : linesInFile) {

            ImdbLocationListEntry entry = parser.parse(line);

            /**
             * Store key in lowercase characters
             */
            String titleLowerCase = entry.getTitle().toLowerCase();
            String locationLowerCase = entry.getLocation().toLowerCase();

            /**
             * Store entry by title
             */
            storeByField(titleLowerCase, entry, title);


            /**
             * Store entry by location
             */
            storeByField(locationLowerCase, entry, location);
        }

    }

    public List<ImdbLocationListEntry> findByTitle(String titleToQuery) {
        return title.get(titleToQuery.toLowerCase());
    }

    public List<ImdbLocationListEntry> findByLocation(String locationToQuery) {
        return location.get(locationToQuery.toLowerCase());
    }

    private void storeByField(String field, ImdbLocationListEntry entry, Map<String, List<ImdbLocationListEntry>> map) {

        boolean isNew = map.get(field) == null;
        if (isNew) { // construct an empty list for a new map entry
            List<ImdbLocationListEntry> emptyListOfEntries = new ArrayList<>();
            map.put(field, emptyListOfEntries);
        }
        map.get(field).add(entry);
    }

}
