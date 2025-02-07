/*
 * �z��ɂ��X�^�b�N�̎���(�W�F�l���b�N��)
 */
import java.util.*;

public class GenericStack<E>                                    // @@@
{
    private E  stack[];         // �X�^�b�N�{��                 // @@@
    private int  stackSize;     // �X�^�b�N�̑傫��
    private int  sp;            // �X�^�b�N�|�C���^

    private static final int  DEFAULT_STACK_SIZE = 100; // �f�t�H���g�̃X�^�b�N�̑傫��

    /**
     * �X�^�b�N�𐶐�����i�傫����DEFAULT_STACK_SIZE�j
     */
    public GenericStack()                                       // @@@
    {
        this(DEFAULT_STACK_SIZE);
    }

    /**
     * �傫�����w�肵�ăX�^�b�N�𐶐�����
     *
     * @param size  �X�^�b�N�̑傫��
     */
    @SuppressWarnings("unchecked")                              // @@@ Added
    public GenericStack(int size)                               // @@@
    {
        // �z��stack�ɂ́Cpush���\�b�h�ɂ����E�N���X           // @@@ Added
        // �̃I�u�W�F�N�g�݂̂��i�[�����̂ŁC���̃L���X�g��   // @@@ Added
        // ���S�ł���B                                         // @@@ Added
        stack = (E[]) new Object[size];                         // @@@
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
    public void push(E x)                                       // @@@
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
    public E pop()                                              // @@@
    {
        if (sp <= 0) {
            throw new EmptyStackException();
        }
        E  value = stack[--sp];                                 // @@@
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
        String s = "GenericStack=[";                            // @@@
        for (int i = 0; i < sp; i++) {
            s = s + stack[i];
            if (i < sp - 1)
                s = s + ",";
        }
        s = s + "]";
        return s;
    }
}
