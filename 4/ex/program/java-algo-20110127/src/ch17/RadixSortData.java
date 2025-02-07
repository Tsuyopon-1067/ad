/**
 * ��\�[�g�̑ΏۂƂȂ�f�[�^�^
 */
public class RadixSortData {

    final static int KEY_MAX = 0xffff;     // �L�[�̍ő�l

    private int     key;  // ����̃L�[�B
                          // 0�`65535(0xffff)�͈̔͂łȂ���΂Ȃ�Ȃ�
    private Object  data; // ���̑��̏��

    /**
     * �\�[�g�̑ΏۂƂȂ�f�[�^�𐶐�����
     *
     * @param key  �L�[
     * @param data  ���̑��̏��
     */
    RadixSortData(int key, Object data)
    {
        // �L�[���͈͓��Ɏ��܂��Ă��邩���`�F�b�N����
        if (key < 0 || key > KEY_MAX) {
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
