import java.util.Scanner;
class Rp4_4 {

    public static void main(String[] args) {
        MyQueue4 q = new MyQueue4();

        String cmdLine = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("command: ");
            cmdLine = sc.nextLine();
            String[] cmds = cmdLine.split(" ", 3); // 3つに分割
            int len = cmds.length;

            String cmd = ""; // コマンドの1要素目
            if (len >= 1) cmd = cmds[0].toLowerCase(); // コマンドは小文字にする
            if (len < 2) {
                if (len == 1 && cmds[0].equals("quit")) break;
                System.out.println("Unknown command or too less arguments.");
                continue;
            }
            // ここからは2要素以上
            if (!(cmd.equals("enqueue") || cmd.equals("dequeue"))) {
                System.out.println("Unknown command.");
                continue;
            }
            // ここからは意味のあるコマンドしか来ない
            int num;
            try {
                num = Integer.parseInt(cmds[1]);
            } catch (NumberFormatException e) {
                System.out.println("Second command has to be a number.");
                continue;
            }
            // ここからは学籍番号は数値のみ
            if (cmd.equals("enqueue")) {
                if (len != 3) {
                    System.out.println("The arguments have to be two and student number and text.");
                    continue;
                }
                q.enqueue(cmds[2], num);
            } else if (cmd.equals("dequeue")) {
                if (len != 2) {
                    System.out.println("The arguments have to be one and student number.");
                    continue;
                }
                String txt = q.dequeue(num);
                if (!txt.equals(q.DEQUEUEERROR)) {
                    txt = "printing...\n===========\n" + txt + "\n===========";
                } else {
                    txt = "No file in the queue.";
                }
                System.out.println(txt);
            }
            
        } while (!cmdLine.equals("quit"));
    }
}