package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created with Simple_love
 * Date: 2016/7/20.
 * Time: 15:52
 */
public class BucketSort {
        public static void bucketSort(int[] datas,int min,int max){
                int[] buckets = new int[max-min];
                for (int value:datas){
                        buckets[value-min]++;
                }
                for (int i = 1; i < max -min; i++){
                        buckets[i] = buckets[i] + buckets[i-1];
                }
                int[] temp = new int[datas.length];
                System.arraycopy(datas,0,temp,0,datas.length);
                for (int i = datas.length - 1; i >=0 ; i--){
                        datas[--buckets[temp[i]-min]] = temp[i];
                }
        }

        public static void main(String[] args) {
                int[] datas = new int[]{21,30,39,30,21,16,9};
                bucketSort(datas,9,40);
                System.out.println(Arrays.toString(datas));
        }
}
