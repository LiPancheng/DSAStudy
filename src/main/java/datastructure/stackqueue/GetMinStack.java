package datastructure.stackqueue;

import java.util.Stack;

/**
 * 一种特殊的栈，除了栈的基本操作，还能返回栈中最小的元素
 */
public class GetMinStack<E extends Comparable> {
    private Stack<E> dataStack;
    private Stack<E> minStack;

    public GetMinStack(){
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(E item){
        dataStack.push(item);
        if (minStack.empty() || item.compareTo(minStack.peek()) <= 0){
            minStack.push(item);
        }
    }

    public E pop(){
        /*if (dataStack.empty()){
            throw new RuntimeException("The Stack is Empty");
        }*/
        E v = dataStack.pop();
        if (v.compareTo(getMin()) == 0){
            minStack.pop();
        }
        return v;
    }

    public E getMin(){
        /*if (minStack.empty()){
            throw new RuntimeException("The Stack is Empty");
        }*/
        return minStack.peek();
    }

    public static void main(String[] args) {
        GetMinStack<Integer> stack = new GetMinStack<>();
        stack.push(3);
        stack.push(4);
        stack.push(1);
        stack.push(5);
        stack.push(2);
        stack.push(1);

        for (int i = 0; i < 6; i++) {
            System.out.println("Min: " + stack.getMin() + ", pop: " + stack.pop());
        }
    }
}
