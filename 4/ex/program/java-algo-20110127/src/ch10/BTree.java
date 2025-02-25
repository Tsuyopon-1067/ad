/*
 * B木
 */
import java.io.*;

public class BTree
{
    /**
     * B木の節
     */
    private abstract class Node
    {
        int  serial;            // シリアル番号（B木の処理には不要だが，
                                // toStringによる表示を見やすくするため）

        // Nodeクラスは抽象クラスである。
        // 実際のインスタンスは，サブクラスであるInternalNodeクラス（内部節），
        // またはLeafクラス（葉）として生成する
    }

    /**
     * B木の内部節
     */
    private class InternalNode extends Node
    {
        int           nChilds;  // この節がもっている子の数
        Node[]        child;    // 部分木
        Integer[]     low;      // 各部分木の最小の要素

        /**
         * コンスラクタ：空の内部節を生成する
         */
        private InternalNode()
        {
            serial = serialNumber++;    // シリアル番号を付ける
            nChilds = 0;
            child = new Node[MAX_CHILD];
            low   = new Integer[MAX_CHILD];
        }

        /**
         * キーkeyをもつデータは何番目の部分木に入るかを調べる
         *
         * @param key  調べるべきキー
         * @return     キーkeyが何番目の部分木に入るかを返す
         */
        private int  locateSubtree(Integer key)
        {
            for (int  i = nChilds - 1; i > 0; i--) {
                if (key.compareTo(low[i]) >= 0) {
                    return i;
                }
            }
            return 0;
        }
    }

    /**
     * B木の葉
     */
    private class Leaf extends Node
    {
        Integer     key;        // 葉がもっているキーの値
        Object      data;       // 葉に格納するデータ

        /**
         * コンスラクタ：葉を生成する
         *
         * @param  key  この葉がもつキー
         * @param  data この葉がもつデータ
         */
        private Leaf(Integer key, Object data)
        {
            serial = serialNumber++;    // シリアル番号を付ける
            this.key  = key;
            this.data = data;
        }
    }

    private Node  root;                 // B木の根
    private int   serialNumber = 0;     // Nodeに付番するシリアル番号

    // searchメソッドは探索に成功すると，見つけた葉をcurrentLeafフィー
    // ルドにセットする。見つけた葉のdataフィールドの値を得るには
    // getDataメソッドを，dataフィールドに値をセットするにはsetDataメ
    // ソッドを使う。
    // deleteメソッドとinsertメソッドを呼び出すと，この変数はクリアさ
    // れる。
    private Leaf currentLeaf;

    final private static int  MAX_CHILD = 5;    // 五分木
    final private static int  HALF_CHILD = ((MAX_CHILD+1)/2);

    /**
     * コンスラクタ：空のB木を生成する
     */
    public BTree()
    {
        root = null;
    }

    /**
     * B木からキーkeyを探索する。
     * キーkeyをもつ葉が見つかれば，それをcurrentLeafフィールドにセットする
     * 
     * このメソッドは探索の成否を示す情報だけを返す。
     * 実際にキーkeyに対応する値を得るには，searchに成功した後で
     * getDataメソッドを呼び出すこと。また，setDataメソッドを呼び出せば，
     * キーkeyに対応する値を変えることができる
     *
     * @param key  探索すべきキー
     * @return  キーkeyをもつ葉が見つかればtrue，見つからなければfalseを返す
     */
    public boolean search(Integer key)
    {
        currentLeaf = null;     // currentLeafフィールドをnullにする

        // 空の木であれば，即座にfalseを返す
        if (root == null) {
            return false;
        } else {
            // 根から始めて，葉にたどりつくまで内部節をたどる
            Node p = root;
            while (p instanceof InternalNode) {
                InternalNode node = (InternalNode)p;
                p = node.child[node.locateSubtree(key)];
            }

            // 与えられたキーと，葉にセットされているキーを比較する
            Leaf leaf = (Leaf)p;
            if (key.compareTo(leaf.key) == 0) {
                // 探索に成功した。この葉をcurrentLeafフィールドにセットして，
                // trueを返す
                currentLeaf = leaf;
                return true;
            } else {
                return false;           // 探索に失敗のでfalseを返す
            }
        }
    }

