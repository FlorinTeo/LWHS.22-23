package main;

public class IntNode {
    private int _data;
    private IntNode _next;
    
    public IntNode(int data, IntNode next) {
        _data = data;
        _next = next;
    }
    
    public int getData() {
        return _data;
    }
    
    public IntNode getNext() {
        return _next;
    }
    
    public void linkTo(IntNode other) {
        other._next = _next;
        _next = other;
    }
    
    @Override
    public String toString() {
        String output = "" + _data;
        if (_next != null) {
            output += ", " + _next.toString();
        }
        return output;
    }
}
