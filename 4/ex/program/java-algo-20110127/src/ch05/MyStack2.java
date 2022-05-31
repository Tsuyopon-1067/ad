/*
 * 双方向リストによるスタックの実装
 */
import java.util.*;

public class MyStack2
{
    private MyDoublyLinkedList  stack;  // スタックの実体を表す双方向リスト
    private int  nElements;             // スタックに入っている要素の個数

    /**
     * スタックを生成する
     */
    public MyStack2()
    {
        // スタックの実体の双方向リストを割り当てる
        stack = new MyDoublyLinkedList();

        // 要素の個数を0にする
        nElements = 0;
    }

    /*
     * スタックの中身を捨てて空の状態にする
     */
    public void clear()
    {
        // 双方向リストを作り直す
        stack = new MyDoublyLinkedList();
        
        // 要素の個数を0にする
        nElements = 0;
    }

    /*
     * スタックにデータを積む
     *
     * @param x  積むデータ
     */
    public void push(Object x)
    {
        stack.insertLast(x);
        nElements++;
    }

    /*
     * スタックからデータを降ろす
     *
     * @return  スタックから降ろしたデータ
     */
    public Object pop()
    {
        if (nElements-- <= 0) {
            throw new EmptyStackException();
        }
        return stack.removeLast();
    }

    /*
     * スタックが空かどうかを調べる
     *
     * @return 空ならばtrue，空でなければfalseを返す
     */
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    /*
     * スタックの内容を表す文字列を返す
     *
     * @return スタックの内容を表す文字列
     */
    public String toString()
    {
        return "要素数=" + nElements + "  " + stack.toString();
    }

    /*
     * テスト用メインルーチン
     */
    public static void main(String args[])
    {
        MyStack2 stack = new MyStack2();

        stack.push("a");  stack.push("b");  stack.push("c");
        System.out.println(stack);
        System.out.println("pop:" + stack.pop());
        System.out.println(stack);
        stack.push("d");  stack.push("e");  stack.push("f");
        System.out.println(stack);
        while (!stack.isEmpty()) {
                System.out.println("pop:" + stack.pop());
        }
        System.out.println("DONE! " + stack);
    }
}
