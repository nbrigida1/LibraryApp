/**
 * The BookInterface is a boilerplate for the Book
 * class. Books have the title, author, genre, and
 * status attributes. The status can be flipped.
 */
public interface BookInterface {
//	/**
//	 * Creates a Book with the provided title, author,
//	 * genre, status, and times checked out.
//	 *
//	 * @param title The title of the Book
//	 * @param author The author of the Book
//	 * @param genre The genre of the Book
//	 * @param status The status of the Book
//	 * @param timesCheckedOut The number of times the Book was checked out
//	 */
//	public Book(
//	  String title, String author,
//	  String genre, boolean status,
//	  int timesCheckedOut
//	);

    /**
     * Gets the title of the Book
     *
     * @return The title of the Book
     */
	String getTitle();

    /**
     * Gets the author of the Book
     *
     * @return The author of the Book
     */
	String getAuthor();

    /**
     * Gets the genre of the Book
     *
     * @return The genre of the Book
     */
	String getGenre();

    /**
     * Gets the status of the Book
     *
     * @return true if the Book is available,
     * otherwise false
     */
	boolean getStatus();

    /**
     * Updates the Book's status.
     *
     * @param newStatus true if the Book is now available,
     *                  otherwise false
     */
	void updateStatus(boolean newStatus);

    /**
     * Marks the Book as checked out (and no longer available).
     */
	void checkoutBook();

    /**
     * Marks the Book as returned (and now available).
     */
	void returnBook();

    /**
     * Gets the number of times the Book was checked out.
     *
     * @return The number of times the Book was checked out
     */
	int getTimesCheckedOut();
}