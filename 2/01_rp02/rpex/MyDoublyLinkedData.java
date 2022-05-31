public class MyDoublyLinkedData {
    private String key;
    private String data;
    public int[] links;
    private int linkNum;

    MyDoublyLinkedData(String key, String data, linkNum) {
        this.key = key;
        this.data = data;
        this.linkNum = linkNum;
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
}