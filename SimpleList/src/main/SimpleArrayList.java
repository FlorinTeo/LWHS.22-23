package main;

public class SimpleArrayList implements SimpleList {
    
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
        
        private void addTail(SimpleNode other) {
            if (_next == null) {
                _next = other;
            } else {
                _next.addTail(other);;
            }
        }
        
        private SimpleNode atIndex(int index) {
            if (index == 0) {
                return this;
            } else if (_next == null) {
                return null;
            } else {
                return _next.atIndex(index - 1); 
            }
        }
    };
    
    private SimpleNode _list;
    
    public SimpleArrayList() {
        _list = null;
    }

    @Override
    public boolean add(Object element) {
        SimpleNode other = new SimpleNode(element);
        if (_list == null) {
            _list = other;
        } else {
            _list.addTail(other);
        }
        return true;
    }

    @Override
    public void add(int index, Object element) {
        if (index == 0) {
            _list = new SimpleNode(element, _list);
        } else {
            SimpleNode other = _list.atIndex(index-1);
            if (other != null) {
                other._next = new SimpleNode(element, other._next);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    @Override
    public void clear() {
        _list = null;
    }

    @Override
    public Object remove(int index) {
        SimpleNode removed = null;
        if (_list == null) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                removed = _list;
                _list = _list._next;
            } else {
                SimpleNode other = _list.atIndex(index-1);
                if (other == null) {
                    throw new IndexOutOfBoundsException();
                }
                removed = other._next;
            }
        }
        return removed;
    }

    @Override
    public Object set(int index, Object element) {
        if (_list == null) {
            throw new IndexOutOfBoundsException();
        }
        SimpleNode other = _list.atIndex(index);
        if (other == null) {
            throw new IndexOutOfBoundsException();
        }
        other._data = element;
        return other;
    }

    @Override
    public int size() {
        int count = 0;
        SimpleNode node = _list;
        while(node != null) {
            count++;
            node = node._next;
        }
        
        return count;
    }

}
