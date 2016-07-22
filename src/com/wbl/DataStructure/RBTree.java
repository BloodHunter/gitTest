package com.wbl.DataStructure;

import java.util.*;

/**
 * Created with Simple_love
 * Date: 2016/5/20.
 * Time: 14:13
 *
 * 定义插入的节点为N，插入节点的父亲节点为P, 插入节点的祖父节点为G，插入节点的叔叔节点为U
 */
public class RBTree {

        public final static int RED = 0;
        public final static int BLACK = 1;

        private RBNode root;

        public static class RBNode{

                private RBNode parent;
                private RBNode right;
                private RBNode left;

                private int value;
                private int color = RED;

                public RBNode(int value) {
                        this.value = value;
                }
        }

        private RBNode getGrandparent(RBNode node){
                return node.parent == null ? null : node.parent.parent;
        }

        private RBNode getUncle(RBNode node){
                RBNode grand = getGrandparent(node);
                if (grand == null)
                        return null;
                else
                        return grand.left == node.parent ? grand.right : grand.left;
        }

        /*
        * 插入的节点N为根节点
        * */
        private void insert_case1(RBNode node){
                if (node.parent == null){
                        node.color = BLACK;
                }else
                        insert_case2(node);
        }

        /*
        * 插入节点N的父亲节点P是黑色的
        * */
        private void insert_case2(RBNode node){
                if (node.parent.color == BLACK)
                        return;
                else
                        insert_case3(node);
        }

        /*
        * 插入节点N的父亲节点P是红色的，而且叔叔节点U是红色的
        * */
        private void insert_case3(RBNode node){
                RBNode uncle = getUncle(node);
                RBNode grand = getGrandparent(node);
                if (uncle != null && uncle.color == RED){
                        node.parent.color = BLACK;
                        uncle.color = BLACK;
                        grand.color = RED;
                        insert_case1(grand);
                }else
                        insert_case4(node);
        }

        /*
        * 插入节点N的父亲节点P是红色，而且
        * 叔叔节点U是黑色的，而且
        * 插入节点N是P的右孩子
        * */
        private void insert_case4(RBNode node){
                RBNode grand = getGrandparent(node);
                if (node == node.parent.right && node.parent == grand.left){
                        rotateLeft(node.parent);
                        node = node.left;
                }else if (node == node.parent.left && node.parent == grand.right){
                        rotateRight(node.parent);
                        node = node.right;
                }
                insert_case5(node);
        }

        /*
        * 插入节点N的父亲节点P是红色，而且
        * 叔叔节点U是黑色的，而且
        * 插入节点N是P的左孩子
        * */
        private void insert_case5(RBNode node){
                RBNode grand = getGrandparent(node);
                grand.color = RED;
                node.parent.color = BLACK;
                if (node == node.parent.left && node.parent == grand.left)
                        rotateRight(grand);
                else
                        rotateLeft(grand);
        }

        /*
        *   对X进行左旋
        *               px                                           px
        *             /                                               /
        *           x                                               y
        *         /   \           ----->                       /  \
        *       lx   y                                          x    ry
        *          /  \                                        /  \
        *       ly    ry                                     lx  ly
        *
        * */
        private void rotateLeft(RBNode x){
                RBNode y = x.right;
                x.right = y.left;
                if (y.left != null)
                        y.left.parent = x;
                y.parent = x.parent;

                if (x.parent != null){
                        if (x == x.parent.left)
                                x.parent.left = y;
                        else
                                x.parent.right = y;
                }

                y.left = x;
                x.parent = y;
        }

        /*
        * 对Y进行右旋
        *             px                                            px
        *             /                                               /
        *           y                                               x
        *         /   \           -------->                   /  \
        *       x   ry                                          lx    y
        *     /  \                                                   /  \
        *  lx    rx                                               rx  ry
        *
        * */
        private void rotateRight(RBNode y){
                RBNode x = y.right;
                if (x.right != null)
                        x.right.parent = y;

                x.parent = y.parent;
                if (y.parent != null){
                        if (y == y.parent.left)
                                y.parent.left = x;
                        else
                                y.parent.right = x;
                }

                x.right = y;
                y.parent = x;
        }

        /*private void insert(RBNode node){
                if (root == null){
                        root = node;
                        insert_case1(node);
                        return;
                }
                RBNode temp = root;
                while (temp != null){
                        if (node.value < temp.value){
                                if (temp.left == null){
                                        temp.left = node;
                                        node.parent = temp;
                                        insert_case1(node);
                                        print();
                                        return;
                                }else
                                        temp = temp.left;
                        }else if (node.value > temp.value){
                                if (temp.right == null){
                                        temp.right = node;
                                        node.parent = temp;
                                        insert_case1(node);
                                        print();
                                        return;
                                }else
                                        temp = temp.right;
                        }else{
                                print();
                                return;
                        }
                }
        }*/

