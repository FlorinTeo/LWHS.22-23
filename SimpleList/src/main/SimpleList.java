package main;

public interface SimpleList {
    /**
     * Appends the specified element to the end of this list (optional operation).
     * @param element - element to be appended to this list.
     * @return true
     */
    boolean add(Object element);
    
    /**
     * Inserts the specified element at the specified position in this list.
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     */
    void add(int index, Object element);
    
    /**
     * Removes all of the elements from this list (optional operation).
     */
    void clear();
    
    /**
     * Removes the element at the specified position in this list.
     * @param index - the index of the element to be removed.
     * @return the element previously at the specified position.
     */
    Object remove(int index);
    
    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index - index of the element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     */
    Object set(int index, Object element);
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    int size();
}
