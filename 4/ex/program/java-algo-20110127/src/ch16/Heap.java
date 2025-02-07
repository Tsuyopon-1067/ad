/*
 * �q�[�v
 */
class Heap
{
    int[] a;    // �q�[�v�̎��̂�����z��
    int   n;    // �q�[�v�ɓ����Ă���v�f�̌�

    /**
     * �q�[�v�𐶐�����
     *
     * @param size  �q�[�v�ɓo�^�ł���v�f�̍ő��
     */
    public Heap(int size)
    {
        // �z��a�̂���a[1]�`a[size+1]���g�p����
        // a[0]�͎g��Ȃ����߁Csize+1�̗v�f���m�ۂ��邱�Ƃɒ���
        a = new int[size + 1];
        n = 0;
    }

    /**
     * �q�[�v����x�Ԗڂ̗v�f��K�v�ȏꏊ�܂ŕ����яオ�点��
     *
     * @param x  �����яオ�点��v�f�̓Y����
     */
    private void upHeap(int x)
    {
        // �����яオ�点��v�f�̒l��value�ɓ���Ă���
        int value = a[x];

        // �v�f�����܂ŕ����яオ���Ă��Ȃ��C����
        // �u�e���q���傫���v�ԌJ��Ԃ�
        while (x > 1 && a[x/2] > value) {

            // �e�̒l���q�Ɉڂ�
            a[x] = a[x/2];

            // ���ړ_��e�Ɉڂ�
            x /= 2;
        }

        // �ŏI�I�ȗ��������悪���܂���
        a[x] = value;
    }

    /**
     * �q�[�v�ɗv�f��o�^����
     *
     * @param elem  �o�^���ׂ��v�f
     */
    public void insert(int elem)
    {
        // �q�[�v�ɓo�^�ł���]�T�����邩�m�F����
        if (n >= a.length) {
            throw new IllegalStateException("����ȏ�q�[�v�ɗv�f��o�^�ł��܂���B");
        }

        // �v�f���Ƃ肠�����q�[�v�̍Ō�ɓ����
        a[++n] = elem;

        // �ǉ������v�f�𕂂��яオ�点��
        upHeap(n);
    }

    /**
     * �q�[�v�̐擪�̗v�fa[1]��K�v�ȂƂ���܂Œ��߂�
     */
    private void downHeap()
    {
        // ���߂���v�f�̒l��value�ɃZ�b�g���Ă���
        int  value = a[1];

        // ������n�߂āC��i���q�������Ă������J��Ԃ�
        int  i = 1;
        while (i <= n/2) {

            // ��i�̎q�̂����������ق����j�Ƃ���
            int  j = i * 2;
            if (j + 1 <= n && a[j] > a[j + 1]) {
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

    /**
     * �q�[�v����ŏ��̗v�f���폜����
     * �v�f�̒l��Ԃ�
     *
     * @return �폜�����v�f�̒l
     */
    int deleteMin()
    {
        // �q�[�v����łȂ����Ƃ��m�F����
        if (n < 1) {
            throw new IllegalStateException("�q�[�v����ł��B");
        }

        // ���̗v�f��߂�l�ɂ���
        //  �i�q�[�v�̐擪�����ɓ�����j
        int value = a[1];

        // �q�[�v�̍Ō�̗v�f��擪�Ɉړ�����
        a[1] = a[n--];

        // �擪�Ɉړ������v�f��K�؂ȏꏊ�܂Œ��߂�
        downHeap();

        return value;
    }
}
