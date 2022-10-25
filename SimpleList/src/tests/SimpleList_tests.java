package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.SimpleList;

class SimpleList_tests {

    @Test
    void test_add() {
        SimpleList sList = new SimpleList();
        sList.add(11);
        sList.add(22);
        sList.add(33);
        assertEquals(3, sList.size());
        assertEquals(11, sList.get(0));
        assertEquals(22, sList.get(1));
        assertEquals(33, sList.get(2));
    }
    
    @Test
    void test_addAtIndex() {
        SimpleList sList = new SimpleList();
        sList.add(0, "abc");
        sList.add(1, "def");
        sList.add(0, "ghi");
        assertEquals(3, sList.size());
        assertEquals("ghi", sList.get(0));
        assertEquals("abc", sList.get(1));
        assertEquals("def", sList.get(2));
    }
    
    @Test
    void test_remove() {
        SimpleList sList = new SimpleList();
        sList.add(0, "x");
        sList.add(1, "y");
        sList.add(2, "z");
        String str = (String)sList.remove(1);
        assertEquals("y", str);
        assertEquals(2, sList.size());
        assertEquals("x", sList.get(0));
        assertEquals("z", sList.get(1));
        str = (String)sList.remove(0);
        assertEquals(1, sList.size());
        assertEquals("x", str);
        assertEquals("z", sList.get(0));
        str = (String)sList.remove(0);
        assertEquals(0, sList.size());
        assertEquals("z", str);
    }
}
