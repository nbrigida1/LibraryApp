import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmEngineerTests {

    //Node Deletion Tests

    @Test
    /**
     * Case where red node replaces black node
     */
    public void redReplaceBlackRemoval() {
        RBTLibrary.Node<String> A = new RBTLibrary.Node<>("A");
        RBTLibrary.Node<String> B = new RBTLibrary.Node<>("B");
        RBTLibrary.Node<String> C = new RBTLibrary.Node<>("C");
        RBTLibrary.Node<String> D = new RBTLibrary.Node<>("D");
        RBTLibrary<String> tree = new RBTLibrary<>();

        A.blackHeight = 1;
        B.blackHeight = 1;
        D.blackHeight = 1;
        C.blackHeight = 0;

        B.context[1] = A;
        B.context[2] = D;
        A.context[0] = B;
        D.context[0] = B;
        A.context[1] = C;
        C.context[0] = A;

        tree.root = B;

        tree.remove("A");
        assertEquals(tree.toInOrderString(), "[ C, B, D ]");
        assertEquals(1, B.blackHeight);
        assertEquals(1, D.blackHeight);
        assertEquals(1, C.blackHeight);

    }

    /**
     * Case where black node replaces red node
     */
    @Test
    public void blackReplaceRedRemoval() {
        RBTLibrary.Node<String> A = new RBTLibrary.Node<>("A");
        RBTLibrary.Node<String> B = new RBTLibrary.Node<>("B");
        RBTLibrary.Node<String> C = new RBTLibrary.Node<>("C");
        RBTLibrary.Node<String> D = new RBTLibrary.Node<>("D");
        RBTLibrary<String> tree = new RBTLibrary<>();

        A.blackHeight = 0;
        B.blackHeight = 1;
        D.blackHeight = 1;
        C.blackHeight = 1;

        B.context[1] = A;
        B.context[2] = D;
        A.context[0] = B;
        D.context[0] = B;
        A.context[1] = C;
        C.context[0] = A;

        tree.root = B;

        tree.remove("A");
        assertEquals(tree.toInOrderString(), "[ C, B, D ]");
        assertEquals(1, B.blackHeight);
        assertEquals(1, D.blackHeight);
        assertEquals(1, C.blackHeight);

    }


    @Test
    /**
     * Case where removed and replacing node are black and inner nephew is red
     */
    public void redInnerNephewRemoval() {

        RBTLibrary.Node<String> A = new RBTLibrary.Node<>("A");
        RBTLibrary.Node<String> B = new RBTLibrary.Node<>("B");
        RBTLibrary.Node<String> D = new RBTLibrary.Node<>("D");
        RBTLibrary.Node<String> E = new RBTLibrary.Node<>("E");
        RBTLibrary<String> tree = new RBTLibrary<>();

        A.blackHeight = 1;
        B.blackHeight = 1;
        E.blackHeight = 1;
        D.blackHeight = 0;

        B.context[1] = A;
        A.context[0] = B;
        B.context[2] = E;
        E.context[0] = B;
        E.context[1] = D;
        D.context[0] = E;

        tree.root = B;

        tree.remove("A");
        assertEquals(tree.toInOrderString(), "[ B, D, E ]");
        assertEquals(1, B.blackHeight);
        assertEquals(1, D.blackHeight);
        assertEquals(1, E.blackHeight);

    }

    /**
     * Case where removed and replacing node are black and outer nephew is red
     */
    @Test
    public void redOuterNephewRemoval() {
        RBTLibrary.Node<String> A = new RBTLibrary.Node<>("A");
        RBTLibrary.Node<String> B = new RBTLibrary.Node<>("B");
        RBTLibrary.Node<String> C = new RBTLibrary.Node<>("C");
        RBTLibrary.Node<String> D = new RBTLibrary.Node<>("D");
        RBTLibrary.Node<String> E = new RBTLibrary.Node<>("E");
        RBTLibrary<String> tree = new RBTLibrary<>();

        A.blackHeight = 1;
        B.blackHeight = 1;
        E.blackHeight = 0;
        D.blackHeight = 1;
        C.blackHeight = 0;

        B.context[1] = A;
        B.context[2] = D;
        A.context[0] = B;
        D.context[0] = B;
        D.context[1] = C;
        D.context[2] = E;
        C.context[0] = D;
        E.context[0] = D;

        tree.root = B;

        tree.remove("A");
        assertEquals(tree.toInOrderString(), "[ B, C, D, E ]");
        assertEquals(1, B.blackHeight);
        assertEquals(1, D.blackHeight);
        assertEquals(1, E.blackHeight);
        assertEquals(0, C.blackHeight);
    }


    /**
     * Case where sibling has no red nephews and sibling is black
     */
    @Test
    public void noRedNephewRemoval() {

        RBTLibrary.Node<String> A = new RBTLibrary.Node<>("A");
        RBTLibrary.Node<String> B = new RBTLibrary.Node<>("B");
        RBTLibrary.Node<String> C = new RBTLibrary.Node<>("C");
        RBTLibrary.Node<String> D = new RBTLibrary.Node<>("D");
        RBTLibrary.Node<String> E = new RBTLibrary.Node<>("E");
        RBTLibrary<String> tree = new RBTLibrary<>();

        A.blackHeight = 1;
        B.blackHeight = 1;
        E.blackHeight = 1;
        D.blackHeight = 1;
        C.blackHeight = 1;

        B.context[1] = A;
        B.context[2] = D;
        A.context[0] = B;
        D.context[0] = B;
        D.context[1] = C;
        D.context[2] = E;
        C.context[0] = D;
        E.context[0] = D;

        tree.root = B;

        tree.remove("A");

        assertEquals(tree.toInOrderString(), "[ B, C, D, E ]");
        assertEquals(1, B.blackHeight);
        assertEquals(0, D.blackHeight);
        assertEquals(1, E.blackHeight);
        assertEquals(1, C.blackHeight);
    }

    @Test
    /**
     * Case where sibling is red
     */
    public void redSiblingRemoval() {
        RBTLibrary.Node<String> A = new RBTLibrary.Node<>("A");
        RBTLibrary.Node<String> B = new RBTLibrary.Node<>("B");
        RBTLibrary.Node<String> C = new RBTLibrary.Node<>("C");
        RBTLibrary.Node<String> D = new RBTLibrary.Node<>("D");
        RBTLibrary.Node<String> E = new RBTLibrary.Node<>("E");
        RBTLibrary<String> tree = new RBTLibrary<>();

        A.blackHeight = 1;
        B.blackHeight = 1;
        E.blackHeight = 1;
        D.blackHeight = 0;
        C.blackHeight = 1;

        B.context[1] = A;
        B.context[2] = D;
        A.context[0] = B;
        D.context[0] = B;
        D.context[1] = C;
        D.context[2] = E;
        C.context[0] = D;
        E.context[0] = D;

        tree.root = B;

        tree.remove("A");
        assertEquals(tree.toInOrderString(), "[ B, C, D, E ]");
        assertEquals(1, B.blackHeight);
        assertEquals(1, D.blackHeight);
        assertEquals(1, E.blackHeight);
        assertEquals(0, C.blackHeight);
    }

    //Node insertion tests (From previous week)

    @Test
    /**
     * Tests the first case described in the repair operation where the aunt of the inserted node is black
     * and the inserted node is an inner leaf
     */
    public void blackAuntInnerLeafInsertion() {
        //Case 1
        {
            RBTLibrary<Integer> tree = new RBTLibrary<>();
            tree.insert(8);
            tree.insert(6);
            RBTLibrary.Node<Integer> n = new RBTLibrary.Node<>(10);
            //gives the new node a black node aunt
            n.blackHeight = 1;
            tree.findNodeWithData(8).context[2] = n;
            assertEquals(tree.findNodeWithData(8).blackHeight, 1);
            assertEquals(tree.findNodeWithData(6).blackHeight, 0);
            assertEquals(tree.findNodeWithData(10).blackHeight, 1);
            tree.insert(7);
            assertEquals(tree.findNodeWithData(8).blackHeight, 0);
            assertEquals(tree.findNodeWithData(6).blackHeight, 0);
            assertEquals(tree.findNodeWithData(10).blackHeight, 1);
            assertEquals(tree.findNodeWithData(7).blackHeight, 1);
        }

        //Case 1 Mirrored
        {
            RBTLibrary<Integer> treeMirrored = new RBTLibrary<>();
            treeMirrored.insert(8);
            RBTLibrary.Node<Integer> n = new RBTLibrary.Node<>(6);
            //gives the new node a black node aunt
            n.blackHeight = 1;
            treeMirrored.findNodeWithData(8).context[1] = n;
            treeMirrored.insert(10);
            assertEquals(treeMirrored.findNodeWithData(8).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(6).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(10).blackHeight, 0);
            treeMirrored.insert(9);
            assertEquals(treeMirrored.findNodeWithData(8).blackHeight, 0);
            assertEquals(treeMirrored.findNodeWithData(6).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(10).blackHeight, 0);
            assertEquals(treeMirrored.findNodeWithData(9).blackHeight, 1);
        }
    }

    @Test
    /**
     * Tests the second case described in the repair operation where the aunt of the inserted node is black
     * and the inserted node is an outer leaf
     */
    public void blackAuntOuterLearInsertion() {
        //Case 2
        {
            RBTLibrary<Integer> tree = new RBTLibrary<>();
            tree.insert(4);
            tree.insert(3);
            RBTLibrary.Node<Integer> n = new RBTLibrary.Node<>(5);
            //gives the new node a black node aunt
            n.blackHeight = 1;
            tree.findNodeWithData(4).context[2] = n;
            assertEquals(tree.findNodeWithData(4).blackHeight, 1);
            assertEquals(tree.findNodeWithData(3).blackHeight, 0);
            assertEquals(tree.findNodeWithData(5).blackHeight, 1);
            tree.insert(1);
            assertEquals(tree.findNodeWithData(4).blackHeight, 0);
            assertEquals(tree.findNodeWithData(3).blackHeight, 1);
            assertEquals(tree.findNodeWithData(5).blackHeight, 1);
            assertEquals(tree.findNodeWithData(1).blackHeight, 0);
        }

        //Case 2 Mirrored
        {
            RBTLibrary<Integer> treeMirrored = new RBTLibrary<>();
            treeMirrored.insert(4);
            RBTLibrary.Node<Integer> n = new RBTLibrary.Node<>(3);
            //gives the new node a black node aunt
            n.blackHeight = 1;
            treeMirrored.findNodeWithData(4).context[1] = n;
            treeMirrored.insert(5);
            assertEquals(treeMirrored.findNodeWithData(4).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(3).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(5).blackHeight, 0);
            treeMirrored.insert(6);
            assertEquals(treeMirrored.findNodeWithData(4).blackHeight, 0);
            assertEquals(treeMirrored.findNodeWithData(3).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(5).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(6).blackHeight, 0);
        }
    }

    @Test
    /**
     * Tests the third case described in the repair operation where the aunt of the inserted node is red
     */
    public void redAuntInsertion() {
        //Case 3
        {
            RBTLibrary<Integer> tree = new RBTLibrary<>();
            tree.insert(4);
            tree.insert(3);
            tree.insert(5);
            assertEquals(tree.findNodeWithData(4).blackHeight, 1);
            assertEquals(tree.findNodeWithData(3).blackHeight, 0);
            assertEquals(tree.findNodeWithData(5).blackHeight, 0);
            tree.insert(1);
            assertEquals(tree.findNodeWithData(4).blackHeight, 1);
            assertEquals(tree.findNodeWithData(3).blackHeight, 1);
            assertEquals(tree.findNodeWithData(5).blackHeight, 1);
            assertEquals(tree.findNodeWithData(1).blackHeight, 0);
        }

        //Case 3 Mirrored
        {
            RBTLibrary<Integer> treeMirrored = new RBTLibrary<>();
            treeMirrored.insert(4);
            treeMirrored.insert(3);
            treeMirrored.insert(5);
            assertEquals(treeMirrored.findNodeWithData(4).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(3).blackHeight, 0);
            assertEquals(treeMirrored.findNodeWithData(5).blackHeight, 0);
            treeMirrored.insert(6);
            assertEquals(treeMirrored.findNodeWithData(4).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(3).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(5).blackHeight, 1);
            assertEquals(treeMirrored.findNodeWithData(6).blackHeight, 0);
        }
    }


    @Test
    /**
     * Tests contains method
     */
    public void containsTest() {
        //setup
        RBTLibrary<String> tree = new RBTLibrary<>();
        tree.insert("d");
        tree.insert("w");
        tree.insert("q");

        //valid searches
        assertTrue(tree.contains("d"));
        assertTrue(tree.contains("w"));
        assertTrue(tree.contains("q"));


        //invalid searches
        assertFalse(tree.contains("g"));
        assertFalse(tree.contains("b"));
        assertFalse(tree.contains("l"));
    }

    @Test
    /**
     * Tests size method
     */
    public void sizeTest() {
        //setup
        RBTLibrary<String> tree = new RBTLibrary<>();

        //test with size = 0
        assertEquals(tree.size(), 0);

        //test with size = 3 after inserts
        tree.insert("a");
        tree.insert("b");
        tree.insert("c");
        assertEquals(tree.size(), 3);

        //test with size = 2 after removal
        tree.remove("a");
        assertEquals(tree.size(), 2);
    }

    @Test
    /**
     * tests isEmpty method
     */
    public void isEmptyTest() {
        //setup
        RBTLibrary<String> tree = new RBTLibrary<>();

        //test after initialization
        assertTrue(tree.isEmpty());

        //test after inserts
        tree.insert("a");
        tree.insert("b");
        assertFalse(tree.isEmpty());

        //test with empty tree again after removals
        tree.remove("a");
        tree.remove("b");
        assertTrue(tree.isEmpty());
    }

    @Test
    /**
     * tests getNode method
     */
    public void getNodeTest() {
        //setup
        RBTLibrary<String> tree = new RBTLibrary<>();
        RBTLibrary.Node<String> a = new RBTLibrary.Node<>("a");
        RBTLibrary.Node<String> b = new RBTLibrary.Node<>("b");
        RBTLibrary.Node<String> c = new RBTLibrary.Node<>("c");
        tree.root = b;
        b.context[1] = a;
        b.context[2] = c;
        a.context[0] = b;
        c.context[0] = b;

        //valid search
        assertEquals(tree.getNode("c").data, c.data);

        //invalid search
        boolean expectedException = false;
        try {
            tree.getNode("l");
        } catch (NoSuchElementException e) {
            expectedException = true;
        } catch (Exception e) {

        }
        assertTrue(expectedException);
    }

    /**
     * Tests initialization of Book and get methods
     */
    @Test
    public void CodeReviewOfDataWranglerTest1()
    {
        Book mouse = new Book("If you give a mouse a cookie", "Laura Numeroff", "Children's Book", true, 0);
        assertEquals("If you give a mouse a cookie", mouse.getTitle());
        assertEquals("Laura Numeroff", mouse.getAuthor());
        assertEquals("Children's Book", mouse.getGenre());
        assertEquals(true, mouse.getStatus());
        mouse.checkoutBook();
        assertEquals(false, mouse.getStatus());
        mouse.returnBook();
        assertEquals(true, mouse.getStatus());
        mouse.updateStatus(false);
        assertEquals(false, mouse.getStatus());
        assertEquals(1, mouse.getTimesCheckedOut());
    }

    /**
     * Tests loading data from a file
     */
    @Test
    public void CodeReviewOfDataWranglerTest2() throws FileNotFoundException {
        BookReader reader = new BookReader();
        List<BookInterface> bookInterfaceList = reader.readBooksFromFile("testBookList.txt");
        assertEquals(bookInterfaceList.get(0).getAuthor(), "Walter Issacson");
    }

    /**
     * Tests searching for a book
     */
    @Test
    public void IntegrationTest1()
    {
        TextUITester uiTester = new TextUITester("L\ntestBookList.txt\nSB\nSteve Jobs\nSB\n1984\nQ\n");
        //Loads testBookList.txt and tries to find 2 books: "Steve Jobs" and "1984"
        BookReaderInterface bookReader = new BookReader();
        RBTLibraryInterface<Book> bookTree = new RBTLibrary<>();
        LibraryCheckoutBackendInterface backend = new LibraryCheckoutBackend((RBTLibrary<Book>) bookTree, (BookReader) bookReader);
        Scanner s = new Scanner(System.in);
        LibraryCheckoutFrontend frontend = new LibraryCheckoutFrontend(s, backend);
        frontend.runCommandLoop();
        String systemOutput = uiTester.checkOutput();
        boolean libraryContainsSteveJobs = systemOutput.contains("Library does contain: Steve Jobs by Walter Issacson");
        //true if Steve Jobs book is in library which it should be

        boolean libraryContains1984 = systemOutput.contains("Library does contain 1984 by George Orwell");
        //true if 1984 is in library which it should not be

        boolean bookNotFound = systemOutput.contains("Library does not contain this book.");
        //true if a book in the library is not found which should happen to the search for 1984

        //tests booleans and their intended states
        assertTrue(libraryContainsSteveJobs);
        assertFalse(libraryContains1984);
        assertTrue(bookNotFound);

    }

    /**
     * Tests check out feature
     */
    @Test
    public void IntegrationTest2()
    {
        TextUITester uiTester = new TextUITester("L\ntestBookList.txt\nCO\nSteve Jobs\nCO\nSteve Jobs\nQ\n");
        //Loads testBookList.txt and
        BookReaderInterface bookReader = new BookReader();
        RBTLibraryInterface<Book> bookTree = new RBTLibrary<>();
        LibraryCheckoutBackendInterface backend = new LibraryCheckoutBackend((RBTLibrary<Book>) bookTree, (BookReader) bookReader);
        Scanner s = new Scanner(System.in);
        LibraryCheckoutFrontend frontend = new LibraryCheckoutFrontend(s, backend);
        frontend.runCommandLoop();
        String systemOutput = uiTester.checkOutput();
        boolean CheckOut = systemOutput.contains("Steve Jobs has been successfully checked out.");
        boolean AlreadyCheckedOut = systemOutput.contains("Steve Jobs is already checked out by another person.");
    }
}
