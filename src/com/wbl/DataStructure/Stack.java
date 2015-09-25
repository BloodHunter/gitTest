package com.wbl.DataStructure;

/**
 * Created by Simple_love on 2015/8/31.
 * 基础数据结构——栈
 */
public class Stack<T>{
        private int size;
        private int top;
        private Object[] elements;
        Stack(){
                top = -1;
                elements = new Object[100];
        }
        public void push(T item){
                elements[++top] = item;
        }
        public T pop(){
                T obj;
                obj = peek();
                top--;
                return obj;
        }

        public T peek(){
                if (top == -1){
                        throw new ArrayIndexOutOfBoundsException();
                }
                return (T)elements[top];
        }

        public boolean isEmpty(){
                return top == -1;
        }

        public static void main(String[] args){
                Stack stack = new Stack();
                stack.push("A");
                stack.push("b");
                stack.push("c");
                while (!stack.isEmpty()){
                        System.out.println(stack.pop());
                }
        }
}
