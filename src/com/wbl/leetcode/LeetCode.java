package com.wbl.leetcode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Lulala on 2015/7/13.
 */
public class LeetCode {
        //Triangle
        public int minimumTotal(List<List<Integer>> triangle) {
                int N = triangle.size();
                int[] minSum = new int[(N+1)*N/2];
                for(int i = 0; i < triangle.get(N-1).size(); i++){
                        minSum[(N-1)*N/2+i] = triangle.get(N-1).get(i);
                }
                for(int i = N - 1; i >= 0; i--){
                        for(int j = 0; j < i; j++){
                                minSum[(i-1)*i/2 + j] = Math.min(minSum[i*(i+1)/2 + j],minSum[i*(i+1)/2+j+1])+ triangle.get(i-1).get(j);
                        }
                }
                return minSum[0];
        }

        //Minimum Path Sum
        public int minPathSum(int[][] grid) {
                int m = grid.length;
                int n = grid[0].length;
                int[][] minSum = new int[m][n];
                minSum[m-1][n-1] = grid[m-1][n-1];
                for(int i = n-1; i > 0; i--)
                        minSum[m-1][i-1] = minSum[m-1][i] + grid[m-1][i-1];
                for(int i = m - 1; i >0; i--){
                        for(int j = n-1; j >=0; j--){
                                if(j==n-1){
                                        minSum[i-1][j] = minSum[i][j] + grid[i-1][j];
                                }else
                                        minSum[i-1][j] = Math.min(minSum[i][j],minSum[i-1][j+1]) + grid[i-1][j];
                        }
                }
                return minSum[0][0];
        }

        //Binary Tree Inorder Traversal
        public List<Integer> inorderTraversal(TreeNode root) {
                List<Integer> list = new ArrayList<>();
                binaryInorder(root,list);
                return list;
        }

        private void binaryInorder(TreeNode root, List<Integer> list){
                if (root != null){
                        binaryInorder(root.left,list);
                        list.add(root.val);
                        binaryInorder(root.right,list);
                }
        }
        //Morris Traversal  中序遍历
        public void morrisTraversal(TreeNode root){
                TreeNode temp;
                while (root != null){
                        if (root.left != null){
                                temp = root.left;
                                while (temp.right != null){
                                        temp = temp.right;
                                }
                                temp.right = root;
                                temp = root.left;
                                root.left = null;
                                root = temp;
                        }else {
                                System.out.print(root.val + " ");
                                root = root.right;
                        }
                }
        }

        //Morris Traversal  前序遍历
        public void morrisPreorder(TreeNode root){
                TreeNode temp;
                while (root != null){
                        if (root.left != null){
                                temp = root.left;
                                while (temp.right != null && temp.right != root){
                                        temp = temp.right;
                                }
                                if (temp.right == null){
                                        System.out.print(root.val + " ");
                                        temp.right = root;
                                        root = root.left;
                                }else {
                                        temp.right = null;
                                        root = root.right;
                                }
                        }else {
                                System.out.print(root.val + " ");
                                root = root.right;
                        }
                }
        }


        //Binary Tree Paths
        public List<String> binaryTreePaths(TreeNode root) {
                List<String> list = new ArrayList<>();
                if (root != null){
                        binaryTreePathsHelper(root,list,"");
                }
                return list;
        }

        private void binaryTreePathsHelper(TreeNode root, List<String> list, String path){
                if (root !=null){
                        path = path + "->" + root.val;
                        if (root.left == null && root.right == null){
                                if (!list.contains(path)){
                                        list.add(path.substring(2));
                                }
                        }
                        binaryTreePathsHelper(root.left, list, path);
                        binaryTreePathsHelper(root.right,list,path);
                }
        }

        //Construct Binary Tree from Preorder and Inorder Traversal
        /*public TreeNode buildTree(int[] preorder, int[] inorder) {
                if(preorder == null && inorder == null || preorder.length == 0){
                        return null;
                }
                TreeNode node = new TreeNode(preorder[0]);
                int i = 0;
                for (; i < preorder.length; i ++){
                        if (preorder[0] == inorder[i]){
                                break;
                        }
                }
                int[] new_preorder_left,new_preorder_right,new_inorder_left,new_inorder_right;
                if (i < inorder.length){
                        new_inorder_left = new int[i];
                        System.arraycopy(inorder,0,new_inorder_left,0,i);
                        new_preorder_left = new int[i];
                        System.arraycopy(preorder,1,new_preorder_left,0,i);
                        node.left = buildTree(new_preorder_left,new_inorder_left);

                        new_inorder_right = new int[preorder.length - i - 1];
                        System.arraycopy(inorder,i + 1,new_inorder_right,0,preorder.length - i - 1);
                        new_preorder_right = new int[preorder.length - i -1];
                        System.arraycopy(preorder,i + 1,new_preorder_right,0,preorder.length -i - 1);
                        node.right = buildTree(new_preorder_right,new_inorder_right);
                }
                return node;
        }*/

        //Construct Binary Tree from Inorder and Postorder Traversal
        public TreeNode buildTree(int[] inorder, int[] postorder) {
                if (inorder == null && postorder == null || inorder.length == 0){
                        return null;
                }
                TreeNode node = new TreeNode(postorder[postorder.length - 1]);
                int i = 0;
                for (; i < inorder.length; i++){
                        if (inorder[i] == postorder[postorder.length - 1]){
                                break;
                        }
                }
                int[] new_inorder_left, new_inorder_right,new_postorder_left,new_postorder_right;
                if (i < inorder.length){
                        new_inorder_left = new int[i];
                        System.arraycopy(inorder,0,new_inorder_left,0,i);
                        new_postorder_left = new int[i];
                        System.arraycopy(postorder,0,new_postorder_left,0,i);
                        node.left =buildTree(new_inorder_left,new_postorder_left);

                        new_inorder_right = new int[inorder.length - i -1];
                        System.arraycopy(inorder,i + 1,new_inorder_right,0,inorder.length - i - 1);
                        new_postorder_right = new int[inorder.length - i - 1];
                        int length = inorder.length - i - 1;
                        for (int j = 0; j <length;j++ ){
                                new_postorder_right[j] = postorder[i];
                                i++;
                        }
                        node.right = buildTree(new_inorder_right,new_postorder_right);
                }
                return node;
        }

        //Move Zeroes
        public void moveZeroes(int[] nums) {
                int zeroIndex = 0;
                int nozeroIndex = 0;
                while (nozeroIndex <= nums.length -1 && zeroIndex <= nums.length - 1){
                        while (zeroIndex <= nums.length - 1 && nums[zeroIndex] != 0){
                                zeroIndex++;
                        }
                        while ( nozeroIndex <= nums.length -1 && nums[nozeroIndex] == 0){
                                nozeroIndex ++;
                        }
                        if (zeroIndex >= nums.length || nozeroIndex >= nums.length){
                                break;
                        }
                        if ( zeroIndex < nozeroIndex){
                                int temp = nums[zeroIndex];
                                nums[zeroIndex] = nums[nozeroIndex];
                                nums[nozeroIndex] = temp;
                        }else {
                                nozeroIndex ++;
                        }

                }
        }

        //Add Digits
        public int addDigits(int num) {
                int[] trival = new int[91];
                for (int i =1; i < trival.length; i++){
                        if (i <10){
                                trival[i] = i;
                        }else {
                                trival[i] = trival[i /10 + i % 10];
                        }
                        //System.out.println(i + ": " + trival[i]);
                }
                String temp = String.valueOf(num);
                int sum = 0;
                for (int i = 0; i < temp.length();i++){
                        sum = sum + (temp.charAt(i) - '0');
                }
                return trival[sum];
        }

        //Basic Calculator II
        public int calculate(String s) {
                ArrayList<String> temp = new ArrayList<>();
                String[] digial = s.replaceAll(" ","").split("\\D");
                String[] test = s.replaceAll(" ","").split("\\d");
                ArrayList<String> operate = new ArrayList<>();
                for (int i = 0; i < test.length; i++){
                        if (!test[i].equals("")){
                                operate.add(test[i]);
                        }
                }
                if (digial.length == 1){
                        return Integer.valueOf(digial[0]);
                }
                int j = 0;
                for (int i = 0; i < operate.size();i++){
                        if ("+".equals(operate.get(i)) || "-".equals(operate.get(i))){
                                temp.add(digial[j++]);
                                temp.add(operate.get(i));
                        }else {
                                int val1 = Integer.valueOf(digial[j++]);
                                int val2 = Integer.valueOf(digial[j]);
                                if ("/".equals(operate.get(i))){
                                        int result = val1 / val2;
                                        digial[j] = String.valueOf(result);
                                }
                                if ("*".equals(operate.get(i))){
                                        int result = val1 * val2;
                                        digial[j] = String.valueOf(result);
                                }
                        }
                }
                temp.add(digial[digial.length - 1]);
                for (int i = 0; i < temp.size() - 1; ){
                        int val1 = Integer.valueOf(temp.get(i));
                        String ope = temp.get(i + 1);
                        int val2 = Integer.valueOf(temp.get(i+2));
                        if ("+".equals(ope)){
                                int result = val1 + val2;
                                temp.set(i+=2,String.valueOf(result));
                        }else {
                                int result = val1 - val2;
                                temp.set(i += 2, String.valueOf(result));
                        }
                }
                return Integer.valueOf(temp.get(temp.size() - 1));
        }
        public int calculate2(String s){
                s = s.replaceAll(" ","");
                Stack<Integer> stack = new Stack<>();
                String[] digial = s.split("\\D");
                if (digial.length == 1){
                        return Integer.valueOf(digial[0]);
                }
                String[] test = s.split("\\d");
                ArrayList<String> operate = new ArrayList<>();
                for (int i = 0; i < test.length; i++){
                        if (!test[i].equals("")){
                                operate.add(test[i]);
                        }
                }
                stack.push(Integer.valueOf(digial[0]));
                int j = 1;
                for (int i = 0; i < operate.size(); i++){
                        switch (operate.get(i)){
                                case "+":
                                        stack.push(Integer.valueOf(digial[j++]));
                                        break;
                                case "-":
                                        stack.push(-Integer.valueOf(digial[j++]));
                                        break;
                                case "/":
                                        stack.push(stack.pop() / Integer.valueOf(digial[j++]));
                                        break;
                                case "*":
                                        stack.push(stack.pop() * Integer.valueOf(digial[j++]));
                                        break;
                        }
                }
                int result = 0;
                for (Integer integer:stack){
                        result += integer;
                }
                return result;
        }

