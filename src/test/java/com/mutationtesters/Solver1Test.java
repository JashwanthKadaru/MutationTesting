package com.mutationtesters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Solver1Test {
  @Test
    public void testSolver1() {
        Solver1 solver = new Solver1();
        solver.V=6;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < solver.V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        solver.adj=adj;
        solver.solve();

        int[] expectedTopo = {4, 5, 0, 2, 3, 1};
        assertArrayEquals(expectedTopo, solver.topo);
    }

    @Test
    public void testSingleNodeGraph() {
        Solver1 solver = new Solver1();
        int V = 1;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>()); // Node 0

        solver.V=V;
        solver.adj=adj;
        solver.solve();
        int[] expectedTopo = {0};
        assertArrayEquals(expectedTopo, solver.topo);
    }

    @Test
    public void testLinearGraph() {
        Solver1 solver = new Solver1();
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);

        solver.V=V;
        solver.adj=adj;
        solver.solve();
        int[] expectedTopo = {0, 1, 2, 3, 4};
        assertArrayEquals(expectedTopo, solver.topo);
    }

    @Test
    public void testDisconnectedGraph() {
        Solver1 solver = new Solver1();
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(2).add(3);

        solver.V=V;
        solver.adj=adj;
        solver.solve();
        assertTrue(isValidTopoSort(V, adj, solver.topo));
    }

    @Test
    public void testMultipleIncomingEdges() {
        Solver1 solver = new Solver1();
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(2);
        adj.get(1).add(2);
        adj.get(2).add(3);

        solver.V=V;
        solver.adj=adj;
        solver.solve();
        assertTrue(isValidTopoSort(V, adj, solver.topo));
    }

    @Test
    public void testDiamondShapedGraph() {
        Solver1 solver = new Solver1();
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(3);
        adj.get(3).add(4);

        solver.V=V;
        solver.adj=adj;
        solver.solve();
        
        assertTrue(isValidTopoSort(V, adj, solver.topo));
    }

    @Test
    public void testEmptyGraph() {
        Solver1 solver = new Solver1();
        int V = 3;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        solver.V=V;
        solver.adj=adj;
        solver.solve();
      
        assertTrue(isValidTopoSort(V, adj, solver.topo));
    }

    @Test
    public void testStarGraph() {
        Solver1 solver = new Solver1();
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(0).add(3);
        adj.get(0).add(4);

        solver.V=V;
        solver.adj=adj;
        solver.solve();

        assertTrue(isValidTopoSort(V, adj, solver.topo));
    }

    @Test
    public void testGraphWithMultipleComponents() {
        Solver1 solver = new Solver1();
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(2).add(3);
        adj.get(4).add(5);

        solver.V=V;
        solver.adj=adj;
        solver.solve();

        assertTrue(isValidTopoSort(V, adj, solver.topo));
    }

    // @Test
    // public void testGraphWithCyclesHandled() {
    //     Solver1 solver = new Solver1();
    //     int V = 4;
    //     ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    //     for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    //     adj.get(0).add(1);
    //     adj.get(1).add(2);
    //     adj.get(2).add(3);
    //     adj.get(3).add(1); // Back edge forming a cycle

    //     solver.V=V;
    //     solver.adj=adj;
    //     solver.solve();

    //     assertEquals(0, result.length); // Ensure no result for cyclic graph
    // }

    @Test
    public void testComplexGraph() {
        Solver1 solver = new Solver1();
        int V = 8;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(5).add(6);
        adj.get(6).add(7);

        solver.V=V;
        solver.adj=adj;
        solver.solve();
        
        assertTrue(isValidTopoSort(V, adj, solver.topo));
    }

    private boolean isValidTopoSort(int V, ArrayList<ArrayList<Integer>> adj, int[] result) {
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < result.length; i++) {
            position.put(result[i], i);
        }

        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                if (position.get(i) > position.get(neighbor)) {
                    return false;
                }
            }
        }
        return true;
    }
}
