class MyKeyword {
    private String key;
    private String data;

    MyKeyword(String key, String data) {
        this.key = key;
        this.data = data;
    }

    // getter your code
    public String getKey() {
        return this.key;
    }

    public String getData() {
        return this.data;
    }

    // setter your code
    public void setKey(String key) {
        this.key = key;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        String str = key + " : ";
        if (data.length() > 28) {
            str = str + data.substring(0, 27) + "..";
        } else {
            str = str + data;
        }
        return str;
    }

    public String toStringAll() {
        return key + " : " + data;
    }
}
