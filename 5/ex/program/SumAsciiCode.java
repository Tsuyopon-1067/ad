
// サンプルプログラム  SumAsciiCode.java
import java.util.Scanner;

class SumAsciiCode {
    public static void main(String[] argv) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("String: ");
            String line = sc.next();
            if (line.equals("quit")) break;
            int t = 0;
            int s = 0;
            for (int i = 0; i < line.length(); i++) {
                System.out.print("line.charAt(" + i + ") = [" + line.charAt(i) + "]");
                s = (int) line.charAt(i); // cast char to int
                System.out.println(" -> " + s);
                t = t + s;
            }
            System.out.println("total: " + t);
        }
        sc.close();
    }
}
