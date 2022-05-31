
/*
 * 配列による待ち行列の実装
 */
import java.util.*;

public class MyQueue3 {
    public final String DEQUEUEERROR = "__ERROR__";

    // デフォルトの待ち行列の大きさ
    private static final int DEFAULT_QUEUE_SIZE = 100;

    private MyQueue1 queue = new MyQueue1();
    private TreeMap<Integer, MyQueue1> pQueue = new TreeMap<Integer, MyQueue1>();

    /**
     * 待ち行列の中身を捨てて空にする
     */
    public void clear() {
        queue = new MyQueue1();
        pQueue = new TreeMap<Integer, MyQueue1>();
    }

    /**
     * 待ち行列にデータを入れる
     *
     * @param x 待ち行列に入れるデータ
     */
    public boolean enqueue(String x, int b) {
        if (!pQueue.containsKey(b)) {
            pQueue.put(b, new MyQueue1());
        }
        MyQueue1 tmp = pQueue.get(b);
        tmp.enqueue(x);
        return true;
    }
    public boolean enqueue(String x) {
        queue.enqueue(x);
        return true;
    }

    /**
     * 待ち行列からデータを取り出す
     *
     * @return 待ち行列から取り出したデータ
     */
    public String dequeue() {
        if (pQueue.size() != 0) {
            int idx = pQueue.firstKey();
            MyQueue1 tmp = pQueue.get(idx);
            if (tmp.getSize() == 1) {
                pQueue.remove(idx);
            }
            return tmp.dequeue();
        }
        return queue.dequeue();
    }

    /**
     * 待ち行列が空かどうかを調べる
     *
     * @return 空ならtrue，空でなければfalseを返す
     */
    public boolean isEmpty() {
        boolean res = true; // 最初は空と仮定し，空でないものを見つけたらfalseにする
        if (!queue.isEmpty()) res = false;
        for (var q : pQueue.values()) {
            if (!q.isEmpty()) res = false;
        }
        return res;
    }

    /**
     * 待ち行列の内容を表す文字列を返す
     *
     * @return 待ち行列の内容を表す文字列
     */
    public String toString() {
        String s = "MyQueue1=[";
        s += queue + "\n";
        for (int idx : pQueue.keySet()) {
            s += idx + " : ";
            s += pQueue.get(idx);
            s += "\n";
        }
        s += "]";
        return s;
    }
}
