import java.util.Random;

class MyHashOA2 extends MyHash {
    static final MyKeyword EMPTY = new MyKeyword("EMPTY", "EMPTY");
    static final MyKeyword DELETED = new MyKeyword("DELETED", "DELETED");

    MyKeyword[] table;
    int[] rn;
    int elements;

    MyHashOA2(int size) {
        table = new MyKeyword[size];
        rn = new int[size];
        super.size = size;
        for (int i = 0; i < size; i++) {
            table[i] = EMPTY;
            rn[i] = i;
        }
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 1; i < size; i++) {
            int x = i + (Math.abs(rand.nextInt()) % (size - i));
            int tmp = rn[i];
            rn[i] = rn[x];
            rn[x] = tmp;
        }

        elements = 0;
    }

    // Case A:
    //public int rehash(String key, int k) {
    //    return (hash(key) + k) % table.length;
    //}

    // Case B:
    public int rehash(String key, int k){
    return (hash(key) + rn[k]) % table.length;
    }

    public MyKeyword search(String key) {
        for (int k = 0; k < table.length; k++) {
            int h = rehash(key, k);
            if (table[h].getKey().equals(key)) {
                searchCount = searchCount + k + 1;
                return table[h];
            } else if (table[h] == EMPTY) {
                searchCount = searchCount + k + 1;
                return null;
            }
        }
        searchCount = searchCount + table.length;
        return null;
    }

    public boolean insert(MyKeyword nk) {
        for (int k = 0; k < table.length; k++) {
            int h = rehash(nk.getKey(), k);
            if (table[h] == EMPTY || table[h] == DELETED) {
                table[h] = nk;
                insertCount = insertCount + k + 1;
                return true;
            }
        }
        insertCount = insertCount + table.length;
        return false;
    }

    public MyKeyword delete(String key) {
        for (int k = 0; k < table.length; k++) {
            int h = rehash(key, k);
            if (table[h].getKey().equals(key)) {
                MyKeyword tmp = table[h];
                table[h] = DELETED;
                deleteCount = deleteCount + k + 1;
                return tmp;
            } else if (table[h] == EMPTY) {
                deleteCount = deleteCount + k + 1;
                return null;
            }
        }
        deleteCount = deleteCount + table.length;
        return null;
    }

    public String toString() {
        String str = "";
        boolean pef = false;
        boolean ef = false;

        str = "table[0] -- [" + (table.length - 1) + "]\n";
        str = str + "=============================\n";
        for (int i = 0; i < table.length; i++) {
            if (table[i] == EMPTY) {
                ef = true;
                if (pef == false) {
                    str = str + "   :   \n";
                }
            } else {
                ef = false;
                str = str + "table[" + i + "] ";
                if (table[i] == DELETED) {
                    str = str + ": DELETED\n";
                } else {
                    str = str + " (" + hash(table[i].getKey()) + "): " + table[i] + "\n";
                }
            }
            pef = ef;
        }
        str = str + "=============================\n";
        return str;
    }
}
