package frq;

public class LinkedIntList {
    
    private ListNode _front;
    
    public LinkedIntList(int... numbers) {
        ListNode crt = null;
        for(int n : numbers) {
            if (crt == null) {
                _front = crt = new ListNode(n, null);
            } else {
                crt._next = new ListNode(n, null);
                crt = crt._next;
            }
        }
    }
    
    /**
     * Swap the elements at index i and i+1 in the linked list referenced by _front.
     * @param i - the index of the first element in the list that should be swapped
     *            with the element that follows.
     * @throws IndexOutOfBoundsException if list is empty or elements cannot be located. 
     */
    public void swap(int i) throws IndexOutOfBoundsException {
        ListNode prev = null;
        ListNode crt = _front;
        while(crt != null && i != 0) {
            i--;
            prev = crt;
            crt = crt._next;
        }
        if (i != 0 || crt == null || crt._next == null) {
            throw new IndexOutOfBoundsException();
        }
        ListNode tmp = crt._next._next;
        if (prev == null) {
            _front = crt._next;
            crt._next = tmp;
            _front._next = crt;
        } else {
            prev._next = crt._next;
            crt._next = tmp;
            prev._next._next = crt;
        }
    }
    
    public int remove(int i) {
        ListNode prev = null;
        ListNode crt = _front;
        while(crt != null && i > 0) {
            i--;
            prev = crt;
            crt = crt._next;
        }
        if (i != 0 || crt == null) {
            throw new IndexOutOfBoundsException();
        }
        if (prev == null) {
            _front = crt._next;
        } else {
            prev._next = crt._next;
        }
        return crt.getData();
    }
    
    @Override
    public String toString() {
        String output = "[";
        ListNode crt = _front;
        while(crt != null) {
            output += crt.getData();
            if (crt._next != null) {
                output += " ";
            }
            crt = crt._next;
        }
        output += "]";
        return output;
    }
    
}
