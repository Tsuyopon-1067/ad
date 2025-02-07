/*
 * �z��ɂ��X�^�b�N�̎���
 */
import java.util.*;

public class MyStack
{
    private Object  stack[];    // �X�^�b�N�{��
    private int  stackSize;     // �X�^�b�N�̑傫��
    private int  sp;            // �X�^�b�N�|�C���^

    private static final int  DEFAULT_STACK_SIZE = 100; // �f�t�H���g�̃X�^�b�N�̑傫��

    /**
     * �X�^�b�N�𐶐�����i�傫����DEFAULT_STACK_SIZE�j
     */
    public MyStack()
    {
        this(DEFAULT_STACK_SIZE);
    }

    /**
     * �傫�����w�肵�ăX�^�b�N�𐶐�����
     *
     * @param size  �X�^�b�N�̑傫��
     */
    public MyStack(int size)
    {
        stack = new Object[size];
        stackSize = size;
        sp = 0;
    }

    /*
     * �X�^�b�N�̒��g���̂Ăċ�̏�Ԃɂ���
     */
    public void clear()
    {
        Arrays.fill(stack, 0, sp, null);   // �X�^�b�N��null�ŃN���A����
        sp = 0;                            // �X�^�b�N�|�C���^��0�ɂ���
    }

    /*
     * �X�^�b�N�Ƀf�[�^��ς�
     *
     * @param x  �ςރf�[�^
     */
    public void push(Object x)
    {
        if (sp >= stackSize) {
            throw new IllegalStateException("Stack overflow");
        }
        stack[sp++] = x;
    }

    /*
     * �X�^�b�N����f�[�^���~�낷
     *
     * @return  �X�^�b�N����~�낵���f�[�^
     */
    public Object pop()
    {
        if (sp <= 0) {
            throw new EmptyStackException();
        }
        Object  value = stack[--sp];
        stack[sp] = null;               // �󂢂��v�f��null�ŃN���A����
        return value;
    }

    /*
     * �X�^�b�N���󂩂ǂ����𒲂ׂ�
     *
     * @return ��Ȃ��true�C��łȂ����false��Ԃ�
     */
    public boolean isEmpty()
    {
        return sp == 0;
    }

    /*
     * �X�^�b�N�̓��e��\���������Ԃ�
     *
     * @return �X�^�b�N�̓��e��\��������
     */
    public String toString()
    {
        String s = "MyStack=[";
        for (int i = 0; i < sp; i++) {
            s = s + stack[i];
            if (i < sp - 1)
                s = s + ",";
        }
        s = s + "]";
        return s;
    }

    /*
     * �e�X�g�p���C�����[�`��
     */
    public static void main(String args[])
    {
        MyStack stack = new MyStack();

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
