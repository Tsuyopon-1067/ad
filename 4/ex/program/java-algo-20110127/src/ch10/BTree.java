/*
 * B��
 */
import java.io.*;

public class BTree
{
    /**
     * B�؂̐�
     */
    private abstract class Node
    {
        int  serial;            // �V���A���ԍ��iB�؂̏����ɂ͕s�v�����C
                                // toString�ɂ��\�������₷�����邽�߁j

        // Node�N���X�͒��ۃN���X�ł���B
        // ���ۂ̃C���X�^���X�́C�T�u�N���X�ł���InternalNode�N���X�i�����߁j�C
        // �܂���Leaf�N���X�i�t�j�Ƃ��Đ�������
    }

    /**
     * B�؂̓�����
     */
    private class InternalNode extends Node
    {
        int           nChilds;  // ���̐߂������Ă���q�̐�
        Node[]        child;    // ������
        Integer[]     low;      // �e�����؂̍ŏ��̗v�f

        /**
         * �R���X���N�^�F��̓����߂𐶐�����
         */
        private InternalNode()
        {
            serial = serialNumber++;    // �V���A���ԍ���t����
            nChilds = 0;
            child = new Node[MAX_CHILD];
            low   = new Integer[MAX_CHILD];
        }

        /**
         * �L�[key�����f�[�^�͉��Ԗڂ̕����؂ɓ��邩�𒲂ׂ�
         *
         * @param key  ���ׂ�ׂ��L�[
         * @return     �L�[key�����Ԗڂ̕����؂ɓ��邩��Ԃ�
         */
        private int  locateSubtree(Integer key)
        {
            for (int  i = nChilds - 1; i > 0; i--) {
                if (key.compareTo(low[i]) >= 0) {
                    return i;
                }
            }
            return 0;
        }
    }

    /**
     * B�؂̗t
     */
    private class Leaf extends Node
    {
        Integer     key;        // �t�������Ă���L�[�̒l
        Object      data;       // �t�Ɋi�[����f�[�^

        /**
         * �R���X���N�^�F�t�𐶐�����
         *
         * @param  key  ���̗t�����L�[
         * @param  data ���̗t�����f�[�^
         */
        private Leaf(Integer key, Object data)
        {
            serial = serialNumber++;    // �V���A���ԍ���t����
            this.key  = key;
            this.data = data;
        }
    }

    private Node  root;                 // B�؂̍�
    private int   serialNumber = 0;     // Node�ɕt�Ԃ���V���A���ԍ�

    // search���\�b�h�͒T���ɐ�������ƁC�������t��currentLeaf�t�B�[
    // ���h�ɃZ�b�g����B�������t��data�t�B�[���h�̒l�𓾂�ɂ�
    // getData���\�b�h���Cdata�t�B�[���h�ɒl���Z�b�g����ɂ�setData��
    // �\�b�h���g���B
    // delete���\�b�h��insert���\�b�h���Ăяo���ƁC���̕ϐ��̓N���A��
    // ���B
    private Leaf currentLeaf;

    final private static int  MAX_CHILD = 5;    // �ܕ���
    final private static int  HALF_CHILD = ((MAX_CHILD+1)/2);

    /**
     * �R���X���N�^�F���B�؂𐶐�����
     */
    public BTree()
    {
        root = null;
    }

