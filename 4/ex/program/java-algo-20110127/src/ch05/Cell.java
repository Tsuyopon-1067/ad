/**
 * �A�����X�g�̃Z��
 */
class Cell
{
    Cell   next;        // ���̃Z���ւ̃����N
    Object data;        // ���̃Z�������f�[�^

    /**
     * �Z���𐶐�����
     *
     * @param data  ���̃Z�������f�[�^
     */
    Cell(Object data)
    {
        next      = null;
        this.data = data;
    }
}
