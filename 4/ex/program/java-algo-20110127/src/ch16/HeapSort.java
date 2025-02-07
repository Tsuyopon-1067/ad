/**
 * �q�[�v�\�[�g
 */
class HeapSort
{
    /**
     * �q�[�v�̐擪�̗v�f��K�v�ȂƂ���܂Œ��߂�B
     * �������Ca[from]���q�[�v�̐擪�Ca[to]���q�[�v�̍Ō�̗v�f�Ƃ���
     *
     * @param a     �q�[�v�������Ă���z��
     * @param from  �q�[�v�̐擪�̗v�f�̓Y����
     * @param to    �q�[�v�̍Ō�̗v�f�̓Y����
     */
    private static void downHeap(int[] a, int from, int to)
    {
        // ���߂���v�f�̒l��value�ɃZ�b�g���Ă���
        int  value = a[from];

        // ������n�߂āC��i���q�������Ă������J��Ԃ�
        int  i = from;
        while (i <= to/2) {

            // ��i�̎q�̂����������ق����j�Ƃ���
            int  j = i * 2;
            if (j + 1 <= to && a[j] > a[j + 1]) {
                j++;
            }

            // �����C�e���q���傫���Ȃ��Ƃ����֌W�����藧�Ă΁C
            // ����ȏ㒾�߂�K�v�͂Ȃ�
            if (value <= a[j]) {
                break;
            }

            // ��i�ɐ�j�̒l�����āC��j�ɒ��ڂ���
            a[i] = a[j];
            i = j;
        }

        // �擪�ɂ������v�f���i�ɓ����
        a[i] = value;
    }

    /*
     * �q�[�v�\�[�g�ɂ���Ĕz��𐮗񂷂�
     * �w�肳�ꂽ�z��a�̂����Ca[1]����Ō�̗v�f�܂ł��~���ɐ��񂷂�
     * (a[0]�͐���̑ΏۂɂȂ�Ȃ��̂Œ���)
     * 
     * @param a   ���񂷂�z��
     */
    public static void sort(int[] a)
    {
        // �n���ꂽ�z��a�̍Ō�̗v�f�̓Y����
        int n = a.length - 1;

        for (int i = n / 2; i >= 1; i--) {
            downHeap(a, i, n);
        }

        for (int i = n; i >= 2; i--) {
            int tmp = a[1];
            a[1] = a[i];
            a[i] = tmp;
            downHeap(a, 1, i - 1);
        }
    }
}
