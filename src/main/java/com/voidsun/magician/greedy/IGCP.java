package com.voidsun.magician.greedy;

import java.util.LinkedList;

import static com.voidsun.magician.greedy.Active.active;

/**
 * interval-graph color problem
 * 区间染色问题 一些由边连接的节点，染色使与边上其他节点颜色均不相同，计算最少颜色数量
 * 等同于同时安排ActiveSelect最少线程数，两个活动时间有交集等同于边连接
 *
 * @Description
 * @Author voidsun
 * @Date 2015/9/6
 * @Email voidsun@126.com
 */
public class IGCP {
    void select(Active[] sortedActives){
        LinkedList<LinkedList<Active>> selectedList = new LinkedList<>();
        LinkedList<Active> firstList = new LinkedList<>();
        selectedList.add(firstList);
        firstList.add(sortedActives[0]);
        select(selectedList, sortedActives, 1);
        selectedList.stream().forEach(list -> {
            list.stream().forEach(a -> System.out.print(a + " "));
            System.out.println();
        });
    }

    void select(LinkedList<LinkedList<Active>> selectedList, Active[] sortedActives, int idx){
        for(int i=idx; i<sortedActives.length; i++){
            for(LinkedList<Active> linkedList : selectedList){
                if(sortedActives[i].end < linkedList.peekLast().start){
                    linkedList.add(sortedActives[i]);
                    select(selectedList, sortedActives, i+1);
                    return;
                }
            }
            LinkedList<Active> newList = new LinkedList<>();
            selectedList.add(newList);
            newList.add(sortedActives[i]);
            select(selectedList, sortedActives, idx+1);
            return;
        }
    }

    public static void main(String[] args) {
        Active[] sortedActives = {active(12, 16), active(8, 12), active(8, 11),
                active(6, 10), active(5, 9), active(5, 7), active(3, 9), active(3, 5),
                active(2, 14), active(1, 4), active(0, 6)};
        new IGCP().select(sortedActives);
    }

}
