package com.mutationtesters;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class Solver11Test {
    @Test
    public void testSolver11SimpleGraph() {
        // Simple graph with known shortest paths
        Solver11 solver = new Solver11();
        String input = "3 2\n3\n0 1 1\n0 2 6\n1 2 3\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(4, 3, 0);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11SingleNode() {
        // Graph with a single node
        Solver11 solver = new Solver11();
        String input = "1 0\n0\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11ZeroWeightEdges() {
        // Graph with zero-weight edges
        Solver11 solver = new Solver11();
        String input = "3 0\n2\n0 1 0\n1 2 0\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0, 0, 0);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11GraphWithCycles() {
        // Graph containing cycles
        Solver11 solver = new Solver11();
        String input = "4 0\n5\n0 1 1\n1 2 1\n2 3 1\n3 0 1\n1 3 2\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0, 1, 2, 1);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11LargeGraph() {
        // Larger graph to test algorithm correctness
        Solver11 solver = new Solver11();
        String input = "6 0\n9\n0 1 7\n0 2 9\n0 5 14\n1 2 10\n1 3 15\n2 3 11\n2 5 2\n3 4 6\n4 5 9\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0, 7, 9, 20, 20, 11);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11LargeEdgeWeights() {
        // Graph with large edge weights
        Solver11 solver = new Solver11();
        String input = "3 0\n2\n0 1 1000000\n1 2 1000000\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0, 1000000, 2000000);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11MultipleShortestPaths() {
        // Graph with multiple shortest paths to a node
        Solver11 solver = new Solver11();
        String input = "4 0\n4\n0 1 1\n1 3 1\n0 2 1\n2 3 1\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0, 1, 1, 2);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11SourceIsLastNode() {
        // Graph where the source node has the highest index
        Solver11 solver = new Solver11();
        String input = "3 2\n2\n0 1 1\n1 2 2\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(3, 2, 0);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11GraphWithSelfLoops() {
        // Graph containing self-loops
        Solver11 solver = new Solver11();
        String input = "2 0\n2\n0 0 5\n0 1 1\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0, 1);
        assertEquals(expected, solver.ans);
    }

    @Test
    public void testSolver11MultipleEdgesBetweenNodes() {
        // Graph with multiple edges between the same nodes
        Solver11 solver = new Solver11();
        String input = "3 0\n4\n0 1 10\n0 1 1\n1 2 2\n0 2 100\n";
        Scanner sc = new Scanner(input);
        solver.takeInput(sc);
        solver.solve();
        List<Integer> expected = Arrays.asList(0, 1, 3);
        assertEquals(expected, solver.ans);
    }
}