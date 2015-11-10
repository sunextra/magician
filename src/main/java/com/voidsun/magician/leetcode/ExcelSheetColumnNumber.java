package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 * @Description
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

    For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
 * @Author voidsun
 * @Date 2015/11/10
 * @Email voidsun@126.com
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int result = 0;
        for(char c : s.toCharArray()){
            result = result * 24 + c -64;
        }
        return result;
    }

    public static void main(String[] args) {
        ExcelSheetColumnNumber run = new ExcelSheetColumnNumber();
        System.out.println(run.titleToNumber("A"));
        System.out.println(run.titleToNumber("AA"));
        System.out.println(run.titleToNumber("AB"));
    }
}
