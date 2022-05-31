/*
 * 二分木
 */
class BinaryTreeNode
{
    String label;               // この節のラベル
    BinaryTreeNode   left;      // 左部分木
    BinaryTreeNode   right;     // 右部分木

    /**
     * 二分木の節を生成する
     *
     * @param label  ラベル
     * @param left   左部分木
     * @param right  右部分木
     */
    BinaryTreeNode(String label, BinaryTreeNode left, BinaryTreeNode right)
    {
        this.left  = left;
        this.right = right;
        this.label = label;
    }

    /**
     * 二分木を行きがけ順でなぞる
     *
     * @param p  なぞるべき二分木
     */
    static void traversePreorder(BinaryTreeNode p)
    {
        if (p == null)  // 木が空なら何もしない
            return;
        System.out.println("節" + p.label + "に立ち寄りました。");
        traversePreorder(p.left);
        traversePreorder(p.right);
    }

    /**
     * 二分木を通りがけ順でなぞる
     *
     * @param p  なぞるべき二分木
     */
    static void traverseInorder(BinaryTreeNode p)
    {
        if (p == null)  // 木が空なら何もしない
            return;
        traverseInorder(p.left);
        System.out.println("節" + p.label + "に立ち寄りました。");
        traverseInorder(p.right);
    }

    /**
     * 二分木を帰りがけ順でなぞる
     *
     * @param p  なぞるべき二分木
     */
    static void traversePostorder(BinaryTreeNode p)
    {
        if (p == null)  // 木が空なら何もしない
            return;
        traversePostorder(p.left);
        traversePostorder(p.right);
        System.out.println("節" + p.label + "に立ち寄りました。");
    }

    /**
     * テスト用のメインルーチン
     *
     * Fig.6.7の二分木を作り出してから，それを行きがけ順，通りがけ順，
     * 帰りがけ順でなぞる
     */
    public static void main(String args[])
    {
        // Fig.6.7の二分木を作る
        BinaryTreeNode tree =
            new BinaryTreeNode(
                    "a",
                    new BinaryTreeNode("b",
                                       new BinaryTreeNode("c", null, null),
                                       null),
                    new BinaryTreeNode("d", null, null));

        // 行きがけ順になぞる
        System.out.println("行きがけ順");
        traversePreorder(tree);

        // 通りがけ順になぞる
        System.out.println("通りがけ順");
        traverseInorder(tree);

        // 帰りがけ順になぞる
        System.out.println("帰りがけ順");
        traversePostorder(tree);
    }
}
