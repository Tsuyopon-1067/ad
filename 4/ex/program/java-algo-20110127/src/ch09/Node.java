/**
 * �񕪒T���؂̐�
 */
class Node
{
    Integer data;           // ���̐߂̃��x��
    Node    left;           // ��������
    Node    right;          // �E������

    /**
     * �񕪖؂̐߂𐶐�����
     *
     * @param data     ���x��
     */
    Node(Integer data)
    {
        left       = null;
        right      = null;
        this.data  = data;
    }
}
