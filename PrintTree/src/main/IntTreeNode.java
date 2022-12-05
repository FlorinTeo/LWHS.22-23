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
    
    public static void mergeQueues(Queue<String> q1, int sep, Queue<String> q2, Queue<String> qMerged) {
        int len1 = q1.isEmpty() ? 0 : q1.peek().length();
        int len2 = q2.isEmpty() ? 0 : q2.peek().length();
        String spaces = newString(' ', sep);
        while(!q1.isEmpty() && !q2.isEmpty()) {
            qMerged.add(q1.remove() + spaces + q2.remove());
        }
        while(!q1.isEmpty()) {
            qMerged.add(q1.remove() + spaces + newString(' ', len2));
        }
        while(!q2.isEmpty()) {
            qMerged.add(newString(' ', len1) + spaces + q2.remove());
        }
    }
    
    public static Queue<String> newQueue(Queue<String> q1, String label, Queue<String>q2) {
        Queue<String> queue = new LinkedList<String>();
        return queue;
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
        String nodeLabel = toString();
        Queue<String> qLeft = left != null 
                ? left.toPrettyPrint() 
                : new LinkedList<String>();
        Queue<String> qRight = right != null 
                ? right.toPrettyPrint()
                : new LinkedList<String>();
        Queue<String> qOutput = newQueue(qLeft, nodeLabel, qRight);
        mergeQueues(qLeft, nodeLabel.length(), qRight, qOutput);
        return qOutput;
    }
    
    public String toString() {
        return String.format("[%d]", data);
    }
}
