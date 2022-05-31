class MyStack {
    static final int STACKSIZE = 30;
 
    private int[] stack;
    private int sp;
    // コンストラクタx3
    MyStack() {
        this(STACKSIZE, 0);
    }
 
    MyStack(int size) {
        this(size, 0);
    }

    MyStack(int size, int start) {
        stack = new int[size];
        sp = start;
    }
 
    public int pop() {
        sp--;
        return stack[sp];
    }
 
    public void push(int data) {
        stack[sp] = data;
        sp++;
    }
}