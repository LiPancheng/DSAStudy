package datastructure.stackqueue;

import java.util.Stack;

/**
 * 对栈中元素逆序。只能使用递归操作，不能使用其他数据结构
 */
public class ReverseStack{

    public int getAndRemoveLast(Stack<Integer> stack){
        int top = stack.pop();
        if (stack.empty()){
            return top;
        }
        else{
            int last = getAndRemoveLast(stack);
            stack.push(top);
            return last;
        }
    }

    public void reverse(Stack<Integer> stack){
        if (stack.empty()){
            return;
        }
        int i = getAndRemoveLast(stack);
        reverse(stack);
        stack.push(i);
    }
}
