class MyTreeNode {
    MyTreeNode left;
    MyTreeNode right;
    MyToken token;

    public void preOrderPrint() {
        preOrderPrint(this);
    }
    public void inOrderPrint() {
        inOrderPrint(this);
    }
    public void postOrderPrint() {
        postOrderPrint(this);
    }

    public void preOrderPrint(MyTreeNode node) {
        if (node.token.type == MyToken.NUMBER) {
            System.out.print("" + node.token.value);
        } else if (node.token.type == MyToken.OP) {
            System.out.print("(");
            System.out.print("" + (char)node.token.value); // 演算子
            System.out.print(" ");
            preOrderPrint(node.left);
            System.out.print(" ");
            preOrderPrint(node.right);
            System.out.print(")");
        }
    }

    public void inOrderPrint(MyTreeNode node) {
        if (node.token.type == MyToken.NUMBER) {
            System.out.print("" + node.token.value);
        } else if (node.token.type == MyToken.OP) {
            System.out.print("(");
            inOrderPrint(node.left);
            System.out.print(" ");
            System.out.print("" + (char)node.token.value);
            System.out.print(" ");
            inOrderPrint(node.right);
            System.out.print(")");
        }
    }

    public void postOrderPrint(MyTreeNode node) {
        if (node.token.type == MyToken.NUMBER) {
            System.out.print("" + node.token.value);
        } else if (node.token.type == MyToken.OP) {
            postOrderPrint(node.left);
            System.out.print(" ");
            postOrderPrint(node.right);
            System.out.print(" ");
            System.out.print("" + (char)node.token.value);
        }
    }
}
