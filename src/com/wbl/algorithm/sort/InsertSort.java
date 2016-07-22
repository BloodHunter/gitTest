package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created with Simple_love
 * Date: 2016/7/20.
 * Time: 10:34
 */
public class InsertSort {
         public static void insertSort(int[] datas){
                 for (int i = 1; i < datas.length; i++){
                         int temp = datas[i];
                         if (datas[i] < datas[i-1]){
                                 int j = i - 1;
                                 for (; j >= 0 && datas[j] >temp;j--)
                                         datas[j+1] = datas[j];
                                 datas[j+1] = temp;
                         }
                         System.out.println(Arrays.toString(datas));
                 }
         }

        public static void main(String[] args) {
                int[] datas = new int[]{21,30,39,30,21,16,9};
                insertSort(datas);
        }
}