        //Word Pattern
        public boolean wordPattern(String pattern, String str) {
                char[] temp = pattern.toCharArray();
                String[] word = str.split(" ");
                if (temp.length != word.length)
                        return false;
                Map<Character,String> map = new HashMap<>();
                for (int i = 0; i < temp.length; i++){
                        if (map.containsKey(temp[i])){
                                if (!map.get(temp[i]).equals(word[i]))
                                        return false;
                        }else if (map.containsValue(word[i])){
                                return false;
                        }
                        map.put(temp[i],word[i]);
                }
                return true;
        }

        //Missing Number
        public int missingNumber(int[] nums) {
                int length = nums.length;
                Arrays.sort(nums);
                for (int i = 0; i < length; i++){
                        if (nums[i] != i)
                                return i;
                }
                return length;
        }

        //ZigZag Conversion
        public String convert(String s, int numRows) {
                if (s == null || s.isEmpty() || numRows == 1)
                        return s;
                Map<Integer,ArrayList<Character>> map = new HashMap<>();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < numRows; i++){
                        map.put(i,new ArrayList<Character>());
                        list.add(numRows - i - 1);
                }
                for (int i = 0,j =0; i < s.length(); i++){
                        if (i % (numRows - 1) == 0){
                                Collections.reverse(list);
                                j = 0;
                        }
                        map.get(list.get(j)).add(s.charAt(i));
                        j++;
                }
                StringBuilder temp = new StringBuilder();
                for (ArrayList<Character> set: map.values()){
                        for (Character c: set){
                                temp.append(c);
                        }
                }
                return temp.toString();
        }

        //Longest Substring Without Repeating Characters
        public int lengthOfLongestSubstring(String s) {
                if (s == null || s.isEmpty())
                        return 0;
                HashSet<Character> set = new HashSet<>();
                int i=0,j=0,max = 0;
                while (j < s.length()){
                        if (set.contains(s.charAt(j))){
                                if (max <= j - i){
                                        max = j - i;
                                }
                                while (s.charAt(i) != s.charAt(j)){
                                        set.remove(s.charAt(i));
                                        i++;
                                }
                                i++;
                        }else {
                                set.add(s.charAt(j));
                        }
                        j++;
                }
                return Math.max(max, j - i);
        }

        //Combination Sum III
        public List<List<Integer>> combinationSum3(int k, int n) {
                List<List<Integer>> result = new ArrayList<>();
                helper(1,k,n,new ArrayList<Integer>(),result);
                return result;
        }

        private void helper(int start,int times,int target,List<Integer> list,List<List<Integer>> result){
                if (list.size() == times && target == 0){
                        result.add(new ArrayList<Integer>(list));
                        return;
                }
                for (int i = start; i <= 9; i++){
                        list.add(i);
                        helper(i + 1,times, target - i, list, result);
                        list.remove(list.size() - 1);
                }
        }

        //Generate Parentheses
        public List<String> generateParenthesis(int n) {
                List<String> result = new ArrayList<>();
                helper(result,new StringBuilder(),0,0,n);
                return result;
        }
        public void helper(List<String> result,StringBuilder builder,int l,int r,int n){
                if (r == n){
                        result.add(builder.toString());
                        return;
                }

                if (l < n){
                        builder.append("(");
                        helper(result, builder, l + 1, r, n);
                        builder.deleteCharAt(builder.length() - 1);
                }

                if (l > r){
                        builder.append(")");
                        helper(result, builder, l, r + 1, n);
                        builder.deleteCharAt(builder.length() - 1);
                }
        }

        //Permutation Sequence
        public String getPermutation(int n, int k){
                StringBuilder origal = new StringBuilder();
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <= n; i++){
                        origal.append(i);
                }
                while (k != 0){
                        int temp = k / fab(n-1);
                        k = k % fab(n-1);
                        n = n -1;
                        if (k == 0){
                                result.append(origal.charAt(temp-1));
                                origal.deleteCharAt(temp-1);
                                result.append(origal.reverse());
                                return result.toString();
                        }else {
                                result.append(origal.charAt(temp));
                                origal.deleteCharAt(temp);
                        }
                }
                return result.append(origal).toString();
        }

        public int fab(int n){
                int sum = 1;
                while (n!=0){
                        sum *= n;
                        n= n-1;
                }
                return sum;
        }
        /*private void helper(List<String> result, StringBuilder builder,int n,int k){
                if (result.size() == k)
                        return;
                if (builder.length() == n){
                        result.add(builder.toString());
                        return;
                }
                for (int i = 1; i <= n; i ++){
                        if (builder.indexOf(String.valueOf(i)) == -1){
                                builder.append(i);
                                helper(result, builder, n,k);
                                builder.deleteCharAt(builder.length()-1);
                        }
                }
        }*/
        //N-Queens
        public List<List<String>> solveNQueens(int n) {
                List<List<String>> result = new ArrayList<>();
                int[][] m = new int[n][n];
                helper(result,0,n,m);
                return result;
        }
        //N-Queens II
        public int totalNQueens(int n) {
                List<Integer> list = new ArrayList<>();
                int[][] m = new int[n][n];
                helper2(list,0,n,m);
                return list.size();
        }

        private Set<Integer> col = new HashSet<>();
        private Set<Integer> dg1 = new HashSet<>();
        private Set<Integer> dg2 = new HashSet<>();

        public List<List<String>> solveNQueens2(int n){
                List<List<String>> result = new ArrayList<>();
                dfs(result,new ArrayList<String>(),0,n);
                return result;
        }

        public void dfs(List<List<String>> result,List<String> list, int row,int n){
                if(row == n){
                        result.add(new ArrayList<String>(list));
                        return;
                }
                for (int i = 0; i < n; i++){
                        if (col.contains(i) || dg1.contains(row + i) || dg2.contains(row - i))
                                continue;
                        char[] arr = new char[n];
                        Arrays.fill(arr,'.');
                        arr[i] = 'Q';
                        String rowString = new String(arr);

                        list.add(rowString);
                        col.add(i);
                        dg1.add(row + i);
                        dg2.add(row -i);

                        dfs(result, list, row + 1, n);

                        list.remove(list.size() - 1);
                        col.remove(i);
                        dg1.remove(row + i);
                        dg2.remove(row - i);
                }
        }

        private void helper2(List<Integer> list,int times,int n, int[][] m){
                if (times >= n){
                        list.add(n);
                        return;
                }
                for (int i = 0; i < n; i++){
                        m[times][i] = 1;
                        if (isSafe(times,i,m))
                                helper2(list, times + 1, n, m);
                        m[times][i] = 0;
                }
        }

        private void helper(List<List<String>> result,int times,int n,int[][] m){
                if (times >=n){
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i <n; i++){
                                StringBuilder builder = new StringBuilder();
                                for (int j =0; j < n; j++){
                                        if (m[i][j] == 1)
                                                builder.append("Q");
                                        else
                                                builder.append(".");
                                }
                                list.add(builder.toString());
                        }
                        result.add(list);
                        return;
                }
                for (int i = 0; i < n; i++){
                        m[times][i] = 1;
                        if (isSafe(times,i,m))
                                helper(result, times + 1, n, m);
                        m[times][i] = 0;
                }
        }
        private boolean isSafe(int i,int j, int[][] m){
                int length = m[0].length;
                for (int k = 0; k < length; k++)
                        if ((m[i][k] == 1 && k != j) || (m[k][j] == 1 && k != i))
                                return false;
                for (int k = i-1, l = j-1; k >=0 && l >=0; k--,l--)
                        if (m[k][l] == 1)
                                return false;
                for (int k = i+1, l = j+1; k <length && l <length;k++,l++)
                        if (m[k][l] == 1)
                                return false;
                for (int k = i + 1, l = j-1; k <length && l >= 0;k++,l--)
                        if (m[k][l] == 1)
                                return false;
                for (int k = i-1,l = j+1; k >=0 && l < length;k--,l++)
                        if (m[k][l] == 1)
                                return false;
                return true;
        }

        //Gray Code
        public List<Integer> grayCode(int n) {
                List<Integer> list = new ArrayList<>();
                list.add(0);
                for (int i = 1;i <= n;i++){
                        int point = (int) Math.pow(2,i-1);
                        int length = list.size();
                        for (int j = length-1; j>=0; j--){
                                list.add(list.get(j) + point);
                        }
                }
                return list;
        }

        //Palindrome Partitioning
        public List<List<String>> partition(String s) {
                List<List<String>> result = new ArrayList<>();
                if (s == null || s.isEmpty())
                        return result;
                helper(result, new ArrayList<String>(), s);
                return result;
        }

        private void helper(List<List<String>> result, List<String> list, String s){
                if (s.isEmpty()){
                        result.add(new ArrayList<String>(list));
                }
                for (int i = 1; i <= s.length();i++){
                        String temp = s.substring(0,i);
                        if (isPalindrome(temp)){
                                list.add(temp);
                                helper(result, list, s.substring(i));
                                list.remove(list.size() - 1);
                        }

                }
        }

        private boolean isPalindrome(String s){
                for (int low = 0,high = s.length() -1; low <= high; low++,high--){
                        if (s.charAt(low) != s.charAt(high))
                                return false;
                }
                return true;
        }


        //Permutations II
        public List<List<Integer>> permuteUnique(int[] nums) {
                List<List<Integer>> result = new ArrayList<>();
                Arrays.sort(nums);
                helper(result, nums, new boolean[nums.length], new ArrayList<Integer>());
                return result;
        }

        private void helper(List<List<Integer>> result,LinkedList<Integer> linkedList,int start){
                if (start == linkedList.size() - 1){
                        result.add(new ArrayList<Integer>(linkedList));
                        return;
                }
                for (int i = start; i < linkedList.size();i++ ){
                        if (i > start && linkedList.get(i) == linkedList.get(i-1))
                                continue;
                        linkedList.add(start,linkedList.get(i));
                        linkedList.remove(i+1);
                        helper(result, linkedList,start + 1);
                        linkedList.add(i + 1, linkedList.get(start));
                        linkedList.remove(start);
                }

        }

        private void helper(List<List<Integer>> result,int[] nums, boolean[] used,List<Integer> list){
                if (list.size() == nums.length){
                        result.add(new ArrayList<Integer>(list));
                        return;
                }
                for (int i = 0; i < nums.length; i++){
                       if (i > 0 && nums[i] == nums[i-1] && !used[i-1])
                               continue;
                        if (!used[i]){
                                used[i] = true;
                                list.add(nums[i]);
                                helper(result, nums, used, list);
                                used[i] = false;
                                list.remove(list.size() - 1);
                        }
                }
        }


        //Restore IP Addresses
        public List<String> restoreIpAddresses(String s) {
                List<String> result = new ArrayList<>();
                if (s.length() > 12)
                        return result;
                else {
                        helper2(result, new ArrayList<String>(), s);
                        return result;
                }
        }

        private void helper2(List<String> result, List<String> list, String s){
                if (list.size() == 4 && s.isEmpty()){
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < list.size(); i++){
                                if (i > 0)
                                        builder.append("." + list.get(i));
                                else
                                        builder.append(list.get(i));
                        }
                        result.add(builder.toString());
                        return;
                }
                for (int i = 1; i <=s.length();i++){
                        String temp = s.substring(0,i);
                        if (isLeagal(temp)){
                                list.add(temp);
                                helper2(result, list, s.substring(i));
                                list.remove(list.size() - 1);
                        }
                }
        }

        private boolean isLeagal(String s){
               if (s.length() >=4)
                       return false;
               else{
                       if (Integer.valueOf(s) > 255)
                               return false;
                       if (s.charAt(0) == '0' && s.length() > 1)
                               return false;
                       return true;
               }
        }

        //Word Search
        public boolean exist(char[][] board, String word) {
                int n = board[0].length;
                int m = board.length;
                boolean[][] used =  new boolean[m][n];
                for (int i = 0; i < m;i++)
                        for (int j = 0; j < n; j++){
                                if(word.charAt(0) == board[i][j] && helper(used,board,word,0,i,j))
                                        return true;
                        }
                return false;
        }

        private boolean helper(boolean[][] used,char[][] board,String word,int step,int i,int j){
                if (step >= word.length())
                        return true;
                if (i < 0 || i >= board.length || j < 0 || j>=board[0].length || used[i][j] || board[i][j] != word.charAt(step))
                        return false;
                used[i][j] = true;
                if (helper(used,board,word,step+1,i,j-1) ||
                        helper(used,board,word,step+1,i-1,j)||
                        helper(used,board,word,step + 1,i, j+1)||
                        helper(used,board,word,step+1,i+1,j)){
                        return true;
                }
                used[i][j] = false;
                return false;

        }

        //Ugly Number
        public boolean isUgly(int num) {
                if (num == 0)
                        return false;
                if (num == 1)
                        return true;
                if (num % 2 == 0){
                        if (isUgly(num/2))
                                return true;
                }
                if (num % 3 == 0){
                        if (isUgly(num/3))
                                return true;
                }
                if (num % 5 == 0){
                        if (isUgly(num/5))
                                return true;
                }
                return false;
        }

        //Summary Ranges
        public List<String> summaryRanges(int[] nums) {
                List<String> result = new ArrayList<>();
                if (nums == null || nums.length == 0)
                        return result;
                for (int i = 0; i < nums.length;){
                        int end = nums[i];
                        result.add(String.valueOf(end));
                        while (i < nums.length && end == nums[i]){
                                end++;
                                i++;
                        }
                         if (i > 0 && nums[i-1] != Integer.valueOf(result.get(result.size() - 1))){
                                 String temp = result.get(result.size() - 1);
                                 result.set(result.size()-1,temp + "->" + nums[i-1]);
                         }
                }
                return result;
        }

        //Power of Two
        public boolean isPowerOfTwo(int n) {
                //判断一个数是否是2的幂，如果一个数的二进制最高位为1，其他为0，则是2的幂
                if (n <=0 )
                        return false;
                return (n & (n-1)) == 0;
        }

        //Count Complete Tree Nodes
        public int countNodes(TreeNode root){
                if (root == null)
                        return 0;
                int l = getLeftLevel(root);
                int r = getRightLevel(root);

                //如果左右子树的高度相等，则为满二叉树，所以节点数为2^h - 1
                if (l == r){
                        return (1 << l) - 1;
                }else
                        return countNodes(root.left) + countNodes(root.right) + 1;
        }

        private int getLeftLevel(TreeNode node){
                int count = 0;
                while (node!= null){
                        node = node.left;
                        count++;
                }
                return count;
        }

        private int getRightLevel(TreeNode node){
                int count = 0;
                while (node != null){
                        node = node.right;
                        count++;
                }
                return count;
        }

        //Palindrome Linked List
        public boolean isPalindrome(ListNode head) {
                if (head == null || head.next == null)
                        return true;
                ListNode t = head;
                int length = getLength(t);
                ListNode oneSetp = head,twoSetp = head;
                List<Integer> list = new ArrayList<>();
                while (twoSetp != null &&twoSetp.next != null ){
                        list.add(oneSetp.val);
                        oneSetp = oneSetp.next;
                        twoSetp = twoSetp.next.next;
                }
                if (length % 2 != 0 && oneSetp.next != null){
                        list.add(oneSetp.val);
                }
                Collections.reverse(list);
                int i = 0;
                while (oneSetp != null){
                        if (oneSetp.val != list.get(i))
                                return false;
                        oneSetp = oneSetp.next;
                        i++;
                }
                return i == list.size();
        }

        private int getLength(ListNode head){
                int count = 0;
                while (head != null){
                        head = head.next;
                        count++;
                }

                return count;
        }

        //Maximum Subarray
        public int maxSubArray(int[] nums) {
                int[] index = divide_conquer(nums, 0, nums.length - 1);
                return index[2];
        }

        private int[] divide_conquer(int[] nums,int low,int high){
                if (low == high){
                        return new int[]{low,high,nums[low]};
                }
                int mid = (low + high) /2;
                int[] left_sub = divide_conquer(nums,low,mid);
                int[] right_sub = divide_conquer(nums,mid + 1,high);
                int[] cross_sub = find_max_cross(nums, low, mid, high);

                return combine(left_sub,right_sub,cross_sub);
        }

        private int[] find_max_cross(int[] nums,int low,int mid,int high){
                int left_max_low = mid;
                int left_max_sum = nums[mid];
                int left_sum = 0;
                for (int i = mid; i >= low; i--){
                        left_sum = nums[i] + left_sum;
                        if (left_sum > left_max_sum){
                                left_max_sum = left_sum;
                                left_max_low = i;
                        }
                }

                int right_max_high = mid + 1;
                int right_max_sum = nums[mid+1];
                int right_sum = 0;
                for (int i = mid + 1; i <= high; i++){
                        right_sum = nums[i] + right_sum;
                        if (right_sum > right_max_sum){
                                right_max_sum = right_sum;
                                right_max_high = i;
                        }
                }
                return new int[]{left_max_low,right_max_high,left_max_sum + right_max_sum};
        }

        public int maxSubArray2(int[] nums){
                if (nums == null || nums.length == 0){
                        return 0;
                }
                int currentSum = 0;
                int greetSum = Integer.MIN_VALUE;
                for (int i = 0; i < nums.length;i++){
                        if (currentSum <= 0){
                                currentSum = nums[i];
                        }else
                                currentSum += nums[i];
                        if (currentSum > greetSum)
                                greetSum = currentSum;
                }
                return greetSum;
        }


        private int[] combine(int[] left_sub,int[] right_sub,int[] cross_sub){
                if (left_sub[2] >= right_sub[2] && left_sub[2] >= cross_sub[2])
                        return left_sub;
                else if (right_sub[2] >= left_sub[2] && right_sub[2] >= cross_sub[2])
                        return right_sub;
                else
                        return cross_sub;
        }

        //Kth Largest Element in an Array
        public int findKthLargest(int[] nums, int k) {
                int start = 0;
                int end = nums.length;
                int k1 = k-1;
                int j,d;
                do {
                        j = start;
                        d = nums[start];
                        for (int i = start+1; i < end; i++){
                                if (nums[i] > d){
                                        nums[j] = nums[i];
                                        nums[i] = nums[j+1];
                                        j++;
                                }
                        }

                        nums[j] = d;
                        if (j < k1) {
                                start = j+1;
                        } else if (j > k1) {
                                end = j;
                        }
                }while (j != k1);
                return nums[k1];
        }

        //Different Ways to Add Parentheses
        public List<Integer> diffWaysToCompute(String input) {
                List<Integer> result = new ArrayList<>();
                if (input.matches("\\d+")){
                        result.add(Integer.valueOf(input));
                        return result;
                }

                for (int i = 0; i < input.length(); i++){
                        if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                                for (int l : left)
                                        for (int k : right){
                                                switch (input.charAt(i)){
                                                        case '+':
                                                                result.add(l+k);
                                                                break;
                                                        case '-':
                                                                result.add(l-k);
                                                                break;
                                                        case '*':
                                                                result.add(l*k);
                                                                break;
                                                }
                                        }
                        }
                }
                return result;
        }

        //Search a 2D Matrix II
        public boolean searchMatrix(int[][] matrix, int target) {
                /*int length = matrix.length;
                for (int i = 0; i < length;i++){
                        if (binarySearch(matrix[i],target))
                                return true;
                }
                return false;*/
                int m = matrix.length;
                int n = matrix[0].length;
                int i=0,j=n-1;
                while (i <= m -1 && j>=0){
                        if (matrix[i][j] == target){
                                return true;
                        }else if (matrix[i][j] >target){
                                j--;
                        }else {
                                i++;
                        }
                }
                return false;
        }

        public boolean binarySearch(int[] nums, int target){
                int low = 0,high = nums.length-1;
                while (low<=high){
                        int mid = (low + high)/2;
                        if (target > nums[mid]){
                                low = mid + 1;
                        }else if (target < nums[mid]){
                                high = mid - 1;
                        }else
                                return true;
                }
                return false;
        }

        //Delete Node in a Linked List
        public void deleteNode(ListNode node) {
                ListNode temp = node;
                while (node.next != null){
                        node.val = node.next.val;
                        temp = node;
                        node = node.next;
                }
                temp.next = null;
        }

        //Bulls and Cows
        public String getHint(String secret, String guess) {
                int[] num = new int[10];
                int bulls=0,cows = 0;
                for (int i = 0; i < secret.length();i++){
                        int s = secret.charAt(i) - '0';
                        int t = guess.charAt(i) - '0';
                        if (s == t)
                                bulls++;
                        else {
                                if (num[s] < 0) cows++;
                                if (num[t]  > 0) cows++;
                                num[s]++;
                                num[t]--;
                        }
                }
                return bulls + "A" + cows + "B";
        }

        //Longest Increasing Subsequence
       /* public int lengthOfLIS(int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                int[] flag = new int[nums.length];
                Arrays.fill(flag,1);
                int maxLength = 1;
                for (int i =nums.length - 2; i >= 0; i--){
                        int maxTemp = flag[i];
                        for (int j = i+1; j < nums.length;j++){
                                if (nums[i] < nums[j]){
                                        maxTemp = maxTemp > flag[j] + 1 ? maxTemp : flag[j] + 1;
                                }
                        }
                        flag[i] = maxTemp;
                        maxLength = maxLength > flag[i] ? maxLength: flag[i];
                }
                return maxLength;
        }*/
        public int lengthOfLIS(int[] nums) {
                int[] dp = new int[nums.length];
                int len = 0;

                for(int x : nums) {
                        int i = Arrays.binarySearch(dp, 0, len, x);
                        if(i < 0) i = -(i + 1);
                        dp[i] = x;
                        if(i == len) len++;
                }

                return len;
        }

        //Serialize and Deserialize Binary Tree
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
                Queue<TreeNode> queue = new LinkedList<>();
                if (root == null)
                        return null;
                queue.offer(root);
                StringBuilder builder = new StringBuilder(root.val + ",");
                while (!queue.isEmpty()){
                        TreeNode temp = queue.poll();
                        //builder.append(temp.val + ",");
                        if (temp.left != null){
                                queue.offer(temp.left);
                                builder.append(temp.left.val + ",");
                        }else {
                                builder.append("*,");
                        }
                        if (temp.right != null){
                                queue.offer(temp.right);
                                builder.append(temp.right.val + ",");
                        }else {
                                builder.append("*,");
                        }
                }
                String result = builder.toString();
                result = result.substring(0,result.length()-1);
                return result;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
                if (data == null || data.isEmpty())
                        return null;
                String[] val = data.split(",");
                List<TreeNode> nodes = new ArrayList<>();
                nodes.add(new TreeNode(Integer.parseInt(val[0])));
                int count = 0;
                for (int i = 0;count < nodes.size();count++,i+=2){
                        if (i +1 < val.length &&!"*".equals(val[i+1])){
                                nodes.get(count).left = new TreeNode(Integer.parseInt(val[i+1]));
                                nodes.add(nodes.get(count).left);
                        }
                        if (i + 2 < val.length && !"*".equals(val[i+2])){
                                nodes.get(count).right = new TreeNode(Integer.parseInt(val[i+2]));
                                nodes.add(nodes.get(count).right);
                        }
                }
                return nodes.get(0);
        }

        //Find the Duplicate Number
        public int findDuplicate(int[] nums) {
                int slow = 0;
                int fast = 0;
                while (true){
                        slow = nums[slow];
                        fast = nums[nums[fast]];
                        if (slow == fast)
                                break;
                }
                fast = 0;
                while (true){
                        slow = nums[slow];
                        fast = nums[fast];
                        if (slow == fast)
                                break;
                }
                return slow;
        }

        public int[] singleNumber(int[] nums){
                int sum = 0,bit1 =0,bit0=0;
                for (int num:nums){
                        sum ^= num;
                }
                int i = 1;
                while (((sum >> i) & 1) != 1){
                        i++;
                }
                for (int num: nums){
                        if (((num >> i) & 1) == 1)
                                bit1 ^= num;
                        else
                                bit0 ^= num;
                }

                return new int[]{sum ^ bit0,sum ^ bit1};
        }

        //Valid Anagram
        public boolean isAnagram(String s, String t) {
                if (s == null && t == null)
                        return true;
                if (s == null || t == null)
                        return false;
                char[] sChar = s.toCharArray();
                char[] tChar = t.toCharArray();
                Arrays.sort(sChar);
                Arrays.sort(tChar);
                return String.valueOf(sChar).equals(String.valueOf(tChar));
        }

        //Product of Array Except Self
        /*
        * r0 = 1 * (X1 * X2 * ..... * Xn-1)
        * r1= X0 * (X2 * ..... * Xn-1)
        * r2= (X0  * X1)* (X3* ..... * Xn-1)
        * r3= (X0  * X1 * X2)* (X4*X5 ..... * Xn-1)
        * .
        * .
        * rn-1= (X1 * X2 * ..... * Xn-2) * 1
        * */
        public int[] productExceptSelf(int[] nums) {
                int[] temp = new int[nums.length];
                temp[0] = 1;
                for (int i = 1; i < nums.length; i++)
                        temp[i] = temp[i-1] * nums[i-1];
               int product = 1;
                for (int i = nums.length - 1;i >=0;i--){
                        temp[i] = temp[i] * product;
                        product *= nums[i];
                }
                return temp;
        }

        //Lowest Common Ancestor of a Binary Search Tree
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
                if (p.val > root.val && q.val > root.val)
                        return lowestCommonAncestor(root.right, p, q);
                else if (p.val < root.val && q.val < root.val)
                        return lowestCommonAncestor(root.left, p, q);
                else
                        return root;
        }

        //Lowest Common Ancestor of a Binary Tree
        public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
                if (root == null)
                        return null;
                TreeNode left = lowestCommonAncestor(root.left,p,q);
                TreeNode right = lowestCommonAncestor(root.right,p,q);
                if (root == p || root ==q)
                        return root;
                else if (left == null && right != null)
                        return right;
                else if (right == null && left != null)
                        return left;
                else if (right == null && left == null)
                        return null;
                else
                        return root;
        }

        //Perfect Squares
        public int numSquares(int n) {
                /*int square = (int)Math.pow(n,0.5);
                if (square * square == n)
                        return 1;
                int max = n;
                int temp;
                for (int i = square; i >=1; i--){
                        temp = numSquares(n-i*i) + 1;
                        max = max >temp ? temp :max;
                        if (max == 2)
                                return 2;
                }
                return max;*/
                int[] dp = new int[n + 1];
                Arrays.fill(dp,Integer.MAX_VALUE);
                for (int i = 1; i * i<=n;i++)
                        dp[i] = 1;
                for (int i = 1; i <= n; i++)
                        for (int j=1; i + j*j <=n;j++)
                                dp[i + j*j] = Math.min(dp[i] + 1,dp[i + j*j]);
                return dp[n];
        }

        //Ugly Number II
        /*
        * (1) 1 * 2, 2 * 2, 3 * 2, 4 * 2
        * (2) 1 * 3, 2 * 3, 3 * 3, 4 * 3
        * (3) 1 * 5, 2 * 5, 3 * 5, 4 * 5
        *
        * */
        public int nthUglyNumber(int n) {
                int[] dp = new int[n];
                dp[0] = 1;
                int minIndexof2=0,minIndexof3=0,minIndexof5=0;
                int f2 = 2,f3 =3, f5 = 5;
                for (int i = 1; i < n;i++){
                        int min = Math.min(f2,Math.min(f3,f5));
                        dp[i] = min;
                        if (f2 == min)
                                f2 = 2 * dp[++minIndexof2];
                        if (f3 == min)
                                f3 = 3 * dp[++minIndexof3];
                        if (f5 == min)
                                f5 = 5 * dp[++minIndexof5];
                }
                return dp[n-1];
        }


        //Number of Digit One
        public int countDigitOne(int n) {
                if(n <= 0)
                        return 0;
                if (n>=1 && n <=9)
                        return 1;
                return countDigitOne(n-1) + helper(n);
        }

        private int helper(int n){
                int sum = 0;
                String s = String.valueOf(n);
                for (int i = 0; i < s.length(); i++){
                        if (s.charAt(i) == '1')
                                sum++;
                }
                return sum;
        }

        //Spiral Matrix
        public List<Integer> spiralOrder(int[][] matrix) {
                List<Integer> result = new ArrayList<>();
                if (matrix == null || matrix.length == 0)
                        return result;
                int top = 0;
                int bottom = matrix.length-1;
                int left = 0;
                int right = matrix[0].length - 1;
                while (true){
                        //1.left-->right
                        for (int j = left; j <= right; j++)
                                result.add(matrix[top][j]);
                        top++;
                        if (helper(top,bottom,left,right))
                                break;
                        //2.top-->bottom
                        for (int i = top; i <= bottom;i++)
                                result.add(matrix[i][right]);
                        right--;
                        if (helper(top,bottom,left,right))
                                break;

                        //3.right --> left
                        for (int j = right; j >=left; j--)
                                result.add(matrix[bottom][j]);
                        bottom--;
                        if (helper(top,bottom,left,right))
                                break;

                        //4.bottom --> top;
                        for (int i = bottom; i >=top; i--){
                                result.add(matrix[i][left]);
                        }
                        left++;
                        if (helper(top,bottom,left,right))
                                break;

                }
                return result;
        }

        private boolean helper(int top,int bottom,int left,int right){
                if (top > bottom || left > right)
                        return true;
                else
                        return false;
        }

        public ListNode reverseListByresume(ListNode root){
                if (root != null){
                        ListNode next = root.next;
                        root.next = null;
                        return reverseList(root,next);
                }
                else
                        return null;
        }

        private ListNode reverseList(ListNode head,ListNode node){
                if (node == null){
                        return head;
                }

                ListNode next = node.next;
                node.next = head;
                return  reverseList(node, next);
        }

        //Validate Binary Search Tree
        public boolean isValidBST(TreeNode root) {
                if (root == null)
                        return true;
                Stack<Integer> order = new Stack<>();
                return isValidBSTHelper(root, order);
        }

        private boolean isValidBSTHelper(TreeNode root,Stack<Integer> order){
                if (root == null)
                        return true;
                boolean left = true,right = true;
                if (root.left != null){
                        left = isValidBSTHelper(root.left,order);
                }
                if (order.size() == 0){
                        order.push(root.val);
                }else if (root.val > order.peek()){
                        order.push(root.val);
                }else {
                        return false;
                }
                if (root.right != null){
                        right = isValidBSTHelper(root.right,order);
                }

                return left && right;
        }

        //Copy List with Random Pointer
        public RandomListNode copyRandomList(RandomListNode head) {
                cloneNodes(head);
                cloneRandom(head);
                return disconnectNode(head);
        }
        //复制原始链表的任意节点并创建新的节点N'，在把N'连接到N的后面
        private void cloneNodes(RandomListNode head){
                while (head!=null){
                        RandomListNode cloneNode = new RandomListNode(head.label);
                        cloneNode.next = head.next;
                        head.next = cloneNode;
                        head = cloneNode.next;
                }
        }
        //如果原始链表的节点N的random指向S，则N'的random指向S'
        private void cloneRandom(RandomListNode head){
                while (head != null){
                        RandomListNode cloneNode = head.next;
                        if (head.random != null){
                                cloneNode.random = head.random.next;
                        }
                        head = cloneNode.next;
                }
        }

        //把前两步中得到的链表拆成两个链表
        private RandomListNode disconnectNode(RandomListNode head){
                RandomListNode cloneHead = null;
                RandomListNode cloneNode = null;
                if (head != null){
                        cloneHead = head.next;
                        cloneNode = cloneHead;
                        head.next = cloneHead.next;
                        head = head.next;
                }
                while (head != null){
                        cloneNode.next = head.next;
                        cloneNode = cloneNode.next;
                        head.next = cloneNode.next;
                        head = head.next;
                }
                return cloneHead;
        }

        //Convert Sorted Array to Binary Search Tree
        public TreeNode sortedArrayToBST(int[] nums) {
                if (nums == null || nums.length == 0)
                        return null;
                return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
        }

        private TreeNode sortedArrayToBSTHelper(int[] nums,int low,int high){
                if (low > high)
                        return null;
                int mid = (low + high) >> 1;
                TreeNode root = new TreeNode(nums[mid]);
                root.left = sortedArrayToBSTHelper(nums,low,mid-1);
                root.right = sortedArrayToBSTHelper(nums,mid+1,high);
                return root;
        }

        //Convert Binary Search Tree to List
        public TreeNode BSTToList(TreeNode root){
                return covert(root, null);
        }

        private TreeNode covert(TreeNode root,TreeNode lastNodeInList){
                if (root == null)
                        return null;
                if (root.left != null){
                        lastNodeInList = covert(root.left,lastNodeInList);
                }
               root.left = lastNodeInList;
                if (lastNodeInList != null)
                        lastNodeInList.right = root;
                lastNodeInList = root;
                if (root.right != null)
                        lastNodeInList = covert(root.right,lastNodeInList);
                return lastNodeInList;
        }

        //Majority Element
        public int majorityElement(int[] num) {
                int result = num[0];
                int times = 1;
                for (int i = 1; i < num.length;i++){
                        if (times == 0){
                                result = num[i];
                                times = 1;
                        }else if (result == num[i]){
                                times++;
                        }else
                                times--;
                }
                return result;
        }

        //Find Minimum in Rotated Sorted Array II
        public int findMin(int[] nums) {
                int index1 = 0, index2 = nums.length - 1;
                int indexMid = index1;
                while (nums[index1] >= nums[index2]) {
                        if (index2 - index1 == 1) {
                                indexMid = index2;
                                break;
                        }
                        indexMid = index1 + ((index2 - index1) >> 1);
                        if (nums[indexMid] >= nums[index1]) {
                                index1 = indexMid;
                        }else if (nums[indexMid] <= nums[index2]) {
                                index2 = indexMid;
                        }
                        if (nums[index1] == nums[index2] && nums[index1] == nums[indexMid])
                                return minInOrder(nums, index1, index2);
                }
                return nums[indexMid];
        }
        private int minInOrder(int[] nums,int index1,int index2){
                int result = nums[index1];
                for (int num:nums){
                        if (num < result)
                                result = num;
                }
                return result;
        }

        public boolean isBalanced(TreeNode root){
                return dfsHeight(root) != -1;
        }

        public int dfsHeight(TreeNode node){
                if (node == null)
                        return 0;
                int left = dfsHeight(node.left);
                if (left == -1)
                        return -1;
                int right = dfsHeight(node.right);
                if (right == -1)
                        return -1;
                if (Math.abs(left - right) > 1)
                        return  -1;
                return left > right ? left + 1 : right + 1;
        }

        //Unique Paths II
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
                if(obstacleGrid == null || obstacleGrid[0].length == 0)
                        return 0;
                int[] result = new int[]{0};
                int n = obstacleGrid.length;
                int m = obstacleGrid[0].length;
                //用obstacleGrig[i][j]表示由（0,0）到（i,j)的路径
                //初始化（0,0），如果obstacleGrid[0][0] = 0,则路径数为1，obstacleGrid[0][0]=1,则路径数为0
                obstacleGrid[0][0] ^= 1;

                //对于第一行
                for (int i = 1; i < m;i++){
                        obstacleGrid[0][i] = obstacleGrid[0][i] == 1 ? 0: obstacleGrid[0][i-1];
                }

                //对于第一列
                for (int i = 1; i < n; i++){
                        obstacleGrid[i][0] = obstacleGrid[i][0] == 1 ? 0:obstacleGrid[i-1][0];
                }

                //对于其他行和其他列
                for (int i = 1; i < n; i++)
                        for (int j =1; j < m; j++ ){
                                obstacleGrid[i][j] = obstacleGrid[i][j] == 1 ? 0 : obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                        }
                return obstacleGrid[n-1][m-1];
        }

        //Spiral Matrix II
        public int[][] generateMatrix(int n) {
                int[][] result = new int[n][n];
                if (n < 0)
                        return null;
                if (n == 0)
                        return result;
                int left = 0,right = n -1,top = 0,bottom = n-1;
                int count = 0;
                while (count <= n * n){
                        for (int i = left; i <= right; i++){
                                result[top][i] = ++count;
                        }
                        top++;
                        if (helper(top,bottom,left,right))
                                break;

                        for (int i = top; i <= bottom; i++){
                                result[i][right] = ++count;
                        }
                        right--;
                        if (helper(top,bottom,left,right))
                                break;

                        for (int i = right; i >=left; i--){
                                result[bottom][i] = ++count;
                        }
                        bottom--;
                        if (helper(top,bottom,left,right))
                                break;

                        for (int i = bottom; i >= top; i--){
                                result[i][left] = ++count;
                        }
                        left++;
                        if (helper(top,bottom,left,right))
                                break;
                }
                return result;
        }

        //Sort Colors
        public void sortColors(int[] nums) {
                if (nums != null){
                        int[] temp = new int[3];
                        for (int num:nums)
                                temp[num]++;
                        int i = 0;
                        Arrays.fill(nums,0,temp[0],0);
                        Arrays.fill(nums,temp[0],temp[0] + temp[1],1);
                        Arrays.fill(nums,temp[0]+temp[1],temp[0]+temp[1]+temp[2],2);
                }
        }

        //Set Matrix Zeroes
        public void setZeroes(int[][] matrix) {
               /* if (matrix != null && matrix[0].length != 0){
                        int n = matrix.length;
                        int m = matrix[0].length;
                       boolean[] cloumn = new boolean[m];
                        boolean[] row = new boolean[n];
                        for (int i = 0 ; i < n; i++)
                                for (int j = 0; j < m; j++){
                                        if (matrix[i][j] == 0){
                                                row[i] = true;
                                                cloumn[j] = true;
                                        }
                                }
                        for (int i = 0; i < n ; i++){
                                if (row[i])
                                        Arrays.fill(matrix[i],0);
                        }
                        for (int j = 0; j < m; j++){
                                if (cloumn[j]){
                                        for (int i = 0; i < n; i++)
                                                matrix[i][j] = 0;
                                }
                        }
                }*/
                int col0 = 1, rows = matrix.length, cols = matrix[0].length;

                for (int i = 0; i < rows; i++) {
                        if (matrix[i][0] == 0) col0 = 0;
                        for (int j = 1; j < cols; j++)
                                if (matrix[i][j] == 0)
                                        matrix[i][0] = matrix[0][j] = 0;
                }

                for (int i = rows - 1; i >= 0; i--) {
                        for (int j = cols - 1; j >= 1; j--)
                                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                                        matrix[i][j] = 0;
                        if (col0 == 0) matrix[i][0] = 0;
                }
        }

        //Maximum Product Subarray
        public int maxProduct(int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                int max_temp = nums[0];
                int min_temp = nums[0];
                int maxProduct = nums[0];
                //子数组乘积最大值的可能性为：累乘的最大值碰到了一个正数；或者，累乘的最小值（负数），碰到了一个负数。
                for (int i = 1; i < nums.length;i++){
                        int a = max_temp * nums[i];
                        int b = min_temp * nums[i];
                        //更新累成的最大数，与累乘的最小值
                        max_temp = Math.max(nums[i],Math.max(a,b));
                        min_temp = Math.min(nums[i], Math.min(a,b));
                        maxProduct = Math.max(max_temp,maxProduct);
                }
                return maxProduct;
        }

        //Best Time to Buy and Sell Stock
        public int maxProfit(int[] prices) {
                if (prices == null || prices.length == 0)
                        return 0;
                int min = prices[0],max = prices[0],maxProfit = 0;
                for (int i = 1; i < prices.length;i++){
                        maxProfit = maxProfit > (max - min) ? maxProfit : max - min;
                        if (prices[i] < min){
                                min = prices[i];
                                max = prices[i];
                        }else {
                                if (prices[i] > max){
                                        max = prices[i];
                                }
                        }
                }
                return  maxProfit > (max - min) ? maxProfit : max - min;
        }

        //Reverse Linked List II
        public ListNode reverseBetween(ListNode head, int m, int n) {
               /* if (head == null){
                        return null;
                }
                ListNode pre_start = null,temp = head,start = head,end = null;
                int i = 1;
                for (; i <= m-1;i++){
                        pre_start = temp;
                        start = temp.next;
                        temp = temp.next;
                }
                for (;i <= n-1;i++){
                        temp = temp.next;
                }
                end = temp;
                ListNode end_behind = end.next;
                end.next = null;
                ListNode root = reverseList(start);
                temp = root;
                while (temp.next != null){
                        temp = temp.next;
                }
                temp.next = end_behind;
                if (pre_start == null)
                        return root;
                else {
                        pre_start.next = root;
                        return head;
                }*/
                if(head == null) return null;
                ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
                dummy.next = head;
                ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
                for(int i = 0; i<m-1; i++) pre = pre.next;

                ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
                ListNode then = start.next; // a pointer to a node that will be reversed

                // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
                // dummy-> 1 -> 2 -> 3 -> 4 -> 5

                for(int i=0; i<n-m; i++)
                {
                        start.next = then.next;
                        then.next = pre.next;
                        pre.next = then;
                        then = start.next;
                }

                // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
                // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

                return dummy.next;

        }

        public ListNode reverseList(ListNode head) {
                if(head == null || head.next == null){
                        return head;
                }
                ListNode tail = new ListNode(0);
                tail = head;
                ListNode next = head.next;
                tail.next = null;
                while(head!=null){
                        head = next.next;
                        next.next = tail;
                        tail = next;
                        if(head!=null)
                                next = head;
                }
                return tail;
        }

        private static void printListNode(ListNode root){
                ListNode head = root;
                while (head != null){
                        System.out.print(head.val + " ");
                        head = head.next;
                }
                System.out.println();
        }

        //Convert Sorted List to Binary Search Tree
        public TreeNode sortedListToBST(ListNode head) {
                if (head == null)
                        return null;
                List<Integer> nums = new ArrayList<>();
                while (head != null){
                        nums.add(head.val);
                        head = head.next;
                }
                return generateTreeNode(nums,0,nums.size() - 1);
        }

        private TreeNode generateTreeNode(List<Integer> nums,int low,int high){
                if (low > high)
                        return null;
                int mid = (low + high) / 2;
                TreeNode root = new TreeNode(nums.get(mid));
                root.left = generateTreeNode(nums,low,mid-1);
                root.right = generateTreeNode(nums,mid + 1,high);
                return root;
        }

        //Super Ugly Number
        public int nthSuperUglyNumber(int n, int[] primes) {
                int[] index = new int[primes.length];
                int[] dp = new int[n];
                dp[0] = 1;
                for (int i = 1; i <= n-1;i++){
                        dp[i] =Integer.MAX_VALUE;
                        for (int j = 0; j < primes.length;j++){
                                dp[i] = Math.min(dp[i],primes[j] * dp[index[j]]);
                        }
                        for (int j = 0; j < primes.length;j++){
                                if (dp[i] == primes[j] * dp[index[j]]){
                                        index[j]++;
                                }
                        }
                }
                return dp[n-1];
        }

        //Minimum Size Subarray Sum
        public int minSubArrayLen(int s, int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                if (nums.length == 1)
                        return nums[0] == s ? 1:0;
                int p1 = 0, p2 = 0;
                int sum = nums[0];
                int minLen = Integer.MAX_VALUE;
                while (p2 < nums.length){
                        if (sum < s){
                                p2++;
                                if (p2 >= nums.length)
                                        break;
                                else
                                        sum += nums[p2];
                        }else{
                                minLen = minLen < p2-p1 + 1?minLen:p2-p1 + 1;
                                sum -= nums[p1];
                                p1++;
                        }
                }
                return minLen == Integer.MAX_VALUE ? 0: minLen;
        }

        //Simplify Path
        public String simplifyPath(String path) {
                if (path == null || path.length() == 0)
                        return null;
                Stack<String> stack = new Stack<>();
                for (String dir : path.split("/")){
                        if (dir.equals("..") && !stack.isEmpty())
                                stack.pop();
                        else if (!dir.equals(".") && !dir.equals("") && !dir.equals(".."))
                                stack.push(dir);
                }

                StringBuilder builder = new StringBuilder("/");
                for (String s : stack){
                        builder.append(s).append("/");
                }
                if (!stack.isEmpty())
                        builder.setLength(builder.length() - 1);
                return builder.toString();
        }



        //Decode Ways
        /*public int numDecodings(String s) {
                if (s == null || s.isEmpty() || s.charAt(0) == '0')
                        return 0;
                String[] tokens = s.split("0");
                int sum = 1;
                for (int i = 0; i < tokens.length - 1;i++){
                        int temp = count(tokens[i] + "0");
                        if (temp == 0)
                                return 0;
                        else {
                                sum *= temp;
                        }
                }
                if (s.endsWith("0"))
                        return sum * count(tokens[tokens.length - 1] + "0");
                else
                        return sum * count(tokens[tokens.length - 1]);
        }

        public int count(String s){
                int sum = 1;
                for (int i = 1; i < s.length(); i++){
                        if (s.charAt(i) == '0'){
                                if (s.charAt(i-1) > '2')
                                        return 0;
                                else {
                                        sum--;
                                }
                        }else {
                                int temp = Integer.parseInt(s.substring(i-1,i+1));
                                sum = temp > 26 ? sum : sum + 1;
                        }
                }
                return sum;
        }*/
        /*
        * 类似爬楼梯
        * dp[i] = dp[i-1] + dp[i-2]
        * 只不过本题限制更多
        * (1) s[i-1] 不能为0, 若为0，则dp[i] = dp[i-2]
        * (2) s[i-2,i]的首字母不能为0，且parseInt(s.subString(i-2,i))要在1-26之间
        * */
        public int numDecodings(String s) {
                if (s.length()==0||s==null||s=="0")
                        return 0;

                int[] dp = new int[s.length()+1];
                dp[0] = 1;

                if (isValid(s.substring(0,1)))
                        dp[1] = 1;
                else
                        dp[1] = 0;

                for(int i=2; i<=s.length();i++){
                        //对应(1)
                        if (isValid(s.substring(i-1,i)))
                                dp[i] += dp[i-1];
                        //对应(2)
                        if (isValid(s.substring(i-2,i)))
                                dp[i] += dp[i-2];
                }
                return dp[s.length()];
        }

        public boolean isValid(String s){
                if (s.charAt(0)=='0')
                        return false;
                int code = Integer.parseInt(s);
                return code>=1 && code<=26;
        }

        // Integer to Roman
        public String intToRoman(int num) {
                String result = "";
                if (num <= 0 || num > Integer.MAX_VALUE)
                        return result;
                int[] val = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
                String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
                for (int i = 0; i < roman.length;i++){
                        while (num >= val[i]){
                                result += roman[i];
                                num -= val[i];
                        }
                }
                return result;
        }

        //Kth Smallest Element in a BST
        public int kthSmallest(TreeNode root, int k) {
                if (root == null)
                        return 0;
                int leftSize = calculateTreeSize(root.left);
                if (k == leftSize + 1)
                        return root.val;
                else if (leftSize >= k)
                        return kthSmallest(root.left,k);
                else
                        return kthSmallest(root.right,k-leftSize-1);
        }

        private int calculateTreeSize(TreeNode node){
                if (node == null)
                        return 0;
                return 1 + calculateTreeSize(node.left) + calculateTreeSize(node.right);
        }

        //Unique Binary Search Trees

        /**
         *G(0) = 1
         * G(n) = G(0)*G(n-1) + G(1) * G(n-2) + .... + G(n-1) * G(0)
         */
        public int numTrees(int n) {
                int[] dp = new int[n+1];
                dp[0] = dp[1] = 1;
                for (int i = 2; i <=n;i++){
                      for (int j = i; j >= 1;j--){
                              dp[i]  += dp[j-1] * dp[i-j];
                      }
                }
                return dp[n];
        }



        //Unique Binary Search Trees II
        public List<TreeNode> generateTrees(int n) {
                if (n < 1)
                        return new ArrayList<>();
                else
                        return getTree(1,n);
        }

        private List<TreeNode> getTree(int start,int end){
                List<TreeNode> result = new ArrayList<>();
                if (start > end){
                        result.add(null);
                        return result;
                }
                if (start == end){
                        TreeNode root = new TreeNode(start);
                        result.add(root);
                        return result;
                }

                for (int i = start; i <= end; i++){
                        List<TreeNode> left = getTree(start,i-1);
                        List<TreeNode> right = getTree(i+1,end);
                        for (TreeNode lnode:left){
                                for (TreeNode rnode:right){
                                        TreeNode root = new TreeNode(i);
                                        root.left = lnode;
                                        root.right = rnode;
                                        result.add(root);
                                }
                        }
                }
                return result;
        }

        //Power of Three
        public boolean isPowerOfThree(int n) {
                return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
        }

        //Maximal Square
        public int maximalSquare(char[][] matrix) {
                if (matrix == null || matrix.length == 0)
                        return 0;
                int n = matrix.length;
                int m = matrix[0].length;
                int max = 0;
                int size[][] = new int[n][m];
                for (int i = 0; i < m; i++){
                        size[0][i] = matrix[0][i] - '0';
                        max = Math.max(max,size[0][i]);
                }
                for (int  i = 0; i < n; i++){
                        size[i][0] = matrix[i][0] - '0';
                        max = Math.max(max,size[i][0]);
                }

                for (int i = 1 ; i < n; i++){
                        for (int j = 1; j < m; j++){
                                if (matrix[i][j] == '1'){
                                        size[i][j] = Math.min(Math.min(size[i][j-1],size[i-1][j-1]),size[i-1][j]) + 1;
                                        max = Math.max(size[i][j],max);
                                }
                        }
                }
                return max * max;
        }


        //House Robber
        public int rob(int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                int[] dp = new int[nums.length + 1];
                dp[1] = nums[0];
                for (int i = 1; i < nums.length;i++)
                        dp[i +1] = Math.max(dp[i],nums[i] + dp[i-1]);
                return dp[nums.length];
        }

        //House Robber II
        public int rob2(int[] nums){
                if (nums == null || nums.length == 0)
                        return 0;
                if (nums.length == 1)
                        return nums[0];
                return Math.max(rob(Arrays.copyOfRange(nums, 0, nums.length - 1)),rob(Arrays.copyOfRange(nums,1,nums.length)));
        }

        //Coin Change
        public int coinChange(int[] coins, int amount) {
                if (coins == null || coins.length == 0)
                        return -1;
                int[] dp = new int[amount + 1];
                Arrays.fill(dp,Integer.MAX_VALUE);
                dp[0] = 0;
                for (int i = 1; i <= amount; i++){
                        for (int j = 0; j < coins.length;j++){
                                 if (i-coins[j] >= 0 && dp[i-coins[j]] + 1 < dp[i] && dp[i - coins[j]] != -1)
                                         dp[i] = dp[i-coins[j]] + 1;
                        }
                        if (dp[i] == Integer.MAX_VALUE)
                                dp[i] = -1;
                }
                return dp[amount];
        }

        //Odd Even Linked List
        public ListNode oddEvenList(ListNode head) {
                if(head == null || head.next == null)
                        return head;
                ListNode odd_fail = head;
                ListNode even_head = head.next, temp = head.next;
                while (odd_fail.next != null && temp.next != null){
                        odd_fail.next = temp.next;
                        odd_fail = odd_fail.next;
                        temp.next = odd_fail.next;
                        temp = temp.next;
                }
                odd_fail.next = even_head;
                return head;
        }

        //Remove Duplicate Letters
        public String removeDuplicateLetters(String s) {
                if (s == null || s.isEmpty())
                        return  s;
                Stack<Character> stack = new Stack<>();
                int[] sum = new int[26];
                boolean[] visited = new boolean[26];

                //统计字符出现的个数
                for (int i = 0; i < s.length(); i++){
                        sum[s.charAt(i) - 'a']++;
                }
                for (int i = 0; i <s.length();i++){
                        char ch = s.charAt(i);
                        sum[ch - 'a'] --;
                        if (visited[ch-'a'])
                                continue;
                        //当前字符小于栈顶字符，并且后面还会出现栈顶字符，则弹出栈顶元素
                        while (!stack.isEmpty() && stack.peek() > ch && sum[stack.peek() -'a'] > 0){
                                visited[stack.pop() - 'a'] = false;
                        }
                        stack.push(ch);
                        visited[ch-'a'] = true;
                }
                StringBuilder builder = new StringBuilder();
                while (!stack.isEmpty()){
                        builder.append(stack.pop());
                }
                return builder.reverse().toString();
        }


        //Binary Tree Zigzag Level Order Traversal
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                List<List<Integer>> result = new ArrayList<>();
                Stack<TreeNode> stack = new Stack<>();
                Stack<TreeNode> temp = new Stack<>();
                int height = 1;
                if (root == null)
                        return result;
                stack.push(root);
                while (!stack.isEmpty()){
                        List<Integer> list = new ArrayList<>();
                        while (!stack.isEmpty()){
                                TreeNode node = stack.pop();
                                list.add(node.val);
                                if (node.left != null)
                                        temp.push(node.left);
                                if (node.right != null)
                                        temp.push(node.right);
                        }
                        result.add(list);
                        while (!temp.isEmpty()){
                                stack.push(temp.pop());
                        }
                }
                for (int i = 0; i< result.size(); i++){
                        if (i % 2 != 0)
                                Collections.reverse(result.get(i));
                }
                return result;
        }


        //Best Time to Buy and Sell Stock II
        public int maxProfit2(int[] prices) {
                if (prices == null || prices.length == 0)
                        return 0;
                int profit = 0;
                for (int i = 1; i < prices.length; i++){
                        profit += Math.max(prices[i] - prices[i-1],0);
                }
                return profit;
        }

        private int maxProfitHelp(int[] prices){
                if (prices == null || prices.length == 0){
                        return 0;
                }
                int max = 0;
                int target = prices[0];
                for (int i = 1; i < prices.length; i++){
                        if (prices[i] - target > max)
                                return prices[i] - target + maxProfitHelp(Arrays.copyOfRange(prices,i+1,prices.length));
                }
                return max;
        }

        //Wiggle Sort II
        public void wiggleSort(int[] nums) {
                Arrays.sort(nums);
                int length = nums.length % 2 == 0 ? nums.length /2 : nums.length /2 + 1;
                int[] small = Arrays.copyOf(nums,length);
                int[] large = Arrays.copyOfRange(nums,length,nums.length);
                int s = small.length - 1, l = large.length -1,j=0;
                for (;l>=0;s--,l--,j+=2){
                        nums[j] = small[s];
                        nums[j+1] = large[l];
                }
                if (nums.length % 2 != 0){
                        nums[nums.length -1] = small[0];
                }
        }

        //Self Crossing
        public boolean isSelfCrossing(int[] x) {
                if (x == null || x.length < 4)
                        return false;
                int n = 0,w = 0, s = 0, e = 0;
                int i = 0;
                while (i < x.length){
                        if (i < x.length){
                                n += x[i];
                                i++;
                        }
                        if (i < x.length){
                                w += x[i];
                                i++;
                        }
                        if (i < x.length){
                                s += x[i];
                                i++;
                        }
                        if (i < x.length){
                               e += x[i];
                                i++;
                        }
                }

                return n >= s && e >= w;
        }

        //Longest Increasing Path in a Matrix
        public int longestIncreasingPath(int[][] matrix) {
                if (matrix == null || matrix.length == 0)
                        return 0;
                int n = matrix.length, m = matrix[0].length;
                int[][] record = new int[n][m];
                int longest = 0;
                for (int i = 0; i < n;i++)
                        for (int j = 0; j < m ; j++)
                                longest = Math.max(longest,dfs(matrix,record,i,j,Integer.MIN_VALUE));
                for (int[] arr:record){
                        System.out.println(Arrays.toString(arr));
                }
                return longest;
        }

        private int dfs(int[][] matrix, int[][] record,int x, int y,int lastval){
                if (x< 0 || x >= matrix.length || y< 0 || y >= matrix[0].length)
                        return 0;
                if (matrix[x][y] > lastval){
                        if (record[x][y] != 0)
                                return record[x][y];
                        int left = dfs(matrix,record,x-1,y,matrix[x][y]) + 1;
                        int right = dfs(matrix,record,x+1,y,matrix[x][y]) + 1;
                        int up = dfs(matrix,record,x,y-1,matrix[x][y]) + 1;
                        int down = dfs(matrix,record,x,y+1,matrix[x][y]) + 1;
                        record[x][y] = Math.max(left,Math.max(right,Math.max(up,down)));
                        return record[x][y];
                }
                return 0;
        }

        //Maximum Product of Word Lengths
        public int maxProduct(String[] words) {
                /*if (words == null || words.length == 0)
                        return 0;
                int max = 0;
                for (int i = 0; i < words.length ; i++)
                        for (int j = i + 1; j < words.length; j++){
                                if (!isContainsCommonLetter(words[i],words[j])){
                                        max = Math.max(max,words[i].length() * words[j].length());
                                }
                        }
                return max;*/

                //function 1
                /*int max = 0;
                if (words == null || words.length == 0)
                        return 0;
                int[] bits = new int[words.length];
                for (int i = 0; i < words.length; i++){
                        for (char c : words[i].toCharArray())
                                bits[i] |= 1 << (c-'a');
                }
                System.out.println(Arrays.toString(bits));
                for (int i = 0; i < words.length; i++){
                        if (words[i].length() * words[i].length() <= max)
                                break;
                        for (int j = i + 1; j < words.length; j ++)
                                if ((bits[i] & bits[j]) == 0){
                                        max = Math.max(max,words[i].length() * words[j].length());
                                }
                }
                return max;*/

                //function2
                int max = 0;
                BitSet[] bits = new BitSet[words.length];
                for (int i = 0 ; i < words.length ; i++){
                        bits[i] = new BitSet(26);
                        for (char c : words[i].toCharArray())
                                bits[i].set(c-'a',true);
                }

                for (int i = 0; i < words.length; i++){
                        if (words[i].length() * words[i].length() <= max)
                                break;
                        for (int j = i + 1; j < words.length; j ++){
                                BitSet temp = (BitSet) bits[i].clone();
                                temp.and(bits[j]);
                                if (temp.cardinality() == 0){
                                        max = Math.max(max,words[i].length() * words[j].length());
                                }
                        }

                }
                return max;
        }

        public boolean isContainsCommonLetter(String str1,String str2){
                BitSet b1 = new BitSet(26);
                BitSet b2 = new BitSet(26);
                for (char ch : str1.toCharArray()){
                        b1.set(ch-'a',true);
                }
                for (char ch:str2.toCharArray()){
                        b2.set(ch-'a',true);
                }
               b1.and(b2);

                return b1.cardinality() != 0;
        }

        //Bulb Switcher
        public int bulbSwitch(int n) {
                /*if (n <= 0)
                        return 0;
                BitSet bitSet = new BitSet(n);
                for (int i = 1; i <= n; i++){
                        for (int j = 1; j * i <=n;j ++){
                                bitSet.set(j*i,!bitSet.get(j*i));
                        }
                }
                return bitSet.cardinality();*/
                return (int)Math.floor(Math.sqrt(n));
        }

        //Count of Smaller Numbers After Self
        public List<Integer> countSmaller(int[] nums) {
                /*List<Integer> res = new ArrayList<>();
                if(nums == null || nums.length == 0)
                        return res;
                int[] counter = new int[nums.length];
                for (int i = nums.length - 1; i > 0; i--){
                        for (int j = i-1; j >=0; j--){
                                if (nums[j] > nums[i])
                                        counter[j]++;
                        }
                }
                for (int i : counter)
                        res.add(i);
                return res;*/
                List<Integer> res = new ArrayList<>();
                List<Integer> sorted = new ArrayList<>();
                for (int i = nums.length - 1; i >=0; i--){
                        int index = findIndex(sorted,nums[i]);
                        res.add(index);
                        sorted.add(index,nums[i]);
                }
                Collections.reverse(res);
                return res;
        }

        private int findIndex(List<Integer> sorted,int target){
                int start = 0, end = sorted.size()-1;
                if (sorted.size() == 0)
                        return 0;
                if (target <= sorted.get(start))
                        return 0;
                if (target > sorted.get(end))
                        return end + 1;
                while (start + 1 < end){
                        int mid = start + (end - start)/2;
                        if (target > sorted.get(mid))
                                start = mid + 1;
                        else
                                end = mid;
                }
                if (sorted.get(start) >= target)
                        return start;
                return end;
        }


        public static void main(String[] args){
                LeetCode test = new LeetCode();
                TreeNode node1 = new TreeNode(3);
                TreeNode node2 = new TreeNode(1);
                TreeNode node3 = new TreeNode(5);
                TreeNode node4 = new TreeNode(4);
                TreeNode node5 = new TreeNode(6);
                node1.left = node2;
                node1.right = node3;
                node3.left = node4;
                node3.right = node5;

                /*ListNode listNode1 = new ListNode(1);
                ListNode listNode2 = new ListNode(2);
                ListNode listNode3 = new ListNode(3);
                ListNode listNode4 = new ListNode(4);
                ListNode listNode5 = new ListNode(5);
                listNode1.next = listNode2;
                listNode2.next = listNode3;
                listNode3.next = listNode4;
                listNode4.next = listNode5;

                int[] postorder = {4,5,2,3,1};
                int[] inorder = {4,2,5,1,3};
                int[] nums = {1,0,0,0,0};
                char[][] board = new char[][]{{'A','B','C','E'},
                                                                {'S','F','C','S'},
                                                                {'A','D','E','E'}};
                int[][] martix = new int[][]{
                        {1,   4,  7, 11, 15},
                        {2,   5,  8, 12, 19},
                        {3,   6,  9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}
                };*/
        }
}
class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
}
class ListNode {
        int val;
        ListNode next;
        ListNode(int x){ val = x ;}
}
class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
}
class WordDictionary{
        private Node root = new Node();
        class Node{
                public Node(){};
                public Node(char val){
                        this.val = val;
                }
                private Node[] children = new Node[26];
                private boolean isWord = false;
                private char val;
                public boolean add(Node node){
                        if (children[node.val-'a'] == null){
                                children[node.val-'a'] = node;
                                return true;
                        }
                        return false;
                }
        }

