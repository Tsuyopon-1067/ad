/**
 * バブルソート
 */
class BubbleSort
{
    /*
     * バブルソートによって配列を整列する
     * 
     * @param a   整列する配列
     */
    public static void sort(int[] a)
    {
        int  n = a.length;      // 配列の要素数

        for (int i = 0; i < n - 1; i++) {
            for (int j = n - 1; j > i; j--) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j];
                    a[j]     = a[j-1];
                    a[j - 1] = temp;
                }
            }
        }
    }
}
