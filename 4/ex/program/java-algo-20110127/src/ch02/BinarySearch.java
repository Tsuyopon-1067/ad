public class BinarySearch {

    /*
     * �e�[�u���̃G���g��
     */
    static private class Entry {

        int key;        // ��r�̑ΏۂƂȂ�L�[
        Object data;    // ����ȊO�̏��

        /**
         * �G���g���𐶐�����
         *
         * @param key �L�[
         * @param data �L�[key �ɑΉ�����f�[�^
         */
        private Entry(int key, Object data)
        {
            this.key = key;
            this.data = data;
        }
    }

    final static int MAX = 100;         // �f�[�^�̍ő��
    Entry[] table = new Entry[MAX];     // �f�[�^���i�[����z��
    int n = 0;                          // table �ɓo�^����Ă���f�[�^�̌�

    /*
     * �L�[key �ɑΉ�����f�[�^��T��
     *
     * @param key �L�[
     * @return �L�[key �ɑΉ�����f�[�^�B�L�[��������Ȃ����null ��Ԃ�
     */
    public Object search(int key)
    {
        int low = 0;                            //�i1�j
        int high = n - 1;                       //�i2�j

        while (low <= high) {                   //�i3�j
            int middle = (low + high) / 2;      //�i4�j
            if (key == table[middle].key)       //�i5�j
                return table[middle].data;      //�i6�j��������
            else if (key < table[middle].key)   //�i7�j
                high = middle - 1;              //�i8�j
            else // key > table[middle].key �ł���
                low = middle + 1;               //�i9�j
        }
        return null;                            //�i10�j������Ȃ�����
    }
}
