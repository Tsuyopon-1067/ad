/**
 * �r���\�[�g���s��
 */
public class BinSort {

    /**
     * �r���\�[�g���s���B�n���ꂽ�z��a�̒��g���\�[�g����
     *
     * @param  a  �\�[�g����z��
     */
    public static void sort(BinSortData[] a)
    {
        final int N = a.length;         // �z��̗v�f��
        final int M = BinSortData.M;    // �L�[��0����M�܂ł͈̔͂̐�

        // �r�������蓖�Ă�
        // ���蓖�Ă̎��_�ł́C���ׂẴr���ɂ͋�ł��邱�Ƃ�����null��
        // �Z�b�g����Ă���
        BinSortData[] bin = new BinSortData[M + 1];

        // �z��a�̃f�[�^�����ԂɃr���ɐU�蕪����
        for (int i = 0; i < N; i++) {

	    // �L�[��ϐ�key�ɓ����
	    int key = a[i].getKey();

	    // �L�[���d�����Ă��Ȃ����`�F�b�N
	    if (bin[key] != null) {
		throw new IllegalArgumentException(
				   "�L�["+key+"���d�����Ă��܂��B");
	    }

	    // �v�f���r���ɓ����
            bin[key] = a[i];
        }

        // �f�[�^���r������i�����Ɂj���o���āC�z��a�ɖ߂�
        int j = 0;
        for (int i = 0; i <= M; i++) {
            if (bin[i] != null) {
                a[j++] = bin[i];
            }
        }
    }
}
