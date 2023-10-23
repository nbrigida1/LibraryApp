// --== CS400 Spring 2023 File Header Information ==--
// Name: Aryav Bharali
// Email: bharali@wisc.edu
// Team: CM
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The BookReaderBD class is a mock class for the
 * BookReader class for BackendDeveloper tests.
 *
 * @author Aryav Bharali
 * @version March 29, 2023
 */
public class BookReaderBD implements BookReaderInterface {
    /**
     * "Reads" books from a file where there are
     * three cases "invalid.txt," "small.txt,"
     * and "big.txt."
     *
     * @param filename A file name
     * @return The List
     * @throws FileNotFoundException when invalid.txt is provided
     */
    @Override
    public List<BookInterface> readBooksFromFile(String filename) throws FileNotFoundException {
        if (filename.equals("invalid.txt")) throw new FileNotFoundException("File DNE");

        List<BookInterface> out = new ArrayList<>();
        out.add(new Book("A", "aAuthor", null, true, 0));
        out.add(new Book("B", "bAuthor", null, true, 0));

        if (filename.equals("big.txt")) {
            out.add(new Book("C", "cAuthor", null, true, 0));
            out.add(new Book("D", "dAuthor", null, true, 0));
        }
        return out;
    }
}