    /**
     * 最後に成功したsearchメソッドが見つけた要素のデータを得る
     *
     * @return  直前にsearchされた要素のデータ（dataフィールド）。
     *          直前にsearch以外（insert，delete）が実行されていた場合，
     *          および直前のsearchが失敗した場合には，nullを返す
     */
    public Object getData()
    {
        if (currentLeaf == null) {
            return null;
        } else {
            return currentLeaf.data;
        }
    }

    /**
     * 最後に成功したsearchメソッドが見つけた要素がもつデータをセットする
     *
     * @param  data  セットすべき値
     * @return  セットに成功したらtrue，直前にsearch以外（insert，delete）が
     *          実行されていた場合，および直前のsearchが失敗した場合
     *          にはfalseを返す
     */
    public boolean setData(Object data)
    {
        if (currentLeaf == null) {
            return false;
        } else {
            currentLeaf.data = data;
            return true;
        }
    }

    /**
     * InsertAuxメソッドの結果
     */
    private static class InsertAuxResult
    {
        Node       newNode;     // 新しい節を作った場合に，その節が入る。
        Integer    lowest;      // 新しい節を作った場合に，newNodeが指す部分木
                                // の最小要素が入る

        private InsertAuxResult(Node newNode, Integer lowest)
        {
            this.newNode = newNode;
            this.lowest  = lowest;
        }
    }

