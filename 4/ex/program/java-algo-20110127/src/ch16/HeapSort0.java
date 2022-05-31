/**
 * ヒープを利用したソート
 */
class HeapSort0
{
    /*
     * ヒープを利用して配列を整列する
     *
     * @param a  整列すべき配列
     */
    public static void sort(int[] a)
    {
        int n = a.length;       // 配列の要素数

        // 作業用のヒープを生成する
        Heap heap = new Heap(n);
        
        // 配列のすべての要素をヒープに挿入する
        for (int i = 0; i < n; i++) {
            heap.insert(a[i]);
        }

        // キーが小さい順に要素を取り出して，配列に戻す
        for (int i = 0; i < n; i++) {
            a[i] = heap.deleteMin();
        }
    }
}
