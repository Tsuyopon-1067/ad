/*
 * ヒープ
 */
class Heap
{
    int[] a;    // ヒープの実体が入る配列
    int   n;    // ヒープに入っている要素の個数

    /**
     * ヒープを生成する
     *
     * @param size  ヒープに登録できる要素の最大個数
     */
    public Heap(int size)
    {
        // 配列aのうちa[1]～a[size+1]を使用する
        // a[0]は使わないため，size+1個の要素を確保することに注意
        a = new int[size + 1];
        n = 0;
    }

    /**
     * ヒープ中のx番目の要素を必要な場所まで浮かび上がらせる
     *
     * @param x  浮かび上がらせる要素の添え字
     */
    private void upHeap(int x)
    {
        // 浮かび上がらせる要素の値をvalueに入れておく
        int value = a[x];

        // 要素が根まで浮かび上がっていない，かつ
        // 「親が子より大きい」間繰り返す
        while (x > 1 && a[x/2] > value) {

            // 親の値を子に移す
            a[x] = a[x/2];

            // 注目点を親に移す
            x /= 2;
        }

        // 最終的な落ち着き先が決まった
        a[x] = value;
    }

    /**
     * ヒープに要素を登録する
     *
     * @param elem  登録すべき要素
     */
    public void insert(int elem)
    {
        // ヒープに登録できる余裕があるか確認する
        if (n >= a.length) {
            throw new IllegalStateException("これ以上ヒープに要素を登録できません。");
        }

        // 要素をとりあえずヒープの最後に入れる
        a[++n] = elem;

        // 追加した要素を浮かび上がらせる
        upHeap(n);
    }

    /**
     * ヒープの先頭の要素a[1]を必要なところまで沈める
     */
    private void downHeap()
    {
        // 沈められる要素の値をvalueにセットしておく
        int  value = a[1];

        // 根から始めて，節iが子をもっている限り繰り返す
        int  i = 1;
        while (i <= n/2) {

            // 節iの子のうち小さいほうを節jとする
            int  j = i * 2;
            if (j + 1 <= n && a[j] > a[j + 1]) {
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

    /**
     * ヒープから最小の要素を削除する
     * 要素の値を返す
     *
     * @return 削除した要素の値
     */
    int deleteMin()
    {
        // ヒープが空でないことを確認する
        if (n < 1) {
            throw new IllegalStateException("ヒープが空です。");
        }

        // 根の要素を戻り値にする
        //  （ヒープの先頭が根に当たる）
        int value = a[1];

        // ヒープの最後の要素を先頭に移動する
        a[1] = a[n--];

        // 先頭に移動した要素を適切な場所まで沈める
        downHeap();

        return value;
    }
}
