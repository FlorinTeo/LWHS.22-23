package main;

public class OpNode extends RawNode {
    
    public enum OpCode {
        UNKNOWN,
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        POWER,
        MODULO
    }
    
    private OpCode _opCode;
    
    /**
     * Class constructor. Builds a new operator node.
     * @param rawContent - the raw content stored in this node.
     */
    protected OpNode(String rawContent) {
        super(rawContent);
        _opCode = OpCode.UNKNOWN;
    }
    
    /**
     * OpNode factory method, returning a new operator node 
     * for the given raw content or null if the node could not be created.
     * @param rawContent - the raw content stored in this node.
     * @return the new OpNode as a RawNode.
     */
    public static RawNode createNode(String rawContent) {
        OpNode opNode = new OpNode(rawContent);
        switch(rawContent) {
        case "+":
            opNode._opCode = OpCode.ADDITION;
            break;
        case "-":
            opNode._opCode = OpCode.SUBTRACTION;
            break;
        case "*":
            opNode._opCode = OpCode.MULTIPLICATION;
            break;
        case "/":
            opNode._opCode = OpCode.DIVISION;
            break;
        case "%":
            opNode._opCode = OpCode.MODULO;
            break;
        default:
            opNode = null;
        }
        
        return opNode;
    }
    
    /**
     * Gets the node's operator code.
     * @return - the operator code.
     */
    public OpCode getOpCode() {
        return _opCode;
    }

}
