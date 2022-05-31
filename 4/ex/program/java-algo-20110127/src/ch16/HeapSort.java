/**
 * ヒープソート
 */
class HeapSort
{
    /**
     * ヒープの先頭の要素を必要なところまで沈める。
     * ただし，a[from]をヒープの先頭，a[to]をヒープの最後の要素とする
     *
     * @param a     ヒープが入っている配列
     * @param from  ヒープの先頭の要素の添え字
     * @param to    ヒープの最後の要素の添え字
     */
    private static void downHeap(int[] a, int from, int to)
    {
        // 沈められる要素の値をvalueにセットしておく
        int  value = a[from];

        // 根から始めて，節iが子をもっている限り繰り返す
        int  i = from;
        while (i <= to/2) {

            // 節iの子のうち小さいほうを節jとする
            int  j = i * 2;
            if (j + 1 <= to && a[j] > a[j + 1]) {
                j++;
            }

            // もし，親が子より大きくないという関係が成り立てば，
            // これ以上沈める必要はない
            if (value <= a[j]) {
                break;
            }

            // 節iに節jの値を入れて，節jに注目する
            a[i] = a[j];
            i = j;
        }

        // 先頭にあった要素を節iに入れる
        a[i] = value;
    }

    /*
     * ヒープソートによって配列を整列する
     * 指定された配列aのうち，a[1]から最後の要素までを降順に整列する
     * (a[0]は整列の対象にならないので注意)
     * 
     * @param a   整列する配列
     */
    public static void sort(int[] a)
    {
        // 渡された配列aの最後の要素の添え字
        int n = a.length - 1;

        for (int i = n / 2; i >= 1; i--) {
            downHeap(a, i, n);
        }

        for (int i = n; i >= 2; i--) {
            int tmp = a[1];
            a[1] = a[i];
            a[i] = tmp;
            downHeap(a, 1, i - 1);
        }
    }
}