    /**
     * B�؂���L�[key��T������B
     * �L�[key�����t��������΁C�����currentLeaf�t�B�[���h�ɃZ�b�g����
     * 
     * ���̃��\�b�h�͒T���̐��ۂ�������񂾂���Ԃ��B
     * ���ۂɃL�[key�ɑΉ�����l�𓾂�ɂ́Csearch�ɐ����������
     * getData���\�b�h���Ăяo�����ƁB�܂��CsetData���\�b�h���Ăяo���΁C
     * �L�[key�ɑΉ�����l��ς��邱�Ƃ��ł���
     *
     * @param key  �T�����ׂ��L�[
     * @return  �L�[key�����t���������true�C������Ȃ����false��Ԃ�
     */
    public boolean search(Integer key)
    {
        currentLeaf = null;     // currentLeaf�t�B�[���h��null�ɂ���

        // ��̖؂ł���΁C������false��Ԃ�
        if (root == null) {
            return false;
        } else {
            // ������n�߂āC�t�ɂ��ǂ���܂œ����߂����ǂ�
            Node p = root;
            while (p instanceof InternalNode) {
                InternalNode node = (InternalNode)p;
                p = node.child[node.locateSubtree(key)];
            }

            // �^����ꂽ�L�[�ƁC�t�ɃZ�b�g����Ă���L�[���r����
            Leaf leaf = (Leaf)p;
            if (key.compareTo(leaf.key) == 0) {
                // �T���ɐ��������B���̗t��currentLeaf�t�B�[���h�ɃZ�b�g���āC
                // true��Ԃ�
                currentLeaf = leaf;
                return true;
            } else {
                return false;           // �T���Ɏ��s�̂�false��Ԃ�
            }
        }
    }

    /**
     * �Ō�ɐ�������search���\�b�h���������v�f�̃f�[�^�𓾂�
     *
     * @return  ���O��search���ꂽ�v�f�̃f�[�^�idata�t�B�[���h�j�B
     *          ���O��search�ȊO�iinsert�Cdelete�j�����s����Ă����ꍇ�C
     *          ����ђ��O��search�����s�����ꍇ�ɂ́Cnull��Ԃ�
     */
    public Object getData()
    {
        if (currentLeaf == null) {
            return null;
        } else {
            return currentLeaf.data;
        }
    }

    /**
     * �Ō�ɐ�������search���\�b�h���������v�f�����f�[�^���Z�b�g����
     *
     * @param  data  �Z�b�g���ׂ��l
     * @return  �Z�b�g�ɐ���������true�C���O��search�ȊO�iinsert�Cdelete�j��
     *          ���s����Ă����ꍇ�C����ђ��O��search�����s�����ꍇ
     *          �ɂ�false��Ԃ�
     */
    public boolean setData(Object data)
    {
        if (currentLeaf == null) {
            return false;
        } else {
            currentLeaf.data = data;
            return true;
        }
    }

    /**
     * InsertAux���\�b�h�̌���
     */
    private static class InsertAuxResult
    {
        Node       newNode;     // �V�����߂�������ꍇ�ɁC���̐߂�����B
        Integer    lowest;      // �V�����߂�������ꍇ�ɁCnewNode���w��������
                                // �̍ŏ��v�f������

        private InsertAuxResult(Node newNode, Integer lowest)
        {
            this.newNode = newNode;
            this.lowest  = lowest;
        }
    }

