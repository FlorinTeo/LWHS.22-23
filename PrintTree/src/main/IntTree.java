package main;

public class IntTree {
    private IntTreeNode overallRoot;
    
    public IntTree() {
        overallRoot = null;
    }
    
    public void addValue(int data) {
        if (overallRoot == null) {
            overallRoot = new IntTreeNode(data);
        } else {
            overallRoot.insert(data);
        }
    }
    
    public String toPreOrderString() {
        String output = "";
        if (overallRoot != null) {
            output += overallRoot.toPreOrderString();
        }
        return output;
    }
    
    public String toInOrderString() {
        String output = "";
        if (overallRoot != null) {
            output += overallRoot.toInOrderString();
        }
        return output;
    }

    public String toPostOrderString() {
        String output = "";
        if (overallRoot != null) {
            output += overallRoot.toPostOrderString();
        }
        return output;
    }

}
