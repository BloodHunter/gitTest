package com.wbl.algorithm.sort;

/**
 * Created with Simple_love
 * Date: 2016/7/21.
 * Time: 14:28
 */
public class QuickSort {
        public static void quickSort(DataWrap[] data,int start,int end){
                if (start < end) {
                        DataWrap target = data[start];
                        int i = start;
                        int j = end;
                        while(i < j){
                                while(j > i && data[j].compareTo(target) >= 0)
                                        j--;
                                while(i < j && data[i].compareTo(target) <= 0)
                                        i++;
                                if (i < j) {
                                        swap(data,i,j);
                                }else{
                                        break;
                                }
                        }
                        data[start] = data[i];
                        data[i] = target;
                        quickSort(data,start,i-1);
                        quickSort(data,i+1,end);
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
                quickSort(data,0,data.length-1);
                System.out.println("After sort: \n" + java.util.Arrays.toString(data));
        }
}
