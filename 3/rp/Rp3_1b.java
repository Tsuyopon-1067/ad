import java.util.Scanner;

public class Rp3_1b {
    class InterpreterResult {
        int cmd;
        int op;

        InterpreterResult(int cmd, int op) { // 2:pushのときだけこっち
            this.cmd = cmd;
            this.op  = op;
        }

        InterpreterResult(int cmd) { // 1:pop, 3:add, 4:sub, 5:mul, 6:div, -1:end
            this.cmd = cmd;
            this.op = 0;
        }
    }

    public InterpreterResult commandInterpreter(Scanner sc) {
        try {
            System.err.print("cmd:"); // コマンド催促
            String buf = sc.next(); // コマンドをもらう
            if (buf.equals("pop")) {
                return new InterpreterResult(1);
            } else if (buf.equals("push")) {
                return new InterpreterResult(2, sc.nextInt()); // pushのときだけその後ろの数字を読み取る
            } else if (buf.equals("add")) {
                return new InterpreterResult(3);
            } else if (buf.equals("sub")) {
                return new InterpreterResult(4);
            } else if (buf.equals("mul")) {
                return new InterpreterResult(5);
            } else if (buf.equals("div")) {
                return new InterpreterResult(6);
            } else if (buf.equals("end")) {
                return new InterpreterResult(-1);
            }
        } catch (Exception e) {
            System.err.println(e);
            return new InterpreterResult(-2);
        }
        return null;
    }

    public void commandExecution(MyStack stack, InterpreterResult ir) {
        int op; // 結果
        int operand1;
        int operand2;
        switch (ir.cmd) { // 1:pop, 2:push, 3:add, 4:sub, 5:mul, 6:div, -1:end
        case 1:
            op = stack.pop();
            System.out.println("POP " + op);
            break;
        case 2:
            stack.push(ir.op);
            System.out.println("PUSH " + ir.op);
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
    }

    public void interpreter() { // mainから呼ばれる
        boolean endFlag = false;
        String buf;
        int cmd = 0, op = 0;
        int operand1 = 0, operand2 = 0;
        MyStack stack = new MyStack(10,5);
        Scanner sc = new Scanner(System.in);
        while (endFlag == false) {
            InterpreterResult ir; // InterpreterResult:インナークラス(上で定義されている)
            ir = commandInterpreter(sc); // コマンド内容をもらう
            System.err.println("cmd = " + ir.cmd + " op = " + ir.op);
            if (ir.cmd == -1) { // cmd:endのとき
                endFlag = true;
            }
            if (ir.cmd < 0) { // cmd:errのとき
                continue;
            }
            commandExecution(stack, ir);
        }
    }

    public static void main(String args[]) {
        Rp3_1b mainObj = new Rp3_1b();
        mainObj.interpreter();
    }
}