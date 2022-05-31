/**
 * 連結リスト
 * 常に要素（整数）が昇順に並ぶようにする
 */
public class MyLinkedList implements Iterable
{
    final Cell header;        // リストの頭へのリンク

    /**
     * 連結リストを生成する。
     * 生成される連結リストは空になっている
     */
    public MyLinkedList()
    {
        header = new Cell("**List Head**");     // リストの頭を作成する
    }

    /**
     * 連結リストに要素numを挿入する。
     * 要素は，連結リストが昇順になるような場所に挿入される
     *
     * @param num  挿入する要素（整数）
     */
    public void insert(int num)
    {
        // 挿入すべき場所を探す
        Cell p = header.next;
        Cell q = header;
        while (p != null && num > (Integer)p.data) {  // オートアンボクシング
            q = p;
            p = p.next;
        }

        // セルを挿入する
        Cell newCell = new Cell(num);                 // オートボクシング
        newCell.next = p;
        q.next = newCell;
    }

    /**
     * イテレータを得る
     *
     * @return この連結リストに対するイテレータ
     */
    public MyLinkedListIterator iterator()
    {
        return new MyLinkedListIterator(this);
    }

    /**
     * 連結リストを表す文字列表現を返す
     *
     * @return この連結リストの内容を表す文字列
     */
    public String toString()
    {
        String s = "[";
        for (Cell p = header.next; p != null; p = p.next) {
            s += p.data + " ";
        }
        s += "]";
        return s;
    }

    /**
     * テスト用のメインルーチン
     */
    public static void main(String args[])
    {
        MyLinkedList list = new MyLinkedList();

        System.out.println(list);
        list.insert(5);         System.out.println(list);
        list.insert(7);         System.out.println(list);
        list.insert(2);         System.out.println(list);
        list.insert(12);        System.out.println(list);
        list.insert(4);         System.out.println(list);
    }
}
