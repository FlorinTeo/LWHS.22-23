package main;

public class NumCalc {
    
    private RawNode _head = null;
    private RawNode _trace = null;
    
    /**
     * Class constructor.
     */
    public NumCalc() {
        // initialize the calculator
    }
    
    /**
     * Takes the expression strings and builds the internal
     * double-linked list storing the expression nodes.
     * @param exprStrings - components of the expression string 
     */
    private void buildExprList(String[] exprStrings) {
        for(String exprString : exprStrings) {
            RawNode newNode = RawNode.createNode(exprString);
            if (_head == null) {
                _head = newNode;
            } else {
                _head.addTail(newNode);
            }
        }
    }
    
    /**
     * Evaluates the expression stored in the double linked list
     * and returns the final result. 
     * @return - evaluation result.
     */
    private String evalExprList() {
        addTraceFrame();
        return "<Result not implemented yet>";
    }
    
    /**
     * Constructs a new trace frame string from the expression list
     * and adds it to the trace list.
     */
    private void addTraceFrame() {
        String traceFrame = "";
        for(RawNode node = _head; node != null; node = node.getNext()) {
            traceFrame += node.getRawContent() + " ";
        }
        
        RawNode newTraceNode = RawNode.createNode(traceFrame);
        if (_trace == null) {
            _trace = newTraceNode;
        } else {
            _trace.addTail(newTraceNode);
        }
    }
    
    /**
     * Takes a raw expression and returns the final evaluation result.
     * @param expression as entered by the user.
     * @return - evaluation result.
     */
    public String evaluate(String expression) {
        _head = null;
        _trace = null;
        String[] exprParts = expression.split(" ");
        buildExprList(exprParts);
        return evalExprList();
    }
    
    /**
     * Gives the evaluation trace of the last evaluated expression,
     * as a multi-line string.
     */
    @Override
    public String toString() {
        String output = "";
        for(RawNode node = _trace; node != null; node = node.getNext()) {
            output += node.getRawContent() + "\n";
        }
        
        return output;
    }
}
