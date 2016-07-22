package com.wbl.algorithm.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created withSimple_love
 * Date: 2016/3/3.
 * Time: 15:32
 */
public class BFS {
        public static void bfs1(int[][] nums,int q,int p){
                int[][] next = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
                Queue<note> queue = new LinkedList<>();
                int[][] mark = new int[nums.length][nums[0].length];
                boolean flag = false;
                queue.add(new note(0,0,0));
                mark[0][0] = 1;
                while (!queue.isEmpty()){
                        note temp = queue.poll();
                        for (int i = 0; i <=3; i++){
                                int tx = temp.x + next[i][0];
                                int ty = temp.y + next[i][1];
                                if (tx < 0 || ty < 0 || tx >= nums.length || ty >= nums[0].length)
                                        continue;
                                if (nums[tx][ty] == 0 && mark[tx][ty] == 0){
                                        mark[tx][ty] = 1;
                                        queue.add(new note(tx,ty,temp.s + 1));
                                }

                                if (tx == q && ty ==p){
                                        flag = true;
                                        break;
                                }
                        }
                        if (flag){
                                System.out.println(temp.s);
                                break;
                        }
                }
        }

        public static void main(String[] args) {
                int[][] nums = new  int[][]{
                        {0,0,1,0},
                        {0,0,0,0},
                        {0,0,1,0},
                        {0,1,0,0},
                        {0,0,0,1},
                };
                bfs1(nums,3,2);
        }
}
class note{
        int x,y;
        int s;
        note(int x,int y,int s){
                this.x = x;
                this.y = y;
                this.s = s;
        }
        int getS(){
                return s;
        }
}
