import java.util.List;
import java.util.ArrayList;

public class FiboCache {
    
    private class _FiboItem {
        private int _number;
        private long _fiboValue;
    }
    
    private int _size;
    private List<_FiboItem> _cache;
    private long _nHits;
    private long _nMisses;
    
    public FiboCache(int size) {
        _size = size;
        _cache = new ArrayList<_FiboItem>(_size);
    }
    
    public Long retrieveFibo(int n) {
        for(int i = 0; i < _cache.size(); i++) {
            _FiboItem fiboItem = _cache.get(i);
            if (n == fiboItem._number) {
                _nHits++;
                _cache.remove(i);
                _cache.add(0, fiboItem);
                return fiboItem._fiboValue;
            }
        }
        
        _nMisses++;
        return null;
    }
    
    public void cacheFibo(int n, long fiboValue) {
        _FiboItem fiboItem = new _FiboItem();
        fiboItem._number = n;
        fiboItem._fiboValue = fiboValue;

        if (_cache.size() == _size) {
            _cache.remove(_size-1);
        }
        _cache.add(_cache.size(), fiboItem);
    }
    
    @Override
    public String toString() {
        return String.format("Cache hit ratio: %.2f%%\n",
                (100.0 * _nHits)/(_nHits + _nMisses));
    }

}
