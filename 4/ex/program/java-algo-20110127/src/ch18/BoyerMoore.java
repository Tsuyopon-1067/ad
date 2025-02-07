 /*
  * Boyer-Moore�̃A���S���Y���ɂ�镶����T��
  */
import java.util.*;

 public class BoyerMoore
 {
     /*
      * ������text���當����pattern��T������iBM�@�j
      *
      * @param text     �e�L�X�g�i�T���̑ΏۂƂȂ镶����j
      * @param pattern  �p�^�[���i�T���o��������j
      * @return         ���������ʒu��Ԃ��B������Ȃ����-1��Ԃ�
      */
     public static int search(String text, String pattern)
     {
         int patLen  = pattern.length(); // �p�^�[���̒���
         int textLen = text.length();    // �e�L�X�g�̒���
 
         // �e�L�X�g�ƃp�^�[���̕s��v�����������Ƃ��ɁC
         // �ǂꂾ�����炷���������\
         int [] skip = new int[65536];
 
         // �\skip���쐬����
         Arrays.fill(skip, patLen);
         for (int x = 0; x < patLen - 1; x++) {
             skip[pattern.charAt(x)] = patLen - x - 1;
         }
 
         // ���ڂ��Ă���e�L�X�g�̈ʒu��\���|�C���^
         // �p�^�[������납��O�Ɍ������Ĕ�r����̂ŁC
         // �u�p�^�[���̒���-1�v�ɏ���������
         int i = patLen - 1;
 
         // �e�L�X�g�̍Ō���ɍs��������܂ŌJ��Ԃ�
         while (i < textLen) {
 
             // ���ڂ��Ă���p�^�[���̈ʒu��\���|�C���^
             // �p�^�[���̍Ō�̕������w���悤�ɂ���
             int j = patLen - 1;
 
             // �e�L�X�g�ƃp�^�[������v����ԁC�J��Ԃ�
             while (text.charAt(i) == pattern.charAt(j)) {
 
                 // �ŏ��̕����܂ň�v������C�T���͐����ł���
                 if (j == 0) {
                     return i;
                 }
 
                 // �|�C���^i��j�����ꂼ��1�������߂�
                 i--;  j--;
             }
 
             // ��v���Ȃ������̂ŁC�p�^�[�������炷
             i = i + Math.max(skip[text.charAt(i)], patLen - j);
         }
 
         // ���ǁC������Ȃ�����
         return -1;
     }
 }
