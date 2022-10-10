package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.NumNode;

class NumNode_tests {

    @Test
    void test_createNode() {
        NumNode num = NumNode.createNode("123");
        assertNotNull(num);
        assertEquals(123, num.getNumValue());
        
        num = NumNode.createNode("");
        assertNull(num);
        
        num = NumNode.createNode("-12.34");
        assertNotNull(num);
        assertEquals(-12.34, num.getNumValue());
        
        num = NumNode.createNode("abc");
        assertNull(num);
    }

}
