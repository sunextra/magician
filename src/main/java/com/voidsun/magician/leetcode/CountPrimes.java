package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/count-primes/
 *
 * @Description
 * Description:
    Count the number of prime numbers less than a non-negative number, n.
 * @Author voidsun
 * @Date 2015/9/9
 * @Email voidsun@126.com
 */
public class CountPrimes {
    public int countPrimes(int n) {
        if(n<2) return 0;
        int result = 0;
        int[] bucket = new int[n];
        for(int i=2; i<n; i++){
            if(bucket[i] == 0){
                result ++;
                int j = 2;
                while(i*j < n){
                    bucket[i*j] = 1;
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CountPrimes countPrimes = new CountPrimes();
        System.out.println(countPrimes.countPrimes(3));
        System.out.println(countPrimes.countPrimes(20));

    }
}
