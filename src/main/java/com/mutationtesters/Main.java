package com.mutationtesters;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        boolean flag=false;
        int problemChoice = -1;
        int subChoice = -1;
        Scanner sc = new Scanner(System.in);

        // Change Names of Solvers here to something more descriptive and short 1 liner !!!
        String[] problemNames = {
            "Solver1",
            "Solver2",
            "Solver3",
            "Solver4",
            "Solver5",
            "Solver6",
            "Solver7",
            "Solver8",
            "Solver9",
            "Solver10",
            "Solver11",
        };

        System.out.println("@******************************************************************************************************");
        System.out.println("************************************** Choose a problem to solve **************************************");
        System.out.println("******************************************************************************************************@");
        System.out.println("Enter problem number followed by single space, followed by 1 for description and 2 for Entering Input.");
        for(int i=0; i<problemNames.length; i++) {
             System.out.println((i+1) + ") " + problemNames[i]);
        }

        System.out.println("\nEnter a Choice: \n");
        problemChoice = sc.nextInt();
        subChoice = sc.nextInt();

        Solver solverInstance=null;
        switch(problemChoice) {
            case 1: {
                solverInstance = new Solver1();
                break;
            }
            case 2: {
                solverInstance = new Solver2();
                break;
            }
            case 3: {
                solverInstance = new Solver3();
                break;
            }
            case 4: {
                solverInstance = new Solver4();
                break;
            }
            case 5: {
                solverInstance = new Solver5();
                break;
            }
            case 6: {
                solverInstance = new Solver6();
                break;
            }
            case 7: {
                solverInstance = new Solver7();
                break;
            }
            case 8: {
                solverInstance = new Solver8();
                break;
            }
            case 9: {
                solverInstance = new Solver9();
                break;
            }
            case 10: {
                solverInstance = new Solver10();
                break;
            }
            case 11: {
                solverInstance = new Solver11();
                break;
            }
            default: {
                System.out.println("Invalid Input for problem Choice: " + problemChoice);
                // System.exit(0);
                flag=true;
                break;
            }
        }

        if(flag) { sc.close(); return ;}
        if(subChoice!=1 && subChoice!=2) {
            System.out.println("Invalid Input sub choice: " + subChoice);
            sc.close(); 
            return ;
        }

        if(solverInstance!=null) {
            if(subChoice==1) {
                System.out.println("\n*****************PROBLEM STATEMENT***************");
                solverInstance.printProblemStatement();
                System.out.println("\n*****************INPUT FORMAT***************");
                solverInstance.printInputFormat();
                System.out.println("\n*****************OUTPUT FORMAT***************");
                solverInstance.printOutputFormat();
            } else if(subChoice==2) {
                System.out.println("\nInputs (Only 1 Test Case): ");
                solverInstance.takeInput(sc);
                solverInstance.solve();
                solverInstance.printOutput();
            }
        }
        
        sc.close();
        return;
    }
}

interface Solver {
    public default String about() {
        return "Instance of a solver extending Solver interface";
    }
    public void solve();
    public void printProblemStatement();
    public void printInputFormat();
    public void printOutputFormat();
    public void takeInput(Scanner sc);
    public void printOutput();
}


// Topo Sort of DAG using Kahn's Algorithm. 
// Link: https://www.geeksforgeeks.org/problems/topological-sort/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=topological-sort
class Solver1 implements Solver {
    int V; 
    ArrayList<ArrayList<Integer>> adj;
    int[] topo;

    @Override
    public void solve() {   
        int V = this.V;
        ArrayList<ArrayList<Integer>> adj = this.adj;
        int indegree[] = new int[V];
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();
        ;
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int topo[] = new int[V];
        int i = 0;
        while (!q.isEmpty()) {
            int node = q.peek();
            q.remove();
            topo[i++] = node;
            // node is in your topo sort
            // so please remove it from the indegree

            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }
        this.topo = topo;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given an adjacency list for a Directed Acyclic Graph (DAG) where adj[u] contains a list of all vertices v such that there exists a directed edge u -> v. Return topological sort for the given graph. Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u -> v, vertex u comes before v in the ordering. Note: As there are multiple Topological orders possible, you may return any of them. If your returned Topological sort is correct then the output will be 1 else 0. It is guaranteed in the testcases that there exists a valid topological order.");
        return ;
    }