    /**
     * 指定した節に対して，キーkeyをもつ要素を挿入する（insertの下請け）
     *
     * @param pnode   内部節pnodeのnth番目の子に対して挿入を行う。
     *                pnodeがnullの場合は根が対象となる
     * @param nth     内部節pnodeのnth番目の子に対して挿入を行う
     * @param key     挿入する要素のキー
     * @param data    挿入する要素のデータ
     *
     * @return  結果を表すInsertAuxResult型のオブジェクト。
     *          キーkeyがすでに登録済みならnull
     */
    private InsertAuxResult insertAux(InternalNode pnode, int nth,
                                      Integer key, Object data)
    {
        // 要素の挿入の対象となる節へのリンクを変数thisNodeに入れる
        Node thisNode;
        if (pnode == null) {
            thisNode = root;
        } else {
            thisNode = pnode.child[nth];
        }

        if (thisNode instanceof Leaf) {
        // この節は葉である

            // これ以降，この節を葉leafとして参照する
            Leaf leaf = (Leaf)thisNode;

            // すでに登録済みであれば，何もしないでnullを返す
            if (leaf.key.compareTo(key) == 0) {
                return null;
            } else {
              // 新たに葉newLeafを割り当てる
                Leaf newLeaf = new Leaf(key, data);

                // もし，割り当てた葉newLeafのほうが葉leafよりも小さいなら，
                // newLeafとleafの位置を入れ換える
                if (key.compareTo(leaf.key) < 0) {
                    // 元の節には，新しく割り当てた葉newLeafを入れる
                    if (pnode == null) {
                        root = newLeaf;
                    } else {
                        pnode.child[nth] = newLeaf;
                    }

                    // 新たに割り当てた葉として，leafを報告する
                    return new InsertAuxResult(leaf, leaf.key);
                } else {
                    // 新たに割り当てた葉として，newLeafを報告する
                    return new InsertAuxResult(newLeaf, key);
                }
            }
        } else {
        // この節は内部節である
            // これ以降，この節を内部節nodeとして参照する
            InternalNode node = (InternalNode)thisNode;

            // 何番目の部分木に挿入するかを決める
            int pos = node.locateSubtree(key);

            // 部分木に対して，自分自身を再帰呼び出しする
            InsertAuxResult  result = insertAux(node, pos, key, data);

          // もし分割が行われていなければ，そのまま戻る
            if (result == null || result.newNode == null) {
                return result;
            }

          // 分割が行われていたので，節nodeにそれ（result.newNode）を挿入する
            // 節nodeに追加の余地があるか？
            if (node.nChilds < MAX_CHILD) {
              // 追加の余地があったので，適切な位置に挿入する
                for (int i = node.nChilds - 1; i > pos; i--) {
                    node.child[i+1] = node.child[i];
                    node.low  [i+1] = node.low  [i];
                }
                node.child[pos+1] = result.newNode;
                node.low  [pos+1] = result.lowest;
                node.nChilds++;
                return new InsertAuxResult(null, null);
            } else {
              // 追加の余地がないので，節nodeを2つに分割しなければならない
                // 新しい内部節newNodeを割り当てる
                InternalNode newNode = new InternalNode();

                // 節result.newNodeがどちらの節に挿入されるかで，場合分けする
                if (pos < HALF_CHILD - 1) {
                  // 節result.newNodeは，節nodeの側に挿入される
                    // まず，HALF_CHILD-1〜MAX_CHILD-1番目の部分木を，
                    // 節nodeから節newNodeへと移す
                    for (int i = HALF_CHILD-1, j = 0; i < MAX_CHILD; i++, j++) {
                        newNode.child[j] = node.child[i];
                        newNode.low  [j] = node.low  [i];
                    }
                    // 0〜HALF_CHILD-2番目の部分木の間の適切な位置に，
                    // 節result.newNodeを挿入する
                    for (int i = HALF_CHILD-2; i > pos; i--) {
                        node.child[i+1] = node.child[i];
                        node.low  [i+1] = node.low  [i];
                    }
                    node.child[pos+1] = result.newNode;
                    node.low  [pos+1] = result.lowest;
                } else {
                  // 節result.newNodeは節newNodeの側に挿入される
                    // HALF_CHILD〜MAX_CHILD-1番目の部分木を，節newNodeに
                    // 移動する。同時に，節result.newNodeを適切な位置に
                    // 挿入する
                    int j = MAX_CHILD - HALF_CHILD;
                    for (int i = MAX_CHILD-1; i >= HALF_CHILD; i--) {
                        if (i == pos) {
                            newNode.child[j]   = result.newNode;
                            newNode.low  [j--] = result.lowest;
                        }
                        newNode.child[j]   = node.child[i];
                        newNode.low  [j--] = node.low  [i];
                    }
                    if (pos < HALF_CHILD) {
                        newNode.child[0] = result.newNode;
                        newNode.low  [0] = result.lowest;
                    }
                }
                // 子の数nChildを更新する
                node   .nChilds = HALF_CHILD;
                newNode.nChilds = (MAX_CHILD + 1) - HALF_CHILD;

                // 分割して作られた節をフィールドnewNodeに，
                // またその最小値をlowestフィールドに返す
                return new InsertAuxResult(newNode, newNode.low[0]);
            }
        }
    }

    /**
     * B木に要素を挿入する
     *
     * @param key  挿入する要素のキー
     * @param data 挿入する要素のデータ
     * @return  要素の挿入に成功したらtrue，すでにキーkeyをもつ要素が
     *          登録されていたら，何もしないでfalseを返す
     */
    public boolean insert(Integer key, Object data)
    {
        // currentLeafフィールドをnullにする
        currentLeaf = null;

        // 木が空の場合には，葉を作ってtrueを返す
        if (root == null) {
            root = new Leaf(key, data);
            return true;
        } else {
            // 木が空でない場合には，insertAuxメソッドを呼び出して，
            // 要素の挿入を行う
            InsertAuxResult result = insertAux(null, -1, key, data);

            // もし結果がnullなら，すでにキーkeyは登録されているので，
            // そのままfalseを返す
            if (result == null) {
                return false;
            }

            // もし分割が行われたなら，木の高さを1段高くする
            if (result.newNode != null) {
                InternalNode newNode = new InternalNode();
                newNode.nChilds  = 2;
                newNode.child[0] = root;
                newNode.child[1] = result.newNode;
                newNode.low[1]   = result.lowest;
                root = newNode;
            }
            return true;
        }
    }

