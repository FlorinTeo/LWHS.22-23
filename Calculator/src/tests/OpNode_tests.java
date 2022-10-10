package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.NumNode;
import main.OpNode;
import main.OpNode.OpCode;

class OpNode_tests {

    @Test
    void test_createNode() {
        String[] opStrings =  {"+", "-", "*", "/", "%", "^" };
        OpNode.OpCode[] opCodes = {
                OpCode.ADDITION, 
                OpCode.SUBTRACTION,
                OpCode.MULTIPLICATION, 
                OpCode.DIVISION,
                OpCode.MODULO,
                OpCode.POWER };
        OpNode opNode;
        for (int i = 0; i < opStrings.length; i++) {
            opNode = OpNode.createNode(opStrings[i]);
            assertNotNull(opNode);
            assertEquals(opCodes[i], opNode.getOpCode());
        }
        
        opNode = OpNode.createNode("abc");
        assertNull(opNode);
    }
    
    @Test
    void test_evaluate() {
        String[][] tests = {
                // operand1, operator, "operand2", expected_result
                { "1", "+", "2", "3" },
                { "1", "-", "2", "-1" },
                { "3", "*", "4", "12" },
                { "4", "/", "-5", "-0.8" },
                { "7", "%", "5", "2" },
                { "4", "^", "2", "16" },
                { "8", "/", "0", null }
        };
        
        for (String[] test : tests) {
            NumNode n1 = NumNode.createNode(test[0]);
            OpNode op = OpNode.createNode(test[1]);
            NumNode n2 = NumNode.createNode(test[2]);
            n1.addNext(op);
            op.addNext(n2);
            try {
                NumNode result = op.evaluate();
                assertNotNull(test[3]);
                double expectedResult = Double.parseDouble(test[3]);
                assertNotNull(result);
                double actualResult = result.getNumValue();
                assertEquals(expectedResult, actualResult);
            } catch (RuntimeException e) {
                assertNull(test[3]);
                assertEquals("Division by zero", e.getMessage());
            }
        }
    }
}
