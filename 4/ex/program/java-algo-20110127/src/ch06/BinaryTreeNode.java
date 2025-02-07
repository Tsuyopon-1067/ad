/*
 * �񕪖�
 */
class BinaryTreeNode
{
    String label;               // ���̐߂̃��x��
    BinaryTreeNode   left;      // ��������
    BinaryTreeNode   right;     // �E������

    /**
     * �񕪖؂̐߂𐶐�����
     *
     * @param label  ���x��
     * @param left   ��������
     * @param right  �E������
     */
    BinaryTreeNode(String label, BinaryTreeNode left, BinaryTreeNode right)
    {
        this.left  = left;
        this.right = right;
        this.label = label;
    }

    /**
     * �񕪖؂��s���������łȂ���
     *
     * @param p  �Ȃ���ׂ��񕪖�
     */
    static void traversePreorder(BinaryTreeNode p)
    {
        if (p == null)  // �؂���Ȃ牽�����Ȃ�
            return;
        System.out.println("��" + p.label + "�ɗ������܂����B");
        traversePreorder(p.left);
        traversePreorder(p.right);
    }

    /**
     * �񕪖؂�ʂ肪�����łȂ���
     *
     * @param p  �Ȃ���ׂ��񕪖�
     */
    static void traverseInorder(BinaryTreeNode p)
    {
        if (p == null)  // �؂���Ȃ牽�����Ȃ�
            return;
        traverseInorder(p.left);
        System.out.println("��" + p.label + "�ɗ������܂����B");
        traverseInorder(p.right);
    }

    /**
     * �񕪖؂��A�肪�����łȂ���
     *
     * @param p  �Ȃ���ׂ��񕪖�
     */
    static void traversePostorder(BinaryTreeNode p)
    {
        if (p == null)  // �؂���Ȃ牽�����Ȃ�
            return;
        traversePostorder(p.left);
        traversePostorder(p.right);
        System.out.println("��" + p.label + "�ɗ������܂����B");
    }

    /**
     * �e�X�g�p�̃��C�����[�`��
     *
     * Fig.6.7�̓񕪖؂����o���Ă���C������s���������C�ʂ肪�����C
     * �A�肪�����łȂ���
     */
    public static void main(String args[])
    {
        // Fig.6.7�̓񕪖؂����
        BinaryTreeNode tree =
            new BinaryTreeNode(
                    "a",
                    new BinaryTreeNode("b",
                                       new BinaryTreeNode("c", null, null),
                                       null),
                    new BinaryTreeNode("d", null, null));

        // �s���������ɂȂ���
        System.out.println("�s��������");
        traversePreorder(tree);

        // �ʂ肪�����ɂȂ���
        System.out.println("�ʂ肪����");
        traverseInorder(tree);

        // �A�肪�����ɂȂ���
        System.out.println("�A�肪����");
        traversePostorder(tree);
    }
}
