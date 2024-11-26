package com.mutationtesters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit Test Suite for Solver10 - Number of Islands
 */
public class Solver10Test {

    /**
     * Helper method to validate the expected number of islands.
     *
     * @param expected The expected number of islands.
     * @param actual   The actual number of islands returned by Solver10.
     */
    private void validateNumberOfIslands(int expected, int actual) {
        assertEquals("Number of islands does not match the expected value.", expected, actual);
    }

    /**
     * Helper method to convert a 2D array of integers to a 2D array of characters.
     * '1' represents land and '0' represents water.
     *
     * @param gridInt 2D array of integers.
     * @return 2D array of characters.
     */
    private char[][] convertToCharGrid(int[][] gridInt) {
        if (gridInt == null) return null;
        char[][] gridChar = new char[gridInt.length][];
        for (int i = 0; i < gridInt.length; i++) {
            gridChar[i] = new char[gridInt[i].length];
            for (int j = 0; j < gridInt[i].length; j++) {
                gridChar[i][j] = gridInt[i][j] == 1 ? '1' : '0';
            }
        }
        return gridChar;
    }

    @Test
    public void testEmptyGrid() {
        Solver10 solver = new Solver10();
        solver.grid = new char[][]{};
        solver.solve();
        // Expected: 0
        validateNumberOfIslands(0, solver.ans);
    }

    @Test
    public void testSingleCellLand() {
        Solver10 solver = new Solver10();
        solver.grid = new char[][]{{'1'}};
        solver.solve();
        // Expected: 1
        validateNumberOfIslands(1, solver.ans);
    }

    @Test
    public void testSingleCellWater() {
        Solver10 solver = new Solver10();
        solver.grid = new char[][]{{'0'}};
        solver.solve();
        // Expected: 0
        validateNumberOfIslands(0, solver.ans);
    }

    @Test
    public void testAllWaterGrid() {
        Solver10 solver = new Solver10();
        solver.grid = new char[][]{
            {'0', '0'},
            {'0', '0'}
        };
        solver.solve();
        // Expected: 0
        validateNumberOfIslands(0, solver.ans);
    }

    @Test
    public void testAllLandGrid() {
        Solver10 solver = new Solver10();
        solver.grid = new char[][]{
            {'1', '1'},
            {'1', '1'}
        };
        solver.solve();
        // Expected: 1
        validateNumberOfIslands(1, solver.ans);
    }

    @Test
    public void testMultipleSeparateIslands() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 0, 0, 1},
            {0, 0, 0, 0},
            {1, 0, 0, 1}
        });
        solver.solve();
        // Expected: 4
        validateNumberOfIslands(4, solver.ans);
    }

    @Test
    public void testSingleLargeIsland() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        });
        solver.solve();
        // Expected: 1
        validateNumberOfIslands(1, solver.ans);
    }

    @Test
    public void testIslandsWithComplexShapes() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0}
        });
        solver.solve();
        // Expected: 3
        validateNumberOfIslands(3, solver.ans);
    }

    @Test
    public void testIslandsConnectedDiagonally() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 0},
            {0, 1}
        });
        solver.solve();
        // Expected: 2 (Diagonal connections are not considered)
        validateNumberOfIslands(2, solver.ans);
    }

    @Test
    public void testGridWithHoles() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        });
        solver.solve();
        // Expected: 1
        validateNumberOfIslands(1, solver.ans);
    }

    @Test
    public void testGridWithOneRow() {
        Solver10 solver = new Solver10();
        solver.grid = new char[][]{
            {'1', '0', '1', '1', '0', '1'}
        };
        solver.solve();
        // Expected: 3
        validateNumberOfIslands(3, solver.ans);
    }

    @Test
    public void testGridWithOneColumn() {
        Solver10 solver = new Solver10();
        solver.grid = new char[][]{
            {'1'},
            {'0'},
            {'1'},
            {'1'},
            {'0'},
            {'1'}
        };
        solver.solve();
        // Expected: 3
        validateNumberOfIslands(3, solver.ans);
    }

    @Test
    public void testLargeGridWithMixedIslands() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 1, 0, 0, 0, 1, 1},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0, 1, 1},
            {0, 1, 0, 0, 1, 0, 0},
            {1, 0, 1, 1, 0, 1, 1}
        });
        solver.solve();
        // Expected: 8
        validateNumberOfIslands(8, solver.ans);
    }

    @Test
    public void testGridWithNestedIslands() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        });
        solver.solve();
        // Expected: 2 (Single outer island with a "lake" in the center)
        validateNumberOfIslands(2, solver.ans);
    }

    @Test
    public void testGridWithAlternatingLandAndWater() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1}
        });
        solver.solve();
        // Expected: 8
        validateNumberOfIslands(8, solver.ans);
    }

    @Test
    public void testGridUp() {
        Solver10 solver = new Solver10();
        solver.grid = convertToCharGrid(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 0}
        });
        solver.solve();
        // Expected: 1
        validateNumberOfIslands(1, solver.ans);
    }
}
