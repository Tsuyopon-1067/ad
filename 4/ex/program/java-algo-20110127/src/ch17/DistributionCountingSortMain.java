/**
 * 分布数え上げソートのメインプログラム
 */
public class DistributionCountingSortMain {

    /**
     * 分布数え上げソートをデモする
     */
    public static void main(String[] args)
    {
        // ソートする配列を初期化する
	int data[] = {
	    13, 24, 15,  5, 98, 44, 35, 15, 82, 73
	};
        BinSortData[]  array = new BinSortData[data.length];
	for (int i = 0; i < data.length; i++) {
	    array[i] = new BinSortData(data[i], "要素" + i);
	}

        // 配列arrayを分布数え上げソートする
        DistributionCountingSort.sort(array);

        // 配列arrayの内容を表示する
        for (int i = 0; i < array.length; i++) {
            System.out.println("key=" + array[i].getKey() +
                               "  data=" + array[i].getData());
        }
    }
}
