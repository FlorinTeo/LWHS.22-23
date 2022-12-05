package tests;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import main.IntTreeNode;

class IntTreeNode_tests {
    
    @Test
    void test_newString() {
        Assert.assertEquals("", IntTreeNode.newString('x', 0));
        Assert.assertEquals("xxxxxxx", IntTreeNode.newString('x', 7));
    }
    
    @Test
    void test_mergeQueues() {
        Queue<String> q1 = new LinkedList<String>(Arrays.asList("abc", "def" ));
        Queue<String> q2 = new LinkedList<String>(Arrays.asList("1234", "5678"));
        Queue<String> qMerged = new LinkedList<String>();
        IntTreeNode.mergeQueues(q1, 1, q2, qMerged);
        Assert.assertEquals("abc 1234", qMerged.remove());
        Assert.assertEquals("def 5678", qMerged.remove());
        
        q1 = new LinkedList<String>(Arrays.asList("abc", "def", "ghj" ));
        q2 = new LinkedList<String>(Arrays.asList("1234", "5678"));
        IntTreeNode.mergeQueues(q1, 1 ,q2, qMerged);
        Assert.assertEquals("abc 1234", qMerged.remove());
        Assert.assertEquals("def 5678", qMerged.remove());
        Assert.assertEquals("ghj     ", qMerged.remove());

        q1 = new LinkedList<String>(Arrays.asList("abc", "def"));
        q2 = new LinkedList<String>(Arrays.asList("1234", "5678", "9012"));
        IntTreeNode.mergeQueues(q1, 1, q2, qMerged);
        Assert.assertEquals("abc 1234", qMerged.remove());
        Assert.assertEquals("def 5678", qMerged.remove());
        Assert.assertEquals("    9012", qMerged.remove());
    
        q1 = new LinkedList<String>(Arrays.asList("abc", "def"));
        q2 = new LinkedList<String>();
        IntTreeNode.mergeQueues(q1, 1, q2, qMerged);
        Assert.assertEquals("abc ", qMerged.remove());
        Assert.assertEquals("def ", qMerged.remove());
    }
}