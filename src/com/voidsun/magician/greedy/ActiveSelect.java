package com.voidsun.magician.greedy;

import java.util.LinkedList;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/6
 * @Email voidsun@126.com
 */
public class ActiveSelect {

    static Active active(int start, int end){
        return new Active(start, end);
    }

    void select(Active[] sortedActives, int deadline){
        LinkedList<Active> selectedList = new LinkedList<>();
        select(selectedList, sortedActives, -1, deadline);
        selectedList.stream().forEach(a -> System.out.print(a));
    }

    void select(LinkedList<Active> selectedList, Active[] sortedActives, int idx, int start){
        while(++idx < sortedActives.length && sortedActives[idx].end >= start);
        if(idx < sortedActives.length) {
            selectedList.add(sortedActives[idx]);
            if (idx != sortedActives.length) select(selectedList, sortedActives, idx, sortedActives[idx].start);
        }
    }



    public static void main(String[] args) {
        Active[] sortedActives = {active(12, 16), active(8, 12), active(8, 11),
                active(6, 10), active(5, 9), active(5, 7), active(3, 9), active(3, 5),
                active(2, 14), active(1, 4), active(0, 6)};
        new ActiveSelect().select(sortedActives, 17);
    }
}
//select last start
class Active{
    int start, end;
    public Active(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "<"+start+"-"+end+">";
    }
}