    @Override
    public void printInputFormat() {
        System.out.println("Nodes are numbered from 0 to V-1. The input consists of the number of vertices V and the Adjacency List. The first line of the input contains an integer V, V vertices. Followed by V lines, each containing an integer ai, followed by ai number of integers in the same line. All integers in a line are separated by spaces.\n\nExample:5\n2 1 2\n0\n0\n1 4\n1 2\n");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("The output should be printed in a single line, as `V` space separated integers.\n\nExample: 0 1 3 4 2\n");
    }

    @Override
    public void takeInput(Scanner sc) {
        int V;
        V = sc.nextInt();
        this.V = V;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if(i<0) break;
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            int ai  = sc.nextInt();
            while(ai>0) {
                int node = sc.nextInt();
                adj.get(i).add(node);
                ai--;
            }
        }
        this.adj = adj;
        return;
    }

    @Override
    public void printOutput() {
        for(int i=0; i<this.V; i++) {
            if(i!=this.V-1) System.out.print(this.topo[i]+" ");
            else System.out.print(this.topo[i]);
        }
        return ;
    }
}


// Two sum problem arrays. Find indices of 2 elements such that sum is equal to target.
// Link: https://leetcode.com/problems/two-sum/description/
class Solver2 implements Solver {
    int n;
    int[] nums;
    int target;
    int first=-1;
    int last=-1;

    @Override
    public void solve() {
       Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < this.nums.length; i++) {
            int complement = this.target - this.nums[i];
            if (map.containsKey(complement)) {
                this.first = map.get(complement);
                this.last = i;
                break;
            }
            map.put(this.nums[i], i);
        }
        return;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order.");
        return;
    }

    @Override
    public void printInputFormat() {
        System.out.println("Input consists of 2 lines. The first line consists of 2 integers `n` and `target`. The second line consists of n space separated integers.\n\nExample: 4 9\n2 7 11 15\n");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("Output consists of 2 space separated integers (indices of the elements from the array whose sum is `target`).\n\nExample: 0 1\n");
    }

    @Override
    public void takeInput(Scanner sc) {
        this.n = sc.nextInt();
        this.target = sc.nextInt();
        int nums[] = new int [n];
        for(int i=0; i<n; i++) {
            nums[i]=sc.nextInt();
        }
        this.nums=nums;
    }

    @Override
    public void printOutput() {
        System.out.println(this.first + " " + this.last);
    }
}

// Longest Substring Without Repeating Characters
// Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
class Solver3 implements Solver {
    String str;
    int ans;

    @Override
    public void solve() {
        Map<Character, Integer> map = new HashMap<>();
        this.ans = 0;
        int cnt = 0;
        int position = 0;
        for(int i = 0; i < this.str.length(); i++) {
            if(map.containsKey(this.str.charAt(i)) && map.get(this.str.charAt(i)) >=position) {
                cnt = i - map.get(this.str.charAt(i));
                position = map.get(this.str.charAt(i));
            } else {
                cnt ++;
            }
            map.put(this.str.charAt(i), i);
            this.ans = Math.max(this.ans, cnt);
        }
        return;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given a string s, find the length of the longest `substring` without repeating characters.");
    }

    @Override
    public void printInputFormat() {
        System.out.println("The one and only line of input contains the string itself.\n\nExample: \"abcabcbb\"\n");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("The one and only line of output contains an integer, the length of the longest substring that meets the criteria.\n\nExample: 3\n");
    }

    @Override
    public void takeInput(Scanner sc) {
        this.str = sc.nextLine();
        if(str.equals("")) this.str = sc.nextLine();
        return;
    }

    @Override
    public void printOutput() {
        System.out.println(this.ans);
    }
}

// Median of Two Sorted Arrays
// Link : https://leetcode.com/problems/median-of-two-sorted-arrays/description/
class Solver4 implements Solver {
    int n;
    int m;
    int[] nums1;
    int[] nums2;
    double ans;
    
    @Override
    public void solve() {
        this.ans=findMedianSortedArrays(this.n, this.m, this.nums1, this.nums2);
        return;
    }

    private static boolean isSorted(int[] array) {
        return Arrays.stream(array)
            .reduce((a, b) -> a <= b ? b : Integer.MAX_VALUE)
            .orElse(Integer.MIN_VALUE) != Integer.MAX_VALUE;
    }

