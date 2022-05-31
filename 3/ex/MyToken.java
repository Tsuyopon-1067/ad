class MyToken {
    static public final int OPERATOR = 1;
    static public final int DIGIT = 2;
    private int type; // 演算子化数値どっち？
    private int digit; // 数値を保存
    private char operator; // 演算子の種類

    MyToken(String data) {
        char c = ' ';

        if (data.length() > 0) { // 空じゃなければ1文字目を入れる
            c = data.charAt(0);
        }

        if (c == '-' || c == '+' || c == '*' || c == '/') {
            type = OPERATOR;
            operator = c;
            digit = 0;
        } else { // 演算子でなければ数字と決め打ち
            type = DIGIT;
            digit = Integer.parseInt(data); // intに変換
            operator = ' ';
        }
    }

    public boolean isOperator() { // ありのままに返す
        if (type == OPERATOR) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDigit() {
        if (type == DIGIT) {
            return true;
        } else {
            return false;
        }
    }

    public char getOperator() {
        return operator;
    }

    public int getDigit() {
        return digit;
    }
}