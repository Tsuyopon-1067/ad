/**
 * �}���\�[�g
 */
class InsertionSort
{
    /*
     * �}���\�[�g�ɂ���Ĕz��𐮗񂷂�
     * 
     * @param a   ���񂷂�z��
     */
    public static void sort(int[] a)
    {
        int  n = a.length;      // �z��̗v�f��

        for (int i = 1; i < n; i++) {
            int j = i;
            while (j >= 1 && a[j - 1] > a[j]) {
                int temp = a[j];
                a[j]     = a[j - 1];
                a[j - 1] = temp;
                j--;
            }
        }
    }
}
