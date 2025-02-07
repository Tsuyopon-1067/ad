/*
 * �z��ɂ��҂��s��̎���
 */
import java.util.*;

public class MyQueue
{
    private Object  queue[];    // �҂��s��{��
    private int  queueSize;     // �҂��s��̑傫��
    private int  front;         // �҂��s��̐擪
    private int  rear;          // �҂��s��̖���
                                // �i���ۂɂ́A�����̎��̗v�f���w���j

    // �f�t�H���g�̑҂��s��̑傫��
    private static final int  DEFAULT_QUEUE_SIZE = 100;

    /**
     * �҂��s��𐶐�����i�傫����DEFAULT_QUEUE_SIZE�j
     */
    public MyQueue()
    {
        this(DEFAULT_QUEUE_SIZE);
    }

    /**
     * �傫�����w�肵�āA�҂��s��𐶐�����
     *
     * @param size   �҂��s��̑傫��
     */
    public MyQueue(int size)
    {
        queueSize = size;
        queue = new Object[size];
        front = rear = 0;
    }

    /**
     * ���̗v�f�̓Y���������߂�
     *
     * @param a  ���݂̗v�f�̓Y����
     */
    private int next(int a)
    {
        return (a + 1) % queueSize;
    }

    /**
     * �҂��s��̒��g���̂Ăċ�ɂ���
     */
    public void clear()
    {
        front = rear = 0;
        Arrays.fill(queue, 0, queueSize, null);  // �҂��s���null�ŃN���A����
    }

    /**
     * �҂��s��Ƀf�[�^������
     *
     * @param x  �҂��s��ɓ����f�[�^
     */
    public void enqueue(Object x)
    {
        if (next(rear) == front) {
            throw new IllegalStateException("����ȏ�C�҂��s��ɗv�f��ǉ��ł��܂���");
        }
        queue[rear] = x;
        rear = next(rear);
    }

    /**
     * �҂��s�񂩂�f�[�^�����o��
     *
     * @return  �҂��s�񂩂���o�����f�[�^
     */
    public Object dequeue()
    {
        if (front == rear) {
            throw new NoSuchElementException("�҂��s�񂪋�Ȃ̂ŗv�f�����o���܂���");
        }
        Object x = queue[front];
        queue[front] = null;            // �v�f��null�ŃN���A����
        front = next(front);
        return x;
    }

    /**
     * �҂��s�񂪋󂩂ǂ����𒲂ׂ�
     *
     * @return  ��Ȃ�true�C��łȂ����false��Ԃ�
     */
    public boolean isEmpty()
    {
        return front == rear;
    }

    /**
     * �҂��s��̓��e��\���������Ԃ�
     *
     * @return  �҂��s��̓��e��\��������
     */
    public String toString()
    {
        String s = "MyQueue=[";
        for (int i = front; i != rear; i = next(i)) {
            s += queue[i] + " ";
        }
        s += "] front=" + front + " rear=" + rear;
        return s;
    }

    /**
     * �e�X�g�p�̃��C�����[�`��
     */
    public static void main(String args[])
    {
        MyQueue q = new MyQueue(5);

        q.enqueue("a");         System.out.println("a����ꂽ");
        q.enqueue("b");         System.out.println("b����ꂽ");
        q.enqueue("c");         System.out.println("c����ꂽ");
        System.out.println(q);
        System.out.println(q.dequeue() + "�����o����");
        System.out.println(q.dequeue() + "�����o����");
        System.out.println(q);
        q.enqueue("d");         System.out.println("d����ꂽ");
        q.enqueue("e");         System.out.println("e����ꂽ");
        System.out.println(q.dequeue() + "�����o����");
        System.out.println(q);
        q.enqueue("f");         System.out.println("f����ꂽ");
        System.out.println(q.dequeue() + "�����o����");
        System.out.println(q.dequeue() + "�����o����");
        System.out.println(q);
        q.clear();              System.out.println("�҂��s����N���A����");
        System.out.println(q);
        q.enqueue("g");         System.out.println("g����ꂽ");
        q.enqueue("h");         System.out.println("h����ꂽ");
        System.out.println(q);
    }
}
