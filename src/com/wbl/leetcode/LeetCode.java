package com.wbl.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        public static void main(String[] args){
                LeetCode test = new LeetCode();
                TreeNode node1 = new TreeNode(1);
                TreeNode node2 = new TreeNode(2);
                TreeNode node3 = new TreeNode(3);
                TreeNode node4 = new TreeNode(4);
                TreeNode node5 = new TreeNode(5);
                node1.left = node2;
                node1.right = node3;
                node2.left = node4;
                node2.right = node5;
                int[] postorder = {4,5,2,3,1};
                int[] inorder = {4,2,5,1,3};
                int[] nums = {1,0,0,0,0};
                System.out.println("dd");
        }
}
class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
}
