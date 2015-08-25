package com.voidsun.magician.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 *
 *   All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
     Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
     For example,
     Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
     Return:
     ["AAAAACCCCC", "CCCCCAAAAA"].
 * @Description
 * @Author voidsun
 * @Date 2015/8/25
 * @Email voidsun@126.com
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> ht = new HashSet<>();
        Set<String> picked = new HashSet<>();
        List<String> result = new LinkedList<>();
        for(int i=0; i<=s.length()-10; i++){
            String seq = s.substring(i, i+10);
            if(ht.contains(seq)){
                if(!picked.contains(seq)){
                    result.add(seq);
                    picked.add(seq);
                }
            }else{
                ht.add(seq);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        for(String s : new RepeatedDNASequences().findRepeatedDnaSequences("AAAAAAAAAAAA")){
            System.out.println(s);
        }
    }
}
