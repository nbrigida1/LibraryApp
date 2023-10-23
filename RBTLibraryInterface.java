public interface RBTLibraryInterface<T extends Comparable<T>> extends SortedCollectionInterface<T> {
//    public RBTLibrary();

    boolean insert(T data);

    boolean contains(T data);

    boolean remove(T data);

    int size();

    boolean isEmpty();

    Node<T> getNode(T data);

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    class Node<T> {
        public T data;
        public int blackHeight;
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public RBTLibrary.Node<T>[] context = (RBTLibrary.Node<T>[]) new RBTLibrary.Node[3];


        public Node(T data) {
            this.data = data;
            this.blackHeight = 0;
        }

    }

}
