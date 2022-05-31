/**
 * ロボット
 */
public class Robot
{
    private Position position; // 現在の位置
    private String   name;     // 名前

    /*
     * ロボットを生成する
     * @param position  ロボットがいる位置
     * @param name      ロボットの名前
     */
    public Robot(Position position, String name)
    {
        this.position = position;
        this.name     = name;
    }

    /*
     * X方向にxDeltaだけ移動する
     * @param xDelta    X方向の移動量
     */
    public void moveX(int xDelta)
    {
        position.moveX(xDelta);
    }

    /*
     * Y方向にyDeltaだけ移動する
     * @param yDelta    Y方向の移動量
     */
    public void moveY(int yDelta)
    {
        position.moveY(yDelta);
    }

    /*
     * X方向にxDelta，Y方向にyDeltaだけ移動する
     * @param xDelta    X方向の移動量
     * @param yDelta    Y方向の移動量
     */
    public void moveXY(int xDelta, int yDelta)
    {
        position.moveXY(xDelta, yDelta);
    }

    /*
     * ロボットの名前を得る
     * @return  ロボットの名前
     */
    public String getName()
    {
        return name;
    }

    /*
     * ロボットの現在の位置を得る
     * @return  ロボットの位置
     */
    public Position getPosition()
    {
        return position;
    }

    /*
     * ロボットの情報を表す文字列を返す
     * @return  ロボットの情報
     */
    public String toString()
    {
        return "name:" + name + " position:" + position;
    }
}
