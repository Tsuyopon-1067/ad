/**
 * �o�������X�g�̃Z��
 */
class CellDouble
{
    CellDouble   prev;  // �O�̃Z���ւ̃����N
    CellDouble   next;  // ���̃Z���ւ̃����N
    Object       data;  // ���̃Z�������f�[�^

    /**
     * �Z���𐶐�����
     *
     * @param data  ���̃Z�������f�[�^
     */
    CellDouble(Object data)
    {
        prev = next = null;
        this.data   = data;
    }
}