    /**
     * �w�肵���߂ɑ΂��āC�L�[key�����v�f��}������iinsert�̉������j
     *
     * @param pnode   ������pnode��nth�Ԗڂ̎q�ɑ΂��đ}�����s���B
     *                pnode��null�̏ꍇ�͍����ΏۂƂȂ�
     * @param nth     ������pnode��nth�Ԗڂ̎q�ɑ΂��đ}�����s��
     * @param key     �}������v�f�̃L�[
     * @param data    �}������v�f�̃f�[�^
     *
     * @return  ���ʂ�\��InsertAuxResult�^�̃I�u�W�F�N�g�B
     *          �L�[key�����łɓo�^�ς݂Ȃ�null
     */
    private InsertAuxResult insertAux(InternalNode pnode, int nth,
                                      Integer key, Object data)
    {
        // �v�f�̑}���̑ΏۂƂȂ�߂ւ̃����N��ϐ�thisNode�ɓ����
        Node thisNode;
        if (pnode == null) {
            thisNode = root;
        } else {
            thisNode = pnode.child[nth];
        }

        if (thisNode instanceof Leaf) {
        // ���̐߂͗t�ł���

            // ����ȍ~�C���̐߂�tleaf�Ƃ��ĎQ�Ƃ���
            Leaf leaf = (Leaf)thisNode;

            // ���łɓo�^�ς݂ł���΁C�������Ȃ���null��Ԃ�
            if (leaf.key.compareTo(key) == 0) {
                return null;
            } else {
              // �V���ɗtnewLeaf�����蓖�Ă�
                Leaf newLeaf = new Leaf(key, data);

                // �����C���蓖�Ă��tnewLeaf�̂ق����tleaf�����������Ȃ�C
                // newLeaf��leaf�̈ʒu����ꊷ����
                if (key.compareTo(leaf.key) < 0) {
                    // ���̐߂ɂ́C�V�������蓖�Ă��tnewLeaf������
                    if (pnode == null) {
                        root = newLeaf;
                    } else {
                        pnode.child[nth] = newLeaf;
                    }

                    // �V���Ɋ��蓖�Ă��t�Ƃ��āCleaf��񍐂���
                    return new InsertAuxResult(leaf, leaf.key);
                } else {
                    // �V���Ɋ��蓖�Ă��t�Ƃ��āCnewLeaf��񍐂���
                    return new InsertAuxResult(newLeaf, key);
                }
            }
        } else {
        // ���̐߂͓����߂ł���
            // ����ȍ~�C���̐߂������node�Ƃ��ĎQ�Ƃ���
            InternalNode node = (InternalNode)thisNode;

            // ���Ԗڂ̕����؂ɑ}�����邩�����߂�
            int pos = node.locateSubtree(key);

            // �����؂ɑ΂��āC�������g���ċA�Ăяo������
            InsertAuxResult  result = insertAux(node, pos, key, data);

          // �����������s���Ă��Ȃ���΁C���̂܂ܖ߂�
            if (result == null || result.newNode == null) {
                return result;
            }

          // �������s���Ă����̂ŁC��node�ɂ���iresult.newNode�j��}������
            // ��node�ɒǉ��̗]�n�����邩�H
            if (node.nChilds < MAX_CHILD) {
              // �ǉ��̗]�n���������̂ŁC�K�؂Ȉʒu�ɑ}������
                for (int i = node.nChilds - 1; i > pos; i--) {
                    node.child[i+1] = node.child[i];
                    node.low  [i+1] = node.low  [i];
                }
                node.child[pos+1] = result.newNode;
                node.low  [pos+1] = result.lowest;
                node.nChilds++;
                return new InsertAuxResult(null, null);
            } else {
              // �ǉ��̗]�n���Ȃ��̂ŁC��node��2�ɕ������Ȃ���΂Ȃ�Ȃ�
                // �V����������newNode�����蓖�Ă�
                InternalNode newNode = new InternalNode();

                // ��result.newNode���ǂ���̐߂ɑ}������邩�ŁC�ꍇ��������
                if (pos < HALF_CHILD - 1) {
                  // ��result.newNode�́C��node�̑��ɑ}�������
                    // �܂��CHALF_CHILD-1�`MAX_CHILD-1�Ԗڂ̕����؂��C
                    // ��node�����newNode�ւƈڂ�
                    for (int i = HALF_CHILD-1, j = 0; i < MAX_CHILD; i++, j++) {
                        newNode.child[j] = node.child[i];
                        newNode.low  [j] = node.low  [i];
                    }
                    // 0�`HALF_CHILD-2�Ԗڂ̕����؂̊Ԃ̓K�؂Ȉʒu�ɁC
                    // ��result.newNode��}������
                    for (int i = HALF_CHILD-2; i > pos; i--) {
                        node.child[i+1] = node.child[i];
                        node.low  [i+1] = node.low  [i];
                    }
                    node.child[pos+1] = result.newNode;
                    node.low  [pos+1] = result.lowest;
                } else {
                  // ��result.newNode�͐�newNode�̑��ɑ}�������
                    // HALF_CHILD�`MAX_CHILD-1�Ԗڂ̕����؂��C��newNode��
                    // �ړ�����B�����ɁC��result.newNode��K�؂Ȉʒu��
                    // �}������
                    int j = MAX_CHILD - HALF_CHILD;
                    for (int i = MAX_CHILD-1; i >= HALF_CHILD; i--) {
                        if (i == pos) {
                            newNode.child[j]   = result.newNode;
                            newNode.low  [j--] = result.lowest;
                        }
                        newNode.child[j]   = node.child[i];
                        newNode.low  [j--] = node.low  [i];
                    }
                    if (pos < HALF_CHILD) {
                        newNode.child[0] = result.newNode;
                        newNode.low  [0] = result.lowest;
                    }
                }
                // �q�̐�nChild���X�V����
                node   .nChilds = HALF_CHILD;
                newNode.nChilds = (MAX_CHILD + 1) - HALF_CHILD;

                // �������č��ꂽ�߂��t�B�[���hnewNode�ɁC
                // �܂����̍ŏ��l��lowest�t�B�[���h�ɕԂ�
                return new InsertAuxResult(newNode, newNode.low[0]);
            }
        }
    }