    public double findMedianSortedArrays(int a, int b, int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        
        // Ensure nums1 is the smaller array for simplicity
        if (n1 > n2)
            return findMedianSortedArrays(n1, n2, nums2, nums1);
        
        boolean areSorted= isSorted(nums1);
        areSorted=areSorted && isSorted(nums2);

        if(areSorted){
            int n = n1 + n2;
            int left = (n1 + n2 + 1) / 2; // Calculate the left partition size
            int low = 0, high = n1;
            
            while (low <= high) {
                int mid1 = (low + high) >> 1; // Calculate mid index for nums1
                int mid2 = left - mid1; // Calculate mid index for nums2
                
                int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE, r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;
                
                // Determine values of l1, l2, r1, and r2
                if (mid1 < n1)
                    r1 = nums1[mid1];
                if (mid2 < n2)
                    r2 = nums2[mid2];
                if (mid1 - 1 >= 0)
                    l1 = nums1[mid1 - 1];
                if (mid2 - 1 >= 0)
                    l2 = nums2[mid2 - 1];
                
                if (l1 <= r2 && l2 <= r1) {
                    // The partition is correct, we found the median
                    if (n % 2 == 1)
                        return Math.max(l1, l2);
                    else
                        return ((double)(Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
                }
                else if (l1 > r2) {
                    // Move towards the left side of nums1
                    high = mid1 - 1;
                }
                else {
                    // Move towards the right side of nums1
                    low = mid1 + 1;
                }
            } 
        }

        return 0; // If arrays are not sorted answer will be zero.
    } 

    @Override
    public void printProblemStatement() {
        System.out.println("Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.");
        return;
    }

    @Override
    public void printInputFormat() {
        System.out.println("The first line consists of 2 integers n and m. The second line consists of n space-separated integers. The third line consists of m space-separated integers.");
        return;
    }

    @Override
    public void printOutputFormat() {
        System.out.println("The output consists of a single decimal floating-point value which is the median of 2 arrays.");
        return;
    }

    @Override
    public void takeInput(Scanner sc) {
        this.n = sc.nextInt();
        this.m = sc.nextInt();
        int[] nums1 = new int[n];
        int[] nums2 = new int[m];

        for(int i=0; i<this.n; i++) {
            nums1[i] = sc.nextInt();
        }
        for(int i=0; i<this.m; i++) {
            nums2[i] = sc.nextInt();
        }
        this.nums1=nums1;
        this.nums2=nums2;
        return;
    }

    @Override
    public void printOutput() {
        System.out.println(this.ans);
        return;
    }
}

// Valid Parentheses
// Link: https://leetcode.com/problems/valid-parentheses/description/
class Solver5 implements Solver {
    String str;
    boolean ans; 

    @Override
    public void solve() {
        boolean flag=false;
        Stack<Character> stack = new Stack<>();
        for (char c : this.str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {flag=true; break;};
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    flag=true; break;
                }
            }
        }
        if(flag) ans=false;
        else if(stack.isEmpty()) ans=true;
        else ans=false;

        return;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. An input string is valid if: 1) Open brackets must be closed by the same type of brackets. 2) Open brackets must be closed in the correct order. 3) Every close bracket has a corresponding open bracket of the same type.");
    }

    @Override
    public void printInputFormat() {
        System.out.println("Input consists of a string s containing just the characters '(', ')', '{', '}', '[' and ']'.\n\nExample: \"()\"\n");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("If input string is valid print \"Valid\", otherwise \"Invalid\".");
    }

    @Override
    public void takeInput(Scanner sc) {
        sc.nextLine();
        this.str = sc.nextLine();
    }

    @Override
    public void printOutput() {
        if(this.ans) System.out.println("Valid");
        else System.out.println("Invalid");
    }
}

// Merge Intervals
// Link: https://leetcode.com/problems/merge-intervals/description/
class Solver6 implements Solver {
    int[][] intervals;
    int[][] output; 
    @Override
    public void solve() {
        Arrays.sort(this.intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : this.intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(
                    merged.getLast()[1],
                    interval[1]
                );
            }
        }
        this.output = merged.toArray(new int[merged.size()][]);
        return;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.");
        return;
    }

    @Override
    public void printInputFormat() {
        System.out.println("The first line of Input consists of `n` number of intervals. Next, `n` lines follow:Each line consists of 2 space-separated integers, firsti and lasti.");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("The first line of Output consists of `n1` number of intervals. Next, `n1` lines follow: Each line consists of 2 space-separated integers, firsti and lasti. The output must match the list of intervals after merging overlapping intervals.");
    }

    @Override
    public void takeInput(Scanner sc) {
        int n=sc.nextInt();
        int[][] intervals = new  int[n][2];
        for(int i=0; i<n; i++) {
            int first, last;
            first=sc.nextInt();
            last=sc.nextInt();
            intervals[i][0]=first;
            intervals[i][1]=last;
        }
        this.intervals=intervals;
        return;
    }

    @Override
    public void printOutput() {
        int n=this.output.length;
        System.out.println(n);
        for(int i=0; i<n; i++) {
            System.out.println(this.output[i][0] + " " + this.output[i][1]);
        }
        return ;
    }
}

