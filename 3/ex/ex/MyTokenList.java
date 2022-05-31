class MyTokenList {
    private int size;
    private MyToken[] tokenList;

    MyTokenList(String data) {
        String list[] = data.split(" "); // 空白区切り
        size = list.length;
        tokenList = new MyToken[size]; // 区切ったやつを突っ込む
        for (int i = 0; i < size; i++) {
            tokenList[i] = new MyToken(list[i]); // コンストラクタがいい感じにセットしてくれる
        }
    }

    public int size() {
        return size;
    }

    public MyToken get(int i) {
        if (0 <= i && i <= size - 1) {
            return tokenList[i];
        } else {
            return null;
        }
    }
    public void show() {
        for (int i = 0; i < tokenList.length; i++) {
            System.out.printf("%d ", tokenList[i].getDigit());
        }
        System.out.println();
    }
}