class MyHashC {
    Entry[] table;
    int elements;

    MyHashC(int size) {
        table = new Entry[size];
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        elements = 0;
    }

    public int hash(String key) {
        int r = 0;
        // your code
        for (char c : key.toCharArray())
            r += (int) c;
        return r % table.length;
    }

    private static class Entry {
        MyKeyword keyword;
        Entry next;

        Entry(MyKeyword keyword) {
            this.keyword = keyword;
        }
    }

    public void insert(MyKeyword k) {
        Entry newNode = new Entry(k);
        int newNodeHash = hash(k.getKey());
        newNode.next = table[newNodeHash];
        table[newNodeHash] = newNode;
        elements++;
        return;
    }

    public MyKeyword search(String key) {
        int keyHash = hash(key);
        Entry curNode = table[keyHash];
        while (curNode != null) {
            if (curNode.keyword.getKey().equals(key)) {
                return curNode.keyword;
            }
            curNode = curNode.next;
        }
        return null;
    }

    public MyKeyword delete(String key) {
        int keyHash = hash(key);
        Entry curNode = table[keyHash];
        if (curNode == null) {
            return null;
        }
        if (curNode.keyword.getKey().equals(key)) {
            table[keyHash] = curNode.next;
            return curNode.keyword;
        }
        Entry prevCurNode = curNode;
        curNode = curNode.next;
        while (curNode != null) {
            if (curNode.keyword.getKey().equals(key)) {
                prevCurNode.next = curNode.next;
                return curNode.keyword;
            }
            curNode = curNode.next;
        }
        return null;
    }

    public String toString() {
        String str = "";
        boolean pef = false;
        boolean ef = false;

        str = "table[0] -- [" + (table.length - 1) + "]\n";
        str = str + "=============================\n";
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                ef = true;
                if (pef == false) {
                    str = str + "   :   \n";
                }
            } else {
                ef = false;
                str = str + "table[" + i + "]";
                Entry t = table[i];
                while (t != null) {
                    str = str + "->(" + t.keyword + ") ";
                    t = t.next;
                }
                str = str + "\n";
            }
            pef = ef;
        }
        str = str + "=============================\n";
        return str;
    }
}
