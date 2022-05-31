import java.util.ArrayList;

class MyBinaryTree {
    MyNode rootnode;
    MyNode currentnode;
    ArrayList<MyNode> findrangebuf = new ArrayList<MyNode>();

    MyBinaryTree() {
        currentnode = null;
        rootnode = null;
    }

    static void printTxt(String s) {
        System.out.print(s);
    }

    void resetCurrentnode() {
        currentnode = rootnode;
    }

    int compare(KeyAndData one, KeyAndData two) {
        if (one == null || two == null) {
            return -1;
        }
        return compareByKey(one.key, two);
    }

    int compareByKey(String onekey, KeyAndData two) {
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
                if (node.left != null) {
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

    MyNode deleteMinNode() {
        return deleteMinNode(rootnode);
    }

    MyNode deleteMinNode(MyNode node) {
        if (node == null) {
            return (null);
        }
        if (node.left == null) {
            if (node.parent == null) {
                rootnode = node.right; // 自分がrootのときは右の子をrootにする
            } else if (node.parent.left == node) { // nodeが親の左の子
                node.parent.left = node.right; // 親の左の子を自身の右の子にする (親→左(自分)→右 より親よりも右の子の方が小さいことが保証される)

            } else { // nodeが親の右の子
                node.parent.right = node.right;  // 親の右の子を自身の右の子にする
            }
            if (node.right != null) { // 右に子をもつならその親を自身の親にする
                node.right.parent = node.parent;
            }
            node.right = null; // もともとleftがnullという前提
            return (node);
        } else {
            return (deleteMinNode(node.left));
        }
    }

    MyNode deleteMaxNode() {
        return deleteMaxNode(rootnode);
    }

    MyNode deleteMaxNode(MyNode node) {
        if (node == null) {
            return (null);
        }
        if (node.right == null) {
            if (node.parent == null) {
                rootnode = node.left; // 自分がrootのときは左の子をrootにする
            } else if (node.parent.right == node) { // nodeが親の右の子
                node.parent.right = node.left; // 親の左の子を自身の右の子にする (親→右(自分)→左 より親よりも左の子の方が大きいことが保証される)
            } else { // nodeが親の左の子
                node.left.parent = node.parent;  // 親の左の子を自身の右の子にする
            }
            if (node.left != null) { // 左に子をもつならその親を自身の親にする
                node.left.parent = node.parent;
            }
            node.left = null;
            return (node);
        } else {
            return (deleteMaxNode(node.right));
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

        compareflag = compareByKey(key, node.keyAndData); // 0:引数と同値 1:引数より大きい -1:引数より小さい

        node.printThisNode();

        if (compareflag == 0) { // 目的のノードを発見した場合
            if (node.right != null) {
                nd = deleteMinNode(node.right); // nodeの右の部分木の中で最小値ノードを取得・削除

                printTxt(" (deleted:");
                nd.printThisNode();
                printTxt(" ");
                node.printThisNode();
                printTxt(" will be replaced with ");
                nd.printThisNode();
                printTxt(") ");

                if (node.parent == null) { // nodeがrootのとき
                    rootnode = nd;
                } else if (node.parent.left == node) { // nodeが親の左にあるとき
                    node.parent.left = nd; // nodeを最小値ノードで置き換える
                } else { // nodeが親の右側にあるとき
                    node.parent.right = nd;
                }

                nd.parent = node.parent; // ここから3行でnode位置にndを入れる
                nd.left = node.left;
                nd.right = node.right;
                if (node.left != null) {
                    node.left.parent = nd; // 元nodeの子たちに親をわからせる
                }
                if (node.right != null) {
                    node.right.parent = nd;
                }
                return (node);
            } else { // こっちはnode.rightがnullなのでndが利用できない nodeが右に子を持たない場合
                if (node.parent == null) { // nodeがrootならnode.leftをrootに格上げする(rightはnull)
                    rootnode = node.left;
                } else if (node.parent.left == node) { // nodeが親の左側にあるとき 親->左子:node(右はnull)->孫たち(2つかも) nodeだけを抜き取る
                } else { // nodeが親の右側にあるとき
                    node.parent.right = node.left; // 親の左側にnodeの左の子を割り当てる
                }

                if (node.left != null) { // nodeが左に子を持つ場合
                    node.left.parent = node.parent; // 唯一の子に親をわからせる
                }
                return (node);
            }
        } else if (compareflag < 0) { // nodeが目的のノードよりも小さい場合は左へ
            return (deleteNodeByKey(key, node.left));
        } else { // nodeが目的のノードよりも小さい場合は右へ
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

        node.printThisNode(); // keyを探索するときに通るノードを標準出力する

        compareflag = compareByKey(key, node.keyAndData);

        if (compareflag == 0) {
            return (node);
        } else if (compareflag < 0) { // nodeがkeyよりも小さかった場合
            return (findNodeByKey(key, node.left)); // nodeを更に小さくする
        } else { // nodeがkey以上だった場合
            return (findNodeByKey(key, node.right));
        }
    }

    void findNodeByRange(String key0, String key1) {
        findNodeByRange(key0, key1, rootnode);
    }

    void findNodeByRange(String key0, String key1, MyNode node) {
        int compareflag0, compareflag1;
        if (node == null) {
            return;
        }

        node.printThisNode();

        compareflag0 = compareByKey(key0, node.keyAndData);
        compareflag1 = compareByKey(key1, node.keyAndData);

        if (compareflag0 < 0 && node.left != null) { // 範囲より右側なら左のnodeに移動
            findNodeByRange(key0, key1, node.left);
            node.printThisNode();
        }

        if (compareflag0 <= 0 && compareflag1 >= 0) { // 範囲内ならbufに追加
            addNodeToBuf(node);
        }

        if (compareflag1 > 0 && node.right != null) { // 範囲より左側なら右のnodeに移動
            findNodeByRange(key0, key1, node.right);
            node.printThisNode();
        }
    }

    void printAllInBuf() {
        int i;
        printTxt("\n |Found:");
        for (i = 0; i < findrangebuf.size(); i++) {
            MyNode nd = findrangebuf.get(i);
            nd.printThisNode();
        }
        printTxt("\n");
    }

    void addNodeToBuf(MyNode node) {
        findrangebuf.add(node);
    }

    void initNodeBuf() {
        findrangebuf.clear();
    }

    boolean moveParent() {
        if (currentnode != null && currentnode.parent != null) {
            currentnode = currentnode.parent;
            return true;
        } else {
            return false;
        }
    }

    boolean moveLeft() {
        if (currentnode != null && currentnode.left != null) {
            currentnode = currentnode.left;
            return true;
        } else {
            return false;
        }
    }

    boolean moveRight() {
        if (currentnode != null && currentnode.right != null) {
            currentnode = currentnode.right;
            return true;
        } else {
            return false;
        }
    }
}
