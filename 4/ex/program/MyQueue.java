
/*
 * 配列による待ち行列の実装
 */
import java.util.*;

public class MyQueue {
    private Object queue[]; // 待ち行列本体
    private int queueSize; // 待ち行列の大きさ
    private int front; // 待ち行列の先頭
    private int rear; // 待ち行列の末尾
                      // （実際には、末尾の次の要素を指す）
    private int size = 0; // 待ち行列の現在要素数
    public final String DEQUEUEERROR = "__ERROR__";

    // デフォルトの待ち行列の大きさ
    private static final int DEFAULT_QUEUE_SIZE = 100;

    /**
     * 待ち行列を生成する（大きさはDEFAULT_QUEUE_SIZE）
     */
    public MyQueue() {
        this(DEFAULT_QUEUE_SIZE);
    }

    /**
     * 大きさを指定して、待ち行列を生成する
     *
     * @param size 待ち行列の大きさ
     */
    public MyQueue(int size) {
        queueSize = size;
        queue = new Object[size];
        front = rear = 0;
    }

    /**
     * 次の要素の添え字を求める
     *
     * @param a 現在の要素の添え字
     */
    private int next(int a) {
        return (a + 1) % queueSize;
    }

    /**
     * 待ち行列の中身を捨てて空にする
     */
    public void clear() {
        front = rear = 0;
        Arrays.fill(queue, 0, queueSize, null); // 待ち行列をnullでクリアする
        size = 0; // 要素数を0にする
    }

    /**
     * 待ち行列にデータを入れる
     *
     * @param x 待ち行列に入れるデータ
     */
    public boolean enqueue(Object x) {
        if (size == queueSize && front == rear) {
            return false;
            // throw new IllegalStateException("これ以上，待ち行列に要素を追加できません");
        }
        queue[rear] = x;
        rear = next(rear);
        size++;
        return true;
    }

    /**
     * 待ち行列からデータを取り出す
     *
     * @return 待ち行列から取り出したデータ
     */
    public String dequeue() {
        if (size == 0 && front == rear) {
            return DEQUEUEERROR;
            // throw new NoSuchElementException("待ち行列が空なので要素を取り出せません");
        }
        Object x = queue[front];
        queue[front] = null; // 要素をnullでクリアする
        front = next(front);
        size--;
        return x.toString();
    }

    /**
     * 待ち行列が空かどうかを調べる
     *
     * @return 空ならtrue，空でなければfalseを返す
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 待ち行列の内容を表す文字列を返す
     *
     * @return 待ち行列の内容を表す文字列
     */
    public String toString() {
        String s = "MyQueue=[";
        for (int i = 0; i < size; i++) {
            int idx = (i + front) % queueSize;
            s += queue[idx] + " ";
        }
        s += "] front=" + front + " rear=" + rear;
        return s;
    }
}
