/**
 * �r���\�[�g�̃��C���v���O����
 */
public class BinSortMain {

    /**
     *  �r���\�[�g���f������
     */
    public static void main(String[] args)
    {
        // �\�[�g����z�������������
	int keys[] = {
	    13, 24, 15,  5, 98, 44, 35, 96, 82, 73
	};
        BinSortData[]  array = new BinSortData[keys.length];
	for (int i = 0; i < keys.length; i++) {
	    array[i] = new BinSortData(keys[i], "�v�f" + i);
	}

        // �z��array���r���\�[�g����
        BinSort.sort(array);

        // �z��array�̓��e��\������
        for (int i = 0; i < array.length; i++) {
            System.out.println("key=" + array[i].getKey() +
                               "  data=" + array[i].getData());
        }
    }
}
