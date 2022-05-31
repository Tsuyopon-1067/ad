class Rp4_1 {
    public static void main(String[] args){
        MyQueue2 q = new MyQueue2();
        q.enqueue("a");         System.out.println("enqueue a");
        q.enqueue("b");         System.out.println("enqueue b");
        q.enqueue("c");         System.out.println("enqueue c");
        System.out.println(q);
        System.out.println("dequeue: " + q. dequeue());
        System.out.println("dequeue: " + q. dequeue());
        System.out.println("dequeue: " + q. dequeue());
        System.out.println(q);
        q.enqueue("d");         System.out.println("enqueue d");
        q.enqueue("e");         System.out.println("enqueue e");
        System.out.println("dequeue: " + q. dequeue());
        System.out.println(q);
        q.enqueue("f");         System.out.println("enqueue f");
        System.out.println("dequeue: " + q. dequeue());
        System.out.println("dequeue: " + q. dequeue());
        System.out.println(q);
        q.clear();              System.out.println("clear q");
        System.out.println(q);
        q.enqueue("g");         System.out.println("enqueue g");
        q.enqueue("h");         System.out.println("enqueue h");
        System.out.println(q);
    }
}
