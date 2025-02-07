/**
 * N�N�C�[���̃��C���v���O�����i���ׂẲ���\���j
 *
 *      �N���@:  java NQueenAll �N�C�[���̐�
 */
public class NQueenAll {

    /**
     * �N���@�ƃ��b�Z�[�Wmessage��\�����āC�v���O�������ُ�I��������
     */
    private static void abort(String message)
    {
        System.err.println("�N���@: java NQueenAll �N�C�[���̐�");
        System.err.println(message);
        System.exit(1);         // �X�e�[�^�X�R�[�h1�ُ͈�I��������
    }

    /**
     * ���C���v���O�����BN�N�C�[���̂��ׂẲ���\������B
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

        // N�N�C�[���̂��ׂẲ���\������
        NQueen  nq = new NQueen(n);
        nq.tryQueenAll(0);
    }
}
