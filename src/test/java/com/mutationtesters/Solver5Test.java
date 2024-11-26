package com.mutationtesters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Solver5Test {
    @Test
    public void testSolver5() {
      Solver5 solver = new Solver5();
      solver.str="(){}{[(()())]}()";

      solver.solve();

      assertTrue(solver.ans);
      return;
    }

    @Test
    public void testSolver5UnbalancedStackNonEmpty() {
        // Simple unbalanced parentheses
        Solver5 solver = new Solver5();
        solver.str = "([{}]";

        solver.solve();

        assertFalse(solver.ans);
    }

    @Test
    public void testSolver5UnbalancedSimple() {
        // Simple unbalanced parentheses
        Solver5 solver = new Solver5();
        solver.str = "([)]";

        solver.solve();

        assertFalse(solver.ans);
    }

    @Test
    public void testSolver5NestedBalanced() {
        // Nested balanced parentheses
        Solver5 solver = new Solver5();
        solver.str = "{[()]}";

        solver.solve();

        assertTrue(solver.ans);
    }

    @Test
    public void testSolver5UnmatchedOpenClose() {
        // Unmatched open and close parentheses
        Solver5 solver = new Solver5();
        solver.str = "{[(])}";

        solver.solve();

        assertFalse(solver.ans);
    }

    @Test
    public void testSolver5LongBalanced() {
        // Long balanced string
        Solver5 solver = new Solver5();
        solver.str = "(((([[[{{{}}}]]]))))";

        solver.solve();

        assertTrue(solver.ans);
    }

    @Test
    public void testSolver5EmptyString() {
        // Empty string input
        Solver5 solver = new Solver5();
        solver.str = "";

        solver.solve();

        assertTrue(solver.ans);
    }

    @Test
    public void testSolver5ExtraClosing() {
        // Extra closing parenthesis
        Solver5 solver = new Solver5();
        solver.str = "(()))";

        solver.solve();

        assertFalse(solver.ans);
    }
}
