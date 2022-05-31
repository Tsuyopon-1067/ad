public class Ex3_12 {
    static int isEmptyCount = 0;
    static int isEmptyCorrectCount = 0;
    static int isFullCount = 0;
    static int isFullCorrectCount = 0;
    static int pushCount = 0;
    static int pushCorrectCount = 0;
    static int popCount = 0;
    static int popCorrectCount = 0;

    static public void resetCount() {
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
        if (result == estimated) {
            System.out.println("success.");
            return true;
        } // 想定通りならtrue
        else {
            System.out.println("failure.");
            return false;
        }

    }

    static public boolean test(String label, boolean result, boolean estimated) {
        System.out.print(label + ": result[" + result + "] estimated[" + estimated + "] ");
        if (result == estimated) {
            System.out.println("success.");
            return true;
        } else {
            System.out.println("failure.");
            return false;
        }

    }

    static public void isEmptyTest(MyStack ms, boolean estimated) { //
        isEmptyCount++;
        if (test("isEmpty", ms.isEmpty(), estimated))
            isEmptyCorrectCount++;
    }

    static public void isFullTest(MyStack ms, boolean estimated) {
        isFullCount++;
        if (test(" isFull", ms.isFull(), estimated))
            isFullCorrectCount++;
    }

    static public void pushTest(MyStack ms, int data, boolean estimated) {
        pushCount++;
        if (test("   push(" + data + ")", ms.push(data), estimated))
            pushCorrectCount++;
    }

    static public void popTest(MyStack ms, int estimated) {
        popCount++;
        if (test("    pop", ms.pop(), estimated))
            popCorrectCount++;
    }

    static public void printTestResult(String label, int count, int total) { // 項目ごと
        System.out.print(label + ": " + count + "/" + total + " ");
        if (count == total)
            System.out.println("OK");
        else
            System.out.println("NG");
    }

    static public void printAllTestResult() {
        printTestResult("isFull", isFullCorrectCount, isFullCount);
        printTestResult("isEmpty", isEmptyCorrectCount, isEmptyCount);
        printTestResult("   push", pushCorrectCount, pushCount);
        printTestResult("    pop", popCorrectCount, popCount);
    }

    static public void printCase(int n) {
        System.err.println("Case " + n + " --------------------------------------------------");
    }

    static public void testCase1() {
        resetCount();
        printCase(1);
        int array[] = { 1, 2, 3, 4, 5 };
        MyStack ms = new MyStack(5);

        // test push
        for (int i = 0; i < 5; i++) {
            pushTest(ms, array[i], true);
        }
        // test pop
        for (int i = 4; i >= 0; i--) {
            popTest(ms, array[i]);
        }

        printAllTestResult();
    }

    static public void testCase2() {
        resetCount();
        printCase(2);
        MyStack ms = new MyStack(5);

        // test push
        for (int i = 0; i < 5; i++) {
            pushTest(ms, 16777216, true); // ここまではpushできる
        }
        pushTest(ms, 16777216, false); // たぶんpush失敗する

        printAllTestResult();
    }

    static public void testCase3() {
        resetCount();
        printCase(3);
        MyStack ms = new MyStack(10);

        // test push
        for (int i = 0; i < 10; i++) {
            pushTest(ms, i, true);
        }

        // test pop
        for (int i = 9; i >= 0; i--) {
            popTest(ms, i); // ここまではpopできる
        }
        popTest(ms, -2147483648); // 多分pop失敗する

        printAllTestResult();
    }

    static public void testCase4() {
        resetCount();
        printCase(4);
        MyStack ms = new MyStack(100);

        // test push
        for (int i = 0; i < 100; i++) {
            pushTest(ms, i, true);
        }

        // test isFull
        isFullTest(ms, true); // さっきのでお腹いっぱい

        // test pop
        for (int i = 99; i >= 0; i--) {
            popTest(ms, i); // ここまではpopできる
        }
        isEmptyTest(ms, true); // 餓死しそう

        printAllTestResult();
    }

    static public void main(String args[]) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }
}