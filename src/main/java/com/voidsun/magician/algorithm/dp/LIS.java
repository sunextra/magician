package com.voidsun.magician.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/8/17
 * @Email voidsun@126.com
 */
public class LIS {
    static int[] n = {5, 1, 15, 25, 3, 6, 9, 12, 18, 15, 18, 21, 19};
    static int[][] nn = new int[n.length][2];

    static void lis() {
        nn[0][0] = -1;
        nn[0][1] = 1;
        for (int i=1; i<n.length; i++) {
            nn[i][0] = -1;
            for(int j=0; j<i; j++){
                if(n[i] > n[j] && nn[j][1] > nn[i][1]){
                    nn[i][1] = nn[j][1];
                    nn[i][0] = j;
                }
            }
            nn[i][1] ++;
        }
    }


    public static void main(String[] args) {
        lis();

        List<Integer> ends = null;
        int maxLength = 0;
        for(int i=0; i<nn.length; i++){
            if(nn[i][1] > maxLength){
                maxLength = nn[i][1];
                ends = new ArrayList<>();
                ends.add(i);
            }else if(nn[i][1] == maxLength){
                ends.add(i);
            }
        }
        for(Integer i : ends){
            String lis = "";
            while(nn[i][0] != -1){
                lis = lis + n[i] + " ";
                i = nn[i][0];
            }
            lis = lis + n[i];
            System.out.println(lis);
        }
    }
}