    /**
     * 内部節pのx番目とx+1番目の部分木を再編成する。
     * もし，併合が必要なら，すべての要素をx番目の部分木に集めて
     * trueを返す。併合が不要ならfalseを返す
     *
     * @param p   内部節p
     * @param x   内部節pのx番目とx+1番目の部分木を再編成する
     * @return    併合が必要ならtrue，必要でなければfalse
     */
    private static boolean mergeNodes(InternalNode p, int x)
    {
        InternalNode  a = (InternalNode)p.child[x];    // x番目の部分木
        InternalNode  b = (InternalNode)p.child[x+1];  // x+1番目の部分木
        b.low[0] = p.low[x+1];

        int   an = a.nChilds;                          // 部分木aの子の数
        int   bn = b.nChilds;                          // 部分木bの子の数

        if (an + bn <= MAX_CHILD) {
          // 部分木aとbを併合しなければならない
            // bの子をすべてaへ移動する
            for (int i = 0; i < bn; i++) {
                a.child[i+an] = b.child[i];
                b.child[i]    = null;           // 不要な参照を消す
                a.low  [i+an] = b.low  [i];
            }
            a.nChilds += bn;                    // 子の数を更新する
            // ### ここでbを解放する ###
            return true;                        // 併合したことを通知する
        } else {
          // 部分木aとbとで，節を再分配する
            int  move;                          // 移動する要素の個数

            // 部分木aに分配すべき子の数を求める
            int n = (an + bn) / 2;
            if (an > n) {
              // 部分木aから部分木bへと移動する
                move = an - n;                  // move個の子をaからbへ移す
                // bの要素を右にずらす
                for (int i = bn - 1; i >= 0; i--) {
                    b.child[i+move] = b.child[i];
                    b.low  [i+move] = b.low  [i];
                }
                // aからbへmove個の子を移動する
                for (int i = 0; i < move; i++) {
                    b.child[i] = a.child[i+n];
                    a.child[i+n] = null;        // 不要な参照を消す
                    b.low  [i] = a.low  [i+n];
                }
            } else {
              // 部分木bから部分木aへと移動する
                move = n - an;                  // move個の子をbからaへ移す
                // bからaへmove個の子を移動する
                for (int i = 0; i < move; i++) {
                    a.child[i+an] = b.child[i];
                    a.low  [i+an] = b.low  [i];
                }
                // bの要素を左へ詰め合わせる
                for (int i = 0; i < bn - move; i++) {
                    b.child[i] = b.child[i+move];
                    b.child[i+move] = null;     // 不要な参照を消す
                    b.low  [i] = b.low  [i+move];
                }
            }
            // 子の個数を更新する
            a.nChilds = n;
            b.nChilds = an + bn - n;
            // 部分木bの最小値を節pにセットする
            p.low[x+1] = b.low[0];
            return false;
        }
    }

    // deleteAuxメソッドの戻り値
    //    値の意味は，deleteAuxメソッドのコメントを参照のこと。
    private enum Status {
        OK,
        OK_REMOVED,
        OK_NEED_REORG,
        NOT_FOUND
    }

