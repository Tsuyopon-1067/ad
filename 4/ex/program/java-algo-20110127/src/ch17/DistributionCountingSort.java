/**
 * 分布数え上げソートを行う
 */
public class DistributionCountingSort {

    /**
     * 分布数え上げソートを行う。配列aの内容をソートする
     *
     * @param  a  ソートする配列
     */
    static void sort(BinSortData[] a)
    {
        final int N = a.length;         // 配列の要素数
        final int M = BinSortData.M;    // キーは0からMまでの範囲の数

        // カウンタに使う配列を割り当てる
        // （要素は自動的に0に初期化される）
        int[] count = new int[M + 1];

        // キーを数え上げる
        for (int i = 0; i < N; i++) {
            count[a[i].getKey()]++;
        }

        // 数え上げたキーの累積度数分布を求める
        for (int i = 0; i < M; i++) {
            count[i+1] += count[i];
        }

        // 度数分布に従って，データを配列aから作業用配列bにコピーする
        BinSortData[] b = new BinSortData[N];
        for (int i = N - 1; i >= 0; i--) {
            b[--count[a[i].getKey()]] = a[i];
        }

        // 配列bに入っている整列されたデータを配列aにコピーする
        System.arraycopy(b, 0, a, 0, N);
    }
}
