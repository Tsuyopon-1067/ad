import java.util.HashMap;
import java.util.Scanner;

class Ex8_11 {
    static class Instruction {
        int cmd;
        String optkey;
        String optdata;
    }

    static final int NOP = 0;
    static final int HALT = 1;
    static final int SHOW = 2;
    static final int SHOW90 = 90;
    static final int INS = 3;
    static final int DEL = 4;
    static final int FIND = 5;

    static HashMap<String, Integer> commandMap = new HashMap<String, Integer>(); // コマンドの文字列とintを対応付ける

    static {
        commandMap.put("nop", NOP);
        commandMap.put("halt", HALT);
        commandMap.put("show", SHOW);
        commandMap.put("show90", SHOW90);
        commandMap.put("ins", INS);
        commandMap.put("insert", INS);
        commandMap.put("del", DEL);
        commandMap.put("delete", DEL);
        commandMap.put("find", FIND);
        commandMap.put("search", FIND);
    }

    public static void main(String args[]) {
        run_interpreter(); // 2つ下
    }

    public static void printTxt(String s) {
        System.out.print(s);
    }

    static void run_interpreter() { // 実質mainメソッド
        MyBinaryTree mbt = new MyBinaryTree();
        mbt.rootnode = null;

        MyNode nd;
        Instruction inst; // 実質構造体 int cmd, String optkey, String optdata

        Scanner sc = new Scanner(System.in);

        do {
            inst = read_instruction(sc); // 構造体もどきをもらう 
            switch (inst.cmd) { // NOP, SHOW, SHOW90, INS, DEL, FIND, HALT
                case NOP:
                    break;
                case SHOW:
                    if (mbt.rootnode != null) {
                        mbt.rootnode.printTree();
                    }
                    printTxt("\n");
                    break;
                case SHOW90:
                    if (mbt.rootnode != null) {
                        mbt.rootnode.printTree90();
                    }
                    printTxt("\n");
                    break;
                case INS:
                    MyNode newNode = new MyNode(inst.optkey, inst.optdata);
                    mbt.insertNode(newNode);
                    break;
                case DEL:
                    nd = mbt.deleteNodeByKey(inst.optkey);
                    if (nd != null) {
                        printTxt(" |Node:");
                        nd.printThisNode();
                        printTxt(" deleted.\n");
                    } else {
                        printTxt(" |Key not found.\n");
                    }
                    break;
                case FIND:
                    nd = mbt.findNodeByKey(inst.optkey);
                    if (nd != null) {
                        printTxt(" |found:");
                        nd.printThisNode();
                        printTxt(" \n");
                    } else {
                        printTxt(" |Key not found.\n");
                    }
                    break;
            }
        } while (inst.cmd != HALT);

        return;
    }

    static String promptAndReadLine(Scanner sc, String prompt) {
        System.err.print(prompt + ":");
        return sc.nextLine();
    }

    static void inputInstructionKey(Instruction inst, Scanner sc) { // optkeyをセット
        inst.optkey = promptAndReadLine(sc, " key");
    }

    static void inputInstructionData(Instruction inst, Scanner sc) { // optdataをセット
        inst.optdata = promptAndReadLine(sc, "data");
    }

    static void inputInstructionKeyAndDataEachLine(Instruction inst, Scanner sc) { // 
        inputInstructionKey(inst, sc); // 2つ上
        inputInstructionData(inst, sc); // 1つ上
    }

    static void setInstructionCommand(Instruction inst, String cmd) { // instにcmdに該当するデータを入れる
        if (commandMap.containsKey(cmd)) {
            inst.cmd = commandMap.get(cmd); // mapで該当する数値をcmdに入れる
        } else {
            inst.cmd = NOP; // 該当しなければNOP(=0)を入れる
        }
    }

    static void setInstructionKeyOneLine(Instruction inst, String[] param) { // optkeyをセット
        System.err.print(" key:" + param[1] + "\n");
        inst.optkey = param[1];
    }

    static void setInstructionDataOneLine(Instruction inst, String[] param) { // optdataをセット
        System.err.print("data:" + param[2] + "\n");
        inst.optdata = param[2];
    }

    static void setInstructionKeyAndDataOneLine(Instruction inst, String[] param) { // paramに対応するデータをinstにいれる
        setInstructionKeyOneLine(inst, param); // 2つ上
        setInstructionDataOneLine(inst, param); // 1つ上
    }

    static Instruction read_instruction(Scanner sc) { // 構造体もどきを返す
        Instruction inst = new Instruction();
        try {
            String buf;
            String[] param;
            buf = promptAndReadLine(sc, "cmd"); // "cmd"をコンソールに出力してreadLine
            param = buf.split(" ", 0); // 配列に突っ込む
            setInstructionCommand(inst, param[0]); // param[0]がコマンドに該当する instにcmdに該当するデータを入れる
            if (inst.cmd == INS) {
                if (param.length > 2) { // INS optkey optdata
                    setInstructionKeyAndDataOneLine(inst, param); // paramに対応するデータをinstにいれる(1行)
                } else {
                    inputInstructionKeyAndDataEachLine(inst, sc); // 空白区切りでoptkey, optdataがないときは次の行から読み込み
                }
            } else if (inst.cmd == DEL || inst.cmd == FIND) {
                if (param.length > 1) {
                    setInstructionKeyOneLine(inst, param); // optkeyをセット
                } else {
                    inputInstructionKey(inst, sc); // 
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            inst.cmd = NOP;
        }
        return inst;
    }
}
