/**
 * ���z�����グ�\�[�g�̃��C���v���O����
 */
public class DistributionCountingSortMain {

    /**
     * ���z�����グ�\�[�g���f������
     */
    public static void main(String[] args)
    {
        // �\�[�g����z�������������
	int data[] = {
	    13, 24, 15,  5, 98, 44, 35, 15, 82, 73
	};
        BinSortData[]  array = new BinSortData[data.length];
	for (int i = 0; i < data.length; i++) {
	    array[i] = new BinSortData(data[i], "�v�f" + i);
	}

        // �z��array�𕪕z�����グ�\�[�g����
        DistributionCountingSort.sort(array);

        // �z��array�̓��e��\������
        for (int i = 0; i < array.length; i++) {
            System.out.println("key=" + array[i].getKey() +
                               "  data=" + array[i].getData());
        }
    }
}
