import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The BookReader class reads a file with all the Book
 * names and info and processes it into a List.
 */
public class BookReader implements BookReaderInterface {
    private boolean status;

    /**
     * Reads in a list of Books from a file.
     *
     * @param filename The file name to read from
     * @return A List of the Books that were read in
     * @throws FileNotFoundException if the file name doesn't exist
     */
    @Override
    public List<BookInterface> readBooksFromFile(String filename) throws FileNotFoundException {
        ArrayList<BookInterface> books = new ArrayList<>();
        Scanner in = new Scanner(new File(filename)); // in contains the info of file

        while (in.hasNextLine()) {//while in has a next line
            // for each line in the file being read:
            String line = in.nextLine();
            // split that line into parts around the delimiter: ,
            String[] parts = line.split(",");
            char quotes = '"';
            String quote = quotes + "";
            // most lines should have all three parts
            ////////////////////////////Normal Case\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            if (parts.length == 5) {

                status = !Objects.equals(parts[4], "issued");
                books.add(new Book(parts[1], parts[2], parts[3], status, 0));
                // although are missing a body: we'll use empty string in those cases
            }
            /////////////// ///Case where the title has commas in it\\\\\\\\\\\\\\\\\\\\\\\
            else if (line.contains(quote)) {
                String[] parts2 = line.split(quote);
                if (parts2.length == 3) {//this contains the title separated
                    String[] part3 = parts2[2].split(",");
                    if (part3.length == 4) {
                        status = Objects.equals(part3[3], "issued");
                    }
                    books.add(new Book(parts2[1], part3[1], part3[2], status, 0));//makes books object
                }
            } else {
                System.out.println("Warning: found a line without exactly 2 or 3 parts: " + line);
            }
        }
        // then close the scanner before returning the list of posts
        in.close();
        return books;
    }
}
