class MyStack {
    static final int STACKSIZE = 30;
    static final int POPERROR = -2147483648;

    private int sp = 0;
    private int[] stack;

    MyStack() {
        this(STACKSIZE);
    }

    MyStack(int size) {
        stack = new int[size];
    }

    public int pop() {
        if (isEmpty()) {
            return POPERROR;
        }
        sp--;
        return stack[sp];
    }

    public boolean push(int data) { // push成功ならtrue
        if (isFull()) {
            return false;
        }
        stack[sp] = data;
        sp++;
        return true;
    }

    public boolean isEmpty() { // 空ならtrue
        if (sp == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() { // 空ならtrue
        if (sp == stack.length) { // push前のspは次に挿入するインデックス
            return true;
        }
        return false;
    }
}