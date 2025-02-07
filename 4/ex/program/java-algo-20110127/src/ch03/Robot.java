/**
 * ���{�b�g
 */
public class Robot
{
    private Position position; // ���݂̈ʒu
    private String   name;     // ���O

    /*
     * ���{�b�g�𐶐�����
     * @param position  ���{�b�g������ʒu
     * @param name      ���{�b�g�̖��O
     */
    public Robot(Position position, String name)
    {
        this.position = position;
        this.name     = name;
    }

    /*
     * X������xDelta�����ړ�����
     * @param xDelta    X�����̈ړ���
     */
    public void moveX(int xDelta)
    {
        position.moveX(xDelta);
    }

    /*
     * Y������yDelta�����ړ�����
     * @param yDelta    Y�����̈ړ���
     */
    public void moveY(int yDelta)
    {
        position.moveY(yDelta);
    }

    /*
     * X������xDelta�CY������yDelta�����ړ�����
     * @param xDelta    X�����̈ړ���
     * @param yDelta    Y�����̈ړ���
     */
    public void moveXY(int xDelta, int yDelta)
    {
        position.moveXY(xDelta, yDelta);
    }

    /*
     * ���{�b�g�̖��O�𓾂�
     * @return  ���{�b�g�̖��O
     */
    public String getName()
    {
        return name;
    }

    /*
     * ���{�b�g�̌��݂̈ʒu�𓾂�
     * @return  ���{�b�g�̈ʒu
     */
    public Position getPosition()
    {
        return position;
    }

    /*
     * ���{�b�g�̏���\���������Ԃ�
     * @return  ���{�b�g�̏��
     */
    public String toString()
    {
        return "name:" + name + " position:" + position;
    }
}
