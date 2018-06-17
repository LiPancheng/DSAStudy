package datastructure.stackqueue;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈排序
 * 除此之外，只能申请变量，不能申请其他数据结构
 */
public class StackSort {
    /*方案一： 自己思路*/
    public Stack<Integer> sort(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        int cur = stack.pop();
        while (!stack.empty()){
            int source = stack.pop();

            if (source >= cur){
                sortHelpStak(help, source);
            }
            else {
                help.push(cur);
                cur = source;
            }
        }
        help.push(cur);

        while (!help.empty()){
            stack.push(help.pop());
        }
        return stack;
    }

    private Stack<Integer> sortHelpStak(Stack<Integer> stack, int cur){
        if (stack.empty() || stack.peek() >= cur){
            stack.push(cur);
        }
        else {
            int tmp = stack.pop();
            sortHelpStak(stack, cur);
            stack.push(tmp);
        }
        return stack;
    }

    /*方案二： 答案*/
    public Stack<Integer> sortSackByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();

        while (!stack.empty()){
            int cur = stack.pop();
            while (!help.empty() && cur > help.peek()){
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.empty()){
            stack.push(help.pop());
        }
        return stack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(45);
        stack.push(7);
        stack.push(9);
        stack.push(23);
        stack.push(1);
        stack.push(4);
        stack.push(12);
        stack.push(8);
        Stack<Integer> sorted = new StackSort().sortSackByStack(stack);
        while (!sorted.empty()){
            System.out.print(sorted.pop() + " ");
        }
    }
}
