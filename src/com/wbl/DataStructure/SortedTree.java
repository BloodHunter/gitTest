package com.wbl.DataStructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created with Simple_love
 * Date: 2016/7/14.
 * Time: 16:41
 */
public class SortedTree<T extends Comparable> {
        static class Node{
                Object data;
                Node left;
                Node right;
                Node parent;

                public Node(Object data, Node parent,Node left,Node right){
                        this.data = data;
                        this.parent = parent;
                        this.left = left;
                        this.right = right;
                }

                @Override
                public String toString() {
                        return "[data=" + data + "]";
                }

                @Override
                public boolean equals(Object obj) {
                        if (this == obj)
                                return true;
                        if (obj.getClass() == Node.class){
                                Node target = (Node) obj;
                                return data.equals(target.data) && left == target.left && right == target.right &&
                                        parent == target.parent;
                        }
                        return false;
                }
        }

        private Node root;

        public SortedTree() {
                root = null;
        }

        public SortedTree(T o) {
                this.root = new Node(o,null,null,null);
        }

        public void add(T ele){
                if (root == null){
                        root = new Node(ele,null,null,null);
                }else {
                        Node current = root;
                        Node parent = null;
                        int cmp = 0;
                        while (current != null){
                                parent = current;
                                cmp = ele.compareTo(current.data);
                                if (cmp > 0){
                                        current = current.right;
                                }else {
                                        current = current.left;
                                }
                        }

                        Node newNode = new Node(ele,parent,null,null);
                        if (cmp > 0){
                                parent.right = newNode;
                        }else{
                                parent.left = newNode;
                        }
                }
        }

        public void remove(T ele){
                Node target = getNode(ele);
                if (target == null)
                        return;
                if (target.left == null && target.right == null){
                        if (target == root)
                                root = null;
                        if (target == target.parent.left)
                                target.parent.left = null;
                        else
                                target.parent.right = null;
                        target.parent = null;
                }else if (target.left == null){
                        if (target == root){
                                root = target.right;
                        }else {
                                if (target.parent.left == target)
                                        target.parent.left = target.right;
                                else
                                        target.parent.right = target.right;
                        }
                        target.right.parent = target.parent;
                        target.parent = null;
                }else if (target.right == null){
                        if (target == root){
                                root = target.left;
                        }else {
                                if (target.parent.left == target)
                                        target.parent.left = target.left;
                                else
                                        target.parent.right = target.left;
                        }
                        target.left.parent = target.parent;
                        target.parent = null;
                }else {
                        Node leftMaxNode = target.left;  //小于目标节点的最大节点
                        while (leftMaxNode.right != null){
                                leftMaxNode = leftMaxNode.right;
                        }
                        leftMaxNode.parent.right = null;
                        leftMaxNode.parent = target.parent;
                        if (target.parent.left == target){
                                target.parent.left = leftMaxNode;
                        }else
                                target.parent.right = leftMaxNode;

                        leftMaxNode.left = target.left;
                        leftMaxNode.right = target.right;
                        target.parent = target.left = target.right = null;
                }
        }

        public Node getNode(T ele){
                Node p = root;
                while (p != null){
                        int cmp = ele.compareTo(p.data);
                        if (cmp > 0){
                                p = p.right;
                        }else if (cmp < 0){
                                p = p.left;
                        }else {
                                return p;
                        }
                }
                return null;
        }

        public List<Object> printInorder(){
                List<Object> result = new ArrayList<>();
                Stack<Node> stack = new Stack<>();
                Node p = root;
                while (p != null){
                        stack.push(p);
                        p = p.left;
                }

                while (!stack.isEmpty()){
                        Node temp = stack.pop();
                        result.add(temp.data);
                        temp = temp.right;
                        while (temp != null){
                                stack.push(temp);
                                temp = temp.left;
                        }
                }
                return result;
        }

        public List<Node> breadthFirst(){
                Queue<Node> queue = new ArrayDeque<>();
                List<Node> result = new ArrayList<>();
                if (root != null){
                        queue.offer(root);
                }
                while (!queue.isEmpty()){
                        Node temp = queue.poll();
                        result.add(temp);
                        if (temp.left != null)
                                queue.add(temp.left);
                        if (temp.right != null)
                                queue.add(temp.right);
                }
                return result;
        }

        public static void main(String[] args) {
                SortedTree<Integer> tree = new SortedTree<>();
                tree.add(5);
                tree.add(20);
                tree.add(10);
                tree.add(3);
                tree.add(8);
                tree.add(15);
                tree.add(30);
                System.out.println(tree.printInorder());
                tree.remove(20);
                System.out.println(tree.breadthFirst());
        }
}
