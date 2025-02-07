/**
 * �N�C�b�N�\�[�g�i�ċA�Łj
 */
class QuickSort1
{
    /*
     * �z��a[l]�`a[r]�𕪊�����B�����̓Y������Ԃ�
     */
    private static int partition(int[] a, int l, int r)
    {
        // �|�C���^i��j������������
        int i = l - 1;
        int j = r;

        // �E�[�̗v�f�𐕎��ɂ���
        int pivot = a[r];

        // �|�C���^i��j���Ԃ���܂ŌJ��Ԃ�
        while (true) {
            // �|�C���^i���E�֐i�߂�
            while (a[++i] < pivot)
                ;
            // �|�C���^j�����֐i�߂�
            while (i < --j && pivot < a[j])
                ;
            // �|�C���^i��j���Ԃ������烋�[�v�𔲂���
            if (i >= j)
                break;
            // i�̎w���v�f��j�̎w���v�f����������
            int temp = a[i];
            a[i]     = a[j];
            a[j]     = temp;
        }
        // a[i]�Ɛ�������������
        int temp = a[i];
        a[i]     = a[r];
        a[r]     = temp;
        return i;
    }

    /*
     * ���ۂɃN�C�b�N�\�[�g���s��
     * �z��a�̂���a[l]�`a[r]�𐮗񂷂�
     */
    private static void quickSort(int[] a, int l, int r)
    {
        // ���񂷂�v�f��1�Ȃ�C�������Ȃ��Ŗ߂�
        if (l >= r)
            return;

        // ����v����ɕ�������
        int v = partition(a, l, r);

        // ���̕����z��a[l]�`a[v-1]�𐮗񂷂�
        quickSort(a, l, v - 1);

        // �E�̕����z��a[v+1]�`a[r]�𐮗񂷂�
        quickSort(a, v + 1, r);
    }

    /*
     * �N�C�b�N�\�[�g�ɂ���Ĕz��𐮗񂷂�
     * 
     * @param a   ���񂷂�z��
     */
    public static void sort(int[] a)
    {
        quickSort(a, 0, a.length - 1);
    }
}