    /**
     * 節thisNodeから，キーkeyをもつ要素を削除する（deleteの下請け）
     *
     * @param thisNode この節（またはその部分木）から要素を削除する
     * @param key  削除する要素のキー
     * @return  以下の値を返す。
     *             OK:           削除に成功。thisNodeには何の変化もない
     *             OK_REMOVED:   削除に成功。thisNodeそのものが削除された
     *             OK_NEED_REORG:削除に成功。thisNodeの子が少なく（HALF_CHILD
     *                          以下）なったので，再編成が必要になった
     *             NOT_FOUND:   削除に失敗。キーkeyをもつ子は見つからなかった
     */
    private static Status deleteAux(Node thisNode, Integer key)
    {
        if (thisNode instanceof Leaf) {
        // この節は葉である
            // これ以降，この節を葉leafとして参照する
            Leaf leaf = (Leaf)thisNode;

            // この葉のキーとkeyが等しければ，削除する
            if (leaf.key.compareTo(key) == 0) {
                // ### ここでleafを解放する ###
                return Status.OK_REMOVED;
            } else {
                // キーが一致しない。つまり，与えられたキーをもつ要素は
                // 存在しなかった
                return Status.NOT_FOUND;
            }
        } else {
        // この節は内部節である
            // これ以降，この節を内部節nodeとして参照する
            InternalNode node = (InternalNode)thisNode;

            boolean joined = false;// 再編成の結果，部分木が併合されたか？

            // どの部分木から削除するかを決める
            int pos = node.locateSubtree(key);
            // その部分木に対して，自分自身を再帰呼び出しする
            Status result = deleteAux(node.child[pos], key);
            // 部分木に何の変化もなければ，そのまま戻る
            if (result == Status.NOT_FOUND || result == Status.OK) {
                return result;
            }

          // 部分木posを再編成する必要があるか？
            if (result == Status.OK_NEED_REORG) {
                int sub = (pos == 0) ? 0 : pos - 1;
                // 部分木subとsub+1を再編成する
                joined = mergeNodes(node, sub);
                // もし，subとsub+1が併合されていたら，部分木sub+1を
                // nodeから削除する必要がある
                if (joined) {
                    pos = sub + 1;
                }
            }

            Status myResult = Status.OK;  // このメソッドが返す戻り値。
                                          // とりあえずOKにしておく
          // 部分木posが削除された
            if (result == Status.OK_REMOVED || joined) {
                // nodeの部分木を詰め合わせる
                for (int i = pos; i < node.nChilds - 1; i++) {
                    node.child[i] = node.child[i+1];
                    node.low[i]   = node.low[i+1];
                }
                node.child[node.nChilds-1] = null;      // 不要な参照を消す
                // もし，nodeの部分木の数のHALF_CHILDより小さいなら
                // 再編成が必要である
                if (--node.nChilds < HALF_CHILD) {
                    myResult = Status.OK_NEED_REORG;
                }
            }
            return myResult;
        }
    }

    /**
     * B木から要素を削除する
     *
     * @param key  削除する要素のキー
     * @return  削除に成功すればtrue，要素が存在しなければfalseを返す
     */
    public boolean delete(Integer key)
    {
        // currentLeafフィールドをnullにする
        currentLeaf = null;

        // 木が空ならばfalseを返す
        if (root == null) {
            return false;
        } else {
          // 木が空でない場合
            // deleteAuxメソッドを呼び出して，キーkeyをもつ要素を削除する
            Status  result = deleteAux(root, key);

            // 見つからなければ，falseを返す
            if (result == Status.NOT_FOUND) {
                return false;
            }

            if (result == Status.OK_REMOVED) {
              // 根が削除されたので，rootにnullを代入する（木が空になる）
                root = null;
            } else if (result == Status.OK_NEED_REORG
                       && ((InternalNode)root).nChilds == 1) {
              // 根が再編成された結果，根の子が1個だけになったら，
              // 木の高さを1つ減らす
              // ### Node p = root; ###
                root = ((InternalNode)root).child[0];
              // ### ここでpを解放する ###
            }
            return true;
        }
    }