// Longest Palindromic Substring
// Link: https://leetcode.com/problems/longest-palindromic-substring/description/
class Solver7 implements Solver {
    String str;
    String ans;

    @Override
    public void solve() {
        if (this.str == null || this.str.isEmpty()) {
            this.ans = "";
            return;
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < this.str.length(); i++) {
            // Expand around center for odd-length palindromes
            int left = i;
            int right = i;
            while (left >= 0 && right < this.str.length() && this.str.charAt(left) == this.str.charAt(right)) {
                int currentLength = right - left + 1;
                if (currentLength > maxLength) {
                    start = left;
                    maxLength = currentLength;
                }
                left--;
                right++;
            }

            // Expand around center for even-length palindromes
            left = i;
            right = i + 1;
            while (left >= 0 && right < this.str.length() && this.str.charAt(left) == this.str.charAt(right)) {
                int currentLength = right - left + 1;
                if (currentLength > maxLength) {
                    start = left;
                    maxLength = currentLength;
                }
                left--;
                right++;
            }
        }

        this.ans = this.str.substring(start, start + maxLength);
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given a string s, return the longest palindromic substring in s.");
    }

    @Override
    public void printInputFormat() {
        System.out.println("The input consists of a string s.");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("Output the longest palindromic substring in input string s.");
    }

    @Override
    public void takeInput(Scanner sc) {
        sc.nextLine();
        this.str = sc.nextLine();
    }

    @Override
    public void printOutput() {
        System.out.println(this.ans);
    }
}

