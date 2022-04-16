public class KdNode 
{
    private Penguin penguin;
    private KdNode left;
    private KdNode right;

    public KdNode(Penguin penguin) {
        this.penguin = penguin;
    }

    public Penguin getPenguin()
    {
        return penguin;
    }

    public KdNode getLeft() {
        return this.left;
    }

    public void setLeft(KdNode left) {
        this.left = left;
    }

    public KdNode getRight() {
        return this.right;
    }

    public void setRight(KdNode right) {
        this.right = right;
    }

}
