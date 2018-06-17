package datastructure.stackqueue;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
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
