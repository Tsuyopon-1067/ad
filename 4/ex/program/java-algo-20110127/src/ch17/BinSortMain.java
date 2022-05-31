/**
 * ビンソートのメインプログラム
 */
public class BinSortMain {

    /**
     *  ビンソートをデモする
     */
    public static void main(String[] args)
    {
        // ソートする配列を初期化する
	int keys[] = {
	    13, 24, 15,  5, 98, 44, 35, 96, 82, 73
	};
        BinSortData[]  array = new BinSortData[keys.length];
	for (int i = 0; i < keys.length; i++) {
	    array[i] = new BinSortData(keys[i], "要素" + i);
	}

        // 配列arrayをビンソートする
        BinSort.sort(array);

        // 配列arrayの内容を表示する
        for (int i = 0; i < array.length; i++) {
            System.out.println("key=" + array[i].getKey() +
                               "  data=" + array[i].getData());
        }
    }
}
