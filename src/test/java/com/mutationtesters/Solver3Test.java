package com.mutationtesters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Solver3Test {
    @Test
    public void testSolver3() {
      Solver3 solver = new Solver3();
      solver.str="abcabcbb";
      int ans=3;

      solver.solve();

      assertEquals(ans, solver.ans);
      return;
    }  

    @Test
    public void testSolver3AllUnique() {
        // All characters are unique
        Solver3 solver = new Solver3();
        solver.str = "abcdef";
        int ans = 6;

        solver.solve();

        assertEquals(ans, solver.ans);
    }

    @Test
    public void testSolver3AllRepeating() {
        // All characters are the same
        Solver3 solver = new Solver3();
        solver.str = "aaaaa";
        int ans = 1;

        solver.solve();

        assertEquals(ans, solver.ans);
    }

    @Test
    public void testSolver3MixedPattern() {
        // Mixed repeating and unique pattern
        Solver3 solver = new Solver3();
        solver.str = "pwwkew";
        int ans = 3;

        solver.solve();

        assertEquals(ans, solver.ans);
    }

    @Test
    public void testSolver3EmptyString() {
        // Empty input string
        Solver3 solver = new Solver3();
        solver.str = "";
        int ans = 0;

        solver.solve();

        assertEquals(ans, solver.ans);
    }

    @Test
    public void testSolver3SingleCharacter() {
        // Single character input
        Solver3 solver = new Solver3();
        solver.str = "b";
        int ans = 1;

        solver.solve();

        assertEquals(ans, solver.ans);
    }
}
