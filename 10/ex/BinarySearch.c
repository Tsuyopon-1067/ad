#include <stdio.h>

#define LENGTH 256
#define NUM 3

int a[] = {3, 5, 11};

int search(int key) {
    int l, m, r;
    l = 0;
    r = sizeof(a) / sizeof(a[0]);
    while (l <= r) {
        m = (l+r)/2;
        if (a[m] == key) {
            return m;
        } else if (a[m] < key) {
            l = m+1;
        } else {
            r = m-1;
        }
    }
    return -1;
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