        public void addWord(String word){
                Node temp = root;
                int i = 0;
                char val = word.charAt(i);
                while (!temp.add(new Node(val)) || i < word.length()){
                        temp = temp.children[val-'a'];
                        i = i + 1;
                        if (i >= word.length()){
                                break;
                        }
                        val = word.charAt(i);
                }
                temp.isWord = true;
        }

        public boolean search(String word){
                return dfsSearch(word.toCharArray(),0,root);
        }

        private boolean dfsSearch(char[] word, int index,Node node){
                if (word.length == index){
                        return node.isWord;
                }

                char val = word[index];
                if (val == '.'){
                        for (int i = 0; i < 26; i++){
                                Node child = node.children[i];
                                if (child != null){
                                        if (dfsSearch(word,index  + 1,child)){
                                                return true;
                                        }
                                }
                        }
                        return false;
                }
                if (node.children[val-'a'] == null)
                        return false;
                return dfsSearch(word,++index,node.children[val -'a']);
        }
}

class PeekingIterator implements Iterator<Integer> {
        int cusor = 0;
        List<Integer> list = new ArrayList<>();
        public PeekingIterator(Iterator<Integer> iterator) {
                // initialize any member here.
                while (iterator.hasNext()){
                        list.add(iterator.next());
                }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
               return list.get(cusor);
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
               Integer value =  list.get(cusor);
                cusor++;
                return value;
        }

