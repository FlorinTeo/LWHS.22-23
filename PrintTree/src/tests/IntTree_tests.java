package tests;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import main.IntTree;
import main.IntTreeNode;

class IntTree_tests {

    private IntTree newTree(int... values) {
        IntTree tree = new IntTree();
        for(int data : values) {
            tree.addValue(data);
        }
        return tree;
    }

    @Test
    void test_toPreOrderString() {
        IntTree tree = new IntTree();
        Assert.assertEquals("", tree.toPreOrderString());
        tree = newTree(2);
        Assert.assertEquals("[2]()()", tree.toPreOrderString());
        tree = newTree(2, 1, 3);
        Assert.assertEquals("[2]([1]()())([3]()())", tree.toPreOrderString());
        tree = newTree(7,5, 9, 4, 12, 6, 10);
        Assert.assertEquals("[7]([5]([4]()())([6]()()))([9]()([12]([10]()())()))", tree.toPreOrderString());
    }

    @Test
    void test_toInOrderString() {
        IntTree tree = new IntTree();
        Assert.assertEquals("", tree.toInOrderString());
        tree = newTree(2);
        Assert.assertEquals("()[2]()", tree.toInOrderString());
        tree = newTree(2, 1, 3);
        Assert.assertEquals("(()[1]())[2](()[3]())", tree.toInOrderString());
        tree = newTree(7,5, 9, 4, 12, 6, 10);
        Assert.assertEquals("((()[4]())[5](()[6]()))[7](()[9]((()[10]())[12]()))", tree.toInOrderString());
    }
    
    @Test
    void test_toPostOrderString() {
        IntTree tree = new IntTree();
        Assert.assertEquals("", tree.toPostOrderString());
        tree = newTree(2);
        Assert.assertEquals("()()[2]", tree.toPostOrderString());
        tree = newTree(2, 1, 3);
        Assert.assertEquals("(()()[1])(()()[3])[2]", tree.toPostOrderString());
        tree = newTree(7,5, 9, 4, 12, 6, 10);
        Assert.assertEquals("((()()[4])(()()[6])[5])(()((()()[10])()[12])[9])[7]", tree.toPostOrderString());
    }
}
