import java.util.Scanner;

class Ex07 {
    static public void main(String[] argv) {
        Scanner scan = new Scanner(System.in);
        MyTreeNode node = null;
        while(true) {
            System.out.print("command: ");
            String str = scan.nextLine();

            if ("quit".equals(str)) break; // quitが入力されたら終了

            if (str.length()>5 && "token ".equals(str.substring(0,6))) {
                MyTokenizer tokenizer = new MyTokenizer(str.substring(6));
                System.out.print("  token: ");
                while(tokenizer.hasNextToken()) {
                    System.out.print("" + tokenizer.nextToken() + " ");
                }
                System.out.println("");
            } else if (str.length()>3 && "new ".equals(str.substring(0,4))) {
                MyTokenizer tokenizer = new MyTokenizer(str.substring(4)); // 4(5)文字目から先
                node = tokenizer.parse();
                System.out.print("  node : ");
                if (node != null) node.preOrderPrint();
                System.out.println("");
            } else if ("pre".equals(str)) {
                System.out.print("  pre order : ");
                if (node != null) node.preOrderPrint();
                System.out.println("");
            } else if ("in".equals(str)) {
                System.out.print("   in order : ");
                if (node != null) node.inOrderPrint();
                System.out.println("");
            } else if ("post".equals(str)) {
                System.out.print(" post order : ");
                if (node != null) node.postOrderPrint();
                System.out.println("");
            }
        }
    }
}
