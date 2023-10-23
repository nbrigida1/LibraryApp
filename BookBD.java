// --== CS400 Spring 2023 File Header Information ==--
// Name: Aryav Bharali
// Email: bharali@wisc.edu
// Team: CM
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: None

/**
 * BookBD is a mock class for the Book class
 * for the BackendDeveloper tests.
 *
 * @author Aryav Bharali
 * @version March 29, 2023
 */
public class BookBD implements BookInterface {
    private final String title;
    private final String author;
    private final String genre;
    private boolean status; // true/false => available/checked out
    private int timesCheckedOut;

    /**
     * Creates a BookBD with the given title,
     * author, genre, and status.
     *
     * @param title The Book's title
     * @param author The Book's author
     * @param genre The Book's genre
     * @param status true if the Book is available
     */
    public BookBD(String title, String author, String genre, boolean status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.timesCheckedOut = 0;
    }

    /**
     * Gets the Book's title.
     *
     * @return The Book's title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Gets the Book's author.
     *
     * @return The Book's author
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the Book's genre.
     *
     * @return The Book's genre
     */
    @Override
    public String getGenre() {
        return genre;
    }

    /**
     * Determines whether the Book is
     * available to be checked out.
     *
     * @return true if the Book is available,
     *         otherwise false
     */
    @Override
    public boolean getStatus() {
        return status;
    }

    /*
     * Sets the Book's status with
     * the provided updated status.
     * 
     * @param newStatus The new status
     */
    public void updateStatus(boolean newStatus) {
        this.status = newStatus;
    }

    /**
     * Marks the Book as checked out if possible.
     */
    @Override
    public void checkoutBook() {
        status = false;
        timesCheckedOut++;
    }

    /**
     * Marks the Book as checked in if possible.
     */
    @Override
    public void returnBook() {
        status = true;
    }

    /**
     * Determines the number of times the Book was checked out.
     *
     * @return The number of times
     */
    @Override
    public int getTimesCheckedOut() {
        return timesCheckedOut;
    }
}
