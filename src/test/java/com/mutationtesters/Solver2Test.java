package com.mutationtesters;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Solver2Test {
    @Test
    public void testSolver2() {
      Solver2 solver  = new Solver2();
      solver.n=4;
      solver.target=9;
      solver.nums = new int[]{2,7,11,15};
      solver.solve();
      int[] expectedAns = {0, 1};
      int[] actualAns = {solver.first, solver.last};
      assertArrayEquals(expectedAns, actualAns);
      return;
    } 

    @Test
    public void testSolver2NegativeValues() {
        // Includes negative numbers
        Solver2 solver = new Solver2();
        solver.n = 4;
        solver.target = -1;
        solver.nums = new int[]{-3, -4, 90, 3};
        solver.solve();
        int[] expectedAns = {1, 3};
        int[] actualAns = {solver.first, solver.last};
        assertArrayEquals(expectedAns, actualAns);
    }

    @Test
    public void testSolver2MultiplePairs() {
        // Multiple pairs but only one correct answer
        Solver2 solver = new Solver2();
        solver.n = 5;
        solver.target = 6;
        solver.nums = new int[]{3, 2, 4, 3, 3};
        solver.solve();
        int[] expectedAns = {1, 2};
        int[] actualAns = {solver.first, solver.last};
        assertArrayEquals(expectedAns, actualAns);
    }

    @Test
    public void testSolver2SameValuePair() {
        // Pair formed by the same value twice
        Solver2 solver = new Solver2();
        solver.n = 4;
        solver.target = 10;
        solver.nums = new int[]{5, 5, 11, 15};
        solver.solve();
        int[] expectedAns = {0, 1};
        int[] actualAns = {solver.first, solver.last};
        assertArrayEquals(expectedAns, actualAns);
    }

    @Test
    public void testSolver2NoSolution() {
        // No valid pair exists
        Solver2 solver = new Solver2();
        solver.n = 3;
        solver.target = 100;
        solver.nums = new int[]{1, 2, 3};
        solver.solve();
        int[] expectedAns = {-1, -1}; // Assuming solver uses -1, -1 for no solution
        int[] actualAns = {solver.first, solver.last};
        assertArrayEquals(expectedAns, actualAns);
    }
}
