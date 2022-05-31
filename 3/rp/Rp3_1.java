import java.util.Scanner;

public class Rp3_1 {
    class InterpreterResult {
        int cmd;
        int op;

        InterpreterResult(int cmd, int op) {
            this.cmd = cmd;
            this.op  = op;
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
        int op;
        int operand1;
        int operand2;
        switch (ir.cmd) {
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

    public void interpreter() {
        boolean endFlag = false;
        String buf;
        int cmd = 0, op = 0;
        int operand1 = 0, operand2 = 0;
        MyStack stack = new MyStack(10);
        Scanner sc = new Scanner(System.in);
        while (endFlag == false) {
            InterpreterResult ir;
            ir = commandInterpreter(sc);
            System.err.println("cmd = " + ir.cmd + " op = " + ir.op);
            if (ir.cmd == -1) {
                endFlag = true;
            }
            if (ir.cmd < 0) {
                continue;
            }
            commandExecution(stack, ir);
        }
    }

    public static void main(String args[]) {
        Rp3_1 mainObj = new Rp3_1();
        mainObj.interpreter();
    }
}