package datastructure;

public class BiLNode {
    private int value;
    private BiLNode pre;
    private BiLNode next;

    public BiLNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BiLNode pre() {
        return pre;
    }

    public void setPre(BiLNode pre) {
        this.pre = pre;
    }

    public BiLNode next() {
        return next;
    }

    public void setNext(BiLNode next) {
        this.next = next;
    }
}
