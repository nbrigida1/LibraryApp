// --== CS400 Spring 2023 File Header Information ==--
// Name: Aryav Bharali
// Email: bharali@wisc.edu
// Team: CM
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: None

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The BackendDeveloperTests class has 5 JUnit tests
 * that test the LibraryCheckoutBackend class and
 * uses mock classes as its dependencies.
 *
 * @author Aryav Bharali
 * @version March 29, 2023
 */
public class BackendDeveloperTests {
    protected LibraryCheckoutBackend _instance = null;

    /**
     * Creates a new BookCheckoutBackend for each JUnit test.
     */
    @BeforeEach
    public void createInstance() {
        _instance = new LibraryCheckoutBackend();
    }

    /**
     * Tests three cases when loading files: loading an invalid
     * file name, loading a small valid file, loading a file
     * with duplicates, and loading a bigger valid file.
     */
    @Test
    public void individual1() {
        try {
            _instance.loadData("invalid.txt");
            fail("invalid.txt did not throw any exception");
        } catch (FileNotFoundException ignored) {

        } catch (Exception e) {
            fail("invalid.txt had an unexpected exception");
        }

        try {
            System.out.println("SDFSD");
            assertTrue(_instance.loadData("small.txt"));
            System.out.println("SDFSD");
            assertEquals("aAuthor", _instance.getBook("A").getAuthor());
            assertEquals("bAuthor", _instance.getBook("B").getAuthor());
        } catch (Exception e) {
            fail("small.txt threw unexpected Exception " + e.getMessage());
        }

        try {
            assertFalse(_instance.loadData("big.txt"));
            assertEquals("aAuthor", _instance.getBook("A").getAuthor());
            assertEquals("bAuthor", _instance.getBook("B").getAuthor());
        } catch (Exception e) {
            fail("big.txt threw unexpected Exception " + e.getMessage());
        }

        try {
            _instance = new LibraryCheckoutBackend();
            assertTrue(_instance.loadData("big.txt"));
            assertEquals("aAuthor", _instance.getBook("A").getAuthor());
            assertEquals("bAuthor", _instance.getBook("B").getAuthor());
            assertEquals("cAuthor", _instance.getBook("C").getAuthor());
            assertEquals("dAuthor", _instance.getBook("D").getAuthor());
        } catch (Exception e) {
            fail("big.txt threw unexpected Exception " + e.getMessage());
        }
    }

    /**
     * Tests marking specific books as checked in/out.
     */
    @Test
    public void individual2() {
        try {
            _instance.loadData("big.txt");
        } catch (Exception ignored) { }

        assertTrue(_instance.getBook("A").getStatus());
        assertTrue(_instance.getBook("B").getStatus());
        assertTrue(_instance.getBook("C").getStatus());
        assertTrue(_instance.getBook("D").getStatus());
        _instance.checkOut("A");
        assertFalse(_instance.getBook("A").getStatus());
        assertTrue(_instance.getBook("B").getStatus());
        assertTrue(_instance.getBook("C").getStatus());
        assertTrue(_instance.getBook("D").getStatus());

        _instance.checkOut("D");
        assertFalse(_instance.getBook("A").getStatus());
        assertTrue(_instance.getBook("B").getStatus());
        assertTrue(_instance.getBook("C").getStatus());
        assertFalse(_instance.getBook("D").getStatus());

        _instance.checkIn("A");
        assertTrue(_instance.getBook("A").getStatus());
        assertTrue(_instance.getBook("B").getStatus());
        assertTrue(_instance.getBook("C").getStatus());
        assertFalse(_instance.getBook("D").getStatus());

        _instance.checkOut("B");
        assertTrue(_instance.getBook("A").getStatus());
        assertFalse(_instance.getBook("B").getStatus());
        assertTrue(_instance.getBook("C").getStatus());
        assertFalse(_instance.getBook("D").getStatus());

        _instance.checkIn("B");
        _instance.checkIn("D");
        assertTrue(_instance.getBook("A").getStatus());
        assertTrue(_instance.getBook("B").getStatus());
        assertTrue(_instance.getBook("C").getStatus());
        assertTrue(_instance.getBook("D").getStatus());
    }

    /**
     * Tests removing books from the library/rbt
     * signifying they were lost, destroyed, etc.
     */
    @Test
    public void individual3() {
        try {
            _instance.loadData("big.txt");
        }
        catch (Exception ignored) { }

        assertNotNull(_instance.getBook("A"));
        assertNotNull(_instance.getBook("B"));
        assertNotNull(_instance.getBook("C"));
        assertNotNull(_instance.getBook("D"));

        _instance.removeBook("A");
        assertNull(_instance.getBook("A"));
        assertNotNull(_instance.getBook("B"));
        assertNotNull(_instance.getBook("C"));
        assertNotNull(_instance.getBook("D"));

        _instance.removeBook("C");
        assertNull(_instance.getBook("A"));
        assertNotNull(_instance.getBook("B"));
        assertNull(_instance.getBook("C"));
        assertNotNull(_instance.getBook("D"));

        _instance.removeBook("B");
        _instance.removeBook("D");
        assertNull(_instance.getBook("A"));
        assertNull(_instance.getBook("B"));
        assertNull(_instance.getBook("C"));
        assertNull(_instance.getBook("D"));
    }

    /**
     * Tests getting all pending returns.
     */
    @Test
    public void individual4() {
        try {
            _instance.loadData("big.txt");
        }
        catch (Exception ignored) { }

        assertNotNull(_instance.getPendingReturns());
        assertEquals(0, _instance.getPendingReturns().size());

        _instance.checkOut("B");
        List<BookInterface> res = _instance.getPendingReturns();
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals(_instance.getBook("B"), res.get(0));

        _instance.checkOut("C");
        res = _instance.getPendingReturns();
        assertNotNull(res);
        assertEquals(2, res.size());
        assertTrue(res.contains(_instance.getBook("B")));
        assertTrue(res.contains(_instance.getBook("C")));

        _instance.checkIn("B");
        _instance.checkIn("C");
        res = _instance.getPendingReturns();
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    /**
     * Tests getting statistics of the library.
     */
    @Test
    public void individual5() {
        try {
            _instance.loadData("big.txt");
        }
        catch (Exception ignored) { }

        for (int i = 1; i <= 10; i++) {
            _instance.checkOut("A");
            _instance.checkIn("A");

            if (i <= 5) {
                _instance.checkOut("B");
                _instance.checkIn("B");
            }

            if (i <= 7) {
                _instance.checkOut("C");
                _instance.checkIn("C");
            }
        }

        String res = _instance.getStatisticsString();
        System.out.println(res);
        assertTrue(res.contains("A: 10"));
        assertTrue(res.contains("B: 5"));
        assertTrue(res.contains("C: 7"));

    }
}
