// --== CS400 Spring 2023 File Header Information ==--
// Name: Aryav Bharali
// Email: bharali@wisc.edu
// Team: CM
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: None

import java.util.ArrayList;
import java.util.List;

/**
 * RBTLibraryBD mocks the RBTLibrary class for the BackendDeveloper tests,
 * which acts as an RBT for the backend.
 *
 * @author Aryav Bharali
 * @version March 29, 2023
 */
public class RBTLibraryBD<T extends Comparable<T>> implements RBTLibraryInterface<T> {
    protected static class Node<T> {
        public T data;

        public Node(T data) {
            this.data = data;
        }
    }

    List<T> books = new ArrayList<>();

    /**
     * Inserts the given data into the Collection.
     *
     * @param data The data to insert
     * @return true if the data could be inserted,
     *         otherwise false
     */
    @Override
    public boolean insert(T data) {
        if (contains(data)) return false;
        books.add(data);
        return true;
    }

    /**
     * Determines whether the Collection already contains
     * the given name.
     *
     * @param key The Book to match
     * @return true if the name exists within the Collection,
     *         otherwise false
     */
    @Override
    public boolean contains(T key) {
        for (T book : books)
            if (book.equals(key))
                return true;

        return false;
    }

    /**
     * Removes the data associated to the given name
     * from the Collection.
     *
     * @param key The name to match
     * @return true if the data could be removed,
     *         otherwise false
     */
    @Override
    public boolean remove(T key) {
        for (int i = 0; i < books.size(); i++)
            if (books.get(i).equals(key))  {
                books.remove(i);
                return true;
            }

        return false;
    }

    public RBTLibraryInterface.Node<T> getNode(T data) {
        for (T book : books)
            if (book.equals(data))
                return new RBTLibraryInterface.Node<>(book);
        return null;
    }

    /**
     * Gets the size of the Collection.
     *
     * @return The size of the Collection
     */
    @Override
    public int size() {
        return books.size();
    }

    /**
     * Determines whether the Collection is empty.
     *
     * @return true if the Collection is empty,
     *         otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}