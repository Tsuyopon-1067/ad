#include <stdio.h>

#define LENGTH 256
#define NUM 3

int a[] = {3, 5, 11};

int search(int key) {
    int res = -1;
    int size = sizeof(a) / sizeof(a[0]);
    for (int i = 0; i < size; ++i) {
        if (a[i] == key) {
            res = i;
        }
    }
    return res;
}

int main(void) {
    char input[LENGTH];
    int r, n;
    while (1) {
        printf("index: ");
        fgets(input, LENGTH-1, stdin);
        sscanf(input, "%d", &n);
        if (n == -999999) break; // 終了 9が6個
        r = search(n);
        if (r == -1) {
            printf("%d is not found.\n", n);
        } else {
            printf("%d is found.\n", n);
        }
    }
    return 0;
}
