/**
 * 位置
 */
public class Position
{
    private int x;      // X座標
    private int y;      // Y座標

    /*
     * 位置を生成する
     * @param x   X座標
     * @param y   Y座標
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /*
     * X方向にxDeltaだけ移動する
     * @param xDelta    X方向の移動量
     */
    public void moveX(int xDelta)
    {
        x += xDelta;
    }

    /*
     * Y方向にyDeltaだけ移動する
     * @param yDelta    Y方向の移動量
     */
    public void moveY(int yDelta)
    {
        y += yDelta;
    }

    /*
     * X方向にxDelta，Y方向にyDeltaだけ移動する
     * @param xDelta    X方向の移動量
     * @param yDelta    Y方向の移動量
     */
    public void moveXY(int xDelta, int yDelta)
    {
        x += xDelta;
        y += yDelta;
    }

    /*
     * X座標を得る
     * @return  X座標
     */
    public int getX()
    {
        return x;
    }

    /*
     * Y座標を得る
     * @return  Y座標
     */
    public int getY()
    {
        return y;
    }

    /*
     * 位置を表す文字列を返す
     * @return  位置を表す文字列
     */
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    /*
     * 位置が等しいかどうかを調べる
     *
     * @param o   比較の対象となる位置
     * @return    等しければtrue，等しくなければfalse を返す
     */
    public boolean equals(Object o)
    {
        // パラメータoが，Positionクラスであることを確認する。
        // Positionクラスでなければ，falseを返す
        if (! (o instanceof Position)) {
            return false;
        }

        // パラメータoをPosition型にキャストして，
        // 各フィールドの内容を比較する
        Position  pos = (Position)o;
        return this.x == pos.x && this.y == pos.y;
    }

    /*
     * ハッシュ値を求める
     *
     * @return    このオブジェクトのハッシュ値を返す
     */
    public int hashCode()
    {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }
}
