public class MyDoublyLinkedList {
    private MyDoublyLinkedData head;
    private MyDoublyLinkedData tail;
    private MyDoublyLinkedData current;
    private int compareCount;

    MyDoublyLinkedList() {
        head = new MyDoublyLinkedData("*header*", "");
        tail = head;
        current = head;
        tail.next = null;
    }

    public MyDoublyLinkedData getCurrent() {
        return current;
    }

    public void setCurrent(String str) {
        if ("head".equals(str)) {
            current = head;
        } else if ("tail".equals(str)) {
            current = tail;
        }
    }

    public boolean moveNext() {
        if (current.next != null) { // 進める(nextが存在する)なら次へ進む
            //current.prev = current;
            current = current.next;
            return true;
        } else {
            return false;
        }
    }

    public boolean movePrev() {
        if (current.prev != null) {
            current = current.prev;
            System.out.println("sdflkjds");
            return true;
        } else {
            return false;
        }
    }

    public void resetCompareCount() {
        compareCount = 0;
    }

    public void setCompareCount(int count) {
        compareCount = count;
    }

    public int getCompareCount() {
        return compareCount;
    }

    // current の後ろに新規データを挿入
    public void insert(String key, String data) {
        // current -> nanika
        // current -> newData -> nanika
        // newData.next == null <==> nanika = tail
        MyDoublyLinkedData newData = new MyDoublyLinkedData(key, data);
        newData.next = current.next; // new -> cur.next
        current.next.prev = newData; // new <- cur.next
        newData.prev = current; // cur <- new
        current.next = newData; // cur -> new
        if (newData.next == null) {
            tail = newData;
        }
        current = newData;
    }

    // 連結リストの末尾に新規データを挿入
    public void insertLast(String key, String data) {
        MyDoublyLinkedData newData = new MyDoublyLinkedData(key, data);
        newData.next = null;
        newData.prev = tail;
        tail.next = newData;

        tail = newData;
        current = newData;
    }

    public MyDoublyLinkedData searchByKey(String key) {
        MyDoublyLinkedData p = head;
        while (p != null) {
            if (strcmp(key, p.getKey()) == 0) {
                current = p;
                return p;
            }
            p = p.next;
        }
        current = head;
        return null;
    }

    public MyDoublyLinkedData searchByData(String data) {
        MyDoublyLinkedData p = head;
        while (p != null) {
            if (strcmp(data, p.getData()) == 0) {
                current = p;
                return p;
            }
            p = p.next;
        }
        current = head;
        return null;
    }

    public MyDoublyLinkedData delete() {
        if (current != null) {
            if (current.getKey().equals("*header*")) {
                return null;
            }
            MyDoublyLinkedData prevData = current.prev;
            MyDoublyLinkedData nextData = current.next;
            if (nextData != null) {
                prevData.next = nextData; // prev -> next
                nextData.prev = prevData; // prev <- next
            } else {
                prevData.next = null;
            }
            
            MyDoublyLinkedData oldCurrent = current;
            current = current.next;
            if (current == null) {
                tail = oldCurrent.prev;
                current = head;
            }
            return oldCurrent;
        } else {
            return null;
        }
    }

    public void printData() {
        MyDoublyLinkedData p = head; // 初めから
        while (p != null) { // 最後まで
            if (p == current) {
                System.out.print("*cur* ");
            } else {
                System.out.print("      ");
            }
            System.out.println(p.toStringSimple());
            p = p.next;
        }
    }

    public int strcmp(String key1, String key2) {
        compareCount++;
        return key1.compareTo(key2);
    }
}
