/**
 * �q�[�v�𗘗p�����\�[�g
 */
class HeapSort0
{
    /*
     * �q�[�v�𗘗p���Ĕz��𐮗񂷂�
     *
     * @param a  ���񂷂ׂ��z��
     */
    public static void sort(int[] a)
    {
        int n = a.length;       // �z��̗v�f��

        // ��Ɨp�̃q�[�v�𐶐�����
        Heap heap = new Heap(n);
        
        // �z��̂��ׂĂ̗v�f���q�[�v�ɑ}������
        for (int i = 0; i < n; i++) {
            heap.insert(a[i]);
        }

        // �L�[�����������ɗv�f�����o���āC�z��ɖ߂�
        for (int i = 0; i < n; i++) {
            a[i] = heap.deleteMin();
        }
    }
}
