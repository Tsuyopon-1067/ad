import java.util.HashMap;
import java.util.Scanner;

class Rp8_13 {
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
    static final int FINDRANGE = 10;
    static final int FINDDATA = 20;
    static final int FINDDATARANGE = 21;

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
        commandMap.put("findrange", FINDRANGE);
        commandMap.put("keyrange", FINDRANGE);
        commandMap.put("finddata", FINDDATA);
        commandMap.put("datarange", FINDDATARANGE);
    }

    public static void main(String args[]) {
        run_interpreter();
    }

    public static void printTxt(String s) {
        System.out.print(s);
    }

    static void run_interpreter() {
        MyBinaryTree mbt = new MyBinaryTree();
        mbt.rootnode = null;

        MyNode nd;
        Instruction inst;

        Scanner sc = new Scanner(System.in);

        do {
            inst = read_instruction(sc);
            switch (inst.cmd) {
            case NOP:
                break;
            case SHOW:
                printTxt("key-index: ");
                if (mbt.rootnode != null) {
                    mbt.rootnode.printTree();
                }
                printTxt("\n");
                printTxt("data-index: ");
                if (mbt.rootdatanode != null) {
                    mbt.rootdatanode.printTree();
                }
                printTxt("\n");

                break;
            case SHOW90:
                printTxt("key-index:\n");
                if (mbt.rootnode != null) {
                    mbt.rootnode.printTree90();
                }
                printTxt("\n");
                printTxt("data-index:\n");
                if (mbt.rootdatanode != null) {
                    mbt.rootdatanode.printTree90();
                }
                printTxt("\n");
                break;
            case INS:
                KeyAndData kd = new KeyAndData(inst.optkey, inst.optdata);
                MyNode newnode1 = new MyNode(kd);
                MyNode newnode2 = new MyNode(kd);
                mbt.insertNode(newnode1);
                mbt.insertNode2(newnode2);
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
            case FINDDATA:
                nd = mbt.findNodeByData(inst.optkey);
                if (nd != null) {
                    printTxt(" |found:");
                    nd.printThisNode();
                    printTxt(" \n");
                } else {
                    printTxt(" |Key not found.\n");
                }
                break;
            case FINDRANGE:
                mbt.initNodeBuf();
                // don't confuse! optkey=rmin, optdata=rmax
                mbt.findNodeByKeyRange(inst.optkey, inst.optdata);
                mbt.printAllInBuf();
                break;
            case FINDDATARANGE:
                mbt.initNodeBuf();
                // don't confuse! optkey=rmin, optdata=rmax
                mbt.findNodeByDataRange(inst.optkey, inst.optdata);
                mbt.printAllInBuf();
                break;
            }
        } while (inst.cmd != HALT);

        return;
    }

    static String promptAndReadLine(Scanner sc, String prompt) {
        System.err.print(prompt + ":");
        return sc.nextLine();
    }

    static void inputInstructionKey(Instruction inst, Scanner sc) {
        inst.optkey = promptAndReadLine(sc, " key");
    }

    static void inputInstructionData(Instruction inst, Scanner sc) {
        inst.optdata = promptAndReadLine(sc, "data");
    }

    static void inputInstructionMinAndMaxEachLine(Instruction inst, Scanner sc) {
        inst.optkey = promptAndReadLine(sc, "min");
        inst.optdata = promptAndReadLine(sc, "max");
    }

    static void inputInstructionKeyAndDataEachLine(Instruction inst, Scanner sc) {
        inputInstructionKey(inst, sc);
        inputInstructionData(inst, sc);
    }

    static void setInstructionCommand(Instruction inst, String cmd) {
        if (commandMap.containsKey(cmd)) {
            inst.cmd = commandMap.get(cmd);
        } else {
            inst.cmd = NOP;
        }
    }

    static void setInstructionKeyOneLine(Instruction inst, String[] param) {
        System.err.print(" key:" + param[1] + "\n");
        inst.optkey = param[1];
    }

    static void setInstructionDataOneLine(Instruction inst, String[] param) {
        System.err.print("data:" + param[2] + "\n");
        inst.optdata = param[2];
    }

    static void setInstructionDataOneLineForFinddata(Instruction inst, String[] param) {
        System.err.print("data:" + param[1] + "\n");
        inst.optkey = param[1];
    }

    static void setInstructionKeyAndDataOneLine(Instruction inst, String[] param) {
        setInstructionKeyOneLine(inst, param);
        setInstructionDataOneLine(inst, param);
    }

    static void setInstructionMinAndMaxOneLine(Instruction inst, String[] param) {
        System.err.print("min:" + param[1] + "\n");
        inst.optkey = param[1];
        System.err.print("max:" + param[2] + "\n");
        inst.optdata = param[2];
    }

    static Instruction read_instruction(Scanner sc) {
        Instruction inst = new Instruction();
        try {
            String buf;
            String[] param;
            buf = promptAndReadLine(sc, "cmd");
            param = buf.split(" ", 0);
            setInstructionCommand(inst, param[0]);
            if (inst.cmd == INS) {
                if (param.length > 2) {
                    setInstructionKeyAndDataOneLine(inst, param);
                } else {
                    inputInstructionKeyAndDataEachLine(inst, sc);
                }
            } else if (inst.cmd == DEL || inst.cmd == FIND) {
                if (param.length > 1) {
                    setInstructionKeyOneLine(inst, param);
                } else {
                    inputInstructionKey(inst, sc);
                }
            } else if (inst.cmd == FINDRANGE || inst.cmd == FINDDATARANGE) {
                if (param.length > 1) {
                    setInstructionMinAndMaxOneLine(inst, param);
                } else {
                    inputInstructionMinAndMaxEachLine(inst, sc);
                }
            } else if (inst.cmd == FINDDATA) {
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