    /**
     * B�؂ɗv�f��}������
     *
     * @param key  �}������v�f�̃L�[
     * @param data �}������v�f�̃f�[�^
     * @return  �v�f�̑}���ɐ���������true�C���łɃL�[key�����v�f��
     *          �o�^����Ă�����C�������Ȃ���false��Ԃ�
     */
    public boolean insert(Integer key, Object data)
    {
        // currentLeaf�t�B�[���h��null�ɂ���
        currentLeaf = null;

        // �؂���̏ꍇ�ɂ́C�t�������true��Ԃ�
        if (root == null) {
            root = new Leaf(key, data);
            return true;
        } else {
            // �؂���łȂ��ꍇ�ɂ́CinsertAux���\�b�h���Ăяo���āC
            // �v�f�̑}�����s��
            InsertAuxResult result = insertAux(null, -1, key, data);

            // �������ʂ�null�Ȃ�C���łɃL�[key�͓o�^����Ă���̂ŁC
            // ���̂܂�false��Ԃ�
            if (result == null) {
                return false;
            }

            // �����������s��ꂽ�Ȃ�C�؂̍�����1�i��������
            if (result.newNode != null) {
                InternalNode newNode = new InternalNode();
                newNode.nChilds  = 2;
                newNode.child[0] = root;
                newNode.child[1] = result.newNode;
                newNode.low[1]   = result.lowest;
                root = newNode;
            }
            return true;
        }
    }

