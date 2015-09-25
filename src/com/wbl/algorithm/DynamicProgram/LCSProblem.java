package com.wbl.algorithm.DynamicProgram;

/**
 * Created by Lulala on 2015/7/6.
 * Type: Dynamic programing
 * Problem: 最长公共子序列问题
 */
public class LCSProblem {
    public static void LCSLength(String X, String Y){
        int m = X.length();
        int n = Y.length();
        int[][] c= new int[m+1][n+1];
        for (int i = 1; i <= m; i++)
            c[i][0] = 0;
        for (int j = 1; j <= n; j++)
            c[0][j] = 0;

        for(int i = m - 1; i >= 0; i--){
            for(int j = n-1; j >= 0; j--){
                if(X.charAt(i) == Y.charAt(j)){
                    c[i][j] = c[i+1][j+1] + 1;
                }else {
                    c[i][j] = Math.max(c[i][j+1],c[i+1][j]);
                }
            }
        }

        System.out.println(X);
        System.out.println(Y);

        int i = 0, j = 0;
        while (i <= m-1 && j <= n-1){
            if(X.charAt(i) == Y.charAt(j)){
                System.out.print(X.charAt(i));
                i++;
                j++;
            }else if (c[i+1][j] >= c[i][j])
                i++;
            else
                j++;
        }
    }

    public static void main(String[] args){
        LCSLength("abcd","acd");
    }
}
