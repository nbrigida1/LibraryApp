import java.io.FileNotFoundException;
import java.util.List;

public interface LibraryCheckoutBackendInterface {
//  public LibraryCheckoutBackend();
	
	public boolean loadData(String filename) throws FileNotFoundException;
	
	// null if not found
	public BookInterface getBook(String name);
	
	// false if not found or already checked in/out
	public boolean checkOut(String name);
	public boolean checkIn(String name);
	public boolean removeBook(String name);
	
	public List<BookInterface> getPendingReturns();
	
	public String getStatisticsString();
}

