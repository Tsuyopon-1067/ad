/**
 * ビンソートと分布数え上げソートの対象となるデータ型
 */
public class BinSortData {

    // キーは0からMまでの範囲の整数
    static final int  M = 100;

    private int     key;  // 整列のキー
                          // 0からMまでの範囲でなければならない
    private Object  data; // その他の情報

    /**
     * ソートの対象となるデータを生成する
     *
     * @param key  キー
     * @param data  その他の情報
     */
    BinSortData(int key, Object data)
    {
        // キーが範囲内に収まっているかをチェックする
        if (key < 0 || key > M) {
            throw new IllegalArgumentException(
                            "キー" + key + "が範囲外です。");
        }
        this.key = key;
        this.data = data;
    }

    /**
     * キーを取得する
     *
     * @return キーの値を返す
     */
    public int getKey()
    {
        return key;
    }

    /**
     * その他の情報を取得する
     *
     * @return その他の情報を返す
     */
    public Object getData()
    {
        return data;
    }
}