    /**
     * ������p��x�Ԗڂ�x+1�Ԗڂ̕����؂��ĕҐ�����B
     * �����C�������K�v�Ȃ�C���ׂĂ̗v�f��x�Ԗڂ̕����؂ɏW�߂�
     * true��Ԃ��B�������s�v�Ȃ�false��Ԃ�
     *
     * @param p   ������p
     * @param x   ������p��x�Ԗڂ�x+1�Ԗڂ̕����؂��ĕҐ�����
     * @return    �������K�v�Ȃ�true�C�K�v�łȂ����false
     */
    private static boolean mergeNodes(InternalNode p, int x)
    {
        InternalNode  a = (InternalNode)p.child[x];    // x�Ԗڂ̕�����
        InternalNode  b = (InternalNode)p.child[x+1];  // x+1�Ԗڂ̕�����
        b.low[0] = p.low[x+1];

        int   an = a.nChilds;                          // ������a�̎q�̐�
        int   bn = b.nChilds;                          // ������b�̎q�̐�

        if (an + bn <= MAX_CHILD) {
          // ������a��b�𕹍����Ȃ���΂Ȃ�Ȃ�
            // b�̎q�����ׂ�a�ֈړ�����
            for (int i = 0; i < bn; i++) {
                a.child[i+an] = b.child[i];
                b.child[i]    = null;           // �s�v�ȎQ�Ƃ�����
                a.low  [i+an] = b.low  [i];
            }
            a.nChilds += bn;                    // �q�̐����X�V����
            // ### ������b��������� ###
            return true;                        // �����������Ƃ�ʒm����
        } else {
          // ������a��b�ƂŁC�߂��ĕ��z����
            int  move;                          // �ړ�����v�f�̌�

            // ������a�ɕ��z���ׂ��q�̐������߂�
            int n = (an + bn) / 2;
            if (an > n) {
              // ������a���畔����b�ւƈړ�����
                move = an - n;                  // move�̎q��a����b�ֈڂ�
                // b�̗v�f���E�ɂ��炷
                for (int i = bn - 1; i >= 0; i--) {
                    b.child[i+move] = b.child[i];
                    b.low  [i+move] = b.low  [i];
                }
                // a����b��move�̎q���ړ�����
                for (int i = 0; i < move; i++) {
                    b.child[i] = a.child[i+n];
                    a.child[i+n] = null;        // �s�v�ȎQ�Ƃ�����
                    b.low  [i] = a.low  [i+n];
                }
            } else {
              // ������b���畔����a�ւƈړ�����
                move = n - an;                  // move�̎q��b����a�ֈڂ�
                // b����a��move�̎q���ړ�����
                for (int i = 0; i < move; i++) {
                    a.child[i+an] = b.child[i];
                    a.low  [i+an] = b.low  [i];
                }
                // b�̗v�f�����֋l�ߍ��킹��
                for (int i = 0; i < bn - move; i++) {
                    b.child[i] = b.child[i+move];
                    b.child[i+move] = null;     // �s�v�ȎQ�Ƃ�����
                    b.low  [i] = b.low  [i+move];
                }
            }
            // �q�̌����X�V����
            a.nChilds = n;
            b.nChilds = an + bn - n;
            // ������b�̍ŏ��l���p�ɃZ�b�g����
            p.low[x+1] = b.low[0];
            return false;
        }
    }

    // deleteAux���\�b�h�̖߂�l
    //    �l�̈Ӗ��́CdeleteAux���\�b�h�̃R�����g���Q�Ƃ̂��ƁB
    private enum Status {
        OK,
        OK_REMOVED,
        OK_NEED_REORG,
        NOT_FOUND
    }

    /**
     * ��thisNode����C�L�[key�����v�f���폜����idelete�̉������j
     *
     * @param thisNode ���̐߁i�܂��͂��̕����؁j����v�f���폜����
     * @param key  �폜����v�f�̃L�[
     * @return  �ȉ��̒l��Ԃ��B
     *             OK:           �폜�ɐ����BthisNode�ɂ͉��̕ω����Ȃ�
     *             OK_REMOVED:   �폜�ɐ����BthisNode���̂��̂��폜���ꂽ
     *             OK_NEED_REORG:�폜�ɐ����BthisNode�̎q�����Ȃ��iHALF_CHILD
     *                          �ȉ��j�Ȃ����̂ŁC�ĕҐ����K�v�ɂȂ���
     *             NOT_FOUND:   �폜�Ɏ��s�B�L�[key�����q�͌�����Ȃ�����
     */
    private static Status deleteAux(Node thisNode, Integer key)
    {
        if (thisNode instanceof Leaf) {
        // ���̐߂͗t�ł���
            // ����ȍ~�C���̐߂�tleaf�Ƃ��ĎQ�Ƃ���
            Leaf leaf = (Leaf)thisNode;

            // ���̗t�̃L�[��key����������΁C�폜����
            if (leaf.key.compareTo(key) == 0) {
                // ### ������leaf��������� ###
                return Status.OK_REMOVED;
            } else {
                // �L�[����v���Ȃ��B�܂�C�^����ꂽ�L�[�����v�f��
                // ���݂��Ȃ�����
                return Status.NOT_FOUND;
            }
        } else {
        // ���̐߂͓����߂ł���
            // ����ȍ~�C���̐߂������node�Ƃ��ĎQ�Ƃ���
            InternalNode node = (InternalNode)thisNode;

            boolean joined = false;// �ĕҐ��̌��ʁC�����؂��������ꂽ���H

            // �ǂ̕����؂���폜���邩�����߂�
            int pos = node.locateSubtree(key);
            // ���̕����؂ɑ΂��āC�������g���ċA�Ăяo������
            Status result = deleteAux(node.child[pos], key);
            // �����؂ɉ��̕ω����Ȃ���΁C���̂܂ܖ߂�
            if (result == Status.NOT_FOUND || result == Status.OK) {
                return result;
            }

          // ������pos���ĕҐ�����K�v�����邩�H
            if (result == Status.OK_NEED_REORG) {
                int sub = (pos == 0) ? 0 : pos - 1;
                // ������sub��sub+1���ĕҐ�����
                joined = mergeNodes(node, sub);
                // �����Csub��sub+1����������Ă�����C������sub+1��
                // node����폜����K�v������
                if (joined) {
                    pos = sub + 1;
                }
            }

            Status myResult = Status.OK;  // ���̃��\�b�h���Ԃ��߂�l�B
                                          // �Ƃ肠����OK�ɂ��Ă���
          // ������pos���폜���ꂽ
            if (result == Status.OK_REMOVED || joined) {
                // node�̕����؂��l�ߍ��킹��
                for (int i = pos; i < node.nChilds - 1; i++) {
                    node.child[i] = node.child[i+1];
                    node.low[i]   = node.low[i+1];
                }
                node.child[node.nChilds-1] = null;      // �s�v�ȎQ�Ƃ�����
                // �����Cnode�̕����؂̐���HALF_CHILD��菬�����Ȃ�
                // �ĕҐ����K�v�ł���
                if (--node.nChilds < HALF_CHILD) {
                    myResult = Status.OK_NEED_REORG;
                }
            }
            return myResult;
        }
    }

