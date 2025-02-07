/**
 * ��\�[�g���s��
 */
public class RadixSort {

    // �T�u�L�[��0����15�܂ł͈̔͂̐����i4�r�b�g�j
    static final int  M = 15;

    // �T�u�L�[�����o���̂Ɏg���}�X�N�i2�i����1111�j
    static final int  MASK = 0xf;

    /**
     * ��\�[�g���s���B�n���ꂽ�z��a�̒��g���\�[�g����
     *
     * @param  a  �\�[�g����z��
     */
    static void sort(RadixSortData[] a)
    {
        final int N = a.length;         // �z��̗v�f��
        int  pass = 1;                  // ����ڂ̃\�[�g���H

        // ��Ɨp�̔z��
        RadixSortData[] b = new RadixSortData[N];

        // �L�[�̕��z�𐔂��グ�邽�߂̔z��
        int[] count = new int[M + 1];

        // ���ʂ����ʂɌ������āC4�r�b�g����4�񃋁[�v�����s����
        for (int bit = 0; bit < 16; bit += 4) {

            // �J�E���^�����ׂ�0�ɂ���
            for (int i = 0; i <= M; i++) {
                count[i] = 0;
            }

            // �L�[�𐔂��グ��
            for (int i = 0; i < N; i++) {
                count[(a[i].getKey()>>bit) & MASK]++;
            }

            // �����グ���L�[�̗ݐϓx�����z�����߂�
            for (int i = 0; i < M; i++) {
                count[i+1] += count[i];
            }

            // �x�����z�ɏ]���āC�f�[�^��z��a�����Ɨp�z��b�ɃR�s�[����
            for (int i = N - 1; i>= 0; i--) {
                b[--count[(a[i].getKey() >> bit) & MASK]] = a[i];
            }

            // �f�[�^����Ɨp�z��b����z��a�փR�s�[����
            System.arraycopy(b, 0, a, 0, N);

            // �z��̓��e��\������
            System.out.println("Pass " + pass++ + " --------------------");
            dumpArray(a);
        }
    }

    /**
     * RadixSortData�^�̔z��̓��e��16�i���Ń_���v����
     *
     * @param a  �_���v����z��
     */
    public static void dumpArray(RadixSortData a[])
    {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("key=%04x  data=%s%n",
			      a[i].getKey(), a[i].getData());
        }
    }
}
