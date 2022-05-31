class MyMemory2 {
    static final int CODESIZE = 30;
    static final int STACKSIZE = 30;
    static final int DATASIZE = 30;

    private int codeSize;
    private int stackSize;
    private int dataSize;

    private int[] stack;
    private int sp;
    private int pc;
    private int dp;

    MyMemory2() {
        this(CODESIZE, STACKSIZE, DATASIZE);
    }

    MyMemory2(int codeSize, int stackSize, int dataSize) {
        stack = new int[codeSize + stackSize + dataSize];
        this.sp = codeSize;
        this.pc = 0;
        this.dp = codeSize + stackSize; // データ保存
        this.codeSize = codeSize;
        this.stackSize = stackSize;
        this.dataSize = dataSize;
    }

    public int pop() {
        sp--;
        return stack[sp];
    }

    public void push(int data) {
        stack[sp] = data;
        sp++;
    }

    public void regist(int data) {
        stack[pc] = data;
        pc++;
    }

    public int read() {
        int c = stack[pc];
        pc++;
        return c;
    }

    public void resetPC() {
        pc = 0;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public int load(int ptr) {
        return stack[dp + ptr];
    }

    public void save(int ptr, int data) {
        stack[dp + ptr] = data;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < pc; i++) {
            str = str + "[" + i + "]: " + stack[i] + "\n";
        }
        for (int i = codeSize; i < sp; i++) {
            str = str + "[" + i + "]: " + stack[i] + "\n";
        }
        for (int i = dp; i < stack.length; i++) {
            str = str + "[" + i + "]: " + stack[i] + "\n";
        }
        return str;
    }
    // 以下デバッグ用
    public void show() {
        for (int i = 0; i < codeSize; i++) {
            System.out.printf("%2d ", stack[i]);
        }
        System.out.print("| ");
        for (int i = codeSize; i < codeSize+stackSize; i++) {
            System.out.printf("%2d ", stack[i]);
        }
        System.out.print("| ");
        for (int i = codeSize+stackSize; i < codeSize+stackSize+dataSize; i++) {
            System.out.printf("%2d ", stack[i]);
        }
        System.out.println();
    }
}
