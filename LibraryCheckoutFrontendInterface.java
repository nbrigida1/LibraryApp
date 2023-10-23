public interface LibraryCheckoutFrontendInterface {
//public LibraryCheckoutFrontendXX(Scanner userInput, LibraryCheckoutBackendInterface backend);
	public void runCommandLoop();

	public String mainMenuPrompt();

	public void loadDataCommand();

	public void searchBookCommand(String name);

	public void checkOutCommand(String name);

	public void checkInCommand(String name);
	
	public void removeBookCommand(String name);

	public void displayPendingReturnsCommand();

	public void displayStatsCommand();
}

