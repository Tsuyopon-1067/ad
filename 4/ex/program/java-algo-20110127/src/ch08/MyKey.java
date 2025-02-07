/**
 * �n�b�V���\�Ŏg���L�[
 */
public class MyKey
{
    String str;         // �L�[�ƂȂ镶����

    /**
     * �L�[�𐶐�����
     *
     * @param s  �L�[�ƂȂ镶����
     */
    public MyKey(String s)
    {
        str = s;
    }

    /***
     * �L�[���r����
     *
     * @param  o ��r����L�[
     * @return ���̃L�[�ƃL�[o�����������true�C�������Ȃ����false
     */
    public boolean equals(Object o)
    {
        // �p�����[�^o��MyKey�I�u�W�F�N�g���m�F����
        if (! (o instanceof MyKey)) {
            return false;
        }
        // �p�����[�^o��MyKey�^�ɃL���X�g���āC���e���r����
        MyKey k = (MyKey)o;
        return str.equals(k.str);
    }

    /***
     * �L�[�̃n�b�V���l��Ԃ�
     *
     * @return ���̃L�[�̃n�b�V���l
     */
    public int hashCode()
    {
        int sum = 0;

        // ������Ɋ܂܂�邷�ׂĂ̕����̃R�[�h�̘a�����߂�
        for (int i = 0; i < str.length(); i++) {
            sum += (int)str.charAt(i);
        }
        return sum;
    }

    /**
     * �L�[�̕�����\����Ԃ�
     * (���ۂɂ́C�L�[�ƂȂ��Ă��镶���񂻂̂��̂�Ԃ�)
     *
     * @return �L�[�𕶎���ŕ\����������
     */
    public String toString()
    {
        return str;
    }
}