// Binary Tree Maximum Path Sum Solver
// Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
class Solver8 implements Solver {
    public TreeNode root;
    public int maxSum;

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { 
            val = x; 
        }
    }

    @Override
    public void solve() {
        maxSum = Integer.MIN_VALUE;
        maxPathSum(root);
    }

    public int maxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively compute the maximum path sum of left and right subtrees
        int left = Math.max(maxPathSum(node.left), 0);  // Ignore paths with negative sums
        int right = Math.max(maxPathSum(node.right), 0);

        // Current path sum includes the node and both left and right subtrees
        int currentSum = node.val + left + right;

        // Update the global maximum sum if current path sum is greater
        maxSum = Math.max(maxSum, currentSum);

        // Return the maximum sum path extending from the current node to one of its subtrees
        return node.val + Math.max(left, right);
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given the root of a binary tree, return the maximum path sum of any non-empty path. " +
                "A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. " +
                "The path must contain at least one node and does not need to go through the root.");
    }

    @Override
    public void printInputFormat() {
        System.out.println("Provide the binary tree in level-order traversal as a single line of space-separated values. " +
                "Use 'null' (without quotes) to represent missing nodes.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Represents the following binary tree:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "     / \\\n" +
                "    4   5");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("Output a single integer representing the maximum path sum of the binary tree.\n\n" +
                "Example:\n" +
                "Input: 1 2 3 null null 4 5\n" +
                "Output: 12\n" +
                "Explanation: The maximum path sum is 4 + 3 + 5 = 12.");
    }

    @Override
    public void takeInput(Scanner sc) {
        sc.nextLine();
        String inputLine = sc.nextLine().trim();
        if (inputLine.isEmpty()) {
            root = null;
            return;
        }

        String[] nodeValues = inputLine.split("\\s+");
        root = buildTree(nodeValues);
    }

    /**
     * Builds the binary tree from the given level-order traversal array.
     * @param nodeValues Array of node values in level-order where "null" represents a missing node.
     * @return The root of the binary tree.
     */
    public TreeNode buildTree(String[] nodeValues) {
        if (nodeValues.length == 0 || nodeValues[0].equalsIgnoreCase("null")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(nodeValues[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;

        while (index < nodeValues.length) {
            TreeNode current = queue.poll();

            // Process left child
            if (index < nodeValues.length && !nodeValues[index].equalsIgnoreCase("null")) {
                TreeNode leftChild = new TreeNode(Integer.parseInt(nodeValues[index]));
                current.left = leftChild;
                queue.add(leftChild);
            }
            index++;

            // Process right child
            if (index < nodeValues.length && !nodeValues[index].equalsIgnoreCase("null")) {
                TreeNode rightChild = new TreeNode(Integer.parseInt(nodeValues[index]));
                current.right = rightChild;
                queue.add(rightChild);
            }
            index++;
        }

        return root;
    }

    @Override
    public void printOutput() {
        System.out.println(this.maxSum);
    }
}

// Word Ladder
// Link: https://leetcode.com/problems/word-ladder/description/
class Solver9 implements Solver {
    String beginWord;
    String endWord;
    int n;
    List<String> wordList;
    int shortestTransformationLength;

    static class Pair {
        String first;
        int second;

        public Pair(String first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    @Override
    public void solve() {
        String beginWord = this.beginWord;
        String endWord = this.endWord;
        List<String> wordList = this.wordList;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(beginWord, 1));
        Set<String> set = new HashSet<>();

        for (String word : wordList) {
            set.add(word);
        }
        set.remove(beginWord);

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            String word = pair.first;
            int steps = pair.second;

            if (word.equals(endWord)) {
                this.shortestTransformationLength =  steps;
                return;
            }

            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);
                    if (set.contains(replacedWord)) {
                        set.remove(replacedWord);
                        queue.add(new Pair(replacedWord, steps + 1));
                    }
                }
            }
        }

        this.shortestTransformationLength=0;
        return;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that: Every adjacent pair of words differs by a single letter. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList. `sk == endWord`. Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.");
    }

    @Override
    public void printInputFormat() {
        System.out.println("The input consists of 3+n lines. Each line contains the following: beginWord, endWord, an integer (length of words list) and a new line for each word in words list.\n\nExample:\"hit\"\n\"cog\"\n6\n\"hot\"\n\"dot\"\n\"dog\"\n\"lot\"\n\"log\"\n\"cog\"");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("The output concists of an integer. The length of the shortest transformation sequence.\n\nExample: 5");
    }

    @Override
    public void takeInput(Scanner sc) {
        sc.nextLine();
        this.beginWord = sc.nextLine();
        this.endWord = sc.nextLine();
        this.n = sc.nextInt();
        this.wordList = new ArrayList<String>();
        sc.nextLine();
        // System.out.println(this.n);
        for(int i=0; i<this.n; i++) {
            String word = sc.nextLine();
            this.wordList.add(word);
            // System.out.println(i);
        }
        return;
    }

    @Override
    public void printOutput() {
        System.out.println(this.shortestTransformationLength);
        return;
    }
}

// Number Of Islands
// Link: https://leetcode.com/problems/number-of-islands/description/
class Solver10 implements Solver {
    int n;
    int m;
    char[][] grid;
    int ans;
    
    @Override
    public void solve() {
        if(grid.length==0 || grid==null) { 
            this.ans=0; 
            return; 
        }
        
        int count=0;
        
        for(int row=0;row<grid.length;row++){
            for(int col =0;col<grid[0].length;col++){
                if(grid[row][col]=='1'){
                    count+=helper(grid,row,col);
                }
            }
        }

        this.ans = count;
        return;
    }
    
    private static int helper(char [][] grid,int row,int col){
        if(row <0 || row>=grid.length || col<0||col >=grid[0].length|| grid[row][col]!='1') return 0;
        
        grid[row][col]='0';

        helper(grid,row+1,col);
        helper(grid,row-1,col);
        helper(grid,row,col+1);
        helper(grid,row,col-1);
        
        return 1;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.");
    }

    @Override
    public void printInputFormat() {
        System.out.println("The first line of Input consists of 2 integers n, m. Then n lines follow each with m characters ('1'/'0').\n\nExample:4 5\n11110\n11010\n11000\n00000");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("The output consists of single integer, number of Islands.");
    }

    @Override
    public void takeInput(Scanner sc) {
        this.n = sc.nextInt();
        this.m = sc.nextInt();
        this.grid = new char[n][m];
        
        sc.nextLine();

        for(int i=0; i<n; i++) {
            String str = sc.nextLine();

            for(int j=0; j<m; j++) {
                this.grid[i][j]=str.charAt(j);
            }
        }

        return;
    }