    /**
     * B�؂���v�f���폜����
     *
     * @param key  �폜����v�f�̃L�[
     * @return  �폜�ɐ��������true�C�v�f�����݂��Ȃ����false��Ԃ�
     */
    public boolean delete(Integer key)
    {
        // currentLeaf�t�B�[���h��null�ɂ���
        currentLeaf = null;

        // �؂���Ȃ��false��Ԃ�
        if (root == null) {
            return false;
        } else {
          // �؂���łȂ��ꍇ
            // deleteAux���\�b�h���Ăяo���āC�L�[key�����v�f���폜����
            Status  result = deleteAux(root, key);

            // ������Ȃ���΁Cfalse��Ԃ�
            if (result == Status.NOT_FOUND) {
                return false;
            }

            if (result == Status.OK_REMOVED) {
              // �����폜���ꂽ�̂ŁCroot��null��������i�؂���ɂȂ�j
                root = null;
            } else if (result == Status.OK_NEED_REORG
                       && ((InternalNode)root).nChilds == 1) {
              // �����ĕҐ����ꂽ���ʁC���̎q��1�����ɂȂ�����C
              // �؂̍�����1���炷
              // ### Node p = root; ###
                root = ((InternalNode)root).child[0];
              // ### ������p��������� ###
            }
            return true;
        }
    }

    /**
     * B�؂̓��e��\���������Ԃ��itoString�̉������j
     *
     * @param p  ���̐߂�艺�̕�����\��������𐶐����ĕԂ�
     * @return   ��p��艺�̕�����\��������
     */
    private static String toStringAux(Node p)
    {
        // �t�������߂��ŏ����𕪂���
        if (p instanceof Leaf) {
          // �t�ł���
            Leaf l = (Leaf) p;
            return "Leaf #" + l.serial + " key=" + l.key;
        } else {
          // �����߂ł���
            InternalNode n = (InternalNode)p;
            String s = "Node #" + n.serial + " (" + n.nChilds + " childs): ";
            s += "#" + n.child[0].serial + " ";
            for (int i = 1; i < n.nChilds; i++) {
                s += "[" + n.low[i] + "] #" + n.child[i].serial + " ";
            }
            s += "\n";
            for (int i = 0; i < n.nChilds; i++) {
                s += toStringAux(n.child[i]) + "\n";
            }
            return s;
        }
    }

