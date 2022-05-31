/**
 * 連結リスト版マージソート
 */
class MergeSortList
{
    /*
     * 2つの連結リストaとbをマージする。
     * マージされた連結リストの先頭要素へのリンクを返す
     *
     * @param a  マージすべき連結リスト
     * @param b  マージすべき連結リスト
     * @return   マージして得られた連結リストの先頭要素へのリンク
     */
    private static Cell mergeList(Cell a, Cell b)
    {
        // 変数headはマージ済み連結リストの先頭にあるダミーのセルを指す
        Cell head = new Cell(0);

        // リンクpがダミーのセルを指すようにしておく
        Cell p = head;

        // 連結リストa，bのいずれかが空になるまで繰り返す
        while (a != null && b != null) {

            // 連結リストaとbの先頭の要素を比較する
            if (a.data <= b.data) {

                // 連結リストaの先頭の要素を取り除いて，マージ済み連結リスト
                // の末尾に連結する
                p.next = a;
                p = a;
                a = a.next;
            } else {
                // 連結リストbの先頭の要素を取り除いて，マージ済み連結リスト
                // の末尾に連結する
                p.next = b;
                p = b;
                b = b.next;
            }
        }

        // 残っている要素をマージ済み連結リストの最後尾に連結する
        if (a == null) {
            p.next = b;
        } else {
            p.next = a;
        }

        // マージ済みの連結リストを戻り値として返す
        return head.next;
    }

    /**
     * 連結リスト版のマージソート
     * 連結リストxを整列する
     *
     * @param x  整列すべき連結リスト
     * @return   整列された連結リストの先頭要素へのリンクを返す
     */
    public static Cell mergeSortList(Cell x)
    {
        // 連結リストの要素がまったくないか，1つしかないときは
        // そのまま戻る
        if (x == null || x.next == null) {
            return x;
        }

        // 連結リストをスキャンする変数を初期化する

        // aは1番目の要素を指す
        Cell a = x;

        // bは3番目の要素（もし連結リストの長さが2のときは2番目の要素）を指す
        Cell b = x.next;
        if (b != null) {
            b = b.next;
        }

        // 変数bが連結リストの末尾に到達するまで，変数aを1つ進め，
        // 変数bを2つ進める。変数bが末尾に到達したとき，変数aは
        // 連結リストのほぼ中央の要素を指しているはずである
        while (b != null) {
            a = a.next;
            b = b.next;
            if (b != null) {
                b = b.next;
            }
        }

        // 連結リストを，変数aが指す要素の直後で2つに分割する
        // 変数pは，後半の連結リストの先頭の要素を指す
        Cell p = a.next;
        a.next = null;

        // 分割した連結リストをそれぞれ個別に整列して，その結果をマージする
        return mergeList(mergeSortList(x), mergeSortList(p));
    }
}
