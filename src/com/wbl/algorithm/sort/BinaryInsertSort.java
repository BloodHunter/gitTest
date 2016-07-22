package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created with Simple_love
 * Date: 2016/7/21.
 * Time: 15:13
 */
public class BinaryInsertSort {
        public static void binaryInsertSort(DataWrap[] data){
                System.out.println("Start sort");
                for (int i = 1; i < data.length ; i++ ) {
                        DataWrap temp = data[i];
                        int low = 0, high = i-1;
                        while(low <= high){
                                int mid = low + ((high - low)>>1);
                                if (temp.compareTo(data[mid]) > 0){
                                        low = mid+1;
                                }else {
                                        high = mid-1;
                                }
                        }
                        for (int j = i; j > low; j-- ) {
                                data[j] = data[j-1];
                        }
                        data[low] = temp;
                        System.out.println(Arrays.toString(data));
                }
        }
        public static void main(String[] args) {
                DataWrap[] data = {
                        new DataWrap(21,""),
                        new DataWrap(30,""),
                        new DataWrap(49,""),
                        new DataWrap(30,"*"),
                        new DataWrap(16,""),
                        new DataWrap(9,""),
                };
                System.out.println("Before sort: \n" + java.util.Arrays.toString(data));
                binaryInsertSort(data);
                System.out.println("After sort: \n" + java.util.Arrays.toString(data));
        }
}