    /**
     * B�؂̓��e��\���������Ԃ�
     * �i���ۂ̏�����toStringAux���\�b�h���s���j
     *
     * @return B�؂̓��e��\��������
     */
    public String toString()
    {
        if (root == null) {
            return "<�؂͋�ł�>";
        } else {
            return toStringAux(root);
        }
    }

    /**
     * �e�X�g�p�̃��C�����[�`��
     *
     *     ">"�Ƃ����v�����v�g���\�������̂ŁC�R�}���h����͂���ƁC
     *     ���s���ʂ��\�������
     *
     *     �R�}���h�ꗗ�in�͐����j
     *          +n      : n��}������
     *          -n      : n���폜����
     *          /n      : n��T������
     *          =string : ���O�ɐ�������/�R�}���h�Ō������v�f�ɑ΂���l��
     *                    string�ɂ���
     *          p       : B�؂̓��e��\������
     *          q       : �I������
     */
    public static void main(String[] args) throws IOException
    {
        BTree tree = new BTree();

        // B�؂ɏ����f�[�^��}������
        int[] data = { 1, 100, 27, 45, 3, 135, 13 };
        for (int x: data) {
            tree.insert(x, "["+x+"]" );
        }

        // �R�}���h��1�s���͂��āC��������s����B
        // ������CEOF�ɂȂ�܂ŌJ��Ԃ�
        System.out.print(">");
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = input.readLine()) != null) {
            if (str.length() == 0) {    // ��s�͓ǂݔ�΂�
                System.out.print(">");
                continue;
            }

            // �擪��1�����i�R�}���h�j��command�ɓ����
            char  command = str.charAt(0);

            // ����������arg�ɓ����B���̍ۂɐ擪�̃X�y�[�X���폜����
            String arg = str.substring(1).trim();       

            // �R�}���h�ɂ���ĕ��򂷂�
            if (command == 'q') {
              // q�R�}���h: �I������
                break;
            } else if (command == 'p') {
              // p�R�}���h�FB�؂̓��e��\������
                System.out.println(tree);
            } else if (command == '=') {
              // =�R�}���h�F���O�ɐ�������/�R�}���h�̗v�f�̃f�[�^�ɃZ�b�g����
                if (tree.setData(arg)) {
                    System.out.println("�l" + arg + "�̐ݒ�ɐ������܂����B");
                } else {
                    System.out.println("�l" + arg + "�̐ݒ�Ɏ��s���܂����B");
                }
            } else if (command == '+' || command == '-' || command == '/') {
              // +�C-�C/�R�}���h�Ȃ�΁C�R�}���h�ɑ������l��num�ɓ���
                int num = 0;
                try {
                    num = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    System.err.println("�����ȊO�̂��̂��w�肳��܂���:" + arg);
                    continue;
                }

                if (command == '+') {
                  // +�R�}���h�F�v�f��}������
                    if (tree.insert(num, "[" + num + "]")) {
                        System.out.println(num + "�̑}���ɐ������܂����B");
                    } else {
                        System.out.println(num + "�̑}���Ɏ��s���܂����B");
                    }
                } else if (command == '-') {
                  // -�R�}���h�F�v�f���폜����
                    if (tree.delete(num)) {
                        System.out.println(num + "�̍폜�ɐ������܂����B");
                    } else {
                        System.out.println(num + "�̍폜�Ɏ��s���܂����B");
                    }
                } else if (command == '/') {
                  // /�R�}���h�F�v�f��T������
                    if (tree.search(num)) {
                        System.out.println(num + "��������܂����B�l=" + tree.getData());
                    } else {
                        System.out.println(num + "��������܂���ł����B");
                    }
                }
            } else {
                System.out.println("�R�}���h" + command +
                                   "�̓T�|�[�g����Ă��܂���B");
            }
            System.out.print(">");
        }
    }
}
