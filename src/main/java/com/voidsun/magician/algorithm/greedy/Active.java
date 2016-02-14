package com.voidsun.magician.algorithm.greedy;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/7
 * @Email voidsun@126.com
 */
public class Active {
    int start, end;
    boolean isVisit = false;
    public Active(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "<"+start+"-"+end+">";
    }

    static Active active(int start, int end){
        return new Active(start, end);
    }

    boolean visit(){
        if(!isVisit){
            isVisit = true;
            return false;
        }
        return true;
    }
}
