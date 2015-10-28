package com.voidsun.magician.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/
 *
 * @Description
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

    You may assume that the intervals were initially sorted according to their start times.

    Example 1:
    Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

    Example 2:
    Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

    This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * @Author voidsun
 * @Date 2015/9/24
 * @Email voidsun@126.com
 */
public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>(intervals.size());
        if(intervals == null) return result;
        if(intervals.isEmpty()){
            result.add(newInterval);
            return result;
        }
        Interval mergeInterval = new Interval();
        boolean findHead = true;
        boolean findEnd = true;
        for(Interval interval : intervals){
            if(findHead && interval.end >= newInterval.start){
                findHead = false;
                mergeInterval.start = Math.min(interval.start, newInterval.start);
            }
            if(findEnd){
                if(interval.start <= newInterval.end){
                    mergeInterval.end = Math.max(interval.start, newInterval.end);
                }else{
                    findEnd = false;
                }
            }
            if(findHead || !findEnd) {
                result.add(interval);
            }
        }
        return result;
    }
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
