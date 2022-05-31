/**
 * 基数ソートのメインプログラム
 */
public class RadixSortMain {

    /**
     *  基数ソートをデモする
     */
    public static void main(String[] args)
    {
        // ソートする配列を初期化する
	int data[] = {
	    0x2f38, 0x2fb7, 0x9328, 0xa400, 0x000f, 
	    0x0002, 0x0844, 0xef85, 0x289a, 0x2f30,
        };
        RadixSortData[]  array = new RadixSortData[data.length];
	for (int i = 0; i < data.length; i++) {
	    array[i] = new RadixSortData(data[i], "要素" + i);
	}

        // 配列arrayの内容を表示する
        System.out.println("ソート前");
        RadixSort.dumpArray(array);

        // 配列arrayを基数ソートする
        RadixSort.sort(array);
    }
}
