/**
 * 二分探索木の節
 */
class Node
{
    Integer data;           // この節のラベル
    Node    left;           // 左部分木
    Node    right;          // 右部分木

    /**
     * 二分木の節を生成する
     *
     * @param data     ラベル
     */
    Node(Integer data)
    {
        left       = null;
        right      = null;
        this.data  = data;
    }
}
