// --== CS400 Spring 2023 File Header Information ==--
// Name: Aryav Bharali
// Email: bharali@wisc.edu
// Team: CM
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The BookCheckoutBackend is the backend system for
 * the library checkout application which abstracts
 * an RBT.
 *
 * @author Aryav Bharali
 * @version March 29, 2023
 */
public class LibraryCheckoutBackend implements LibraryCheckoutBackendInterface {
    private final RBTLibraryInterface<Book> rbt;
    private final BookReaderInterface bookReader;
    private final Set<Book> allCheckedOut = new HashSet<>();
    private final Set<Book> allCurrentlyCheckedOut = new HashSet<>();

    public LibraryCheckoutBackend() {
        this.rbt = new RBTLibraryBD<>();
        this.bookReader = new BookReaderBD();
    }

    public LibraryCheckoutBackend(RBTLibrary<Book> rbt, BookReader bookReader) {
        this.rbt = rbt;
        this.bookReader = bookReader;
    }

    /**
     * Loads a dataset of Books from a file.
     *
     * @param fileName The file name to load from
     * @return true if all Books were loaded successfuly,
     *         otherwise false
     * @throws FileNotFoundException if the file name is invalid
     */
    @Override
    public boolean loadData(String fileName) throws FileNotFoundException {
        List<BookInterface> books = bookReader.readBooksFromFile(fileName);
        for (BookInterface book : books)
            if (!rbt.insert((Book)(book)))
                return false;

        return true;
    }

    /**
     * Gets the Book that matches the provided name.
     *
     * @param name The name to match
     * @return The matched Book
     */
    @Override
    public BookInterface getBook(String name) {
        try {
            RBTLibraryInterface.Node<Book> match = rbt.getNode(
                new Book(name, "", "", true, 0)
            );
            if (match != null) return match.data;
            else return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Marks the Book with the provided name as checked out.
     *
     * @param name The Book name to match
     * @return true if the Book was marked successfully,
     *         otherwise false
     */
    @Override
    public boolean checkOut(String name) {
        BookInterface match = getBook(name);
        if (match == null || !match.getStatus()) return false;

        match.checkoutBook();
        allCheckedOut.add((Book)(match));
        allCurrentlyCheckedOut.add((Book)(match));
        return true;
    }

    /**
     * Marks the Book with the provided name as checked in.
     *
     * @param name The Book name to match
     * @return true if the Book was marked successfully,
     *         otherwise false
     */
    @Override
    public boolean checkIn(String name) {
        BookInterface match = getBook(name);
        if (match == null || match.getStatus()) return false;

        match.returnBook();
        allCurrentlyCheckedOut.remove(match);
        return true;
    }

    /**
     * Removes the Book with the provided name
     * from the library/internal RBT.
     *
     * @param name The Book name to match
     * @return true if the Book was removed successfully,
     *         otherwise false
     */
    @Override
    public boolean removeBook(String name) {
        return rbt.remove(new Book(name, "", "", true, 0));
    }

    /**
     * Gets a List of books that are checked out and
     * are pending return.
     *
     * @return The List of book that need to be returned
     */
    @Override
    public List<BookInterface> getPendingReturns() {
        return new ArrayList<>(allCurrentlyCheckedOut);
    }

    /**
     * Generates a statistics string which has the
     * number of times each book was checked out
     * in the format "title: #" on each line.
     *
     * @return The statistics String
     */
    @Override
    public String getStatisticsString() {
        StringBuilder out = new StringBuilder();

        for (BookInterface book : allCheckedOut)
            if (book.getTimesCheckedOut() > 0)
                out.append(book.getTitle()).append(": ").append(book.getTimesCheckedOut()).append("\n");

        return out.toString();
    }
}
