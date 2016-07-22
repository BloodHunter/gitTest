package com.wbl.test;

import java.util.Scanner;

/**
 * Created with Simple_love
 * Date: 2016/4/8.
 * Time: 19:20
 */
public class Solution {
        public static void main(String[] args) {
                System.out.println(func(2015));
                Scanner in = new Scanner(System.in);
                while (in.hasNext()){
                        char[][] grid = new char[3][3];
                        int c0 = 0,cx =0;
                        for (int i = 0;i < 3;i++){
                                String line = in.nextLine();
                                for (int j =0;j<3;j++){
                                        grid[i][j] = line.charAt(j);
                                        if (grid[i][j] == '0')
                                                c0++;
                                        if (grid[i][j] == 'X')
                                                cx++;
                                }
                        }
                        if (Math.abs(c0-cx) > 1)
                                System.out.println("x");
                        else if (c0 == cx){
                               for (int i = 0; i < 3; i++){
                                       if (judeColumn(i,grid) || judeRow(i,grid))
                                               System.out.println("1 won");
                               }
                                if (judeX(grid))
                                        System.out.println("1 won");
                                if (c0+cx ==9)
                                        System.out.println("Draw");
                                else
                                        System.out.println("1");
                        }else{
                                for (int i = 0; i < 3; i++){
                                        if (judeColumn(i,grid) || judeRow(i,grid))
                                                System.out.println("2 won");
                                }
                                if (judeX(grid))
                                        System.out.println("2 won");
                                if (c0+cx ==9)
                                        System.out.println("Draw");
                                else
                                        System.out.println("2");
                        }
                }
        }


        public static boolean judeRow(int i,char[][] grid){
                if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][2] !='.')
                        return true;
                return false;
        }

        public static boolean judeColumn(int j,char[][] grid){
                if (grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j] && grid[2][j] !='.')
                        return true;
                return false;
        }

        public static boolean judeX(char[][] grid){
                if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] !='.')
                        return true;
                if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] !='.')
                        return true;
                return false;
        }

        public static int func(int x){
                int coumt =0;
                while (x!=0){
                        coumt++;
                        x = x &(x-1);
                }
                return coumt;
        }
}
