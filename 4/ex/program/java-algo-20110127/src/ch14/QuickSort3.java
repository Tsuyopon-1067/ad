/**
 * �N�C�b�N�\�[�g�i��ċA�ŁC�X�^�b�N�I�[�o�[�t���[�΍�ς݁j
 */
class QuickSort3
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
     * �N�C�b�N�\�[�g�ɂ���Ĕz��𐮗񂷂�
     * 
     * @param a   ���񂷂�z��
     */
    public static void sort(int[] a)
    {
        int n = a.length;
        int[] low  = new int[30];
        int[]  high= new int[30];
        int sp;

        // �X�^�b�N������������
        low[0]  = 0;
        high[0] = n - 1;
        sp = 1;

        // �X�^�b�N����ɂȂ�܂ŌJ��Ԃ�
        while (sp > 0) {
            // �X�^�b�N����C���񂷂�͈͂����o��
            int l = low[--sp];
            int r = high[sp];

            // ���񂷂�v�f��1�Ȃ�C�������Ȃ�
            // �i�Ă�while�������s����j
            if (l >= r) {
                // �������Ȃ�
            } else {
                // ����v����ɕ�������
                int v = partition(a, l, r);

                // ���E�̕����z��̂����C�Z���ق����ɏ�������
                if (v - l < r - v) {
                    // �������z����ɐ��񂷂�
                    // �i�X�^�b�N�Ȃ̂ŁC�u�E���v�̏��ɐςނ��Ƃɒ��Ӂj
                    low[sp]    = v + 1;
                    high[sp++] = r;
                    low[sp]    = l;
                    high[sp++] = v - 1;
                } else {
                    // �E�����z����ɐ��񂷂�
                    // �i�X�^�b�N�Ȃ̂ŁC�u���E�v�̏��ɐςނ��Ƃɒ��Ӂj
                    low[sp]    = l;
                    high[sp++] = v - 1;
                    low[sp]    = v + 1;
                    high[sp++] = r;
                }
            }
        }
    }
}