        public void insert(RBNode x){
                if (root == null){
                        root = x;
                        fixAfterInsert(x);
                }else {
                        RBNode temp = root;
                        while (temp != null){
                                if (x.value < temp.value){
                                        if (temp.left == null){
                                                temp.left = x;
                                                x.parent = temp;
                                                break;
                                        }else{
                                                temp = temp.left;
                                        }
                                }else if (x.value > temp.value){
                                        if (temp.right == null){
                                                temp.right = x;
                                                x.parent = temp;
                                                break;
                                        }else
                                                temp = temp.right;
                                }else {
                                        break;
                                }
                        }
                        fixAfterInsert(x);
                }
                print();
        }


        /*
        *将红黑树内的某一个节点删除。需要执行的操作依次是：
        * 首先，将红黑树当作一颗二叉查找树，将该节点从二叉查找树中删除；
        * 然后，通过"旋转和重新着色"等一系列来修正该树，使之重新成为一棵红黑树。
        *
        * 第一步：将红黑树当作一颗二叉查找树，将节点删除。
        * 这和"删除常规二叉查找树中删除节点的方法是一样的"。分3种情况：
        *  ① 被删除节点没有儿子，即为叶节点。那么，直接将该节点删除就OK了。
        *  ② 被删除节点只有一个儿子。那么，直接删除该节点，并用该节点的唯一子节点顶替它的位置。
        *  ③ 被删除节点有两个儿子。那么，先找出它的后继节点；然后把“它的后继节点的内容”复制给“该节点的内容”；
        * 之后，删除“它的后继节点”。在这里，后继节点相当于替身，在将后继节点的内容复制给"被删除节点"之后，再将后继节点删除。
        * 这样就巧妙的将问题转换为"删除后继节点"的情况了，下面就考虑后继节点。
        * 在"被删除节点"有两个非空子节点的情况下，它的后继节点不可能是双子非空。
        * 既然"的后继节点"不可能双子都非空，就意味着"该节点的后继节点"要么没有儿子，要么只有一个儿子。
        * 若没有儿子，则按"情况① "进行处理；若只有一个儿子，则按"情况② "进行处理。
        * */
        public void remove(int value){
                RBNode target = getNode(value);
                if (target == null)
                        return;
                /*
                * 情况3
                * */
                //如果被删除节点的左右子树都不为空
                if (target.left != null && target.right != null){
                        //找到target节点中序遍历的前一个节点
                        //s用于保存target节点左子树值最大的节点
                        RBNode s = target.left;
                        while (s.right != null){
                                s = s.right;
                        }
                        target.value = s.value;
                        target = s;
                }
                //被删除节点只有一个儿子。那么用该节点的唯一子节点顶替它的位置。
                RBNode replace = (target.left != null ? target.left: target.right);
                /*
                * 情况2
                * */
                if (replace != null){
                        replace.parent = target.parent;
                        //如果target本身是根节点
                        if (target.parent == null)
                                root = replace;
                        else if (target == target.parent.left)
                                target.parent.left = replace;
                        else
                                target.parent.right = replace;

                        target.left = target.right = target.parent = null;
                        if (target.color == BLACK){
                                fixAfterRemove(replace);
                        }

                }else if (target.parent == null){
                        root = null;
                }else {                         //情况1
                        if (target.color == BLACK){
                                fixAfterRemove(target);
                        }
                        if (target.parent != null){
                                if (target == target.parent.left)
                                        target.parent.left = null;
                                else if (target == target.parent.right)
                                        target.parent.right = null;
                                target.parent = null;
                        }
                }
                print();
        }

        private void fixAfterRemove(RBNode x){
                //直到x不是根节点，而且x的颜色为黑色
                while (x != root && x.color == BLACK){
                        //x是其父节点的左节点
                        if (x == leftOf(x.parent)){
                                //获取x的兄弟节点
                                RBNode sib = rightOf(x.parent);
                                if (colorOf(sib) == RED){
                                        setColor(sib,BLACK);
                                        setColor(x.parent,RED);
                                        rotateLeft(x.parent);
                                        sib = rightOf(x.parent);
                                }

                                if (colorOf(sib.left) == BLACK  && colorOf(sib.right) == BLACK){
                                        setColor(sib,RED);
                                        x = x.parent;
                                }else {
                                        if (colorOf(sib.right) == BLACK){
                                                setColor(leftOf(sib),BLACK);
                                                setColor(sib,RED);
                                                rotateRight(sib);
                                                sib = rightOf(x.parent);
                                        }
                                        setColor(sib,colorOf(x.parent));
                                        setColor(x.parent,BLACK);
                                        setColor(rightOf(sib),BLACK);
                                        rotateLeft(x.parent);
                                        x = root;
                                }

                        }else {
                                RBNode sib = leftOf(x.parent);
                                if (colorOf(sib) == RED){
                                        setColor(sib,BLACK);
                                        setColor(x.parent,RED);
                                        rotateRight(x.parent);
                                        sib = leftOf(x.parent);
                                }

                                if (colorOf(sib.left) == BLACK && colorOf(sib.right) == BLACK){
                                        setColor(sib,RED);
                                        x = x.parent;
                                }else {
                                        if (colorOf(leftOf(sib)) == BLACK){
                                                setColor(rightOf(sib), BLACK);
                                                setColor(sib,RED);
                                                rotateLeft(sib);
                                                sib = leftOf(x.parent);
                                        }
                                        setColor(sib,colorOf(x.parent));
                                        setColor(x.parent,BLACK);
                                        setColor(leftOf(sib),BLACK);
                                        rotateRight(x.parent);
                                        x = root;
                                }
                        }
                }
                setColor(x, BLACK);
        }