    @Override
    public void printOutput() {
        System.out.println(this.ans);
        return;
    }
}

class Solver11 implements Solver {
    int V;
    int E;
    int S;

    ArrayList<ArrayList<Integer>> edgeList;
    ArrayList<Integer> ans;

    @Override
    public void solve() {
        ArrayList<ArrayList<iPair>> adj = new ArrayList<>();
        
        for(int i=0; i<this.V; i++) {
            ArrayList<iPair> adjListNode = new ArrayList<iPair>();
            adj.add(adjListNode); 
        }

        for(ArrayList<Integer> edge: this.edgeList) {
            iPair ip1 = new iPair(edge.get(1), edge.get(2));
            iPair ip2 = new iPair(edge.get(0), edge.get(2));

            adj.get(edge.get(0)).add(ip1);
            adj.get(edge.get(1)).add(ip2);
        }

        this.ans = dijkstra(adj, S);
        return;
    }

    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        int V = adj.size();
        
        // creating a list to hold the distance of all the vertices from the source.
        ArrayList<Integer> dist = new ArrayList<>();

        // initializing distance of all vertices as infinite and distance of
        // source as 0.
        for (int i = 0; i < V; i++) {
            dist.add(Integer.MAX_VALUE);
        }

        dist.set(src, 0);

        // creating a priority queue to store vertices (having minimum
        // distance from source) and extracting them one by one.
        PriorityQueue<iPair> pq = new PriorityQueue<>((a, b) -> a.second - b.second);

        // initially, distance to source vertex is 0 and all other vertices is infinity.
        for (int i = 0; i < adj.size(); i++) {
            if (i == src) {
                pq.add(new iPair(src, 0));
            }
            else {
                pq.add(new iPair(i, Integer.MAX_VALUE));
            }
        }

        // while priority queue is not empty, we extract the vertex with minimum
        // distance and then for all the adjacent vertices, we check if the
        // distance can be minimized or not.
        while (!pq.isEmpty()) {
            iPair node = pq.poll();
            int u = node.first;

            // iterating over all the adjacent vertices of the extracted vertex.
            for (iPair it : adj.get(u)) {
                // if the distance to the adjacent vertex is more than the distance
                // to the current vertex + weight of edge between them, we update
                // the distance of adjacent vertex.

                if (dist.get(it.first) > dist.get(u) + it.second) {
                    dist.set(it.first, dist.get(u) + it.second);
                    pq.add(new iPair(it.first, dist.get(it.first)));
                }
            }
        }

        // returning the list.
        return dist;
    }

    @Override
    public void printProblemStatement() {
        System.out.println("Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is an edge between i and j, second integers corresponds to the weight of that edge. You are given the source vertex S and You have to Find the shortest distance of all the vertex from the source vertex S. You have to return a list of integers denoting the shortest distance between each node and Source vertex S. Note: The Graph doesn’t contain any negative weight cycle");
    }

    @Override
    public void printInputFormat() {
        System.out.println("Nodes are numbered from 0 to V-1. The input consists of the number of vertices V, start node and the Edge List. The first line of the input contains an integer V (V vertices) and start node id S. Second line consists of E, number of edges. E lines follow, each containing 3 integers separated by single space (node1, node2, weight).\n\nExample:3 2\n3\n0 1 1\n0 2 6\n1 2 3\n");
    }

    @Override
    public void printOutputFormat() {
        System.out.println("The output consists of V integers, ith integer is equal to length of shortest path from S to node i.");
    }

    @Override
    public void takeInput(Scanner sc) {
        this.V = sc.nextInt();
        this.S = sc.nextInt();
        this.E = sc.nextInt();

        ArrayList<ArrayList<Integer>> edgeList = new ArrayList<>();

        for(int i=0; i<this.E; i++) {
            ArrayList<Integer> edge = new ArrayList<Integer>();
            int u=sc.nextInt(); int v=sc.nextInt(); int w=sc.nextInt();

            edge.add(u);edge.add(v);edge.add(w);
            edgeList.add(edge);
        }

        this.edgeList=edgeList;

        return;
    }

    @Override
    public void printOutput() {
        for(int i=0; i<this.V; i++) {
            if(i!=this.V-1) {
                System.out.print(this.ans.get(i) + " ");
            }
            else {
                System.out.print(this.ans.get(i));
            }
        }

        return;
    }
}