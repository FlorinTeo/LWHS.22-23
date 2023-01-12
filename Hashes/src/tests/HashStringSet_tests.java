package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.HashStringSet;

class HashStringSet_tests {

    private HashStringSet _hss;
    
    @BeforeEach
    void test_setup() {
        _hss = new HashStringSet();
        _hss.add("abc");
        _hss.add("def");
    }
    
    @Test
    void test_add() {
        assertEquals("[def, abc]", _hss.toString());
        _hss.add("xyz");
        assertEquals("[xyz, def, abc]", _hss.toString());
        _hss.add("def");
        assertEquals("[xyz, def, abc]", _hss.toString());
    }
    
    @Test
    void test_size() {
        assertEquals(2, _hss.size());
        _hss.add("xyz");
        assertEquals(3, _hss.size());
        _hss.add("def");
        assertEquals(3, _hss.size());
    }
    
    @Test
    void test_buckets() {
        assertEquals(10, _hss.buckets());
    }
    
    @Test
    void test_loadFactor() {
        assertEquals(0.2, _hss.loadFactor());
        _hss.add("xyz");
        assertEquals(0.3, _hss.loadFactor());
    }
    
    @Test
    void test_memoryFactor() {
        // payload = 6
        // memory = 4 + 4 + 4*10 + (4 + 4 + 3) + (4 + 4 + 3) = 70
        assertEquals(6.0/70, _hss.memoryFactor());
    }

}
