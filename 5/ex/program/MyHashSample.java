// サンプルプログラム  MyHashSample.java
class MyHashSample {
    int[] table; // 仮のハッシュテーブル

    MyHashSample(int size) {
        table = new int[size];
    }

    public int hash(String key) {
        int r = 0;
        // 文字列 key のいち文字ずつのアスキーコードの合計を r に保存
        for (char c : key.toCharArray()) r += (int) c;
        return r % table.length; // (配列 table の長さを示すフィールド)
    }
}