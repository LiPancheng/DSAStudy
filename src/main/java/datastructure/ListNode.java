package datastructure;

public class ListNode {
    private int value;
    private ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode next() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
