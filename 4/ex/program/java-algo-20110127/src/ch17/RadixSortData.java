/**
 * 基数ソートの対象となるデータ型
 */
public class RadixSortData {

    final static int KEY_MAX = 0xffff;     // キーの最大値

    private int     key;  // 整列のキー。
                          // 0～65535(0xffff)の範囲でなければならない
    private Object  data; // その他の情報

    /**
     * ソートの対象となるデータを生成する
     *
     * @param key  キー
     * @param data  その他の情報
     */
    RadixSortData(int key, Object data)
    {
        // キーが範囲内に収まっているかをチェックする
        if (key < 0 || key > KEY_MAX) {
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
