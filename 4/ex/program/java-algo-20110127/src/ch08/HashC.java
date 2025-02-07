/**
 * �`�F�C���@�ɂ��n�b�V���\
 */
public class HashC
{
    /**
     * �A�����X�g�̃Z��
     */
    private static class Cell
    {
        MyKey  key;     // �L�[
        Object data;    // �f�[�^
        Cell   next;    // ���̃Z��

        /**
         * �Z���𐶐�����
         *
         * @param key  �L�[
         * @param data �f�[�^
         */
        private Cell(MyKey key, Object data)
        {
            this.key  = key;
            this.data = data;
        }
    }

    Cell[] table;       // �n�b�V���\�̎���
    int    bucketSize;  // �o�P�b�g�̌�
    int    nElements;   // �o�^����Ă���f�[�^�̌�

    // �f�t�H���g�̃n�b�V���\�̑傫��
    static final int  DEFAULT_BUCKET_SIZE = 50;

    /**
     * �n�b�V���\�𐶐�����i�傫����DEFAULT_BUCKET_SIZE�j
     */
    public HashC()
    {
        this(DEFAULT_BUCKET_SIZE);
    }

    /**
     * �n�b�V���\�𐶐�����
     *
     * @param bucketSize  �n�b�V���\�̑傫��
     */
    public HashC(int bucketSize)
    {
        // �n�b�V���\�̎��̂̔z������蓖�Ă�
        table = new Cell[bucketSize];

        // �n�b�V���\�̃o�P�b�g�̌����L�^���Ă���
        this.bucketSize = bucketSize;

        // �v���̌���0�ɂ��Ă���
        nElements = 0;
    }

    /**
     * �n�b�V���֐������߂�
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
     * �n�b�V���\��T������
     *
     * @param key  �T���ׂ��L�[
     * @return �f�[�^��������΂����Ԃ��B
     *         ������Ȃ����null��Ԃ�
     */
    public Object find(MyKey key)
    {
        for (Cell p = table[hash(key)]; p != null; p = p.next) {
            if (key.equals(p.key)) {
                return p.data;
            }
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
        if (find(key) != null) {
            return false;
        }
        Cell p = new Cell(key, data);
        int  h = hash(key);
        p.next = table[h];
        table[h] = p;

        nElements++;    // �v�f�̌���1���₷
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
        int  h = hash(key);

        // ���̃o�P�b�g�͋󂩁H
        if (table[h] == null) {
            return false;
        }

        // ���X�g�̐擪�̃Z�����폜���ׂ��f�[�^���H
        if (key.equals(table[h].key)) {
            Cell p = table[h];
            table[h] = p.next;
            nElements--;        // �v�f�̌���1���炷
            return true;
        }

        // ���X�g��2�Ԗڈȍ~�̃Z���ɂ��ď��ԂɃ`�F�b�N����
        Cell p;
        Cell q;
        for (q = table[h], p = q.next; p != null; q = p, p = p.next) {
            if (key.equals(p.key)) {
                q.next = p.next;
                nElements--;    // �v�f�̌���1���炷
                return true;
            }
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

        // ���ׂẴo�P�b�g�����ɏ�������
        for (int i = 0; i < table.length; i++) {
            s += "�o�P�b�g " + i + ":";

            // ���̃o�P�b�g�̓��e�𕶎���s�ɒǉ�����
            for (Cell p = table[i]; p != null; p = p.next) {
                s += "[" + p.key + ":" + p.data + "] ";
            }
            s += "\n";
        }
        // �Ō�ɓo�^����Ă���v�f�̌���ǉ�����
        s += "�v�f�̌�:" + nElements + "\n";
        return s;
    }
}
