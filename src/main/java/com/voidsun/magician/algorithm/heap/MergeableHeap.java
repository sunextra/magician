package com.voidsun.magician.algorithm.heap;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/8
 * @Email voidsun@126.com
 */
public abstract class MergeableHeap<T extends Comparable> {
    int size = 0;

    abstract void insert(T t);

    /**
     *  返回最小的节点
     */
    abstract T minimum();

    /**
     *  pop最小的节点
     */
    abstract T extractMin();

    /**
     *  合并两个堆
     */
    abstract MergeableHeap union(MergeableHeap anotherHeap);
    int getSize(){
        return this.size;
    }
}
