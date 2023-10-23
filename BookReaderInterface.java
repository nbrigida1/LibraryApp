import java.io.FileNotFoundException;
import java.util.List;

/**
 * The BookReaderInterface is a boilerplate for the BookReader class,
 * which reads in a list of Books from a given file.
 */
public interface BookReaderInterface {
//  /**
//   * Creates a BookReader.
//   */
//  public BookReader();

    /**
     * Reads in a list of Books from a file.
     *
     * @param filename The file name to read from
     * @return A List of the Books that were read in
     * @throws FileNotFoundException if the file name doesn't exist
     */
    public List<BookInterface> readBooksFromFile(String filename) throws FileNotFoundException;
}
