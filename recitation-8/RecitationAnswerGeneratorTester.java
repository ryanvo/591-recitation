import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class RecitationAnswerGeneratorTester {

    public static void main(String[] args) {

        final String PATH = "../locations.list";
        FileReaderv2 fileReader = null;
        ImdbLocationListParser parser = new ImdbLocationListParser();

        try {
            fileReader = new FileReaderv2(PATH);
        } catch (FileNotFoundException e) {
            System.out.println(PATH + " could not be opened...exiting");
            System.exit(1);
        }

        System.out.println("Press ENTER to proceed with parsing...WARNING every line will be " +
                "printed");
        Scanner cin = new Scanner(System.in);
        cin.nextLine();

        ImdbLocationList data = new ImdbLocationList(fileReader, parser);


        System.out.println("File parsed successfully...press ENTER to proceed with answers...");
        cin.nextLine();

        RecitationAnswerGenerator answers = new RecitationAnswerGenerator(data);
        String film = "In the heart of the sea";
        List<String> filmLocations = answers.queryFilmingLocation(film);

        System.out.println("Filming locations for \"" + film + "\":");
        for (String location : filmLocations) {
            System.out.println('\t' + location);
        }

        System.out.println("\nNumber of films filmed in Philly: ");
        System.out.print('\t' + answers.queryNumMoviesFilmedInPhilly());

    }

}
