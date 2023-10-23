import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        //instantiates a bookReader to load data
        BookReaderInterface bookReader = new BookReader();
        //instantiates a RBT for the books
        RBTLibraryInterface<Book> bookTree = new RBTLibrary<>();
        //instantiates a backend with the RBT and bookReader
        LibraryCheckoutBackendInterface backend = new LibraryCheckoutBackend((RBTLibrary<Book>) bookTree, (BookReader) bookReader);
        //instantiates a front end and that runs the command loop
        Scanner scanner = new Scanner(System.in);
        LibraryCheckoutFrontendInterface frontend = new LibraryCheckoutFrontend(scanner, backend);
        frontend.runCommandLoop();
    }
}
