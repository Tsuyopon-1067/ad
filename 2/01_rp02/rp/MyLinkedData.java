public class MyLinkedData {
    private String key;
    private String data;
    MyLinkedData next;

    MyLinkedData(String key, String data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return this.key;
    }

    public String getData() {
        return this.data;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toStringSimple() {
        if (this.next != null) {
            return "[" + this.key + "] " + this.data + ": next [" + this.next.getKey() + "]";
        } else {
            return "[" + this.key + "] " + this.data + ": next [null]";
        }
    }

    // Step 3 を行う場合は元々のメソッド定義をコメントアウトし，以下の方を有効にしてください
    // public String toStringSimple(){
    // return "(" + this + ") " + this.key + ": " + this.data + ": next([" +
    // this.next + ")";
    // }

}
