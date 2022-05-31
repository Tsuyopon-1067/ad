public class LinearSearch extends MySearch {
    
    LinearSearch(int max){
        super(max);
    }

    public boolean insert(String key, String data){
        if (maxnum > datanum) {
            MyData newData = new MyData(key, data);
            this.data[datanum] = newData;
            datanum++;
            return true;
        }else{
            return false;
        }
    }

    public MyData search(String key){
        int index = searchIndex(key);
        if (index == -1) {
            return null;
        }else{
            return data[index];
        }

    }

    public int searchIndex(String key){
        /*    using strcmp(data[i].getKey(), key) == 0    */
        /*    strcmp is in MySearch.java */
        for (int i = 0; i < datanum; ++i) {
            if (strcmp(data[i].getKey(), key) == 0) {
                return i;
            }
        }
        return -1;
    }

    public MyData delete(String key){
        int deleteDataIndex = searchIndex(key);
        if (deleteDataIndex == -1){
            return null;
        }
        MyData deletedData = data[deleteDataIndex];
        for(int i = deleteDataIndex; i < datanum - 1; i++){
            data[i] = data[i+1];
        }
        datanum--;
        return deletedData;
    }
}
