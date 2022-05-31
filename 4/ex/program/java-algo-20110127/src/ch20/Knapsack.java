/**
 * 動的計画法を使ってナップザック問題を解く
 */
import java.util.*;

public class Knapsack {
    int[]  size;        // 品物の大きさ
    int[]  value;       // 品物の価値
    int  N;             // 品物の種類の数

    /**
     * ナップザック問題を表すオブジェクトを生成する
     * @param  size   品物の大きさを表す配列
     * @param  value  品物の価値を表す配列
     */
    public Knapsack(int[] size, int[] value) {
        // パラメータsizeとvalueの要素の個数が等しいことを確認する
        if (size.length != value.length) {
            throw new IllegalArgumentException(
                          "'size'と'value'の要素数が一致していません");
        }

        // 品物の種類の数をセットする
        this.N     = size.length;

        // 配列sizeの複製を作成してフィールドsizeにセットする
        this.size = (int[])size.clone();

        // 配列valueの複製を作成してフィールドvalueにセットする
        this.value = (int[])value.clone();
    }

    /**
     * 大きさmのナップザックに対する解を求めて表示する
     * @param m  ナップザックの大きさ
     */
    public void solve(int m) {

        // 現時点でナップザックに詰め込んだ品物の価値の合計
        int[] total = new int[m+1];     // 全要素が0に初期化される

        // 最後に選んだ品物
        int[] choice = new int[m+1];
        Arrays.fill(choice, -1);        // 全要素を-1に初期化する

        // 品物iを入れたときの価値の合計
        int repackTotal;

        // ナップザックの大きさを表示する
        System.out.printf("ナップザックの大きさは%d%n", m);

        // 品物0～iまでを考慮に入れる
        for (int i = 0; i < N; i++) {

            // 大きさjのナップザックに対して、品物を詰め込んでみる
            for (int j = size[i]; j <= m; j++) {

                // もし品物iを入れたとすると、価値の合計はいくらに
                // なるかを計算して、変数repackTotalに入れる
                repackTotal = total[j - size[i]] + value[i];

                // もし品物iを入れたほうが（入れないより）価値が
                // 大きくなるのなら、品物iを入れる
                if (repackTotal > total[j]) {
                    total[j] = repackTotal;
                    choice[j] = i;
                }
            }

            // 配列total、choiceの中身をダンプする
            System.out.printf("i = %d%n", i);
            System.out.printf("total  = ");
            for (int j = 0; j <= m; j++) {
                System.out.printf("%4d", total[j]);
            }
            System.out.printf("%nchoice = ");
            for (int j = 0; j <= m; j++) {
                System.out.printf("%4d", choice[j]);
            }
            System.out.printf("\n");
        }

        // どの品物をナップザックに入れたかを表示する
        for (int i = m; choice[i] >= 0; i -= size[choice[i]]) {
            System.out.printf("品物 %d（価値%d）を詰め込む%n",
                              choice[i], value[choice[i]]);
        }
        System.out.printf("価値の合計 = %d%n", total[m]);
    }

    /**
     * 起動法とメッセージmessageを表示して，プログラムを異常終了させる
     * @param message    表示するメッセージ
     */
    private static void abort(String message)
    {
        System.err.printf("起動法: java Knapsack 大きさ%n");
        System.err.printf("%s%n", message);
        System.exit(1);         // ステータスコード1は異常終了を示す
    }

    /**
     * ナップザック問題を解くメインプログラム。
     * コマンドライン引数によって，ナップザックの大きさを指定する
     */
    public static void main(String args[]) {

        // ナップザック問題を解決するためのオブジェクトを生成する
        Knapsack  knapsack =
            new Knapsack(
                    new int[] { 2, 3, 5,  7,  9},   // 品物の大きさ
                    new int[] { 2, 4, 7, 11, 14});  // 品物の価値

        // コマンドラインからナップザックの大きさを得る
        int size = 0;   // ナップザックの大きさ
        if (args.length != 1) {
            abort("パラメータの個数が違います。");
        }
        try {
            size = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            abort("大きさには正の整数を指定してください。");
        }
        if (size <= 0) {
            abort("大きさには正の整数を指定してください。");
        }

        // ナップザック問題を解く
        knapsack.solve(size);
    }
}
