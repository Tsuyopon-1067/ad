/**
 * �A�����X�g�iMyLinkedList�N���X�j�̃C�e���[�^
 */
import java.util.*;

public class MyLinkedListIterator implements Iterator
{
    private Cell p;     // ���ݒ��ڂ��Ă���Z���������J�[�\��

    /**
     * �C�e���[�^�𐶐�����
     *
     * @param list  �C�e���[�^�̑ΏۂƂȂ�MyLinkedList�I�u�W�F�N�g
     */
    public MyLinkedListIterator(MyLinkedList list)
    {
        // ���ړ_�����X�g�̓��ɐݒ肷��
        p = list.header;
    }

    /**
     * ���̗v�f������Ȃ�true��Ԃ�
     *
     * @return ���̗v�f�������true�C�Ȃ����false��Ԃ�
     */
    public boolean hasNext()
    {
        return p.next != null;
    }

    /**
     * ���̗v�f��Ԃ�
     *
     * @return ���̗v�f�����l
     */
    public Object next()
    {
        // ���̗v�f�����݂��Ȃ���Η�ONoSuchElementException���X���[����
        if (p.next == null) {
            throw new NoSuchElementException();
        }

        // ���ړ_�����̗v�f�Ɉړ����āC���̃f�[�^��l�Ƃ��ĕԂ�
        p = p.next;
        return p.data;
    }

    /**
     * ���O��next���Ԃ����v�f���폜����
     */
    public void remove()
    {
        // ���̃��\�b�h�͖������ł���B
        // ��OUnsupportedOperationException�𓊂���
        throw new UnsupportedOperationException();
    }

    /**
     * �e�X�g�p�̃��C�����[�`��
     */
    public static void main(String args[])
    {
        // �A�����X�glist���쐬���āC�v�f20, 15, 18, 37, 3��ǉ�����
        MyLinkedList list = new MyLinkedList();
        list.insert(20);        list.insert(15);        list.insert(18);
        list.insert(37);        list.insert(3);
        System.out.println(list);

        // �C�e���[�^iter�𗘗p���āC���ׂĂ̗v�f��\������
        System.out.println("----<�C�e���[�^>--------");
        Iterator iter = list.iterator();
        int count = 1;
        while (iter.hasNext()) {
            System.out.println(count++ + "�Ԗڂ̗v�f:" + iter.next());
        }

        // �g��for��(for-each��) �𗘗p���āC���ׂĂ̗v�f��\������(Java 5�ȍ~)
        System.out.println("----<�g��for��>--------");
        count = 1;
        for (Object o: list) {
            System.out.println(count++ + "�Ԗڂ̗v�f:" + o);
        }
    }
}
