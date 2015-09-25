package com.wbl.DataStructure;

/**
 * Created by Simple_love on 2015/8/31.
 * 基础数据结构————双向链表
 */
public class DoubleList {
        class Node{
                private Node prior;
                private Node next;
                private String data;
                Node(){}
                Node(String data){
                        this.data = data;
                }
                public Node getPrior() {
                        return prior;
                }

                public void setPrior(Node prior) {
                        this.prior = prior;
                }

                public Node getNext() {
                        return next;
                }

                public void setNext(Node next) {
                        this.next = next;
                }

                public String getData() {
                        return data;
                }

                public void setData(String data) {
                        this.data = data;
                }
                public void add(Node node){
                        if (this.next != null){
                                this.next.add(node);
                        }else {
                                this.next = node;
                                node.prior = this;
                                rail = node;
                        }
                }
        }
        private Node head;
        private Node rail;
        DoubleList(){
                head = new Node();
                rail = head;
        }
        public void addNode(String data){
                Node node = new Node(data);
                if (head.next == null){
                        head.next = node;
                        node.prior = head;
                        rail = node;
                }else {
                        head.next.add(node);
                }
        }

        public boolean insertNode(int index, String data){
                Node node = new Node(data);
                if (index <= 0){
                        System.out.println("Insert node failed !");
                        return false;
                }
                Node temp = head;
                for (; index > 1; index --){
                        temp = temp.next;
                        if (temp == null)
                                break;
                }

                if (index > 0 && temp == null){
                        System.out.println("Insert node failed !");
                        return false;
                }

                if (temp.next == null){
                        temp.add(node);
                        rail = node;
                        return true;
                }else {
                        node.next = temp.next;
                        temp.next.prior = node;
                        node.prior = temp;
                        temp.next = node;
                        return true;
                }
        }

        public void printBySeq(){
                Node node = head;
                while (node.next != null){
                        System.out.print(node.next.getData() + " ");
                        node = node.next;
                }
                System.out.println();
        }

        public void printByRec(){
                Node temp = rail;
                while (temp != null){
                        if (temp.getData() != null)
                                System.out.print(temp.getData() + " ");
                        temp = temp.prior;
                }
                System.out.println();
        }

        public static void main(String args[]){
                DoubleList doubleList = new DoubleList();
                doubleList.addNode("A");
                doubleList.addNode("B");
                doubleList.addNode("C");
                doubleList.insertNode(1,"D");
                doubleList.printBySeq();

                doubleList.insertNode(4,"E");
                doubleList.printBySeq();

                doubleList.insertNode(6,"F");
                doubleList.printBySeq();
                doubleList.printByRec();
        }
}
