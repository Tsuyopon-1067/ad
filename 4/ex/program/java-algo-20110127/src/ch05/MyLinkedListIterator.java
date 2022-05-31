/**
 * 連結リスト（MyLinkedListクラス）のイテレータ
 */
import java.util.*;

public class MyLinkedListIterator implements Iterator
{
    private Cell p;     // 現在着目しているセルを示すカーソル

    /**
     * イテレータを生成する
     *
     * @param list  イテレータの対象となるMyLinkedListオブジェクト
     */
    public MyLinkedListIterator(MyLinkedList list)
    {
        // 着目点をリストの頭に設定する
        p = list.header;
    }

    /**
     * 次の要素があるならtrueを返す
     *
     * @return 次の要素があればtrue，なければfalseを返す
     */
    public boolean hasNext()
    {
        return p.next != null;
    }

    /**
     * 次の要素を返す
     *
     * @return 次の要素がもつ値
     */
    public Object next()
    {
        // 次の要素が存在しなければ例外NoSuchElementExceptionをスローする
        if (p.next == null) {
            throw new NoSuchElementException();
        }

        // 着目点を次の要素に移動して，そのデータを値として返す
        p = p.next;
        return p.data;
    }

    /**
     * 直前にnextが返した要素を削除する
     */
    public void remove()
    {
        // このメソッドは未実装である。
        // 例外UnsupportedOperationExceptionを投げる
        throw new UnsupportedOperationException();
    }

    /**
     * テスト用のメインルーチン
     */
    public static void main(String args[])
    {
        // 連結リストlistを作成して，要素20, 15, 18, 37, 3を追加する
        MyLinkedList list = new MyLinkedList();
        list.insert(20);        list.insert(15);        list.insert(18);
        list.insert(37);        list.insert(3);
        System.out.println(list);

        // イテレータiterを利用して，すべての要素を表示する
        System.out.println("----<イテレータ>--------");
        Iterator iter = list.iterator();
        int count = 1;
        while (iter.hasNext()) {
            System.out.println(count++ + "番目の要素:" + iter.next());
        }

        // 拡張for文(for-each文) を利用して，すべての要素を表示する(Java 5以降)
        System.out.println("----<拡張for文>--------");
        count = 1;
        for (Object o: list) {
            System.out.println(count++ + "番目の要素:" + o);
        }
    }
}
