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
    
    public static void mergeQueues(Queue<String> qLeft, int sep, Queue<String> qRight, Queue<String> qMerged) {
        int len1 = qLeft.isEmpty() ? 0 : qLeft.peek().length();
        int len2 = qRight.isEmpty() ? 0 : qRight.peek().length();
        String spaces = newString(' ', sep);
        while(!qLeft.isEmpty() && !qRight.isEmpty()) {
            qMerged.add(qLeft.remove() + spaces + qRight.remove());
        }
        while(!qLeft.isEmpty()) {
            qMerged.add(qLeft.remove() + spaces + newString(' ', len2));
        }
        while(!qRight.isEmpty()) {
            qMerged.add(newString(' ', len1) + spaces + qRight.remove());
        }
    }
    
    public static Queue<String> initializeQueue(Queue<String> qLeft, String label, Queue<String>qRight) {
        String strLeft = qLeft.isEmpty() ? "" : qLeft.peek();
        String strRight = qRight.isEmpty() ? "" : qRight.peek();
        int iLeft = strLeft.indexOf(']');
        int iRight = strRight.indexOf('[');
        String line1 = "";
        String line2 = "";
        if (iLeft >= 0) {
            line1 += newString(' ', iLeft) + " " + newString('_', strLeft.length() - iLeft - 1);
            line2 += newString(' ', iLeft) + "/" + newString(' ', strLeft.length() - iLeft - 1);
        }
        line1 += label;
        line2 += newString(' ', label.length());
        if (iRight >= 0) {
            line1 += newString('_', iRight) + " " + newString(' ', strRight.length() - iRight - 1);
            line2 += newString(' ', iRight) + "\\" + newString(' ', strRight.length() - iRight - 1);
        }

        Queue<String> queue = new LinkedList<String>();
        queue.add(line1);
        queue.add(line2);
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
        Queue<String> qOutput = initializeQueue(qLeft, nodeLabel, qRight);
        mergeQueues(qLeft, nodeLabel.length(), qRight, qOutput);
        return qOutput;
    }
    
    public String toString() {
        return String.format("[%d]", data);
    }
}
