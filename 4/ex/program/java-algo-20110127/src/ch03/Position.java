/**
 * �ʒu
 */
public class Position
{
    private int x;      // X���W
    private int y;      // Y���W

    /*
     * �ʒu�𐶐�����
     * @param x   X���W
     * @param y   Y���W
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /*
     * X������xDelta�����ړ�����
     * @param xDelta    X�����̈ړ���
     */
    public void moveX(int xDelta)
    {
        x += xDelta;
    }

    /*
     * Y������yDelta�����ړ�����
     * @param yDelta    Y�����̈ړ���
     */
    public void moveY(int yDelta)
    {
        y += yDelta;
    }

    /*
     * X������xDelta�CY������yDelta�����ړ�����
     * @param xDelta    X�����̈ړ���
     * @param yDelta    Y�����̈ړ���
     */
    public void moveXY(int xDelta, int yDelta)
    {
        x += xDelta;
        y += yDelta;
    }

    /*
     * X���W�𓾂�
     * @return  X���W
     */
    public int getX()
    {
        return x;
    }

    /*
     * Y���W�𓾂�
     * @return  Y���W
     */
    public int getY()
    {
        return y;
    }

    /*
     * �ʒu��\���������Ԃ�
     * @return  �ʒu��\��������
     */
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    /*
     * �ʒu�����������ǂ����𒲂ׂ�
     *
     * @param o   ��r�̑ΏۂƂȂ�ʒu
     * @return    ���������true�C�������Ȃ����false ��Ԃ�
     */
    public boolean equals(Object o)
    {
        // �p�����[�^o���CPosition�N���X�ł��邱�Ƃ��m�F����B
        // Position�N���X�łȂ���΁Cfalse��Ԃ�
        if (! (o instanceof Position)) {
            return false;
        }

        // �p�����[�^o��Position�^�ɃL���X�g���āC
        // �e�t�B�[���h�̓��e���r����
        Position  pos = (Position)o;
        return this.x == pos.x && this.y == pos.y;
    }

    /*
     * �n�b�V���l�����߂�
     *
     * @return    ���̃I�u�W�F�N�g�̃n�b�V���l��Ԃ�
     */
    public int hashCode()
    {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }
}
