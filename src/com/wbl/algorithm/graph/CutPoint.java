package com.wbl.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created withSimple_love
 * Date: 2016/3/7.
 * Time: 10:55
 * 图的割点
 * 如果删除某个点之后，图不在联通，这样的顶点成为割点
 */
public class CutPoint {
        private int index = 0;
        private int nodeNum = 0;
        private int[] num;
        private int[] low;
        private int[][] edge;
        private List<Integer> list = new ArrayList<>();

        CutPoint(int nodeNum,int[][] edge){
                this.nodeNum = nodeNum;
                this.edge = edge;
                num = new int[nodeNum];
                low = new int[nodeNum];
        }

        public List<Integer> getCutPoint(){
                dfs(0,0);
                return list;
        }


        private void dfs(int cur,int father){
                int child = 0;

                index++;
                num[cur] = index;
                low[cur] = index;
                for (int i = 0; i < nodeNum; i++){
                        if (edge[cur][i] == 1){
                                if (num[i] == 0){
                                        child++;
                                        dfs(i,cur);
                                        low[cur] = Math.min(low[cur],low[i]);
                                        if (cur != 0 && low[i] >= num[cur])
                                                list.add(cur);
                                        if (cur == 0 && child == 2)
                                                list.add(cur);
                                }else if (i != father){
                                        low[cur] = Math.min(low[cur],num[i]);
                                }
                        }
                }
                System.out.println("index = " + index);
                System.out.println("num = " + Arrays.toString(num));
                System.out.println("low = " + Arrays.toString(low));
        }

        public static void main(String[] args) {
                int[][] edge = new int[][]{
                        {0,0,1,1,0,0},
                        {0,0,1,1,1,1},
                        {1,1,0,0,0,0},
                        {1,1,0,0,0,0},
                        {0,1,0,0,0,1},
                        {0,1,0,0,1,0}
                };

                CutPoint point = new CutPoint(6,edge);
                System.out.println(point.getCutPoint());
        }
}
