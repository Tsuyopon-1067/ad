/**
 * �A�����X�g�̃Z��
 */
class Cell
{
    Cell   next;        // ���̃Z���ւ̃����N
    int    data;        // ���̃Z�������f�[�^

    /**
     * �Z���𐶐�����
     *
     * @param data  ���̃Z�������f�[�^
     */
    Cell(int data)
    {
        next      = null;
        this.data = data;
    }
}
