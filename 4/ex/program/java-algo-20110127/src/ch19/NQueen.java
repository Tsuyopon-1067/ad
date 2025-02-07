/**
 * N�N�C�[��������
 */
import java.util.*;

public class NQueen {

    private enum Status {
        FREE,           // �����؂ɂȂ��Ă��Ȃ��i�u����j
        NOT_FREE        // �����؂ɂȂ��Ă���i�u���Ȃ��j
    }

    private final int  N;  // �N�C�[���̐�
    private int[] pos;     // �e�s�ɒu���ꂽ�N�C�[���̈ʒu
    private Status[] col;  // �N�C�[�������������ɗ����Ă��邩�������z��
    private Status[] down; // �N�C�[�����E�΂߉������ɗ����Ă��邩�������z��
    private Status[] up;   // �N�C�[�����E�΂ߏ�����ɗ����Ă��邩�������z��

    /**
     * N�N�C�[��������������߂̃I�u�W�F�N�g�𐶐�����
     * @param numberOfQueens  �N�C�[���̌�
     */
    public NQueen(int numberOfQueens) {

        // �z������蓖�Ă�
        N = numberOfQueens;
        pos = new int[N];
        col = new Status[N];
        down = new Status[2*N - 1];
        up = new Status[2*N - 1];

        // �N�C�[���̈ʒu�Ɨ����؂�����������
        Arrays.fill(pos, -1);
        Arrays.fill(col, Status.FREE);
        Arrays.fill(down, Status.FREE);
        Arrays.fill(up, Status.FREE);
    }

    /**
     * �sa�ȍ~�̂��ׂĂ̍s�ɃN�C�[����u��
     * @param a  ���̍s�ȍ~�ɃN�C�[����u��
     * @return  �N�C�[�����u������true�C�u���Ȃ�������false��Ԃ�
     */
    public boolean tryQueen(int a) {

        // ������E�Ɍ������ď��ԂɃN�C�[�����u���邩�ǂ����𒲂ׂ�
        for (int b = 0; b < N; b++) {

            // �sa��b�Ԗڂɒu���邩�ǂ����𒲂ׂ�
            if (col[b] == Status.FREE &&
                up[a + b] == Status.FREE &&
                down[a - b + (N-1)] == Status.FREE) {

                // �u�����Ƃ��ł����B�ꏊ���L�^���āA�����؂��Z�b�g����
                pos[a] = b;
                col[b] = Status.NOT_FREE;
                up[a + b] = Status.NOT_FREE;
                down[a - b + (N-1)] = Status.NOT_FREE;

                // N�̃N�C�[�������ׂĒu�����Ƃ��ł���ΐ����ł���
                if (a + 1 >= N) {
                    return true;
                } else {
                    // �sa+1�ȍ~�̂��ׂĂ̍s�ɒu�����琬���ł���
                    if (tryQueen(a + 1)) {
                        return true;
                    } else {
                        // ���s�����B�N�C�[������菜��
                        pos[a] = -1;
                        col[b] = Status.FREE;
                        up[a + b] = Status.FREE;
                        down[a - b + (N-1)] = Status.FREE;
                    }
                }
            }
        }
        // ���ǂ��̍s�ɂ͒u����ꏊ���Ȃ�����
        return false;
    }

    /**
     * �N�C�[���̈ʒu���o�͂���
     */
    public void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pos[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

// List 19.5  tryQueenAll���\�b�h
    /**
     * �sa�ȍ~�̂��ׂĂ̍s�ɃN�C�[����u���Ă݂�i���ׂẲ���\������j
     *
     * @param a  ���̍s�ȍ~�ɃN�C�[����u��
     */
    public void tryQueenAll(int a) {

        // ������E�Ɍ������ď��ԂɃN�C�[�����u���邩�ǂ����𒲂ׂ�
        for (int b = 0; b < N; b++) {

            // �sa��b�Ԗڂɒu���邩�ǂ����𒲂ׂ�
            if (col[b] == Status.FREE &&
                up[a + b] == Status.FREE &&
                down[a - b + (N-1)] == Status.FREE) {

                // �u�����Ƃ��ł����B�ꏊ���L�^���āA�����؂��Z�b�g����
                pos[a] = b;
                col[b] = Status.NOT_FREE;
                up[a + b] = Status.NOT_FREE;
                down[a - b + (N-1)] = Status.NOT_FREE;

                // N�̃N�C�[�������ׂĒu�����Ƃ��ł���ΐ����ł���
                if (a + 1 >= N) {
                    print();
                } else {
                    tryQueenAll(a + 1);
                }

                // �N�C�[������菜��
                pos[a] = -1;
                col[b] = Status.FREE;
                up[a+b] = Status.FREE;
                down[a - b + (N-1)] = Status.FREE;
            }
        }
    }
}