        @Override
        public void remove() {

        }

        @Override
        public boolean hasNext() {
                return cusor < list.size();
        }
}

class MyQueue{
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();
        // Push element x to the back of queue.
        public void push(int x) {
                stack1.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
                if (stack2.isEmpty()){
                        while (!stack1.isEmpty())
                                stack2.push(stack1.pop());
                }
                stack2.pop();
        }

        // Get the front element.
        public int peek() {
                if (stack2.isEmpty()){
                        while (!stack1.isEmpty())
                                stack2.push(stack1.pop());
                }
                return stack2.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
                return stack1.isEmpty() && stack2.isEmpty();
        }
}

class MyStack {
        // Push element x onto stack.
        int size = 0;
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        public void push(int x) {
                if (!queue1.isEmpty()){
                        queue1.offer(x);
                }else
                        queue2.offer(x);
                size++;
        }

        // Removes the element on top of the stack.
        public void pop() {
                if (queue1.isEmpty()){
                        int i = 0;
                        while (i < size-1){
                                queue1.offer(queue2.poll());
                                i++;
                        }
                        queue2.poll();
                }else {
                        int i = 0;
                        while (i < size -1){
                                queue2.offer(queue1.poll());
                                i++;
                        }
                        queue1.poll();
                }
                size--;
        }

        // Get the top element.
        public int top() {
                if (queue1.isEmpty()){
                        int i = 0;
                        while (i < size-1){
                                queue1.offer(queue2.poll());
                                i++;
                        }
                        queue1.offer(queue2.peek());
                        return queue2.poll();
                }else {
                        int i = 0;
                        while (i < size -1){
                                queue2.offer(queue1.poll());
                                i++;
                        }
                        queue2.offer(queue1.peek());
                        return queue1.poll();
                }
        }

        // Return whether the stack is empty.
        public boolean empty() {
                return size == 0;
        }
}

class MinStack{
        Stack<Integer> dataStack = new Stack<>();
        Stack<Integer> helpStack = new Stack<>();
        public void push(int x) {
                dataStack.push(x);
               if (helpStack.size() == 0 || x < helpStack.peek())
                       helpStack.push(x);
                else
                       helpStack.push(helpStack.peek());
        }

        public void pop() {
               helpStack.pop();
                dataStack.pop();
        }

        public int top() {
                return dataStack.peek();
        }

        public int getMin() {
                return helpStack.peek();
        }
}


