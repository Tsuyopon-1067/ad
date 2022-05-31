abstract public class MySearch {
    protected int datanum;
    protected int maxnum;
    protected MyData[] data;
    protected int compareCount;

    MySearch(int max){
        data = new MyData[max];
        datanum = 0;
        maxnum = max;
    }

    public void resetCompareCount(){
        compareCount = 0;
    }

    public void setCompareCount(int count){
        compareCount = count;
    }

    public int getCompareCount(){
        return compareCount;
    }

    abstract public boolean insert(String key, String data);
    abstract public MyData search(String key);
    abstract public int searchIndex(String key);
    abstract public MyData delete(String key);
    
    public void printData(){
        for(int i = 0; i < datanum; i++){
            System.out.println(data[i].toStringSimple());
        }
    }
    
    public int strcmp(String key1, String key2){
        compareCount++;
        return key1.compareTo(key2);
    }
}
