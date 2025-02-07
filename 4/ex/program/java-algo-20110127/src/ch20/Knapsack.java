/**
 * ���I�v��@���g���ăi�b�v�U�b�N��������
 */
import java.util.*;

public class Knapsack {
    int[]  size;        // �i���̑傫��
    int[]  value;       // �i���̉��l
    int  N;             // �i���̎�ނ̐�

    /**
     * �i�b�v�U�b�N����\���I�u�W�F�N�g�𐶐�����
     * @param  size   �i���̑傫����\���z��
     * @param  value  �i���̉��l��\���z��
     */
    public Knapsack(int[] size, int[] value) {
        // �p�����[�^size��value�̗v�f�̌������������Ƃ��m�F����
        if (size.length != value.length) {
            throw new IllegalArgumentException(
                          "'size'��'value'�̗v�f������v���Ă��܂���");
        }

        // �i���̎�ނ̐����Z�b�g����
        this.N     = size.length;

        // �z��size�̕������쐬���ăt�B�[���hsize�ɃZ�b�g����
        this.size = (int[])size.clone();

        // �z��value�̕������쐬���ăt�B�[���hvalue�ɃZ�b�g����
        this.value = (int[])value.clone();
    }

    /**
     * �傫��m�̃i�b�v�U�b�N�ɑ΂���������߂ĕ\������
     * @param m  �i�b�v�U�b�N�̑傫��
     */
    public void solve(int m) {

        // �����_�Ńi�b�v�U�b�N�ɋl�ߍ��񂾕i���̉��l�̍��v
        int[] total = new int[m+1];     // �S�v�f��0�ɏ����������

        // �Ō�ɑI�񂾕i��
        int[] choice = new int[m+1];
        Arrays.fill(choice, -1);        // �S�v�f��-1�ɏ���������

        // �i��i����ꂽ�Ƃ��̉��l�̍��v
        int repackTotal;

        // �i�b�v�U�b�N�̑傫����\������
        System.out.printf("�i�b�v�U�b�N�̑傫����%d%n", m);

        // �i��0�`i�܂ł��l���ɓ����
        for (int i = 0; i < N; i++) {

            // �傫��j�̃i�b�v�U�b�N�ɑ΂��āA�i�����l�ߍ���ł݂�
            for (int j = size[i]; j <= m; j++) {

                // �����i��i����ꂽ�Ƃ���ƁA���l�̍��v�͂������
                // �Ȃ邩���v�Z���āA�ϐ�repackTotal�ɓ����
                repackTotal = total[j - size[i]] + value[i];

                // �����i��i����ꂽ�ق����i����Ȃ����j���l��
                // �傫���Ȃ�̂Ȃ�A�i��i������
                if (repackTotal > total[j]) {
                    total[j] = repackTotal;
                    choice[j] = i;
                }
            }

            // �z��total�Achoice�̒��g���_���v����
            System.out.printf("i = %d%n", i);
            System.out.printf("total  = ");
            for (int j = 0; j <= m; j++) {
                System.out.printf("%4d", total[j]);
            }
            System.out.printf("%nchoice = ");
            for (int j = 0; j <= m; j++) {
                System.out.printf("%4d", choice[j]);
            }
            System.out.printf("\n");
        }

        // �ǂ̕i�����i�b�v�U�b�N�ɓ��ꂽ����\������
        for (int i = m; choice[i] >= 0; i -= size[choice[i]]) {
            System.out.printf("�i�� %d�i���l%d�j���l�ߍ���%n",
                              choice[i], value[choice[i]]);
        }
        System.out.printf("���l�̍��v = %d%n", total[m]);
    }

    /**
     * �N���@�ƃ��b�Z�[�Wmessage��\�����āC�v���O�������ُ�I��������
     * @param message    �\�����郁�b�Z�[�W
     */
    private static void abort(String message)
    {
        System.err.printf("�N���@: java Knapsack �傫��%n");
        System.err.printf("%s%n", message);
        System.exit(1);         // �X�e�[�^�X�R�[�h1�ُ͈�I��������
    }

    /**
     * �i�b�v�U�b�N�����������C���v���O�����B
     * �R�}���h���C�������ɂ���āC�i�b�v�U�b�N�̑傫�����w�肷��
     */
    public static void main(String args[]) {

        // �i�b�v�U�b�N�����������邽�߂̃I�u�W�F�N�g�𐶐�����
        Knapsack  knapsack =
            new Knapsack(
                    new int[] { 2, 3, 5,  7,  9},   // �i���̑傫��
                    new int[] { 2, 4, 7, 11, 14});  // �i���̉��l

        // �R�}���h���C������i�b�v�U�b�N�̑傫���𓾂�
        int size = 0;   // �i�b�v�U�b�N�̑傫��
        if (args.length != 1) {
            abort("�p�����[�^�̌����Ⴂ�܂��B");
        }
        try {
            size = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            abort("�傫���ɂ͐��̐������w�肵�Ă��������B");
        }
        if (size <= 0) {
            abort("�傫���ɂ͐��̐������w�肵�Ă��������B");
        }

        // �i�b�v�U�b�N��������
        knapsack.solve(size);
    }
}
