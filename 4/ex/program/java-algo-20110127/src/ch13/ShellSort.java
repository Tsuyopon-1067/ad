/**
 * シェルソート
 */
class ShellSort
{
    /*
     * シェルソートによって配列を整列する
     * 
     * @param a   整列する配列
     */
    public static void sort(int[] a)
    {
        int  n = a.length;      // 配列の要素数
        int  h;

        for (h = 1; h < n / 9; h = h * 3 + 1)
            ;

        for (; h > 0; h /= 3) {
            for (int i = h; i < n; i++) {
                int j = i;
                while (j >= h && a[j - h] > a[j]) {
                    int temp = a[j];
                    a[j]     = a[j - h];
                    a[j - h] = temp;
                    j -= h;
                }
            }
        }
    }
}
