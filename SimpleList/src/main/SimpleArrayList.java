package main;

public class SimpleArrayList implements SimpleList {
    
    /**
     * SimpleNode definition of a single-linked list of Objects 
     */
    private class SimpleNode {
        private Object _data;
        private SimpleNode _next;
        
        private SimpleNode(Object data) {
            this(data,  null);
        }
        
        private SimpleNode(Object data, SimpleNode next) {
            _data = data;
            _next = next;
        }
    }
    
    /**
     * TODO: Class fields: Keep track of the head and tail of the list
     * TODO: and the number of nodes it contains.
     */
    public SimpleArrayList() {
        // TODO: Initialize class fields
    }

    /**
     * Appends the specified element to the end of this list (optional operation).
     * @param element - element to be appended to this list.
     * @return true
     */
    @Override
    public boolean add(Object element) {
        // TODO: Implement interface method add
        throw new RuntimeException("Not yet implemented");
    }
    
    /**
     * Inserts the specified element at the specified position in this list.
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     */
    @Override
    public void add(int index, Object element) {
        // TODO: Implement interface method add
        throw new RuntimeException("Not yet implemented");
    }
    
    /**
     * Removes all of the elements from this list (optional operation).
     */
    @Override
    public void clear() {
        // TODO: Implement interface method clear
        throw new RuntimeException("Not yet implemented");
    }
    
    /**
     * Returns the element at the specified position in this list.
     * @param index - index of the element to return.
     * @return the element at the specified position in this list.
     */
    @Override
    public Object get(int index) {
        // TODO: Implement interface method get
        throw new RuntimeException("Not yet implemented");
    }
    
    /**
     * Removes the element at the specified position in this list.
     * @param index - the index of the element to be removed.
     * @return the element previously at the specified position.
     */
    @Override
    public Object remove(int index) {
        // TODO: Implement interface method remove
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index - index of the element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     */
    @Override
    public Object set(int index, Object element) {
        // TODO: Implement interface method set
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    @Override
    public int size() {
        // TODO: Implement interface method size
        throw new RuntimeException("Not yet implemented");
    }
}
