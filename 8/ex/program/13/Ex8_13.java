import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

class Ex8_13 {
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
    static final int UP = 6;
    static final int L = 7;
    static final int R = 8;
    static final int ROOT = 9;
    static final int FNDR = 10;
    static final int DMIN = 11;
    static final int DMAX = 12;

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
        commandMap.put("l", L);
        commandMap.put("left", L);
        commandMap.put("r", R);
        commandMap.put("right", R);
        commandMap.put("up", UP);
        commandMap.put("parent", UP);
        commandMap.put("root", ROOT);
        commandMap.put("findrange", FNDR);
        commandMap.put("delmin", DMIN);
        commandMap.put("delmax", DMAX);
    }

    public static void main(String args[]) {
        run_interpreter();
    }

    public static void printTxt(String s) {
        System.out.print(s);
    }

    public static void printCurrentnode(MyBinaryTree mbt) {
        if (mbt.currentnode != null) {
            mbt.currentnode.printThisNodeDetails();
            printTxt("\n");
        }
    }

    static void run_interpreter() {
        MyBinaryTree mbt = new MyBinaryTree();
        mbt.rootnode = null;

        MyNode nd;
        Instruction inst;

        boolean flag = false;

        Scanner sc = new Scanner(System.in);

        do {
            inst = read_instruction(sc);
            switch (inst.cmd) {
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
                mbt.resetCurrentnode();
                break;
            case DEL:
                nd = mbt.deleteNodeByKey(inst.optkey);
                if (nd != null) {
                    printTxt(" |Node:");
                    nd.printThisNode();
                    printTxt(" deleted.\n");
                    if (mbt.currentnode == nd) {
                        mbt.resetCurrentnode();
                    }
                } else {
                    printTxt(" |Key not found.\n");
                }
                break;
            case DMIN:
                nd = mbt.deleteMinNode();
                if (nd != null) {
                    printTxt(" |Node:");
                    nd.printThisNode();
                    printTxt(" deleted.\n");
                    if (mbt.currentnode == nd) {
                        mbt.resetCurrentnode();
                    }
                } else {
                    printTxt(" |no node.\n");
                }
                break;
            case DMAX:
                nd = mbt.deleteMaxNode();
                if (nd != null) {
                    printTxt(" |Node:");
                    nd.printThisNode();
                    printTxt(" deleted.\n");
                    if (mbt.currentnode == nd) {
                        mbt.resetCurrentnode();
                    }
                } else {
                    printTxt(" |no node.\n");
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
            case FNDR:
                mbt.initNodeBuf(); // ArrayListをclearする
                mbt.findNodeByRange(inst.optkey, inst.optdata);
                mbt.printAllInBuf();
                break;
            case UP:
                flag = mbt.moveParent();
                if (flag == false) {
                    printTxt("no parent node.\n");
                }
                printCurrentnode(mbt);
                break;
            case L:
                flag = mbt.moveLeft();
                if (flag == false) {
                    printTxt("no left node.\n");
                }
                printCurrentnode(mbt);
                break;
            case R:
                flag = mbt.moveRight();
                if (flag == false) {
                    printTxt("no right node.\n");
                }
                printCurrentnode(mbt);
                break;
            case ROOT:
                mbt.resetCurrentnode();
                printCurrentnode(mbt);
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

    static void inputInstructionKeyAndDataEachLine(Instruction inst, Scanner sc) {
        inputInstructionKey(inst, sc);
        inputInstructionData(inst, sc);
    }

    static void inputInstructionMinAndMaxEachLine(Instruction inst, Scanner sc) {
        inst.optkey = promptAndReadLine(sc, "min");
        inst.optdata = promptAndReadLine(sc, "max");
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
            } else if (inst.cmd == FNDR) {
                if (param.length > 1) {
                    setInstructionMinAndMaxOneLine(inst, param);
                } else {
                    inputInstructionMinAndMaxEachLine(inst, sc);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            inst.cmd = NOP;
        }
        return inst;
    }
}
