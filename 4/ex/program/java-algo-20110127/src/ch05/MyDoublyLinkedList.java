/**
 * 双方向リスト
 */
import java.util.*;

public class MyDoublyLinkedList
{
    final CellDouble head;    // リストの頭へのリンク

    /**
     * 双方向リストを生成する。
     * 生成される双方向リストは空になっている
     */
    public MyDoublyLinkedList()
    {
        // リストの頭を割り当てる
        head = new CellDouble("**List Head**");

        // リストの頭のprevとnextが自分自身を指すようにする
        head.prev = head.next = head;
    }

    /**
     * 双方向リストのセルpの直後にデータdataを挿入する
     *
     * @param p     このセルの直後にデータを挿入する
     * @param data  挿入するデータ
     */
    private void insertAfter(CellDouble p, Object data)
    {
        CellDouble x = new CellDouble(data);
        x.prev = p;
        x.next = p.next;
        p.next.prev = x;
        p.next = x;
    }

    /**
     * 双方向リストの先頭にデータdataを挿入する
     *
     * @param data  挿入するデータ
     */
    public void insertFirst(Object data)
    {
        // リストの頭の次に挿入する
        insertAfter(head, data);
    }

    /**
     * 双方向リストの末尾にデータxを挿入する
     *
     * @param data  挿入するデータ
     */
    public void insertLast(Object data)
    {
        // 最後の要素の次に挿入する
        insertAfter(head.prev, data);
    }

    /**
     * 指定されたセルを削除する
     */
    private void removeCell(CellDouble p)
    {
        p.prev.next = p.next;
        p.next.prev = p.prev;
    }
    
    /**
     * 双方向リストの先頭のデータを削除する
     *
     * @return 削除した要素を返す。
     *         ただし要素がなければ例外NoSuchElementExceptionをスローする
     */
    public Object removeFirst()
    {
        // 要素がなければ例外NoSuchElementExceptionをスローする
        if (isEmpty()) {
            throw new NoSuchElementException("双方向リストが空です。");
        }

        // 最初のセルを削除して，そこに入っていた値を返す
        CellDouble cell = head.next;
        removeCell(cell);
        return cell.data;
    }

    /**
     * 双方向リストの末尾のデータを削除する
     *
     * @return 削除した要素を返す。
     *         ただし要素がなければ例外NoSuchElementExceptionをスローする
     */
    public Object removeLast()
    {
        // 要素がなければ例外NoSuchElementExceptionをスローする
        if (isEmpty()) {
            throw new NoSuchElementException("双方向リストが空です。");
        }

        // 最後のセルを削除して，そこに入っていた値を返す
        CellDouble cell = head.prev;
        removeCell(cell);
        return cell.data;
    }

    /**
     * 双方向リストが空かどうかチェックする
     *
     * @return 空ならばtrue，空でなければfalseを返す
     */
    public boolean isEmpty()
    {
        return head.next == head;
    }

    /**
     * 双方向リストを表す文字列表現を返す
     *
     * @return この双方向リストの内容を表す文字列
     */
    public String toString()
    {
        String s = "[";
        for (CellDouble p = head.next; p != head; p = p.next) {
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
        MyDoublyLinkedList list = new MyDoublyLinkedList();

        // 要素を挿入する
        System.out.print("初期状態(空リスト)"); System.out.println(list);
        list.insertFirst("a");
        System.out.print("aを先頭に挿入  ");    System.out.println(list);
        list.insertLast("b");
        System.out.print("bを末尾に挿入  ");    System.out.println(list);
        list.insertFirst("c");
        System.out.print("cを先頭に挿入  ");    System.out.println(list);
        list.insertFirst("d");
        System.out.print("dを先頭に挿入  ");    System.out.println(list);
        list.insertLast("e");
        System.out.print("eを末尾に挿入  ");    System.out.println(list);

        // 要素を削除する
        // 変数iを利用して，先頭と末尾から交互に要素を削除する
        int  i = 0;

        while(!list.isEmpty()) {
            if (i++ % 2 == 0) {
                System.out.print(list.removeFirst() + "を先頭から取り出した  ");
            } else {
                System.out.print(list.removeLast() +  "を末尾から取り出した  ");
            }
            System.out.println(list);
        }
    }
}
