package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Simple_love on 2015/12/6.
 * 快速排序，挖坑 + 分治
 */
public class QuickSortTest {
        public int partition(int[] nums,int start,int end){
                int i = start, j = end;
                int x = nums[i]; // x是挖的第一坑
                while (i < j){
                        //从右往左，即由后往前，找小于x的数填入坑中
                        while (i < j && nums[j] > x){
                                j--;
                        }
                       if (i < j){
                               nums[i] = nums[j]; //将nums[j]填入nums[i]，此时nums[j]成为新的坑
                               i++;
                       }

                        //从左往右，即由前往后，找到大于x的数填入坑中
                        while (i < j && nums[i] < x){
                                i++;
                        }
                        if (i < j){
                                nums[j] = nums[i]; //将nums[i]填入坑中，此时nums[i]成为新的坑
                                j--;
                        }
                }
                //退出时，i==j,将x填入坑中
                nums[i] = x;
                return i;
        }

        public void quickSort(int[] nums,int start,int end){
                if (start < end){
                        int index = partition(nums,start,end);
                        quickSort(nums,start,index-1);
                        quickSort(nums,index+1,end);
                }
        }


        public static void main(String[] args) {
                QuickSortTest test = new QuickSortTest();
                int[] nums = new int[]{99,99};
                test.quickSort(nums,0,nums.length - 1);
                System.out.println(Arrays.toString(nums));
        }
}
