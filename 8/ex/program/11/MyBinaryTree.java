class MyBinaryTree {
    MyNode rootnode;

    MyBinaryTree() {
        rootnode = null;
    }

    static void printTxt(String s) {
        System.out.print(s);
    }

    int compare(KeyAndData one, KeyAndData two) {
        if (one == null || two == null) {
            return -1;
        }
        return compareByKey(one.key, two);
    }

    int compareByKey(String onekey, KeyAndData two) { // onekey:削除・比較対象
        if (onekey == null || two == null || two.key == null) {
            return -1;
        }
        return onekey.compareTo(two.key);
    }

    void insertNode(MyNode newnode) {
        insertNode(newnode, rootnode);
    }

    void insertNode(MyNode newnode, MyNode node) {
        int compareflag;
        if (newnode == null) {
            return;
        }

        if (node == null) {
            rootnode = newnode;
            newnode.parent = null;
        } else {
            compareflag = compare(newnode.keyAndData, node.keyAndData);
            if (compareflag < 0) {
                if (node.left != null) { // 葉を見つけるまで再帰
                    insertNode(newnode, node.left);
                } else {
                    node.left = newnode;
                    newnode.parent = node;
                }
            } else {
                if (node.right != null) {
                    insertNode(newnode, node.right);
                } else {
                    node.right = newnode;
                    newnode.parent = node;
                }
            }
        }
        return;
    }

    MyNode deleteMinNode(MyNode node) {
        if (node == null) {
            return (null);
        }
        if (node.left == null) {
            if (node.parent == null) {
                rootnode = node.right;
            } else if (node.parent.left == node) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            if (node.right != null) {
                node.right.parent = node.parent;
            }
            node.right = null;
            return (node);
        } else {
            return (deleteMinNode(node.left));
        }
    }

    MyNode deleteNodeByKey(String key) {
        return deleteNodeByKey(key, rootnode);
    }

    MyNode deleteNodeByKey(String key, MyNode node) {
        int compareflag;
        MyNode nd;
        if (node == null) {
            return null;
        }

        compareflag = compareByKey(key, node.keyAndData);

        node.printThisNode();

        if (compareflag == 0) {
            if (node.right != null) {
                nd = deleteMinNode(node.right);

                printTxt(" (deleted:");
                nd.printThisNode();
                printTxt(" ");
                node.printThisNode();
                printTxt(" will be replaced with ");
                nd.printThisNode();
                printTxt(") ");

                if (node.parent == null) {
                    rootnode = nd;
                } else if (node.parent.left == node) {
                    node.parent.left = nd;
                } else {
                    node.parent.right = nd;
                }
                nd.parent = node.parent;
                nd.left = node.left;
                nd.right = node.right;
                if (node.left != null) {
                    node.left.parent = nd;
                }
                if (node.right != null) {
                    node.right.parent = nd;
                }
                return (node);
            } else {
                if (node.parent == null) {
                    rootnode = node.left;
                } else if (node.parent.left == node) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
                if (node.left != null) {
                    node.left.parent = node.parent;
                }
                return (node);
            }
        } else if (compareflag < 0) {
            return (deleteNodeByKey(key, node.left));
        } else {
            return (deleteNodeByKey(key, node.right));
        }
    }

    MyNode findNodeByKey(String key) {
        return findNodeByKey(key, rootnode);
    }

    MyNode findNodeByKey(String key, MyNode node) {
        int compareflag;
        if (node == null) {
            return (null);
        }

        node.printThisNode();

        compareflag = compareByKey(key, node.keyAndData);

        if (compareflag == 0) {
            return (node);
        } else if (compareflag < 0) {
            return (findNodeByKey(key, node.left));
        } else {
            return (findNodeByKey(key, node.right));
        }
    }
}