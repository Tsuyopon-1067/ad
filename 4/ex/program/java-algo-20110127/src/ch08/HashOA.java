/**
 * �I�[�v���A�h���X�@�ɂ��n�b�V���\
 */
public class HashOA
{
    /**
     * �n�b�V���\�̃G���g���i�o�P�b�g�j
     */
    private static class Bucket
    {
        MyKey  key;     // �L�[
        Object data;    // �Ή�����f�[�^

        /**
         * �o�P�b�g�𐶐�����
         *
         * @param key   �L�[
         * @param data  �f�[�^
         */
        private Bucket(MyKey key, Object data)
        {
            this.key  = key;
            this.data = data;
        }
    }

    Bucket[] table;     // �n�b�V���\�̎���
    int    bucketSize;  // �o�P�b�g�̌�
    int    nElements;   // �o�^����Ă���f�[�^�̌�

    // �폜�ς݂ł��邱�Ƃ��������ʂȃL�[�̒l
    static final MyKey DELETED = new MyKey(null);

    // �󂫂ł��邱�Ƃ��������ʂȃL�[�̒l
    static final MyKey EMPTY   = new MyKey(null);

    // �f�t�H���g�̃n�b�V���\�̑傫��(�f�����]�܂���)
    static final int  DEFAULT_BUCKET_SIZE = 53;

    /**
     * �n�b�V���\�𐶐�����i�傫����DEFAULT_BUCKET_SIZE�j
     */
    public HashOA()
    {
        this(DEFAULT_BUCKET_SIZE);
    }

    /**
     * �n�b�V���\�𐶐�����
     *
     * @param bucketSize  �n�b�V���\�̑傫��
     */
    public HashOA(int bucketSize)
    {
        // �n�b�V���\�̎��̂̔z������蓖�ĂāC���ׂĂ̗v�f��
        // �L�[��EMPTY�ŏ��������Ă���
        table = new Bucket[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            table[i] = new Bucket(EMPTY, null);
        }

        // �n�b�V���\�̃o�P�b�g�̌����L�^���Ă���
        this.bucketSize = bucketSize;

        // �v���̌���0�ɂ��Ă���
        nElements = 0;
    }

    /**
     * �n�b�V���l�����߂�
     * �L�[�ƂȂ�I�u�W�F�N�g��hashCode���\�b�h���Ԃ����l���C
     * �o�P�b�g�̌��Ŋ������]���Ԃ�
     *
     * @param key  �L�[
     * @return �^����ꂽ�L�[�ɑ΂���n�b�V���l
     */
    private int hash(MyKey key)
    {
        return key.hashCode() % bucketSize;
    }

    /**
     * �ăn�b�V�����s��
     *
     * @param h  �n�b�V���l
     * @return   �ăn�b�V���ŋ��߂��n�b�V���l
     */
    private int rehash(int h)
    {
        return (h + 1) % bucketSize;
    }

    /**
     * �n�b�V���\��T������
     *
     * @param key  �T���ׂ��L�[
     * @return �f�[�^��������΂����Ԃ��B
     *         ������Ȃ����null��Ԃ�
     */
    public Object find(MyKey key)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY) {
            if (k != DELETED && key.equals(k)) {
                return table[h].data;
            }
            if (++count > bucketSize) {
                return null;
            }
            h = rehash(h);
        }
        return null;
    }

    /**
     * �n�b�V���\�Ƀf�[�^��}������
     *
     * @param  key  �L�[
     * @param  data �o�^����f�[�^
     * @return �o�^�ɐ��������true�C���s�i���łɓ����L�[������
     *         �f�[�^������j������false��Ԃ�
     */
    public boolean insert(MyKey key, Object data)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY && k != DELETED) {
            if (key.equals(k)) {
                return false;
            }
            if (++count > bucketSize) {
                throw new IllegalStateException(
                        "�n�b�V���\�ɂ���ȏ�f�[�^��o�^�ł��܂���B");
            }
            h = rehash(h);
        }
        table[h].key  = key;
        table[h].data = data;
        nElements++;
        return true;
    }

    /**
     * �n�b�V���\����f�[�^���폜����
     *
     * @param key  �폜���ׂ��f�[�^�̃L�[
     * @return �폜�ɐ���������true�C�f�[�^��������Ȃ����false��Ԃ�
     */
    public boolean delete(MyKey key)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY) {
            if (k != DELETED && key.equals(k)) {
                table[h].key = DELETED;
                table[h].data = null;
                nElements--;
                return true;
            }
            if (++count > bucketSize) {
                return false;
            }
            h = rehash(h);
        }
        return false;
    }

    /**
     * �n�b�V���\�̓��e��\���������Ԃ�
     *
     * @return �n�b�V���\�̓��e��\��������
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            s += "�o�P�b�g " + i + ": ";
            MyKey k = table[i].key;
            if (k == EMPTY) {
                s += "��\n";
            } else if (k == DELETED) {
                s += "�폜�ς�\n";
            } else {
                s += "key=[" + k + "] data=[" + table[i].data + "]\n";
            }
        }
        // �Ō�ɓo�^����Ă���v�f�̌���ǉ�����
        s += "�v�f�̌�:" + nElements + "\n";
        return s;
    }
}
