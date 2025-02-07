/**
 * �z��Ń}�[�W�\�[�g
 */
class MergeSortArray
{
    /* ��Ɨp�z�� */
    private static int[] b;

    /*
     * �z����}�[�W�\�[�g����B
     * a[low]�`a[high]�̗v�f�𐮗񂷂�
     *
     * @param a    ���񂷂ׂ��z��
     * @param low  ���񂷂�͈͂̉���
     * @param high ���񂷂�͈͂̏��
     */
    private static void mergeSortArray(int[] a, int low, int high)
    {
        // �����v�f��1�����Ȃ���΁C�����ɖ߂�
        if (low >= high) {
            return;
        }

        // ���2�̕�������ꏊmid�����߂�
        int mid = (low + high) / 2;

        // �O���̗v�fa[low]�`a[mid]�𐮗񂷂�
        mergeSortArray(a, low, mid);

        // �㔼�̗v�fa[mid+1]�`a[high]�𐮗񂷂�
        mergeSortArray(a, mid + 1, high);

        // �O���̗v�f�����̂܂܍�Ɨp�z��b�ɃR�s�[����
        System.arraycopy(a, low, b, low, mid - low + 1);

        // �㔼�̗v�f���t���ɍ�Ɨp�z��b�ɃR�s�[����
        for (int i = mid + 1, j = high; i <= high; i++, j--) {
            b[i] = a[j];
        }

        // ��Ɨp�z��b�̗��[������o�����f�[�^���}�[�W���Ĕz��a�ɓ����
        int i = low;
        int j = high;
        for (int k = low; k <= high; k++) {
            if (b[i] <= b[j]) {
                a[k] = b[i++];
            } else {
                a[k] = b[j--];
            }
        }
    }

    /*
     * �}�[�W�\�[�g�ɂ���Ĕz��𐮗񂷂�
     * 
     * @param a   ���񂷂�z��
     */
    public static void sort(int[] a)
    {

        b = new int[a.length];                  // ��Ɨp�z����m�ۂ���

        mergeSortArray(a, 0, a.length - 1);     // �z��a���}�[�W�\�[�g����

        b = null;                               // ��Ɨp�z����������
    }
}
