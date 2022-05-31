public class Ex3_11 {
    static int isEmptyCount = 0;
    static int isEmptyCorrectCount = 0;
    static int isFullCount = 0;
    static int isFullCorrectCount = 0;
    static int pushCount = 0;
    static int pushCorrectCount = 0;
    static int popCount = 0;
    static int popCorrectCount = 0;
 
    static public void resetCount(){
        isEmptyCount = 0; // isEmpty判定関数カウント
        isEmptyCorrectCount = 0; // 正解数カウント
        isFullCount = 0;
        isFullCorrectCount = 0;
        pushCount = 0;
        pushCorrectCount = 0;
        popCount = 0;
        popCorrectCount = 0;
    }
 
    static public boolean test(String label, int result, int estimated) { // hoge : result[hoge] estimated[hoge]
        System.out.print(label + ": result[" + result + "] estimated[" + estimated + "] "); // これテストするよ(宣言風)
        if (result == estimated) { System.out.println("success."); return true;} // 想定通りならtrue
        else                     { System.out.println("failure."); return false;}
 
    }
 
    static public boolean test(String label, boolean result, boolean estimated) {
        System.out.print(label + ": result[" + result + "] estimated[" + estimated + "] ");
        if (result == estimated) { System.out.println("success."); return true;}
        else                     { System.out.println("failure."); return false;}
 
    }
 
    static public void isEmptyTest(MyStack ms, boolean estimated) { // 
        isEmptyCount++;
        if (test("isEmpty", ms.isEmpty(), estimated)) isEmptyCorrectCount++;
    }
 
    static public void isFullTest(MyStack ms, boolean estimated) {
        isFullCount++;
        if (test(" isFull", ms.isFull(), estimated)) isFullCorrectCount++;
    }
 
    static public void pushTest(MyStack ms, int data, boolean estimated) {
        pushCount++;
        if (test("   push("+data+")" , ms.push(data), estimated)) pushCorrectCount++;
    }
 
    static public void popTest(MyStack ms, int estimated) {
        popCount++;
        if (test("    pop", ms.pop(), estimated)) popCorrectCount++;
    }
 
    static public void printTestResult(String label, int count, int total) { // 項目ごと
        System.out.print(label + ": " + count + "/" + total + " ");
        if (count == total) System.out.println("OK");
        else                System.out.println("NG");
    }
 
    static public void printAllTestResult() {
        printTestResult("isEmpty", isEmptyCorrectCount, isEmptyCount);
        printTestResult("   push", pushCorrectCount   , pushCount);
        printTestResult("    pop", popCorrectCount    , popCount);
    }
 
    static public void printCase(int n) {
        System.err.println("Case " + n);
    }
    static public void testCase1() {
        printCase(1);
        MyStack ms = new MyStack(3);
    }
    static public void main(String args[]) {
        MyStack ms = new MyStack(3);
        isEmptyTest(ms, true);  //stack[] is empty
        pushTest(ms, 10, true); //stack[10]
        pushTest(ms, 5, true);  //stack[10,5]
        popTest(ms, 5);         //stack[10]
        isEmptyTest(ms, false); //stack[10] is not empty
        pushTest(ms, 1, true);  //stack[10,1]
        pushTest(ms, 6, true);  //stack[10,1,6]
        isFullTest(ms, true);   //stack[10,1,6] is full
        pushTest(ms, 5, false); //stack[10,1,6] because stack size is 3
        popTest(ms, 6);         //stack[10,1]
        popTest(ms, 1);         //stack[10]
        isEmptyTest(ms, false); //stack[10] is not isEmpty
        popTest(ms, 10);        //stack[]
        popTest(ms, -2147483648);  //pop returns INT_MIN if stack is empty
 
        printAllTestResult();
    }
}