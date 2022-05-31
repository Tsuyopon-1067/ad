class MyHash {
    int searchCount;
    int insertCount;
    int deleteCount;

    int size;

    public int hash(String key) {
        int r = 0;
        for (int i = 0; i < key.length(); i++) {
            int buf = (int)(key.charAt(i));
            r = r + buf * buf;
        }
        return r % size;
    }

    public void clearcount() {
        searchCount = 0;
        insertCount = 0;
        deleteCount = 0;
    }

    public String countString() {
        return "searchCount : " + searchCount +
                " / insertCount : " + insertCount +
                " / deleteCount : " + deleteCount;
    }
}
