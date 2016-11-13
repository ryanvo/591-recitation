import java.util.StringTokenizer;

/**
 * Parses a line from the .list into a ImdbLocationListEntry object
 * @author ryan
 */
public class ImdbLocationListParser {

    private int lineNo = 15;

    /**
     * Parses the necessary fields to create ImdbLocationListEntry object
     * @param line from the .list file
     * @return ImdbLocationListEntry representing that line
     */
    ImdbLocationListEntry parse(String line) {

        String title = parseTitle(line);
        String remainingStr = line.substring(title.length() + 1);
        boolean isMovie = line.indexOf('{') == -1;

        /**
         * Read more about StringTokenizer on javadocs
         */
        StringTokenizer st = new StringTokenizer(remainingStr, "\t");
        st.nextToken(); // Discard one token
        String location = st.nextToken();

        /**
         * Add print statements to help debug
         */
        System.out.printf("Line #%d: \"%s\"\n", lineNo++, line);
        System.out.printf("Title=%s; Location=%s; isMovie=%b\n\n", title, location, isMovie);

        return new ImdbLocationListEntry(title, location, isMovie);
    }

    private String parseTitle(String line) {
        if (line.charAt(0) == '\"') {
            return line.substring(1, line.indexOf('\"', 1));
        } else {
            return line.substring(0, line.indexOf('(', 1) - 1);
        }

    }

}
