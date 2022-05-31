/*
 * 配列による待ち行列の実装
 */
import java.util.*;

public class MyQueue
{
    private Object  queue[];    // 待ち行列本体
    private int  queueSize;     // 待ち行列の大きさ
    private int  front;         // 待ち行列の先頭
    private int  rear;          // 待ち行列の末尾
                                // （実際には、末尾の次の要素を指す）

    // デフォルトの待ち行列の大きさ
    private static final int  DEFAULT_QUEUE_SIZE = 100;

    /**
     * 待ち行列を生成する（大きさはDEFAULT_QUEUE_SIZE）
     */
    public MyQueue()
    {
        this(DEFAULT_QUEUE_SIZE);
    }

    /**
     * 大きさを指定して、待ち行列を生成する
     *
     * @param size   待ち行列の大きさ
     */
    public MyQueue(int size)
    {
        queueSize = size;
        queue = new Object[size];
        front = rear = 0;
    }

    /**
     * 次の要素の添え字を求める
     *
     * @param a  現在の要素の添え字
     */
    private int next(int a)
    {
        return (a + 1) % queueSize;
    }

    /**
     * 待ち行列の中身を捨てて空にする
     */
    public void clear()
    {
        front = rear = 0;
        Arrays.fill(queue, 0, queueSize, null);  // 待ち行列をnullでクリアする
    }

    /**
     * 待ち行列にデータを入れる
     *
     * @param x  待ち行列に入れるデータ
     */
    public void enqueue(Object x)
    {
        if (next(rear) == front) {
            throw new IllegalStateException("これ以上，待ち行列に要素を追加できません");
        }
        queue[rear] = x;
        rear = next(rear);
    }

    /**
     * 待ち行列からデータを取り出す
     *
     * @return  待ち行列から取り出したデータ
     */
    public Object dequeue()
    {
        if (front == rear) {
            throw new NoSuchElementException("待ち行列が空なので要素を取り出せません");
        }
        Object x = queue[front];
        queue[front] = null;            // 要素をnullでクリアする
        front = next(front);
        return x;
    }

    /**
     * 待ち行列が空かどうかを調べる
     *
     * @return  空ならtrue，空でなければfalseを返す
     */
    public boolean isEmpty()
    {
        return front == rear;
    }

    /**
     * 待ち行列の内容を表す文字列を返す
     *
     * @return  待ち行列の内容を表す文字列
     */
    public String toString()
    {
        String s = "MyQueue=[";
        for (int i = front; i != rear; i = next(i)) {
            s += queue[i] + " ";
        }
        s += "] front=" + front + " rear=" + rear;
        return s;
    }

    /**
     * テスト用のメインルーチン
     */
    public static void main(String args[])
    {
        MyQueue q = new MyQueue(5);

        q.enqueue("a");         System.out.println("aを入れた");
        q.enqueue("b");         System.out.println("bを入れた");
        q.enqueue("c");         System.out.println("cを入れた");
        System.out.println(q);
        System.out.println(q.dequeue() + "を取り出した");
        System.out.println(q.dequeue() + "を取り出した");
        System.out.println(q);
        q.enqueue("d");         System.out.println("dを入れた");
        q.enqueue("e");         System.out.println("eを入れた");
        System.out.println(q.dequeue() + "を取り出した");
        System.out.println(q);
        q.enqueue("f");         System.out.println("fを入れた");
        System.out.println(q.dequeue() + "を取り出した");
        System.out.println(q.dequeue() + "を取り出した");
        System.out.println(q);
        q.clear();              System.out.println("待ち行列をクリアした");
        System.out.println(q);
        q.enqueue("g");         System.out.println("gを入れた");
        q.enqueue("h");         System.out.println("hを入れた");
        System.out.println(q);
    }
}
