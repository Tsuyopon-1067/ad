public class MyData {
    static private int NEXT_ID = 0;
    private int id;
    private String key;
    private String data;

    MyData(String key, String data) {
        this.id = NEXT_ID;
        NEXT_ID++;
        this.key = key;
        this.data = data;
    }

    public int getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public String getData() {
        return this.data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(String data) {
        this.data = data;
    }
    // 番号 key data
    public String toString() {
        return String.format("[%5d] %s: %s", this.id, this.key, this.data);
    }

    public String toStringSimple() {
        if (this.data.length() > 41) {
            return String.format("[%5d] %s: %s...", this.id, this.key, this.data.substring(0, 40));
        } else {
            return String.format("[%5d] %s: %s", this.id, this.key, this.data);
        }
    }
}
