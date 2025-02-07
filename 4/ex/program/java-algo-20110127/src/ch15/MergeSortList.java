/**
 * �A�����X�g�Ń}�[�W�\�[�g
 */
class MergeSortList
{
    /*
     * 2�̘A�����X�ga��b���}�[�W����B
     * �}�[�W���ꂽ�A�����X�g�̐擪�v�f�ւ̃����N��Ԃ�
     *
     * @param a  �}�[�W���ׂ��A�����X�g
     * @param b  �}�[�W���ׂ��A�����X�g
     * @return   �}�[�W���ē���ꂽ�A�����X�g�̐擪�v�f�ւ̃����N
     */
    private static Cell mergeList(Cell a, Cell b)
    {
        // �ϐ�head�̓}�[�W�ςݘA�����X�g�̐擪�ɂ���_�~�[�̃Z�����w��
        Cell head = new Cell(0);

        // �����Np���_�~�[�̃Z�����w���悤�ɂ��Ă���
        Cell p = head;

        // �A�����X�ga�Cb�̂����ꂩ����ɂȂ�܂ŌJ��Ԃ�
        while (a != null && b != null) {

            // �A�����X�ga��b�̐擪�̗v�f���r����
            if (a.data <= b.data) {

                // �A�����X�ga�̐擪�̗v�f����菜���āC�}�[�W�ςݘA�����X�g
                // �̖����ɘA������
                p.next = a;
                p = a;
                a = a.next;
            } else {
                // �A�����X�gb�̐擪�̗v�f����菜���āC�}�[�W�ςݘA�����X�g
                // �̖����ɘA������
                p.next = b;
                p = b;
                b = b.next;
            }
        }

        // �c���Ă���v�f���}�[�W�ςݘA�����X�g�̍Ō���ɘA������
        if (a == null) {
            p.next = b;
        } else {
            p.next = a;
        }

        // �}�[�W�ς݂̘A�����X�g��߂�l�Ƃ��ĕԂ�
        return head.next;
    }

    /**
     * �A�����X�g�ł̃}�[�W�\�[�g
     * �A�����X�gx�𐮗񂷂�
     *
     * @param x  ���񂷂ׂ��A�����X�g
     * @return   ���񂳂ꂽ�A�����X�g�̐擪�v�f�ւ̃����N��Ԃ�
     */
    public static Cell mergeSortList(Cell x)
    {
        // �A�����X�g�̗v�f���܂������Ȃ����C1�����Ȃ��Ƃ���
        // ���̂܂ܖ߂�
        if (x == null || x.next == null) {
            return x;
        }

        // �A�����X�g���X�L��������ϐ�������������

        // a��1�Ԗڂ̗v�f���w��
        Cell a = x;

        // b��3�Ԗڂ̗v�f�i�����A�����X�g�̒�����2�̂Ƃ���2�Ԗڂ̗v�f�j���w��
        Cell b = x.next;
        if (b != null) {
            b = b.next;
        }

        // �ϐ�b���A�����X�g�̖����ɓ��B����܂ŁC�ϐ�a��1�i�߁C
        // �ϐ�b��2�i�߂�B�ϐ�b�������ɓ��B�����Ƃ��C�ϐ�a��
        // �A�����X�g�̂قڒ����̗v�f���w���Ă���͂��ł���
        while (b != null) {
            a = a.next;
            b = b.next;
            if (b != null) {
                b = b.next;
            }
        }

        // �A�����X�g���C�ϐ�a���w���v�f�̒����2�ɕ�������
        // �ϐ�p�́C�㔼�̘A�����X�g�̐擪�̗v�f���w��
        Cell p = a.next;
        a.next = null;

        // ���������A�����X�g�����ꂼ��ʂɐ��񂵂āC���̌��ʂ��}�[�W����
        return mergeList(mergeSortList(x), mergeSortList(p));
    }
}
