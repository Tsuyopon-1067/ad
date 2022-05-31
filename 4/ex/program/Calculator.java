
/*
 * 逆ポーランド電卓
 */
import java.io.*;
import java.util.*;

public class Calculator {
    private final MyStack stack; // スタック

    /**
     * 逆ポーランド電卓オブジェクトを生成する
     */
    public Calculator() {
        stack = new MyStack(); // スタックを生成する
    }

    /*
     * 逆ポーランド記法の数式の値を計算する
     *
     * @param exp 逆ポーランド記法の数式を表す文字列
     * 
     * @return 数式の値
     */
    public long compute(String exp) throws IOException {
        // スタックの内容をクリアする
        stack.clear();

        // 文字列expを1文字ずつ読み込むためのリーダーを用意する。
        // PushbackReaderクラスを利用して，読みすぎた文字をunreadで戻せる
        // ようにしている。
        // 文字列の末尾に，式の終わりを示すセミコロンを付けていることに注意。
        PushbackReader input = new PushbackReader(new StringReader(exp + ";"));

        // セミコロンに出会うまで，1文字読み込んで繰り返す
        int c; // 読み込んだ文字
        while ((c = input.read()) != ';') {
            char ch = (char) c;

            if (Character.isDigit(ch)) {
                // 読み込んだ文字が数字であった。
                // 数字が続く限り読み込んで，それを十進数として解釈して
                // long値に変換する。得られた値をスタックに積む
                long num = 0;
                while (Character.isDigit(ch)) {
                    num = 10 * num + (ch - '0');
                    c = input.read();
                    ch = (char) c;
                }
                input.unread(c); // 1文字読み過ぎているのでそれを戻す
                stack.push(num);
            } else {
                long a, b; // 作業用の変数
                switch (ch) {
                    case '+': // + 加算
                        b = (Long) stack.pop();
                        a = (Long) stack.pop();
                        stack.push(a + b);
                        break;
                    case '-': // - 減算
                        b = (Long) stack.pop();
                        a = (Long) stack.pop();
                        stack.push(a - b);
                        break;
                    case '*': // * 乗算
                        b = (Long) stack.pop();
                        a = (Long) stack.pop();
                        stack.push(a * b);
                        break;
                    case '/': // / 除算
                        b = (Long) stack.pop();
                        a = (Long) stack.pop();
                        stack.push(a / b);
                        break;
                    case ' ': // 空白文字 何もしないで読み飛ばす
                    case '\t':
                    case '\r':
                        break;
                    default: // それ以外の文字ならエラー
                        throw new IllegalArgumentException(
                                "不正な文字" + ch + "がありました。");
                }
            }
        }

        // 計算結果をスタックから取り出して返す。
        if (!stack.isEmpty()) {
            return (Long) stack.pop();
        } else {
            // スタックが空の場合には，式が無かったので，例外をスローする。
            throw new IllegalArgumentException("式がありません。");
        }
    }

    /*
     * 逆ポーランド電卓のメインプログラム
     * 標準入力から読み込んだ式を評価して結果を表示する
     */
    public static void main(String args[]) throws IOException {
        // 標準入力から1行ずつ読むために，リーダーを用意する。
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // 逆ポーランド電卓を生成する。
        Calculator calculator = new Calculator();

        // 標準入力から式を1行を読み込んで，電卓で値を求めて表示する
        String line;
        while ((line = input.readLine()) != null) { // 1行読み込む
            try {
                // 入力された式の値を計算して表示する
                long answer = calculator.compute(line);
                System.out.println("値は " + answer + " です。");
            } catch (EmptyStackException e) {
                // スタックが空の場合には，メッセージ文字列がないので，
                // 自前でメッセージを表示する
                System.out.println("式が正しくありません。");
            } catch (Exception e) {
                // それ以外の例外では，例外のメッセージを表示する
                System.out.println(e.getMessage());
            }
        }
    }
}
