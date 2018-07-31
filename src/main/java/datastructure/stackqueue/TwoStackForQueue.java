package datastructure.stackqueue;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
 *
 * 假定两个栈分别是inbox和outbox。

 对于Enqueue操作，都是对inbox进行push操作。
 对于Dequeue操作，如果outbox栈为空，则将inbox的内容全部倒入outbox，然后弹出。如果outbox栈不为空，则直接对outbox弹出。

 这个算法的效率在大多数情况下表现良好，Enqueue永远是O(1)，Dequeue在大多数情况下表现良好。
 */
public class TwoStackForQueue<E> {
    private Stack<E> pushStack;
    private Stack<E> popStack;

    public TwoStackForQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void add(E item){
        pushStack.push(item);
    }

    public E poll(){
        if (popStack.empty()){
            if (pushStack.empty()){
                throw new RuntimeException("The Stack is Empty");
            }
            else {
                while ( !pushStack.empty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }
        return popStack.pop();
    }

    public E peek(){
        if (popStack.empty()){
            if (pushStack.empty()){
                throw new RuntimeException("The Stack is Empty");
            }
            else {
                while ( !pushStack.empty()){
                    popStack.push(pushStack.pop());
                }
            }
        }
        return popStack.peek();
    }
}
