class Ex4_11 {
    static int isEmptyCount = 0;
    static int isEmptyCorrectCount = 0;
    static int isFullCount = 0;
    static int isFullCorrectCount = 0;
    static int enqueueCount = 0;
    static int enqueueCorrectCount = 0;
    static int dequeueCount = 0;
    static int dequeueCorrectCount = 0;

    static public void resetCount() {
        isEmptyCount = 0;
        isEmptyCorrectCount = 0;
        isFullCount = 0;
        isFullCorrectCount = 0;
        enqueueCount = 0;
        enqueueCorrectCount = 0;
        dequeueCount = 0;
        dequeueCorrectCount = 0;
    }

    static public boolean test(String label, String result, String estimated) { // hoge : result[hoge] estimated[hoge]
        System.out.print(label + ": result[" + result + "] estimated[" + estimated + "] "); // これテストするよ(宣言風)
        if (result.equals(estimated)) {
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

    static public void enqueueTest(MyQueue mq, String data, boolean estimated) {
        enqueueCount++;
        if (test("   enqueue(" + data + ")", mq.enqueue(data), estimated))
            enqueueCorrectCount++;
    }

    static public void dequeueTest(MyQueue mq, String estimated) {
        dequeueCount++;
        if (test("   dequeue()", mq.dequeue(), estimated))
            dequeueCorrectCount++;
    }

    static public void isEmptyTest(MyQueue mq, boolean estimated) { //
        isEmptyCount++;
        if (test("   isEmpty", mq.isEmpty(), estimated))
            isEmptyCorrectCount++;
    }

    static public void printTestResult(String label, int count, int total) { // 項目ごと
        System.out.print(label + ": " + count + "/" + total + " ");
        if (count == total)
            System.out.println("OK");
        else
            System.out.println("NG");
    }

    static public void printAllTestResult() {
        printTestResult("enqueue", enqueueCount, enqueueCorrectCount);
        printTestResult("dequeue", dequeueCount, dequeueCorrectCount);
        printTestResult("isEmpty", isEmptyCorrectCount, isEmptyCount);
    }

    static public void printCase(int n) {
        System.err.println("Case " + n + "---------------------------");
    }

    static void testCase1() {
        printCase(1);
        MyQueue q = new MyQueue(5);
        enqueueTest(q, "a", true);
        enqueueTest(q, "b", true);
        enqueueTest(q, "c", true);
        enqueueTest(q, "d", true);
        enqueueTest(q, "e", true);
        enqueueTest(q, "f", false);
        isEmptyTest(q, false);
        dequeueTest(q, "a");
        dequeueTest(q, "b");
        dequeueTest(q, "c");
        dequeueTest(q, "d");
        dequeueTest(q, "e");
        dequeueTest(q, q.DEQUEUEERROR);
        isEmptyTest(q, true);
    }

    public static void main(String[] args) {
        // オリジナル MyQueue.java にあった，テスト用のメインルーチン
        testCase1();
        printAllTestResult();
    }
}