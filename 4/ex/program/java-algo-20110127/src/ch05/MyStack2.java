/*
 * �o�������X�g�ɂ��X�^�b�N�̎���
 */
import java.util.*;

public class MyStack2
{
    private MyDoublyLinkedList  stack;  // �X�^�b�N�̎��̂�\���o�������X�g
    private int  nElements;             // �X�^�b�N�ɓ����Ă���v�f�̌�

    /**
     * �X�^�b�N�𐶐�����
     */
    public MyStack2()
    {
        // �X�^�b�N�̎��̂̑o�������X�g�����蓖�Ă�
        stack = new MyDoublyLinkedList();

        // �v�f�̌���0�ɂ���
        nElements = 0;
    }

    /*
     * �X�^�b�N�̒��g���̂Ăċ�̏�Ԃɂ���
     */
    public void clear()
    {
        // �o�������X�g����蒼��
        stack = new MyDoublyLinkedList();
        
        // �v�f�̌���0�ɂ���
        nElements = 0;
    }

    /*
     * �X�^�b�N�Ƀf�[�^��ς�
     *
     * @param x  �ςރf�[�^
     */
    public void push(Object x)
    {
        stack.insertLast(x);
        nElements++;
    }

    /*
     * �X�^�b�N����f�[�^���~�낷
     *
     * @return  �X�^�b�N����~�낵���f�[�^
     */
    public Object pop()
    {
        if (nElements-- <= 0) {
            throw new EmptyStackException();
        }
        return stack.removeLast();
    }

    /*
     * �X�^�b�N���󂩂ǂ����𒲂ׂ�
     *
     * @return ��Ȃ��true�C��łȂ����false��Ԃ�
     */
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    /*
     * �X�^�b�N�̓��e��\���������Ԃ�
     *
     * @return �X�^�b�N�̓��e��\��������
     */
    public String toString()
    {
        return "�v�f��=" + nElements + "  " + stack.toString();
    }

    /*
     * �e�X�g�p���C�����[�`��
     */
    public static void main(String args[])
    {
        MyStack2 stack = new MyStack2();

        stack.push("a");  stack.push("b");  stack.push("c");
        System.out.println(stack);
        System.out.println("pop:" + stack.pop());
        System.out.println(stack);
        stack.push("d");  stack.push("e");  stack.push("f");
        System.out.println(stack);
        while (!stack.isEmpty()) {
                System.out.println("pop:" + stack.pop());
        }
        System.out.println("DONE! " + stack);
    }
}
