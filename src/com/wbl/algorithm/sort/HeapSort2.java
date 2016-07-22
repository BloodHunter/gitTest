package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created with Simple_love
 * Date: 2016/7/20.
 * Time: 9:30
 */
public class HeapSort2 {
        public static void heapSort(int[] datas){
                for (int i = 0; i < datas.length; i++){
                        buildMaxHeap(datas, datas.length- i - 1);
                        swap(datas, 0, datas.length - i - 1);
                        System.out.println(Arrays.toString(datas));
                }
        }

        public static void buildMaxHeap(int[] datas, int lastIndex){
                for (int i = (lastIndex -1)/2; i >=0; i--){
                        int k = i;
                        while (k * 2<= lastIndex){
                                int temp;
                                temp = datas[k] > datas[k*2] ? k : k *2;
                                if (k * 2 + 1 <= lastIndex){
                                        if (datas[k*2 + 1] > datas[temp])
                                                temp = k*2 + 1;
                                }

                                if (k != temp){
                                        swap(datas,k,temp);
                                        k = temp;
                                }else
                                        break;
                        }
                }
        }

        public static void swap(int[] datas ,int i, int j){
                int temp = datas[i];
                datas[i] = datas[j];
                datas[j] = temp;
        }

        public static void main(String[] args) {
                int[] datas = new int[]{21,30,39,30,21,16,9};
                heapSort(datas);
        }
}
