/**
 * �o�������X�g
 */
import java.util.*;

public class MyDoublyLinkedList
{
    final CellDouble head;    // ���X�g�̓��ւ̃����N

    /**
     * �o�������X�g�𐶐�����B
     * ���������o�������X�g�͋�ɂȂ��Ă���
     */
    public MyDoublyLinkedList()
    {
        // ���X�g�̓������蓖�Ă�
        head = new CellDouble("**List Head**");

        // ���X�g�̓���prev��next���������g���w���悤�ɂ���
        head.prev = head.next = head;
    }

    /**
     * �o�������X�g�̃Z��p�̒���Ƀf�[�^data��}������
     *
     * @param p     ���̃Z���̒���Ƀf�[�^��}������
     * @param data  �}������f�[�^
     */
    private void insertAfter(CellDouble p, Object data)
    {
        CellDouble x = new CellDouble(data);
        x.prev = p;
        x.next = p.next;
        p.next.prev = x;
        p.next = x;
    }

    /**
     * �o�������X�g�̐擪�Ƀf�[�^data��}������
     *
     * @param data  �}������f�[�^
     */
    public void insertFirst(Object data)
    {
        // ���X�g�̓��̎��ɑ}������
        insertAfter(head, data);
    }

    /**
     * �o�������X�g�̖����Ƀf�[�^x��}������
     *
     * @param data  �}������f�[�^
     */
    public void insertLast(Object data)
    {
        // �Ō�̗v�f�̎��ɑ}������
        insertAfter(head.prev, data);
    }

    /**
     * �w�肳�ꂽ�Z�����폜����
     */
    private void removeCell(CellDouble p)
    {
        p.prev.next = p.next;
        p.next.prev = p.prev;
    }
    
    /**
     * �o�������X�g�̐擪�̃f�[�^���폜����
     *
     * @return �폜�����v�f��Ԃ��B
     *         �������v�f���Ȃ���Η�ONoSuchElementException���X���[����
     */
    public Object removeFirst()
    {
        // �v�f���Ȃ���Η�ONoSuchElementException���X���[����
        if (isEmpty()) {
            throw new NoSuchElementException("�o�������X�g����ł��B");
        }

        // �ŏ��̃Z�����폜���āC�����ɓ����Ă����l��Ԃ�
        CellDouble cell = head.next;
        removeCell(cell);
        return cell.data;
    }

    /**
     * �o�������X�g�̖����̃f�[�^���폜����
     *
     * @return �폜�����v�f��Ԃ��B
     *         �������v�f���Ȃ���Η�ONoSuchElementException���X���[����
     */
    public Object removeLast()
    {
        // �v�f���Ȃ���Η�ONoSuchElementException���X���[����
        if (isEmpty()) {
            throw new NoSuchElementException("�o�������X�g����ł��B");
        }

        // �Ō�̃Z�����폜���āC�����ɓ����Ă����l��Ԃ�
        CellDouble cell = head.prev;
        removeCell(cell);
        return cell.data;
    }

    /**
     * �o�������X�g���󂩂ǂ����`�F�b�N����
     *
     * @return ��Ȃ��true�C��łȂ����false��Ԃ�
     */
    public boolean isEmpty()
    {
        return head.next == head;
    }

    /**
     * �o�������X�g��\��������\����Ԃ�
     *
     * @return ���̑o�������X�g�̓��e��\��������
     */
    public String toString()
    {
        String s = "[";
        for (CellDouble p = head.next; p != head; p = p.next) {
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
        MyDoublyLinkedList list = new MyDoublyLinkedList();

        // �v�f��}������
        System.out.print("�������(�󃊃X�g)"); System.out.println(list);
        list.insertFirst("a");
        System.out.print("a��擪�ɑ}��  ");    System.out.println(list);
        list.insertLast("b");
        System.out.print("b�𖖔��ɑ}��  ");    System.out.println(list);
        list.insertFirst("c");
        System.out.print("c��擪�ɑ}��  ");    System.out.println(list);
        list.insertFirst("d");
        System.out.print("d��擪�ɑ}��  ");    System.out.println(list);
        list.insertLast("e");
        System.out.print("e�𖖔��ɑ}��  ");    System.out.println(list);

        // �v�f���폜����
        // �ϐ�i�𗘗p���āC�擪�Ɩ���������݂ɗv�f���폜����
        int  i = 0;

        while(!list.isEmpty()) {
            if (i++ % 2 == 0) {
                System.out.print(list.removeFirst() + "��擪������o����  ");
            } else {
                System.out.print(list.removeLast() +  "�𖖔�������o����  ");
            }
            System.out.println(list);
        }
    }
}
