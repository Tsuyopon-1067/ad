/**
 * 選択ソート
 */
class SelectionSort
{
    /*
     * 選択ソートによって配列を整列する
     * 
     * @param a   整列する配列
     */
    public static void sort(int[] a)
    {
        int  n = a.length;      // 配列の要素数

        for (int i = 0; i < n - 1; i++) {
            int lowest = i;
            int lowkey = a[i];
            for (int j = i + 1; j < n; j++) {
                if (a[j] < lowkey) {
                    lowest = j;
                    lowkey = a[j];
                }
            }
            int temp  = a[i];
            a[i]      = a[lowest];
            a[lowest] = temp;
        }
    }
}
