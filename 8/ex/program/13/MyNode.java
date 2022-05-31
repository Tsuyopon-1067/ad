class MyNode {
    MyNode left;
    MyNode right;
    MyNode parent;
    KeyAndData keyAndData;
 
    public MyNode(String key, String data) {
        this.keyAndData = new KeyAndData(key,data);
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    
    static void printTxt(String s) {
        System.out.print(s);
    }
    
    void printTree() {
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
    
    void printTree90(int l) {
        if( right != null ) { right.printTree90(l+1); }
        for(int i=0; i<l*8; i++) { printTxt(" "); }
        printThisNode();
        printTxt("\n");
        if( left != null ) { left.printTree90(l+1); }
    }
    
    void printThisNode() {
        if( keyAndData == null ) {
            printTxt("(null)");
        } else {
            String key_i  = cutLongString(keyAndData.key,  10);
            String data_i = cutLongString(keyAndData.data, 10);
            printTxt("(" + key_i + ":" + data_i + ")");
        }
    }

    void printThisNodeDetails() {
        printTxt("node.parent= ");
        if( parent != null ) { parent.printThisNode(); }
        printTxt("\n");
        printTxt("node       = ");
        printThisNode(); printTxt("\n");
        printTxt("node.left  = ");
        if( left != null ) { left.printThisNode(); }
        printTxt("\n");
        printTxt("node.right = ");
        if( right != null ) { right.printThisNode(); }
        printTxt("\n");
    }
    
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
