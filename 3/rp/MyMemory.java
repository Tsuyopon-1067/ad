class MyMemory {
    static final int CODESIZE = 30; // 追加
    static final int STACKSIZE = 30;

    private int codeSize; // 追加
    private int stackSize; // 追加

    private int[] stack;
    private int sp;
    private int pc; // 追加
 
    MyMemory() {
        this(CODESIZE, STACKSIZE);
    }
 
    MyMemory(int codeSize, int stackSize) {
        stack = new int[codeSize + stackSize];
        this.sp = codeSize; // stack部分の頭を指す
        this.pc = 0; // プログラムカウンタ？
        this.codeSize = codeSize;
        this.stackSize = stackSize;
    }
 
    public int pop() {
        sp--;
        return stack[sp];
    }
 
    public void push(int data) {
        stack[sp] = data;
        sp++;
    }

    public void regist(int data) { // 追加 レジストリ追加?
        stack[pc] = data;
        pc++;
    }

    public int read() { // 追加 
        int c = stack[pc];
        pc++;
        return c;
    }

    public void resetPC() { // 追加
        pc = 0;
    }

    public String toString(){ // 追加
        String str = "";
        for(int i=0; i<pc; i++) {
            str = str + "[" + i + "]: " + stack[i] + "\n";
        }
        for(int i=codeSize; i<sp; i++) {
            str = str + "[" + i + "]: " + stack[i] + "\n";
        }
        return str;
    }
    // 以下デバッグ用
    public int[] getStack() {return stack;}
    public int getSp() {return sp;}
    public int getPc() {return pc;}
}