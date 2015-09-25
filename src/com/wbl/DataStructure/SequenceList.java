package com.wbl.DataStructure;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Simple_love on 2015/8/7.
 * 基础数据结构————顺序表
 */
public class SequenceList<T>{
    private int size;
    private Class<T> type;
    private T[] elements;
    private static int length = 0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SequenceList(Class<T> type,int size) {
        if (size > 0){
            this.size = size;
            this.type = type;
            elements = (T[]) Array.newInstance(type,size);
        }
    }

    public boolean insertElement(T element){
        int currentLength = length;
        if (length >= size){
            return false;
        }else {
            elements[length] = element;
            length++;
            return true;
        }
    }

    public boolean deleteElement(int location){
        if (location < 0 || location >= length){
            return false;
        }else {
            for (int i = location; i < length; i++){
                elements[i] = elements[i+1];
            }
            length--;
            return true;
        }
    }

    public T getElement(int location){
        if (location < 0 || location >= length){
            return null;
        }else {
            return elements[location];
        }
    }

    public boolean changeElement(int location, T data){
        if (location < 0 || location >= length){
            return false;
        }else {
            elements[location] = data;
            return true;
        }
    }

    public void print(){
        System.out.println(Arrays.toString(elements));
    }

    public static void main(String[] args){
        SequenceList<Integer> list1 = new SequenceList<>(Integer.class,10);
        list1.insertElement(1);
        list1.insertElement(2);
        list1.insertElement(3);
        list1.print();

        list1.deleteElement(2);
        list1.print();

        list1.changeElement(0, 4);
        list1.print();

        System.out.println(list1.getElement(0));
    }
}
