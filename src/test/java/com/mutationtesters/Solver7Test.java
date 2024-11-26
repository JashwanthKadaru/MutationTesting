package com.mutationtesters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Solver7Test {

    /**
     * Helper method to validate the expected longest palindrome.
     * Since multiple longest palindromic substrings of the same length can exist,
     * this method checks if the actual result is among the possible expected results.
     *
     * @param expectedPalindromes Array of possible expected palindromic substrings.
     * @param actualPalindrome    The actual palindrome returned by Solver7.
     */
    private void validateLongestPalindrome(String[] expectedPalindromes, String actualPalindrome) {
        for (String expected : expectedPalindromes) {
            if (expected.equals(actualPalindrome)) {
                return; // Test passes if actual is among expected
            }
        }
        // If none match, fail the test
        throw new AssertionError("Expected one of " + String.join(", ", expectedPalindromes)
                + " but got " + actualPalindrome);
    }

    @Test
    public void testEmptyString() {
        Solver7 solver = new Solver7();
        solver.str = "";
        solver.solve();
        assertEquals("", solver.ans);
    }

    @Test
    public void testSingleCharacter() {
        Solver7 solver = new Solver7();
        solver.str = "a";
        solver.solve();
        assertEquals("a", solver.ans);
    }

    @Test
    public void testAllIdenticalCharacters() {
        Solver7 solver = new Solver7();
        solver.str = "aaaaa";
        solver.solve();
        assertEquals("aaaaa", solver.ans);
    }

    @Test
    public void testEvenLengthPalindrome() {
        Solver7 solver = new Solver7();
        solver.str = "abba";
        solver.solve();
        assertEquals("abba", solver.ans);
    }

    @Test
    public void testOddLengthPalindrome() {
        Solver7 solver = new Solver7();
        solver.str = "racecar";
        solver.solve();
        assertEquals("racecar", solver.ans);
    }

    @Test
    public void testPalindromeAtBeginning() {
        Solver7 solver = new Solver7();
        solver.str = "madamxyz";
        solver.solve();
        assertEquals("madam", solver.ans);
    }

    @Test
    public void testPalindromeAtEnd() {
        Solver7 solver = new Solver7();
        solver.str = "xyzmadam";
        solver.solve();
        assertEquals("madam", solver.ans);
    }

    public void testPalindromeInMiddle() {
        Solver7 solver = new Solver7();
        solver.str = "xyzmadamxyz";
        solver.solve();
        assertEquals("madam", solver.ans);
    }

    @Test
    public void testMultiplePalindromicSubstrings() {
        Solver7 solver = new Solver7();
        solver.str = "babad";
        solver.solve();
        // Possible expected palindromes: "bab", "aba"
        String[] expected = { "bab", "aba" };
        validateLongestPalindrome(expected, solver.ans);
    }

    @Test
    public void testMixedCaseCharacters() {
        Solver7 solver = new Solver7();
        solver.str = "AbBa";
        solver.solve();
        // Depending on case sensitivity, "AbBa" might not be a palindrome.
        // Assuming case-sensitive, expected palindrome could be "bB"
        String[] expected = { "A", "b", "B", "a" };
        validateLongestPalindrome(expected, solver.ans);
    }

    @Test
    public void testPalindromeWithSpaces() {
        Solver7 solver = new Solver7();
        solver.str = "nurses run";
        solver.solve();
        // Depending on implementation, spaces may or may not be considered
        // Assuming spaces are treated as characters, the longest palindrome is " run" or similar
        // But "nurses run" is not a palindrome if spaces are considered
        // Thus, expect the first single character
        assertEquals("ses", solver.ans);
    }

    @Test
    public void testPalindromeWithNumbers() {
        Solver7 solver = new Solver7();
        solver.str = "12332145";
        solver.solve();
        assertEquals("123321", solver.ans);
    }

    @Test
    public void testLongPalindrome() {
        Solver7 solver = new Solver7();
        // Construct a long palindrome
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append("a");
        }
        sb.append("b");
        for (int i = 0; i < 500; i++) {
            sb.append("a");
        }
        solver.str = sb.toString();
        solver.solve();
        assertEquals(sb.toString(), solver.ans);
    }

    @Test
    public void testMultipleSameLengthPalindromes() {
        Solver7 solver = new Solver7();
        solver.str = "abaxcdcyaba";
        solver.solve();
        // Possible longest palindromes: "aba", "cdc", "aba"
        String[] expected = { "aba", "cdc"};
        validateLongestPalindrome(expected, solver.ans);
    }
}
