package com.wbl.DataStructure;

/**
 * Created by Simple_love on 2015/8/14.
 */
public class LinkList {
       public class Node{
                private String data;
                private Node next;
                public Node(){
                        next = null;
                }
                public Node(String data){
                        this.data = data;
                }

                public String getData() {
                        return data;
                }

                public void setData(String data) {
                        this.data = data;
                }

                public Node getNext() {
                        return next;
                }

                public void setNext(Node next) {
                        this.next = next;
                }

                public void add(Node node){
                        if (this.next == null){
                                this.next = node;
                        }else {
                                this.next.add(node);
                        }
                }

                public boolean find(String data){
                        if (data.equals(this.data)){
                                return true;
                        }else {
                                if (this.next == null){
                                        return false;
                                }else {
                                      return   this.next.find(data);
                                }
                        }
                }
        }
        private Node head;

        public Node getHead() {
                return head;
        }

        public void setHead(Node head) {
                this.head = head;
        }

        public LinkList(){
                head = new Node();
        }

        public LinkList(Node head){
                this.head = head;
        }

        public void addNode(String data){
                Node node = new Node(data);
                if (this.head == null){
                        this.head = node;
                }else {
                        this.head.add(node);
                }
        }

        public boolean insertNode(int index, Node node){
                Node pre = head;
                for (; index > 1; index--){
                        pre = pre.next;
                        if (pre == null){
                                break;
                        }
                }
                if (pre == null && index > 0){
                        return false;
                }
                node.next = pre.next;
                pre.next = node;
                return true;
        }
        public void print(){
                Node temp = head;
                while (temp.next != null){
                        System.out.print(temp.next.getData() + ",");
                        temp = temp.next;
                }
                System.out.println();
        }

        public static void main(String[] args){
                LinkList linkList = new LinkList();
                linkList.addNode("A");
                linkList.addNode("B");
                linkList.addNode("C");
                linkList.print();
                LinkList.Node node1 = linkList.new Node("E");
                linkList.insertNode(5,node1);
                linkList.print();
        }
}
