import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Scanner;

/**
 *Manages tests for the Frontend Developer
 *
 */
public class FrontendDeveloperTests {


	/**
	 *Tests to ensure that the welcome message is diplayed through mainMenuPrompt()
	 */
	@Test
	public void test1(){
	    
	    TextUITester uiTester = new TextUITester("Q");
	    LibraryCheckoutBackendFD backendTest = new LibraryCheckoutBackendFD();

	    Scanner sc = new Scanner(System.in);
	    LibraryCheckoutFrontend testFront = new LibraryCheckoutFrontend(sc, backendTest);

	    testFront.runCommandLoop();
	    String systemOutput = uiTester.checkOutput();

	    assertTrue(systemOutput.contains("Welcome to CM Library’s Checkout!"));
	}


	/**
	 *Tests to ensure that the displayStatsCommand() method works correctly
	 */
	@Test
	public void test2() {

	    TextUITester uiTester = new TextUITester("ST\nQ");
	    LibraryCheckoutBackendFD backendTest = new LibraryCheckoutBackendFD();

	    Scanner sc = new Scanner(System.in);
	    LibraryCheckoutFrontend testFront= new LibraryCheckoutFrontend(sc, backendTest);

	    testFront.runCommandLoop();
	    String systemOutput = uiTester.checkOutput();

	    assertTrue(systemOutput.contains("Good statistics"));
	}


	/**
	 *Tests to ensure that the checkOutCommand() method works correctly
	 */
	@Test
	public void test3() {

	    TextUITester uiTester = new TextUITester("CO\nname\nQ");
            LibraryCheckoutBackendFD backendTest = new LibraryCheckoutBackendFD();

            Scanner sc = new Scanner(System.in);
            LibraryCheckoutFrontend testFront= new LibraryCheckoutFrontend(sc, backendTest);

            testFront.runCommandLoop();
            String systemOutput = uiTester.checkOutput();

            assertTrue(systemOutput.contains("name is already checked out by another person."));
	}


	/**
	 *Tests to ensure that the checkInCommand() method works correctly
	 */
	@Test
	public void test4() {

	    TextUITester uiTester = new TextUITester("CI\nname\nQ");
            LibraryCheckoutBackendFD backendTest = new LibraryCheckoutBackendFD();

            Scanner sc = new Scanner(System.in);
            LibraryCheckoutFrontend testFront= new LibraryCheckoutFrontend(sc, backendTest);

            testFront.runCommandLoop();
            String systemOutput = uiTester.checkOutput();

            assertTrue(systemOutput.contains("name is already checked in."));
	}


	/**
	 *Tests to ensure that the removeBookCommand() method works correctly
	 */
	@Test
	public void test5() {

	    TextUITester uiTester = new TextUITester("R\nname\nQ");
            LibraryCheckoutBackendFD backendTest = new LibraryCheckoutBackendFD();

            Scanner sc = new Scanner(System.in);
            LibraryCheckoutFrontend testFront= new LibraryCheckoutFrontend(sc, backendTest);

            testFront.runCommandLoop();
            String systemOutput = uiTester.checkOutput();

            assertTrue(systemOutput.contains("name is still in the Library."));
	}
}

