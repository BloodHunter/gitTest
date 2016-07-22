package com.wbl.leetcode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created with Simple_love
 * Date: 2016/3/27.
 * Time: 13:06
 */
public class Main {
        public static void main(String[] args) {
               Scanner in = new Scanner(System.in);
                int time = in.nextInt();
                int line = 1;
                while (line<=time){
                       int n = in.nextInt();
                        int m = in.nextInt();
                        int k = in.nextInt();
                        char[][] fileds = new char[n][m];
                        int Xx = 0,Xy=0;
                        for (int i = 0; i < n;i++){
                                String s = in.next();
                                for (int j = 0; j < m;j++){
                                        fileds[i][j] = s.charAt(j);
                                        if (fileds[i][j] == 'x'){
                                                Xx = i;
                                                Xy = j;
                                        }
                                }
                        }
                        int startX = 0,endX = m-1;
                        for (int j=Xy-1;j>=0;j--){
                                if (fileds[Xx][j] == 'y'){
                                        startX = j+1;
                                        break;
                                }
                        }
                        for (int j = Xy+1;j<m;j++){
                                if (fileds[Xx][j] == 'y')
                                        endX = j-1;
                        }
                        int startY = 0,endY=n-1;
                        for (int i = startX; i<= endX;i++){
                                for (int j = Xx-1; j>=0;j--){
                                        if (fileds[j][i]=='y'){
                                                startY = Math.max(startY,j-1);
                                                break;
                                        }
                                }
                                for (int j = Xx+1; j < n;j++){
                                        if (fileds[j][i]=='y' ){
                                                endY = Math.min(endY,j-1);
                                        }
                                }
                        }

                        int width = endX-startX +1;
                        int height = endY-startY+1;
                        System.out.println("Case #" + line + ": " + width*height);
                        line++;
                }
        }

}