        private void fixAfterInsert(RBNode x){
                //直到X为根节点或者X的父亲节点不为红色
                while (x != null && x != root && x.parent.color == RED){
                        //X为父亲节点的左孩子
                        if (x.parent == leftOf(getGrandparent(x))){
                                RBNode uncle = getUncle(x);
                                //X的叔叔节点是红色的
                                if (colorOf(uncle) == RED){
                                        //设置父亲节点为黑色
                                        setColor(x.parent,BLACK);

                                        //设置叔叔节点为黑色
                                        setColor(uncle,BLACK);

                                        //设置祖先节点为红色
                                        setColor(getGrandparent(x),RED);

                                        //将祖先节点设置为新的当前节点
                                        x = getGrandparent(x);
                                }else {
                                        //X是父亲节点的右孩子
                                        if (x == rightOf(x.parent)){
                                                x = x.parent;
                                                rotateLeft(x);
                                        }
                                        setColor(x.parent,BLACK);
                                        setColor(getGrandparent(x),RED);
                                        rotateRight(getGrandparent(x));
                                }
                        }else {
                                RBNode uncle = getUncle(x);
                                if (colorOf(uncle) == RED){
                                        setColor(x.parent,BLACK);
                                        setColor(uncle,BLACK);
                                        setColor(getGrandparent(x),RED);
                                        x = getGrandparent(x);
                                }else {
                                        if (x == leftOf(x.parent)){
                                                x = x.parent;
                                                rotateRight(x);
                                        }
                                        setColor(x.parent,BLACK);
                                        setColor(getGrandparent(x),RED);
                                        rotateLeft(getGrandparent(x));
                                }
                        }
                }
                root.color = BLACK;
        }

        private RBNode leftOf(RBNode p){
                return p == null ? null : p.left;
        }

        private RBNode rightOf(RBNode p){
                return p == null ? null : p.right;
        }

        private int colorOf(RBNode node){
                return node == null ? BLACK : node.color;
        }

        private void setColor(RBNode node, int color){
                if (node != null)
                        node.color = color;
        }


        private void print(){
                System.out.println("***************************");
                Queue<RBNode> queue = new LinkedList<>();
                queue.add(root);
                while (!queue.isEmpty()){
                        RBNode temp = queue.poll();
                        System.out.print("value = " + temp.value);
                        if (temp.color == RED)
                                System.out.println(" ,color = red" );
                        else
                                System.out.println(" ,color = black");
                        if (temp.left != null)
                                queue.add(temp.left);
                        if (temp.right != null)
                                queue.add(temp.right);
                }
        }

        private RBNode getNode(int value){
                RBNode temp = root;
                while (temp != null){
                        if (temp.value > value)
                                temp = temp.left;
                        else if (temp.value < value)
                                temp = temp.right;
                        else
                                return temp;
                }
                return null;
        }

        public static void main(String[] args) {
                RBTree rbTree = new RBTree();
                RBNode node1 = new RBNode(13);
                rbTree.insert(node1);
                RBNode node2 = new RBNode(8);
                rbTree.insert(node2);
                RBNode node3 = new RBNode(17);
                rbTree.insert(node3);
                RBNode node4 = new RBNode(1);
                rbTree.insert(node4);
                RBNode node5 = new RBNode(11);
                rbTree.insert(node5);
                RBNode node6 = new RBNode(15);
                rbTree.insert(node6);
                RBNode node7 = new RBNode(25);
                rbTree.insert(node7);
                RBNode node8 = new RBNode(6);
                rbTree.insert(node8);
                RBNode node9 = new RBNode(22);
                rbTree.insert(node9);
                RBNode node10 = new RBNode(27);
                rbTree.insert(node10);
                rbTree.remove(17);
        }

}


