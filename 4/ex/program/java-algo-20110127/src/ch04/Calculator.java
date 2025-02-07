/*
 * �t�|�[�����h�d��
 */
import java.io.*;
import java.util.*;

public class Calculator
{
    private final MyStack stack;        // �X�^�b�N

    /**
     * �t�|�[�����h�d��I�u�W�F�N�g�𐶐�����
     */
    public Calculator()
    {
        stack = new MyStack();          // �X�^�b�N�𐶐�����
    }

    /*
     *  �t�|�[�����h�L�@�̐����̒l���v�Z����
     *
     *  @param  exp     �t�|�[�����h�L�@�̐�����\��������
     *  @return         �����̒l
     */
    public long compute(String exp) throws IOException
    {
        // �X�^�b�N�̓��e���N���A����
        stack.clear();

        // ������exp��1�������ǂݍ��ނ��߂̃��[�_�[��p�ӂ���B
        // PushbackReader�N���X�𗘗p���āC�ǂ݂�����������unread�Ŗ߂���
        // �悤�ɂ��Ă���B
        // ������̖����ɁC���̏I���������Z�~�R������t���Ă��邱�Ƃɒ��ӁB
        PushbackReader input =
                new PushbackReader(new StringReader(exp + ";"));

        // �Z�~�R�����ɏo��܂ŁC1�����ǂݍ���ŌJ��Ԃ�
        int c;                  // �ǂݍ��񂾕���
        while ((c = input.read()) != ';') {
            char ch = (char)c;

            if (Character.isDigit(ch)) {        
                // �ǂݍ��񂾕����������ł������B
                // ��������������ǂݍ���ŁC������\�i���Ƃ��ĉ��߂���
                // long�l�ɕϊ�����B����ꂽ�l���X�^�b�N�ɐς�
                long num = 0;
                while (Character.isDigit(ch)) {
                    num = 10 * num + (ch  - '0');
                    c = input.read();
                    ch = (char)c;
                }
                input.unread(c);    // 1�����ǂ݉߂��Ă���̂ł����߂�
                stack.push(num);
            } else {
                long a, b;      // ��Ɨp�̕ϐ�
                switch (ch) {
                case '+':       // +  ���Z
                    b = (Long)stack.pop(); a = (Long)stack.pop();
                    stack.push(a + b);
                    break;
                case '-':       // -  ���Z
                    b = (Long)stack.pop(); a = (Long)stack.pop();
                    stack.push(a - b);
                    break;
                case '*':       // *  ��Z
                    b = (Long)stack.pop(); a = (Long)stack.pop();
                    stack.push(a * b);
                    break;
                case '/':       // /  ���Z
                    b = (Long)stack.pop(); a = (Long)stack.pop();
                    stack.push(a / b);
                    break;
                case ' ':       // �󔒕���  �������Ȃ��œǂݔ�΂�
                case '\t':
                case '\r':
                    break;
                default:        // ����ȊO�̕����Ȃ�G���[
                    throw new IllegalArgumentException(
                                   "�s���ȕ���" + ch + "������܂����B");
                }
            }
        }

        //   �v�Z���ʂ��X�^�b�N������o���ĕԂ��B
        if (! stack.isEmpty()) {
            return (Long)stack.pop();
        } else {
            // �X�^�b�N����̏ꍇ�ɂ́C�������������̂ŁC��O���X���[����B
            throw new IllegalArgumentException("��������܂���B");
        }
    }

    /*
     * �t�|�[�����h�d��̃��C���v���O����
     *     �W�����͂���ǂݍ��񂾎���]�����Č��ʂ�\������
     */
    public static void main(String args[]) throws IOException
    {
        // �W�����͂���1�s���ǂނ��߂ɁC���[�_�[��p�ӂ���B
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

        // �t�|�[�����h�d��𐶐�����B
        Calculator  calculator = new Calculator();

        // �W�����͂��玮��1�s��ǂݍ���ŁC�d��Œl�����߂ĕ\������
        String line;
        while ((line = input.readLine()) != null) {    // 1�s�ǂݍ���
            try {
                // ���͂��ꂽ���̒l���v�Z���ĕ\������
                long answer = calculator.compute(line);
                System.out.println("�l�� " + answer + " �ł��B");
            } catch (EmptyStackException e) {
                // �X�^�b�N����̏ꍇ�ɂ́C���b�Z�[�W�����񂪂Ȃ��̂ŁC
                // ���O�Ń��b�Z�[�W��\������
                System.out.println("��������������܂���B");
            } catch (Exception e) {
                // ����ȊO�̗�O�ł́C��O�̃��b�Z�[�W��\������
                System.out.println(e.getMessage());
            }
        }
    }
}
