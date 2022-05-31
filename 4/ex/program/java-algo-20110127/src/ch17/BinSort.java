/**
 * ビンソートを行う
 */
public class BinSort {

    /**
     * ビンソートを行う。渡された配列aの中身をソートする
     *
     * @param  a  ソートする配列
     */
    public static void sort(BinSortData[] a)
    {
        final int N = a.length;         // 配列の要素数
        final int M = BinSortData.M;    // キーは0からMまでの範囲の数

        // ビンを割り当てる
        // 割り当ての時点では，すべてのビンには空であることを示すnullが
        // セットされている
        BinSortData[] bin = new BinSortData[M + 1];

        // 配列aのデータを順番にビンに振り分ける
        for (int i = 0; i < N; i++) {

	    // キーを変数keyに入れる
	    int key = a[i].getKey();

	    // キーが重複していないかチェック
	    if (bin[key] != null) {
		throw new IllegalArgumentException(
				   "キー"+key+"が重複しています。");
	    }

	    // 要素をビンに入れる
            bin[key] = a[i];
        }

        // データをビンから（昇順に）取り出して，配列aに戻す
        int j = 0;
        for (int i = 0; i <= M; i++) {
            if (bin[i] != null) {
                a[j++] = bin[i];
            }
        }
    }
}
