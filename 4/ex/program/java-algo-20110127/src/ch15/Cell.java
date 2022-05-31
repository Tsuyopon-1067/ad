/**
 * 連結リストのセル
 */
class Cell
{
    Cell   next;        // 次のセルへのリンク
    int    data;        // このセルがもつデータ

    /**
     * セルを生成する
     *
     * @param data  このセルがもつデータ
     */
    Cell(int data)
    {
        next      = null;
        this.data = data;
    }
}
