class MyBinaryTree {
    MyNode rootnode;
    MyNode rootdatanode; // data用木

    MyBinaryTree() { // コンストラクタ
        rootnode = null;
        rootdatanode = null;
    }

    static void printTxt(String s) { // System.outを書きたくなかった
        System.out.print(s);
    }

    int compare(KeyAndData one, KeyAndData two) { // keyを比較する．比較データにnullがあるなら-1，oneKeyとtwoの比較において <：負の整数，==：0，>：正の整数
        if (one == null || two == null) {
            return -1;
        }
        return compareByKey(one.key, two);
    }

    int compareByKey(String onekey, KeyAndData two) { // keyを比較する．比較データにnullがあるなら-1，oneKeyとtwoの比較において <：負の整数，==：0，>：正の整数
        if (onekey == null || two == null || two.key == null) {
            return -1;
        }
        return onekey.compareTo(two.key);
    }

    int compareByData(String onedata, KeyAndData two ) { // dataを比較する．比較データにnullがあるなら-1，oneKeyとtwoの比較において <：負の整数，==：0，>：正の整数
        if( onedata == null || two == null || two.data == null ) {
            return -1;
        }
        return onedata.compareTo(two.data);
    }

/*
 * insertNodeメソッド
 * @param newnode 挿入するノード
 * @param node 挿入する部分木のroot(指定されていなければrootから判定する)
 */
    void insertNode(MyNode newnode) {
        insertNode(newnode, rootnode);
    }
    void insertNode(MyNode newnode, MyNode node) {
        int compareflag;
        if (newnode == null) {
            return;
        }

        if (node == null) { // 指定部分木が存在しなければrootにする 再起では呼び出されない
            rootnode = newnode;
            newnode.parent = null; // rootは親を持たない
        } else {
            compareflag = compare(newnode.keyAndData, node.keyAndData);
            if (compareflag < 0) { // newnode < node 左に行きたい
                if (node.left != null) { // nodeの左子が存在するならそっちと比較する
                    insertNode(newnode, node.left);
                } else { // nodeの左子があいていればそこに挿入
                    node.left = newnode;
                    newnode.parent = node;
                }
            } else { // newnode >= node 右に行きたい
                if (node.right != null) {
                    insertNode(newnode, node.right); // nodeの右子が存在するならそっちと比較する
                } else { // nodeの右子があいていればそこに挿入
                    node.right = newnode;
                    newnode.parent = node;
                }
            }
        }
        return;
    }

    // やってることは無印と一緒
    void insertNode2(MyNode newnode) {
        insertNode2(newnode, rootdatanode);
    }

