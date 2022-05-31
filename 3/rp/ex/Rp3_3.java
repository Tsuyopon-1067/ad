import java.util.Scanner;

public class Rp3_3 {
    class InterpreterResult {
        int cmd;
        int op;

        InterpreterResult(int cmd, int op) {
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
            } else if (buf.equals("ld")) {
                return new InterpreterResult(8);
            } else if (buf.equals("st")) {
                return new InterpreterResult(9);
            } else if (buf.equals("jnz")) {
                return new InterpreterResult(10);
            } else if (buf.equals("end")) {
                return new InterpreterResult(-1);
            }
        } catch (Exception e) {
            System.err.println(e);
            return new InterpreterResult(-2);
        }
        return null;
    }

    public boolean commandRegistration(MyMemory2 stack, InterpreterResult ir) {
        switch (ir.cmd) {
        case 2:
            stack.regist(ir.cmd);
            stack.regist(ir.op);
            return false;
        case -1:
        case 1:
        case 3:
        case 4:
        case 5:
        case 6:
        case 8:
        case 9:
        case 10:
            stack.regist(ir.cmd);
            return false;
        case 7:
            commandExecution(stack);
            
        }
        return true;
    }

    public void commandExecution(MyMemory2 stack) {
        int op = 0;
        int operand1;
        int operand2;

        stack.resetPC();
        op = stack.read();
        while (op != -1) {
            // System.out.println(stack);
            switch (op) {
            case 1:
                op = stack.pop();
                System.out.println("POP " + op);
                break;
            case 2:
                op = stack.read();
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
            case 8: // op1にあるデータをpush
                operand1 = stack.pop();
                op = stack.load(operand1);
                stack.push(op);
                System.out.println("LD (" + operand1 + "(= " + op + "))");
                break;
            case 9: // op2にop1を保存
                operand2 = stack.pop(); // address
                operand1 = stack.pop(); // value to be stored
                stack.save(operand2, operand1);
                System.out.println("ST (" + operand1 + " " +operand2 + ")");
                break;
            case 10: // op1が0でないときpcをop2に移動
                operand2 = stack.pop(); // jump adress
                operand1 = stack.pop(); // flag
                System.out.println("JNZ (" + operand1 + " " +operand2 + ")");
                if( operand1 != 0 ) {
                    stack.setPC(operand2);
                }
                break;
            }
            //stack.show();
            op = stack.read();
        }
    }

    public void interpreter() {
        boolean endFlag = false;
        MyMemory2 stack = new MyMemory2(60, 30, 30);
        Scanner sc = new Scanner(System.in);
        while (endFlag == false) {
            InterpreterResult ir;
            ir = commandInterpreter(sc);
            System.err.println("cmd = " + ir.cmd + " op = " + ir.op);
            endFlag = commandRegistration(stack, ir);
            // System.out.println(stack);
        }
    }

    public static void main(String args[]) {
        Rp3_3 mainObj = new Rp3_3();
        mainObj.interpreter();
    }
}