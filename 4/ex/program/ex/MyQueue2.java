class MyQueue2 {
    private MyNode front = null;
    private MyNode rear = null;
    private int size = 0;
    private MyNode sentinelNode;
    static final String SENTINEL = "*SENTINEL*";

    MyQueue2() {
        sentinelNode = new MyNode(SENTINEL);
        clear();
    }

    public void clear() {
        front = sentinelNode;
        rear = sentinelNode;

        front.next = rear;
        rear.prev = front;

        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(String name) {
        // your code
        MyNode newNode = new MyNode(name);
        newNode.prev = rear;
        newNode.next = front;
        rear.next = newNode;
        front.prev = newNode;
        rear = newNode;
        size++;
        return;
    }

    public MyNode dequeue() {
        if (size == 0) {
            return null;
        } else {
            MyNode res = front.next;
            MyNode newFrontNext = res.next;
            front.next = newFrontNext;
            newFrontNext.prev = front;
            size--;

            if (size == 0) {
                // your code
                // rear = front;
            }
            return res;
        }
    }

    public String toString() {
        MyNode pos = front.next;
        String s = "MyQueue2=[\n";
        while (pos != front) {
            s += "    " + pos + "\n";
            pos = pos.next;
        }
        s += "]\n  front=" + front + "\n  rear=" + rear + "\n\n";
        return s;
    }
}
