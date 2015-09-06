package com.voidsun.magician.std;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/8/12
 * @Email voidsun@126.com
 */
public class Std {
    public static char[][] buildMatrix(String... str){
        char[][] matrix = new char[str.length][];
        for(int i=0; i<matrix.length; i++){
            matrix[i] = str[i].toCharArray();
        }
        return matrix;
    }
}
