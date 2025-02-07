/**
 * N�N�C�[���̃��C���v���O�����i�ŏ��̉��݂̂�\���j
 *
 *      �N���@:  java NQueenMain �N�C�[���̐�
 */
public class NQueenMain {

    /**
     * �N���@�ƃ��b�Z�[�Wmessage��\�����āC�v���O�������ُ�I��������
     */
    private static void abort(String message)
    {
        System.err.println("�N���@: java NQueenMain �N�C�[���̐�");
        System.err.println(message);
        System.exit(1);         // �X�e�[�^�X�R�[�h1�ُ͈�I��������
    }

    /**
     * ���C���v���O�����BN�N�C�[���̍ŏ��̉���\������B
     * �N�C�[���̌��́C�R�}���h���C�������Ŏw�肷��
     */
    public static void main(String args[]) {

        // �p�����[�^�̌���1�łȂ���΂Ȃ�Ȃ�
        if (args.length != 1) {
            abort("�p�����[�^�̌����Ⴂ�܂��B");
        }

        // �p�����[�^�Ŏw�肳�ꂽ�N�C�[���̐����擾����n�ɃZ�b�g����
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            abort("�N�C�[���̐��ɂ͐��̐������w�肵�Ă�������: " + args[0]);
        }
        if (n <= 0) {
            abort("�N�C�[���̐��ɂ͐��̐������w�肵�Ă�������: " + args[0]);
        }

        // N�N�C�[���������āC��������Ή���\������
        NQueen  nq = new NQueen(n);
        if (nq.tryQueen(0)) {
            // ���������̂ŉ���\������
            nq.print();
        } else {
            // ���s�����̂ŁC���̎|�̃��b�Z�[�W��\������
            System.out.println("�c�O�Ȃ���C���͑��݂��܂���B");
        }
    }
}
