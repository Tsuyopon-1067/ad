/*
 *  ジェネリック型スタックGenericStackのテストプログラム
 */
class GenericStackTest {

    static void testString()
    {
        GenericStack<String> stack = new GenericStack<String>();

        stack.push("a");  stack.push("b");  stack.push("c");
        System.out.println(stack);
        System.out.println("pop:" + stack.pop());
        System.out.println(stack);
        stack.push("d");  stack.push("e");  stack.push("f");
        System.out.println(stack);
        while (!stack.isEmpty()) {
                System.out.println("pop:" + stack.pop());
        }
        System.out.println("DONE! " + stack);
    }

    static void testInteger()
    {
        GenericStack<Integer> stack = new GenericStack<Integer>();

        stack.push(1);  stack.push(2);  stack.push(3);
        System.out.println(stack);
        System.out.println("pop:" + stack.pop());
        System.out.println(stack);
        stack.push(4);  stack.push(5);  stack.push(6);
        System.out.println(stack);
        while (!stack.isEmpty()) {
                System.out.println("pop:" + stack.pop());
        }
        System.out.println("DONE! " + stack);
    }

    public static void main(String args[])
    {
        testString();
        testInteger();
    }
}
