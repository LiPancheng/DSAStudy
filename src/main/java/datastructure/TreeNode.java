package datastructure;

public class TreeNode {
    int value;
    TreeNode cleft;
    TreeNode cRight;

    public TreeNode(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getCleft() {
        return cleft;
    }

    public void setCleft(TreeNode cleft) {
        this.cleft = cleft;
    }

    public TreeNode getcRight() {
        return cRight;
    }

    public void setcRight(TreeNode cRight) {
        this.cRight = cRight;
    }
}
