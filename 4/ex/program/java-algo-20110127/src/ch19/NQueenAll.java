/**
 * Nクイーンのメインプログラム（すべての解を表示）
 *
 *      起動法:  java NQueenAll クイーンの数
 */
public class NQueenAll {

    /**
     * 起動法とメッセージmessageを表示して，プログラムを異常終了させる
     */
    private static void abort(String message)
    {
        System.err.println("起動法: java NQueenAll クイーンの数");
        System.err.println(message);
        System.exit(1);         // ステータスコード1は異常終了を示す
    }

    /**
     * メインプログラム。Nクイーンのすべての解を表示する。
     * クイーンの個数は，コマンドライン引数で指定する
     */
    public static void main(String args[]) {

        // パラメータの個数は1個でなければならない
        if (args.length != 1) {
            abort("パラメータの個数が違います。");
        }

        // パラメータで指定されたクイーンの数を取得してnにセットする
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            abort("クイーンの数には正の整数を指定してください: " + args[0]);
        }
        if (n <= 0) {
            abort("クイーンの数には正の整数を指定してください: " + args[0]);
        }

        // Nクイーンのすべての解を表示する
        NQueen  nq = new NQueen(n);
        nq.tryQueenAll(0);
    }
}
