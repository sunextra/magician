package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/interleaving-string/
 *
 * @Description
 *  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
    For example,
    Given:
    s1 = "aabcc",
    s2 = "dbbca",
    When s3 = "aadbbcbcac", return true.
    When s3 = "aadbbbaccc", return false.

 * @Author voidsun
 * @Date 2015/9/7
 * @Email voidsun@126.com
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        boolean[][] f = new boolean[s1.length()+1][s2.length()+1];
        f[0][0] = true;
        for(int i=1; i<s1.length()+1; i++)
            f[i][0] = s1.charAt(i-1)==s3.charAt(i-1);
        for(int i=1; i<s2.length()+1; i++)
            f[0][i] = s2.charAt(i-1)==s3.charAt(i-1);
        for(int i=1; i<s1.length()+1; i++){
            for(int j=1; j<s2.length()+1; j++){
                f[i][j] = (f[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1))
                        ||(f[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1));
            }
        }
        return f[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        InterleavingString is = new InterleavingString();
        System.out.println(is.isInterleave("aabc", "abad", "aabcabad"));
        System.out.println(is.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(is.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
		/*System.out.println(is.isInterleave("aa", "ab", "aaba"));
		System.out.println(is.isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa", "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab", "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));*/
    }
}
