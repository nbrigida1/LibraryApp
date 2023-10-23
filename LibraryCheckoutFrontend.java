import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Provide a text-based user interface to the operations of the Library Checkout application
 *
 * @author Hunter Heller
 */
public class LibraryCheckoutFrontend implements LibraryCheckoutFrontendInterface {
    private final Scanner userInput;
    private final LibraryCheckoutBackendInterface backend;

    /**
     * Constructor for frontend to make use of Scanner and LibraryCheckoutBackend
     *
     * @param userInput A Scanner to pull user input from
     * @param backend   The backend to use for interacting with the Library
     */
    public LibraryCheckoutFrontend(Scanner userInput, LibraryCheckoutBackendInterface backend) {
        this.userInput = userInput;
        this.backend = backend;
    }

    /**
     * After creating an instance of the RBT, backend,
     * and frontend, it runs the command loop.
     *
     * @param args An array of String command line arguments
     */
    public static void main(String[] args) {
        RBTLibrary<Book> rbt = new RBTLibrary<>();
        BookReader bookReader = new BookReader();
        LibraryCheckoutBackend bd = new LibraryCheckoutBackend(rbt, bookReader);
        LibraryCheckoutFrontend fd = new LibraryCheckoutFrontend(new Scanner(System.in), bd);
        fd.runCommandLoop();
    }

    /**
     * Private helper to display lines above and below welcome and exit messages
     */
    private void hr() {
        System.out.println("----------------------------------------------------------------------"
                + "-----------------------------------------------");
    }

    /**
     * Repeatedly prompts user for input and display the correct operation for each.
     * Exits when "Q" is pressed.
     */
    public void runCommandLoop() {
        hr(); //lines to separate message
        System.out.println("Welcome to CM Library’s Checkout!");
        hr(); //same as above

        String command = "";
        while (!command.equals("Q")) { //continues until user inputs "Q"
            command = this.mainMenuPrompt();
            switch (command) {
                case "L": //System.out.println("[L]: Load Library Data");
                    loadDataCommand();
                    break;
                case "SB": //System.out.println("[SB]: Search for Book by Name");
                    System.out.println("Enter the name of the book you want to search for: ");
                    String SBname = userInput.nextLine().trim();
                    searchBookCommand(SBname);
                    break;
                case "CO": //System.out.println("[CO]: Checkout Book");
                    System.out.println("Enter the name of the book you would like to check out: ");
                    String COname = userInput.nextLine().trim();
                    checkOutCommand(COname);
                    break;
                case "CI": //System.out.println("[CI]: Checkin Book");
                    System.out.println("Enter the name of the book you would like to check in: ");
                    String CIname = userInput.nextLine().trim();
                    checkInCommand(CIname);
                    break;
                case "R": //System.out.println("[R]: Remove Book");
                    System.out.println("Enter the name of the book you would like to remove: ");
                    String Rname = userInput.nextLine().trim();
                    removeBookCommand(Rname);
                    break;
                case "P": //System.out.println("[P]: Get Pending Returns");
                    displayPendingReturnsCommand();
                    break;
                case "ST": //System.out.println("[ST]: Get Statistics");
                    displayStatsCommand();
                    break;
                case "Q": //System.out.println("[Q]: Quit");
                    break;
                default:
                    System.out.println("Command not recognized");
                    break;
            }
        }
        hr();
        System.out.println("Thank you for using CM’s Library Checkout!");
        hr();
    }

    /**
     * Prints the command options to System.out and reads user's choice through
     * userInput scanner
     *
     * @return userInput
     */
    public String mainMenuPrompt() {

        //display menu
        System.out.println("Choose a command from the list below:");
        System.out.println("     [L]: Load Library Data");
        System.out.println("     [SB]: Search for Book by Name");
        System.out.println("     [CO]: Checkout Book");
        System.out.println("     [CI]: Checkin Book");
        System.out.println("     [R]: Remove Book");
        System.out.println("     [P]: Get Pending Returns");
        System.out.println("     [ST]: Get Statistics");
        System.out.println("     [Q]: Quit");

        //read in and trim user's input
        System.out.println("Choose command: ");
        String input = userInput.nextLine().trim();
        if (input.length() == 0) { //blank input results in null string returned
            return null;
        }
        //return uppercase version of input
        return input.toUpperCase();
    }

    /**
     * Prompts user to load in a file and displays an error message when filename is invalid
     */
    public void loadDataCommand() {

        System.out.println("Enter the file to load: ");
        String filename = userInput.nextLine().trim();
        try {
            backend.loadData(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find or load file: " + filename);
        }
    }

    /**
     * Allows user to search for a book
     */
    public void searchBookCommand(String name) {

        if (backend.getBook(name) == null) {
            System.out.println("Library does not contain this book.");
        } else {
            System.out.println("Library does contain: " + backend.getBook(name).getTitle() + " by "
                    + backend.getBook(name).getAuthor());
        }
    }

    /**
     * Allows user to attempt to checkout a book
     */
    public void checkOutCommand(String name) {

        if (!backend.checkOut(name)) {
            System.out.println(name + " is already checked out by another person.");
        } else {
            System.out.println(name + " has been successfully checked out.");
        }

    }

    /**
     * Allows user to attempt to checkin a book
     */
    public void checkInCommand(String name) {

        if (!backend.checkIn(name)) {
            System.out.println(name + " is already checked in.");
        } else {
            System.out.println("You have successfully checked in " + name);
        }

    }

    /**
     * Allows user to attempt to remove a book
     */
    public void removeBookCommand(String name) {

        if (!backend.removeBook(name)) {
            System.out.println(name + " is still in the Library.");
        } else {
            System.out.println(name + " has been successfully removed.");
        }
    }

    /**
     * Displays pending returns of the user
     */
    public void displayPendingReturnsCommand() {

        List<BookInterface> results = backend.getPendingReturns();

        int resultIndex = 1;

        if (results.size() > 0) {
            System.out.println("Pending returns: ");
        } else {
            System.out.println("No pending returns.");
        }

        for (BookInterface result : results) {
            System.out.println("(" + (resultIndex++) + ") " + result.getTitle() + " by " + result.getAuthor());
        }
    }

    /**
     * Displays general statistics selected by the backend
     */
    public void displayStatsCommand() {
        System.out.println(backend.getStatisticsString());
    }
}

