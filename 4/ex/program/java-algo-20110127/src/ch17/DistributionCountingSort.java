/**
 * ���z�����グ�\�[�g���s��
 */
public class DistributionCountingSort {

    /**
     * ���z�����グ�\�[�g���s���B�z��a�̓��e���\�[�g����
     *
     * @param  a  �\�[�g����z��
     */
    static void sort(BinSortData[] a)
    {
        final int N = a.length;         // �z��̗v�f��
        final int M = BinSortData.M;    // �L�[��0����M�܂ł͈̔͂̐�

        // �J�E���^�Ɏg���z������蓖�Ă�
        // �i�v�f�͎����I��0�ɏ����������j
        int[] count = new int[M + 1];

        // �L�[�𐔂��グ��
        for (int i = 0; i < N; i++) {
            count[a[i].getKey()]++;
        }

        // �����グ���L�[�̗ݐϓx�����z�����߂�
        for (int i = 0; i < M; i++) {
            count[i+1] += count[i];
        }

        // �x�����z�ɏ]���āC�f�[�^��z��a�����Ɨp�z��b�ɃR�s�[����
        BinSortData[] b = new BinSortData[N];
        for (int i = N - 1; i >= 0; i--) {
            b[--count[a[i].getKey()]] = a[i];
        }

        // �z��b�ɓ����Ă��鐮�񂳂ꂽ�f�[�^��z��a�ɃR�s�[����
        System.arraycopy(b, 0, a, 0, N);
    }
}
