package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created withSimple_love
 * Date: 2016/3/2.
 * Time: 20:33
 */
public class QuickSortTest2 {
        public int partition(int[] nums, int start, int end){
                int target = nums[start];
                int i = start,j = end;
                while (i < j){
                        while (nums[j] >= target && i < j)
                                j--;
                        while (nums[i] <= target && i < j)
                                i++;
                        if (i < j){
                                int temp = nums[i];
                                nums[i] = nums[j];
                                nums[j] = temp;
                        }
                }
                nums[start] = nums[i];
                nums[i] = target;
                return i;
        }

        public void qucikSort(int[] nums,int start,int end){
                if (start < end){
                        int index = partition(nums,start,end);
                        qucikSort(nums,start,index-1);
                        qucikSort(nums,index+1,end);
                }
        }

        public static void main(String[] args) {
                int[] nums = new int[]{5,8,9,5,7,4,5,3,2,1};
                QuickSortTest2 test = new QuickSortTest2();
                test.qucikSort(nums,0,nums.length - 1);
                System.out.println(Arrays.toString(nums));
        }
}
