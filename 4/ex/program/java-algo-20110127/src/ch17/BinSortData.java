/**
 * �r���\�[�g�ƕ��z�����グ�\�[�g�̑ΏۂƂȂ�f�[�^�^
 */
public class BinSortData {

    // �L�[��0����M�܂ł͈̔͂̐���
    static final int  M = 100;

    private int     key;  // ����̃L�[
                          // 0����M�܂ł͈̔͂łȂ���΂Ȃ�Ȃ�
    private Object  data; // ���̑��̏��

    /**
     * �\�[�g�̑ΏۂƂȂ�f�[�^�𐶐�����
     *
     * @param key  �L�[
     * @param data  ���̑��̏��
     */
    BinSortData(int key, Object data)
    {
        // �L�[���͈͓��Ɏ��܂��Ă��邩���`�F�b�N����
        if (key < 0 || key > M) {
            throw new IllegalArgumentException(
                            "�L�[" + key + "���͈͊O�ł��B");
        }
        this.key = key;
        this.data = data;
    }

    /**
     * �L�[���擾����
     *
     * @return �L�[�̒l��Ԃ�
     */
    public int getKey()
    {
        return key;
    }

    /**
     * ���̑��̏����擾����
     *
     * @return ���̑��̏���Ԃ�
     */
    public Object getData()
    {
        return data;
    }
}
