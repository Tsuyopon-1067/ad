/**
 * 双方向リストのセル
 */
class CellDouble
{
    CellDouble   prev;  // 前のセルへのリンク
    CellDouble   next;  // 次のセルへのリンク
    Object       data;  // このセルがもつデータ

    /**
     * セルを生成する
     *
     * @param data  このセルがもつデータ
     */
    CellDouble(Object data)
    {
        prev = next = null;
        this.data   = data;
    }
}
