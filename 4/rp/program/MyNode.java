public class MyNode {
    String name;
    MyNode next;
    MyNode prev;

    MyNode(String name) {
        this.name = name;
        next = null;
        prev = null;
    }

    public String toString() {
        return this.prev.name + " <== [" + this.name + "] ==> " + this.next.name;
    }

    public String getName() {
        return name;
    }
}
