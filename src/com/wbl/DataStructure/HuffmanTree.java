package com.wbl.DataStructure;

import java.util.*;
import java.util.Stack;

/**
 * Created with Simple_love
 * Date: 2016/7/14.
 * Time: 9:17
 */
public class HuffmanTree {

        public static class Node<E>{
                E data;
                double weight;
                Node left;
                Node right;

                public Node(E data, double weight){
                        this.data = data;
                        this.weight = weight;
                }
        }

        private static Node createTree(List<Node> nodes){
                while (nodes.size() > 1){
                        quickSort(nodes);
                        Node left = nodes.get(nodes.size()-1);
                        Node right = nodes.get(nodes.size()-2);
                        Node parent = new Node(null,left.weight + right.weight);
                        parent.left = left;
                        parent.right = right;
                        nodes.remove(nodes.size()-1);
                        nodes.remove(nodes.size()-1);
                        nodes.add(parent);
                }
                return nodes.get(0);
        }

        private static void swapNode(List<Node> nodes,int i, int j){
                Node temp = nodes.get(i);
                nodes.set(i,nodes.get(j));
                nodes.set(j,temp);
        }

        private static void subSort(List<Node> nodes,int start,int end){
                if (start < end){
                        Node base = nodes.get(start);
                        int i = start;
                        int j = end;
                        while (i < j){

                                while (j > i && nodes.get(j).weight <= base.weight){
                                        j--;
                                }

                                while (i < j && nodes.get(i).weight >=  base.weight){
                                        i++;
                                }

                                if (i < j){
                                        swapNode(nodes,i,j);
                                }
                        }
                        swapNode(nodes,start,i);
                        subSort(nodes,start,i-1);
                        subSort(nodes,i+1,end);
                }

        }

        private static void quickSort(List<Node> nodes){
                subSort(nodes,0,nodes.size()-1);
        }

        private static List<Double> preOrderPrint(Node root){
                List<Double> result = new ArrayList<>();
               /* result.add(root.weight);
                if (root.left != null)
                        result.addAll(preOrderPrint(root.left));
                if (root.right != null)
                        result.addAll(preOrderPrint(root.right));*/
                java.util.Stack<Node> stack = new Stack<>();
                while (root != null){
                        stack.add(root);
                        result.add(root.weight);
                        root = root.left;
                }

                while (!stack.isEmpty()){
                        Node temp = stack.pop().right;
                        while (temp != null){
                                stack.push(temp);
                                result.add(temp.weight);
                                temp = temp.left;
                        }
                }
                return result;
        }


        public static void main(String[] args) {
                List<Node> nodes = new ArrayList<>();
                nodes.add(new Node("A",40.0));
                nodes.add(new Node("B",8.0));
                nodes.add(new Node("C",10.0));
                nodes.add(new Node("D",30.0));
                nodes.add(new Node("E",10.0));
                nodes.add(new Node("F",2.0));
                Node root = HuffmanTree.createTree(nodes);
                System.out.println(HuffmanTree.preOrderPrint(root));
        }
}
