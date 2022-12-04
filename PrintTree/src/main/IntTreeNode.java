package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IntTreeNode {
    private int data;            // data stored at this node
    private IntTreeNode left;    // reference to left subtree
    private IntTreeNode right;   // reference to right subtree
    
    // Constructs a leaf node with given data.
    public IntTreeNode(int data) {
        this(data, null, null);
    }
    
    // Constructs a IntTreeNode with the given data and links.
    public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    public static String newString(char ch, int count) {
        char[] chArr = new char[count];
        Arrays.fill(chArr, ch);
        return new String(chArr);
    }
    
    public void insert(int data) {
        if (data <= this.data) {
           if (left == null) {
               left = new IntTreeNode(data);
           } else {
               left.insert(data);
           }
        } else {
            if (right == null) {
                right = new IntTreeNode(data);
            } else {
                right.insert(data);
            }
        }
    }
    
    public String toPreOrderString() {
        String output = String.format("[%d](", data);
        if (left != null) {
            output += left.toPreOrderString();
        }
        output += ")(";
        if (right != null) {
            output += right.toPreOrderString();
        }
        output += ")";
        return output;
    }
    
    public String toInOrderString() {
        String output = "(";
        if (left != null) {
            output += left.toInOrderString();
        }
        output += String.format(")[%d](", data);
        if (right != null) {
            output += right.toInOrderString();
        }
        output += ")";
        return output;
    }
    
    public String toPostOrderString() {
        String output = "(";
        if (left != null) {
            output += left.toPostOrderString();
        }
        output += ")(";
        if (right != null) {
            output += right.toPostOrderString();
        }
        output += String.format(")[%d]", data);
        return output;
    }
    
    public Queue<String> toPrettyPrint() {
        Queue<String> output = new LinkedList<String>();
        if (left == null && right == null) {
            output.add(toString()); 
        } else if (left != null && right == null) {
            
        } else if (left == null && right != null) {
            
        } else {  // left != null && right != null
            
        }
        return output;
    }
    
    public String toString() {
        return String.format("[%d]", data);
    }
}
