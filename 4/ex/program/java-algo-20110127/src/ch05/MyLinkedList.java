/**
 * �A�����X�g
 * ��ɗv�f�i�����j�������ɕ��Ԃ悤�ɂ���
 */
public class MyLinkedList implements Iterable
{
    final Cell header;        // ���X�g�̓��ւ̃����N

    /**
     * �A�����X�g�𐶐�����B
     * ���������A�����X�g�͋�ɂȂ��Ă���
     */
    public MyLinkedList()
    {
        header = new Cell("**List Head**");     // ���X�g�̓����쐬����
    }

    /**
     * �A�����X�g�ɗv�fnum��}������B
     * �v�f�́C�A�����X�g�������ɂȂ�悤�ȏꏊ�ɑ}�������
     *
     * @param num  �}������v�f�i�����j
     */
    public void insert(int num)
    {
        // �}�����ׂ��ꏊ��T��
        Cell p = header.next;
        Cell q = header;
        while (p != null && num > (Integer)p.data) {  // �I�[�g�A���{�N�V���O
            q = p;
            p = p.next;
        }

        // �Z����}������
        Cell newCell = new Cell(num);                 // �I�[�g�{�N�V���O
        newCell.next = p;
        q.next = newCell;
    }

    /**
     * �C�e���[�^�𓾂�
     *
     * @return ���̘A�����X�g�ɑ΂���C�e���[�^
     */
    public MyLinkedListIterator iterator()
    {
        return new MyLinkedListIterator(this);
    }

    /**
     * �A�����X�g��\��������\����Ԃ�
     *
     * @return ���̘A�����X�g�̓��e��\��������
     */
    public String toString()
    {
        String s = "[";
        for (Cell p = header.next; p != null; p = p.next) {
            s += p.data + " ";
        }
        s += "]";
        return s;
    }

    /**
     * �e�X�g�p�̃��C�����[�`��
     */
    public static void main(String args[])
    {
        MyLinkedList list = new MyLinkedList();

        System.out.println(list);
        list.insert(5);         System.out.println(list);
        list.insert(7);         System.out.println(list);
        list.insert(2);         System.out.println(list);
        list.insert(12);        System.out.println(list);
        list.insert(4);         System.out.println(list);
    }
}
