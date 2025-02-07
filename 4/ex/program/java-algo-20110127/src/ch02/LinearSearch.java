public class LinearSearch {

    /*
     * �e�[�u���̃G���g��
     */
    static private class Entry {

        int key;        // ��r�̑ΏۂƂȂ�L�[
        Object data;    // ����ȊO�̏��

        /**
         * �G���g���𐶐�����
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
     * �f�[�^��o�^����
     * @param key �L�[
     * @param data �L�[key �ɑΉ�����f�[�^
     */
    public void add(int key, Object data)
    {
        if (n >= MAX) {
            throw new IllegalStateException("�f�[�^�̌����������܂�");
        }
        table[n++] = new Entry(key, data);
    }

    /*
     * �L�[key �ɑΉ�����f�[�^��T��
     * @param key �L�[
     * @return �L�[key �ɑΉ�����f�[�^�B�L�[��������Ȃ����null ��Ԃ�
     */
    public Object search(int key)
    {
        int i = 0;                        //�i1�j
        while (i < n) {                   //�i2�j
            if (table[i].key == key)      //�i3�j
                return (table[i].data);   //�i4�j��������
            i++;                          //�i5�j
        }
        return null;                      //�i6�j������Ȃ�����
    }
}
