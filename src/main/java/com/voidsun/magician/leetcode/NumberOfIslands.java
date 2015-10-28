package com.voidsun.magician.leetcode;

import com.voidsun.magician.std.Std;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    11110
    11010
    11000
    00000
    Answer: 1

    Example 2:

    11000
    11000
    00100
    00011
    Answer: 3

 * @Author voidsun
 * @Date 2015/10/28
 * @Email voidsun@126.com
 */
public class NumberOfIslands {

    private String findKeeper(Map<String, String> islandGroup, String key){
        String keeper = islandGroup.get(key);
        while(keeper!=null && !keeper.equals(key)){
            key = keeper;
            keeper = islandGroup.get(key);
        }
        return key;
    }

    public int numIslands(char[][] grid) {
        Map<String, String> islandGroup = new HashMap<>();
        Set<String> islandKeeper = new HashSet<>();
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == '0'){
                    continue;
                }
                String key = i+","+j;
                String linkedLeft = null;
                String linkedTop = null;
                if(j-1>=0 && grid[i][j-1] == '1'){
                    linkedLeft = i + ","+ (j-1);
                }
                if(i-1>=0 && grid[i-1][j] == '1'){
                    linkedTop = (i-1) + "," + j;
                }
                if(linkedLeft == null && linkedTop == null){
                    islandGroup.put(key, key);
                    islandKeeper.add(key);
                    continue;
                }
                String leftKeeper = findKeeper(islandGroup, linkedLeft);
                String topKeeper = findKeeper(islandGroup, linkedTop);
                if(linkedLeft != null && linkedTop != null){
                    if(!leftKeeper.equals(topKeeper)) {
                        islandGroup.put(leftKeeper, topKeeper);
                        islandKeeper.remove(leftKeeper);
                    }
                    islandGroup.put(key, topKeeper);
                }else if(linkedLeft != null){
                    islandGroup.put(key, leftKeeper);
                }else if(linkedTop != null){
                    islandGroup.put(key, topKeeper);
                }
            }
        }
        return islandKeeper.size();
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfIslands().numIslands(Std.buildMatrix(
                "11111110111111111111",
                "11111101110101011111",
                "11111111111000111100",
                "10101111010101101111",
                "10111111011001110111",
                "01111101101001110111",
                "11111111011101011111",
                "11111011111010101011",
                "01111111010110111001",
                "01000111111011101010",
                "01111111110000111111",
                "10110111111111110101",
                "11111101011010111111",
                "11111111111011111110",
                "11010111011111111111",
                "11111101110111011001",
                "10111111111100111111",
                "10011100110111001111",
                "11101100010011110011",
                "11111111011111110101")));

    }
}
