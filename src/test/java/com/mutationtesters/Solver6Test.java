package com.mutationtesters;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class Solver6Test {

    private ArrayList<ArrayList<Integer>> createAdjList(int[][] intervals) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            adj.add(new ArrayList<>());
            adj.get(i).add(intervals[i][0]);
            adj.get(i).add(intervals[i][1]);
        }
        return adj;
    }

    private void validateMergedIntervals(int[][] expected, int[][] actual) {
        // Sort both expected and actual intervals based on start times
        Arrays.sort(expected, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(actual, (a, b) -> Integer.compare(a[0], b[0]));
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEmptyIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = {}; // No intervals
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = {}; // Expected empty output
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testSingleInterval() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 3} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 3} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testNoOverlappingIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 2}, {3, 4}, {5, 6} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 2}, {3, 4}, {5, 6} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testAllIntervalsOverlap() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 5}, {2, 6}, {3, 7} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 7} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testIntervalsTouchingAtBoundaries() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 2}, {2, 3}, {3, 4} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 4} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testIntervalsWithNegativeNumbers() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {-10, -1}, {-5, 0}, {1, 3} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {-10, 0}, {1, 3} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testIntervalsWithSameStartAndEnd() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 1}, {2, 2}, {3, 3} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 1}, {2, 2}, {3, 3} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testNestedIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 10}, {2, 5}, {6, 9} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 10} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testRandomOrderIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {5, 6}, {1, 2}, {3, 4}, {2, 3} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 4}, {5, 6} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testDuplicateIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 3}, {1, 3}, {2, 6}, {8, 10}, {15, 18}, {8, 10} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 6}, {8, 10}, {15, 18} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testMultipleOverlappingIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 4}, {2, 5}, {7, 9}, {3, 6}, {8, 10}, {15, 18} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 6}, {7, 10}, {15, 18} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testSinglePointIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 1}, {2, 2}, {3, 3}, {4, 4} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 1}, {2, 2}, {3, 3}, {4, 4} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testFullyOverlappingIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 10}, {2, 9}, {3, 8}, {4, 7}, {5, 6} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 10} };
        validateMergedIntervals(expected, solver.output);
    }

    /**
     * Additional test case to handle edge scenarios where intervals are identical.
     */
    @Test
    public void testAllIdenticalIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {2, 4}, {2, 4}, {2, 4}, {2, 4} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {2, 4} };
        validateMergedIntervals(expected, solver.output);
    }

    @Test
    public void testMixedOverlappingAndNonOverlappingIntervals() {
        Solver6 solver = new Solver6();
        int[][] intervals = { {1, 3}, {2, 4}, {5, 7}, {6, 8}, {9, 10} };
        solver.intervals = createAdjList(intervals).stream()
                .map(list -> new int[] {list.get(0), list.get(1)})
                .toArray(int[][]::new);
        solver.solve();
        int[][] expected = { {1, 4}, {5, 8}, {9, 10} };
        validateMergedIntervals(expected, solver.output);
    }
}
