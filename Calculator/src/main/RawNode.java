package main;

public class RawNode {
    private String _rawContent;
    private RawNode _prev;
    private RawNode _next;
    
    /**
     * Node factory method, returning a new node for the given raw content
     * or null if the node could not be created.
     * @param rawContent - the row content stored in this node.
     * @return the new node.
     */
    public static RawNode createNode(String rawContent) {
        return new RawNode(rawContent, null, null);
    }
    
    /**
     * Class constructor. Transfers parameters in the class fields.
     * @param rawContent - the row content stored in this node.
     * @param previous - link to the previous node, or null if none exists.
     * @param next - link to the next node, or null if none exists.
     */
    public RawNode(String rawContent, RawNode previous, RawNode next) {
        _rawContent = rawContent;
        _prev = previous;
        _next = next;
    }
    
    /**
     * Accessor for the node's raw content.
     * @return the raw content.
     */
    public String getRawContent() {
        return _rawContent;
    }
    
    /**
     * Gets reference to the next node
     * @return the reference to the next node, or null if none exists.
     */
    public RawNode getNext() {
        return _next;
    }
    
    /**
     * Gets the reference to the previous node.
     * @return the reference to the previous node, or null if none exists.
     */
    public RawNode getPrev() {
        return _prev;
    }
    
    /**
     * Adds another node right after this node.
     * @param other - the node to be added.
     * @return the node that's been added.
     */
    public RawNode addNext(RawNode other) {
        other._next = _next;
        other._prev = this;
        if (_next != null) {
            _next._prev = other;
        }
        _next = other;
        return other;
    }
    
    /**
     * Adds another node ahead of this node.
     * @param other - the node to be added.
     * @return the node that's been added.
     */
    public RawNode addPrev(RawNode other) {
        other._next = this;
        other._prev = _prev;
        if (_prev != null) {
            _prev._next = other;
        }
        _prev = other;
        return other;
    }
    
    /**
     * Adds another node to the tail of the list.
     * @param other - the node to be added.
     * @return the new tail of the list.
     */
    public RawNode addTail(RawNode other) {
        if (_next == null) {
            return addNext(other);
        } else {
            return _next.addTail(other);
        }
    }
    
    /**
     * Adds another node to the head of the list.
     * @param other - the node to be added.
     * @return the new head of the list.
     */
    public RawNode addHead(RawNode other) {
        if (_prev == null) {
            return addPrev(other);
        } else {
            return _prev.addHead(other);
        }
    }
}
