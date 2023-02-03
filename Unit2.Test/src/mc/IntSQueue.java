package mc;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class IntSQueue  {
    private Stack<Integer> _addStack;
    private Stack<Integer> _removeStack;
    
    public IntSQueue() {
        _addStack = new Stack<Integer>();
        _removeStack = null;
    }

    public int size() {
        if (_addStack != null) {
            return _addStack.size();
        } else {
            return _removeStack.size();
        }
    }

    public void add(Integer e) {
        if (_addStack == null) {
            _addStack = new Stack<Integer>();
            while(!_removeStack.isEmpty()) {
                _addStack.push(_removeStack.pop());
            }
            _removeStack = null;
        }
        _addStack.push(e);
    }

    public Integer remove() {
        if (_removeStack == null) {
            _removeStack = new Stack<Integer>();
            while(!_addStack.isEmpty()) {
                _removeStack.push(_addStack.pop());
            }
            _addStack = null;
        }
        return _removeStack.pop();
    }
}
