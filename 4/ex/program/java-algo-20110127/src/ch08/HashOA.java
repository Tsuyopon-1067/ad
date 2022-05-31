/**
 * オープンアドレス法によるハッシュ表
 */
public class HashOA
{
    /**
     * ハッシュ表のエントリ（バケット）
     */
    private static class Bucket
    {
        MyKey  key;     // キー
        Object data;    // 対応するデータ

        /**
         * バケットを生成する
         *
         * @param key   キー
         * @param data  データ
         */
        private Bucket(MyKey key, Object data)
        {
            this.key  = key;
            this.data = data;
        }
    }

    Bucket[] table;     // ハッシュ表の実体
    int    bucketSize;  // バケットの個数
    int    nElements;   // 登録されているデータの個数

    // 削除済みであることを示す特別なキーの値
    static final MyKey DELETED = new MyKey(null);

    // 空きであることを示す特別なキーの値
    static final MyKey EMPTY   = new MyKey(null);

    // デフォルトのハッシュ表の大きさ(素数が望ましい)
    static final int  DEFAULT_BUCKET_SIZE = 53;

    /**
     * ハッシュ表を生成する（大きさはDEFAULT_BUCKET_SIZE）
     */
    public HashOA()
    {
        this(DEFAULT_BUCKET_SIZE);
    }

    /**
     * ハッシュ表を生成する
     *
     * @param bucketSize  ハッシュ表の大きさ
     */
    public HashOA(int bucketSize)
    {
        // ハッシュ表の実体の配列を割り当てて，すべての要素の
        // キーをEMPTYで初期化しておく
        table = new Bucket[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            table[i] = new Bucket(EMPTY, null);
        }

        // ハッシュ表のバケットの個数を記録しておく
        this.bucketSize = bucketSize;

        // 要求の個数を0にしておく
        nElements = 0;
    }

    /**
     * ハッシュ値を求める
     * キーとなるオブジェクトのhashCodeメソッドが返した値を，
     * バケットの個数で割った余りを返す
     *
     * @param key  キー
     * @return 与えられたキーに対するハッシュ値
     */
    private int hash(MyKey key)
    {
        return key.hashCode() % bucketSize;
    }

    /**
     * 再ハッシュを行う
     *
     * @param h  ハッシュ値
     * @return   再ハッシュで求めたハッシュ値
     */
    private int rehash(int h)
    {
        return (h + 1) % bucketSize;
    }

    /**
     * ハッシュ表を探索する
     *
     * @param key  探すべきキー
     * @return データが見つかればそれを返す。
     *         見つからなければnullを返す
     */
    public Object find(MyKey key)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY) {
            if (k != DELETED && key.equals(k)) {
                return table[h].data;
            }
            if (++count > bucketSize) {
                return null;
            }
            h = rehash(h);
        }
        return null;
    }

    /**
     * ハッシュ表にデータを挿入する
     *
     * @param  key  キー
     * @param  data 登録するデータ
     * @return 登録に成功すればtrue，失敗（すでに同じキーをもつ
     *         データがある）したらfalseを返す
     */
    public boolean insert(MyKey key, Object data)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY && k != DELETED) {
            if (key.equals(k)) {
                return false;
            }
            if (++count > bucketSize) {
                throw new IllegalStateException(
                        "ハッシュ表にこれ以上データを登録できません。");
            }
            h = rehash(h);
        }
        table[h].key  = key;
        table[h].data = data;
        nElements++;
        return true;
    }

    /**
     * ハッシュ表からデータを削除する
     *
     * @param key  削除すべきデータのキー
     * @return 削除に成功したらtrue，データが見つからなければfalseを返す
     */
    public boolean delete(MyKey key)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY) {
            if (k != DELETED && key.equals(k)) {
                table[h].key = DELETED;
                table[h].data = null;
                nElements--;
                return true;
            }
            if (++count > bucketSize) {
                return false;
            }
            h = rehash(h);
        }
        return false;
    }

    /**
     * ハッシュ表の内容を表す文字列を返す
     *
     * @return ハッシュ表の内容を表す文字列
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            s += "バケット " + i + ": ";
            MyKey k = table[i].key;
            if (k == EMPTY) {
                s += "空\n";
            } else if (k == DELETED) {
                s += "削除済み\n";
            } else {
                s += "key=[" + k + "] data=[" + table[i].data + "]\n";
            }
        }
        // 最後に登録されている要素の個数を追加する
        s += "要素の個数:" + nElements + "\n";
        return s;
    }
}
