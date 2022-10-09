package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import main.RawNode;

class RawNode_tests {

    @Test
    void test_createRawNode() {
        RawNode n = RawNode.createNode("raw content");
        assertEquals("raw content", n.getRawContent());
        assertNull(n.getNext());
        assertNull(n.getPrev());
    }
    
    @Test
    void test_addNext() {
        RawNode n1 = RawNode.createNode("A");
        RawNode n2 = RawNode.createNode("B");
        n1.addNext(n2);
        assertEquals(n2, n1.getNext());
        assertEquals(n1, n2.getPrev());
        assertNull(n1.getPrev());
        assertNull(n2.getNext());
        
        RawNode n3 = RawNode.createNode("C");
        n1.addNext(n3);
        assertEquals(n1, n3.getPrev());
        assertEquals(n2, n3.getNext());
        assertEquals(n3, n1.getNext());
        assertEquals(n3, n2.getPrev());
    }
    
    @Test
    void test_addTail() {
        RawNode n1 = RawNode.createNode("A");
        RawNode n2 = RawNode.createNode("B");
        RawNode n3 = RawNode.createNode("C");
        n1.addNext(n2);
        n2.addNext(n3);
        
        RawNode n = RawNode.createNode("D");
        n1.addTail(n);
        assertEquals(n, n3.getNext());
        assertEquals(n3, n.getPrev());
        assertNull(n.getNext());
    }
}
