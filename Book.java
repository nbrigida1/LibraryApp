/**
 * Books have the title, author, genre, and
 * status attributes. The status can be flipped.
 */
public class Book implements BookInterface, Comparable<Book> {
    private final String title;
    private final String author;
    private final String genre;
    private boolean status;
    private int timesCheckedOut;

    /**
     * Creates a Book with the provided title, author,
     * genre, status, and times checked out.
     *
     * @param title           The title of the Book
     * @param author          The author of the Book
     * @param genre           The genre of the Book
     * @param status          The status of the Book
     * @param timesCheckedOut The number of times the Book was checked out
     */
    public Book(String title, String author, String genre, boolean status, int timesCheckedOut) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.timesCheckedOut = timesCheckedOut;
    }

    /**
     * Gets the title of the Book
     *
     * @return The title of the Book
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Gets the author of the Book
     *
     * @return The author of the Book
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the genre of the Book
     *
     * @return The genre of the Book
     */
    @Override
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the status of the Book
     *
     * @return true if the Book is available,
     * otherwise false
     */
    @Override
    public boolean getStatus() {
        return status;
    }

    /**
     * Updates the Book's status.
     *
     * @param newStatus true if the Book is now available,
     *                  otherwise false
     */
    @Override
    public void updateStatus(boolean newStatus) {
        this.status = newStatus;

    }

    /**
     * Marks the Book as checked out (and no longer available).
     */
    @Override
    public void checkoutBook() {
        timesCheckedOut++;
        updateStatus(false);

    }

    /**
     * Marks the Book as returned (and now available).
     */
    @Override
    public void returnBook() {
        updateStatus(true);
    }

    /**
     * Gets the number of times the Book was checked out.
     *
     * @return The number of times the Book was checked out
     */
    @Override
    public int getTimesCheckedOut() {
        return timesCheckedOut;
    }

    /**
     * Determines whether obj is a Book and has the same
     * title, author, and genre as this Book.
     *
     * @param obj Any Object
     * @return true if obj is a Book and is equal,
     *         otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BookInterface) {
            BookInterface other = (BookInterface)(obj);
            return title.equals(other.getTitle());
        }

        return false;
    }

    /**
     * Compares this Book to another Book by its title.
     *
     * @param otherBook The Book to be compared to
     * @return An integer of the title difference
     */
    @Override
    public int compareTo(Book otherBook) {
        if (otherBook == null)
            throw new IllegalStateException("otherBook cannot be NULL");

        return this.title.compareTo(otherBook.getTitle());
    }
}
