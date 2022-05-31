/**
 * 基数ソートを行う
 */
public class RadixSort {

    // サブキーは0から15までの範囲の整数（4ビット）
    static final int  M = 15;

    // サブキーを取り出すのに使うマスク（2進数の1111）
    static final int  MASK = 0xf;

    /**
     * 基数ソートを行う。渡された配列aの中身をソートする
     *
     * @param  a  ソートする配列
     */
    static void sort(RadixSortData[] a)
    {
        final int N = a.length;         // 配列の要素数
        int  pass = 1;                  // 何回目のソートか？

        // 作業用の配列
        RadixSortData[] b = new RadixSortData[N];

        // キーの分布を数え上げるための配列
        int[] count = new int[M + 1];

        // 下位から上位に向かって，4ビットずつ4回ループを実行する
        for (int bit = 0; bit < 16; bit += 4) {

            // カウンタをすべて0にする
            for (int i = 0; i <= M; i++) {
                count[i] = 0;
            }

            // キーを数え上げる
            for (int i = 0; i < N; i++) {
                count[(a[i].getKey()>>bit) & MASK]++;
            }

            // 数え上げたキーの累積度数分布を求める
            for (int i = 0; i < M; i++) {
                count[i+1] += count[i];
            }

            // 度数分布に従って，データを配列aから作業用配列bにコピーする
            for (int i = N - 1; i>= 0; i--) {
                b[--count[(a[i].getKey() >> bit) & MASK]] = a[i];
            }

            // データを作業用配列bから配列aへコピーする
            System.arraycopy(b, 0, a, 0, N);

            // 配列の内容を表示する
            System.out.println("Pass " + pass++ + " --------------------");
            dumpArray(a);
        }
    }

    /**
     * RadixSortData型の配列の内容を16進数でダンプする
     *
     * @param a  ダンプする配列
     */
    public static void dumpArray(RadixSortData a[])
    {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("key=%04x  data=%s%n",
			      a[i].getKey(), a[i].getData());
        }
    }
}