    void insertNode2(MyNode newnode, MyNode node) {
        int compareflag;
        if( newnode == null ) {return;}
        
        if( node == null ) {
            rootdatanode = newnode; // only this line is different
            newnode.parent = null;
        } else {
            compareflag = compareByData(newnode.keyAndData.data,node.keyAndData);
            if( compareflag < 0 ) {
                if( node.left != null ) {
                    insertNode2(newnode,node.left);
                } else {
                    node.left = newnode;
                    newnode.parent = node;
                }
            } else {
                if( node.right != null ) {
                    insertNode2(newnode,node.right);
                } else {
                    node.right = newnode;
                    newnode.parent = node;
                }
            }
        }
    }

/*
 * deleteMinNodeメソッド
 * @param node nodeがrootな部分木に含まれる最小値ノードを削除
 * @return 木から切り離された最小値ノード
 */
    MyNode deleteMinNode(MyNode node) {
        if (node == null) { // nodeがnullなら判定仕様がないのでnullを返す
            return null;
        }
        if (node.left == null) { // 左子がないのならnodeが最小
            if (node.parent == null) { //nodeがrootなら右子を新しいrootにする
                rootnode = node.right;
            } else if (node.parent.left == node) { // nodeが親の左子ならnodeの右子が元場所に入る
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            if (node.right != null) { // nodeが右子をもつのなら右子に新しい親を認知させる(nodeは左子を持っていない前提)
                node.right.parent = node.parent;
            }
            node.right = null; // nodeの子を消す
            return node;
        } else { // 左子があるのなら再帰
            return deleteMinNode(node.left);
        }
    }

    MyNode deleteNodeByKey(String key) {
        return deleteNodeByKey(key, rootnode);
    }

/*
 * deleteNodeByKeyメソッド
 * @param key keyが含まれるノードを削除
 * @param node nodeがrootな部分木に含まれてkeyが含まれるノードを削除
 * @return 木から切り離された削除対象ノード
 */
    MyNode deleteNodeByKey(String key, MyNode node) {
        int compareflag;
        MyNode nd;
        if (node == null) { // nodeがnullなら判定仕様がないのでnullを返す
            return null;
        }

        compareflag = compareByKey(key, node.keyAndData);

        node.printThisNode();

        if (compareflag == 0) { // 削除対象を見つけたら木から切り離す
            if (node.right != null) { // nodeが右子を持つ場合nodeの右子がrootな部分木の最小値を元nodeの位置に入れる
                nd = deleteMinNode(node.right);

                printTxt(" (deleted:");
                nd.printThisNode();
                printTxt(" ");
                node.printThisNode();
                printTxt(" will be replaced with ");
                nd.printThisNode();
                printTxt(") ");

                if (node.parent == null) { // nodeがrootであればndを新しいrootにする
                    rootnode = nd;
                } else if (node.parent.left == node) { // nodeがその親の左子ならnodeの親の左子にndを登録する
                    node.parent.left = nd;
                } else { // nodeがその親の右子ならnodeの親の右子にndを登録する
                    node.parent.right = nd;
                }
                nd.parent = node.parent; // ndに親をわからせる
                nd.left = node.left; // ndに元nodeの子を登録する
                nd.right = node.right;
                if (node.left != null) { // ndの子に親をわからせる
                    node.left.parent = nd;
                }
                if (node.right != null) {
                    node.right.parent = nd;
                }
                return node;
            } else { // nodeが右子を持たない場合(左は持ってるかも)
                if (node.parent == null) { // 親がなければ
                    rootnode = node.left;
                } else if (node.parent.left == node) { // nodeがその親の左子の場合
                    node.parent.left = node.left; // 親 -> node -> 左子 から 親 -> 左子
                } else { // nodeがその親の右子の場合
                    node.parent.right = node.left; // 親 -> node -> 右子 から 親 -> 右子
                }
                if (node.left != null) { // nodeが左子を持つ場合は左子に親をわからせる
                    node.left.parent = node.parent;
                }
                return node;
            }
        } else if (compareflag < 0) { // key < node の場合は左再帰
            return deleteNodeByKey(key, node.left);
        } else { // key > node の場合は左再帰
            return deleteNodeByKey(key, node.right);
        }
    }

/*
 * findNodeByKeyメソッド
 * @param key keyが含まれるノードを探索
 * @param node nodeがrootな部分木に含まれてkeyが含まれるノードを探索
 * @return 見つかったノード
 */
    MyNode findNodeByKey(String key) {
        return findNodeByKey(key, rootnode);
    }

    MyNode findNodeByKey(String key, MyNode node) {
        int compareflag;
        if (node == null) {
            return null;
        }

        node.printThisNode();

        compareflag = compareByKey(key, node.keyAndData);

        if (compareflag == 0) {
            return node; // 対象が見つかればそれを返す
        } else if (compareflag < 0) { // 対象と異なれば再帰する
            return findNodeByKey(key, node.left);
        } else {
            return findNodeByKey(key, node.right);
        }
    }

/*
 * findNodeByDataメソッド
 * @param data dataが含まれるノードを探索
 * @param node nodeがrootな部分木に含まれてdataが含まれるノードを探索
 * @return 見つかったノード
 */
    MyNode findNodeByData(String data) {
        return findNodeByData(data, rootdatanode);
    }

    MyNode findNodeByData(String data, MyNode node) {
        int compareflag;
        if( node == null ){
            return null;
        }
        
        node.printThisNode();
        
        compareflag = compareByData(data,node.keyAndData);
        
        if( compareflag == 0 ) { // 対象が見つかればそれを返す
            return(node);
        } else if( compareflag < 0 ) { // 対象と異なれば再帰する
            return findNodeByData(data,node.left);
        } else {
            return findNodeByData(data,node.right);
        }
    }
}
