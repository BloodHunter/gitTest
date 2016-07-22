package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created with Simple_love
 * Date: 2016/7/20.
 * Time: 16:18
 */
public class MultiKeyRadixSort {
        /**
         *
         * @param datas 待排序数组
         * @param radix 指定关键字拆分的进制，如radix=10，表明按照10进制拆分
         * @param d 指定将关键字拆分为几个关键字
         */
        public static void radixSort(int[] datas, int radix, int d){
                int[] temp = new int[datas.length];
                int[] buckets = new int[radix];
                for (int i = 0, rate = 1; i < d; i++){
                        Arrays.fill(buckets, 0);
                        System.arraycopy(datas,0,temp,0,datas.length);
                        for (int j = 0 ; j < datas.length; j++){
                                int subkey = (temp[j]/rate)%radix;
                                buckets[subkey]++;
                        }
                        for (int j = 1; j < radix; j++){
                                buckets[j] += buckets[j-1];
                        }
                        for (int m = datas.length -1; m >= 0; m--){
                                int subkey = (temp[m]/rate)%radix;
                                datas[--buckets[subkey]] = temp[m];
                        }
                        rate *= radix;
                }
        }

        public static void main(String[] args) {
                int[] datas = new int[]{21,30,39,30,21,16,9};
                radixSort(datas,10,2);
                System.out.println(Arrays.toString(datas));
        }
}
