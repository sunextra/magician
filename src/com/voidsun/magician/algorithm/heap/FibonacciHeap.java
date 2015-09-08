package com.voidsun.magician.algorithm.heap;

import com.voidsun.magician.std.exception.heap.NewValueGTOldValueException;
import com.voidsun.magician.std.exception.heap.NodeExsistException;
import com.voidsun.magician.std.exception.heap.NodeNotExsistException;
import com.voidsun.magician.std.exception.heap.NotCorrectTypeException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * 斐波那契堆(最小堆实现)
 * TODO : not finish
 *
 * @Author voidsun
 * @Date 2015/9/8
 * @Email voidsun@126.com
 */
public class FibonacciHeap<T extends Comparable> extends MergeableHeap<T> {
    static final double goldNum = 1.618;
    static final double logGoldNum = Math.log(goldNum);
    Map<T, Node> indexOnNode = new HashMap<>();
    Node min = null;


    class Node implements Comparable{
        T t;
        int degree = 0;
        Node parent = null;
        Node prev = null;
        Node next = null;
        Node child = null;
        boolean mark = false;
        Node(T t){
            this.t = t;
        }

        @Override
        public String toString() {
            return t+"";
        }

        @Override
        public int compareTo(Object o) {
            Node node = (Node)o;
            if(node.t == null){
                return 1;
            }
            if(this.t == null){
                return -1;
            }
            return this.t.compareTo(node.t);
        }
    }

    @Override
    void insert(T t) {
        if(indexOnNode.get(t) != null){
            throw new NodeExsistException();
        }
        Node node = new Node(t);
        indexOnNode.put(t, node);
        size ++;
        addToRoot(node);
    }

    /**
     *  连接两个节点
     */
    void link(Node prev, Node next, Node _new){
        prev.next = _new;
        _new.prev = prev;
        next.prev = _new;
        _new.next = next;
    }

    /**
     *  将一个节点加入根链表
     */
    private void addToRoot(Node node){
        if(min == null){
            min = node;
            node.next = node;
            node.prev = node;
        }else{
            link(min.prev, min, node);
            if(node.compareTo(min) < 0){
                min = node;
            }
        }
    }

    @Override
    T minimum() {
        if(min == null){
            return null;
        }
        return min.t;
    }

    @Override
    T extractMin() {
        if(min == null)return null;
        Node extractMin = min;
        Node current = min;
        do{
            min.parent = null;
            current = current.next;
        }while(current != min);
        Node minNext = min.next;
        Node minPrev = min.prev;
        if(min.child != null){
            Node child = min.child;
            Node childPrev = min.child.prev;
            minNext.next = childPrev;
            childPrev.prev = minNext;
            minPrev.prev = child;
            child.next = minPrev;
        }
        minNext.prev = min.prev;
        minPrev.next = min.next;
        if(min == min.next){
            this.min = null;
        }else{
            this.min = min.next;
            consolidate();
        }
        this.size--;
        indexOnNode.remove(extractMin.t);
        return extractMin.t;
    }

    /**
     * pop最小节点辅助操作
     * 将最小节点所有子节点加入根链表，循环合并度相同的根节点
     */
    private void consolidate(){
        Node[] tempArray = new FibonacciHeap.Node[d()+1];
        Node current = min;
        do{
            while(tempArray[current.degree] != null){
                Node _this = current;
                Node that = tempArray[current.degree];
                if(_this.compareTo(that) > 0){
                    Node temp = _this;
                    _this = that;
                    that = temp;
                }
                heapLink(_this, that);
                tempArray[current.degree] = null;
                current = _this;
            }
            tempArray[current.degree] = current;
            current = current.next;
        }while(current != min);
        min = null;
        for(Node node : tempArray){
            if(node != null)addToRoot(node);
        }
    }

    /**
     *  合并两个度相同的根节点，较小的是base
     */
    private void heapLink(Node base, Node add){
        add.prev.next = add.next;
        add.next.prev = add.prev;
        add.next = add;
        add.prev = add;
        if(base.child == null){
            base.child = add;
        }
        add.parent = base;
        link(base.child.prev, base.child, add);
        base.degree++;
        base.mark = false;
    }

    /**
     *  计算最大度数的界 斐波那契堆命名的由来
     */
    private int d(){
        return (int)Math.ceil(Math.log(size) / logGoldNum);
    }

    /**
     *  返回最小节点的引用表现
     */
    public Node min(){
        return min;
    }

    @Override
    public FibonacciHeap union(MergeableHeap anotherHeap) {
        if(!(anotherHeap instanceof FibonacciHeap)){
            throw new NotCorrectTypeException();
        }
        FibonacciHeap that = (FibonacciHeap)anotherHeap;
        if(this.min == null){
            this.min = that.min();
        }else if(that.min() != null){
            Node thisMin = this.min;
            Node thisPrev = this.min.prev;
            Node thatMin = that.min();
            Node thatPrev = that.min().prev;

            thisPrev.next = thatMin;
            thatMin.prev = thisPrev;
            thisMin.prev = thatPrev;
            thatPrev.next = thisMin;

            if(thatMin.compareTo(thisMin) < 0){
                this.min = thatMin;
            }
        }
        this.size += that.getSize();
        return this;
    }

    /**
     *  减小一个节点的值
     */
    public void fibDecreaseKey(T old, T _new){
        Node node = indexOnNode.get(old);
        if(node == null){
            throw new NodeNotExsistException();
        }
        if(old.compareTo(_new) < 0){
            throw new NewValueGTOldValueException();
        }
        indexOnNode.remove(old);
        indexOnNode.put(_new, node);
        node.t = _new;
        Node parent = node.parent;
        if(parent != null && node.compareTo(parent) < 0){
            cut(parent, node);
            cascadingCut(parent);
        }
        if(min.compareTo(node) > 0){
            min = node;
        }
    }
    void cut(Node parent, Node node){
        parent.degree--;
        if(parent.degree == 0){
            parent.child = null;
        }else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        addToRoot(node);
        node.parent = null;
        node.mark = false;
    }
    void cascadingCut(Node node){
        Node parent = node.parent;
        if(parent != null){
            if(!node.mark){
                node.mark = true;
            }else{
                cut(parent, node);
                cascadingCut(parent);
            }
        }
    }

    /**
     *  删除某一节点
     *  先把值修改成null,然后对fib堆做pop
     */
    public void fibDelete(T t){
        Node node = indexOnNode.get(t);
        if(node == null){
            return;
        }
        fibDecreaseKey(t, null);
        extractMin();
    }


    public static void main(String[] args) {

        FibonacciHeap<Integer> heap = new FibonacciHeap();
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);
        heap.insert(50);
        heap.insert(60);
        heap.insert(70);
        heap.insert(80);
        heap.insert(90);
        heap.insert(100);
        heap.fibDecreaseKey(100, 0);
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
    }
}
