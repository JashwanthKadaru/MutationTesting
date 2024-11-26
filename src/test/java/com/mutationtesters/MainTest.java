package com.mutationtesters;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainTest {
    String[] Initialprompts = {
      "@******************************************************************************************************",
      "************************************** Choose a problem to solve **************************************",
      "******************************************************************************************************@",
      "Enter problem number followed by single space, followed by 1 for description and 2 for Entering Input.",
      "1) Solver1",
      "2) Solver2",
      "3) Solver3",
      "4) Solver4",
      "5) Solver5",
      "6) Solver6",
      "7) Solver7",
      "8) Solver8",
      "9) Solver9",
      "10) Solver10",
      "\nEnter a Choice: \n"
    };

    String[] solverPrompts = {
      "\nInputs (Only 1 Test Case): "
    };

    String[] errorPrompts = {
       "Invalid Input for problem Choice: ",
       "Invalid Input sub choice: "
    };

    @Test
    public void testMainInvocation() {
      String input = "20 2\n";
        
      ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
     
      System.setIn(inContent);
      
      @SuppressWarnings("unused")
      Main runner = new Main();
      Main.main(new String[]{});

      System.setIn(System.in);
      return;
    }

    @Test
    public void testScannerClosure() {
        // First invocation
        String input1 = "99 1\n"; // Invalid problem choice to trigger sc.close()
        ByteArrayInputStream inContent1 = new ByteArrayInputStream(input1.getBytes());
        System.setIn(inContent1);

        Main.main(new String[]{});

        // Second invocation
        String input2 = "1 1\n"; // Valid input to see if Scanner works again
        ByteArrayInputStream inContent2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(inContent2);

        // Capture output if needed
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // This should throw an exception if sc.close() was omitted
        Main.main(new String[]{});

        // Reset System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }


    private void testInvalidInput(int problemChoice, int subChoice) {
        // Simulate input: "11 1"
        String input = problemChoice + " " + subChoice + "\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Assert that output contains Initial prompts
        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }
        
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
        };

        // Assert that output does NOT contain the problem-description-marker strings.
        if(problemChoice>10 || problemChoice<1 || subChoice!=1) {
          for(String prompt: problemDescriptionStrings) {
            assertFalse(output.contains(prompt));
          }
        }

        // Assert that output does NOT contain the problem-description-marker strings.
        if(problemChoice>10 || problemChoice<1 || subChoice!=2) {
          for(String prompt: solverPrompts) {
            assertFalse(output.contains(prompt));
          }
        }

        if(problemChoice>10 || problemChoice<1) {
          // Assert that output contains the error message from Main.
          String validErrorOutput = "Invalid Input for problem Choice: " + problemChoice;
          assertTrue(output.contains(validErrorOutput));

          // Assert that last line of output is the error message from Main.
          String[] lines = output.trim().split("\\r?\\n");
          String lastLine = lines[lines.length - 1];
          assertEquals(validErrorOutput, lastLine);
        }

        if(problemChoice<=10 && problemChoice>=1 && (subChoice>2 || subChoice<1)) {
          // Assert that output contains the error message from Main.
          String validErrorOutput = "Invalid Input sub choice: " + subChoice;
          assertTrue(output.contains(validErrorOutput));

          // Assert that last line of output is the error message from Main.
          String[] lines = output.trim().split("\\r?\\n");
          String lastLine = lines[lines.length - 1];
          assertEquals(validErrorOutput, lastLine);
        }

        if(problemChoice<=10 && problemChoice>=1 && subChoice==1) {
          for(String prompt: problemDescriptionStrings) {
            assertTrue(output.contains(prompt));
          }
        }else if(problemChoice<=10 && problemChoice>=1 && subChoice==2) {
          for(String prompt: solverPrompts) {
            assertTrue(output.contains(prompt));
          }
        }
    }
    @Test
    public void testInvalidInput() {
      testInvalidInput(12, 0);
      testInvalidInput(12, 1);
      testInvalidInput(12, 2);
      testInvalidInput(12, 3);
      testInvalidInput(0, 0);
      testInvalidInput(0, 1);
      testInvalidInput(0, 2);
      testInvalidInput(0, 3);
      testInvalidInput(1, 0);
      testInvalidInput(1, 3);
      testInvalidInput(10, 0);
      testInvalidInput(10, 3);
    }

    @Test 
    public void testSolverInterface() {
      Solver solver = new Solver1();
      String actual = solver.about();
      assertEquals("Instance of a solver extending Solver interface", actual);
      return;
    }

    @Test
    public void testSolverUsingSolver1() throws Exception {
        // Simulate input: "1 2" followed by Solver1 inputs
        String input = "";
        input += "5\n2 1 2\n0\n0\n1 4\n1 2\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Solver solver = new Solver1();
        Scanner sc = new Scanner(System.in);

        solver.printProblemStatement();
        solver.printInputFormat();
        solver.printOutputFormat();
        solver.takeInput(sc);
        solver.solve();
        solver.printOutput();

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "Given an adjacency list for a Directed Acyclic Graph (DAG) where adj[u] contains a list of all vertices v such that there exists a directed edge u -> v. Return topological sort for the given graph. Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u -> v, vertex u comes before v in the ordering. Note: As there are multiple Topological orders possible, you may return any of them. If your returned Topological sort is correct then the output will be 1 else 0. It is guaranteed in the testcases that there exists a valid topological order.",
          "Nodes are numbered from 0 to V-1. The input consists of the number of vertices V and the Adjacency List. The first line of the input contains an integer V, V vertices. Followed by V lines, each containing an integer ai, followed by ai number of integers in the same line. All integers in a line are separated by spaces.\n\nExample:5\n2 1 2\n0\n0\n1 4\n1 2\n",
          "The output should be printed in a single line, as `V` space separated integers.\n\nExample: 0 1 3 4 2\n"
        };
        
        for(String prompt: Initialprompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
            try{
              assertTrue(output.contains(prompt));
            } catch(Error e) {
              throw new Error(output);
            }
        } 

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of topo sort)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1];
        String[] topoOrder = lastLine.trim().split("\\s+");

        // Check that we have 5 nodes in the output
        assertEquals(5, topoOrder.length);

        // Optional: Verify the topological order is valid
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(1, 2))); // Node 0
        adj.add(new ArrayList<>());                   // Node 1
        adj.add(new ArrayList<>());                   // Node 2
        adj.add(new ArrayList<>(Arrays.asList(4)));    // Node 3
        adj.add(new ArrayList<>(Arrays.asList(2)));    // Node 4

        // Map node to its position in topo sort
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < topoOrder.length; i++) {
            position.put(Integer.parseInt(topoOrder[i]), i);
        }

        // Validate topological order
        boolean isValid = true;
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                if (position.get(u) >= position.get(v)) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) break;
        }

        assertTrue("Topological order is invalid", isValid);
    }

    @Test
    public void testSolver1Description() {
        // Simulate input: "1 1"
        String input = "1 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an adjacency list for a Directed Acyclic Graph (DAG) where adj[u] contains a list of all vertices v such that there exists a directed edge u -> v. Return topological sort for the given graph. Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u -> v, vertex u comes before v in the ordering. Note: As there are multiple Topological orders possible, you may return any of them. If your returned Topological sort is correct then the output will be 1 else 0. It is guaranteed in the testcases that there exists a valid topological order.",
          "Nodes are numbered from 0 to V-1. The input consists of the number of vertices V and the Adjacency List. The first line of the input contains an integer V, V vertices. Followed by V lines, each containing an integer ai, followed by ai number of integers in the same line. All integers in a line are separated by spaces.\n\nExample:5\n2 1 2\n0\n0\n1 4\n1 2\n",
          "The output should be printed in a single line, as `V` space separated integers.\n\nExample: 0 1 3 4 2\n"
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver1Execution() {
        // Simulate input: "1 2" followed by Solver1 inputs
        String input = "1 2\n";
        input += "5\n2 1 2\n0\n0\n1 4\n1 2\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an adjacency list for a Directed Acyclic Graph (DAG) where adj[u] contains a list of all vertices v such that there exists a directed edge u -> v. Return topological sort for the given graph. Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u -> v, vertex u comes before v in the ordering. Note: As there are multiple Topological orders possible, you may return any of them. If your returned Topological sort is correct then the output will be 1 else 0. It is guaranteed in the testcases that there exists a valid topological order.",
          "Nodes are numbered from 0 to V-1. The input consists of the number of vertices V and the Adjacency List. The first line of the input contains an integer V, V vertices. Followed by V lines, each containing an integer ai, followed by ai number of integers in the same line. All integers in a line are separated by spaces.\n\nExample:5\n2 1 2\n0\n0\n1 4\n1 2\n",
          "The output should be printed in a single line, as `V` space separated integers.\n\nExample: 0 1 3 4 2\n"
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of topo sort)
        String[] lines = output.split("\\r?\\n");
        String lastLine = lines[lines.length - 1];
        String[] topoOrder = lastLine.trim().split("\\s+");

        // Check that we have 5 nodes in the output
        assertEquals(5, topoOrder.length);
        assertEquals("0 3 1 4 2", lastLine);

        // Verify the topological order is valid
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(1, 2))); // Node 0
        adj.add(new ArrayList<>());                   // Node 1
        adj.add(new ArrayList<>());                   // Node 2
        adj.add(new ArrayList<>(Arrays.asList(4)));    // Node 3
        adj.add(new ArrayList<>(Arrays.asList(2)));    // Node 4

        // Map node to its position in topo sort
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < topoOrder.length; i++) {
            position.put(Integer.parseInt(topoOrder[i]), i);
        }

        // Validate topological order
        boolean isValid = true;
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                if (position.get(u) >= position.get(v)) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) break;
        }

        assertTrue("Topological order is invalid", isValid);
    }

    @Test 
    public void testSolver1_chkAdjSize() {
      String input = "5\n2 1 2\n0\n0\n1 4\n1 2\n";
      
      ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();

      System.setIn(inContent);
      System.setOut(new PrintStream(outContent));

      Solver1 solver = new Solver1();
      Scanner sc = new Scanner(System.in); 
      solver.takeInput(sc);
      assertEquals(5, solver.adj.size());

      System.setIn(System.in);
      System.setOut(System.out);
    }

    @Test
    public void testSolver2Description() {
        // Simulate input: "2 1"
        String input = "2 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order.",
          "Input consists of 2 lines. The first line consists of 2 integers `n` and `target`. The second line consists of n space separated integers.\n\nExample: 4 9\n2 7 11 15\n",
          "Output consists of 2 space separated integers (indices of the elements from the array whose sum is `target`).\n\nExample: 0 1\n"
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver2Execution() {
        // Simulate input: "2 2" followed by Solver2 inputs
        String input = "2 2\n";
        input += "4 9\n2 7 11 15\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

         String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order.",
          "Input consists of 2 lines. The first line consists of 2 integers `n` and `target`. The second line consists of n space separated integers.\n\nExample: 4 9\n2 7 11 15\n",
          "Output consists of 2 space separated integers (indices of the elements from the array whose sum is `target`).\n\nExample: 0 1\n"
        };

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();

        // Expected output is "0 1"
        assertEquals("0 1", lastLine);
    }

    @Test
    public void testSolver3Description() {
        // Simulate input: "3 1"
        String input = "3 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a string s, find the length of the longest `substring` without repeating characters.",
          "The one and only line of input contains the string itself.\n\nExample: \"abcabcbb\"\n",
          "The one and only line of output contains an integer, the length of the longest substring that meets the criteria.\n\nExample: 3\n"
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver3Execution() {
        // Simulate input: "3 2" followed by Solver3 inputs
        String input = "3 2\n";
        input += "abcabcbb\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a string s, find the length of the longest `substring` without repeating characters.",
          "The one and only line of input contains the string itself.\n\nExample: \"abcabcbb\"\n",
          "The one and only line of output contains an integer, the length of the longest substring that meets the criteria.\n\nExample: 3\n"
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();

        // Expected output is "3"
        assertEquals("3", lastLine);
    }

    @Test
    public void testSolver4Description() {
        // Simulate input: "4 1"
        String input = "4 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.",
          "The first line consists of 2 integers n and m. The second line consists of n space-separated integers. The third line consists of m space-separated integers.",
          "The output consists of a single decimal floating-point value which is the median of 2 arrays.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver4Execution() {
        // Simulate input: "4 2" followed by Solver4 inputs
        String input = "4 2\n";
        input += "6 7\n1 3 4 5 9 11\n2 6 7 8 12 14 16\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

         // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.",
          "The first line consists of 2 integers n and m. The second line consists of n space-separated integers. The third line consists of m space-separated integers.",
          "The output consists of a single decimal floating-point value which is the median of 2 arrays.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();
        double actualAns = Double.parseDouble(lastLine);
        double expectedAns = 7.0;

        // Expected output is "7.0"
        assertEquals(expectedAns, actualAns, 0.00001);
    }

    @Test
    public void testSolver5Description() {
        // Simulate input: "5 1"
        String input = "5 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. An input string is valid if: 1) Open brackets must be closed by the same type of brackets. 2) Open brackets must be closed in the correct order. 3) Every close bracket has a corresponding open bracket of the same type.",
          "Input consists of a string s containing just the characters '(', ')', '{', '}', '[' and ']'.\n\nExample: \"()\"\n",
          "If input string is valid print \"Valid\", otherwise \"Invalid\".",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver5Execution_Balanced() {
        // Simulate input: "5 2" followed by Solver5 inputs
        String input = "5 2\n";
        input += "(){}{[(()())]}()\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. An input string is valid if: 1) Open brackets must be closed by the same type of brackets. 2) Open brackets must be closed in the correct order. 3) Every close bracket has a corresponding open bracket of the same type.",
          "Input consists of a string s containing just the characters '(', ')', '{', '}', '[' and ']'.\n\nExample: \"()\"\n",
          "If input string is valid print \"Valid\", otherwise \"Invalid\".",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();

        // Expected output is "Valid"
        assertEquals("Valid", lastLine);
    }

    @Test
    public void testSolver5Execution_Unbalanced() {
        // Simulate input: "5 2" followed by Solver5 inputs
        String input = "5 2\n";
        input += "(){}{[(()())]\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. An input string is valid if: 1) Open brackets must be closed by the same type of brackets. 2) Open brackets must be closed in the correct order. 3) Every close bracket has a corresponding open bracket of the same type.",
          "Input consists of a string s containing just the characters '(', ')', '{', '}', '[' and ']'.\n\nExample: \"()\"\n",
          "If input string is valid print \"Valid\", otherwise \"Invalid\".",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();

        // Expected output is "Valid"
        assertEquals("Invalid", lastLine);
    }

    @Test
    public void testSolver6Description() {
        // Simulate input: "6 1"
        String input = "6 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);  // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.",
          "The first line of Input consists of `n` number of intervals. Next, `n` lines follow:Each line consists of 2 space-separated integers, firsti and lasti.",
          "The first line of Output consists of `n1` number of intervals. Next, `n1` lines follow: Each line consists of 2 space-separated integers, firsti and lasti. The output must match the list of intervals after merging overlapping intervals.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver6Execution() {
        // Simulate input: "6 2" followed by Solver6 inputs
        String input = "6 2\n";
        input += "4\n1 3\n2 6\n8 10\n15 18\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);  // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.",
          "The first line of Input consists of `n` number of intervals. Next, `n` lines follow:Each line consists of 2 space-separated integers, firsti and lasti.",
          "The first line of Output consists of `n1` number of intervals. Next, `n1` lines follow: Each line consists of 2 space-separated integers, firsti and lasti. The output must match the list of intervals after merging overlapping intervals.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last few lines (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        int n = lines.length;

        // The expected output is:
        // 3
        // 1 6
        // 8 10
        // 15 18

        // Get the output lines
        String numIntervalsLine = lines[n - 4];
        String interval1Line = lines[n - 3];
        String interval2Line = lines[n - 2];
        String interval3Line = lines[n - 1];

        // Check number of intervals
        assertEquals("3", numIntervalsLine.trim());

        // Check intervals
        assertEquals("1 6", interval1Line.trim());
        assertEquals("8 10", interval2Line.trim());
        assertEquals("15 18", interval3Line.trim());
    }

    @Test
    public void testSolver7Description() {
        // Simulate input: "7 1"
        String input = "7 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a string s, return the longest palindromic substring in s.",
          "The input consists of a string s.",
          "Output the longest palindromic substring in input string s.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver7Execution() {
        // Simulate input: "7 2" followed by Solver7 inputs
        String input = "7 2\n";
        input += "babad\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a string s, return the longest palindromic substring in s.",
          "The input consists of a string s.",
          "Output the longest palindromic substring in input string s.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();

        // Expected output is "bab" or "aba"
        assertTrue(lastLine.equals("bab") || lastLine.equals("aba"));
    }

    @Test
    public void testSolver8Description() {
      // Simulate input: "7 1"
        String input = "8 1\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given the root of a binary tree, return the maximum path sum of any non-empty path. A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.",
          "Provide the binary tree in level-order traversal as a single line of space-separated values. " +
                "Use 'null' (without quotes) to represent missing nodes.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Represents the following binary tree:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "     / \\\n" +
                "    4   5",
          "Output a single integer representing the maximum path sum of the binary tree.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Output: 12\n" +
                "Explanation: The maximum path sum is 4 + 3 + 5 = 12.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver8Execution() {
      // Simulate input: "7 1"
        String input = "8 2\n";
        input+="1 2 3 null null 4 5\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given the root of a binary tree, return the maximum path sum of any non-empty path. A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.",
          "Provide the binary tree in level-order traversal as a single line of space-separated values. " +
                "Use 'null' (without quotes) to represent missing nodes.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Represents the following binary tree:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "     / \\\n" +
                "    4   5",
          "Output a single integer representing the maximum path sum of the binary tree.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Output: 12\n" +
                "Explanation: The maximum path sum is 4 + 3 + 5 = 12.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();
        int actual = Integer.parseInt(lastLine);
        // Expected output is "5"
        assertEquals(12, actual);
    }

    @Test
    public void testSolver8Execution2() {
      // Simulate input: "7 1"
        String input = "8 2\n";
        input+="10 2 10 null null 20 1 null null 25 30\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given the root of a binary tree, return the maximum path sum of any non-empty path. A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.",
          "Provide the binary tree in level-order traversal as a single line of space-separated values. " +
                "Use 'null' (without quotes) to represent missing nodes.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Represents the following binary tree:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "     / \\\n" +
                "    4   5",
          "Output a single integer representing the maximum path sum of the binary tree.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Output: 12\n" +
                "Explanation: The maximum path sum is 4 + 3 + 5 = 12.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();
        int actual = Integer.parseInt(lastLine);
        // Expected output is "5"
        assertEquals(61, actual);
    }

    @Test
    public void testSolver8Execution3() {
      // Simulate input: "7 1"
        String input = "8 2\n";
        input+="1 2\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given the root of a binary tree, return the maximum path sum of any non-empty path. A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.",
          "Provide the binary tree in level-order traversal as a single line of space-separated values. " +
                "Use 'null' (without quotes) to represent missing nodes.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Represents the following binary tree:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "     / \\\n" +
                "    4   5",
          "Output a single integer representing the maximum path sum of the binary tree.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Output: 12\n" +
                "Explanation: The maximum path sum is 4 + 3 + 5 = 12.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();
        int actual = Integer.parseInt(lastLine);
        // Expected output is "5"
        assertEquals(3, actual);
    }

    @Test
    public void testSolver8ExecutionEmptyInput() {
      // Simulate input: "7 1"
        String input = "8 2\n";
        input+="\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given the root of a binary tree, return the maximum path sum of any non-empty path. A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.",
          "Provide the binary tree in level-order traversal as a single line of space-separated values. " +
                "Use 'null' (without quotes) to represent missing nodes.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Represents the following binary tree:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "     / \\\n" +
                "    4   5",
          "Output a single integer representing the maximum path sum of the binary tree.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Output: 12\n" +
                "Explanation: The maximum path sum is 4 + 3 + 5 = 12.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();
        int actual = Integer.parseInt(lastLine);
        // Expected output is "5"
        assertEquals(Integer.MIN_VALUE, actual);
    }

    @Test
    public void testSolver8ExecutionNullInput() {
      // Simulate input: "7 1"
        String input = "8 2\n";
        input+="null\n";
        
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given the root of a binary tree, return the maximum path sum of any non-empty path. A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.",
          "Provide the binary tree in level-order traversal as a single line of space-separated values. " +
                "Use 'null' (without quotes) to represent missing nodes.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Represents the following binary tree:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "     / \\\n" +
                "    4   5",
          "Output a single integer representing the maximum path sum of the binary tree.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Output: 12\n" +
                "Explanation: The maximum path sum is 4 + 3 + 5 = 12.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();
        int actual = Integer.parseInt(lastLine);
        // Expected output is "5"
        assertEquals(Integer.MIN_VALUE, actual);
    }

    @Test
    public void testSolver9Description() {
        // Simulate input: "9 1"
        String input = "9 1\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that: Every adjacent pair of words differs by a single letter. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList. `sk == endWord`. Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.",
          "The input consists of 3+n lines. Each line contains the following: beginWord, endWord, an integer (length of words list) and a new line for each word in words list.\n\nExample:\"hit\"\n\"cog\"\n6\n\"hot\"\n\"dot\"\n\"dog\"\n\"lot\"\n\"log\"\n\"cog\"",
          "The output concists of an integer. The length of the shortest transformation sequence.\n\nExample: 5",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver9Execution() {
        // Simulate input: "9 2" followed by Solver9 inputs
        String input = "9 2\n";
        input += "hit\n";
        input += "cog\n";
        input += "6\n";
        input += "hot\n";
        input += "dot\n";
        input += "dog\n";
        input += "lot\n";
        input += "log\n";
        input += "cog\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);   // Reset System.in
        System.setOut(System.out); // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that: Every adjacent pair of words differs by a single letter. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList. `sk == endWord`. Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.",
          "The input consists of 3+n lines. Each line contains the following: beginWord, endWord, an integer (length of words list) and a new line for each word in words list.\n\nExample:\"hit\"\n\"cog\"\n6\n\"hot\"\n\"dot\"\n\"dog\"\n\"lot\"\n\"log\"\n\"cog\"",
          "The output concists of an integer. The length of the shortest transformation sequence.\n\nExample: 5",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();

        // Expected output is "5"
        assertEquals("5", lastLine);
    }

    @Test
    public void testSolver10Description() {
        // Simulate input: "10 1"
        String input = "10 1\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);    // Reset System.in
        System.setOut(System.out);  // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.",
          "The first line of Input consists of 2 integers n, m. Then n lines follow each with m characters ('1'/'0').\n\nExample:4 5\n11110\n11010\n11000\n00000",
         "The output consists of single integer, number of Islands.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver10Execution() {
        // Simulate input: "10 2" followed by Solver10 inputs
        String input = "10 2\n";
        input += "4 5\n";
        input += "11110\n";
        input += "11010\n";
        input += "11000\n";
        input += "00000\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);    // Reset System.in
        System.setOut(System.out);  // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.",
          "The first line of Input consists of 2 integers n, m. Then n lines follow each with m characters ('1'/'0').\n\nExample:4 5\n11110\n11010\n11000\n00000",
         "The output consists of single integer, number of Islands.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.trim().split("\\r?\\n");
        String lastLine = lines[lines.length - 1].trim();

        // Expected output is "1"
        assertEquals("1", lastLine);
    }

        @Test
    public void testSolver11Description() {
        // Simulate input: "10 1"
        String input = "11 1\n";

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);    // Reset System.in
        System.setOut(System.out);  // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is an edge between i and j, second integers corresponds to the weight of that edge. You are given the source vertex S and You have to Find the shortest distance of all the vertex from the source vertex S. You have to return a list of integers denoting the shortest distance between each node and Source vertex S. Note: The Graph doesnâ€™t contain any negative weight cycle",
          "Nodes are numbered from 0 to V-1. The input consists of the number of vertices V, start node and the Edge List. The first line of the input contains an integer V (V vertices) and start node id S. Second line consists of E, number of edges. E lines follow, each containing 3 integers separated by single space (node1, node2, weight).\n\nExample:3 2\n3\n0 1 1\n0 2 6\n1 2 3\n",
          "The output consists of V integers, ith integer is equal to length of shortest path from S to node i.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          try{
            assertTrue(output.contains(prompt));
          } catch(Error e) {
            throw new Error("prompt: \n" + prompt + "\noutput: \n" + output);
          }
        }

        for(String prompt: solverPrompts) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }
    }

    @Test
    public void testSolver11Execution() {
        // Simulate input: "10 2" followed by Solver10 inputs
        String input = "11 2\n";
        input += "3 2\n";
        input += "3\n";
        input += "0 1 1\n"; 
        input += "0 2 6\n"; 
        input += "1 2 3\n"; 

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        System.setIn(System.in);    // Reset System.in
        System.setOut(System.out);  // Reset System.out

        String output = outContent.toString();

        // Check for key outputs
        String[] problemDescriptionStrings = {
          "\n*****************PROBLEM STATEMENT***************",
          "\n*****************INPUT FORMAT***************",
          "\n*****************OUTPUT FORMAT***************",
          "Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is an edge between i and j, second integers corresponds to the weight of that edge. You are given the source vertex S and You have to Find the shortest distance of all the vertex from the source vertex S. You have to return a list of integers denoting the shortest distance between each node and Source vertex S. Note: The Graph doesn’t contain any negative weight cycle",
          "Nodes are numbered from 0 to V-1. The input consists of the number of vertices V, start node and the Edge List. The first line of the input contains an integer V (V vertices) and start node id S. Second line consists of E, number of edges. E lines follow, each containing 3 integers separated by single space (node1, node2, weight).\n\nExample:3 2\n3\n0 1 1\n0 2 6\n1 2 3\n",
          "The output consists of V integers, ith integer is equal to length of shortest path from S to node i.",
        };

        for(String prompt: Initialprompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: problemDescriptionStrings) {
          assertFalse(output.contains(prompt));
        }

        for(String prompt: solverPrompts) {
          assertTrue(output.contains(prompt));
        }

        for(String prompt: errorPrompts) {
          assertFalse(output.contains(prompt));
        }

        // Extract the last line (output of the solver)
        String[] lines = output.split("\\r?\\n");
        String lastLine = lines[lines.length - 1];

        // Expected output is "1"
        assertEquals("4 3 0", lastLine);
    }
}


