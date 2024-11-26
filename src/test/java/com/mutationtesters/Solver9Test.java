package com.mutationtesters;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit Test Suite for Solver9 - Word Ladder
 */
public class Solver9Test {

    /**
     * Helper method to validate the expected ladder length.
     *
     * @param expected The expected ladder length.
     * @param actual   The actual ladder length returned by Solver9.
     */
    private void validateLadderLength(int expected, int actual) {
        assertEquals("Ladder length does not match the expected value.", expected, actual);
    }

    @Test
    public void testBasicTransformation() {
        Solver9 solver = new Solver9();
        solver.beginWord = "hit";
        solver.endWord = "cog";
        solver.wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        solver.solve();
        // Expected transformation: "hit" -> "hot" -> "dot" -> "dog" -> "cog" (5 words)
        validateLadderLength(5, solver.shortestTransformationLength);
    }

    @Test
    public void testEndWordDoesNotExist() {
        Solver9 solver = new Solver9();
        solver.beginWord = "hit";
        solver.endWord = "cog";
        solver.wordList = Arrays.asList("hot", "dot", "dog", "lot", "log");
        solver.solve();
        // No transformation possible since "cog" is not in the word list
        validateLadderLength(0, solver.shortestTransformationLength);
    }

    @Test
    public void testEndWordCannotBeReached() {
        Solver9 solver = new Solver9();
        solver.beginWord = "hit";
        solver.endWord = "cog";
        solver.wordList = Arrays.asList("hot", "dot", "cog", "lot", "hig");
        solver.solve();
        // No transformation possible since "cog" is not in the word list
        validateLadderLength(0, solver.shortestTransformationLength);
    }

    @Test
    public void testBeginWordEqualsEndWord() {
        Solver9 solver = new Solver9();
        solver.beginWord = "hit";
        solver.endWord = "hit";
        solver.wordList = Arrays.asList("hit", "hot", "dot", "dog", "lot", "log", "cog");
        solver.solve();
        // Transformation sequence length is 1 since beginWord equals endWord
        validateLadderLength(1, solver.shortestTransformationLength);
    }

    @Test
    public void testMultiplePossibleTransformations() {
        Solver9 solver = new Solver9();
        solver.beginWord = "hit";
        solver.endWord = "cog";
        solver.wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog", "hig", "lig");
        solver.solve();
        // Multiple paths exist; the shortest is still length 5
        validateLadderLength(5, solver.shortestTransformationLength);
    }


    @Test
    public void testWordListContainsDuplicates() {
        Solver9 solver = new Solver9();
        solver.beginWord = "aaa";
        solver.endWord = "bbb";
        solver.wordList = Arrays.asList("aab", "aab", "abb", "abb", "bbb", "bbb");
        solver.solve();
        // Despite duplicates, the shortest transformation is still length 3
        validateLadderLength(4, solver.shortestTransformationLength);
    }

    @Test
    public void testTransformationWithMinimumLengthWords() {
        Solver9 solver = new Solver9();
        solver.beginWord = "a";
        solver.endWord = "c";
        solver.wordList = Arrays.asList("a", "b", "c");
        solver.solve();
        // Transformation: "a" -> "c" (2 words)
        validateLadderLength(2, solver.shortestTransformationLength);
    }

    @Test
    public void testTransformationWithNonAdjacentLetterChanges() {
        Solver9 solver = new Solver9();
        solver.beginWord = "lead";
        solver.endWord = "gold";
        solver.wordList = Arrays.asList("load", "goad", "gold");
        solver.solve();
        // Expected transformation: "lead" -> "load" -> "goad" -> "gold" (4 words)
        validateLadderLength(4, solver.shortestTransformationLength);
    }

    @Test
    public void testTransformationWithAllPossibleSingleLetterChanges() {
        Solver9 solver = new Solver9();
        solver.beginWord = "aaaa";
        solver.endWord = "cccc";
        solver.wordList = Arrays.asList("aaab", "aabb", "abbb", "bbbb", "bbbc", "bbcc", "bccc", "cccc");
        solver.solve();
        validateLadderLength(9, solver.shortestTransformationLength);
    }

    @Test
    public void testTransformationWithZ() {
        Solver9 solver = new Solver9();
        solver.beginWord = "aaaa";
        solver.endWord = "aaaz";
        solver.wordList = Arrays.asList("aaaz");
        solver.solve();
        validateLadderLength(2, solver.shortestTransformationLength);
    }
}
