/**
 * クイックソート（非再帰版，スタックオーバーフロー対策済み）
 */
class QuickSort3
{
    /*
     * 配列a[l]～a[r]を分割する。枢軸の添え字を返す
     */
    private static int partition(int[] a, int l, int r)
    {
        // ポインタiとjを初期化する
        int i = l - 1;
        int j = r;

        // 右端の要素を枢軸にする
        int pivot = a[r];

        // ポインタiとjがぶつかるまで繰り返す
        while (true) {
            // ポインタiを右へ進める
            while (a[++i] < pivot)
                ;
            // ポインタjを左へ進める
            while (i < --j && pivot < a[j])
                ;
            // ポインタiとjがぶつかったらループを抜ける
            if (i >= j)
                break;
            // iの指す要素とjの指す要素を交換する
            int temp = a[i];
            a[i]     = a[j];
            a[j]     = temp;
        }
        // a[i]と枢軸を交換する
        int temp = a[i];
        a[i]     = a[r];
        a[r]     = temp;
        return i;
    }

    /*
     * クイックソートによって配列を整列する
     * 
     * @param a   整列する配列
     */
    public static void sort(int[] a)
    {
        int n = a.length;
        int[] low  = new int[30];
        int[]  high= new int[30];
        int sp;

        // スタックを初期化する
        low[0]  = 0;
        high[0] = n - 1;
        sp = 1;

        // スタックが空になるまで繰り返す
        while (sp > 0) {
            // スタックから，整列する範囲を取り出す
            int l = low[--sp];
            int r = high[sp];

            // 整列する要素が1つなら，何もしない
            // （再びwhile文を実行する）
            if (l >= r) {
                // 何もしない
            } else {
                // 枢軸vを基準に分割する
                int v = partition(a, l, r);

                // 左右の部分配列のうち，短いほうを先に処理する
                if (v - l < r - v) {
                    // 左部分配列を先に整列する
                    // （スタックなので，「右左」の順に積むことに注意）
                    low[sp]    = v + 1;
                    high[sp++] = r;
                    low[sp]    = l;
                    high[sp++] = v - 1;
                } else {
                    // 右部分配列を先に整列する
                    // （スタックなので，「左右」の順に積むことに注意）
                    low[sp]    = l;
                    high[sp++] = v - 1;
                    low[sp]    = v + 1;
                    high[sp++] = r;
                }
            }
        }
    }
}
