package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Simple_love on 2015/12/7.
 * 归并排序
 * 先递归地分解数列，然后再合并数列，就是归并排序
 */
public class MergeSort {
        public void mergeArray(int[] nums,int start,int end){
                int mid = start + ((end - start) >> 1);
                int i = start,j =  mid+ 1;
                int[] temp = new int[end-start+1];
                int k = 0;
                while (i <=mid && j <= end){
                        if (nums[i] > nums[j]){
                                temp[k++] = nums[j++];
                        }else
                                temp[k++] = nums[i++];
                }
                while (i <= mid)
                        temp[k++] = nums[i++];
                while (j <= end){
                        temp[k++] = nums[j++];
                }
                for (i = start,j=0;i <= end;i++,j++)
                        nums[i] = temp[j];
        }

        public void mergeSort(int[] nums,int start,int end){
                if (start < end){
                        int mid = start + ((end - start) >> 1);
                        mergeSort(nums,start,mid);
                        mergeSort(nums,mid + 1,end);
                        mergeArray(nums,start,end);
                }
        }

        public static void main(String[] args) {
                MergeSort mergeSort = new MergeSort();
                int[] nums = new int[]{3,4,2,1};
                mergeSort.mergeSort(nums,0,nums.length -1);
                System.out.println(Arrays.toString(nums));
        }
}