    /**
     * B木の内容を表す文字列を返す（toStringの下請け）
     *
     * @param p  この節より下の部分を表す文字列を生成して返す
     * @return   節pより下の部分を表す文字列
     */
    private static String toStringAux(Node p)
    {
        // 葉か内部節かで処理を分ける
        if (p instanceof Leaf) {
          // 葉である
            Leaf l = (Leaf) p;
            return "Leaf #" + l.serial + " key=" + l.key;
        } else {
          // 内部節である
            InternalNode n = (InternalNode)p;
            String s = "Node #" + n.serial + " (" + n.nChilds + " childs): ";
            s += "#" + n.child[0].serial + " ";
            for (int i = 1; i < n.nChilds; i++) {
                s += "[" + n.low[i] + "] #" + n.child[i].serial + " ";
            }
            s += "\n";
            for (int i = 0; i < n.nChilds; i++) {
                s += toStringAux(n.child[i]) + "\n";
            }
            return s;
        }
    }

    /**
     * B木の内容を表す文字列を返す
     * （実際の処理はtoStringAuxメソッドが行う）
     *
     * @return B木の内容を表す文字列
     */
    public String toString()
    {
        if (root == null) {
            return "<木は空です>";
        } else {
            return toStringAux(root);
        }
    }

    /**
     * テスト用のメインルーチン
     *
     *     ">"というプロンプトが表示されるので，コマンドを入力すると，
     *     実行結果が表示される
     *
     *     コマンド一覧（nは整数）
     *          +n      : nを挿入する
     *          -n      : nを削除する
     *          /n      : nを探索する
     *          =string : 直前に成功した/コマンドで見つけた要素に対する値を
     *                    stringにする
     *          p       : B木の内容を表示する
     *          q       : 終了する
     */
    public static void main(String[] args) throws IOException
    {
        BTree tree = new BTree();

        // B木に初期データを挿入する
        int[] data = { 1, 100, 27, 45, 3, 135, 13 };
        for (int x: data) {
            tree.insert(x, "["+x+"]" );
        }

        // コマンドを1行入力して，それを実行する。
        // これを，EOFになるまで繰り返す
        System.out.print(">");
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = input.readLine()) != null) {
            if (str.length() == 0) {    // 空行は読み飛ばす
                System.out.print(">");
                continue;
            }

            // 先頭の1文字（コマンド）をcommandに入れる
            char  command = str.charAt(0);

            // 引数部分をargに入れる。その際に先頭のスペースを削除する
            String arg = str.substring(1).trim();       

            // コマンドによって分岐する
            if (command == 'q') {
              // qコマンド: 終了する
                break;
            } else if (command == 'p') {
              // pコマンド：B木の内容を表示する
                System.out.println(tree);
            } else if (command == '=') {
              // =コマンド：直前に成功した/コマンドの要素のデータにセットする
                if (tree.setData(arg)) {
                    System.out.println("値" + arg + "の設定に成功しました。");
                } else {
                    System.out.println("値" + arg + "の設定に失敗しました。");
                }
            } else if (command == '+' || command == '-' || command == '/') {
              // +，-，/コマンドならば，コマンドに続く数値をnumに得る
                int num = 0;
                try {
                    num = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    System.err.println("整数以外のものが指定されました:" + arg);
                    continue;
                }

                if (command == '+') {
                  // +コマンド：要素を挿入する
                    if (tree.insert(num, "[" + num + "]")) {
                        System.out.println(num + "の挿入に成功しました。");
                    } else {
                        System.out.println(num + "の挿入に失敗しました。");
                    }
                } else if (command == '-') {
                  // -コマンド：要素を削除する
                    if (tree.delete(num)) {
                        System.out.println(num + "の削除に成功しました。");
                    } else {
                        System.out.println(num + "の削除に失敗しました。");
                    }
                } else if (command == '/') {
                  // /コマンド：要素を探索する
                    if (tree.search(num)) {
                        System.out.println(num + "が見つかりました。値=" + tree.getData());
                    } else {
                        System.out.println(num + "が見つかりませんでした。");
                    }
                }
            } else {
                System.out.println("コマンド" + command +
                                   "はサポートされていません。");
            }
            System.out.print(">");
        }
    }
}
