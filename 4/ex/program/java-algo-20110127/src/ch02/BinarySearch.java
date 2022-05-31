public class BinarySearch {

    /*
     * テーブルのエントリ
     */
    static private class Entry {

        int key;        // 比較の対象となるキー
        Object data;    // それ以外の情報

        /**
         * エントリを生成する
         *
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
     * キーkey に対応するデータを探す
     *
     * @param key キー
     * @return キーkey に対応するデータ。キーが見つからなければnull を返す
     */
    public Object search(int key)
    {
        int low = 0;                            //（1）
        int high = n - 1;                       //（2）

        while (low <= high) {                   //（3）
            int middle = (low + high) / 2;      //（4）
            if (key == table[middle].key)       //（5）
                return table[middle].data;      //（6）見つかった
            else if (key < table[middle].key)   //（7）
                high = middle - 1;              //（8）
            else // key > table[middle].key である
                low = middle + 1;               //（9）
        }
        return null;                            //（10）見つからなかった
    }
}
