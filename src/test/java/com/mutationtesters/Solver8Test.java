package com.mutationtesters;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * JUnit Test Suite for Solver8 - Binary Tree Maximum Path Sum
 */
public class Solver8Test {

    /**
     * Helper method to build a binary tree from an array.
     * The array represents the tree in level-order traversal where "null" indicates missing nodes.
     *
     * @param nodeValues Array of Strings representing the tree nodes in level-order.
     *                   Use "null" (without quotes) to represent missing nodes.
     * @return The root of the binary tree.
     */
    private Solver8.TreeNode buildTree(String[] nodeValues) {
        if (nodeValues.length == 0 || nodeValues[0].equalsIgnoreCase("null")) {
            return null;
        }

        Solver8.TreeNode root = new Solver8.TreeNode(Integer.parseInt(nodeValues[0]));
        Queue<Solver8.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;

        while (index < nodeValues.length) {
            Solver8.TreeNode current = queue.poll();

            // Assign left child
            if (index < nodeValues.length && !nodeValues[index].equalsIgnoreCase("null")) {
                Solver8.TreeNode leftChild = new Solver8.TreeNode(Integer.parseInt(nodeValues[index]));
                current.left = leftChild;
                queue.add(leftChild);
            }
            index++;

            // Assign right child
            if (index < nodeValues.length && !nodeValues[index].equalsIgnoreCase("null")) {
                Solver8.TreeNode rightChild = new Solver8.TreeNode(Integer.parseInt(nodeValues[index]));
                current.right = rightChild;
                queue.add(rightChild);
            }
            index++;
        }

        return root;
    }

    /**
     * Helper method to validate the expected maximum path sum.
     *
     * @param expected The expected maximum path sum.
     * @param actual   The actual maximum path sum returned by Solver8.
     */
    private void validateMaxPathSum(int expected, int actual) {
        assertEquals("Maximum path sum does not match the expected value.", expected, actual);
    }

    @Test
    public void testEmptyTree() {
        Solver8 solver = new Solver8();
        solver.root = null;
        solver.solve();
        // Assuming the solver returns Integer.MIN_VALUE for empty trees
        validateMaxPathSum(Integer.MIN_VALUE, solver.maxSum);
    }

    @Test
    public void testSingleNodeTree() {
        Solver8 solver = new Solver8();
        String[] values = {"5"};
        solver.root = buildTree(values);
        solver.solve();
        validateMaxPathSum(5, solver.maxSum);
    }

    @Test
    public void testOnlyLeftChildren() {
        Solver8 solver = new Solver8();
        String[] values = {"1", "2", "null", "3"};
        /*
                1
               /
              2
             /
            3
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 3 + 2 + 1 = 6
        validateMaxPathSum(6, solver.maxSum);
    }

    @Test
    public void testOnlyRightChildren() {
        Solver8 solver = new Solver8();
        String[] values = {"1", "null", "2", "null", "3"};
        /*
                1
                 \
                  2
                   \
                    3
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 1 + 2 + 3 = 6
        validateMaxPathSum(6, solver.maxSum);
    }

    @Test
    public void testBalancedTree() {
        Solver8 solver = new Solver8();
        String[] values = {"1", "2", "3"};
        /*
                1
               / \
              2   3
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 2 + 1 + 3 = 6
        validateMaxPathSum(6, solver.maxSum);
    }

    @Test
    public void testUnbalancedTree() {
        Solver8 solver = new Solver8();
        String[] values = {"10", "2", "10", "null", "null", "20", "1"};
        /*
                10
               /  \
              2    10
                   / \
                 20   1
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 20 + 10 + 10 + 2 = 42
        validateMaxPathSum(42, solver.maxSum);
    }

    @Test
    public void testTreeWithNegativeValues() {
        Solver8 solver = new Solver8();
        String[] values = {"-10", "9", "20", "null", "null", "15", "7"};
        /*
                -10
                /  \
               9    20
                   /  \
                 15    7
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 15 + 20 + 7 = 42
        validateMaxPathSum(42, solver.maxSum);
    }

    @Test
    public void testTreeWithMixedPositiveAndNegativeValues() {
        Solver8 solver = new Solver8();
        String[] values = {"2", "5", "-3", "null", "null", "-2", "4"};
        /*
                2
               / \
              5  -3
                 / \
               -2   4
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 5 + 2 -3 + 4
        validateMaxPathSum(8, solver.maxSum);
    }

    @Test
    public void testTreeWithMultipleMaximumPaths() {
        Solver8 solver = new Solver8();
        String[] values = {"-10", "2", "3", "2", "1", "1", "1"};
        /*
               -10
               / \
              2   3
             / \ / \
             2 1 1  1
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: Either 2 + 2 + 1 or 3 + 1 + 1
        validateMaxPathSum(5, solver.maxSum);
    }

    @Test
    public void testTreeWithAllSameValues() {
        Solver8 solver = new Solver8();
        String[] values = {"5", "5", "5", "5", "5", "5", "5"};
        /*
                  5
                /   \
               5     5
              / \   / \
             5   5 5   5
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 5 + 5 + 5 + 5 + 5 = 25 (one of the possible paths)
        validateMaxPathSum(25, solver.maxSum);
    }

    @Test
    public void testLargeTree() {
        Solver8 solver = new Solver8();
        // Building a large balanced tree with depth 4
        String[] values = {
                "1",
                "2", "3",
                "4", "5", "6", "7",
                "8", "9", "10", "11", "12", "13", "14", "15"
        };
        /*
                         1
                      /     \
                     2        3
                   / \       / \
                  4   5     6    7
                 /\   /\   /\    /\
                8 9 10 11 12 13 14 15
        */
        solver.root = buildTree(values);
        solver.solve();
        //15, 7, 3, 6, 13
        validateMaxPathSum(44, solver.maxSum);
    }

    @Test
    public void testTreeWithMultipleLevelsOfDepth() {
        Solver8 solver = new Solver8();
        String[] values = {"10", "2", "10", "null", "null", "20", "1", "null", "null", "25", "30"};
        /*
                    10
                   /  \
                  2    10
                      /  \
                    20    1
                          / \
                        25  30
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 30 + 1 + 20 + 10 = 61
        validateMaxPathSum(61, solver.maxSum);
    }

    @Test
    public void testTreeWithAllNegativeNodes() {
        Solver8 solver = new Solver8();
        String[] values = {"-1", "-2", "-3"};
        /*
                -1
               /  \
             -2    -3
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: -1
        validateMaxPathSum(-1, solver.maxSum);
    }

    @Test
    public void testTreeWithAllZeroNodes() {
        Solver8 solver = new Solver8();
        String[] values = {"0", "0", "0"};
        /*
                0
               / \
              0   0
        */
        solver.root = buildTree(values);
        solver.solve();
        // Maximum path: 0
        validateMaxPathSum(0, solver.maxSum);
    }
}
