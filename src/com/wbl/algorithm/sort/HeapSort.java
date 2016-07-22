package com.wbl.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created withSimple_love
 * Date: 2016/3/4.
 * Time: 14:08
 *
 * 堆排序就是一个不断调整的过程，要理解调整的步骤，同时要注意到，堆其实就是用完全二叉树表示的
 */
public class HeapSort {
        private List<Integer> heap = new ArrayList<>();
        private int length;
        HeapSort(int[] nums){
                length = nums.length;
                heap.add(-1);
                for (int i = 1; i <= length; i++)
                        heap.add(nums[i-1]);
        }
        private void swap(int i,int j){
                int temp = heap.get(i);
                heap.set(i,heap.get(j));
                heap.set(j,temp);
        }

        /**
         * 从传入的节点开始向下调整堆
         * @param i 传入节点的编号
         */
        private void sifidown(int i){
                int temp;
                boolean flag = false;
                //当i节点有孩子（其实是至少有左儿子，因为是完全二叉树）并且有需要继续调整的时候
                while (i * 2 <= length && !flag){
                        //首先判断它和左孩子的关系，temp用来记录值较小的节点的编号
                        if (heap.get(i) > heap.get(i * 2))
                                temp = i * 2;
                        else
                                temp = i;

                        //如果它有右孩子
                        if (i * 2 + 1 <= length){
                                //如果右孩子的值更小，更新较小节点的编号
                                if (heap.get(temp) > heap.get(i * 2 + 1))
                                        temp = i * 2 + 1;
                        }

                        //如果最小的节点不是自己，说明子节点中有比父节点更小的
                        if ( i != temp){
                                swap(i,temp);
                                i = temp; //更新i，以便继续向下调整
                        }else
                                flag = true;
                }
        }

        public void sort(){
                //从最后一个非叶子节点到第一个节点依次向下调整（堆用完全二叉树存储，所以最后一个非叶子节点编号为n/2）
                for (int i = length/2; i >0; i--)
                        sifidown(i);
        }

        public void print(){
                int n = length;
                for (int i = 1; i <= n; i++){
                        System.out.print(heap.get(1) + " "); //将堆顶点的值输出，即数组中最小的值
                        heap.set(1, heap.get(length));//将堆的最后一个点赋值到堆顶点
                        length--;                //堆的元素减1
                        sifidown(1);         //向下调整
                }
                System.out.println();
        }

        public static void main(String[] args) {
                HeapSort heapSort = new HeapSort(new int[]{99,5,36,7,22,17,46,12,2,19,25,28,1,92});
                heapSort.sort();
                heapSort.print();
        }
}
