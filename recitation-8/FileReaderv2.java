import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads in a file that is specified. This will use the "specify"
 * option for handling exceptions.
 * 
 * @author swapneel
 *
 */
public class FileReaderv2 {

	private String filename;
	private ArrayList<String> lines;

	/**
	 * The constructor
	 * 
	 * @param file
	 *            the file to read
	 * @throws FileNotFoundException 
	 */
	public FileReaderv2(String file) throws FileNotFoundException {
		filename = file;

		lines = new ArrayList<String>();

		readFile();
	}

	/**
	 * This will read in the entire file. It will store the contents in the
	 * lines ArrayList.
	 * @throws FileNotFoundException 
	 */
	private void readFile() throws FileNotFoundException {

		File inputFile = new File(filename);
		Scanner in = new Scanner(inputFile);

		while (in.hasNextLine()) {
			String line = in.nextLine();
			lines.add(line);
		}
		
		in.close();

	}

	/**
	 * The accessor method for lines
	 * 
	 * @return the lines arraylist
	 */
	public ArrayList<String> getLines() {
		return lines;
	}

}
