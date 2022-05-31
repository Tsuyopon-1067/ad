public class LinearSearch {

    /*
     * テーブルのエントリ
     */
    static private class Entry {

        int key;        // 比較の対象となるキー
        Object data;    // それ以外の情報

        /**
         * エントリを生成する
         * @param key キー
         * @param data キーkey に対応するデータ
         */
        private Entry(int key, Object data)
        {
            this.key = key;
            this.data = data;
        }
    }

    final static int MAX = 100;         // データの最大個数
    Entry[] table = new Entry[MAX];     // データを格納する配列
    int n = 0;                          // table に登録されているデータの個数

    /*
     * データを登録する
     * @param key キー
     * @param data キーkey に対応するデータ
     */
    public void add(int key, Object data)
    {
        if (n >= MAX) {
            throw new IllegalStateException("データの個数が多すぎます");
        }
        table[n++] = new Entry(key, data);
    }

    /*
     * キーkey に対応するデータを探す
     * @param key キー
     * @return キーkey に対応するデータ。キーが見つからなければnull を返す
     */
    public Object search(int key)
    {
        int i = 0;                        //（1）
        while (i < n) {                   //（2）
            if (table[i].key == key)      //（3）
                return (table[i].data);   //（4）見つかった
            i++;                          //（5）
        }
        return null;                      //（6）見つからなかった
    }
}
