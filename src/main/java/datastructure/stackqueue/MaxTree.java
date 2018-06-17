package datastructure.stackqueue;

import java.util.HashMap;
import java.util.Stack;

public class MaxTree {
    class Node{
        int value;
        Node cleft;
        Node cRight;

        public Node(int value){
            this.value = value;
        }
    }

    public Node getMaxTree(int[] arr){
        //初始化构造结点数组
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }

        Stack<Node> stack = new Stack<>(); //快速找到当前值的左（右）侧第一个较大值
        HashMap<Node, Node> leftBigMap = new HashMap<>();
        HashMap<Node, Node> rightBigMap = new HashMap<>();

        //利用Stack和Map记录每个值的左侧第一个较大值
        for (int i = 0; i < arr.length; i++) {
            Node curNode = nodes[i];
            while ( !stack.empty() && stack.peek().value < curNode.value){
                popStackAndSetMap(stack, leftBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.empty()){ //将最后一个值记录，同时也清空了栈
            popStackAndSetMap(stack, leftBigMap);
        }

        //利用Stack和Map记录每个值的右侧第一个较大值
        for (int i = arr.length - 1; i >= 0; i--) {
            Node curNode = nodes[i];
            while ( !stack.empty() && stack.peek().value < curNode.value){
                popStackAndSetMap(stack, rightBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.empty()){
            popStackAndSetMap(stack, rightBigMap);
        }

        //开始链接结点
        Node head = null;
        for (Node curNode : nodes) {
            Node leftFirstBig = leftBigMap.get(curNode);
            Node rightFirstBig = rightBigMap.get(curNode);
            if (leftFirstBig == null && rightFirstBig == null){
                head = curNode;
            }
            else if (leftFirstBig == null){
                if (rightFirstBig.cleft == null){
                    rightFirstBig.cleft = curNode;
                }
                else {
                    rightFirstBig.cRight = curNode;
                }
            }
            else if (rightFirstBig == null){
                if (leftFirstBig.cRight == null){
                    leftFirstBig.cRight  = curNode;
                }
                else {
                    leftFirstBig.cleft = curNode;
                }
            }
            else {
                Node parent = leftFirstBig.value < rightFirstBig.value ? leftFirstBig : rightFirstBig;
                if (parent.cleft == null){
                    parent.cleft = curNode;
                }
                else {
                    parent.cRight = curNode;
                }
            }
        }

        return head;
    }

    public void popStackAndSetMap(Stack<Node> stack, HashMap<Node, Node> map){
        Node curNode = stack.pop();
        map.put(curNode, stack.empty() ? null : stack.peek());
    }
}
