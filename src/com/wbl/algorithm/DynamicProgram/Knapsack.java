package com.wbl.algorithm.DynamicProgram;

/**
 * Created by Lulala on 2015/7/6.
 * Type: Dynamic programing
 * problem: 0-1 背包问题
 */
public class Knapsack {
    /**
     * @param weight 物品的重量
     * @param value 物品的价格
     * @param contain 背包的容量
     * @return 背包问题的最优解
     * */
    public static int package0_1(int[] weight,int[] value,int contain){
        int len = weight.length;
        int[][] m = new int[len+1][contain + 1];
        for(int i = 0; i <= contain; i++){
           m[0][i] = 0;
        }
        for(int i=0; i <= len; i++){
            m[i][0] = 0;
        }
        for(int i = 1; i <= len; i++){
            for(int j = 1; j <= contain; j++){
                if(j >= weight[i-1]){
                    m[i][j] = Math.max(m[i-1][j],m[i-1][j-weight[i-1]] + value[i-1]);
                }else
                    m[i][j] = m[i-1][j];
            }
        }
        return m[len][contain];
    }

    public static int other_package0_1(int[] weight,int[] value, int contain){
        int[] m = new int[contain + 1];
        for(int i = 0; i < weight.length; i++ ){
            for (int j = contain; j >= weight[i]; j--){
                if(m[j-weight[i]] + value[i] > m[j])
                    m[j] = m[j-weight[i]] + value[i];
            }
        }
        return m[contain];
    }

    public static void main(String[] args){
        int[] weight = {5, 4, 6, 3};
        int[] value = {10, 40, 30, 50};
        System.out.println( other_package0_1(weight,value,10));
    }
}
