 /*
  * 力まかせ法による文字列探索
  */
 public class BruteForce
 {
     /*
      * 文字列textから文字列patternを探索する（力まかせ法）
      *
      * @param text     テキスト（探索の対象となる文字列）
      * @param pattern  パターン（探し出す文字列）
      * @return         見つかった位置を返す。見つからなければ-1を返す
      */
     public static int search(String text, String pattern)
     {
         int patLen  = pattern.length(); // パターンの長さ
         int textLen = text.length();    // テキストの長さ
         int i = 0;      // 注目しているテキストの位置を表すポインタ
         int j = 0;      // 注目しているパターンの位置を表すポインタ
 
         // テキストの最後尾に行き当たるか，パターンが見つかるまで繰り返
         while (i < textLen && j < patLen) {
 
             // テキストとパターンを1文字比較する
             if (text.charAt(i) == pattern.charAt(j)) {
 
                 // 一致した。テキストとパターンのポインタを進める
                 i++;    j++;
             } else {
 
                 // 一致しなかった
                 i = i - j + 1;  // テキストのポインタを現在注目している
                                 // 先頭から1つ進める。
                 j = 0;          // パターンのポインタを先頭に戻す
             }
         }
 
         // もし探索が成功したら，パターンが見つかった位置を返す。
         // 失敗したら-1を返す
         return (j == patLen) ? (i - j) : -1;
     }
 }
