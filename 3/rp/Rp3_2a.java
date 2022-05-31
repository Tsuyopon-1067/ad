import java.util.Scanner;

public class Rp3_2a {
    class InterpreterResult {
        int cmd;
        int op;

        InterpreterResult(int cmd, int op) { // cmd識別番号, push値
            this.cmd = cmd;
            this.op = op;
        }

        InterpreterResult(int cmd) {
            this.cmd = cmd;
            this.op = 0;
        }
    }

    public InterpreterResult commandInterpreter(Scanner sc) {
        try {
            System.err.print("cmd:");
            String buf = sc.next();
            if (buf.equals("pop")) {
                return new InterpreterResult(1);
            } else if (buf.equals("push")) {
                return new InterpreterResult(2, sc.nextInt());
            } else if (buf.equals("add")) {
                return new InterpreterResult(3);
            } else if (buf.equals("sub")) {
                return new InterpreterResult(4);
            } else if (buf.equals("mul")) {
                return new InterpreterResult(5);
            } else if (buf.equals("div")) {
                return new InterpreterResult(6);
            } else if (buf.equals("run")) {
                return new InterpreterResult(7);
            } else if (buf.equals("end")) {
                return new InterpreterResult(-1);
            }
        } catch (Exception e) {
            System.err.println(e);
            return new InterpreterResult(-2);
        }
        return null;
    }

    // 実際に登録してくれる
    public boolean commandRegistration(MyMemory stack, InterpreterResult ir) {
        switch (ir.cmd) {
        case 2:
            stack.regist(ir.cmd);  
            stack.regist(ir.op);
            return false; // 継続
        case -1: // end
        case 1: // pop
        case 3: // add
        case 4: // sub
        case 5: // mul
        case 6: // div
        stack.regist(ir.cmd);
            return false; // 継続
        case 7: // end
            commandExecution(stack); // run
        }
        return true;        
    }

    public void commandExecution(MyMemory stack) {
        int op = 0;
        int operand1;
        int operand2;

        stack.resetPC(); // 開始地点に戻す
        op = stack.read(); // 頭から読んでいく
        while (op != -1) {
            // System.out.println(stack);
            //System.out.printf("op:%d sp:%d pc:%d\n", op, stack.getSp(), stack.getPc());
            /*
            int[] tmp = stack.getStack();
            for (int i = 0; i < tmp.length; i++) {
                System.out.printf("%d ", tmp[i]);
                if (i % 10 == 9) System.out.print("|");
            }
            System.out.println();*/

            switch (op) {
            case 1:
                op = stack.pop();
                System.out.println("POP " + op);
                break;
            case 2:
                op = stack.read(); // pushしたい値を読み込む
                stack.push(op);
                System.out.println("PUSH " + op);
                break;
            case 3:
                operand2 = stack.pop();
                operand1 = stack.pop();
                op = operand1 + operand2;
                stack.push(op);
                System.out.println("ADD (" + operand1 + " " + operand2 + ")");
                break;
            case 4:
                operand2 = stack.pop();
                operand1 = stack.pop();
                op = operand1 - operand2;
                stack.push(op);
                System.out.println("SUB (" + operand1 + " " + operand2 + ")");
                break;
            case 5:
                operand2 = stack.pop();
                operand1 = stack.pop();
                op = operand1 * operand2;
                stack.push(op);
                System.out.println("MUL (" + operand1 + " " + operand2 + ")");
                break;
            case 6:
                operand2 = stack.pop();
                operand1 = stack.pop();
                op = operand1 / operand2;
                stack.push(op);
                System.out.println("DIV (" + operand1 + " " + operand2 + ")");
                break;
            }
            op = stack.read(); // 次のを読む
        }
    }

    public void interpreter() {
        boolean endFlag = false;
        String buf;
        int cmd = 0, op = 0;
        int operand1 = 0, operand2 = 0;
        //MyMemory stack = new MyMemory(20, 10);
        MyMemory stack = new MyMemory(50, 10);
        Scanner sc = new Scanner(System.in);
        while (endFlag == false) {
            InterpreterResult ir;
            ir = commandInterpreter(sc); // ir:コマンド識別番号とpush値(あれば)を保持する
            System.err.println("cmd = " + ir.cmd + " op = " + ir.op);
            endFlag = commandRegistration(stack, ir); // flag操作と同時にstackに突っ込んだりする
            // System.out.println(stack);
            //System.out.printf("op:%d sp:%d pc:%d\n", op, stack.getSp(), stack.getPc());
        }
    }

    public static void main(String args[]) {
        Rp3_2a mainObj = new Rp3_2a();
        mainObj.interpreter();
    }
}