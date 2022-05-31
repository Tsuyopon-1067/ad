class MyQueue2 {

    private MyNode front = null;
    private MyNode rear  = null;
    private int size = 0;
    private MyNode sentinelNode;
    static final String SENTINEL = "*SENTINEL*";

    MyQueue2(){
        sentinelNode = new MyNode(SENTINEL);
        clear();
    }

    public void clear(){
        front = sentinelNode;
        rear  = sentinelNode;

        front.next = rear;
        rear.prev = front;

        size = 0;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(String name){
        // your code

        size++;
        return;
    }

    public MyNode dequeue(){
        if (isEmpty()){
            return null;
        }else{
            // your code

            size--;

            if (size==0){
                // your code
            }
            return null;

        }
    }

    public String toString(){
        MyNode pos = front.next;
        String s = "MyQueue2=[\n";
        while(pos != front){
            s += "    " + pos + "\n";
            pos = pos.next;
        }
        s += "]\n  front=" + front + "\n  rear=" + rear;
        return s;
    }
}
