package com.wbl.leetcode;

import javax.xml.stream.events.Characters;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created withSimple_love
 * Date: 2016/3/2.
 * Time: 21:46
 */
public class LeetCode2 {
        //1. Two Sum
        public int[] twoSum(int[] nums, int target) {
                int[] result = new int[2];
                if (nums == null)
                        return result;
                Map<Integer,Integer> map = new HashMap<>();
                for (int i = 0; i < nums.length; i++){
                        if (map.containsKey(nums[i])){
                                result[0] = map.get(nums[i]);
                                result[1] = i;
                                return result;
                        }else {
                                map.put(target-nums[i],i);
                        }
                }
                return result;
        }

        //2. Add Two Numbers
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
                ListNode newHead = new ListNode(0),temp = newHead;
                int count = 0;
                while (l1 != null && l2 !=null){
                        int val = (l1.val + l2.val + count)%10;
                        count = (l1.val + l2.val + count)/10;
                        temp.next = new ListNode(val);
                        temp = temp.next;
                        l1 = l1.next;
                        l2 = l2.next;
                }
                while (l1!=null){
                        int val = (l1.val + count)%10;
                        count = (l1.val + count)/10;
                        temp.next = new ListNode(val);
                        temp = temp.next;
                        l1 = l1.next;
                }
               while (l2!=null){
                        int val = (l2.val + count)%10;
                        count = (l2.val + count)/10;
                        temp.next = new ListNode(val);
                        temp = temp.next;
                        l2 = l2.next;
                }
                if (count!=0)
                        temp.next = new ListNode(count);
                return newHead.next;
        }

        //3. Longest Substring Without Repeating Characters
        public int lengthOfLongestSubstring(String s) {
                if (s == null || s.length() == 0)
                        return 0;
                Map<Character,Integer> map = new HashMap<>();
                int max = 1,temp = 0,index = 0;
                for (int i = 0;i < s.length();i++){
                        char ch = s.charAt(i);
                        if (map.containsKey(ch)){
                                if (map.get(ch) >= index)
                                        temp = i-map.get(ch);
                                else
                                        temp++;
                                index =Math.max(index,map.get(ch));
                        }else{
                                temp++;
                        }
                        map.put(ch,i);
                        max = Math.max(max,temp);
                }
                return max;
        }

        //4. Median of Two Sorted Arrays
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
                if (nums1.length > nums2.length){
                        int[] temp = nums1;
                        nums1 = nums2;
                        nums2 = temp;
                }
                int m = nums1.length,n = nums2.length;
                int low = 0, high = m;
                int i ,j;
                while (low <= high){
                        i = low + (high-low)/2;
                        j = (m+n + 1)/2 -i;
                        if (i > 0 &&  j <  n  && nums1[i-1] > nums2[j])
                                high = i - 1;
                        else if (i  < m  &&  j > 0  && nums1[i] < nums2[j-1])
                                low = i+1;
                        else {
                                int max_left,min_right;
                                if (i == 0)
                                        max_left = nums2[j-1];
                                else if (j == 0)
                                        max_left = nums1[i-1];
                                else
                                        max_left = Math.max(nums1[i-1],nums2[j-1]);
                                if ((m + n) % 2 == 1)
                                        return max_left;
                                if (i == m)
                                        min_right = nums2[j];
                                else if (j == n)
                                        min_right = nums1[i];
                                else
                                        min_right = Math.min(nums1[i],nums2[j]);

                                return (max_left + min_right) /2.0;
                        }
                }
                if (nums2.length % 2 == 0){
                        return (nums2[n/2] + nums2[n/2-1])/2.0;
                }else
                        return nums2[n/2];
        }

        //6. ZigZag Conversion
        public String convert(String s, int numRows) {
                if (numRows == 1)
                        return s;
                List<StringBuilder> row = new ArrayList<>();
                for (int i = 0; i < numRows;i++){
                        row.add(new StringBuilder());
                }
                int i = 0,line = 0;
               while (i < s.length()){
                        while (line < numRows -1&& i < s.length()){
                                row.get(line).append(s.charAt(i));
                                line++;
                                i++;
                        }
                       while (line >=1 && i < s.length()){
                               row.get(line).append(s.charAt(i));
                               i++;
                               line--;
                       }
                }
                StringBuilder result = new StringBuilder();
                for (StringBuilder builder:row){
                        result.append(builder);
                }
                return result.toString();
        }

        //7. Reverse Integer
        public int reverse(int x) {
                int sign = 1;
                if (x < 0)
                        sign = -1;
                int sum = 0;
                while (x!=0){
                        if (sum > Integer.MAX_VALUE/10 || sum > (Integer.MAX_VALUE-(x%10)*sign)/10)
                                return 0;
                        sum = sum * 10 + (x%10)*sign;
                        x /= 10;
                }
                return sum*sign;
        }

        //8. String to Integer (atoi)
        public int myAtoi(String str) {
                if (str == null || str.length() == 0)
                        return 0;
                int flag=1;
                str = str.trim();
                if (str.charAt(0) == '-')
                        flag = -1;
                if (str.charAt(0) != '+' && str.charAt(0) !='-' && str.charAt(0)-'0' < 0 && str.charAt(0)-'0' > 9)
                        return 0;
                int sum = 0;
                for (int i = str.length()-1,j=0;i >=0;i--){
                        if (str.charAt(i) == ' ')
                                continue;
                        int val = str.charAt(i)-'0';
                        if (val < 0 || val >9){
                                if ((str.charAt(i) == '+' || str.charAt(i)=='-') && i==0)
                                        continue;
                                else {
                                        sum=0;
                                        j=0;
                                }
                                continue;
                        }
                        sum += val * Math.pow(10,j)*flag;
                        j++;
                }
                return sum;
        }

        //9. Palindrome Number
        public boolean isPalindrome(int x) {
                if (x < 0)
                        return false;
                int temp = x;
                int sum = 0;
                while (x!=0){
                        sum = sum*10 + x%10;
                        x /= 10;
                }
                return temp == sum;
        }

        //11. Container With Most Water
        public int maxArea(int[] height) {
                if (height == null || height.length < 2)
                        return 0;
                int left = 0,right = height.length-1;
                int result = 0;
                while (left < right){
                        int temp = right-left;
                        result = Math.max(result,temp * Math.min(height[left],height[right]));
                        if (height[left] < height[right])
                                left++;
                        else
                                right--;
                }
                return result;
        }

        //12. Integer to Roman
        public String intToRoman(int num) {
                StringBuilder builder = new StringBuilder();
                int[] val = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
                String[] str = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
                for (int i = 0; i < val.length;i++){
                        while (num >= val[i]){
                                builder.append(str[i]);
                                num-=val[i];
                        }
                }
                return builder.toString();
        }

        //13. Roman to Integer
        public int romanToInt(String s) {
                if (s == null || s.length() == 0)
                        return 0;
                int[] val = new int[s.length()];
                for (int i = 0; i < s.length(); i++){
                        char ch = s.charAt(i);
                        switch (ch){
                                case 'I':
                                        val[i] = 1;
                                        break;
                                case 'V':
                                        val[i] = 5;
                                        break;
                                case 'X':
                                        val[i] = 10;
                                        break;
                                case 'L':
                                        val[i] = 50;
                                        break;
                                case 'C':
                                        val[i] = 100;
                                        break;
                                case 'D':
                                        val[i] = 500;
                                        break;
                                case 'M':
                                        val[i] = 1000;
                                        break;

                        }
                }
                int sum = 0;
                for (int i = 0; i < s.length();i++){
                        if (i+1<s.length()&&val[i] < val[i+1]){
                                val[i] *= -1;
                        }
                        sum += val[i];
                }
                return sum;
        }

        //14. Longest Common Prefix
        public String longestCommonPrefix(String[] strs) {
                if (strs == null || strs.length == 0)
                        return "";
                String target = strs[0];
                for (int i = 1; i < strs.length;i++){
                        int j = 0;
                        while (j < target.length() && j < strs[i].length()
                                && target.charAt(j) == strs[i].charAt(j)){
                                j++;
                        }
                        target = target.substring(0,j);
                }
                return target;
        }

        //15. 3Sum
        public List<List<Integer>> threeSum(int[] nums) {
                if (nums == null || nums.length == 0)
                        return new ArrayList<>();
                List<List<Integer>> res = new ArrayList<>();
                boolean used[] = new boolean[nums.length];
                Arrays.sort(nums);
                threeSumHelper(nums, 0, 0, used, new ArrayList<Integer>(), res);
                return res;
        }

        private void threeSumHelper(int[] nums,int target,int start,boolean[] used,List<Integer> list, List<List<Integer>> res){
                if (list.size() == 3){
                        if (target == 0)
                                res.add(new ArrayList<Integer>(list));
                        return;
                }
                for (int i = start; i < nums.length;i++){
                        if (i >0 && nums[i] == nums[i-1] && !used[i-1])
                                continue;
                        list.add(nums[i]);
                        used[i] = true;
                        threeSumHelper(nums,target+nums[i],i+1,used,list,res);
                        list.remove(list.size() - 1);
                        used[i] = false;
                }
        }

        //16. 3Sum Closest
        public int threeSumClosest(int[] nums, int target) {
                Arrays.sort(nums);
                int result=nums[0] + nums[1] + nums[2];
                for (int i = 0; i < nums.length-2;i++){
                        if (i > 0 && nums[i] == nums[i-1])continue;
                        int low = i+1,high = nums.length-1;
                        while (low < high){
                                int sum = nums[i] + nums[low] + nums[high];
                                if (Math.abs(target-result) > Math.abs(target-sum)){
                                        result = sum;
                                        if (target == sum)
                                                return sum;
                                }
                                if (sum > target)
                                        high--;
                                else
                                        low++;
                        }
                }
                return result;
        }

        //17. Letter Combinations of a Phone Number
        public List<String> letterCombinations(String digits) {
                String[] val = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
                List<String> result = new ArrayList<>();
                if (digits == null || digits.isEmpty())
                        return result;
                letterCombinationsHelper(0,digits,val,new StringBuilder(),result);
                return result;
        }

        private void letterCombinationsHelper(int index,String digits,String[] val,StringBuilder builder,List<String> result){
                if (index == digits.length()){
                        result.add(builder.toString());
                        return;
                }
                int digit = digits.charAt(index)-'0';
                for (int i = 0; i < val[digit].length();i++){
                        builder.append(val[digit].charAt(i));
                        letterCombinationsHelper(index + 1, digits, val, builder, result);
                        builder.deleteCharAt(builder.length()-1);
                }
        }

        //18. 4Sum
        /*public List<List<Integer>> fourSum(int[] nums, int target) {
                List<List<Integer>> res = new ArrayList<>();
                if(nums == null || nums.length == 0)
                        return res;
                Arrays.sort(nums);
                for (int i = 0; i < nums.length-3;i++){
                        if (i > 0 && nums[i] == nums[i-1])continue;
                        for (int j = i+1; j < nums.length-2;j++){
                                if (j > i+1&& nums[j] == nums[j-1])continue;
                                int low = j+1,high = nums.length-1;
                                while (low < high){
                                        int sum = nums[i] + nums[j] + nums[low] + nums[high];
                                        if (sum == target){
                                                res.add(Arrays.asList(nums[i],nums[j],nums[low],nums[high]));
                                                while (low < high && nums[low] == nums[low+1])
                                                        low++;
                                                while (low < high && nums[high] == nums[high-1])
                                                        high--;
                                                low++;
                                                high--;
                                        }
                                        else if (sum > target)
                                                high--;
                                        else
                                                low++;
                                }
                        }
                }
                return res;
        }*/


        //19. Remove Nth Node From End of List
        public ListNode removeNthFromEnd(ListNode head, int n) {
                if (head == null)
                        return null;
                ListNode newHead = new ListNode(0);
                newHead.next = head;
                ListNode temp = head;
                while (n!=0){
                        temp = temp.next;
                        n--;
                }
                ListNode pre = newHead;
                while (temp!=null){
                        temp = temp.next;
                        pre = pre.next;
                }
                pre.next = pre.next.next;
                return newHead.next;
        }

        //20. Valid Parentheses
        public boolean isValid(String s) {
                if(s == null)
                        return false;
                Stack<Character> stack = new Stack<>();
                for (int i = 0; i < s.length(); i++){
                        char ch = s.charAt(i);
                        if (ch == '(' || ch == '[' || ch == '{')
                                stack.push(ch);
                        else {
                                if (stack.isEmpty())
                                        return false;
                                if(ch == ')'){
                                        if (stack.peek() == '(')
                                                stack.pop();
                                        else
                                                return false;
                                }
                                if(ch == ']' ){
                                        if (stack.peek() == '[')
                                                stack.pop();
                                        else
                                                return false;
                                }

                                if(ch == '}'){
                                        if (stack.peek() == '{')
                                                stack.pop();
                                        else
                                                return false;
                                }
                        }
                }
                return stack.isEmpty();
        }

        //21. Merge Two Sorted Lists
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
                ListNode newHead = new ListNode(0),temp = newHead;
                while (l1 != null && l2 != null){
                        if (l1.val > l2.val){
                                temp.next = l2;
                                l2 = l2.next;

                        }else {
                                temp.next = l1;
                                l1 = l1.next;
                        }
                        temp = temp.next;
                        temp.next = null;
                }
                if (l1!=null){
                        temp.next = l1;
                }
                if (l2!=null)
                        temp.next = l2;
                return newHead.next;
        }

        //22. Generate Parentheses
        public List<String> generateParenthesis(int n) {
                List<String> result  = new ArrayList<>();
                if (n <= 0)
                        return result;
                generateParenthesisHelper(0,0,new StringBuilder(),result,n);
                return result;
        }

        public void generateParenthesisHelper(int left,int right,StringBuilder builder,List<String> result,int n){
                if (left == n && right == n){
                        result.add(builder.toString());
                        return;
                }
                if ( left == right || left < n){
                        builder.append('(');
                        generateParenthesisHelper(left +1, right, builder, result,n);
                        builder.deleteCharAt(builder.length() - 1);
                }
                if(right < n && right < left){
                        builder.append(')');
                        generateParenthesisHelper(left , right+1, builder, result,n);
                        builder.deleteCharAt(builder.length() - 1);
                }
        }

        //23. Merge k Sorted Lists
        public ListNode mergeKLists(ListNode[] lists) {
                if (lists == null || lists.length == 0)
                        return null;
                return mergeKlistsHelper(lists,0,lists.length-1);
        }

        private ListNode mergeKlistsHelper(ListNode[] lists,int low,int high){
                if (low == high)
                        return lists[low];
                int mid = low + (high-low)/2;
                ListNode l1 = mergeKlistsHelper(lists,low,mid);
                ListNode l2 = mergeKlistsHelper(lists,mid+1,high);
                return mergeTwoLists(l1,l2);
        }

        //24. Swap Nodes in Pairs
        public ListNode swapPairs(ListNode head) {
                ListNode pre,p,q,newHead;
                if (head == null)
                        return null;
                pre = head;p = head;q=head.next;newHead=q;
                if (q == null)
                        return head;
                while (q != null){
                        pre.next = q;
                        p.next = q.next;
                        q.next = p;
                        pre = p;
                        p = p.next;
                        if (p == null)
                                break;
                        q = p.next;
                }
                return newHead;
        }

        //26. Remove Duplicates from Sorted Array
        /*public int removeDuplicates(int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                int begin = 1;
                int elem = nums[0];
                for (int i = 1;i<nums.length;i++){
                        if (elem != nums[i]){
                                elem=nums[i];
                                nums[begin++] = nums[i];
                        }
                }
                return begin;
        }*/

        //27. Remove Element
        public int removeElement(int[] nums, int val) {
                if (nums == null || nums.length == 0)
                        return 0;
                int start = 0, end = nums.length -1;
                for (;start <= end;start++){
                        while (nums[end] == val){
                                end--;
                                if (end < 0)
                                        return start;
                        }
                        if (start > end)
                                return start;
                        if (nums[start] == val){
                                int temp = nums[end];
                                nums[end] = nums[start];
                                nums[start] = temp;
                        }
                }
                return start;
        }

        //28. Implement strStr()
        public int strStr(String haystack, String needle) {
                if (haystack == null || needle == null || haystack.length() < needle.length())
                        return -1;
                if (haystack.isEmpty() && needle.isEmpty())
                        return 0;
                int[] next = new int[needle.length()];
                strStrHelper(needle,next);
                int i = 0, j = 0;
                while (i < haystack.length() && haystack.length()-i >= needle.length()-j){
                        while (j < needle.length() && haystack.charAt(i) ==needle.charAt(j) ){
                                i++;
                                j++;
                        }
                        if (j == needle.length()){
                                return i-needle.length();
                        }else {
                                if (j == 0)
                                        i++;
                                else
                                       j = next[j-1];
                        }
                }
                return -1;
        }

        public void strStrHelper(String needle,int[] next){
                int i = 1,j=0;
                while (i < needle.length()){
                        if (needle.charAt(i) == needle.charAt(j))
                                next[i++] = ++j;
                        else if (j==0)
                                next[i++] = j;
                        else
                                j = next[j-1];
                }
        }

        //29. Divide Two Integers
        public int divide(int dividend, int divisor) {
                if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
                        return Integer.MAX_VALUE;
                boolean mark;
                if ((dividend >= 0 && divisor >= 0) || (dividend < 0 && divisor < 0)){
                        mark = false;
                }else
                        mark = true;
                int count = 0;
                long num1 = Math.abs((long)dividend);
                long num2 = Math.abs((long)divisor);
                while (num1 >= num2){
                        long temp = num2,mul = 1;
                        while (num1 >= (temp <<1)){
                                temp <<= 1;
                                mul <<= 1;
                        }
                        num1 -= temp;
                        count += mul;
                }

                return mark ? -count : count;
        }

        //31. Next Permutation
        public void nextPermutation(int[] nums) {
                int pos = nums.length-1;
                while (pos > 0){
                        if (nums[pos] >nums[pos-1])break;
                        pos--;
                }
                pos--;
                if (pos < 0){
                        nextPermutationHelper(nums,0);
                        return;
                }

                for (int i = nums.length-1; i >pos;i--){
                        if (nums[i] > nums[pos]){
                                int temp = nums[pos];
                                nums[pos] = nums[i];
                                nums[i] = temp;
                                break;
                        }
                }

                nextPermutationHelper(nums,pos+1);
        }

        private void nextPermutationHelper(int[] nums,int start){
                int low = start,high = nums.length-1;
                while (low < high){
                        int temp = nums[high];
                        nums[high] = nums[low];
                        nums[low] = temp;
                        high--;
                        low++;
                }
        }

        //33. Search in Rotated Sorted Array
        /*public int search(int[] nums, int target) {
                int low = 0, high = nums.length-1;
                while (low <= high){
                        int mid = low + (high - low)/2;
                        if (nums[mid] == target)
                                return mid;
                        else if (nums[mid] >= nums[low]){
                                if (target > nums[mid] || target < nums[low])
                                        low = mid+1;
                                else
                                        high = mid-1;
                        }else if (nums[mid] <= nums[high]){
                                if (target < nums[mid] || target > nums[high])
                                        high = mid-1;
                                else
                                        low = mid+1;
                        }
                }
                return -1;
        }*/

        //32. Longest Valid Parentheses
        public int longestValidParentheses(String s) {
                int[] dp = new int[s.length()];
                int open = 0;
                int max = 0;
                for (int i = 0; i < s.length(); i++){
                        if (s.charAt(i) == '(')
                                open++;
                        if (s.charAt(i) == ')' && open > 0){
                                dp[i] = dp[i-1]+2;
                                if (i-dp[i] > 0)
                                        dp[i] += dp[i-dp[i]];
                                open--;
                        }
                        if (dp[i] > max)
                                max = dp[i];
                }
                return max;
        }

        //34. Search for a Range
        public int[] searchRange(int[] nums, int target) {
                int[] position = new int[]{-1,-1};
                if (nums == null || nums.length == 0)
                        return position;
                int low = 0, high = nums.length-1;
                while (low <= high){
                        int mid = low + (high - low)/2;
                        if (nums[mid] == target){
                                position[0] = position[1] = mid;
                                while (position[0] >= 1 && nums[position[0] -1] == target)
                                        position[0]--;
                                while (position[1] <= nums.length-2 && nums[position[1] +1] == target)
                                        position[1]++;
                                return position;
                        }else if (nums[mid] > target)
                                high = mid-1;
                        else
                                low = mid+1;
                }
                return position;
        }

        //35. Search Insert Position
        public int searchInsert(int[] nums, int target) {
                int low = 0,high = nums.length -1;
                while (low <= high){
                        int mid = low + (high - low)/2;
                        if (nums[mid] == target)
                                return mid;
                        else if (nums[mid] > target)
                                high = mid-1;
                        else
                                low = mid+1;
                }
                return low;
        }

        //36. Valid Sudoku
        public boolean isValidSudoku(char[][] board) {
                for (int i = 0; i < 9; i++){
                        if (!isValidSudokuHelper(board,i,0,i,8))
                                return false;
                        if (!isValidSudokuHelper(board,0,i,8,i))
                                return false;
                }

                for (int i = 0; i < 3; i++){
                        for (int j = 0; j < 3; j++){
                                if (!isValidSudokuHelper(board,i*3,j*3,i*3+2,j*3+2))
                                        return false;
                        }
                }
                return true;
        }

        private boolean isValidSudokuHelper(char[][] board,int x1,int y1,int x2,int y2){
                Set set = new HashSet();
                for (int i = x1; i <=x2;i++){
                        for (int j = y1; j <= y2;j++){
                                if (board[i][j]!='.')
                                        if(!set.add(board[i][j]))
                                                return false;

                        }
                }
                return true;
        }

        //38. Count and Say
        public String countAndSay(int n) {
                String result = "1";
                while (n>1){
                        String pre = result;
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0;i<pre.length();){
                               char num = pre.charAt(i);
                                int count = 0;
                                while (i<pre.length() && num == pre.charAt(i)){
                                        i++;
                                        count++;
                                }
                                builder.append(count);
                                builder.append(num);
                        }
                        result = builder.toString();
                        n--;
                }
                return result;
        }

        //39. Combination Sum
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
                if (candidates == null || candidates.length == 0)
                        return new ArrayList<>();
                List<List<Integer>> res = new ArrayList<>();
                Arrays.sort(candidates);
                combinationSumHelper(candidates, target, new ArrayList<Integer>(), res);
                return res;
        }

        private void combinationSumHelper(int[] candidates,int target,List<Integer> list,List<List<Integer>> res){
                if (target == 0){
                        res.add(new ArrayList<Integer>(list));
                        return;
                }
                if (target < 0)
                        return;
                for (int i = 0; i < candidates.length; i++){
                        if ((!list.isEmpty() && list.get(list.size()-1) <= candidates[i]) || list.isEmpty()){
                                list.add(candidates[i]);
                                combinationSumHelper(candidates, target - candidates[i], list, res);
                                list.remove(list.size()-1);
                        }
                }
        }

        //40. Combination Sum II
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
                List<List<Integer>> res = new ArrayList<>();
                Arrays.sort(candidates);
                boolean[] used = new boolean[candidates.length];
                combinationSum2Helper(candidates,target,0,used,new ArrayList<Integer>(),res);
                return res;
        }

        private void combinationSum2Helper(int[] candidates,int target,int start,boolean[] used,List<Integer> list,List<List<Integer>> res){
                if (target == 0){
                        res.add(new ArrayList<Integer>(list));
                        return;
                }
                if (target < 0)
                        return;
                for (int i = start; i < candidates.length; i++){
                        if (i > 0 && candidates[i] == candidates[i-1] && !used[i-1])
                                continue;
                        list.add(candidates[i]);
                        used[i] = true;
                        combinationSum2Helper(candidates, target - candidates[i], i + 1,used, list, res);
                        list.remove(list.size() - 1);
                        used[i] = false;
                }
        }

        //43. Multiply Strings
        public String multiply(String num1, String num2) {
                int len = num1.length()-1;
                String reslut = "0";
                for (int i = len; i >=0;i--){
                        reslut = addTwoDigit(reslut,multiply_oneDigit(num2,num1.charAt(i)-'0',len-i));
                }
                return reslut;
        }

        private String multiply_oneDigit(String s,int digit,int n){
                StringBuilder builder = new StringBuilder();
                int count = 0;
                for (int i = s.length()-1; i >=0;i--){
                        int sum = (s.charAt(i)-'0') * digit + count;
                        count = sum /10;
                        builder.insert(0,sum%10);
                }
                if (count!=0)
                        builder.insert(0,count);
                for (int i = 0; i < n;i++){
                        builder.append('0');
                }
                if (builder.charAt(0) == '0')
                        return "0";
                return builder.toString();
        }

        private String addTwoDigit(String num1,String num2){
                StringBuilder builder = new StringBuilder();
                int len1 = num1.length()-1,len2 = num2.length()-1;
                int count = 0;
                while (len1>=0 && len2 >=0){
                        int sum = (num1.charAt(len1) -'0') + (num2.charAt(len2)-'0') + count;
                        count = sum/10;
                        builder.insert(0, sum % 10);
                        len1--;
                        len2--;
                }
                while (len1>=0){
                        int sum = (num1.charAt(len1)-'0') + count;
                        count = sum/10;
                        builder.insert(0, sum % 10);
                        len1--;
                }
                while (len2>=0){
                        int sum = (num2.charAt(len2)-'0') + count;
                        count = sum/10;
                        builder.insert(0, sum % 10);
                        len2--;
                }
                if (count!=0)
                        builder.insert(0, count);
                return builder.toString();
        }

        //44. Wildcard Matching
        public boolean isMatch(String s, String p) {
                int i  = 0, j = 0,startId = -1,match = 0;
                while (i < s.length()){
                        if (j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))){
                                i++;
                                j++;
                        }else if (j < p.length() && p.charAt(j) == '*'){
                                startId = j;
                                match = i;
                                j++;
                        }else if (startId != -1){
                                j = startId + 1;
                                match++;
                                i = match;
                        }else
                                return false;
                }
                while (j < p.length() && p.charAt(j) == '*')
                        j++;
                return j == p.length();
        }

        //46. Permutations
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
                if (nums == null)
                        return result;
                int[] mark = new int[nums.length];
                permuteHelper(nums, mark, new ArrayList<Integer>());
                return result;
        }

        private void permuteHelper(int[] nums, int[] mark, List<Integer> temp){
                if (temp.size() == nums.length){
                        result.add(new ArrayList<Integer>(temp));
                        return;
                }
                for (int i = 0; i < nums.length; i++){
                        if (mark[i] == 0){
                                temp.add(nums[i]);
                                mark[i] = 1;
                                permuteHelper(nums, mark, temp);
                                temp.remove(temp.size() - 1);
                                mark[i] = 0;
                        }
                }
        }

        //47. Permutations II
        public List<List<Integer>> permuteUnique(int[] nums) {
                if (nums == null)
                        return result;
                int[] mark = new int[nums.length];
                Arrays.sort(nums);
                permuteUniqueHelper(nums, mark, new ArrayList<Integer>());
                return result;
        }


        private void permuteUniqueHelper(int[] nums,int[] mark,List<Integer> temp){
                if (temp.size() == nums.length){
                        result.add(new ArrayList<Integer>(temp));
                        return;
                }
                for (int i = 0; i < nums.length; i++){
                        if (mark[i] == 0){
                                if (i > 0 && nums[i] == nums[i-1] && mark[i-1] == 0)
                                        continue;
                                temp.add(nums[i]);
                                mark[i] = 1;
                                permuteUniqueHelper(nums, mark, temp);
                                temp.remove(temp.size() - 1);
                                mark[i] = 0;
                        }
                }
        }

        //48. Rotate Image
        /*
        * 顺时针
        * 先上下交换，在对趁交换
        * 1 2 3           7 8 9           7 4 1
        * 4 5 6  ---->4 5 6  ----->8 5 2
        * 7 8 9          1 2 3           9 6 3
        *
        * 逆时针
        * 先左右交换，在对称交换
        * 1 2 3           3 2 1          3 6 9
        * 4 5 6  ---->6 5 4 ----->2 5 8
        * 7 8 9          9 8 7           1 4 7
        * */
        public void rotate(int[][] matrix) {
                if (matrix == null || matrix.length == 0)
                        return;
                int n = matrix.length-1;
                for (int i = 0; i <=n/2;i++)
                        for (int j = 0;j <= n;j++){
                                int temp = matrix[i][j];
                                matrix[i][j] = matrix[n-i][j];
                                matrix[n-i][j] = temp;
                        }
                for (int i = 0; i <=n; i++){
                        for (int j = i+1; j <=n;j++){
                                int temp = matrix[i][j];
                                matrix[i][j] = matrix[j][i];
                                matrix[j][i] = temp;
                        }
                }
        }

        //49. Group Anagrams
        public List<List<String>> groupAnagrams(String[] strs) {
                /*Set<String> set = new HashSet<>();
                List<List<String>> res = new ArrayList<>();
                if (strs == null || strs.length == 0)
                        return res;
                Arrays.sort(strs);
                for (int i = 0; i < strs.length;i++){
                        if (!set.contains(strs[i])){
                                List<String> list = new ArrayList<>();
                                list.add(strs[i]);
                                set.add(strs[i]);
                                for (int j = i+1; j < strs.length;j++){
                                        if (groupAnagramsHelper(strs[i],strs[j])){
                                                set.add(strs[j]);
                                                list.add(strs[j]);
                                        }
                                }
                                res.add(list);
                        }
                }
                return res;*/
                if (strs == null || strs.length == 0)
                        return new ArrayList<>();
                Map<String,List<String>> map = new HashMap<>();
                for (int i = 0; i < strs.length; i++){
                        char[] a = strs[i].toCharArray();
                        Arrays.sort(a);
                        String s = String.valueOf(a);
                        if (!map.containsKey(s))
                                map.put(s,new ArrayList<String>());
                        map.get(s).add(strs[i]);
                }
                for (String key:map.keySet())
                        Collections.sort(map.get(key));
                return new ArrayList<>(map.values());
        }

        private boolean groupAnagramsHelper(String s,String t){
                int[] mark = new int[26];
                if (s.length() != t.length())
                        return false;
                for (int i = 0; i < s.length();i++){
                        mark[s.charAt(i)-'a']++;
                        mark[t.charAt(i)-'a']--;
                }
                for (int i = 0; i < mark.length;i++)
                        if (mark[i] != 0)
                                return false;
                return true;
        }

        //50. Pow(x, n)
        public double myPow(double x, int n) {
                if(n == 0)
                        return 1;
                if (n < 0){
                        n = -n;
                        x= 1/x;
                }
                return (n %2== 0) ? myPow(x*x,n/2) : x * myPow(x*x,n/2);
        }

        //53. Maximum Subarray
        public int maxSubArray(int[] nums) {
                int[] dp = new int[nums.length];
                dp[0] = nums[0];
                int  max = dp[0];
                for (int i = 1; i < nums.length; i++){
                        dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
                        max = Math.max(max,dp[i]);
                }
                System.out.println(Arrays.toString(dp));
                return max;
        }

        //54. Spiral Matrix
        public List<Integer> spiralOrder(int[][] matrix) {
                List<Integer> res = new ArrayList<>();
                if (matrix == null || matrix.length == 0)
                        return res;
                int left = 0,right = matrix[0].length-1,up = 0,bottom = matrix.length-1;
                while (true){
                        for (int i = left; i <= right;i++)
                                res.add(matrix[up][i]);
                        up++;
                        if (left >right || up > bottom)
                                return res;
                        for (int i = up;i <= bottom; i++ )
                                res.add(matrix[i][right]);
                        right--;
                        if (left >right || up > bottom)
                                return res;
                        for (int i = right; i >=left;i--)
                                res.add(matrix[bottom][i]);
                        bottom--;
                        if (left >right || up > bottom)
                                return res;
                        for (int i = bottom; i>=up; i--)
                                res.add(matrix[i][left]);
                        left++;
                        if (left >right || up > bottom)
                                return res;
                }
        }

        //55. Jump Game
        public boolean canJump(int[] nums) {
                int max = 0;
                for (int i = 0; i < nums.length-1; i++){
                        if (max < i)
                                return false;
                        max = Math.max(max,i + nums[i]);
                        if (max >= nums.length -1)
                                return true;
                }
                return max >= nums.length-1;
        }

        //58. Length of Last Word
        public int lengthOfLastWord(String s) {
                String[] words = s.split(" ");
                if (words.length == 0)
                        return 0;
                return words[words.length-1].length();
        }

        //59. Spiral Matrix II
        public int[][] generateMatrix(int n) {
                int[][] matrix = new int[n][n];
                int left = 0,right = n-1,up = 0,bottom = n-1;
                int num = 0;
                while (true){
                        for (int i = left; i <= right; i++)
                                matrix[up][i] = ++num;
                        up++;
                        if (up > bottom || left > right)
                                return matrix;
                        for (int i = up; i <= bottom; i++)
                                matrix[i][right] = ++num;
                        right--;
                        if (up > bottom || left > right)
                                return matrix;
                        for (int i = right; i>=left; i--)
                                matrix[bottom][i] = ++ num;
                        bottom--;
                        if (up > bottom || left > right)
                                return matrix;
                        for (int i = bottom; i >=up;i--)
                                matrix[i][left] = ++num;
                        left++;
                        if (up > bottom || left > right)
                                return matrix;
                }
        }

        //60. Permutation Sequence
        public String getPermutation(int n, int k) {
                StringBuilder origal = new StringBuilder();
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <=n; i++){
                        origal.append(i);
                }
                while (k!=0){
                        int temp = k /getPermutationHelper(n-1);
                        k = k % getPermutationHelper(n-1);
                        if (k == 0){
                                result.append(origal.charAt(temp-1));
                                origal.deleteCharAt(temp-1);
                                result.append(origal.reverse());
                                return result.toString();
                        }else {
                                result.append(origal.charAt(temp));
                                origal.deleteCharAt(temp);
                        }
                        n--;
                }
                return origal.toString();
        }

        private int getPermutationHelper(int n){
                int sum = 1;
                while (n!=0){
                        sum *= n;
                        n--;
                }
                return sum;
        }

        //61. Rotate List
        public ListNode rotateRight(ListNode head, int k) {
                if (head == null)
                        return null;
                int length = 0;
                ListNode pre = null,temp = head,newHead;
                while (temp!=null){
                        length++;
                        temp = temp.next;
                }
                k = k % length;
                if (k == 0)
                        return head;
                temp = head;
                while (k!=0){
                        temp = temp.next;
                        k--;
                }
               newHead = head;
                while (temp!=null){
                        pre = newHead;
                        newHead = newHead.next;
                        temp = temp.next;
                }
                temp = newHead;
                pre.next = null;
                while (temp.next!=null){
                        temp = temp.next;
                }
                temp.next = head;
                return newHead;
        }

        //62. Unique Paths
        public int uniquePaths(int m, int n) {
                //time limit
                /*int[] total = new int[]{0};
                uniquePathsHelper(0,0,m,n,total);
                return total[0];*/
                int[][] dp = new int[n+1][m+1];
                for (int i = 1; i <= m; i++){
                        dp[1][i] = 1;
                }
                for (int i = 1; i <= n; i++){
                        dp[i][1] = 1;
                }
                for (int i = 1; i <n; i++)
                        for (int j = 1; j < m; j++)
                                dp[i+1][j+1] = dp[i][j+1] + dp[i+1][j];
                return dp[n][m];
        }

        //63. Unique Paths II
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
                if (obstacleGrid == null || obstacleGrid.length == 0)
                        return 0;
                int n = obstacleGrid.length;
                int m = obstacleGrid[0].length;

                obstacleGrid[0][0] = obstacleGrid[0][0] == 0 ?1:0;
                for (int i=1; i < m; i++){
                        obstacleGrid[0][i] = obstacleGrid[0][i] == 1 ? 0 : obstacleGrid[0][i-1];
                }
                for (int j = 1; j < n; j++){
                        obstacleGrid[j][0] = obstacleGrid[j][0] == 1 ? 0 : obstacleGrid[j-1][0];
                }
                for (int i = 1; i <n; i++)
                        for (int j = 1; j < m; j++){
                                if (obstacleGrid[i][j] != 1)
                                        obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                                else
                                        obstacleGrid[i][j] = 0;
                        }
                return obstacleGrid[n-1][m-1];
        }

        private void uniquePathsHelper(int i, int j,int m,int n,int[] total){
                if (i >= n || j >=m)
                        return;
                if (i == n-1 && j == m-1)
                        total[0]++;
                uniquePathsHelper(i+1,j,m,n,total);
                uniquePathsHelper(i,j+1,m,n,total);
        }

        //64. Minimum Path Sum
        public int minPathSum(int[][] grid) {
                if (grid == null || grid.length == 0)
                        return 0;
                int n = grid.length,m = grid[0].length;
                for (int i = 1; i < m;i++)
                        grid[0][i] += grid[0][i-1];
                for (int i = 1; i < n; i++)
                        grid[i][0] += grid[i-1][0];
                for (int i = 1; i < n; i++){
                        for (int j = 1; j < m; j++)
                                grid[i][j] = Math.min(grid[i-1][j],grid[i][j-1]) + grid[i][j];
                }
                return grid[n-1][m-1];
        }

        //66. Plus One
        public int[] plusOne(int[] digits) {
                if (digits == null || digits.length == 0)
                        return new int[0];
                int[] result = new int[digits.length + 1];
                int con = (digits[digits.length -1] + 1)/10;
                result[result.length-1] = (digits[digits.length -1] + 1)%10;
                int index =digits.length-2;
                while (index>=0 && con != 0){
                        result[index+1] = (digits[index] + con) % 10;
                        con = (digits[index] + con) /10;
                        index--;
                }
                if (index < 0 && con!=0){
                        result[0] = 1;
                        return result;
                }else {
                        for (;index>=0;index--)
                                result[index+1] = digits[index];
                        return Arrays.copyOfRange(result,1,result.length);
                }
        }

        //67. Add Binary
        public String addBinary(String a, String b) {
                StringBuilder builder = new StringBuilder();
                int len1 = a.length()-1,len2 = b.length()-1;
                int count = 0;
                while (len1>=0 && len2>=0){
                        int sum = a.charAt(len1)-'0' + b.charAt(len2)-'0' + count;
                        builder.insert(0,sum%2);
                        count = sum/2;
                        len1--;
                        len2--;
                }
                while (len1>=0){
                        int sum = a.charAt(len1)-'0'+count;
                        builder.insert(0,sum%2);
                        count = sum/2;
                        len1--;
                }
                while (len2>=0){
                        int sum = b.charAt(len2)-'0' + count;
                        builder.insert(0,sum%2);
                        count = sum/2;
                        len2--;
                }
                if (count!=0)
                        builder.insert(0,count);
                return builder.toString();
        }

        //69. Sqrt(x)
        public int mySqrt(int x) {
                int i = 0;
                for (;i*i<=x;i++){
                        if (i*i == x)
                                return i;
                        if (i*i<0)
                                break;
                }
                return i-1;
        }

        //71. Simplify Path
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

        //72. Edit Distance
        public int minDistance(String word1, String word2) {
                int[][] dp = new int[word1.length()+1][word2.length()+1];
                for (int i = 1; i <=word2.length();i++)
                        dp[0][i] = dp[0][i-1]+1;
                for (int i = 1; i <= word1.length();i++)
                        dp[i][0] = dp[i-1][0] + 1;
                for (int i = 1;i <= word1.length();i++){
                        for (int j = 1; j <= word2.length(); j++){
                                if (word1.charAt(i-1) == word2.charAt(j-1))
                                        dp[i][j] = dp[i-1][j-1];
                                else
                                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j] ,dp[i][j-1])) + 1;
                        }
                }
                return dp[word1.length()][word2.length()];
        }


        //73. Set Matrix Zeroes
        public void setZeroes(int[][] matrix) {
                //O(n)space
               /* if (matrix == null || matrix.length == 0)
                        return;
                int length = Math.max(matrix.length,matrix[0].length);
                int[] book = new int[length];
                for (int i = 0;i < matrix.length;i++)
                        for (int j = 0; j < matrix[0].length; j++){
                                if (matrix[i][j] == 0 ){
                                        if (book[i] != 1 && book[i] < 3)
                                                book[i] += 1;
                                        if (book[j] != 2 && book[j]  < 3)
                                                book[j] += 2;
                                }
                        }
                for (int i = 0; i < matrix.length; i++)
                        if (book[i] == 1 || book[i] == 3)
                                Arrays.fill(matrix[i],0);
                for (int i = 0; i < matrix[0].length; i++){
                        if (book[i] ==2 || book[i] == 3)
                                for (int j = 0;j < matrix.length;j++)
                                        matrix[j][i] = 0;
                }*/

                //O(1)space
                if (matrix == null || matrix.length == 0)
                        return;
                int col = 1;
                for (int i = 0; i < matrix.length; i++){
                        if (matrix[i][0] == 0) col = 0;
                        for (int j = 1 ; j < matrix[0].length;j++)
                                if (matrix[i][j] == 0)
                                        matrix[i][0] = matrix[0][j] = 0;
                }
                for (int i = matrix.length -1; i>=0;i--){
                        for (int j = matrix[0].length-1;j >=1;j--)
                                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                                        matrix[i][j] = 0;
                        if (col == 0)
                                matrix[i][0] = 0;
                }

        }

        //74. Search a 2D Matrix
        /*public boolean searchMatrix(int[][] matrix, int target) {
                int low = 0, high = matrix.length-1;
                int n = matrix.length-1,m = matrix[0].length-1;
                while (low <= high){
                        int mid = low + (high-low)/2;
                        if (matrix[mid][m] == target){
                                return true;
                        }else if (matrix[mid][m] > target){
                                high = mid -1;
                        }else
                                low= mid+1;
                }
                if (low < 0 || low > n)
                        return false;
                int temp = low;
                low = 0;
                high = m;
                while (low <= high){
                        int mid = low + (high - low)/2;
                        if (matrix[temp][mid] == target)
                                return true;
                        else if (matrix[temp][mid] > target)
                                high = mid -1;
                        else
                                low= mid+1;
                }
                return false;
        }*/

        //75. Sort Colors
        public void sortColors(int[] nums) {
                int j0 = 0,j2=nums.length-1;
                for (int i = j0; j0 <= j2 && i>=j0; ){
                        while ( j0 < nums.length && nums[j0]==0)
                                j0++;
                        while (j2>=0 && nums[j2] == 2)
                                j2--;
                        if (j0 > j2 || i > j2)
                                break;
                        if (i < j0)
                                i = j0;
                        if (nums[i] == 0){
                                int temp = nums[j0];
                                nums[j0] = nums[i];
                                nums[i] = temp;
                                j0++;
                        }else if (nums[i] == 2){
                                int temp = nums[j2];
                                nums[j2] = nums[i];
                                nums[i] = temp;
                                j2--;
                        }else
                                i++;
                }
        }

        //78. Subsets
        public List<List<Integer>> subsets(int[] nums) {
                //backtracking
                List<List<Integer>> res = new ArrayList<>();
                if (nums == null || nums.length == 0)
                        return res;
                Arrays.sort(nums);
                subsetsHelper(nums,res,new ArrayList<Integer>(),0);
                res.add(new ArrayList<Integer>());
                return res;

                //bit map
                /*Arrays.sort(nums);
                List<List<Integer>> res = new ArrayList<>();
                for (int i = 1; i <= Math.pow(2,nums.length);i++){
                        res.add(new ArrayList<Integer>());
                }
                for (int i = 0; i < nums.length; i++){
                        for (int j = 0; j < Math.pow(2,nums.length);j++){
                                System.out.println(j>>i);
                                if (((j>>i) & 1)==1)
                                        res.get(j).add(nums[i]);
                        }
                }
                return res;*/
        }

        private void subsetsHelper(int[] nums,List<List<Integer>> res,List<Integer> list,int start){
                if (start >=nums.length){
                        return;
                }
                for (int i = start;i<nums.length;i++){
                        list.add(nums[i]);
                        res.add(new ArrayList<Integer>(list));
                        subsetsHelper(nums,res,list,i+1);
                        list.remove(list.size()-1);
                }
        }

        //79. Word Search
        public boolean exist(char[][] board, String word) {
                if (board == null || board.length == 0 || word == null)
                        return false;
                boolean[][] used = new boolean[board.length][board[0].length];
                for (int i = 0; i < board.length; i++){
                        for (int j = 0; j < board[0].length; j++){
                                if (word.charAt(0) == board[i][j] && existHelper(board,used,word,0,i,j))
                                        return true;
                        }
                }
                return false;
        }

        //80. Remove Duplicates from Sorted Array II
        public int removeDuplicates(int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                int begin = 1;
                int elme = nums[0];
                int count = 1;
                for (int i = 1;i < nums.length;i++){
                        if (nums[i] == elme && count <=1){
                                nums[begin++] = elme;
                                count++;
                        }else if (nums[i] != elme){
                                elme = nums[i];
                                nums[begin++] = elme;
                                count = 1;
                        }
                }
                return begin;
        }

        //81. Search in Rotated Sorted Array II
        public boolean search(int[] nums, int target) {
                int low = 0, high = nums.length-1;
                while (low <= high){
                        int mid = low + (high -low)/2;
                        if (nums[mid] == target)
                                return true;
                        while (low <=high && nums[low] == nums[mid])
                                low++;
                        while (high >= 0 && nums[high] == nums[mid])
                                high--;
                        if (low > high)
                                return false;
                        if (low > mid || mid > high)
                                continue;
                        if (nums[mid] > nums[low]){
                                if (target < nums[low] || target > nums[mid])
                                        low = mid+1;
                                else
                                        high = mid-1;
                        }else if (nums[mid] < nums[high] ){
                                if (target < nums[mid] || target > nums[high])
                                        high = mid-1;
                                else
                                        low = mid+1;
                        }
                }
                return false;
        }

        private boolean existHelper(char[][] board,boolean[][] used,String word,int step,int i,int j){
                if (step >= word.length())
                        return true;
                if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || used[i][j] || board[i][j] != word.charAt(step))
                        return false;
                used[i][j] = true;
                if (existHelper(board,used,word,step+1,i-1,j) ||
                        existHelper(board,used,word,step+1,i+1,j) ||
                        existHelper(board,used,word,step+1,i,j-1) ||
                        existHelper(board,used,word,step+1,i,j+1))
                        return true;
                used[i][j] = false;
                return false;
        }

        //82. Remove Duplicates from Sorted List II
        public ListNode deleteDuplicates(ListNode head) {
                if (head == null || head.next == null)
                        return head;
                ListNode newHead = new ListNode(0),p = head,pre = newHead;
                newHead.next = head;
                Set<Integer> set = new HashSet<>();
                while (p.next!=null){
                        if (p.val == p.next.val)
                                set.add(p.val);
                        p=p.next;
                }
                p = head;
                while (p!=null){
                        if (set.contains(p.val)){
                                pre.next = p.next;
                        }else {
                                pre = pre.next;
                        }
                        p = p.next;
                }
                return newHead.next;
        }

        //83. Remove Duplicates from Sorted List
        /*public ListNode deleteDuplicates(ListNode head) {
                if (head == null)
                        return null;
                ListNode pre = head,p=head.next;
                while (p!=null){
                        if (pre.val == p.val){
                                pre.next = p.next;
                        }else {
                                pre = pre.next;
                        }
                        p = p.next;
                }
                return head;
        }*/

        //86. Partition List
        public ListNode partition(ListNode head, int x) {
                if (head == null || head.next == null)
                        return head;
                ListNode h1 = new ListNode(0),h2 = new ListNode(0),pre=h2,p=head,temp=h1;
                h2.next = head;
                while (p!=null){
                        if (p.val < x){
                                temp.next = p;
                                pre.next = p.next;
                                p=p.next;
                                temp = temp.next;
                        }else {
                                pre = pre.next;
                                p = p.next;
                        }
                }
                temp.next = h2.next;
                return h1.next;
        }

        //88. Merge Sorted Array
        public void merge(int[] nums1, int m, int[] nums2, int n) {
                if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
                        return;
                int p1 = m-1,p2 = n-1,index = m+n-1;
                while (p1 >= 0 && p2 >= 0){
                        if (nums1[p1] >= nums2[p2])
                                nums1[index--] = nums1[p1--];
                        else
                                nums1[index--] = nums2[p2--];
                }
                while (p1 >= 0)
                        nums1[index--] = nums1[p1--];
                while (p2 >=0)
                        nums1[index--] = nums2[p2--];
        }

        //90. Subsets II
        public List<List<Integer>> subsetsWithDup(int[] nums) {
                List<List<Integer>> res = new ArrayList<>();
                if (nums == null || nums.length == 0)
                        return res;
                boolean used[] = new boolean[nums.length];
                Arrays.sort(nums);
                subsetsWithDupHelper(nums, res, new ArrayList<Integer>(), used, 0);
                res.add(new ArrayList<Integer>());
                return res;
        }

        private void subsetsWithDupHelper(int[] nums,List<List<Integer>>res,List<Integer> list,boolean[] used,int start){
                if (start >= nums.length)
                        return;
                for (int i = start; i < nums.length; i++){
                        if (i > 0 && nums[i] == nums[i-1] && used[i-1]==false)
                                continue;
                        list.add(nums[i]);
                        used[i] = true;
                        res.add(new ArrayList<Integer>(list));
                        subsetsWithDupHelper(nums, res, list, used, i + 1);
                        used[i] = false;
                        list.remove(list.size() - 1);
                }
        }

        //91. Decode Ways
        public int numDecodings(String s) {
                if (s==null || s.isEmpty())
                        return 0;
                int[] num = new int[s.length()+1];
                num[s.length()] = 1;
                num[s.length()-1] = s.charAt(s.length()-1) != '0' ? 1:0;
                for (int i = s.length()-2;i>=0;i--){
                        if (s.charAt(i) != '0')
                                num[i] = Integer.parseInt(s.substring(i,i+2)) <=26 ? num[i+1] + num[i+2] : num[i+1];
                }
                return num[0];
        }



        //92. Reverse Linked List II
        public ListNode reverseBetween(ListNode head, int m, int n) {
                if (m == n)
                        return head;
                int step = 1;
                ListNode pre = new ListNode(0),p=head,newHead = pre;
                pre.next = head;
                while (step!=m){
                        pre = p;
                        p = p.next;
                        step++;
                }
                while (step<n){
                        ListNode temp = p.next;
                        p.next = temp.next;
                        temp.next = pre.next;
                        pre.next = temp;
                        step++;
                }
                return newHead.next;
        }

        //93. Restore IP Addresses
        public List<String> restoreIpAddresses(String s) {
                List<String> result = new ArrayList<>();
                restoreIpAddressesHelper(s,result,new ArrayList<String>());
                return result;
        }

        public void restoreIpAddressesHelper(String s,List<String> result,List<String> val){
                if (val.size() == 4){
                        if (s.isEmpty()){
                                result.add(makeIpAddress(val));
                        }
                        return;
                }
                for (int i = 1;i<=3;i++){
                        if (i <= s.length()){
                                String str = s.substring(0,i);
                                int temp = Integer.parseInt(s.substring(0,i));
                                if (temp <= 255&&(str.length()>1 && str.charAt(0) != '0') || str.length()==1){
                                        val.add(s.substring(0,i));
                                        restoreIpAddressesHelper(s.substring(i), result, val);
                                        val.remove(val.size()-1);
                                }
                        }
                }
        }

        private String makeIpAddress(List<String> val){
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < val.size();i++){
                        if (i == 0)
                                builder.append(val.get(i));
                        else
                                builder.append("." + val.get(i));
                }
                return builder.toString();
        }

        //94. Binary Tree Inorder Traversal
        public List<Integer> inorderTraversal(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                Stack<TreeNode> stack = new Stack<>();
                while (root!=null){
                        stack.push(root);
                        root = root.left;
                }
                while (!stack.isEmpty()){
                        TreeNode temp = stack.pop();
                        res.add(temp.val);
                        temp = temp.right;
                        while (temp!= null){
                                stack.add(temp);
                                temp = temp.left;
                        }
                }
                return res;
        }

        //95. Unique Binary Search Trees II
        public List<TreeNode> generateTrees(int n) {
                if (n <= 0)
                        return new ArrayList<>();
                return generateTreesHelper(1,n);
        }

        private List<TreeNode> generateTreesHelper(int start, int end){
                List<TreeNode> list = new ArrayList<>();
                if (start > end){
                        list.add(null);
                        return list;
                }
                if (start == end){
                        list.add(new TreeNode(start));
                        return list;
                }

                List<TreeNode> left ,right;
                for (int i = start; i <= end; i++){
                        left = generateTreesHelper(start,i-1);
                        right = generateTreesHelper(i+1,end);
                        for (TreeNode leftNode:left){
                                for (TreeNode rightNode:right){
                                        TreeNode root = new TreeNode(i);
                                        root.left = leftNode;
                                        root.right = rightNode;
                                        list.add(root);
                                }
                        }
                }
                return list;
        }

        //96. Unique Binary Search Trees
        public int numTrees(int n) {
                int[] dp = new int[n+1];
                dp[0] = 1;
                for (int i = 1; i <= n; i++)
                        for (int j = 0; j < i;j++)
                                dp[i] += dp[j] * dp[i-j-1];
                return dp[n];
        }

        //97. Interleaving String
        public boolean isInterleave(String s1, String s2, String s3) {
                if (s1.length() + s2.length() != s3.length())
                        return false;
                boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
                dp[0][0] = true;
                for (int i = 1;i<=s2.length();i++)
                        dp[0][i] = dp[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);
                for (int i = 1; i <= s1.length();i++)
                        dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
                for (int i = 1;i < s1.length()+1;i++)
                        for (int j = 1; j < s2.length() + 1;j++)
                                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                return dp[s1.length()][s2.length()];
        }


        //98. Validate Binary Search Tree
        public boolean isValidBST(TreeNode root) {
                return isValidBSTHelper(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
        }

        private boolean isValidBSTHelper(TreeNode root,int min,int max){
                /*if (root.left == null && root.right == null){
                        return true;
                }
                if (root.left != null && root.right == null){
                        if (root.left.val > root.val || root.left.val > value)
                                return false;
                        else
                                return isValidBSTHelper(root.left,root.val);
                }
                if (root.left == null){
                        if (root.right.val < root.val || root.val > value)
                                return false;
                        else
                                return isValidBSTHelper(root.right,root.val);
                }
                if (root.left.val > root.val || root.left.val > value || root.right.val < root.val || root.right.val > value)
                        return false;
                return isValidBSTHelper(root.left,root.val) && isValidBSTHelper(root.right,root.val);*/
                if(root == null)
                        return true;
                if (root.val >= max || root.val <= min)
                        return false;
                return isValidBSTHelper(root.left,min,root.val) && isValidBSTHelper(root.right,root.val,max);
        }

        //100. Same Tree
        public boolean isSameTree(TreeNode p, TreeNode q) {
                if (p == null || q == null)
                        return p == q;
                return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        //101. Symmetric Tree
        public boolean isSymmetric(TreeNode root) {
                List<Integer> list = new ArrayList<>();
                if (root == null)
                        return true;
                return isSymmetricHelper(root.left, root.right);
        }

        private boolean isSymmetricHelper(TreeNode node1, TreeNode node2){
                if ((node1 == null && node2 != null) || (node1 != null && node2 == null))
                        return false;
                if (node1 == null)
                        return true;
                if (node1.val != node2.val)
                        return false;
                return isSymmetricHelper(node1.left,node2.right) && isSymmetricHelper(node1.right,node2.left);
        }

        //102. Binary Tree Level Order Traversal
        public List<List<Integer>> levelOrder(TreeNode root) {
                List<List<Integer>> res = new ArrayList<>();
                levelOrderHelper(root,res,0);
                return res;
        }

        private void levelOrderHelper(TreeNode node,List<List<Integer>> res,int level){
                if (node == null)
                        return;
                if (res.size() <= level){
                        List<Integer> temp = new ArrayList<>();
                        res.add(temp);
                }

                res.get(level).add(node.val);
                levelOrderHelper(node.left, res, level + 1);
                levelOrderHelper(node.right,res,level + 1);
        }

        //103. Binary Tree Zigzag Level Order Traversal
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                List<List<Integer>> res = new ArrayList<>();
                if (root == null)
                        return res;
                Queue<TreeNode> queue1 = new LinkedList<>();
                Queue<TreeNode> queue2 = new LinkedList<>();
                queue1.add(root);
                while (!queue1.isEmpty() || !queue2.isEmpty()){
                        if (!queue1.isEmpty()){
                                List<Integer> temp = new ArrayList<>();
                                while (!queue1.isEmpty()){
                                        TreeNode node = queue1.poll();
                                        temp.add(node.val);
                                        if (node.left != null)
                                                queue2.add(node.left);
                                        if (node.right != null)
                                                queue2.add(node.right);
                                }
                                res.add(temp);
                        }
                        if (!queue2.isEmpty()){
                                List<Integer> temp = new ArrayList<>();
                                while (!queue2.isEmpty()){
                                        TreeNode node = queue2.poll();
                                        temp.add(0,node.val);
                                        if (node.left != null)
                                                queue1.add(node.left);
                                        if (node.right != null)
                                                queue1.add(node.right);
                                }
                                res.add(temp);
                        }
                }
                return res;
        }

        //104. Maximum Depth of Binary Tree
        public int maxDepth(TreeNode root) {
                if (root == null)
                        return 0;
                return Math.max(1+maxDepth(root.left), 1 + maxDepth(root.right));
        }

        //105. Construct Binary Tree from Preorder and Inorder Traversal
        /*public TreeNode buildTree(int[] preorder, int[] inorder) {
                if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0)
                        return null;
                TreeNode root = new TreeNode(preorder[0]);
                int index = buildTreeHelper(inorder,preorder[0]);
                int[] inorderLeft = Arrays.copyOfRange(inorder,0,index);
                int[] inorderRight = Arrays.copyOfRange(inorder,index+1,inorder.length);
                int[] preorderLeft = Arrays.copyOfRange(preorder,1,1+ inorderLeft.length);
                int[] preorderRight = Arrays.copyOfRange(preorder,1+ inorderLeft.length,preorder.length);
                root.left = buildTree(preorderLeft,inorderLeft);
                root.right = buildTree(preorderRight,inorderRight);
                return root;
        }*/

        private int buildTreeHelper(int[] nums,int val){
                for (int i = 0; i < nums.length; i++)
                        if (nums[i] == val)
                                return i;
                return -1;
        }

        //106. Construct Binary Tree from Inorder and Postorder Traversal
        public TreeNode buildTree(int[] inorder, int[] postorder) {
                if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0)
                        return null;
                TreeNode root = new TreeNode(postorder[postorder.length-1]);
                int index = buildTreeHelper(inorder,root.val);
                int[] inorderLeft = Arrays.copyOfRange(inorder,0,index);
                int[] inorderRight = Arrays.copyOfRange(inorder,index+1,inorder.length);
                int[] postorderLeft = Arrays.copyOfRange(postorder,0,inorderLeft.length);
                int[] postorderRight = Arrays.copyOfRange(postorder,inorderLeft.length,postorder.length-1);
                root.left = buildTree(inorderLeft, postorderLeft);
                root.right = buildTree(inorderRight, postorderRight);
                return root;
        }

        //107. Binary Tree Level Order Traversal II
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
                List<List<Integer>> res = new ArrayList<>();
                levelOrderBottomHelper(root,res,0);
                return res;
        }

        private void levelOrderBottomHelper(TreeNode node,List<List<Integer>> res, int level){
                if (node == null)
                        return;
                if (res.size() <= level){
                        List<Integer> temp = new ArrayList<>();
                        res.add(0,temp);
                }
                res.get(res.size() - level -1).add(node.val);
                levelOrderBottomHelper(node.left, res, level + 1);
                levelOrderBottomHelper(node.right,res,level + 1);
        }

        //108. Convert Sorted Array to Binary Search Tree
        public TreeNode sortedArrayToBST(int[] nums) {
                if (nums == null || nums.length == 0)
                        return null;
                return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
        }

        private TreeNode sortedArrayToBSTHelper(int[] nums,int i ,int j){
                if(i > j)
                        return null;
                int mid = i + (j - i)/2;
                TreeNode root = new TreeNode(nums[mid]);
                root.left = sortedArrayToBSTHelper(nums,i,mid-1);
                root.right = sortedArrayToBSTHelper(nums,mid+1,j);
                return root;
        }

        //109. Convert Sorted List to Binary Search Tree
        /*public TreeNode sortedListToBST(ListNode head) {
                if (head == null)
                        return null;
                List<Integer> list = new ArrayList<>();
                while (head!=null){
                        list.add(head.val);
                        head = head.next;
                }
                return sortedListToBSTHelper(list,0,list.size()-1);
        }

        private TreeNode sortedListToBSTHelper(List<Integer> list,int i ,int j){
                if (i > j)
                        return null;
                int mid = i + (j - i)/2;
                TreeNode root = new TreeNode(list.get(mid));
                root.left = sortedListToBSTHelper(list,i,mid-1);
                root.right = sortedListToBSTHelper(list,mid+1,j);
                return root;
        }*/
        private ListNode node;

        public TreeNode sortedListToBST(ListNode head) {
                if(head == null){
                        return null;
                }

                int size = 0;
                ListNode runner = head;
                node = head;

                while(runner != null){
                        runner = runner.next;
                        size ++;
                }

                return inorderHelper(0, size - 1);
        }

        public TreeNode inorderHelper(int start, int end){
                if(start > end){
                        return null;
                }

                int mid = start + (end - start) / 2;
                TreeNode left = inorderHelper(start, mid - 1);

                TreeNode treenode = new TreeNode(node.val);
                treenode.left = left;
                node = node.next;

                TreeNode right = inorderHelper(mid + 1, end);
                treenode.right = right;

                return treenode;
        }

        //110. Balanced Binary Tree
        public boolean isBalanced(TreeNode root) {
                if (root == null)
                        return true;
                int left = isBalancedHelper(root.left);
                int right = isBalancedHelper(root.right);
                return (left > right ? left -right <=1 : right - left <=1) && isBalanced(root.left) && isBalanced(root.right);
        }

        private int isBalancedHelper(TreeNode root){
                if (root== null)
                        return 0;
                int left = isBalancedHelper(root.left) + 1;
                int right = isBalancedHelper(root.right) + 1;
                return Math.max(left,right);
        }

        //111. Minimum Depth of Binary Tree
        public int minDepth(TreeNode root) {
                /*if (root == null)
                        return 0;
                int[] min = new int[]{Integer.MAX_VALUE};
                minDepthHelper(root,min,0);
                return min[0];*/
                if (root == null)
                        return 0;
                int left = minDepth(root.left);
                int right = minDepth(root.right);
                return (left == 0 || right == 0) ? left + right + 1: 1+ Math.min(left,right);
        }
        private void minDepthHelper(TreeNode root,int[] min,int count){
                if (root == null)
                        return;
                count++;
                if (root.left == null && root.right == null)
                        min[0] = Math.min(min[0], count);
                minDepthHelper(root.left,min,count);
                minDepthHelper(root.right, min, count);
        }

        //112. Path Sum
        public boolean hasPathSum(TreeNode root, int sum) {
                if (root == null)
                        return false;
                if (root.left == null && root.right == null)
                        return sum - root.val == 0;
                boolean left = false ,right = false;
                if (root.left != null)
                        left = hasPathSum(root.left,sum -root.val);
                if (root.right != null)
                        right = hasPathSum(root.right, sum - root.val);
                return  left || right;
        }

        //113. Path Sum II
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
                List<List<Integer>> res = new ArrayList<>();
                pathSumHelper(res, new ArrayList<Integer>(), root, sum);
                return res;
        }

        //114. Flatten Binary Tree to Linked List
        public void flatten(TreeNode root) {
                //time limit
                /*if (root == null)
                        return;
                Stack<TreeNode> stack = new Stack<>();
                TreeNode pre = null;
                stack.push(root);
                while (!stack.isEmpty()){
                        TreeNode temp = stack.pop();
                        if (pre != null)
                                pre.right = temp;
                        if (temp.right != null)
                                stack.push(temp.right);
                        if (temp.left != null)
                                stack.push(temp.left);
                        pre = temp;
                }*/
                flattenHelper(root, null);
        }

        private TreeNode flattenHelper(TreeNode node,TreeNode pre){
                if (node == null)
                        return pre;
                pre = flattenHelper(node.right,pre);
                pre = flattenHelper(node.left,pre);
                node.right = pre;
                node.left = null;
                pre = node;
                return pre;
        }


        private void pathSumHelper(List<List<Integer>> result, List<Integer> list,TreeNode root,int sum){
                if (root == null)
                        return;
                list.add(root.val);
                sum = sum - root.val;
                if (root.left == null && root.right == null && sum == 0){
                        result.add(new ArrayList<Integer>(list));
                        list.remove(list.size() - 1);
                        return;
                }
                pathSumHelper(result,list,root.left,sum);
                pathSumHelper(result,list,root.right,sum);
                list.remove(list.size() - 1);
        }

        //116. Populating Next Right Pointers in Each Node
        public void connect1(TreeLinkNode root) {

        }

        private void connectHelper(TreeLinkNode root){
                if (root.left != null && root.right != null){
                        root.left.next = root.right;
                        if (root.next != null)
                                root.right.next = root.next.left;
                        connectHelper(root.left);
                        connectHelper(root.right);
                }
        }

        //117. Populating Next Right Pointers in Each Node II
        public void connect(TreeLinkNode root) {
                if (root == null)
                        return;
                if (root.left != null && root.right != null)
                        root.left.next = root.right;
                if (root.left != null && root.right == null){
                        TreeLinkNode temp = root;
                        while (temp.next != null){
                                if (temp.left != null){
                                        root.left.next = temp.left;
                                        break;
                                }else if (temp.right != null){
                                        root.left.next = temp.right;
                                        break;
                                }else {
                                        temp = temp.next;
                                }
                        }
                }

                if (root.right != null){
                        TreeLinkNode temp = root;
                        while (temp.next != null){
                                if (temp.left == null && temp.right == null)
                                        temp = temp.next;
                                else {
                                        root.right.next = temp.left != null ? temp.left : temp.right;
                                        break;
                                }
                        }
                }
                connect(root.left);
                connect(root.right);
        }

        public void connect2(TreeLinkNode root){
                if (root == null)
                        return;
                Queue<TreeLinkNode> queue1 = new LinkedList<>();
                Queue<TreeLinkNode> queue2 = new LinkedList<>();
                queue1.add(root);
                while (!queue1.isEmpty() || !queue2.isEmpty()){
                        while (!queue1.isEmpty()){
                                TreeLinkNode temp = queue1.poll();
                                if (temp.left != null)
                                        queue2.add(temp.left);
                                if (temp.right != null)
                                        queue2.add(temp.right);
                                if (!queue1.isEmpty()){
                                        temp.next = queue1.peek();
                                }
                        }

                        while (!queue2.isEmpty()){
                                TreeLinkNode temp = queue2.poll();
                                if (temp.left != null)
                                        queue1.add(temp.left);
                                if (temp.right != null)
                                        queue1.add(temp.right);
                                if (!queue2.isEmpty())
                                        temp.next = queue2.peek();
                        }
                }
        }

        //118. Pascal's Triangle
        public List<List<Integer>> generate(int numRows) {
                List<List<Integer>> res = new ArrayList<>();
                if (numRows <=0 )
                        return res;
                for (int i = 0; i <numRows;i++){
                        List<Integer> temp = new ArrayList<>();
                        for (int j = 0; j<=i;j++){
                                if ( i <=0 || j <=0 || j >= res.get(i-1).size())
                                        temp.add(1);
                                else
                                        temp.add(res.get(i-1).get(j) + res.get(i-1).get(j-1));
                        }
                        res.add(temp);
                }
                return res;
        }

        //119. Pascal's Triangle II
        public List<Integer> getRow(int rowIndex) {
                Integer[] integers = new Integer[rowIndex + 1];
                Arrays.fill(integers, 1);
                for (int row = 0; row < rowIndex; row++) {
                        for (int col = row; col > 0; col--) {
                                integers[col] += integers[col - 1];
                        }
                }
                return Arrays.asList(integers);

        }

        //120. Triangle
        public int minimumTotal(List<List<Integer>> triangle) {
                int height = triangle.size();
                int total = (1+height)*height/2;
                int[] dp = new int[total];
                dp[0] = triangle.get(0).get(0);
                if (height >=2){
                        dp[1] = dp[0] + triangle.get(1).get(0);
                        dp[2] = dp[0] + triangle.get(1).get(1);
                }
                for (int i = 2; i < height; i++)
                        for (int j = 0; j < triangle.get(i).size();j++){
                               int up = i*(i-1)/2 + j;
                                int n = (i+1)*i/2 +j;
                                if (j < 1){
                                        dp[n] =dp[up]+ triangle.get(i).get(j);
                                } else if (j > triangle.get(i-1).size()-1)
                                        dp[n] = dp[up-1] + triangle.get(i).get(j);
                                else
                                        dp[n] = Math.min(dp[up],dp[up-1])+ triangle.get(i).get(j);
                        }
                int min = Integer.MAX_VALUE;
                for (int i = total-1;i >= total-triangle.get(height-1).size();i--)
                        min = Math.min(min, dp[i]);
                return min;
        }

        //121. Best Time to Buy and Sell Stock
        /*public int maxProfit(int[] prices) {
                if (prices == null || prices.length == 0)
                        return 0;
                int min = prices[0];
                int[] dp = new int[prices.length];
                dp[0] = 0;
                for (int i =1; i < prices.length; i++){
                        if (prices[i] < min){
                                min= prices[i];
                                dp[i] = dp[i-1];
                        }
                        else
                                dp[i] = Math.max(dp[i-1],prices[i]-min);
                }
                return dp[prices.length-1];
        }*/

        //122. Best Time to Buy and Sell Stock II
        public int maxProfit(int[] prices) {
                if (prices == null || prices.length == 0)
                        return 0;
                int min = prices[0];
                int maxp = 0;
                for (int i = 1; i < prices.length;i++){
                        if (prices[i] < prices[i-1]){
                                maxp += prices[i-1]-min;
                                min = prices[i];
                        }
                        if (i == prices.length-1 && prices[i] > min)
                                maxp += prices[i]-min;
                }
                return maxp;
        }

        //125. Valid Palindrome
        public boolean isPalindrome(String s) {
                if (s == null || s.length() <= 1)
                        return true;
                int start = 0,end = s.length()-1;
                s = s.toLowerCase();
                while (start < end){
                        while (start < s.length() &&!Character.isLetterOrDigit(s.charAt(start)))
                                start++;
                        while (end > 0 && !Character.isLetterOrDigit(s.charAt(end)))
                                end--;
                        if (start <s.length()&&end >0 &&(s.charAt(start) != s.charAt(end)))
                                return false;
                        else{
                                start++;
                                end--;
                        }
                }
                return true;
        }

        //128. Longest Consecutive Sequence
        Set<Integer> set = new HashSet<>();
        public int longestConsecutive(int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                int max = 0;
                for (int i = 0; i <nums.length; i++)
                        set.add(nums[i]);
                for (int i = 0; i < nums.length; i++)
                        max = Math.max(max,longestConsecutiveHelper( nums[i]));
                return max;
        }

        private int longestConsecutiveHelper(int value){
                int count = 1;
                int temp = value - 1;
                while(set.contains(temp)){
                        set.remove(temp);
                        count++;
                        temp--;
                }
                temp = value + 1;
                while (set.contains(temp)){
                        set.remove(temp);
                        count ++;
                        temp++;
                }
                return count;
        }

        //129. Sum Root to Leaf Numbers
        public int sumNumbers(TreeNode root) {
                /*if (root == null)
                        return 0;
                List<Integer> list = new ArrayList<>();
                sumNumbersHelper(root,list,new StringBuffer());
                int sum = 0;
                for (int num : list)
                        sum += num;
                return sum;*/
                return sumNumbersHelper(root,0);
        }

        private void sumNumbersHelper(TreeNode root,List<Integer> list,StringBuffer buffer){
                buffer.append(root.val);
                if (root.left == null && root.right == null){
                        int temp = Integer.valueOf(buffer.toString());
                        list.add(temp);
                        buffer.deleteCharAt(buffer.length() - 1);
                }
                if (root.left != null)
                        sumNumbersHelper(root.left, list, buffer);
                if (root.right != null)
                        sumNumbersHelper(root.right, list, buffer);
        }

        private int sumNumbersHelper(TreeNode root,int val){
                if (root == null)
                        return 0;
                if (root.left == null && root.right == null)
                        return 10 * val + root.val;
                return sumNumbersHelper(root.left,10 * val + root.val) + sumNumbersHelper(root.right,10 * val + root.val);
        }

        //130. Surrounded Regions
        public void solve(char[][] board) {
                if (board == null || board.length == 0)
                        return;
                int n = board.length,m = board[0].length;
                for (int i = 0; i < n;i++){
                        if (board[i][0] == 'O'){
                                board[i][0]='F';
                                solverHelper(board,i,0);
                        }
                        if (board[i][m- 1] == 'O'){
                                board[i][m-1] = 'F';
                                solverHelper(board,i,m- 1);
                        }

                }
                for (int j = 0; j < m;j++){
                        if (board[0][j] == 'O'){
                                board[0][j] = 'F';
                                solverHelper(board,0,j);
                        }
                        if (board[n-1][j] == 'O'){
                                board[n-1][j] = 'F';
                                solverHelper(board,n-1,j);
                        }
                }
                for (int i = 0; i < n ; i++)
                        for (int j = 0; j < m; j++)
                                if (board[i][j] == 'F')
                                        board[i][j] = 'O';
                                else
                                        board[i][j] = 'X';
                System.out.println(Arrays.deepToString(board));
        }

        private void solverHelper(char[][] board,int x,int y){
                int[][] next = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{x, y});
                while (!queue.isEmpty()){
                        int[] temp = queue.poll();
                        for (int i = 0; i <= 3; i++){
                                int tx = temp[0] + next[i][0];
                                int ty = temp[1] + next[i][1];
                                if (tx < 0 || ty < 0 || tx >= board.length || ty >= board[0].length)
                                        continue;
                                if (board[tx][ty] == 'O'){
                                        board[tx][ty] = 'F';
                                        queue.add(new int[]{tx,ty});
                                }
                        }
                }

        }

        //136. Single Number
        public int singleNumber(int[] nums) {
                int sum = 0;
                for (int i = 0; i <32;i++){
                        int temp = 0;
                        for (int num : nums){
                                temp ^=(num>>i & 1);
                        }
                        sum += temp<<i;
                }
                return sum;
        }

        //141. Linked List Cycle
        public boolean hasCycle(ListNode head) {
                if (head == null || head.next == null)
                        return false;
                ListNode slow = head,fast = head.next;
                while (fast!=null && fast.next !=null){
                        if (fast == slow)
                                return true;
                        slow = slow.next;
                        fast = fast.next.next;
                }
                return false;
        }

        //142. Linked List Cycle II
        public ListNode detectCycle(ListNode head) {
                if (head == null || head.next == null)
                        return null;
                ListNode slow = head,fast = head.next;
                while (fast!=null && fast.next != null){
                        if (fast == slow)
                                break;
                        fast = fast.next.next;
                        slow = slow.next;
                }
                if (fast!=slow)
                        return null;
                slow = slow.next;
                while (head!=slow){
                        head = head.next;
                        slow = slow.next;
                }
                return slow;
        }

        //143. Reorder List
        public void reorderList(ListNode head) {
                if (head == null || head.next == null)
                        return;
                ListNode slow = head,fast = head;
                while (fast!=null && fast.next!= null){
                        slow = slow.next;
                        fast = fast.next.next;
                }
                ListNode h1 = head,h2 = slow.next;
                slow.next = null;
                h2=reorderListHelper(h2);
                while (h2!=null){
                        ListNode temp1 = h1.next;
                        ListNode temp2 = h2.next;
                        h1.next = h2;
                        h2.next = temp1;
                        h1 = temp1;
                        h2 = temp2;
                }
        }

        private ListNode reorderListHelper(ListNode head){
                if (head == null || head.next == null)
                        return head;
                ListNode l = new ListNode(0);
                ListNode temp = null;
                while (head!=null){
                        temp = head;
                        head = head.next;
                        temp.next = l.next;
                        l.next = temp;
                }
                return l.next;
        }


        //144. Binary Tree Preorder Traversal
        public List<Integer> preorderTraversal(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                if (root == null)
                        return res;
                Stack<TreeNode> stack = new Stack<>();
                while (root != null){
                        stack.add(root);
                        res.add(root.val);
                        root = root.left;
                }
                while (!stack.isEmpty()){
                        TreeNode temp = stack.pop().right;
                        while (temp != null){
                                res.add(temp.val);
                                stack.add(temp);
                                temp = temp.left;
                        }
                }
                return res;
        }

        //145. Binary Tree Postorder Traversal
        public List<Integer> postorderTraversal(TreeNode root) {
                List<Integer> list = new ArrayList<>();
                if (root == null)
                        return list;
                TreeNode pre = null;
                Stack<TreeNode> stack = new Stack<>();
                stack.push(root);
                while (!stack.isEmpty()){
                        TreeNode cur = stack.peek();
                        if ((cur.left == null && cur.right == null)||(pre!=null&&(pre == cur.left || pre == cur.right))){
                                list.add(cur.val);
                                stack.pop();
                                pre = cur;
                        }else {
                                if (cur.right != null)
                                        stack.push(cur.right);
                                if (cur.left != null)
                                        stack.push(cur.left);
                        }

                }
                return list;
        }

        //148. Sort List
        public ListNode sortList(ListNode head) {
                if (head == null || head.next == null)
                        return head;
                ListNode prev = null,slow = head,fast = head;
                while (fast!= null && fast.next != null){
                        prev = slow;
                        slow = slow.next;
                        fast = fast.next.next;
                }
                prev.next = null;
                ListNode h1 = sortList(head);
                ListNode h2 = sortList(slow);
                return sortListHelper(h1,h2);
        }

        private ListNode sortListHelper(ListNode h1,ListNode h2){
                ListNode pre = new ListNode(0),p = pre;
                while (h1!=null && h2!= null){
                        if (h1.val>h2.val){
                                p.next = h2;
                                h2 = h2.next;
                        }else {
                                p.next = h1;
                                h1 = h1.next;
                        }
                        p = p.next;
                }
                if (h1!=null)
                        p.next = h1;
                if (h2!=null)
                        p.next = h2;
                return pre.next;
        }

        //147. Insertion Sort List
        public ListNode insertionSortList(ListNode head) {
                if (head == null || head.next == null)
                        return head;
                ListNode newHead = new ListNode(0),temp;
                while (head!=null){
                        temp = head;
                        head = head.next;
                        temp.next = null;
                        ListNode pre = newHead,cur = pre.next;
                        while (cur!=null && temp.val > cur.val){
                                pre = pre.next;
                                cur = cur.next;
                        }
                        temp.next = cur;
                        pre.next = temp;
                }
                return newHead.next;
        }

        //151. Reverse Words in a String
        public String reverseWords(String s) {
                if (s == null || s.isEmpty())
                        return s;
                StringBuilder builder = new StringBuilder();
                for (int i = s.length()-1;i>=0;i--){
                        StringBuilder word = new StringBuilder();
                        while (i >=0 && !Character.isWhitespace(s.charAt(i))){
                                word.append(s.charAt(i));
                                i--;
                        }
                        if (word.length()!=0){
                                builder.append(word.reverse().toString());
                                builder.append(" ");
                        }
                }
                if (builder.length()!=0){
                        return builder.substring(0,builder.length()-1);
                }else
                        return "";
        }

        //152. Maximum Product Subarray
        public int maxProduct(int[] nums) {
                int max = nums[0],min = nums[0],result = nums[0];
                for (int i = 1; i < nums.length; i++){
                        int temp = max;
                        max = Math.max((Math.max(max * nums[i],min*nums[i])),nums[i]);
                        min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
                        System.out.println(i + ":  max=" + max + " min=" + min);
                        if (max > result)
                                result = max;
                }
                return max;
        }

        //153. Find Minimum in Rotated Sorted Array
        public int findMin1(int[] nums) {
                //function one
               /* for (int i = 1; i < nums.length;i++){
                        if (nums[i] < nums[i-1])
                                return nums[i];
                }
                return nums[0];*/

                //function two
                int low = 0,high = nums.length-1;
                while (low < high){
                        if (nums[low] < nums[high])
                                return nums[low];
                        int mid = low + (high -low)/2;
                        if (nums[low] <= nums[mid])
                                low = mid+1;
                        else
                                high = mid;
                }
                return nums[low];
        }

        //154. Find Minimum in Rotated Sorted Array II
        public int findMin(int[] nums) {
                int low = 0, high = nums.length-1;
                while (low < high){
                        if (nums[low] < nums[high])
                                return nums[low];
                        int mid = low + (high - low)/2;
                        if (nums[low] > nums[high]){
                                if (nums[mid] >= nums[low])
                                        low = mid + 1;
                                else
                                        high = mid;
                        }else {
                                if (nums[mid] > nums[low])
                                        low = mid+1;
                                else if (nums[mid] < nums[low])
                                        high = mid;
                                else {
                                        while (low < mid && nums[low] == nums[mid])
                                                low++;
                                        if (low == mid)
                                                low = mid+1;
                                }
                        }
                }
                return nums[low];
        }

        //160. Intersection of Two Linked Lists
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
                if (headA == null || headB == null)
                        return null;
                int l1 = 0,l2 = 0;
                ListNode temp = headA;
                while (temp!=null){
                        l1++;
                        temp = temp.next;
                }
                temp = headB;
                while (temp!=null){
                        l2++;
                        temp = temp.next;
                }
                if (l1 >= l2){
                        l1 = l1-l2;
                        while (l1!=0){
                                headA = headA.next;
                                l1--;
                        }
                }else {
                        l2 = l2-l1;
                        while (l2!=0){
                                headB = headB.next;
                                l2--;
                        }
                }
                while (headA!=null && headB!=null){
                        if (headA == headB)
                                return headA;
                        headA = headA.next;
                        headB = headB.next;
                }
                return null;
        }

        //162. Find Peak Element
        public int findPeakElement(int[] nums) {
                if (nums.length == 1)
                        return 0;
                for (int i = 0; i < nums.length; i++){
                        if (i == 0 && nums[i] > nums[i+1])
                                return i;
                        else if (i==nums.length-1 && nums[i-1] < nums[i])
                                return i;
                        else if (i >0 && i <= nums.length-2 &&nums[i] > nums[i-1] && nums[i] > nums[i+1])
                                return i;
                }
                return 0;
        }

        //165. Compare Version Numbers
        public int compareVersion(String version1, String version2) {
                String[] v1 = version1.split("\\.");
                String[] v2 = version2.split("\\.");
                int i = 0;
                while (i < v1.length && i < v2.length){
                        int val1 = Integer.parseInt(v1[i]);
                        int val2 = Integer.parseInt(v2[i]);
                        if (val1 > val2)
                                return 1;
                        else if (val1 < val2)
                                return -1;
                        else
                                i++;
                }
                if (v1.length == v2.length)
                        return 0;
                else if (i < v1.length){
                        while (i < v1.length){
                                if (Integer.parseInt(v1[i]) !=0)
                                        return 1;
                                i++;
                        }
                        return 0;
                }else{
                        while (i < v2.length){
                                if (Integer.parseInt(v2[i]) !=0)
                                        return -1;
                                i++;
                        }
                        return 0;
                }
        }

        //166. Fraction to Recurring Decimal
        public String fractionToDecimal(int numerator, int denominator) {
                StringBuilder builder = new StringBuilder();
                Map<Long,Integer> map = new HashMap<>();
                long num = 0;
                long den = 0;
                if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)){
                        builder.append("-");
                }
                num = Math.abs((long)numerator);
                den = Math.abs((long)denominator);
                long mod = num % den;
                builder.append(num/den);
                if (mod == 0)
                        return builder.toString();
                builder.append(".");
                map.put(mod, builder.length() - 1);
                num = mod * 10;
                while (mod != 0){
                        mod = num % den;
                        builder.append(num/den);
                        if (map.containsKey(mod)){
                                int index = map.get(mod);
                                String temp = builder.substring(index+1);
                                builder.delete(index+1,builder.length());
                                builder.append("(");
                                builder.append(temp);
                                builder.append(")");
                                return builder.toString();
                        }else {
                                map.put(mod, builder.length() - 1);
                                num= mod * 10;
                        }
                }
                return builder.toString();
        }

        //168. Excel Sheet Column Title
        public String convertToTitle(int n) {
                char[] val = new char[]{'Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y'};
                StringBuilder builder = new StringBuilder();
                while (n!=0){
                        builder.insert(0,val[n%26]);
                        if (n %26 == 0)
                                n = n/26 -1;
                        else
                                n/=26;
                }
                return builder.toString();
        }

        //169. Majority Element
        public int majorityElement(int[] nums) {
                int count = 0,majority = nums[0];
                for (int i = 0; i < nums.length; i++){
                        if (count == 0){
                                majority = nums[i];
                                count++;
                        }else {
                                if (majority == nums[i])
                                        count++;
                                else
                                        count--;
                        }
                }
                return majority;
        }

        //171. Excel Sheet Column Number
        public int titleToNumber(String s) {
                int result = 0;
                for (int i = 0; i < s.length();i++){
                        result = result * 26 + (s.charAt(i)-'A' +1);
                }
                return result;
        }

        //172. Factorial Trailing Zeroes
        public int trailingZeroes(int n) {
                int result = 0;
                while (n>0){
                        result += n/5;
                        n/=5;
                }
                return result;
        }

        //174. Dungeon Game
        public int calculateMinimumHP(int[][] dungeon) {
                int n = dungeon.length, m = dungeon[0].length;
                int[][] dp = new int[n][m];
                dp[n-1][m-1] = Math.max(1-dungeon[n-1][m-1],1);
                for (int i = n-2; i >= 0; i--)
                        dp[i][m-1] = Math.max(dp[i+1][m-1]-dungeon[i][m-1],1);
                for (int i = m-2; i >= 0; i--)
                        dp[n-1][i] = Math.max(dp[n-1][i+1] - dungeon[n-1][i+1],1);
                for (int i = n-2;i >=0; i--){
                        for (int j = m-2; j >= 0; j--){
                                int right = Math.max(dp[i][j+1]-dungeon[i][j],1);
                                int down = Math.max(dp[i+1][j] - dungeon[i][j],1);
                                dp[i][j] = Math.min(right,down);
                        }
                }
                return dp[0][0];
        }

        //189. Rotate Array
        public void rotate(int[] nums, int k) {
                //function one
               /* k =  k%nums.length;
                int len = nums.length - k;
                for (int i = 0; i <len/2;i++){
                        int temp = nums[i];
                        nums[i] = nums[len-1-i];
                        nums[len-1-i] = temp;
                }
                for (int i =0; i < k/2;i++){
                        int temp = nums[len+i];
                        nums[len+i] = nums[len + k-i-1];
                        nums[len + k -i-1] = temp;
                }
                for (int i = 0; i < nums.length/2; i++){
                        int temp = nums[i];
                        nums[i] = nums[nums.length - i-1];
                        nums[nums.length - i -1] = temp;
                }*/

                //function two
                int n = nums.length;
                if ((n == 0) || (k <= 0))
                {
                        return;
                }

                int cntRotated = 0;
                int start = 0;
                int curr = 0;
                int numToBeRotated = nums[0];
                int tmp = 0;
                // Keep rotating the elements until we have rotated n
                // different elements.
                while (cntRotated < n)
                {
                        do
                        {
                                tmp = nums[(curr + k)%n];
                                nums[(curr+k)%n] = numToBeRotated;
                                numToBeRotated = tmp;
                                curr = (curr + k)%n;
                                cntRotated++;
                        } while (curr != start);
                        // Stop rotating the elements when we finish one cycle,
                        // i.e., we return to start.

                        // Move to next element to start a new cycle.
                        start++;
                        curr = start;
                        numToBeRotated = nums[curr];
                }
        }


        //199. Binary Tree Right Side View
        public List<Integer> rightSideView(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                if (root == null)
                        return res;
                Queue<TreeNode> queue1 = new LinkedList<>();
                Queue<TreeNode> queue2 = new LinkedList<>();
                queue1.add(root);
                while (!queue1.isEmpty() || !queue2.isEmpty()){
                        while (!queue1.isEmpty()){
                                if (queue1.size() == 1)
                                        res.add(queue1.peek().val);
                                TreeNode temp = queue1.poll();
                                if (temp.left != null)
                                        queue2.add(temp.left);
                                if (temp.right != null)
                                        queue2.add(temp.right);
                        }

                        while (!queue2.isEmpty()){
                                if (queue2.size() == 1)
                                        res.add(queue2.peek().val);
                                TreeNode temp = queue2.poll();
                                if (temp.left != null)
                                        queue1.add(temp.left);
                                if (temp.right != null)
                                        queue1.add(temp.right);
                        }
                }
                return res;
        }

        //200. Number of Islands
        public int numIslands(char[][] grid) {
                if (grid == null || grid.length == 0)
                        return 0;
                int count = 0;
                for (int i  = 0; i < grid.length ; i++){
                        for (int j = 0; j < grid[0].length; j++){
                                if (grid[i][j] == '1'){
                                        count++;
                                        grid[i][j] = '2';
                                        //numIslandsHelper(grid,i,j);
                                        numIslandsHelper2(grid,i,j);
                                }
                        }
                }
                return count;
        }

        //DFS
        private void numIslandsHelper(char[][] grid,int x,int y){
                int[][] next = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
                int tx = 0,ty;
                for (int i = 0; i <= 3; i++){
                        tx = x + next[i][0];
                        ty = y + next[i][1];
                        if (tx < 0 || ty < 0 || tx >= grid.length || ty >= grid[0].length)
                                continue;
                        if (grid[tx][ty] == '1'){
                                grid[tx][ty] = '2';
                                //System.out.println("x=" + tx + "," + "y=" + ty);
                                numIslandsHelper(grid,tx,ty);
                        }
                }

        }

        //Number of Islands (BFS)
        public void numIslandsHelper2(char[][] grid,int x,int y){
                int[][] next = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
                int tx ,ty;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{x, y});
                while (!queue.isEmpty()){
                        int[] temp = queue.poll();
                        for (int i = 0; i <= 3; i++){
                                tx = temp[0] + next[i][0];
                                ty = temp[1] + next[i][1];
                                if (tx < 0 || ty < 0 || tx >= grid.length || ty >= grid[0].length)
                                        continue;
                                if (grid[tx][ty] == '1'){
                                        grid[tx][ty] = '2';
                                        queue.add(new int[]{tx,ty});
                                }
                        }
                }
        }

        //202. Happy Number
        public boolean isHappy(int n) {
                Set<Integer> set = new HashSet<>();
                while (n != 1){
                        if (!set.add(n))
                                return false;
                        n = isHappyHelper(n);
                }
                return true;
        }

        private int isHappyHelper(int n){
                String s = String.valueOf(n);
                int sum = 0;
                for (int i = 0;i < s.length(); i++){
                        int temp = Integer.parseInt(String.valueOf(s.charAt(i)));
                        sum += temp * temp;
                }
                return sum;
        }

        //203. Remove Linked List Elements
        public ListNode removeElements(ListNode head, int val) {
                if (head == null)
                        return null;
                ListNode newHead = new ListNode(0),pre = newHead,p=head;
                newHead.next = head;
                while (p!=null){
                        if (p.val==val){
                                pre.next = p.next;
                                p = pre.next;
                        }else {
                                pre = pre.next;
                                p = p.next;
                        }
                }
                return newHead.next;
        }

        //204. Count Primes
        public int countPrimes(int n) {
                int count = 0;
                for (int i = 1; i <n;i++)
                        if (isPrimes(i))
                                count++;
                return count;
        }

        private boolean isPrimes(int n){
                if (n <= 3)
                        return n>1;
                if (n % 2 == 0 || n % 3 == 0)
                        return false;
                for (int i = 5; i * i <=n;i+=6){
                        if (n % i == 0 || n %(i+2) ==0)
                                return false;
                }
                return true;
        }

        //205. Isomorphic Strings
        public boolean isIsomorphic(String s, String t) {
                Map<Character,Character> map1 = new HashMap<>();
                Map<Character,Character> map2 = new HashMap<>();
                for (int i = 0; i < s.length(); i++){
                        char sch = s.charAt(i);
                        char tch = t.charAt(i);
                        if ((map1.containsKey(sch) && map1.get(sch) != tch)
                                || (map2.containsKey(tch) && map2.get(tch) != sch))
                                return false;
                        map1.put(sch,tch);
                        map2.put(tch,sch);
                }
                return true;
        }

        //206. Reverse Linked List
        public ListNode reverseList(ListNode head) {
                if (head == null || head.next == null)
                        return head;
                ListNode l = new ListNode(0);
                ListNode temp = null;
                while (head!=null){
                        temp = head;
                        head = head.next;
                        temp.next = l.next;
                        l.next = temp;
                }
                return l.next;
        }

        //209. Minimum Size Subarray Sum
        public int minSubArrayLen(int s, int[] nums) {
                if (nums == null || nums.length == 0)
                        return 0;
                int start = 0, end = 0,sum = 0,min = Integer.MAX_VALUE;
                while (end < nums.length){
                        while (end < nums.length && sum < s)
                                sum+=nums[end++];
                        if (sum < s)
                                break;
                        while (start < end && sum >= s)
                                sum-=nums[start++];
                        if (end - start + 1 < min) min = end - start + 1;
                }
                return min == Integer.MAX_VALUE ?0:min;
        }


        //214. Shortest Palindrome
        public String shortestPalindrome(String s) {
                if (s == null || s.isEmpty())
                        return s;
                int j = 0;
                for (int i = s.length()-1;i>=0;i--){
                        if (s.charAt(i) == s.charAt(j))
                                j++;
                }
                if (j == s.length())
                        return s;
                String s1 = s.substring(j);
                return new StringBuilder(s1).reverse().toString() + shortestPalindrome(s.substring(0,j)) + s1;
        }


        //215. Kth Largest Element in an Array
        public int findKthLargest(int[] nums, int k) {
                int low = 0,high = nums.length-1;
                while (low < high){
                        int index = findKthLargestHelper(nums,low,high);
                        if (index == nums.length-k)
                                return nums[index];
                        else if (index > nums.length-k)
                                high = index-1;
                        else
                                low = index+1;

                }
                return nums[low];
        }

        private int findKthLargestHelper(int[] nums,int low ,int high){
                int temp = nums[low];
                int left = low;
                int target = nums[low];
                while (low != high){
                        while (nums[high] >=target && low < high)
                                high--;
                        while ( nums[low] <= target && low < high)
                                low++;
                        if (low < high){
                                temp = nums[low];
                                nums[low] = nums[high];
                                nums[high] = temp;
                        }
                }
                nums[left] = nums[low];
                nums[low] = target;
                return low;
        }


        //216. Combination Sum III
        public List<List<Integer>> combinationSum3(int k, int n) {
                List<List<Integer>> res = new ArrayList<>();
                combinationSum3Helper(k,1,n,new ArrayList<Integer>(),res);
                return res;
        }

        private void combinationSum3Helper(int k,int start,int target,List<Integer> list,List<List<Integer>> res){
                if (target == 0 && list.size() == k){
                        res.add(new ArrayList<Integer>(list));
                        return;
                }
                if (target < 0){
                        return;
                }
                for (int i=start; i<=9;i++){
                        list.add(i);
                        combinationSum3Helper(k,  i + 1, target - i, list, res);
                        list.remove(list.size()-1);
                }
        }

        //217. Contains Duplicate
        public boolean containsDuplicate(int[] nums) {
                if (nums == null || nums.length == 0)
                        return false;
                Set<Integer> set = new HashSet<>();
                for (int num:nums){
                        if (!set.contains(num))
                                set.add(num);
                        else
                                return true;
                }
                return false;
        }

        //219. Contains Duplicate II
        public boolean containsNearbyDuplicate(int[] nums, int k) {
                if (nums == null || nums.length == 0)
                        return false;
                Map<Integer,Integer> map = new HashMap<>();
                for (int i = 0; i < nums.length; i++){
                        if (!map.containsKey(nums[i])){
                                map.put(nums[i],i);
                        }else {
                                if (i-map.get(nums[i]) <= k)
                                        return true;
                                map.put(nums[i],i);
                        }
                }
                return false;
        }


        //222. Count Complete Tree Nodes
        public int countNodes(TreeNode root) {
                //time limit
               /* if(root == null)
                        return 0;
                int count = 1;
                Queue<TreeNode> queue = new LinkedList<>();
                queue.add(root);
                while (!queue.isEmpty()){
                        TreeNode  temp = queue.poll();
                        if (temp.left != null && temp.right != null){
                                count++;
                                queue.add(temp.left);
                                queue.add(temp.right);
                        }else if (temp.left != null && temp.right == null){
                                return 2 * count;
                        }else if ( temp.right == null)
                                return 2 * (count-1) + 1;
                }
                return -1;*/
                if (root == null)
                        return 0;
                int leftHeight = countNodesHelper(root.left);
                int rightHeight = countNodesHelper(root.right);
                if (leftHeight == rightHeight)
                        return (1<<leftHeight) + countNodes(root.right);
                else
                        return (1<<rightHeight) + countNodes(root.left);
        }

        //get tree depth
        private int countNodesHelper(TreeNode node){
                int depth = 0;
                while (node != null){
                        depth++;
                        node = node.left;
                }
                return depth;
        }

        //223. Rectangle Area
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
                int left = Math.max(A,E), right = Math.max(Math.min(G, C),left);
                int bottom = Math.max(B,F), top = Math.max(Math.min(D,H),bottom);
                return (C-A)*(D-B) - (right-left) * (top-bottom) +(G-E)*(H-F);
        }


        //226. Invert Binary Tree
        public TreeNode invertTree(TreeNode root) {
                if (root == null)
                        return null;
                TreeNode temp = root.left;
                root.left = invertTree(root.right);
                root.right = invertTree(temp);
                return root;
        }

        //227. Basic Calculator II
        public int calculate(String s) {
                if (s == null || s.isEmpty())
                        return 0;
                String[] val = s.split("[^0-9 ]");
                int index = 0;
                Stack<String> stack = new Stack<>();
                stack.push(val[0].trim());
                for (int i = 0; i < s.length();i++){
                       switch (s.charAt(i)){
                               case '+':
                                       stack.push("+");
                                       stack.push(val[++index].trim());
                                       break;
                               case '-':
                                       stack.push("-");
                                       stack.push(val[++index].trim());
                                       break;
                               case '*':
                                       int num1 = Integer.parseInt(stack.pop());
                                       int num2 = Integer.parseInt(val[++index].trim());
                                       stack.push(String.valueOf(num1*num2));
                                       break;
                               case '/':
                                       num1 = Integer.parseInt(stack.pop());
                                       num2 = Integer.parseInt(val[++index].trim());
                                       stack.push(String.valueOf(num1/num2));
                                       break;
                       }
                }
                if (stack.size() == 1)
                        return Integer.parseInt(stack.pop());
                else {
                        int sum = 0;
                        int temp = 0;
                        while (!stack.isEmpty()){
                                temp = Integer.parseInt(stack.pop());
                                if (stack.isEmpty())
                                        break;
                                if (stack.pop().equals("-"))
                                        temp=-temp;
                                sum+=temp;
                        }
                        sum += temp;
                        return sum;
                }
        }

        //228. Summary Ranges
        public List<String> summaryRanges(int[] nums) {
                List<String> res = new ArrayList<>();
                if (nums == null || nums.length == 0)
                        return res;
                if(nums.length == 1){
                        res.add(String.valueOf(nums[0]));
                        return res;
                }
                int start =0,end = 0;
                for (;end<nums.length-1;end++){
                        if (end == nums.length -2){
                                if (nums[end] !=  nums[end+1]-1){
                                       if (start == end){
                                               res.add(String.valueOf(nums[end]));
                                       }else {
                                               res.add(nums[start] +"->" + nums[end]);
                                       }
                                        res.add(String.valueOf(nums[end+1]));
                                }else
                                        res.add(nums[start] + "->" + nums[end+1]);
                        }else {
                                if (nums[end] +1 !=  nums[end+1]){
                                        if (start == end){
                                                res.add(String.valueOf(nums[end]));
                                                start++;
                                        }
                                        else{
                                                res.add(nums[start] + "->" + nums[end]);
                                                start = end+1;
                                        }
                                }
                        }
                }
                return res;
        }

        //230. Kth Smallest Element in a BST
        public int kthSmallest(TreeNode root, int k) {
                List<Integer> list = new ArrayList<>();
                kthSmallestHelper(list,root);
                return list.get(k-1);
        }

        private void kthSmallestHelper(List<Integer> list, TreeNode root){
                if (root != null){
                        kthSmallestHelper(list,root.left);
                        list.add(root.val);
                        kthSmallestHelper(list,root.right);
                }
        }

        //231. Power of Two
        public boolean isPowerOfTwo(int n) {
                /*if (n == 0)
                        return false;
                while (n %2 == 0)
                        n /=2;
                return n == 1;*/
                if (n < 0)
                        return false;
                return (n & (n-1)) == 0;
        }

        //235. Lowest Common Ancestor of a Binary Search Tree
        /*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
                if (root == null || p == null || q == null)
                        return null;
                if (p == root || q == root || (p.val > root.val && q.val < root.val) || (p.val < root.val && q.val >root.val))
                        return root;
                if (p.val > root.val && q.val > root.val)
                        return lowestCommonAncestor(root.right,p,q);
                return lowestCommonAncestor(root.left,p,q);
        }*/

        //236. Lowest Common Ancestor of a Binary Tree
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
                if (root == null || p == null || q == null)
                        return null;
                if (root == p || root == q)
                        return root;
                TreeNode left = lowestCommonAncestor(root.left,p,q);
                TreeNode right = lowestCommonAncestor(root.right,p,q);
                 return left == null ? (right == null ? null : right) : (right == null ? left:root);
        }

        //238. Product of Array Except Self
        public int[] productExceptSelf(int[] nums) {
                if (nums == null || nums.length == 0)
                        return new int[0];
                int[] output = new int[nums.length];
                output[0] = 1;
                for (int i = 0; i < nums.length;i++){
                        if (i > 0)
                                output[i] = output[i-1] * nums[i-1];
                }
                int temp = 1;
                for (int i = nums.length -1;i>=0;i--){
                        output[i] *= temp;
                        temp *= nums[i];
                }
                return output;
        }

        //234. Palindrome Linked List
        public boolean isPalindrome(ListNode head) {
                if (head == null)
                        return false;
                if (head.next == null)
                        return true;
                ListNode h1 = new ListNode(0),slow = head,fast = head,temp;
                while (fast!=null && fast.next!=null){
                        temp = slow;
                        slow = slow.next;
                        fast = fast.next.next;
                        temp.next = h1.next;
                        h1.next = temp;
                }
                temp = h1.next;
                if (fast!=null)
                        slow = slow.next;
                while (temp!=null){
                        if (temp.val != slow.val)
                                return false;
                        temp = temp.next;
                        slow = slow.next;
                }
                return true;
        }

        //240. Search a 2D Matrix II
        public boolean searchMatrix(int[][] matrix, int target) {
                int col = matrix[0].length-1;
                int row = 0;
                while (col >=0 && row < matrix.length){
                        if (matrix[row][col] == target)
                                return true;
                        else if (matrix[row][col] > target)
                               col--;
                        else
                                row++;
                }
                return false;
        }

        //241. Different Ways to Add Parentheses
        public List<Integer> diffWaysToCompute(String input) {
                List<Integer> result = new ArrayList<>();
                if (input.matches("\\d+")){
                        result.add(Integer.parseInt(input));
                        return result;
                }
                for (int i = 0; i < input.length();i++){
                        if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                                for (int val1 : left){
                                        for (int val2 : right){
                                                switch (input.charAt(i)){
                                                        case '+':
                                                                result.add(val1+val2);
                                                                break;
                                                        case '-':
                                                                result.add(val1-val2);
                                                                break;
                                                        case '*':
                                                                result.add(val1 * val2);
                                                                break;
                                                }
                                        }
                                }
                        }
                }
                return result;
        }

        //242. Valid Anagram
        public boolean isAnagram(String s, String t) {
                if (s == null || t == null)
                        return false;
                int[] mark = new int[26];
                if (s.length() != t.length())
                        return false;
                for (int i = 0; i < s.length(); i++){
                        mark[s.charAt(i)-'a']++;
                        mark[t.charAt(i)-'a']--;
                }
                for (int num:mark)
                        if (num!=0)
                                return false;
                return true;
        }

        //257. Binary Tree Paths
        public List<String> binaryTreePaths(TreeNode root) {
                List<String> res = new ArrayList<>();
                binaryTreePathsHelper(root, new StringBuilder(), res);
                return res;
        }

        private void binaryTreePathsHelper(TreeNode root,StringBuilder builder,List<String> result){
                if (root == null)
                        return;
                if (root.left == null && root.right == null){
                        int length = builder.length();
                        builder.append(root.val);
                        result.add(builder.toString());
                        builder.delete(length,builder.length());
                        return;
                }
                int length = builder.length();
                builder.append(root.val);
                builder.append("->");
                binaryTreePathsHelper(root.left, builder, result);
                binaryTreePathsHelper(root.right, builder, result);
                builder.delete(length, builder.length());
        }

        //258. Add Digits
        public int addDigits(int num) {
                if (num == 0)
                        return 0;
                int total = 0;
                while (num !=0){
                        total += num%10;
                        num /= 10;
                }
                return total %9 == 0?9:total%9;
        }

        //263. Ugly Number
        public boolean isUgly(int num) {
                if (num <=1)
                        return num == 1;
                if (num % 2 == 0)
                        return isUgly(num/2);
                if (num%3 == 0)
                        return isUgly(num/3);
                if (num%5 == 0)
                        return isUgly(num/5);
                return false;
        }

        //264. Ugly Number II
        public int nthUglyNumber(int n) {
                int indexof2 = 0,indexof3=0,indexof5=0,min=1;
                int min2 =2,min3 = 3,min5 = 5;
                int[] dp = new int[n];
                dp[0] = 1;
                for (int i = 1; i < n;i++){
                        min = Math.min(min2,Math.min(min3,min5));
                        dp[i] = min;
                        if (min == min2)
                                min2 = 2 * dp[++indexof2];
                        if (min == min3)
                                min3= 3 * dp[++indexof3];
                        if (min == min5)
                                min5 = 5 * dp[++indexof5];
                }
                return dp[n-1];
        }

        //268. Missing Number
        public int missingNumber(int[] nums) {
                //function one
                /*for (int i = 0; i < nums.length; i++){
                        int next = nums[i];
                        while (next < nums.length && next != -1&& nums[next] != -1){
                                int temp = nums[next];
                                nums[next] = -1;
                                next = temp;
                        }
                }
                for (int i = 0;i < nums.length;i++){
                        if (nums[i] !=-1)
                                return i;
                }
                return nums.length;*/

                //function two
                int result = nums.length;
                for (int i = 0; i < nums.length;i++)
                        result ^= nums[i];
                for (int i = 0;i < nums.length;i++)
                        result ^= i;
                return result;
        }

        //278. First Bad Version


        //279. Perfect Squares
        public int numSquares(int n) {
                //DP
                /*int[] dp = new int[n +1];
                Arrays.fill(dp,Integer.MAX_VALUE);
                dp[0] = 0;
                for (int i = 1; i <=n;i++){
                        for (int j = 1; j*j<=i;j++){
                                dp[i] = Math.min(dp[i],dp[i-j*j] + 1);
                        }
                }
                return dp[n];*/

                //BFS
                /*
                * 把0,1,2.....n当做节点，如果i到j有边存在，则表示i = j + （一个平方数），
                * 或者 j = i + （一个平方数），从节点0开始做广度优先遍历，如果到达节点n
                * 用来m步，则表示n至少由m个平方数构成
                * */
                //mark[i-1]表示i至少由几个平方数构成
                int[] mark = new int[n];
                List<Integer> prefectSquare = new ArrayList<>();
                for (int i = 1; i * i <= n; i++){
                        prefectSquare.add(i*i);
                        mark[i*i-1] = 1;
                }
                if (prefectSquare.get(prefectSquare.size() - 1) == n)
                        return 1;
                Queue<Integer> queue = new LinkedList<>();
                for (int num: prefectSquare)
                        queue.add(num);
                int curSquare = 1;
                while (!queue.isEmpty()){
                        curSquare++;
                        int size = queue.size();
                        for (int i = 0; i < size;i++){
                                int tem = queue.peek();
                                for (int num:prefectSquare){
                                        if (tem + num == n)
                                                return curSquare;
                                        else if (tem + num < n && mark[tem+num -1] == 0){
                                                mark[tem + num - 1] = curSquare;
                                                queue.add(tem + num);
                                        }else
                                                break;
                                }
                                queue.poll();
                        }
                }
                return 0;
        }

        //283. Move Zeroes
        public void moveZeroes(int[] nums) {
                if (nums == null || nums.length == 0)
                        return;
                int index = 0;
                for (int i = 0; i<nums.length;i++)
                        if (nums[i] !=0)
                                nums[index++]=nums[i];
                for (;index<nums.length;index++)
                        nums[index] = 0;
        }

        //287. Find the Duplicate Number
        /*public int findDuplicate(int[] nums) {

        }*/

        //290. Word Pattern
        public boolean wordPattern(String pattern, String str) {
                String[] word = str.split(" ");
                if (pattern.length() != word.length)
                        return false;
                Map<String,String> map1 = new HashMap<>();
                Map<String,String> map2 = new HashMap<>();
                for (int i = 0; i < pattern.length(); i++){
                        String p = String.valueOf(pattern.charAt(i));
                        if (map1.containsKey(p) && !map1.get(p).equals(word[i]))
                                return false;
                        if (map2.containsKey(word[i]) && !map2.get(word[i]).equals(p))
                                return false;
                        map1.put(p,word[i]);
                        map2.put(word[i],p);
                }
               return true;
        }

        //297. Serialize and Deserialize Binary Tree
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
                if (root == null)
                        return null;
                StringBuilder builder = new StringBuilder(root.val + ",");
                Queue<TreeNode> queue = new LinkedList<>();
                queue.add(root);
                while (!queue.isEmpty()){
                        TreeNode node = queue.poll();
                        if (node.left != null){
                                queue.add(node.left);
                                builder.append(node.left.val).append(",");
                        }
                        else
                                builder.append("*").append(",");
                        if (node.right != null){
                                queue.add(node.right);
                                builder.append(node.right.val).append(",");
                        }
                        else
                                builder.append("*").append(",");
                }
                return builder.deleteCharAt(builder.length() - 1).toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
                if (data == null || data.length() == 0)
                        return null;
                List<TreeNode> list = new ArrayList<>();
                String[] token = data.split(",");
                list.add(new TreeNode(Integer.parseInt(token[0])));
                for (int i = 0, count = 0; i < token.length; count++,i+=2){
                        if (i + 1 < token.length && !"*".equals(token[i + 1])){
                                list.get(count).left = new TreeNode(Integer.parseInt(token[i+1]));
                                list.add(list.get(count).left);
                        }
                        if (i+2 < token.length && !"*".equals(token[i+2])){
                                list.get(count).right = new TreeNode(Integer.parseInt(token[i+2]));
                                list.add(list.get(count).right);
                        }
                }
                return list.get(0);
        }

        //300. Longest Increasing Subsequence
        public int lengthOfLIS(int[] nums) {
                int[] dp = new int[nums.length];
                int index = 0,len = 0;
                for (int i = 0; i < nums.length;i++){
                        index = Arrays.binarySearch(dp,0,len,nums[i]);
                        if (index < 0)
                                index = -(index+1);
                        dp[index] = nums[i];
                        if (index == len)
                                len++;
                }
                return len;
        }

        //310. Minimum Height Trees
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
                //time limit
                /*List<Integer> res = new ArrayList<>();
                if (n<= 0 || edges == null || edges.length == 0)
                        return res;
                int[][] edge = new int[n][n];
                for (int[] arr : edges){
                        edge[arr[0]][arr[1]] = 1;
                        edge[arr[1]][arr[0]] = 1;
                }
                int[] mark = new int[n];
                int[] height = new int[n];
                Queue<Integer> queue1 = new LinkedList<>();
                Queue<Integer> queue2 = new LinkedList<>();
                int minHeight = n;
                for (int i = 0; i < n; i++){
                        Arrays.fill(mark,0);
                        mark[i] = 1;
                        queue1.add(i);
                        while (!queue1.isEmpty() || !queue1.isEmpty()){
                                if (!queue1.isEmpty()){
                                        height[i]++;
                                        while (!queue1.isEmpty()){
                                                int temp = queue1.poll();
                                                for (int j = 0; j < n; j++){
                                                        if (edge[temp][j] == 1 && mark[j] == 0){
                                                                queue2.add(j);
                                                                mark[j] = 1;
                                                        }
                                                }
                                        }
                                }
                                if (!queue2.isEmpty()){
                                        height[i]++;
                                        while (!queue2.isEmpty()){
                                                int temp = queue2.poll();
                                                for (int j = 0; j < n; j++){
                                                        if (edge[temp][j] == 1 && mark[j] == 0){
                                                                queue1.add(j);
                                                                mark[j] = 1;
                                                        }
                                                }
                                        }
                                }
                        }
                        minHeight = Math.min(minHeight,height[i]);
                }
                for (int i = 0; i < n; i++)
                        if (height[i] == minHeight)
                                res.add(i);
                return res;*/
                if (n == 1) return Collections.singletonList(0);

                List<Set<Integer>> adj = new ArrayList<>(n);
                for (int i = 0; i < n; ++i) adj.add(new HashSet<Integer>());
                for (int[] edge : edges) {
                        adj.get(edge[0]).add(edge[1]);
                        adj.get(edge[1]).add(edge[0]);
                }

                List<Integer> leaves = new ArrayList<>();
                for (int i = 0; i < n; ++i)
                        if (adj.get(i).size() == 1) leaves.add(i);

                while (n > 2) {
                        n -= leaves.size();
                        List<Integer> newLeaves = new ArrayList<>();
                        for (int i : leaves) {
                                int j = adj.get(i).iterator().next();
                                adj.get(j).remove(i);
                                if (adj.get(j).size() == 1) newLeaves.add(j);
                        }
                        leaves = newLeaves;
                }
                return leaves;

        }

        //313. Super Ugly Number
        public int nthSuperUglyNumber(int n, int[] primes) {
                int[] dp = new int[n];
                dp[0] = 1;
                int[] index = new int[primes.length];
                for (int i = 1; i < n;i++){
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

        //319. Bulb Switcher
        public int bulbSwitch(int n) {
                return (int) Math.sqrt(n);
        }

        //327. Count of Range Sum
        /*public int countRangeSum(int[] nums, int lower, int upper) {

        }*/

        //328. Odd Even Linked List
        public ListNode oddEvenList(ListNode head) {
                if (head == null || head.next == null)
                        return head;
                ListNode oddHead = new ListNode(0),evenHead = new ListNode(0);
                ListNode temp1 = oddHead,temp2 = evenHead;
                while (head!=null){
                        temp1.next = head;
                        temp2.next = head.next;
                        if (head.next == null || head.next.next == null)
                                break;
                        else
                                head = head.next.next;
                        temp1 = temp1.next;
                        temp2 = temp2.next;
                        temp1.next = null;
                        temp2.next = null;
                }
                if (head!=null){
                        temp1.next = head;
                        temp1 = temp1.next;
                }
                temp1.next = evenHead.next;
                return oddHead.next;
        }


        //332. Reconstruct Itinerary
        public List<String> findItinerary(String[][] tickets) {
                List<String> res = new ArrayList<>();
                if (tickets == null || tickets.length == 0)
                        return res;
                Map<String,PriorityQueue<String>> map = new HashMap<>();
                for (String[] ticket : tickets){
                        if (map.containsKey(ticket[0]))
                                map.get(ticket[0]).add(ticket[1]);
                        else {
                                PriorityQueue<String> temp = new PriorityQueue<>();
                                temp.add(ticket[1]);
                                map.put(ticket[0],temp);
                        }
                }
                String start = "JFK";
                res.add(start);
                while (map.containsKey(start)){
                        PriorityQueue<String> temp = map.get(start);
                        if (!temp.isEmpty()){
                                res.add(temp.peek());
                                start = temp.poll();
                        }else{
                                map.remove(start);
                        }
                }
                return res;
        }

        //326. Power of Three
        public boolean isPowerOfThree(int n) {
                return (Math.log10(n)/Math.log10(3)) % 1 == 0;
        }

        //329. Longest Increasing Path in a Matrix
        public int longestIncreasingPath(int[][] matrix) {
                if (matrix == null || matrix.length == 0)
                        return 0;
                int[][] record = new int[matrix.length][matrix[0].length];
                int max = 0;
                for (int i = 0; i < matrix.length; i++)
                        for (int j = 0; j < matrix[0].length;j++){
                                max = Math.max(max,longestIncreasingPathHelper(matrix, record, i, j, Integer.MIN_VALUE));
                        }
                System.out.println(Arrays.deepToString(record));
                return max;
        }

        private int longestIncreasingPathHelper(int[][] matrix,int[][] record,int x,int y,int val){
                if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length)
                        return 0;
                if (matrix[x][y] > val){
                        if (record[x][y] != 0)
                                return record[x][y];
                        int left = longestIncreasingPathHelper(matrix, record, x - 1, y, matrix[x][y]) + 1;
                        int right = longestIncreasingPathHelper(matrix, record, x + 1, y, matrix[x][y]) + 1;
                        int up = longestIncreasingPathHelper(matrix, record, x, y - 1, matrix[x][y]) + 1;
                        int down = longestIncreasingPathHelper(matrix, record, x, y + 1, matrix[x][y]) + 1;
                        record[x][y] = Math.max(left,Math.max(right,Math.max(up,down)));
                        return record[x][y];
                }
                return 0;
        }

        //335. Self Crossing
        // 1. Fourth line crosses first line and works for fifth line crosses second line and so on...
        // 2. Fifth line meets first line and works for the lines after
        // 3. Sixth line crosses first line and works for the lines after
        public boolean isSelfCrossing(int[] x) {
                if (x == null || x.length < 4)
                        return false;
                for (int i = 3; i < x.length;i++){
                        if (x[i] >= x[i-2] && x[i-1] <=x[i-3] ) return true; //1
                        if (i >=4){
                                if (x[i-3] == x[i-1] && x[i-2] <= x[i] + x[i-4])//2
                                        return true;
                        }
                        if (i >=5){
                                if (x[i-2] >= x[i-4] && x[i] >= x[i-2]-x[i-4] && x[i-1]<=x[i-3] && x[i-1] >= x[i-3]-x[i-5])//3
                                        return true;
                        }
                }
                return false;
        }

        //336. Palindrome Pairs
        public List<List<Integer>> palindromePairs(String[] words) {
                List<List<Integer>> result = new ArrayList<>();
                if (words == null || words.length == 0)
                        return result;
                Map<String,Integer> map = new HashMap<>();
                for (int i = 0; i < words.length;i++){
                        map.put(words[i], i);
                }
                for (int i = 0; i < words.length;i++){
                        for (int j = 0;j<=words[i].length();j++){
                                String str1 = words[i].substring(0,j);
                                String str2 = words[i].substring(j);
                                if (isPalindromeOfString(str1)){
                                        String temp = new StringBuilder(str2).reverse().toString();
                                        if (map.containsKey(temp) && map.get(temp) != i){
                                                List<Integer> list = new ArrayList<>();
                                                list.add(map.get(temp));
                                                list.add(i);
                                                if (!result.contains(list))
                                                        result.add(list);
                                        }
                                }
                                if (isPalindromeOfString(str2)){
                                        String temp = new StringBuilder(str1).reverse().toString();
                                        if (map.containsKey(temp)&&map.get(temp) != i){
                                                List<Integer> list = new ArrayList<>();
                                                list.add(i);
                                                list.add(map.get(temp));
                                                if (!result.contains(list))
                                                        result.add(list);
                                        }
                                }
                        }
                }
                return result;
        }


        private boolean isPalindromeOfString(String str){
                int low = 0,high = str.length()-1;
                while (low < high){
                        if (str.charAt(low) != str.charAt(high))
                                return false;
                        low++;
                        high--;
                }
                return true;
        }

        //338. Counting Bits
        /*
        * 1001 可以分成两个部分 100 和 1，所以1001中1的个数等于100中1的个数加上最后一位1的个数
        * 所以dp[i] = dp[i >> 1](向右移动一位) + (i & 1) （i的最后一位是否为1）
        *
        * */
        public int[] countBits(int num) {
                int[] dp = new int[num+1];
                for (int i = 1; i <= num;i++)
                        dp[i] = dp[i >> 1] + (i & 1);
                return dp;
        }

        //342. Power of Four
        public boolean isPowerOfFour(int num) {

                if (num <= 0 || ((num -1) & num) != 0)
                        return false;
                int n = 0;
                while ((num & 1) != 1){
                        num = num >> 1;
                        n++;
                }

                return n > 1 && n % 2 == 0;
                //return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;

        }

        //343. Integer Break
        public int integerBreak(int n) {
                if (n < 4)
                        return n-1;
                int sum = 1;
                while ( n >= 3){
                        if (n == 4){
                                sum  *= 4 ;
                                break;
                        }else if (n == 5){
                                sum *= 6;
                                break;
                        }else {
                                sum *= 3;
                                n -= 3;
                        }
                }
                return sum;
        }

        //344. Reverse String
        public String reverseString(String s) {
                if (s == null || s.isEmpty())
                        return s;
                char[] chars = s.toCharArray();
                int low=0,high = chars.length-1;
                while (low < high){
                        char temp = chars[low];
                        chars[low] = chars[high];
                        chars[high] = temp;
                        low++;
                        high--;
                }
                return new String(chars);
        }

        //345. Reverse Vowels of a String
        public String reverseVowels(String s) {
                if (s == null || s.isEmpty())
                        return s;
                List<Character> vowels = new ArrayList<>();
                vowels.add('a');
                vowels.add('e');
                vowels.add('i');
                vowels.add('o');
                vowels.add('u');
                int low = 0,high = s.length()-1;
                char[] chars = s.toCharArray();
                while (low != high){
                        while (low < s.length() && !vowels.contains(Character.toLowerCase(chars[low])))
                                low++;
                        while (high>=0 && !vowels.contains(Character.toLowerCase(chars[high])))
                                high--;
                        if (low > high)
                                break;
                        char temp = chars[low];
                        chars[low] = chars[high];
                        chars[high] = temp;
                        low++;
                        high--;
                }
                return new String(chars);
        }

        //347. Top K Frequent Elements
        public List<Integer> topKFrequent(int[] nums, int k) {
                List<Integer>[] frequent = new List[nums.length+1];
                Map<Integer,Integer> count = new HashMap<>();
                for (int num : nums){
                        if (count.containsKey(num))
                                count.put(num,count.get(num) +1);
                        else
                                count.put(num,1);
                }
                count.forEach((key, value) -> {
                        if (frequent[value] == null)
                                frequent[value] = new ArrayList<Integer>();
                        frequent[value].add(key);
                });
                List<Integer> result = new ArrayList<>();
                for (int i = frequent.length-1; i > 0 && k != 0; i--){
                        if (frequent[i] != null){
                                for (int j = 0; j < frequent[i].size() && k !=0; j++,k--)
                                        result.add(frequent[i].get(j));
                        }
                }
                return result;
        }

        //349. Intersection of Two Arrays
        public int[] intersection(int[] nums1, int[] nums2) {
                Set<Integer> set1 = new HashSet<>();
                Set<Integer> set2 = new HashSet<>();
                for (int num:nums1)
                        set1.add(num);
                for (int num:nums2){
                        if (set1.contains(num))
                                set2.add(num);
                }
                int[] res = new int[set2.size()];
                int i = 0;
                for (int num : set2){
                        res[i++] = num;
                }
                return res;
        }

        //350. Intersection of Two Arrays II
        public int[] intersect(int[] nums1, int[] nums2) {
                Map<Integer,Integer> map1 = new HashMap<>();
                Map<Integer,Integer> map2 = new HashMap<>();
                for (int num : nums1){
                        if (map1.containsKey(num)){
                                int count = map1.get(num) + 1;
                                map1.put(num,count);
                        }else
                                map1.put(num,1);
                }

                for (int num : nums2){
                        if (map2.containsKey(num)){
                                int count = map2.get(num) + 1;
                                map2.put(num,count);
                        }else
                                map2.put(num, 1);
                }

                List<Integer> list = new ArrayList<>();
                for (int key : map1.keySet()){
                        if (map2.containsKey(key)){
                                int count1 = map1.get(key);
                                int count2 = map2.get(key);
                                int i = count1 > count2 ? count2 : count1;
                                while (i > 0){
                                        list.add(key);
                                        i--;
                                }
                        }
                }
                int[] res = new int[list.size()];
                for (int i = 0; i < list.size();i++)
                        res[i] = list.get(i);
                return res;
        }



        //367. Valid Perfect Square
        /*
        * a square number is 1+3+5+7+...,
        * */
        public boolean isPerfectSquare(int num) {
                /*for (int i = 1; i * i <= num && i * i > 0;i++){
                        if (i * i == num)
                                return true;
                }
                return false;*/
                int i = 1;
                while (num > 0){
                        num -= i;
                        i += 2;
                }
                return num == 0;
        }

        //368. Largest Divisible Subset
        public List<Integer> largestDivisibleSubset(int[] nums) {
                List<Integer> result = new ArrayList<>();
                if (nums == null || nums.length == 0)
                        return result;
                int[] count = new int[nums.length];
                int[] parent = new int[nums.length];
                int max = 0,index =0;
                Arrays.sort(nums);
                for (int i = nums.length -1; i >=0; i--){
                        for (int j = i; j < nums.length; j++){
                                if (nums[j] % nums[i] == 0 && count[i] < count[j] + 1){
                                        count[i] = count[j] + 1;
                                        parent[i] = j;
                                        if (count[i] > max){
                                                max = count[i];
                                                index = i;
                                        }
                                }
                        }
                }

                for (int i = 0; i < max; i++){
                        result.add(nums[index]);
                        index = parent[index];
                }
                return result;
        }

        //371. Sum of Two Integers
        public int getSum(int a, int b) {
                if (b == 0){
                        return a;
                }

                int sum = a ^ b;
                int carry = (a&b)<<1;
                return getSum(sum,carry);
        }


        public ArrayList<Integer> FindNumbersWithSum(int[] array,int sum){
                if (array == null || array.length < 2)
                        return new ArrayList<>();
                ArrayList<Integer> res = new ArrayList<>();
                int min = Integer.MAX_VALUE;
                int low = 0, high = array.length-1;
                while (low < high){
                        if (array[low] + array[high] == sum){
                                if (min>array[low] * array[high]){
                                        min = array[low] * array[high];
                                        if (res.isEmpty()){
                                                res.add(array[low]);
                                                res.add(array[high]);
                                        }else {
                                                res.set(0,array[low]);
                                                res.set(1,array[high]);
                                        }
                                        low++;
                                        high--;
                                }
                        }else if (array[low] + array[high] >sum){
                                high--;
                        }else {
                                low++;
                        }
                }
                return res;
        }

        public boolean isSymmetrical(TreeNode pRoot){
                if (pRoot == null)
                        return true;
                return isSymmetricHelper(pRoot.left,pRoot.right);
        }

        public boolean isSymmetricalHelper(TreeNode node1,TreeNode node2){
                if (node1 == null && node2 == null)
                        return true;
                if (node1 == null || node2 == null)
                        return false;
                return node1.val == node2.val
                        && isSymmetricHelper(node1.left,node2.right) && isSymmetricHelper(node1.right,node2.left);
        }

        public int NumberOf1(int n){
                int count = 0;
                if (n >= 0){
                        while (n!=0){
                                if ((n & 1) == 1)
                                        count++;
                                n = (n >>1);
                        }
                        return count;
                }else{
                        while (n!=-1){
                                if ((n & 1) == 0)
                                        count++;
                                n = (n >> 1);
                        }
                        return 32-count;
                }
        }

        public String rotateString(String A, int n, int p) {
                // write code here
                if(A == null)
                        return null;
                return A.substring(p + 1, n) + A.substring(0, p + 1);
        }

        public String[] foldPaper(int n) {
                // write code here
                if (n == 1)
                        return new String[]{"down"};
                String[] result = new String[(int)Math.pow(2,n)-1];
                String[] pre = foldPaper(n - 1);
                result[0] = "down";
                for (int i = 1,j=0; i < result.length;i++){
                        if (i % 2 == 0){
                                result[i] = "down".equals(result[i-2]) ? "up":"down";
                        }else {
                                result[i] = pre[j++];
                        }
                }
                return result;
        }

        public boolean chkPalindrome(ListNode A) {
                // write code here
                if (A == null || A.next == null)
                        return true;
                ListNode head = new ListNode(0),slow = A,fast = A;

                while (fast!=null && fast.next != null){
                        ListNode temp = slow;
                        slow = slow.next;
                        fast = fast.next.next;
                        temp.next = head.next;
                        head.next = temp;
                }
                if (fast != null){
                        slow = slow.next;
                }
                head = head.next;
                while (slow != null){
                        if (head.val != slow.val)
                                return false;
                        head = head.next;
                        slow = slow.next;
                }
                return true;
        }

        public int[] buildMaxTree(int[] A, int n) {
                // write code here
                int[] result = new int[n];
                int[] left = new int[n];
                int[] right = new int[n];
                Arrays.fill(result,-1);
                Arrays.fill(left,-1);
                Arrays.fill(right,-1);
                Stack<Integer> stack = new Stack<>();
                for (int i = 0; i < n;i++){
                        while (!stack.isEmpty() && A[i] > A[stack.peek()]){
                                stack.pop();
                        }
                        if (!stack.isEmpty()){
                                left[i] = stack.peek();
                        }
                        stack.push(i);
                }
                stack.clear();
                for (int i = n-1; i >=0;i--){
                        while (!stack.isEmpty() && A[i] > A[stack.peek()]){
                                stack.pop();
                        }
                        if (!stack.isEmpty()){
                                right[i] = stack.peek();
                        }
                        stack.push(i);
                }
                for (int i = 0; i < n; i++){
                        if (left[i] != -1 && right[i] != -1)
                                result[i] = A[left[i]] > A[right[i]] ? right[i] : left[i];
                        if (left[i] == -1 && right[i] != -1)
                                result[i] = right[i];
                        if (right[i] == -1 && left[i] != -1)
                                result[i] = left[i];
                }
                return result;
        }

        public int[] reverseStackRecursively(int[] stack, int top) {
                // write code here
                if(top == 1)
                        return new int[]{stack[top-1]};
                int[] res = new int[top];
                res[0] = stack[top-1];
                int[] pre = reverseStackRecursively(stack,top-1);
                System.arraycopy(pre, 0, res, 1, pre.length);
                return res;
        }

        public int findMaxDivision(int[] A, int n) {
                // write code here
                int min = A[0],max = A[1];
                for(int i = 0; i < A.length;i++){
                        if (A[i] > max)
                                max = A[i];
                        if (A[i] < min)
                                min = A[i];
                }
                int path = (max-min)/(n-1);
                int bucket_length = (max-min)/path + 1;
                List<List<Integer>> bucket = new ArrayList<>();
                for (int i = 0;i < bucket_length;i++){
                        bucket.add(new ArrayList<Integer>());
                }

                for (int i = 0; i < n; i++){
                        int index = (A[i]-min)/path;
                        System.out.println("index: " + index);
                        if (index >= n)
                                index --;
                        if (bucket.get(index).isEmpty()){
                                bucket.get(index).add(A[i]);
                                bucket.get(index).add(A[i]);
                        }else {
                                if (A[i] > bucket.get(index).get(0))
                                        bucket.get(index).set(0,A[i]);
                                if (A[i] < bucket.get(index).get(1))
                                        bucket.get(index).set(1,A[i]);
                        }
                }
                int res = path;
                int j = 0;
                for (int i = 1; i < bucket_length; i++){
                        if (!bucket.get(i).isEmpty()){
                                res = Math.max(res,bucket.get(i).get(1)-bucket.get(j).get(0));
                                j=i;
                        }
                }
                return res;
        }

        public String trans(String s, int n) {
                // write code here
                if (s == null || s.isEmpty())
                        return s;
                char[] chars = s.toCharArray();
                transHelper(chars, 0, chars.length - 1);
                int i = 0,j=0;
                while (i < chars.length && j < chars.length){
                        while (i < chars.length && chars[i] == ' ')
                                i++;
                        j = i+1;
                        while (j < chars.length && chars[j] != ' ')
                                j++;
                        transHelper(chars,i,j-1);
                        i = j+1;
                }
                for (int m = 0; m < chars.length;m++){
                        char ch = chars[m];
                        if ('a' > ch || 'z' < ch)
                                chars[m] = Character.toLowerCase(ch);
                        else
                                chars[m] = Character.toUpperCase(ch);
                }
                return new String(chars);
        }

        private void transHelper(char[] chars,int start,int end){
               while (start < end){
                       char temp = chars[start];
                       chars[start] = chars[end];
                       chars[end] = temp;
                       start++;
                       end--;
               }
        }


        public int[] getPartition(int[][] land, int n, int m) {
                // write code here
                int[] result = new int[2];
                int[] val = new int[n];
                result[1] = 1;
                int min = Integer.MAX_VALUE;
                for (int i = 0; i <=n; i++){
                        for (int j = 0; j < i; j++)
                                val[j] = 0;
                        for (int j = i; j<n;j++)
                                val[j] = 1;
                        int temp = 0;
                        for (int k =0; k <m;k++){
                                for (int l = 0;l <n;l++)
                                        if (land[k][l] != val[l])
                                                temp++;
                        }
                        if (temp < min){
                                result[0] = i;
                                result[1] = i+1;
                                min = temp;
                        }
                }
                return result;
        }

        public int findPath(TreeNode root) {
                // write code here
                List<Integer> list = new ArrayList<>();
                findPathWhite(root, list);
                findPathBlack(root, list);
                Collections.sort(list);
                return list.get(list.size() - 1);
        }

        public int findPathWhite(TreeNode root,List<Integer> list){
                if (root == null)
                        return 0;
                int left = findPathWhite(root.left, list);
                int right = findPathWhite(root.right, list);
                if (root.val == 1){
                        list.add(left + right + 1);
                        return Math.max(left,right) + 1;
                }
                else{
                        list.add(Math.max(left,right));
                        return 0;
                }
        }
        public int findPathBlack(TreeNode root,List<Integer> list){
                if (root == null)
                        return 0;
                int left = findPathBlack(root.left, list);
                int right = findPathBlack(root.right, list);
                if (root.val == 0){
                        list.add(left + right + 1);
                        return Math.max(left,right) + 1;
                }
                else{
                        list.add(Math.max(left,right));
                        return 0;
                }
        }

        public int findK(int n) {
                // write code here
                if (n <=5)
                        return 3;
                int step = (n+1)/2-3;
                return Math.min((n+1)/2,n/(step+1)+1);
        }

        public int calcMonoSum(int[] A, int n) {
                // write code here
                int[] f = new int[n];
                int sum = 0;
                List<Integer> list = new LinkedList<>();
                list.add(0);
                for (int i  = 1; i < n;i++){
                        int index = getIndex(i,list,A);
                        for (int j = 0; j <= index; j++)
                                sum += A[list.get(j)];
                }
                return sum;
        }

        private int getIndex(int index,List<Integer> list,int[] A){
                int low = 0,high = list.size()-1;
                while (low < high){
                        int mid = low + (high-low)/2;
                        if (A[list.get(mid)]  == A[index]){
                               while (A[list.get(mid)] == A[index])
                                       mid++;
                                list.add(mid,index);
                                return mid;
                        }else if (A[list.get(mid)] > A[index]){
                                high = mid-1;
                        }else
                                low = mid+1;
                }
                if (A[list.get(low)] <= A[index]){
                        list.add(low+1,index);
                        return low;
                }else{
                        list.add(low,index);
                        return low-1;
                }
        }

        public int findAppearance(String A, int lena, String B, int lenb) {
                // write code here
                int[] next = new int[lenb+1];
                getNext(B, next);
                int j = 0;
                for(int i = 0; i < lena;i++){
                        while(j > 0 && A.charAt(i) != B.charAt(j))
                                j = next[j];
                        if(A.charAt(i) == B.charAt(j))
                                j++;
                        if(j == lenb)
                                return i-j;
                }
                return -1;

        }

        private void getNext(String b,int[] next){
                next[0] = next[1] = 0;
                int j = 0;
                for(int i = 1;i<b.length();i++){
                        while(j > 0 && b.charAt(i) != b.charAt(j))
                                j=next[j];
                        if(b.charAt(i) == b.charAt(j))
                                j++;
                        next[i+1] = j;
                }
        }

        //sunday 算法
        /**
         * 字符串匹配算法
         * 主要思想：
         * 如果字符匹配，则目标字符串与匹配字符串都下移一位，
         * 如果字符不匹配，则找到在目标字符串与匹配字符串最右方对应的下一位ch
         * 在匹配串中查找ch，如果存在ch，则匹配串向右移length-index，length为匹配串的长度，index为ch在匹配串中的位置
         * 如果不存在，则匹配串向右移length位；
         * 例如：
         * 目标串：d g j h o l p k j h h i j w a v n m
         *匹配串： j h h i
         * 1.因为d与j不匹配，所以找到ch = o；
         * 2.因为ch=o在匹配串中不存在，所以匹配串向右移length=4位
         *
         * 目标串：d g j h o l p k j h h i j w a v n m
         *匹配串：            j h h i
         * 1.因为o与j不匹配，所以找到ch = j；
         * 2.因为ch=j在匹配串中的位置为index = 0，所以匹配串向右移length-index=4位
         *
         * 目标串：d g j h o l p k j h h i j w a v n m
         *匹配串：                      j h h i
         * 1.字符串匹配，返回索引8
         * @param target 目标字符串
         * @param pattern 匹配字符串
         * @return 若字符串匹配，则返回第一次匹配的位置，若不匹配，则返回-1；
         */
        public int sundaySearch(String target,String pattern){
                int t_len = target.length();
                int p_len = pattern.length();
                if (t_len < p_len)
                        return -1;
                int t_index = 0, p_index = 0,index = 0; //t_index目标字符串匹配索引，p_index匹配字符串索引,index匹配的位置
                while (t_index < t_len && p_index < p_len){
                        if (target.charAt(t_index) == pattern.charAt(p_index)){  //如果字符匹配，则目标字符串与匹配字符串都下移一位
                                t_index++;
                                p_index++;
                        }else {
                                if (t_index + p_len >= t_len)
                                        return -1;
                                char nextChar = target.charAt(t_index + p_len);  //找到在目标字符串与匹配字符串最右方对应的下一位ch
                                int next_index = p_len-1;
                                while (next_index >=0 && pattern.charAt(next_index) != nextChar) //匹配串中查找ch
                                        next_index--;
                                t_index = t_index + p_len-next_index;  //如果存在ch，则匹配串向右移length-index
                                p_index = 0;
                                index = t_index;
                        }
                }
                if (p_index < p_len)
                        return -1;
                return index;
        }

        public int[] findKthNumbers(int[] A, int n, int k) {
                // write code here
                n = A.length;
                int[] temp = new int[n];
                System.arraycopy(A,0,temp,0,n);
                int low = 0, high = n-1;
                int index = -1;
                while (index != k-1){
                        index = quickSort(A,low,high);
                        if (index > k-1)
                                high= index-1;
                        else if (index < k-1)
                                low = index+1;
                        else
                                break;
                }
                int[] result = new int[k];
                for (int i = 0,m = 0; i < n; i++)
                        if (temp[i] <=A[index])
                                result[m++] = temp[i];

                return result;
        }

        public String addToPalindrome(String A, int n) {
                // write code here
                StringBuilder builder = new StringBuilder();
                for (int i = 0 ; i < A.length(); i++){
                        builder.append(A.charAt(i));
                        if (isPadlindrome(A.substring(i+1)))
                                return builder.reverse().toString();
                }
                return builder.toString();
        }

        private boolean isPadlindrome(String str){
                if (str.length() == 1)
                        return true;
                int low = 0,high = str.length()-1;
                while (low < high){
                        if (str.charAt(low) != str.charAt(high)){
                              return false;
                        }
                        low++;
                        high--;
                }
                return true;
        }

        private int quickSort(int[] arr,int low,int high){
                int i = low,j = high;
                while (i < j){
                       while (arr[j] > arr[low])
                               j--;
                        while (arr[i] < arr[low])
                                i++;
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                }
                return i;
        }

        //最长回文字串  Manacher算法
        public int getLongestPalindrome(String A, int n) {
                // write code here
                char[] chars = new char[2 * A.length()+3];
                chars[0] = '@';
                chars[1]='#';
                chars[chars.length-1] = '$';
                int[] p = new int[chars.length];
                for(int i = 0; i < A.length();i++){
                        chars[2*i+2] = A.charAt(i);
                        chars[2*i+3] = '#';
                }
                int id=0,maxid = 0,ans = 1;
                for (int i = 0; i < chars.length;i++){
                        if (maxid > i && 2*id-i < chars.length)
                                p[i] = Math.min(p[2*id-i],maxid-i);
                        else
                                p[i] = 1;
                        while (i-p[i] >= 0 && i+p[i] <chars.length &&chars[i+p[i]] == chars[i-p[i]])
                                p[i]++;
                        if (p[i] + i > maxid){
                                maxid = p[i] + i;
                                id = i;
                        }
                        if (ans < p[i])
                                ans = p[i];
                }
                return ans-1;
        }

        public int findMaxGap(int[] A, int n) {
                // write code here
                int max = A[0],result = 0;
                for (int i = 0; i <= A.length-2;i++){
                        if (A[i] > max)
                                max = A[i];
                        result = Math.max(result,Math.abs(findMaxGapHelper(A,i+1)-max));
                }
                return result;
        }

        private int findMaxGapHelper(int[] A,int start){
                int max = A[start];
                for (int i = start;i<A.length;i++)
                        if (A[i] > max)
                                max = A[i];
                return max;
        }


        public void lengthOfLis(int[] nums){
                int[] line = new int[nums.length];
                int[] path = new int[nums.length];
                int[] c = new int[nums.length];
                c[0] = -1;
                c[1] = 0;
                line[0] = c[0];
                int len = 1;
                for (int i = 1; i < nums.length; ++i){
                        int index = getPosition(c,len,i,nums);
                        line[i] = c[index-1];
                        c[index] = i;
                        if (index > len)
                                len = index;
                }
                path[0] = c[len];
                int i = 0;
                while (line[path[i]] != -1){
                        path[i+1] = line[path[i]];
                        ++i;
                }

                for (int j = len-1;j>=0;j--){
                        if (j == len-1)
                                System.out.print(nums[path[j]]);
                        else
                                System.out.print(" " + nums[path[j]]);
                }
                System.out.println();
        }

        public int getPosition(int[] c,int len,int i,int[] nums){
                int low = 1,high = len;
                while (low <= high){
                        int mid = low + (high-low)/2;
                        if (nums[c[mid]] <= nums[i])
                                low = mid+1;
                        else
                                high = mid-1;
                }
                return low;
        }

        public static boolean StrIsValid(String line){
                Pattern pattern = Pattern.compile("^[\\s\\d-]+$");
                Matcher matcher = pattern.matcher(line);
                return matcher.matches();
        }

        public boolean chkWildMatch(String A, int lena, String B, int lenb) {
                // write code here
                int i = 0,j= 0;
                while (i <A.length() && j < B.length()){
                        char chA = A.charAt(i);
                        char chB = B.charAt(j);
                        if (chB == '*'){
                                char temp = A.charAt(i-1);
                                if (temp != chA)
                                        return false;
                                int count = 0;
                                while (temp == chA){
                                        i++;
                                        chA = A.charAt(i);
                                        count++;
                                }
                                j++;
                                int count2 = 0;
                                while (temp == B.charAt(j)){
                                        j++;
                                        count2++;
                                }
                                if (count2 > count)
                                        return false;
                        }else if (chB == '.'){
                                if (j < B.length()-1 && B.charAt(j+1) == '*')
                                        i++;
                                else {
                                        i++;
                                        j++;
                                }
                        }else {
                                if (chA != chB)
                                        return false;
                                i++;
                                j++;
                        }
                }
                return i == A.length();
        }

        public boolean chkMixture(String A, int n, String B, int m, String C, int v) {
                // write code here
                if (A.length() + B.length() != C.length())
                        return false;
                StringBuilder stringA = new StringBuilder(A);
                StringBuilder stringB = new StringBuilder(B);
                StringBuilder stringC = new StringBuilder(C);
                Boolean[] dp = new Boolean[m+1];
                dp[0] = true;
                for (int i = 1; i < m+1; i++) {//初始化第一行
                        if (stringB.charAt(i-1)==stringC.charAt(i-1)){
                                dp[i] = true;
                        }else{
                                dp[i] = false;
                        }
                }
                for (int i = 0; i < n; i++) {
                        for (int j = 1; j < m+1; j++) {
                                dp[j] = (dp[j]&&stringA.charAt(i)==stringC.charAt(i+j))||(dp[j-1]&&stringB.charAt(j-1)==stringC.charAt(i+j));
                        }
                }
                return dp[m];
        }

        public static String getSum(String[] nums){
                BigInteger[] weight = new BigInteger[10];
                for (int i = 0; i < weight.length;i++)
                        weight[i] = new BigInteger("0");
                int[] val = new int[10];
               List<Integer> list = new LinkedList<>();
                //list.add(0);
                BigInteger big = new BigInteger("10");
                for (String str:nums){
                        for (int j = 0; j < str.length();j++){
                                weight[str.charAt(j)-'A'] = weight[str.charAt(j)-'A'].add(big.pow(str.length()-j));
                        }
                }
                for (int i = 0; i < weight.length;i++){
                                sort(list,weight,i);
                }
                int temp = 9;
                for (int i = 0; i < list.size();i++){
                        val[list.get(i)] = temp--;
                }
                BigInteger result = new BigInteger("0");
                for (String str:nums){
                        BigInteger sum = new BigInteger("0");
                        for (int i = 0;i < str.length();i++){
                                sum = sum.multiply(new BigInteger("10")).add(new BigInteger(String.valueOf(val[str.charAt(i)-'A'])));
                                //sum = 10 * sum + val[str.charAt(i)-'A'];
                        }
                        result = result.add(sum);
                }
                return result.toString();
        }

        public static void sort(List<Integer> list,BigInteger[] weight,int i){
                int start = 0;
                while (start < list.size() && weight[list.get(start)].compareTo(weight[i]) ==1)
                        start++;
                list.add(start,i);
        }


        public int findMinCost(String A, int n, String B, int m, int c0, int c1, int c2) {
                // write code here
                int[][] dp = new int[A.length()+1][B.length()+1];
                for (int i = 1; i < A.length();i++)
                        dp[i][0] = dp[i-1][0] + c1;
                for (int i = 1; i < B.length();i++)
                        dp[0][i] = dp[0][i-1] + c0;
                for (int i = 1;i <=A.length();i++)
                        for (int j = 1;j <=B.length();j++){
                                if (A.charAt(i-1) == B.charAt(j-1))
                                        dp[i][j] = dp[i-1][j-1];
                                else {
                                        dp[i][j] = Math.min(dp[i-1][j-1] + c2,Math.min(dp[i-1][j] + c0,dp[i][j-1] + c1));
                                }
                        }
                return dp[A.length()][B.length()];
        }

        public int findLongest(String A, int n, String B, int m) {
                // write code here
               /* int[][] dp = new int[A.length()+1][B.length()+1];
                for (int i = 1; i <=A.length();i++){
                        if (A.charAt(i-1) == B.charAt(0))
                                dp[i][0] = 1;
                }
                for (int j = 1; j <=B.length();j++){
                        if (A.charAt(0) == B.charAt(j-1))
                                dp[0][j] = 1;
                }
                for (int i = 1;i <=A.length();i++)
                        for (int j = 1; j <= B.length();j++){
                                if (A.charAt(i-1) == B.charAt(j-1))
                                        dp[i][j] = dp[i-1][j-1] + 1;
                                else
                                        dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                        }
                return dp[A.length()][B.length()];*/

                int[] dp = new int[B.length()+1];
                int max = 0;
                for (int i = 0; i < A.length();i++){
                        for (int j = B.length()-1;j>=0;j--){
                                if (A.charAt(i) == B.charAt(j)){
                                        if (i==0|| j==0)
                                                dp[j] = 1;
                                        else
                                                dp[j] = dp[j-1]+1;
                                }else
                                        dp[j] = 0;
                                max = Math.max(max,dp[j]);
                        }
                }
                return max;
        }

        public int findLongest(int[] A, int n) {
                // write code here
                int[] dp = new int[A.length];
                int len = 0;
                for (int i = 0; i < A.length;i++){
                        int index = Arrays.binarySearch(dp,0,len,A[i]);
                        if (index < 0)
                                index = -(index+1);
                        dp[index] = A[i];
                        if (index == len)
                                len++;
                }
                return len;
        }

        public int countWays(int[] changes, int n, int x) {
                // write code here
                int[][] dp = new int[changes.length+1][x+1];
                for (int i = 0;i <=changes.length;i++ )
                        dp[i][0] = 1;
                for (int i = 1; i <= changes.length;i++){
                        for (int j = 1; j <=x;j++){
                                dp[i][j] = 0;
                                for (int k = 0; k <= j/changes[i-1];k++){
                                        dp[i][j] += dp[i-1][j-k*changes[i-1]];
                                }
                        }
                }
                return dp[changes.length][x];
        }

        public static void main(String[] args) {
                LeetCode2 test = new LeetCode2();
                TreeNode node1 = new TreeNode(3);
                TreeNode node2 = new TreeNode(1);
                TreeNode node3 = new TreeNode(22);
                TreeNode node4 = new TreeNode(4);
                TreeNode node5 = new TreeNode(26);
                node1.left = node2;
                node1.right = node3;
                node2.left = node4;
               /* node2.right = node5;*/
                /*char[][] grid = new char[][]{
                        {'1','1','0','0','0'},
                        {'1','1','0','0','0'},
                        {'0','0','1','0','0'},
                        {'0','0','0','1','1'},
                };*/
                /*char[][] board = new char[][]{
                        *//*{'X','X' ,'X','X'},
                        {'X','O' ,'O','X'},
                        {'X','X','O','X'},
                        {'X' ,'O','X' ,'X'}*//*
                        *//*{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}*//*
                        {'O','O','O'},
                        {'O','O','O'},
                        {'O','O','O'}
                };*/
                String[][] tickets = new String[][]{
                        {"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}
                };
                /*TreeLinkNode linkNode1 = new TreeLinkNode(1);
                TreeLinkNode linkNode2 = new TreeLinkNode(2);
                TreeLinkNode linkNode3 = new TreeLinkNode(3);
                TreeLinkNode linkNode4 = new TreeLinkNode(4);
                TreeLinkNode linkNode5 = new TreeLinkNode(5);
                linkNode1.left = linkNode2;
                linkNode1.right = linkNode3;
                linkNode2.left = linkNode4;
                linkNode2.right = linkNode5;*/
                /*ListNode listNode1 = new ListNode(1);
                ListNode listNode2 = new ListNode(2);
                ListNode listNode3 = new ListNode(2);
                ListNode listNode4 = new ListNode(1);
                ListNode listNode5 = new ListNode(1);
                listNode1.next = listNode2;
                listNode2.next = listNode3;
                listNode3.next = listNode4;
                listNode4.next = listNode5;*/
                /*int[][] martix = new int[][]{
                        {9,9,4},
                        {6,6,8},
                        {2,1,1}
                };*/
                System.out.println(test.isPowerOfFour(2));
        }

}

class TreeLinkNode{
        int val;
        TreeLinkNode left,right,next;
        TreeLinkNode(int x){val = x; }
}

