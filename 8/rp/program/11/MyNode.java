class MyNode {
    MyNode left;
    MyNode right;
    MyNode parent;
    KeyAndData keyAndData; // keyとdataの2つをもつ
 
    public MyNode(String key, String data) {
        this.keyAndData = new KeyAndData(key,data);
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    
    public MyNode(KeyAndData kd) {
        this.keyAndData = kd;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    static void printTxt(String s) {
        System.out.print(s);
    }
    
    void printTree() { // 1行にノードを出力 [左 親 右]
        printTxt("[");
        if( left != null ) { left.printTree(); }
        printTxt(" ");
        printThisNode();
        printTxt(" ");
        if( right != null ) { right.printTree(); }
        printTxt("]");
    }

    void printTree90() {
        printTree90(0);
    }
    

/*
 * printTree90メソッド
 * @param l インデントの深さ
 * thisをrootとした部分木を8マスインデントしながら出力する
 */
    void printTree90(int l) {
        if( right != null ) { right.printTree90(l+1); }
        for(int i=0; i<l*8; i++) { printTxt(" "); }
        printThisNode();
        printTxt("\n");
        if( left != null ) { left.printTree90(l+1); }
    }
    
    void printThisNode() { // key:dataを出力
        if( keyAndData == null ) {
            printTxt("(null)");
        } else {
            String key_i  = cutLongString(keyAndData.key,  10); // 10文字以下に減らす
            String data_i = cutLongString(keyAndData.data, 10);
            printTxt("(" + key_i + ":" + data_i + ")");
        }
    }

/*
 * cutLongStringメソッド
 * @param str カットする文字列
 * @param length strをlength字以下にカットする
 * @return カットされたstr
 */
    String cutLongString(String str, int length) {
        if (str == null) {
            return "(null)";
        }
        if (str.length() > length) {
            return str.substring(0,length);
        } else {
            return str;
        }
    }


}
