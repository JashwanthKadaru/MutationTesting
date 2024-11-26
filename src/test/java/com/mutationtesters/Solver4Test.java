package com.mutationtesters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Solver4Test {
    @Test
    public void testSolver4() {
      Solver4 solver = new Solver4();
      solver.n = 6;
      solver.m = 7;
      solver.nums1 = new int[]{1, 3, 4, 5, 9, 11};
      solver.nums2 = new int[]{2, 6, 7, 8, 12, 14, 16};
      solver.solve();
      double expectedAns=7.0000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_2() {
      Solver4 solver = new Solver4();
      solver.n = 1;
      solver.m = 2;
      solver.nums1 = new int[]{1};
      solver.nums2 = new int[]{2};
      solver.solve();
      double expectedAns=1.5000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_3() {
      Solver4 solver = new Solver4();
      solver.n = 2;
      solver.m = 3;
      solver.nums1 = new int[]{1, 3};
      solver.nums2 = new int[]{2, 4, 5};
      solver.solve();
      double expectedAns=3.0000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_4() {
      Solver4 solver = new Solver4();
      solver.n = 3;
      solver.m = 2;
      solver.nums1 = new int[]{2, 3, 4};
      solver.nums2 = new int[]{1, 5};
      solver.solve();
      double expectedAns=3.0000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_5() {
      Solver4 solver = new Solver4();
      solver.n = 3;
      solver.m = 3;
      solver.nums1 = new int[]{1, 3, 5};
      solver.nums2 = new int[]{2, 4, 6};
      solver.solve();
      double expectedAns=3.5000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_6() {
      Solver4 solver = new Solver4();
      solver.n = 0;
      solver.m = 1;
      solver.nums1 = new int[]{};
      solver.nums2 = new int[]{1};
      solver.solve();
      double expectedAns=1.0000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_7() {
      Solver4 solver = new Solver4();
      solver.n = 2;
      solver.m = 2;
      solver.nums1 = new int[]{1, 2};
      solver.nums2 = new int[]{2, 3};
      solver.solve();
      double expectedAns=2.0000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_8() {
      Solver4 solver = new Solver4();
      solver.n = 2;
      solver.m = 2;
      solver.nums1 = new int[]{1, 3};
      solver.nums2 = new int[]{2, 3};
      solver.solve();
      double expectedAns=2.5000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_9() {
      Solver4 solver = new Solver4();
      solver.n = 3;
      solver.m = 3;
      solver.nums1 = new int[]{1, 2, 3};
      solver.nums2 = new int[]{3, 4, 5};
      solver.solve();
      double expectedAns=3.0000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testSolver4_NotSortedInputs() {
      Solver4 solver = new Solver4();
      solver.n = 3;
      solver.m = 3;
      solver.nums1 = new int[]{1, 3, 2};
      solver.nums2 = new int[]{4, 3, 5};
      solver.solve();
      double expectedAns=0.0000;
      assertEquals(expectedAns, solver.ans, 0.00001);;
      return;
    }

    @Test
    public void testReturnZero() {
        Solver4 solver = new Solver4();
        solver.n = 3;
        solver.m = 3;
        solver.nums1 = new int[]{3, 2, 1};
        solver.nums2 = new int[]{6, 5, 4};
        solver.solve();
        assertEquals(0.0, solver.ans, 0.00001);
    }

    @Test
    public void testConditionL1LessEqualR2() {
        Solver4 solver = new Solver4();
        solver.n = 3;
        solver.m = 3;
        solver.nums1 = new int[]{1, 2, 3};
        solver.nums2 = new int[]{3, 4, 5};
        solver.solve();
        assertEquals(3.0, solver.ans, 0.00001);
    }

    @Test
    public void testConditionL1GreaterR2() {
        Solver4 solver = new Solver4();
        solver.n = 3;
        solver.m = 3;
        solver.nums1 = new int[]{4, 5, 6};
        solver.nums2 = new int[]{1, 2, 3};
        solver.solve();
        assertEquals(3.5, solver.ans, 0.00001);
    }

    @Test
    public void testMutationInMid1Minus1() {
        Solver4 solver = new Solver4();
        solver.n = 1;
        solver.m = 3;
        solver.nums1 = new int[]{1};
        solver.nums2 = new int[]{2, 3, 4};
        solver.solve();
        assertEquals(2.5, solver.ans, 0.00001);
    }

    public void testSolver4OneArrayEmpty() {
        Solver4 solver = new Solver4();
        solver.n = 0;
        solver.m = 5;
        solver.nums1 = new int[]{};
        solver.nums2 = new int[]{1, 2, 3, 4, 5};
        solver.solve();
        double expectedAns = 3.0;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }


    @Test
    public void testSolver4_10() {
        Solver4 solver = new Solver4();
        solver.n = 4;
        solver.m = 3;
        solver.nums1 = new int[]{2, 2, 2, 2};
        solver.nums2 = new int[]{2, 2, 3};
        solver.solve();
        double expectedAns = 2.0;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }

    @Test
    public void testSolver4LargeArrays() {
        Solver4 solver = new Solver4();
        solver.n = 999;
        solver.m = 999;
        solver.nums1 = new int[999];
        solver.nums2 = new int[999];
        for (int i = 0; i < 999; i++) {
            solver.nums1[i] = i * 2;
            solver.nums2[i] = i * 2 + 1;
        }
        solver.solve();
        double expectedAns = 998.5;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }

    @Test
    public void testSolver4BoundaryValues() {
        Solver4 solver = new Solver4();
        solver.n = 2;
        solver.m = 2;
        solver.nums1 = new int[]{-1000000, 1000000};
        solver.nums2 = new int[]{-1000000, 1000000};
        solver.solve();
        double expectedAns = 0.0;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }

    @Test
    public void testSolver4AllNegative() {
        Solver4 solver = new Solver4();
        solver.n = 5;
        solver.m = 5;
        solver.nums1 = new int[]{-10, -9, -8, -7, -6};
        solver.nums2 = new int[]{-5, -4, -3, -2, -1};
        solver.solve();
        double expectedAns = -5.5;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }

    @Test
    public void testSolver4WithDuplicates() {
        Solver4 solver = new Solver4();
        solver.n = 6;
        solver.m = 5;
        solver.nums1 = new int[]{1, 2, 2, 2, 3, 4};
        solver.nums2 = new int[]{2, 2, 3, 4, 5};
        solver.solve();
        double expectedAns = 2.0;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }

    @Test
    public void testSolver4UnequalLengths() {
        Solver4 solver = new Solver4();
        solver.n = 1;
        solver.m = 9;
        solver.nums1 = new int[]{50};
        solver.nums2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        solver.solve();
        double expectedAns = 5.5;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }

    @Test
    public void testSolver4OddTotalLength() {
        Solver4 solver = new Solver4();
        solver.n = 4;
        solver.m = 3;
        solver.nums1 = new int[]{1, 3, 5, 7};
        solver.nums2 = new int[]{2, 4, 6};
        solver.solve();
        double expectedAns = 4.0;
        assertEquals(expectedAns, solver.ans, 0.00001);
    }
}
