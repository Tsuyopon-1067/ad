/*
 * �񕪒T����
 */
class BinarySearchTree
{
    private Node  root; // �񕪒T���؂̍�

    /**
     * �񕪒T���؂𐶐�����
     */
    public BinarySearchTree()
    {
        root = null;
    }

    //---------------------------------------------
    // �����Ń��\�b�h���`����
    //---------------------------------------------

// list 9.3  search���\�b�h
    /**
     * �񕪒T���؂���L�[key�����߂�T������
     *
     * @param key  �T���o���L�[
     * @return  �L�[key���������߂�������΂����Ԃ��C
     *          ������Ȃꂯ��null��Ԃ�
     */
    public Node search(Integer key)
    {
        Node p = root;                  // �܂����ɒ��ڂ���
        while (p != null) {             // ���ڂ��Ă���߂�������葱����
                                        // ���ڂ��Ă���߂ƃL�[���r����
            int result = key.compareTo(p.data);
            if (result == 0) {          // �߂ƃL�[����������΁C
                return p;               //      ��������
            } else if (result < 0) {    // �L�[�̂ق�����������΁C
                p = p.left;             //      �������؂ɐi��
            } else {                    // �L�[�̂ق����傫����΁C
                p = p.right;            //      �E�����؂ɐi��
            }
        }
        return null;                    // �T���Ɏ��s����
    }

// list 9.4  insert���\�b�h
    /**
     * �񕪒T���؂ɃL�[key�����߂�}������
     *
     * @param key  �}������L�[
     * @return  �}�������L�[�����߂ւ̃����N��Ԃ�
     *          ���łɓo�^����Ă����null��Ԃ�
     */
    public Node insert(Integer key)
    {
        Node p = root;                  // �܂����ɒ��ڂ���
        Node parent = null;             // parent�͌��ݒ��ڂ��Ă���߂̐e���w��
        boolean  isLeftChild = false;   // p��parent�̍��̎q�Ȃ�true�C
                                        // �E�̎q�Ȃ�false

        while (p != null) {             // ���ڂ��Ă���߂�������葱����
                                        // ���ڂ��Ă���߂ƃL�[���r����
            int result = key.compareTo(p.data);
            if (result == 0) {          // �߂ƃL�[����������΁C
                return null;            //      ���łɓo�^����Ă���
            } else if (result < 0) {    // �L�[�̂ق�����������΁C
                parent = p;             //      �������؂ɐi��
                isLeftChild = true;
                p = p.left;
            } else {                    // �L�[�̂ق����傫����΁C
                parent = p;             //      �E�����؂ɐi��
                isLeftChild = false;
                p = p.right;
            }
        }

        // �V�����߂�K�؂ȏꏊ�ɑ}������
        Node newNode = new Node(key);   // �V�����߂����蓖�Ă�
        if (parent == null) {
            root = newNode;             // �V�����߂͍��ɂȂ�
        } else if (isLeftChild) {
            parent.left = newNode;      // ��parent�̍��̎q�ɂȂ�
        } else {
            parent.right = newNode;     // ��parent�̉E�̎q�ɂȂ�
        }

        return newNode;
    }

// list 9.5  delete���\�b�h
    /**
     * �񕪒T���؂���L�[key�����߂��폜����
     *
     * @param key  �폜����L�[
     * @return  �폜�ɐ��������true�C�v�f�����݂��Ȃ����false��Ԃ�
     */
    public boolean delete(Integer key)
    {
        Node p = root;                  // �܂����ɒ��ڂ���
        Node parent = null;             // parent�͌��ݒ��ڂ��Ă���߂̐e���w��
        boolean  isLeftChild = false;   // p��parent�̍��̎q�Ȃ�true�C
                                        // �E�̎q�Ȃ�false

        while (p != null) {             // �폜�Ώۂ�T��
            // ���ڂ��Ă���߂ƃL�[���r����
            int result = key.compareTo(p.data);
            if (result == 0) {          // ��������
                if (p.left == null && p.right == null) { // �t�ł���
                    // �t���폜����
                    if (parent == null) {
                        root = null;
                    } else if (isLeftChild) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else if (p.left == null) {            // �E�̎q�݂̂�����
                    // �E�̎q��e�̎q�ɂ���
                    if (parent == null) {
                        root = p.right;
                    } else if (isLeftChild) {
                        parent.left = p.right;
                    } else {
                        parent.right = p.right;
                    }
                } else if (p.right == null) {           // ���̎q�݂̂�����
                    // ���̎q��e�̎q�ɂ���
                    if (parent == null) {
                        root = p.left;
                    } else if (isLeftChild) {
                        parent.left = p.left;
                    } else {
                        parent.right = p.left;
                    }
                } else {                                // ���E2�̎q������
                    // �E�����؂̍ŏ��̗v�f����菜���ĕϐ�x�ɓ����
                    Node x = deleteMin(p, p.right);

                    // ���ڂ��Ă���߂ɍŏ��̗v�fx������
                    if (parent == null) {
                        root = x;               // (1)
                    } else if (isLeftChild) {
                        parent.left = x;        // (1)
                    } else {
                        parent.right = x;       // (1)
                    }
                    x.right = p.right;          // (2)
                    x.left  = p.left;           // (3)
                }
                return true;            // �폜����������

            } else if (result < 0) {    // �L�[�̂ق�����������΁C
                parent = p;             //      �������؂ɐi��
                isLeftChild = true;
                p = p.left;
            } else {                    // �L�[�̂ق����傫����΁C
                parent = p;             //      �E�����؂ɐi��
                isLeftChild = false;
                p = p.right;
            }
        }

        // �폜�Ώۂ�������Ȃ�����
        return false;
    }

// list 9.6  deletemin���\�b�h
    /**
     * �񕪒T���؂���ŏ��̗v�f���폜����
     *
     * @param  p  �񕪒T����
     * @param  parent  p�̐e�̐�
     * @return    �폜������
     */
    private static Node deleteMin(Node parent, Node p)
    {
        boolean  isLeftChild = false;   // p��parent�̍��̎q�Ȃ�true�C
                                        // �E�̎q�Ȃ�false�B
                                        // ���\�b�h���Ăяo���ꂽ���_�ł́C
                                        // p��parent�̉E�̎q�Ȃ̂ŁC
                                        // ��������false�ł���B
        while (p.left != null) {        // �ŏ��̗v�f��������
            parent = p;
            isLeftChild = true;
            p = p.left;
        }

        if (isLeftChild) {              // �ŏ��̗v�f����菜��
            parent.left = p.right;
        } else {
            parent.right = p.right;
        }
        return p;                       // �ŏ��̗v�f��Ԃ�
    }

// list 9.7  showAll���\�b�h
    /**
     * �񕪒T���؂�ʂ肪�����ɂȂ���
     */
    private static void inorder(Node p)
    {
        if (p == null) {
            return;
        } else {
            inorder(p.left);
            System.out.println(p.data);
            inorder(p.right);
        }
    }

    /**
     * �񕪒T���؂̑S�v�f�������ɕ\������
     */
    public void showAll()
    {
        inorder(root);
    }

// ���̑�:  toString���\�b�h
    private static String toStringAux(Node p)
    {
        if (p == null)
            return "-";

        return "(" + p.data + " " + toStringAux(p.left) + " "
            + toStringAux(p.right) + ")";
    }

    public String toString()
    {
        return toStringAux(root);
    }

}
