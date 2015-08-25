package com.voidsun.magician.leetcode;

import java.util.PriorityQueue;

/**
 *  https://leetcode.com/problems/ugly-number-ii/
 *
 *  Write a program to find the n-th ugly number.
    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
    Note that 1 is typically treated as an ugly number.
 * @Description
 * @Author voidsun
 * @Date 2015/8/24
 * @Email voidsun@126.com
 */
public class UglyNumberII {
    long[] cache = {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12};
    long[] mcache = {0, 1, 2, 3, 15, 4, 20, 5, 25, 6, 18, 30, 8, 16, 24, 40, 9, 18, 27, 45, 10, 20, 30, 50, 12, 36, 60};
    PriorityQueue<Long> priorityQueue = new PriorityQueue<>();

    public int nthUglyNumber(int n) {
        if(n < cache.length) return (int)cache[n];
        for(long i : mcache){
            priorityQueue.add(i);
        }
        int j = cache.length;
        long prev = cache[j-1];
        while(j++ < n){
            while(true) {
                long i = priorityQueue.poll();
                if (i > prev) {
                    prev = i;
                    break;
                }
            }
            priorityQueue.add(prev*2);
            priorityQueue.add(prev*3);
            priorityQueue.add(prev*5);
        }
        while(true) {
            long i = priorityQueue.poll();
            if (i > prev) {
                return (int)i;
            }
        }
    }

    public static void main(String[] args) {
        int i = 10;
        int prev = 12;
        UglyNumber un = new UglyNumber();
        UglyNumberII un2 = new UglyNumberII();
        while(i++ < 1600) {
            int next = un2.nthUglyNumber(i);
            while(prev++ < next){
                if(un.isUgly(prev)){
                    System.out.print("----------" + prev);
                }
            }
            System.out.println("=====["+i+"]========="+next);
        }
    }

}
