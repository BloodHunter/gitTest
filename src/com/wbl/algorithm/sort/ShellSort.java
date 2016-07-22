package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created with Simple_love
 * Date: 2016/7/20.
 * Time: 10:51
 */
public class ShellSort {
        public static void shellSort(int[] datas){
                int h = 1;
                while ( h <= datas.length/3){
                        h = h * 3 + 1;
                }
                while (h > 0){
                        for (int i = h; i < datas.length; i++){
                                int temp = datas[i];
                                if (datas[i] < datas[i-h]){
                                        int j = i - h;
                                        for (; j >=0 && datas[j] > temp; j-=h)
                                                datas[j+h] = datas[j];
                                        datas[j+h] = temp;
                                }
                                System.out.println(Arrays.toString(datas));
                        }
                        h = (h-1)/3;
                }
        }

        public static void main(String[] args) {
                int[] datas = new int[]{21,30,39,30,21,16,9};
                shellSort(datas);
        }
}
