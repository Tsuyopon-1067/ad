public class Ex3_22 {
    static void solve(int n, String c) {
        MyStack stack = new MyStack(100);

        MyTokenList tokenList = new MyTokenList(c);

        for (int i = 0; i < tokenList.size(); i++) {
            // tokenList.show();
            MyToken t = tokenList.get(i);
            if (t.isOperator()) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                int num12 = 0;
                switch (t.getOperator()) {
                    case '+':
                        num12 = num1 + num2;
                        break;
                    case '-':
                        num12 = num1 - num2;
                        break;
                    case '*':
                        num12 = num1 * num2;
                        break;
                    case '/':
                        num12 = num1 / num2;
                        break;
                }
                stack.push(num12);
            } else if (t.isDigit()) {
                stack.push(t.getDigit());
            }
        }

        System.out.println("Case" + n + ": result: " + stack.pop());

    }
    public static void main(String[] args) {
        solve(1, "12345679 81 * 3 /");
        solve(2, "1 2 3 4 5 6 7 8 9 10 + + + + + + + + +" );
        solve(3, "400 400 3 / - 400 5 / + 400 7 / - 400 9 / + 400 11 / -" );
    }
}
