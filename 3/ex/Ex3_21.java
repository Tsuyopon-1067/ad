public class Ex3_21 {
    public static void main(String[] args) {
        MyStack stack = new MyStack(100);

        String c = "1 2 + 3 4 - *";

        MyTokenList tokenList = new MyTokenList(c);

        for (int i = 0; i < tokenList.size(); i++) {
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

        System.out.println("result: " + stack.pop());

    }
}
