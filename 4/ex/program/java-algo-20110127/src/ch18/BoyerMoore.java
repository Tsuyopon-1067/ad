 /*
  * Boyer-Mooreのアルゴリズムによる文字列探索
  */
import java.util.*;

 public class BoyerMoore
 {
     /*
      * 文字列textから文字列patternを探索する（BM法）
      *
      * @param text     テキスト（探索の対象となる文字列）
      * @param pattern  パターン（探し出す文字列）
      * @return         見つかった位置を返す。見つからなければ-1を返す
      */
     public static int search(String text, String pattern)
     {
         int patLen  = pattern.length(); // パターンの長さ
         int textLen = text.length();    // テキストの長さ
 
         // テキストとパターンの不一致が見つかったときに，
         // どれだけずらすかを示す表
         int [] skip = new int[65536];
 
         // 表skipを作成する
         Arrays.fill(skip, patLen);
         for (int x = 0; x < patLen - 1; x++) {
             skip[pattern.charAt(x)] = patLen - x - 1;
         }
 
         // 注目しているテキストの位置を表すポインタ
         // パターンを後ろから前に向かって比較するので，
         // 「パターンの長さ-1」に初期化する
         int i = patLen - 1;
 
         // テキストの最後尾に行き当たるまで繰り返す
         while (i < textLen) {
 
             // 注目しているパターンの位置を表すポインタ
             // パターンの最後の文字を指すようにする
             int j = patLen - 1;
 
             // テキストとパターンが一致する間，繰り返す
             while (text.charAt(i) == pattern.charAt(j)) {
 
                 // 最初の文字まで一致したら，探索は成功である
                 if (j == 0) {
                     return i;
                 }
 
                 // ポインタiとjをそれぞれ1文字分戻す
                 i--;  j--;
             }
 
             // 一致しなかったので，パターンをずらす
             i = i + Math.max(skip[text.charAt(i)], patLen - j);
         }
 
         // 結局，見つからなかった
         return -1;
     }
 }
