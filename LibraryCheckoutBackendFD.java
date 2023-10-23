import java.io.FileNotFoundException;
import java.util.List;

public class LibraryCheckoutBackendFD implements LibraryCheckoutBackendInterface {
	public boolean loadData(String filename) throws FileNotFoundException {
		return false;
	}

	@Override
	public BookInterface getBook(String name) {
		return null;
	}

	public boolean checkOut(String name) {
		return false;
	}
	
	public boolean checkIn(String name) {
		return false;
	}

	@Override
	public boolean removeBook(String name) {
		return false;
	}

	public List<BookInterface> getPendingReturns() {
		return null;
	}

	public String getStatisticsString() {
		return "Good statistics";
	}
}

