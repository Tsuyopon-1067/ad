 /*
  * �͂܂����@�ɂ�镶����T��
  */
 public class BruteForce
 {
     /*
      * ������text���當����pattern��T������i�͂܂����@�j
      *
      * @param text     �e�L�X�g�i�T���̑ΏۂƂȂ镶����j
      * @param pattern  �p�^�[���i�T���o��������j
      * @return         ���������ʒu��Ԃ��B������Ȃ����-1��Ԃ�
      */
     public static int search(String text, String pattern)
     {
         int patLen  = pattern.length(); // �p�^�[���̒���
         int textLen = text.length();    // �e�L�X�g�̒���
         int i = 0;      // ���ڂ��Ă���e�L�X�g�̈ʒu��\���|�C���^
         int j = 0;      // ���ڂ��Ă���p�^�[���̈ʒu��\���|�C���^
 
         // �e�L�X�g�̍Ō���ɍs�������邩�C�p�^�[����������܂ŌJ���
         while (i < textLen && j < patLen) {
 
             // �e�L�X�g�ƃp�^�[����1������r����
             if (text.charAt(i) == pattern.charAt(j)) {
 
                 // ��v�����B�e�L�X�g�ƃp�^�[���̃|�C���^��i�߂�
                 i++;    j++;
             } else {
 
                 // ��v���Ȃ�����
                 i = i - j + 1;  // �e�L�X�g�̃|�C���^�����ݒ��ڂ��Ă���
                                 // �擪����1�i�߂�B
                 j = 0;          // �p�^�[���̃|�C���^��擪�ɖ߂�
             }
         }
 
         // �����T��������������C�p�^�[�������������ʒu��Ԃ��B
         // ���s������-1��Ԃ�
         return (j == patLen) ? (i - j) : -1;
     }
 }
