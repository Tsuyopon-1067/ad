import java.util.HashMap;
import java.util.Scanner;

class Rp8_11 {
    static class Instruction { // 実質構造体
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
    static final int FNDD = 20;

    static HashMap<String, Integer> commandMap = new HashMap<String, Integer>();

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
        commandMap.put("finddata", FNDD);
    }

    public static void main(String args[]) {
        run_interpreter();
    }

    public static void printTxt(String s) { // System.outを書きたくなかった
        System.out.print(s);
    }

    static void run_interpreter() { // 実質main
        MyBinaryTree mbt = new MyBinaryTree(); // 二分木本体
        mbt.rootnode = null;

        MyNode nd; // mbtから戻り値をもらうための受け皿
        Instruction inst; // コマンド解析

        Scanner sc = new Scanner(System.in);

        do {
            inst = read_instruction(sc); // コマンド・key・dataを読み取り・格納
            switch (inst.cmd) {
                case NOP:
                    break;
                case SHOW:
                    printTxt("key-index: ");
                    if (mbt.rootnode != null) {
                        mbt.rootnode.printTree(); // 木に格納されたデータを1行で表示(key)
                    }
                    printTxt("\n");
                    printTxt("data-index: ");
                    if (mbt.rootdatanode != null) {
                        mbt.rootdatanode.printTree(); // dataでも同じことをする
                    }
                    printTxt("\n");

                    break;
                case SHOW90:
                    printTxt("key-index:\n");
                    if (mbt.rootnode != null) {
                        mbt.rootnode.printTree90(); // keyを表示
                    }
                    printTxt("\n");
                    printTxt("data-index:\n");
                    if (mbt.rootdatanode != null) {
                        mbt.rootdatanode.printTree90(); // dataを表示
                    }
                    printTxt("\n");
                    break;
                case INS:
                    KeyAndData kd = new KeyAndData(inst.optkey, inst.optdata);
                    MyNode newnode1 = new MyNode(kd); // key木用
                    MyNode newnode2 = new MyNode(kd); // data木用
                    mbt.insertNode(newnode1);
                    mbt.insertNode2(newnode2);
                    break;
                case DEL:
                    nd = mbt.deleteNodeByKey(inst.optkey); // keyが一致するノードを削除
                    if (nd != null) {
                        printTxt(" |Node:");
                        nd.printThisNode();
                        printTxt(" deleted.\n");
                    } else {
                        printTxt(" |Key not found.\n");
                    }
                    break;
                case FIND:
                    nd = mbt.findNodeByKey(inst.optkey); // keyで検索
                    if (nd != null) {
                        printTxt(" |found:");
                        nd.printThisNode();
                        printTxt(" \n");
                    } else {
                        printTxt(" |Key not found.\n");
                    }
                    break;
                case FNDD:
                    nd = mbt.findNodeByData(inst.optkey); // dataで検索
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
    // ここから下はInstruction群
    static String promptAndReadLine(Scanner sc, String prompt) { // prompt:って出して読み取ったStringを返す
        System.err.print(prompt + ":");
        return sc.nextLine();
    }

    static void inputInstructionKey(Instruction inst, Scanner sc) { // key:って出して読み取ったStringを返す
        inst.optkey = promptAndReadLine(sc, " key");
    }

    static void inputInstructionData(Instruction inst, Scanner sc) { // data:って出して読み取ったStringを返す
        inst.optdata = promptAndReadLine(sc, "data");
    }

    static void inputInstructionKeyAndDataEachLine(Instruction inst, Scanner sc) { // keyとdataを取得
        inputInstructionKey(inst, sc);
        inputInstructionData(inst, sc);
    }

    static void setInstructionCommand(Instruction inst, String cmd) {
        if (commandMap.containsKey(cmd)) { // mapに登録されているコマンドならそれを数値にする
            inst.cmd = commandMap.get(cmd);
        } else {
            inst.cmd = NOP; // mapに登録されていないコマンドはNOPにする(何も起こらない)
        }
    }

    static void setInstructionKeyOneLine(Instruction inst, String[] param) { // keyと取得データを表示しつつkeyを取得
        System.err.print(" key:" + param[1] + "\n");
        inst.optkey = param[1];
    }

    static void setInstructionDataOneLine(Instruction inst, String[] param) { // dataと取得データを表示しつつdataを取得
        System.err.print("data:" + param[2] + "\n");
        inst.optdata = param[2];
    }

    static void setInstructionDataOneLineForFinddata(Instruction inst, String[] param) { // dataと取得データを表示しつつdataを取得(コマンド,dataの組に使う)
        System.err.print("data:" + param[1] + "\n");
        inst.optkey = param[1];
    }

    static void setInstructionKeyAndDataOneLine(Instruction inst, String[] param) { // keyとdataを取得
        setInstructionKeyOneLine(inst, param);
        setInstructionDataOneLine(inst, param);
    }

    static Instruction read_instruction(Scanner sc) {
        Instruction inst = new Instruction();
        try {
            String buf;
            String[] param;
            buf = promptAndReadLine(sc, "cmd"); // cmd:って出して読み取ったStringを返す
            param = buf.split(" ", 0); // 空白区切り
            setInstructionCommand(inst, param[0]); // コマンドを数値に変換
            if (inst.cmd == INS) { // keyとdataを取得する
                if (param.length > 2) {
                    setInstructionKeyAndDataOneLine(inst, param); // 当該行にちゃんと2データが揃っている場合
                } else {
                    inputInstructionKeyAndDataEachLine(inst, sc); // なければ2行追加で取る
                }
            } else if (inst.cmd == DEL || inst.cmd == FIND) { // 削除・検索するkeyを取得
                if (param.length > 1) {
                    setInstructionKeyOneLine(inst, param); // 当該行にあればそれを使う
                } else {
                    inputInstructionKey(inst, sc);
                }
            } else if (inst.cmd == FNDD) { // 検索するdataを取得
                if (param.length > 1) {
                    setInstructionDataOneLineForFinddata(inst, param);
                } else {
                    inputInstructionData(inst, sc);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            inst.cmd = NOP;
        }
        return inst;
    }
}
