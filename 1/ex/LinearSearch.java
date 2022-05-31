import java.util.Scanner;

class LinearSearch {
    static final int NUM = 11;
    static int a[] = {0, 1, 8, 27, 64, 125, 216, 343, 512, 729, 1000};
/*
    static final int NUM = 4;
    static int a[] = {4096, 512, 16, 1};

    static final int NUM = 6;
    static int a[] = {1, 1, 2, 3, 5, 8};
*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int key;
        do {
            System.out.print("index: ");
            key = sc.nextInt();
            if (key == -99999) break;
            int idx = search(key);
            if (idx != -1) {
                System.out.printf("%d is found.\n", key);
            } else {
                System.out.printf("%d is not found.\n", key);
            }
        } while (key != -99999);
        
    }
    static int search(int key) {
        int res;
        for (res = 0; res < NUM; ++res) {
           if (a[res] == key) return res;
        }
        return -1;
    }
}
