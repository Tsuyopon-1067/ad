public class BinarySearch extends MySearch {
    /**
     * メソッド
     * boolean insert(String key, String data)
     * データ挿入
     * MyData search(String key)
     * データ検索
     * MyData delete(String key)
     * データ削除
     */
    /**
     * スーパークラス
     * void resetCompareCount()
     *  compareCount = 0
     * void setCompareCount(int count)
     *  compareCount = count;
     * int getCompareCount()
     *  return compareCount;
     * void printData()
     *  全データ出力
     * int strcmp(String key1, String key2)
     *  C言語風
     */

    BinarySearch(int max) {
        super(max);
    }

    public boolean insert(String key, String data) {
        if (maxnum > datanum) { // 枠が余っているかを判定(デフォルト4096)
            MyData newData = new MyData(key, data);
            this.data[datanum] = newData; // 増えるまでは 0 ~ datanum-1 までのデータがあった
            datanum++; // データ数の更新
            /* 新規データを正しい位置まで移動させる */
            for (int i = datanum - 1; i > 0; i--) {
                // 下2行のどちらかをコメントアウトする．上:比較回数をカウントしつつ比較，下:通常の比較
                if (strcmp(this.data[i - 1].getKey(), this.data[i].getKey()) > 0) {
                    // if (this.data[i - 1].getKey().compareTo(this.data[i].getKey()) > 0) {
                    MyData tmp = this.data[i - 1]; // swap
                    this.data[i - 1] = this.data[i];
                    this.data[i] = tmp;
                } else {
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public MyData search(String key) {
        int index = searchIndex(key);
        if (index == -1) {
            return null;
        } else {
            return data[index];
        }
    }

    public MyData[] searchRange(String strL, String strR) {
        int indexL = searchIndex(strL) + 1;
        int indexR = searchIndex(strR);
        if (indexL == -1 || indexR == -1) {
            return null;
        } else {
            MyData[] res = new MyData[indexR - indexL];
            for (int i = indexL; i < indexR; i++) {
                res[i - indexL] = data[i];
            }
            return res;
        }
    }

    public int searchIndex(String key) {
        /* using strcmp(data[i].getKey(), key) == 0 */
        /* strcmp is in MySearch.java */

        // s1 < s2 -> - (ex):and, count
        // s1 > s2 -> + (ex):count, and
        int l, m, r;
        l = 0;
        r = datanum - 1;
        while (l <= r) {
            m = (l + r) / 2;
            int cmp = strcmp(data[m].getKey(), key);
            if (cmp == 0) {
                return m;
            } else if (cmp < 0) { // 左辺の方が辞書的に前
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }

    public MyData delete(String key) {
        int deleteDataIndex = searchIndex(key);
        if (deleteDataIndex == -1) {
            return null;
        }
        MyData deletedData = data[deleteDataIndex];
        for (int i = deleteDataIndex; i < datanum - 1; i++) {
            data[i] = data[i + 1];
        }
        datanum--;
        return deletedData;
    }
}
