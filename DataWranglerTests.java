import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * these are the junit test that tests BookDw and BookReaderDW
 */
class DataWranglerTests {

    /**
     * tests the getter methods of title author genre and status
     */
    @Test
    void testBookDW() {
        Book a = new Book("title", "author", "genre", false, 0);
        assertEquals(a.getTitle(), "title");
        assertEquals(a.getAuthor(), "author");
        assertEquals(a.getGenre(), "genre");
        assertFalse(a.getStatus());
    }

    /**
     * tests the methods of update status and get times checkedout
     */
    @Test
    void testGetTitle() {
        Book a = new Book("title", "author", "genre", false, 0);
        a.updateStatus(true);
        assertTrue(a.getStatus());
        assertEquals(a.getTimesCheckedOut(), 0);
    }

    /**
     * checks out the checkout book method
     * check out retunbook method
     */
    @Test
    void testCheckoutBook() {
        Book a = new Book("title", "author", "genre", false, 0);
        a.checkoutBook();
        assertFalse(a.getStatus());
        assertEquals(a.getTimesCheckedOut(), 1);
        a.returnBook();
        assertTrue(a.getStatus());
    }

    /**
     * tests readbookfromfile method and checks if all the books from the file were
     * transffered
     */
    @Test
    void testBookReader() throws FileNotFoundException {
        BookReader a = new BookReader();
        String filepath = "Books_data - Sheet1.csv";
        List<BookInterface> books = a.readBooksFromFile(filepath);
        System.out.println(books.size());
        assertEquals(books.size(), 61);


        /**
         *tests the special case of readbook from file when the book's title contains ,
         *in the title "Guns,Germs,and Steel"
         *
         */
    }

    @Test
    void testBookReaderSpecialCase() throws FileNotFoundException {
        BookReader a = new BookReader();
        String filepath = "Books_data - Sheet1.csv";
        List<BookInterface> books = a.readBooksFromFile(filepath);
        assertEquals(books.size(), 61);
        assertEquals(books.get(50).getTitle(), "Guns,Germs,and Steel");


    }
}
