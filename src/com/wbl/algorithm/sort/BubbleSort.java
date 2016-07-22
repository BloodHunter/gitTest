package com.wbl.algorithm.sort;

import java.util.Arrays;

/**
 * Created with Simple_love
 * Date: 2016/7/21.
 * Time: 11:17
 */
public class BubbleSort {
        public static void bubbleSort(DataWrap[] data){
                System.out.println("Start sort");
                for (int i = 0; i < data.length - 1 ;i++ ) {
                        boolean flag = false;
                        for (int j = 0; j < data.length - 1 - i ;j++) {
                                if (data[j].compareTo(data[j+1]) > 0) {
                                        swap(data,j,j+1);
                                        flag = true;
                                }
                        }
                        System.out.println(Arrays.toString(data));
                        if (!flag) {
                                break;
                        }
                }
        }

        private static void swap(DataWrap[] data, int i, int j){
                DataWrap temp = data[i];
                data[i] = data[j];
                data[j] = temp;
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
                bubbleSort(data);
                System.out.println("After sort: \n" + java.util.Arrays.toString(data));
        }
}

class DataWrap implements Comparable<DataWrap> {
        int data;
        String flag;
        public DataWrap(int data, String flag){
                this.data = data;
                this.flag = flag;
        }

        public String toString(){
                return data+flag;
        }

        public int compareTo(DataWrap dw){
                return this.data > dw.data ? 1 : (this.data == dw.data ? 0 : -1);
        }
}
